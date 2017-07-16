
import ch.qos.logback.core.util.EnvUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;

import java.util.Map;

/**
 * @description
 * @author dushuangcheng
 * @create 2017/7/7
 */
public class EsConfig implements InitializingBean {
    private boolean sniff = true;
    private String clusterName;
    private Map<String, Integer> productServerInfoMap;
    private Map<String, Integer> devServerInfoMap;
    private Map<String, Integer> serverInfoMap;
    private EnvUtil envUtil;
    private String httpUrl;
    private int httpTimeout = 10000;

    public EsConfig() {
    }

    public Map<String, Integer> getServerInfoMap() {
        return this.serverInfoMap;
    }

    public String getHttpUrl() {
        return this.httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }

    public int getHttpTimeout() {
        return this.httpTimeout;
    }

    public void setHttpTimeout(int httpTimeout) {
        this.httpTimeout = httpTimeout;
    }

    public boolean getSniff() {
        return this.sniff;
    }

    public void setSniff(boolean sniff) {
        this.sniff = sniff;
    }

    public String getClusterName() {
        return this.clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public void afterPropertiesSet() throws Exception {
        if(!StringUtils.isBlank(this.httpUrl) && !StringUtils.isBlank(this.clusterName)) {
            if(!StringUtils.endsWith(this.httpUrl, "/")) {
                throw new RuntimeException(String.format("es 客户端配置信息httpUrl有误! 应该以 http:// 开头 && 以 / 结尾", new Object[0]));
            } else {
                EnvUtil var10000 = this.envUtil;
               /* if(!EnvUtil.isDevelopment()) {
                    this.serverInfoMap = this.productServerInfoMap;
                } else {
                    this.serverInfoMap = this.devServerInfoMap;
                }*/

                if(MapUtils.isEmpty(this.serverInfoMap)) {
                    throw new RuntimeException("es 服务器信息加载错误!");
                }
            }
        } else {
            throw new RuntimeException(String.format("es 客户端配置信息有误! httpUrl=%s, clusterName=%s", new Object[]{this.httpUrl, this.clusterName}));
        }
    }

    public void setProductServerInfoMap(Map<String, Integer> productServerInfoMap) {
        this.productServerInfoMap = productServerInfoMap;
    }

    public void setDevServerInfoMap(Map<String, Integer> devServerInfoMap) {
        this.devServerInfoMap = devServerInfoMap;
    }

    public void setServerInfoMap(Map<String, Integer> serverInfoMap) {
        this.serverInfoMap = serverInfoMap;
    }

    public void setEnvUtil(EnvUtil envUtil) {
        this.envUtil = envUtil;
    }
}
