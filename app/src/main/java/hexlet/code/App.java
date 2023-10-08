package hexlet.code;


import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.Callable;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;


@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<String> {
    @Parameters(paramLabel = "filepath1", description = "path to first file")
    String firstFile;
    @Parameters(paramLabel = "filepath2", description = "path to second file")
    String secondFile;
    @Option(names = { "-f", "--format" }, paramLabel = "format", description = "output format [default: stylish]")
    String format;
    @Override
    public String call() throws Exception { // your business logic goes here...
        String json1 = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
        //System.out.println(getData(json1).toString());
        String json2 = "{ \"color\" : \"White\", \"type\" : \"BMW\", \"model\" : \"525\" }";
        //System.out.println(getData(json1).toString());
        //System.out.println(getData(json2).toString());

        // get AbsolutePath
        Path firstFilePath = Paths.get(firstFile).toAbsolutePath().normalize();

        // Is Exist
        if (!Files.exists(firstFilePath)) {
            throw new Exception("First File '" + firstFilePath + "' does not exist");
        }

        // get AbsolutePath
        Path secondFilePath = Paths.get(secondFile).toAbsolutePath().normalize();

        // Is Exist
        if (!Files.exists(secondFilePath)) {
            throw new Exception("Second File '" + secondFilePath + "' does not exist");
        }

        // Reading content
        String firstFileContent = Files.readString(firstFilePath);
        String secondFileContent = Files.readString(secondFilePath);

/*        System.out.println(firstFileContent);
        System.out.println(secondFileContent);*/
        String result = Differ.generate(getData(firstFileContent), getData(secondFileContent));
        System.out.println(result);
        return result;
    }

    public static void main(String[] args) throws Exception {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
    public static Map<String, String> getData(String content) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(content, new TypeReference<Map<String,String>>(){});
    }
}
