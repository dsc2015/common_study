
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.net.InetAddress;
import java.util.Iterator;
import java.util.Map;

/**
 * @author dushuangcheng
 * @description
 * @create 2017/7/7
 */
public class EsTransportClient {
    private static final String ENCODING = "utf-8";
    private TransportClient transportClient;
    private EsConfig esConfig;

    //注入配置信息
    public void setEsConfig(EsConfig esConfig) {
        this.esConfig = esConfig;
    }

    public TransportClient getClient() {
        if (this.transportClient == null) {
            Class var1 = EsTransportClient.class;
            synchronized (EsTransportClient.class) {
                if (this.transportClient == null) {
                    this.init();
                }
            }
        }

        return this.transportClient;
    }

    private void init() {
        if (this.esConfig == null) {
            // LOGGER.error("es server not config");
            throw new RuntimeException("es server not config");
        } else {
            try {
                Settings e = Settings.settingsBuilder().put("cluster.name", this.esConfig.getClusterName()).put("client.transport.sniff", this.esConfig.getSniff()).build();
                this.transportClient = TransportClient.builder().settings(e).build();
                Map serverInfoMap = this.esConfig.getServerInfoMap();
                Iterator iterator = serverInfoMap.entrySet().iterator();

                while (iterator.hasNext()) {
                    Map.Entry serverInfo = (Map.Entry) iterator.next();
                    this.transportClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName((String) serverInfo.getKey()), ((Integer) serverInfo.getValue()).intValue()));
                }

                if (null == this.transportClient) {
                    throw new RuntimeException("es client create fail");
                }
            } catch (Exception var5) {
                // LOGGER.error("es client create fail,e:{}", var5);
                throw new RuntimeException("es client create fail", var5);
            }
        }
    }

    public String executeHttpReq(String indexName, String typeName, String jsonContent) {
        String url = makeUpUrl(indexName, typeName);
        //创建一个客户端
        HttpClient httpClient = new HttpClient();
        //创建一个post方法
        PostMethod postMethod = new PostMethod(url);
        String result = null;
        try {
            postMethod.setRequestEntity(new StringRequestEntity(jsonContent, "application/json", "utf-8"));
            httpClient.getParams().setConnectionManagerTimeout(5000L);
            postMethod.getParams().setParameter("http.method.retry-handler", new DefaultHttpMethodRetryHandler(0, false));
            postMethod.getParams().setParameter("http.protocol.content-charset", "utf-8");
            postMethod.getParams().setParameter("http.socket.timeout", Integer.valueOf(this.esConfig.getHttpTimeout()));
            int e = httpClient.executeMethod(postMethod);
            if (e != 200) {
                result = null;
                return result;
            }
            result = new String(postMethod.getResponseBody(), "utf-8");
        } catch (Exception e) {
            return null;
        } finally {
            postMethod.releaseConnection();
        }
        return result;
    }

    private String makeUpUrl(String indexName, String typeName) {
        return this.esConfig.getHttpUrl() + indexName + "/" + typeName + "/" + "_search/";
    }
}
