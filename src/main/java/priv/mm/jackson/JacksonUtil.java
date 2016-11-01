package priv.mm.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JackSon简单工具类
 * Created by MaoMao on 2016/6/22.
 */
public final class JacksonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }


    /**
     * JSON to Map
     *
     * @param json 传入字符串
     * @return Map
     */
    public static Map Json2Map(String json) {
        Map map = null;
        if (StringUtils.isEmpty(json)) {
            map = new HashMap();
        } else {
            try {
                map = objectMapper.readValue(json, Map.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * Json to JsonNode
     *
     * @param json 传入字符串
     * @return JsonNode
     */
    public static JsonNode Json2JsonNode(String json) {
        JsonNode node = null;
        try {
            node = objectMapper.readTree(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return node;
    }

    /**
     * JOSN to List
     *
     * @param json 传入字符串
     * @return
     */
    public static List Json2List(String json) {
        List list = null;
        if (StringUtils.isEmpty(json)) {
            list = new ArrayList();
        } else {
            try {
                list = objectMapper.readValue(json, List.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * Object to JSON
     *
     * @param object 传入对象
     * @return JSON
     */
    public static String Object2Json(Object object) {
        String json = null;
        if (object == null) {
            json = "{}";
        } else {
            try {
                StringWriter sw = new StringWriter();
                objectMapper.writeValue(sw, object);
                json = sw.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return json;
    }

    public static void main(String[] args) {
        String str = "[\"a\",\"b\"]";
        List list = JacksonUtil.Json2List(str);
        System.out.println(list);
    }
}
