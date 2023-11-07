package hexlet.code;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    /*@ParameterizedTest
    //@ValueSource(ints = {12,35,-56})
    @CsvFileSource(resources = "../test.csv", numLinesToSkip = 1);

    @DisplayName("Test Parse String Content File To Map")
    void testParseContentFileToMap() {
        Path resourceDirectory = Paths.get("src", "test", "resources");
        resourceDirectory.normalize().toString();
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();

        Path firstFilePath = Paths.get(absolutePath, FIRST_FILE_NAME).toAbsolutePath().normalize();
        Path secondFilePath = Paths.get(absolutePath, SECOND_FILENAME).toAbsolutePath().normalize();
        Path expectedFilePath = Paths.get(absolutePath, EXPECTED_RESULTFILENAME).toAbsolutePath().normalize();

*//*        // Reading content
        String firstFileContent = Files.readString(firstFilePath);
        String secondFileContent = Files.readString(secondFilePath);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> map1 = objectMapper.readValue(firstFileContent, new TypeReference<Map<String, String>>() {
        });
        Map<String, String> map2 = objectMapper.readValue(secondFileContent, new TypeReference<Map<String, String>>() {
        });*//*

        String expected = Files.readString(expectedFilePath);
    }

    @TestFactory
    Collection<ParserTest>*/
}