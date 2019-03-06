package ee.taltech.iti0202.files.morse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

public class MorseTranslator {

    private Map<String, String> fromMorse = Map.ofEntries(
            entry(".-", "a"),
            entry("-...", "b"),
            entry("-.-.", "c"),
            entry("-..", "d"),
            entry(".", "e"),
            entry("..-.", "f"),
            entry("--.", "g"),
            entry("....", "h"),
            entry("..", "i"),
            entry(".---", "j"),
            entry("-.-", "k"),
            entry(".-..", "l"),
            entry("--", "m"),
            entry("-.", "n"),
            entry("---", "o"),
            entry(".--.", "p"),
            entry("--.-", "q"),
            entry(".-.", "r"),
            entry("...", "s"),
            entry("-", "t"),
            entry("..-", "u"),
            entry("...-", "v"),
            entry(".--", "w"),
            entry(".-.-", "ä"),
            entry("---.", "ö"),
            entry("..--", "ü"),
            entry("-..-", "x"),
            entry("-.--", "y"),
            entry("--..", "z"),
            entry("-----", "0"),
            entry(".----", "1"),
            entry("..---", "2"),
            entry("...--", "3"),
            entry("....-", "4"),
            entry(".....", "5"),
            entry("-....", "6"),
            entry("--...", "7"),
            entry("---..", "8"),
            entry("----.", "9"),
            entry(".-...", "&"),
            entry(".----.", "'"),
            entry(".--.-.", "@"),
            entry("-.--.-", ")"),
            entry("-.--.", "("),
            entry("---...", ":"),
            entry("--..--", ","),
            entry("-...-", "="),
            entry("-.-.--", "!"),
            entry(".-.-.-", "."),
            entry("-....-", "-"),
            entry(".-.-.", "+"),
            entry(".-..-.", "\""),
            entry("..--..", "?"),
            entry("-..-.", "/")
    );

    private Map<String, String> toMorse = Map.ofEntries(
            entry("a", ".-"),
            entry("b", "-..."),
            entry("c", "-.-."),
            entry("d", "-.."),
            entry("e", "."),
            entry("f", "..-."),
            entry("g", "--."),
            entry("h", "...."),
            entry("i", ".."),
            entry("j", ".---"),
            entry("k", "-.-"),
            entry("l", ".-.."),
            entry("m", "--"),
            entry("n", "-."),
            entry("o", "---"),
            entry("p", ".--."),
            entry("q", "--.-"),
            entry("r", ".-."),
            entry("s", "..."),
            entry("t", "-"),
            entry("u", "..-"),
            entry("v", "...-"),
            entry("w", ".--"),
            entry("ä", ".-.-"),
            entry("ö", "---."),
            entry("ü", "..--"),
            entry("x", "-..-"),
            entry("y", "-.--"),
            entry("z", "--.."),
            entry("0", "-----"),
            entry("1", ".----"),
            entry("2", "..---"),
            entry("3", "...--"),
            entry("4", "....-"),
            entry("5", "....."),
            entry("6", "-...."),
            entry("7", "--..."),
            entry("8", "---.."),
            entry("9", "----."),
            entry("&", ".-..."),
            entry("'", ".----."),
            entry("@", ".--.-."),
            entry(")", "-.--.-"),
            entry("(", "-.--."),
            entry(":", "---..."),
            entry(",", "--..--"),
            entry("=", "-...-"),
            entry("!", "-.-.--"),
            entry(".", ".-.-.-"),
            entry("-", "-....-"),
            entry("+", ".-.-."),
            entry("\"", ".-..-."),
            entry("?", "..--.."),
            entry("/", "-..-.")
    );

    public Map<String, String> addMorseCodes(List<String> lines) {
        if (lines == null) {
            return new HashMap<>();
        }

        Map<String, String> map = new HashMap<>();
        for (String line : lines) {
            String[] parts = line.split(" ");
            map.put(parts[0].toLowerCase(), parts[1].toLowerCase());
        }
        return map;
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
