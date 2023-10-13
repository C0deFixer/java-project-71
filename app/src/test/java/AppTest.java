import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Differ;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class AppTest {
    final String FIRSTFILENAME = "file1.json";
    final String SECONDFILENAME = "file2.json";
    final String EXPECTEDRESULTFILENAME = "expected.txt";
    @Test
    public void testCompare() throws Exception{
        Path resourceDirectory = Paths.get("src","test","resources");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();

        Path firstFilePath = Paths.get(absolutePath,FIRSTFILENAME).toAbsolutePath().normalize();
        Path secondFilePath = Paths.get(absolutePath,SECONDFILENAME).toAbsolutePath().normalize();
        Path expectedFilePath = Paths.get(absolutePath,EXPECTEDRESULTFILENAME).toAbsolutePath().normalize();

        // Reading content
        String firstFileContent = Files.readString(firstFilePath);
        String secondFileContent = Files.readString(secondFilePath);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> map1 = objectMapper.readValue(firstFileContent, new TypeReference<Map<String, String>>() {
        });
        Map<String, String> map2 = objectMapper.readValue(secondFileContent, new TypeReference<Map<String, String>>() {
        });

        String expected = Files.readString(expectedFilePath);;
        String actual = Differ.generate(map1, map2);

        Assertions.assertEquals(actual,expected);
    }
}
