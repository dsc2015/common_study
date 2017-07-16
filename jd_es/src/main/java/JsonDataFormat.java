import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

import java.util.Date;

/**
 * @description
 * @author dushuangcheng
 * @create 2017/7/7
 */
public class JsonDataFormat {
    private static SerializeConfig mapping = new SerializeConfig();
    private static ObjectSerializer BIG_LONG_SERIALIZER = new BigLongSerializer();
    private static SimpleDateFormatSerializer simpleDateFormatSerializer = new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormatSerializer simpleDateFormatSerializer2 = new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss.SSS");

    public JsonDataFormat() {
    }

    public static String toJsonString2(Object object) {
        String json = null;

        try {
            mapping.put(Date.class, simpleDateFormatSerializer2);
            json = JSON.toJSONString(object, mapping, new SerializerFeature[0]);
        } catch (Exception var3) {
           // LOGGER.error("object convert json toJsonString2 an exception occurs.", var3);
        }

        return json;
    }

    public static String toJsonString(Object object) {
        String json = null;

        try {
            mapping.put(Date.class, simpleDateFormatSerializer);
            mapping.put(Long.class, BIG_LONG_SERIALIZER);
            json = JSON.toJSONString(object, mapping, new SerializerFeature[0]);
        } catch (Exception var3) {
            //LOGGER.error("object convert json convert an exception occurs.", var3);
        }

        return json;
    }

    public static String toJsonString(Object object, String dateFormat) {
        String json = null;

        try {
            SimpleDateFormatSerializer e = new SimpleDateFormatSerializer(dateFormat);
            mapping.put(Date.class, e);
            mapping.put(Long.class, BIG_LONG_SERIALIZER);
            json = JSON.toJSONString(object, mapping, new SerializerFeature[0]);
        } catch (Exception var4) {
            //LOGGER.error("The custom time type, object convert json convert an exception occurs.", var4);
        }

        return json;
    }
}
