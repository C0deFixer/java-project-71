package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {

    public static String generate(String file1, String file2) throws Exception {
        return generate(file1, file2, "stylish");
    }

    public static String generate(String file1, String file2, String format) throws Exception {

        Path filePath1 = getAbsoluteFilePath(file1);
        Path filePath2 = getAbsoluteFilePath(file2);


        String fileFormat1 = getFileFormat(file1);
        String fileFormat2 = getFileFormat(file2);

        if (!fileFormat1.equals(fileFormat2)) {
            throw new Exception("Files have different formats !");
        }

        String contentFile1 = readContentFile(filePath1);
        String contentFile2 = readContentFile(filePath2);

        //Factory inside depends on file format  Throwable inside Check if file format unknown = unsupported
        Map<String, Object> fileContentMap1 = Parser.parseContentFileToMap(contentFile1, fileFormat1);
        Map<String, Object> fileContentMap2 = Parser.parseContentFileToMap(contentFile2, fileFormat2);

        Map<String, Map<String, Object>> resultMapCompare = MapDiffer.compare(fileContentMap1, fileContentMap2);
        return Formatter.convertToString(resultMapCompare, format);
    }

    public static Path getAbsoluteFilePath(String filePathString) {
        // get AbsolutePath
        return Paths.get(filePathString).toAbsolutePath().normalize();
    }

    public static String readContentFile(Path filePath) throws Exception {
        // Is Exist
        if (!Files.exists(filePath)) {
            throw new Exception("File '" + filePath + "' does not exist");
        }
        return Files.readString(filePath);
    }

    public static String getFileFormat(String fileName) throws Exception {
        int indexOfDelimiter = fileName.lastIndexOf(".");
        if (indexOfDelimiter == -1 | indexOfDelimiter == fileName.length() - 1) {
            throw new Exception("File format couldn't be defined");
        } else {
            return fileName.substring(indexOfDelimiter + 1);
        }
    }

}

