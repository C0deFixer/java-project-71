package hexlet.code;


import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

import static hexlet.code.Utils.getJsonDataAsMap;
import static hexlet.code.Utils.readJsonContentFile;

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


        // Reading content using method of Utils Class
/*        String firstFileContent = readJsonContentFile(firstFile);
        String secondFileContent = readJsonContentFile(secondFile);*/

/*        System.out.println(firstFileContent);
        System.out.println(secondFileContent);*/
        //String result = Differ.generate(getJsonDataAsMap(firstFileContent), getJsonDataAsMap(secondFileContent));
        String result = generate(firstFile, secondFile);
        System.out.println(result);
        return result;
    }

    public static String generate(String file1, String file2) throws Exception { // your business logic goes here...

        // Reading content using method of Utils Class
        String firstFileContent = readJsonContentFile(file1);
        String secondFileContent = readJsonContentFile(file2);

        String result = Differ.generate(getJsonDataAsMap(firstFileContent), getJsonDataAsMap(secondFileContent));
        return result;
    }
    public static void main(String[] args) throws Exception {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

}
