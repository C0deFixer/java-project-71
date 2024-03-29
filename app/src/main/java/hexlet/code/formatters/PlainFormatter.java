package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class PlainFormatter {
    public static String convertToString(Map<String, Map<String, Object>> map) throws Exception {
        return map.entrySet().stream()
                .filter(x -> !x.getValue().get("type").equals("equals"))
                .map(x -> {
                            String key = x.getKey();
                            Map<String, Object> value = x.getValue();
                            try {
                                return format(key, value);
                            } catch (Exception ex) {

                                throw new RuntimeException(ex);
                            }
                        }
                )
                .collect(Collectors.joining("\n"));
    }

    private static String format(String key, Map<String, Object> mapDiffer) throws Exception {
        // Map Compare:  key - is a Name of Value, mapDiffer - contains type of modification and old & new values
        String type = mapDiffer.get("type").toString();
        switch (type) {
            case "deleted":
                //Property 'key1' was removed
                return String.format("Property '%s' was removed", key);
            case "added":
                //Property 'key2' was added with value: 'value2'
                return String.format("Property '%s' was added with value: %s",
                        key,
                        formatObjectToString(mapDiffer.get("newValue")));
            case "changed":
                //Property 'chars2' was updated. From [complex value] to false
                return String.format("Property '%s' was updated. From %s to %s",
                        key,
                        formatObjectToString(mapDiffer.get("oldValue")),
                        formatObjectToString(mapDiffer.get("newValue")));
            case "equals":
                return "";
            default:
                throw new Exception("unknown tipe of diff node!");
        }
    }

    public static String formatObjectToString(Object value) {
        if (value instanceof String) {
            return String.format("'%s'", value);
        } else if (value instanceof List<?> || value instanceof Map<?, ?>) {
            return "[complex value]";
        } else {
            return value == null ? "null" : value.toString();
        }
    }

}
