package hexlet.code;


import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;


@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public final class App implements Callable<String> {
    @Parameters(paramLabel = "filepath1", description = "path to first file")
    private String firstFile;
    @Parameters(paramLabel = "filepath2", description = "path to second file")
    private String secondFile;
    @Option(names = {"-f", "--format"},
            paramLabel = "format",
            defaultValue = "stylish",
            description = "output format [default: stylish]")
    private String format;

    @Override
    public String call() throws Exception { // your business logic goes here...
        String result = Differ.generate(firstFile, secondFile, format.replaceAll("\\s", ""));
        System.out.println(result);
        return result;
    }


    public static void main(String[] args) throws Exception {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

}
