package ee.taltech.iti0202.files.morse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MorseTranslator {

    private Map<String, String> toMorse;
    private Map<String, String> fromMorse;

    public Map<String, String> addMorseCodes(List<String> lines) {
        if (lines == null) {
            return toMorse;
        }

        for (String line : lines) {
            String[] parts = line.split(" ");
            toMorse.put(parts[0].toLowerCase(), parts[1].toLowerCase());
            fromMorse.put(parts[1].toLowerCase(), parts[0].toLowerCase());
        }
        return toMorse;
    }

    public List<String> translateLinesToMorse(List<String> lines) {
        List<String> toMorse = new ArrayList<>();
        for (String line : lines) {
            toMorse.add(translateLineToMorse(line));
        }
        return toMorse;
    }

    public List<String> translateLinesFromMorse(List<String> lines) {
        List<String> fromMorse = new ArrayList<>();
        for (String line : lines) {
            fromMorse.add(translateLineFromMorse(line));
        }
        return fromMorse;
    }

    private String translateLineToMorse(String line) {
        StringBuilder answer = new StringBuilder();
        for (String lines : line.split(" ")) {
            for (String s : lines.split("")) {
                answer.append(toMorse.get(s.toLowerCase()));
                answer.append(" ");
            }
            answer.append("\t");
        }
        return answer.toString();
    }

    private String translateLineFromMorse(String line) {
        StringBuilder answer = new StringBuilder();
        line = line.replace("\\t", "~");
        for (String lines : line.split("~")) {
            for (String s : lines.split(" ")) {
                answer.append(fromMorse.get(s));
            }
            answer.append(" ");
        }

        return answer.toString();
    }
}
