package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Utils {
    public static String readJsonContentFile(String filePathString) throws Exception {
        // get AbsolutePath
        Path filePath = Paths.get(filePathString).toAbsolutePath().normalize();

        // Is Exist
        if (!Files.exists(filePath)) {
            throw new Exception("File '" + filePathString + "' does not exist");
        }

        return Files.readString(filePath);
    }

    public static Map<String, String> getJsonDataAsMap(String content) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(content, new TypeReference<Map<String,String>>(){});
    }
}
