package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.MapAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.skyscreamer.jsonassert.JSONAssert;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

class DifferTest {

    public static final String STYLISH_FORMAT = "stylish";
    public static final String PLAIN_FORMAT = "plain";
    public static final String JSON_FORMAT = "json";

    @ParameterizedTest
    @CsvSource({
            ", file1.json, file2.json, expected_json.txt",
            "plain, file1.yaml, file2.yaml, expected_yaml_plain.txt",
            "stylish, file1.yaml, file2.yaml, expected_yaml_stylish.txt",
            "json, file1.yaml, file2.yaml, expected_yaml_json.json"
    })
    public void testGenerate(String format, String firstFileName, String secondFileName, String expectedFileName) throws Exception {
        String expected = readFileContent(expectedFileName);
        String actual;
        if (format == null || format instanceof String && format.isEmpty()) {
            actual = Differ.generate(getFilePath(firstFileName).toString(), getFilePath(secondFileName).toString());
        } else {
            actual = Differ.generate(getFilePath(firstFileName).toString(), getFilePath(secondFileName).toString(), format);
        }

        if (format != null && format.equals(JSON_FORMAT)) {
            JSONAssert.assertEquals(expected, actual, false);
        } else {
            Assertions.assertLinesMatch(Arrays.stream(actual.split("\n")), Arrays.stream(expected.split("\n")));
        }
    }

    public Path getFilePath(String fileName) {
        Path resourceDirectory = Paths.get("src", "test", "resources", "fixtures");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();
        return Paths.get(absolutePath, fileName).toAbsolutePath().normalize();
    }

    public String readFileContent(String fileName) throws Exception {
        String result = Files.readString(getFilePath(fileName));
        return result;
    }


}

