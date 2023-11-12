package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import org.assertj.core.api.MapAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


public class AppTest {
    public static final String FIRST_FILE_JSON = "file1.json";
    public static final String SECOND_FILE_JSON = "file2.json";
    public static final String EXPECTED_RESULT_JSON = "expected_json.txt";

    public static final String FIRST_FILE_YAML = "file1.yaml";
    public static final String SECOND_FILE_YAML = "file2.yaml";
    public static final String EXPECTED_RESULT_YAML_PLAIN = "expected_yaml_plain.txt";
    public static final String EXPECTED_RESULT_YAML_STYLISH = "expected_yaml_stylish.txt";
    public static final String EXPECTED_RESULT_YAML_JSON = "expected_yaml_json.json";

    public static final String STYLISH_FORMAT = "stylish";
    public static final String PLAIN_FORMAT = "plain";
    public static final String JSON_FORMAT = "json";

    @Test
    @DisplayName("Test Json files compare stylish format")
    public void testGenerateJsonStylish() throws Exception {
        Path resourceDirectory = Paths.get("src", "test", "resources");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();

        Path firstFilePath = Paths.get(absolutePath, FIRST_FILE_JSON).toAbsolutePath().normalize();
        Path secondFilePath = Paths.get(absolutePath, SECOND_FILE_JSON).toAbsolutePath().normalize();
        Path expectedFilePath = Paths.get(absolutePath, EXPECTED_RESULT_JSON).toAbsolutePath().normalize();

        String expected = Files.readString(expectedFilePath);

        String actual = App.generate(firstFilePath.toString(), secondFilePath.toString(), STYLISH_FORMAT);

        Assertions.assertLinesMatch(Arrays.stream(actual.split("\n")), Arrays.stream(expected.split("\n")));

    }

    @Test
    @DisplayName("Test yaml files compare stylish format")
    public void testGenerateYamlStylish() throws Exception {
        Path resourceDirectory = Paths.get("src", "test", "resources");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();

        Path firstFilePath = Paths.get(absolutePath, FIRST_FILE_YAML).toAbsolutePath().normalize();
        Path secondFilePath = Paths.get(absolutePath, SECOND_FILE_YAML).toAbsolutePath().normalize();
        Path expectedFilePath = Paths.get(absolutePath, EXPECTED_RESULT_YAML_STYLISH).toAbsolutePath().normalize();


        String expected = Files.readString(expectedFilePath);

        String actual = App.generate(firstFilePath.toString(), secondFilePath.toString(), STYLISH_FORMAT);

        Assertions.assertLinesMatch(Arrays.stream(actual.split("\n")), Arrays.stream(expected.split("\n")));

    }

    @Test
    @DisplayName("Test yaml files compare plain format")
    public void testGenerateYamlPlain() throws Exception {
        Path resourceDirectory = Paths.get("src", "test", "resources");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();

        Path firstFilePath = Paths.get(absolutePath, FIRST_FILE_YAML).toAbsolutePath().normalize();
        Path secondFilePath = Paths.get(absolutePath, SECOND_FILE_YAML).toAbsolutePath().normalize();
        Path expectedFilePath = Paths.get(absolutePath, EXPECTED_RESULT_YAML_PLAIN).toAbsolutePath().normalize();


        String expected = Files.readString(expectedFilePath);

        String actual = App.generate(firstFilePath.toString(), secondFilePath.toString(), PLAIN_FORMAT);

        Assertions.assertLinesMatch(Arrays.stream(actual.split("\n")), Arrays.stream(expected.split("\n")));

    }

    @Test
    @DisplayName("Test yaml files compare json format")
    public void testGenerateYamlJson() throws Exception {
        Path resourceDirectory = Paths.get("src", "test", "resources");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();

        Path firstFilePath = Paths.get(absolutePath, FIRST_FILE_YAML).toAbsolutePath().normalize();
        Path secondFilePath = Paths.get(absolutePath, SECOND_FILE_YAML).toAbsolutePath().normalize();
        Path expectedFilePath = Paths.get(absolutePath, EXPECTED_RESULT_YAML_JSON).toAbsolutePath().normalize();


        String expected = Files.readString(expectedFilePath);

        String actual = App.generate(firstFilePath.toString(), secondFilePath.toString(), JSON_FORMAT);

        ObjectMapper om = new ObjectMapper();

        Map<String, Map<String, Object>> mapActual = om.readValue(actual, new TypeReference<Map<String, Map<String, Object>>>() {
        });
        Map<String, Map<String, Object>> mapExpected = om.readValue(expected, new TypeReference<Map<String, Map<String, Object>>>() {
        });

        for (Map.Entry<String, Map<String, Object>> entry : mapExpected.entrySet()) {
            if (mapActual.containsKey(entry.getKey())) {
                Map<String, Object> expectedValue = entry.getValue();
                assertThat(mapActual.get(entry.getKey())).isInstanceOf(expectedValue.getClass());
                MapAssert ama = new MapAssert(mapActual.get(entry.getKey()));
                ama.containsExactlyEntriesOf(expectedValue);

            } else throw new Exception("Property '" + entry.getKey() + "' expected , but missed in result of compare!");
        }
    }
}
