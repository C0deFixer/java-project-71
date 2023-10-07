package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App {
    @Parameters(paramLabel = "filepath1", description = "path to first file")
    File fileFirst;
    @Parameters(paramLabel = "filepath2", description = "path to second file")
    File fileSecond;
    @Option(names = { "-f", "--format" }, paramLabel = "format", description = "output format [default: stylish]")
    File archive;
    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
        //System.out.println("Hello World!");
    }
}
