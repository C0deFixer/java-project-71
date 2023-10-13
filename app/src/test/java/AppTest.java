import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.App;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

public class AppTest {
    final String FIRSTFILENAME = "file1.json";
    final String SECONDFILENAME = "file2.json";
    final String EXPECTEDRESULTFILENAME = "expected.txt";
    @Test
    public void testCall() throws Exception{
        Path resourceDirectory = Paths.get("src","test","resources");
        resourceDirectory.normalize().toString();
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();

        Path firstFilePath = Paths.get(absolutePath,FIRSTFILENAME).toAbsolutePath().normalize();
        Path secondFilePath = Paths.get(absolutePath,SECONDFILENAME).toAbsolutePath().normalize();
        Path expectedFilePath = Paths.get(absolutePath,EXPECTEDRESULTFILENAME).toAbsolutePath().normalize();

/*        // Reading content
        String firstFileContent = Files.readString(firstFilePath);
        String secondFileContent = Files.readString(secondFilePath);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> map1 = objectMapper.readValue(firstFileContent, new TypeReference<Map<String, String>>() {
        });
        Map<String, String> map2 = objectMapper.readValue(secondFileContent, new TypeReference<Map<String, String>>() {
        });*/

        String expected = Files.readString(expectedFilePath);;

        String actual = App.generate(firstFilePath.toString(),secondFilePath.toString());

        Assertions.assertLinesMatch(Arrays.stream(actual.split("\n")),Arrays.stream(expected.split("\n")));

    }
}
