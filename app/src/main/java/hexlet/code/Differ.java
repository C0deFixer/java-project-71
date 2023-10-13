package hexlet.code;

import java.util.*;

public class Differ {
    public static String generate(Map<String, String> firstMap, Map<String, String> secondMap) {
        return convertToString(compare(firstMap, secondMap));
    }

    public static Map<String, String> compare(Map<String, String> firstMap, Map<String, String> secondMap){
        SortedSet<String> keys = new TreeSet<>();
        keys.addAll(firstMap.keySet());
        keys.addAll(secondMap.keySet());
        LinkedHashMap<String, String> result = new LinkedHashMap<>();

        for (String key : keys) {
            if (!firstMap.containsKey(key)) {
                //added
                result.put("+ "+ key.toString(), secondMap.get(key));
            } else if (!secondMap.containsKey(key)) {
                //deleted
                result.put("- " + key.toString(), firstMap.get(key));
            } else if (firstMap.get(key) == null
                    && secondMap.get(key) == null
                    || firstMap.get(key).equals(secondMap.get(key))) {
                //equals
                result.put("  "+key.toString(), firstMap.get(key));
            } else {
                //changed
                result.put("+ " + key.toString(), firstMap.get(key));
                result.put("- " + key.toString(), secondMap.get(key));
            }
        }
        return result;

    }

    public static String convertToString(Map<String, String> map) {
        final StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<String, String> ent: map.entrySet()) {
            sb.append("\n ").append(ent.getKey()).append(": ").append(ent.getValue());
        }
        sb.append("\n}");
        return sb.toString();
    }

}

