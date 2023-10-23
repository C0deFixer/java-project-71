package hexlet.code;

import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utils {
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
