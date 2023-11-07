package hexlet.code;

import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.Map;


public class Formatter {
    public static String convertToString(Map<String, Map<String, Object>> map, String formatName) throws Exception {
        switch (formatName) {
            case "stylish":
                return StylishFormatter.convertToString(map);
            case "plain":
                return PlainFormatter.convertToString(map);
            default:
                throw new Exception("Unknown output format");

        }

    }
}
