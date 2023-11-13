package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class Parser {
    //Map of Lists file Format End Of, which supported end could be parsed
    public static final Map<String, List<String>> SUPPORTED_FILE_FORMATS = Map.of(
            "json", List.of("json", "jsn"),
            "yaml", List.of("yml", "yaml"));

    public static String getFileFormat(Path fileName) {
        String fileFormatresult = "";
        for (String fileFormat : SUPPORTED_FILE_FORMATS.keySet()) {
            for (String fileFormatEndOf : SUPPORTED_FILE_FORMATS.get(fileFormat)) {
                if (Utils.isFileFormatMatch(fileName.getFileName(), fileFormatEndOf)) {
                    fileFormatresult = fileFormat;
                    break;
                }
            }
        }

        return fileFormatresult.isEmpty() ? fileName.toString()
                .substring(fileName.toString().lastIndexOf(".")) : fileFormatresult;

    }


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
