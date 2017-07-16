import com.jd.common.util.StringUtils;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.update.UpdateRequest;
import query.EsQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author dushuangcheng
 * @description ES与集群操作的客户端
 * @create 2017/7/7
 */
public class EsClient<T> {
    private final int zero = 0;
    private EsTransportClient esTransportClient;

    public EsClient() {
    }

    //注入一个transportClient
    public void setEsTransportClient(EsTransportClient esTransportClient) {
        this.esTransportClient = esTransportClient;
    }

    /**
     * @param
     * @return
     * @description 创建document
     * @Author dushuangcheng
     * @throw
     * @date 2017/7/7
     */
    public boolean createDoc(String index, String type, String id, T t) {
        return this.preCreateDoc(index, type, id, null, t);
    }

    public boolean createDoc(String index, String type, String id,String routing, T t) {
        return this.preCreateDoc(index, type, id, routing, t);
    }
    /**
     * @description 文档更新操作
     * @Author  dushuangcheng
     * @param
     * @return
     * @throw
     * @date   2017/7/7
     */
    public boolean updateDoc(String index,String type,String id,Map<String,Object> dataMap){
        boolean result=false;
        if(!StringUtils.isBlank(index)&&!StringUtils.isBlank(type)&&!StringUtils.isBlank(id)){
            //创建更新请求
            UpdateRequest updateRequest = new UpdateRequest(index, type, id);
            updateRequest.doc(JsonDataFormat.toJsonString2(dataMap));
            this.esTransportClient.getClient().update(updateRequest);
            result=true;

        }
        return result;
    }
    /**
     * @description 进行查询操作
     * @Author  dushuangcheng
     * @param
     * @return
     * @throw
     * @date   2017/7/7
     */
    public EsResult searchById(String index,String type,String id){
        EsResult esResult = new EsResult();
        if(!StringUtils.isBlank(index)&&!StringUtils.isBlank(type)&&!StringUtils.isBlank(id)){
            //调用es的api获取结果
            GetResponse response = esTransportClient.getClient().prepareGet(index, type, id).execute().actionGet();
            if(response.isExists()){
                ArrayList list = new ArrayList<>();
                this.processSourceData(esResult,list,response.getSource());
            }
        }
        return esResult;
    }

   /* public EsResult<T>  searchByQuery(String[] indexNames,String[] typeNames,EsQueryHelper esQueryHelper){

    }*/
    private boolean preCreateDoc(String index, String type, String id, String routing, T t) {
        boolean result = false;
        if (!StringUtils.isBlank(type) && !StringUtils.isBlank(index) && StringUtils.isBlank(id)) {
            IndexRequestBuilder indexRequestBuilder = this.esTransportClient.getClient().prepareIndex(index, type, id);
            if (StringUtils.isNotBlank(routing)) {
                indexRequestBuilder.setRouting(routing);
            }
            indexRequestBuilder.setSource(JsonDataFormat.toJsonString2(t)).execute().actionGet();
            result = true;
        }
        return result;
    }
    private void processSourceData(EsResult result, List<Map<String, Object>> sourceList, Map<String, Object> sourceMap) {
        sourceList.add(sourceMap);
        result.setData(sourceList);
    }

   /* private EsResult<T> searchByQuery(String[] indexNames, String[] typeNames, String routing, EsQueryHelper queryHelper, int pageNum, int pageSize, boolean isPage, boolean isCount){
        EsResult esResult = new EsResult();
        if(indexNames!=null&&indexNames.length>0){
            List<EsQuery> paramList = queryHelper.getParamList();
            EsParseQ
        }
    }*/


}
