package collection.card.identy.api.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.List;

public class JacksonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    protected static Logger logger = LoggerFactory.getLogger(JacksonUtil.class);

    static {
        //对象的所有字段全部列入
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);

        //取消默认转换timesstamps(时间戳)形式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        //忽略空bean转json错误
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        //忽略在json字符串中存在，在java类中不存在字段，防止错误
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        //忽略字段大小写
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);

        //允许字段名不加双引号
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        //允许出现单引号
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);

        //时间格式
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    public static <T> T parseFromJson(String json, Class<T> clazz) {
        if (StringUtils.isEmpty(json) || clazz == null)
            return null;

        try {
            if (!StringUtils.isEmpty(json)) {
                return clazz.equals(String.class)
                        ? (T) json
                        : objectMapper.readValue(json, clazz);
            }
        } catch (Exception e) {
            logger.error("parseFromJson", e);
        }
        return null;
    }

    public static <T> String toJSONString(T obj) {
        if (obj == null) {
            return null;
        }

        try {
            return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            logger.error("toJSONString", e);
        }
        return null;
    }

    public static <T> List<T> parseListFromJson(String json, Class<?>... elements) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, elements);

        try {
            if (!StringUtils.isEmpty(json)) {
                return objectMapper.readValue(json, javaType);
            }
        } catch (Exception e) {
            logger.error("parseListFromJson", e);
        }
        return null;
    }
}
