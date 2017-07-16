
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.jd.fastjson.serializer.JSONSerializer;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @description
 * @author dushuangcheng
 * @create 2017/7/7
 */
public class BigLongSerializer implements ObjectSerializer {
    public BigLongSerializer() {
    }

    public void write(com.alibaba.fastjson.serializer.JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        if(object == null) {
            serializer.getWriter().writeNull();
        } else {
            if(((Long)object).longValue() < 100000000000000L) {
                serializer.getWriter().writeLong(((Long)object).longValue());
            } else {
                serializer.write(String.valueOf(object));
            }

        }
    }
}
