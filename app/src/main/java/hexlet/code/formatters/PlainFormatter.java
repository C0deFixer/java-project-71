package hexlet.code.formatters;

import java.util.List;
import java.util.Map;


public class PlainFormatter {
    public static String convertToString(Map<String, Map<String, Object>> map) {
        final StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<String, Map<String, Object>> entKey : map.entrySet()) {
            Map<String, Object> mapDiffer = entKey.getValue();
            String type = mapDiffer.get("type").toString();
            switch (type) {
                case "added":
                    getAppendPlain(sb, " was added with value: ", entKey.getKey(), mapDiffer, "newValue");
                    break;
                case "deleted":
                    getAppendPlain(sb, " was removed", entKey.getKey(), mapDiffer, "");
                    break;
                case "changed":
                    getAppendPlain(sb, " was updated. From ", entKey.getKey(), mapDiffer, "oldValue");
                    sb.append(" to ");
                    getAppendObjectToString(sb, mapDiffer.get("newValue"));
                    break;
                default: //equals

            }
        }
        sb.append("\n}");
        return sb.toString();
    }

    public static void getAppendPlain(StringBuilder sb,
                                      String operationString,
                                      String entKey,
                                      Map<String, Object> mapDiffer,
                                      String keyValue) {
        sb.append("\n ")
                .append("Property '")
                .append(entKey)
                .append("'")
                .append(operationString);
        if (keyValue.isBlank()) {
            return;
        }
        Object value = mapDiffer.get(keyValue);
        getAppendObjectToString(sb, value);
    }

    public static void getAppendObjectToString(StringBuilder sb, Object value) {
        if (value instanceof String) {
            sb.append("'")
                    .append(value)
                    .append("'");
        } else if (value instanceof List<?> || value instanceof Map<?, ?>) {
            sb.append("[complex value]");
        } else {
            sb.append(value == null ? "null" : value.toString());
        }
    }

}
