package hexlet.code;

import java.util.Map;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Differ {
    public static Map<String, Map<String, Object>> generate(Map<String,
            Object> firstMap, Map<String, Object> secondMap) {
        return compare(firstMap, secondMap);
    }

    public static Map<String, Map<String, Object>> compare(Map<String, Object> firstMap,
                                                           Map<String, Object> secondMap) {
        Set<String> keys = Stream.concat(firstMap.keySet().stream(), secondMap.keySet().stream())
                .sorted()
                .collect(Collectors.toCollection(LinkedHashSet::new));

        // Map Compare:  Key - is a Key, Value is a mapDiffer - contains type of modification and old & new values
        Map<String, Map<String, Object>> resultMapCompare = new LinkedHashMap<>();


        for (String key : keys) {
            Map<String, Object> mapDiffer = new HashMap<>();
            if (!firstMap.containsKey(key)) {
                //added
                mapDiffer.put("type", "added");
                mapDiffer.put("newValue", secondMap.get(key));
            } else if (!secondMap.containsKey(key)) {
                //deleted
                mapDiffer.put("type", "deleted");
                mapDiffer.put("oldValue", firstMap.get(key));
            } else if (firstMap.get(key) != null
                    && secondMap.get(key) != null
                    && firstMap.get(key).equals(secondMap.get(key))
                    || firstMap.get(key) == null
                    && secondMap.get(key) == null) {
                //equals
                mapDiffer.put("type", "equals");
                mapDiffer.put("oldValue", firstMap.get(key));
                mapDiffer.put("newValue", secondMap.get(key));
            } else {
                //changed
                mapDiffer.put("type", "changed");
                mapDiffer.put("oldValue", firstMap.get(key));
                mapDiffer.put("newValue", secondMap.get(key));
            }
            resultMapCompare.put(key, mapDiffer);
        }
        return resultMapCompare;

    }


}

