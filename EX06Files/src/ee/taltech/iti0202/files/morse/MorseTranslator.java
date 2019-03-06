package ee.taltech.iti0202.files.morse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MorseTranslator {

    private Map<String, String> map = new HashMap<>();

    private Map<String, String> reverseMap = new HashMap<>();

    public Map<String, String> addMorseCodes(List<String> lines) {
        if (lines == null) {
            return map;
        }

        for (String line : lines) {
            String[] parts = line.split(" ");
            map.put(parts[0].toLowerCase(), parts[1].toLowerCase());
            reverseMap.put(parts[1].toLowerCase(), parts[0].toLowerCase());
        }
        return map;
    }

    public List<String> translateLinesToMorse(List<String> lines) {
        List<String> toMorse = new ArrayList<>();
        for (String line : lines) {
            if (!line.isEmpty()) {
                toMorse.add(translateLineToMorse(line));
            }
        }
        return toMorse;
    }

    public List<String> translateLinesFromMorse(List<String> lines) {
        List<String> fromMorse = new ArrayList<>();
        for (String line : lines) {
            if (!line.isEmpty()) {
                fromMorse.add(translateLineFromMorse(line));
            }
        }
        return fromMorse;
    }

    private String translateLineToMorse(String line) {
        StringBuilder answer = new StringBuilder();
        int i = 0;
        String[] sentence = line.split(" ");
        for (String lines : sentence) {
            for (String s : lines.split("")) {
                answer.append(map.get(s.toLowerCase()));
                if (!lines.endsWith(s)) {
                    answer.append(" ");
                }
            }
            if (i < sentence.length) {
                answer.append("\t");
            }
            i++;
        }
        return answer.toString();
    }

    private String translateLineFromMorse(String line) {
        StringBuilder answer = new StringBuilder();
        line = line.replace("\t", "~");
        //if (line.endsWith("~")) {
        //    line = line.substring(0, line.length() - 1);
        //}
        int i = 0;
        String[] sentence = line.split("~");
        for (String lines : sentence) {
            for (String s : lines.split(" ")) {
                if (!s.isEmpty()) {
                    answer.append(reverseMap.get(s));
                }
            }
            if (i < sentence.length - 1 && !answer.toString().endsWith(" ")) {
                answer.append(" ");
            }
            i++;
        }

        return answer.toString();
    }
}
