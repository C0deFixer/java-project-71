package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;
import java.util.List;
import java.util.Map;

public class Differ {
    //Map of Lists file Format End Of, which supported end could be parsed
    public static final Map<String, List<String>> SUPPORTED_FILE_FORMATS = Map.of(
            "json", List.of("json", "jsn"),
            "yaml", List.of("yml", "yaml"));

    public static String getFileFormat(Path fileName) {
        String fileFormatresult = "";
        for (String fileFormat : SUPPORTED_FILE_FORMATS.keySet()) {
            for (String fileFormatEndOf : SUPPORTED_FILE_FORMATS.get(fileFormat)) {
                if (isFileFormatMatch(fileName.getFileName(), fileFormatEndOf)) {
                    fileFormatresult = fileFormat;
                    break;
                }
            }
        }

        return fileFormatresult.isEmpty() ? fileName.toString()
                .substring(fileName.toString().lastIndexOf(".")) : fileFormatresult;

    }

    public static String generate(String file1, String file2) throws Exception {
        return generate(file1, file2, "stylish");
    }

    public static String generate(String file1, String file2, String format) throws Exception {

        Path filePath1 = getAbsoluteFilePath(file1);
        Path filePath2 = getAbsoluteFilePath(file2);


        String fileFormat1 = Differ.getFileFormat(filePath1);
        String fileFormat2 = Differ.getFileFormat(filePath2);

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

    public static Path getAbsoluteFilePath(String filePathString) throws Exception {
        // get AbsolutePath
        Path filePath = Paths.get(filePathString).toAbsolutePath().normalize();

        // Is Exist
        if (!Files.exists(filePath)) {
            throw new Exception("File '" + filePathString + "' does not exist");
        }

        return filePath;
    }


    public static boolean isFileFormatMatch(Path filePath, String fileFormat) {
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:*." + fileFormat);
        if (matcher.matches(filePath)) {
            return true;
        }
        return false;
    }

    public static String readContentFile(Path filePath) throws Exception {
        return Files.readString(filePath);
    }


}

