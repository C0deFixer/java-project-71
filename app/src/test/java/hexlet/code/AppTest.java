package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;


public class AppTest {
    public static final String FIRST_FILE_JSON = "file1.json";
    public static final String SECOND_FILE_JSON = "file2.json";
    public static final String EXPECTED_RESULT_JSON = "expected_json.txt";

    public static final String FIRST_FILE_YAML = "file1.yaml";
    public static final String SECOND_FILE_YAML = "file2.yaml";
    public static final String EXPECTED_RESULT_YAML = "expected_yaml.txt";

    public static final String DEFAULT_FORMAT = "stylish";

    @Test
    @DisplayName("Test Json files compare")
    public void testGenerateJson() throws Exception {
        Path resourceDirectory = Paths.get("src", "test", "resources");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();

        Path firstFilePath = Paths.get(absolutePath, FIRST_FILE_JSON).toAbsolutePath().normalize();
        Path secondFilePath = Paths.get(absolutePath, SECOND_FILE_JSON).toAbsolutePath().normalize();
        Path expectedFilePath = Paths.get(absolutePath, EXPECTED_RESULT_JSON).toAbsolutePath().normalize();

        String expected = Files.readString(expectedFilePath);

        String actual = App.generate(firstFilePath.toString(), secondFilePath.toString(), DEFAULT_FORMAT);

        Assertions.assertLinesMatch(Arrays.stream(actual.split("\n")), Arrays.stream(expected.split("\n")));

    }

    @Test
    @DisplayName("Test yaml files compare")
    public void testGenerateYaml() throws Exception {
        Path resourceDirectory = Paths.get("src", "test", "resources");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();

        Path firstFilePath = Paths.get(absolutePath, FIRST_FILE_YAML).toAbsolutePath().normalize();
        Path secondFilePath = Paths.get(absolutePath, SECOND_FILE_YAML).toAbsolutePath().normalize();
        Path expectedFilePath = Paths.get(absolutePath, EXPECTED_RESULT_YAML).toAbsolutePath().normalize();


        String expected = Files.readString(expectedFilePath);

        String actual = App.generate(firstFilePath.toString(), secondFilePath.toString(), DEFAULT_FORMAT);

        Assertions.assertLinesMatch(Arrays.stream(actual.split("\n")), Arrays.stream(expected.split("\n")));

    }
}
