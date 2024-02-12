package hexlet.code.formatters;

import java.util.Map;

public class StylishFormatter {
    public static String convertToString(Map<String, Map<String, Object>> map) throws Exception {
        final StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<String, Map<String, Object>> entKey : map.entrySet()) {
            Map<String, Object> mapDiffer = entKey.getValue();
            String type = mapDiffer.get("type").toString();
            switch (type) {
                case "added":
                    getAppendStylish(sb, " +", entKey.getKey(), mapDiffer, "newValue");
                    break;
                case "deleted":
                    getAppendStylish(sb, " -", entKey.getKey(), mapDiffer, "oldValue");
                    break;
                case "changed":
                    getAppendStylish(sb, " -", entKey.getKey(), mapDiffer, "oldValue");
                    getAppendStylish(sb, " +", entKey.getKey(), mapDiffer, "newValue");
                    break;
                case "equals":
                    getAppendStylish(sb, "  ", entKey.getKey(), mapDiffer, "oldValue");
                    break;
                default:
                    throw new Exception("unknown tipe of diff node!");

            }
        }
        sb.append("\n}");
        return sb.toString();
    }

    public static void getAppendStylish(StringBuilder sb,
                                        String operationString,
                                        String entKey,
                                        Map<String, Object> mapDiffer,
                                        String keyValue) {
        Object value = mapDiffer.get(keyValue);
        sb.append("\n ")
                .append(operationString)
                .append(" ")
                .append(entKey)
                .append(": ")
                .append(value == null ? "null" : value.toString());

    }
}
