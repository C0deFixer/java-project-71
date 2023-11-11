package hexlet.code;


import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.Callable;


@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<String> {
    @Parameters(paramLabel = "filepath1", description = "path to first file")
    String firstFile;
    @Parameters(paramLabel = "filepath2", description = "path to second file")
    String secondFile;
    @Option(names = {"-f", "--format"},
            paramLabel = "format",
            defaultValue = "stylish",
            description = "output format [default: stylish]")
    String format;

    @Override
    public String call() throws Exception { // your business logic goes here...
        String result = generate(firstFile, secondFile, format);
        System.out.println(result);
        return result;
    }

    public static String generate(String file1, String file2, String format) throws Exception {
        // your business logic goes here...

        Path filePath1 = Utils.getAbsoluteFilePath(file1);
        Path filePath2 = Utils.getAbsoluteFilePath(file2);


        String fileFormat1 = Parser.getFileFormat(filePath1);
        String fileFormat2 = Parser.getFileFormat(filePath2);

        if (!fileFormat1.equals(fileFormat2)) {
            throw new Exception("Files has different formats !");
        }

        String contentFile1 = Utils.readContentFile(filePath1);
        String contentFile2 = Utils.readContentFile(filePath2);

        //Factory inside depends on file format  Throwable inside Check if file format unknown = unsupported
        Map<String, Object> fileContentMap1 = Parser.parseContentFileToMap(contentFile1, fileFormat1);
        Map<String, Object> fileContentMap2 = Parser.parseContentFileToMap(contentFile2, fileFormat2);

        Map<String, Map<String, Object>> resultMapCompare = Differ.generate(fileContentMap1, fileContentMap2);
        return Formatter.convertToString(resultMapCompare, format.replaceAll("\\s", ""));
    }

    public static void main(String[] args) throws Exception {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

}
