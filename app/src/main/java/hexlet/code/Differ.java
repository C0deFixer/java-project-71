package hexlet.code;

import java.nio.file.Path;
import java.util.Map;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Differ {

    public static String generate(String file1, String file2, String format) throws Exception {

        Path filePath1 = Utils.getAbsoluteFilePath(file1);
        Path filePath2 = Utils.getAbsoluteFilePath(file2);


        String fileFormat1 = Parser.getFileFormat(filePath1);
        String fileFormat2 = Parser.getFileFormat(filePath2);

        if (!fileFormat1.equals(fileFormat2)) {
            throw new Exception("Files have different formats !");
        }

        String contentFile1 = Utils.readContentFile(filePath1);
        String contentFile2 = Utils.readContentFile(filePath2);

        //Factory inside depends on file format  Throwable inside Check if file format unknown = unsupported
        Map<String, Object> fileContentMap1 = Parser.parseContentFileToMap(contentFile1, fileFormat1);
        Map<String, Object> fileContentMap2 = Parser.parseContentFileToMap(contentFile2, fileFormat2);

        Map<String, Map<String, Object>> resultMapCompare = compare(fileContentMap1, fileContentMap2);
        return Formatter.convertToString(resultMapCompare, format);
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

