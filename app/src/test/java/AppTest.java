
import hexlet.code.App;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;


public class AppTest {
    public static final String FIRST_FILE_NAME = "file1.json";
    public static final String SECOND_FILENAME = "file2.json";
    public static final String EXPECTED_RESULTFILENAME = "expected.txt";

    @Test
    public void testCall() throws Exception {
        Path resourceDirectory = Paths.get("src", "test", "resources");
        resourceDirectory.normalize().toString();
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();

        Path firstFilePath = Paths.get(absolutePath, FIRST_FILE_NAME).toAbsolutePath().normalize();
        Path secondFilePath = Paths.get(absolutePath, SECOND_FILENAME).toAbsolutePath().normalize();
        Path expectedFilePath = Paths.get(absolutePath, EXPECTED_RESULTFILENAME).toAbsolutePath().normalize();

/*        // Reading content
        String firstFileContent = Files.readString(firstFilePath);
        String secondFileContent = Files.readString(secondFilePath);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> map1 = objectMapper.readValue(firstFileContent, new TypeReference<Map<String, String>>() {
        });
        Map<String, String> map2 = objectMapper.readValue(secondFileContent, new TypeReference<Map<String, String>>() {
        });*/

        String expected = Files.readString(expectedFilePath);

        String actual = App.generate(firstFilePath.toString(), secondFilePath.toString());

        Assertions.assertLinesMatch(Arrays.stream(actual.split("\n")), Arrays.stream(expected.split("\n")));

    }
}
