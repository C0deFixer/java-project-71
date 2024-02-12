package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.skyscreamer.jsonassert.JSONAssert;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;


class DifferTest {

    @ParameterizedTest
    @DisplayName("Test default (stylish) format")
    @CsvSource({
        "file1.json, file2.json, expected_stylish.txt",
        "file1.yaml, file2.yaml, expected_stylish.txt",
    })
    public void testGenerateDefaultFormat(
            String firstFileName,
            String secondFileName,
            String expectedFileName) throws Exception {
        String expected = readFileContent(expectedFileName);
        String actual;

        actual = Differ.generate(getFilePath(firstFileName).toString(),
                getFilePath(secondFileName).toString());

        Assertions.assertLinesMatch(Arrays.stream(actual.split("\n")),
                Arrays.stream(expected.split("\n")));
    }

    @ParameterizedTest
    @DisplayName("Test stylish & plain format output")
    @CsvSource({
        "stylish, file1.json, file2.json, expected_json.txt",
        "stylish, file1.yaml, file2.yaml, expected_stylish.txt",
        "plain, file1.json, file2.json, expected_plain.txt",
        "plain, file1.yaml, file2.yaml, expected_plain.txt"

    })
    public void testGenerateTxt(String format,
                                String firstFileName,
                                String secondFileName,
                                String expectedFileName) throws Exception {
        String expected = readFileContent(expectedFileName);
        String actual;
        actual = Differ.generate(getFilePath(firstFileName).toString(),
                getFilePath(secondFileName).toString(), format);
        Assertions.assertLinesMatch(Arrays.stream(actual.split("\n")),
                Arrays.stream(expected.split("\n")));
    }

    @ParameterizedTest
    @DisplayName("Test JSON format output")
    @CsvSource({
        "json, file1.json, file2.json, expected_json.json",
        "json, file1.yaml, file2.yaml, expected_json.json"
    })

    public void testGenerateJSON(String format,
                                 String firstFileName,
                                 String secondFileName,
                                 String expectedFileName) throws Exception {
        String expected = readFileContent(expectedFileName);
        String actual;
        actual = Differ.generate(getFilePath(firstFileName).toString(),
                getFilePath(secondFileName).toString(), format);

        JSONAssert.assertEquals(expected, actual, false);
    }

    public Path getFilePath(String fileName) {
        Path resourceDirectory = Paths.get("src", "test", "resources", "fixtures");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();
        return Paths.get(absolutePath, fileName).toAbsolutePath().normalize();
    }

    public String readFileContent(String fileName) throws Exception {
        return Files.readString(getFilePath(fileName));
    }


}

