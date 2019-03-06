package ee.taltech.iti0202.files.morse;

import ee.taltech.iti0202.files.input.InputFilesBufferReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MorseTranslator {

    private Map<String, String> fromMorse;
    private Map<String, String> toMorse;

    public Map<String, String> addMorseCodes(List<String> lines) {
        Map<String, String> map = new HashMap<>();
        for (String line : lines) {
            String[] parts = line.split(" ");
            map.put(parts[0].toLowerCase(), parts[1].toLowerCase());
        }
        return map;
    }

    public List<String> translateLinesToMorse(List<String> lines) {
        toMorse = addMorseCodes(new InputFilesBufferReader().readTextFromFile("EX06Files\\src\\morse.txt"));
        List<String> toMorse = new ArrayList<>();
        for (String line : lines) {
            toMorse.add(translateLineToMorse(line));
        }
        return toMorse;
    }

    public List<String> translateLinesFromMorse(List<String> lines) {
        fromMorse = addMorseCodes(new InputFilesBufferReader().readTextFromFile("EX06Files\\src\\reverseMorse.txt"));
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
                answer.append(toMorse.get(s));
            }
            answer.append(" ");
        }
        answer.append("\n");
        return answer.toString();
    }

    private String translateLineFromMorse(String line) {
        StringBuilder answer = new StringBuilder();
        for (String lines : line.split("\\t")) {
            for (String s : lines.split(" ")) {
                answer.append(fromMorse.get(s));
            }
            answer.append(" ");
        }
        answer.append("\n");
        return answer.toString();
    }
}
