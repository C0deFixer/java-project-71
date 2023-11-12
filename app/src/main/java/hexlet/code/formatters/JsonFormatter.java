package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class JsonFormatter {

    public static String convertToString(Map<String, Map<String, Object>> map) throws Exception {
        ObjectMapper om = new ObjectMapper();
        String result = om.writeValueAsString(map);
        return result;
    }
}


