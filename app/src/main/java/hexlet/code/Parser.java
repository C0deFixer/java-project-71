package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {


    public static Map<String, Object> parseContentFileToMap(String content, String fileFormat) throws Exception {

        switch (fileFormat) {
            case "json":
                return getJsonDataAsMap(content);
            case "yaml":
                return getYamlDataAsMap(content);
            default:
                throw new Exception("Unknown File format: '" + fileFormat + "' !");
        }
    }

    public static Map<String, Object> getJsonDataAsMap(String content) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> objectMap = objectMapper.readValue(content, new TypeReference<Map>() {
        });
        return objectMap;
    }

    public static Map<String, Object> getYamlDataAsMap(String content) throws Exception {
        ObjectMapper objectMapper = new YAMLMapper();
        // Method inherit from com.fasterxml.jackson.databind.ObjectMapper;
        Map<String, Object> objectMap = objectMapper.readValue(content, new TypeReference<Map>() {
        });
        return objectMap;
    }

}
