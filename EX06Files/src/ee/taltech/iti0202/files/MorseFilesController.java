package ee.taltech.iti0202.files;
import ee.taltech.iti0202.files.input.InputFilesBufferReader;
import ee.taltech.iti0202.files.input.InputFilesScanner;
import ee.taltech.iti0202.files.morse.MorseTranslator;
import ee.taltech.iti0202.files.output.OutputFilesWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MorseFilesController {

    public static void main(String[] args) {
        InputFilesScanner scanner = new InputFilesScanner();
        List<String> lines = scanner.readTextFromFile("morse.txt");
        InputFilesBufferReader bufferReader = new InputFilesBufferReader();
        List<String> lines2 = bufferReader.readTextFromFile("morse.txt");
        MorseTranslator translator = new MorseTranslator();
        Map<String, String> codes = translator.addMorseCodes(lines2);

        List<String> sentence = new ArrayList<>();
        sentence.add(".-.. --- .-. . --\t.. .--. ... ..- --\t-.. --- .-.. --- .-.\t... .. -\t.- -- . - --..--\t-.-. --- -. ... . -.-. - . - ..- .-.\t.- -.. .. .--. .. ... -.-. .. -. --.\t. .-.. .. - ..--..");
        sentence.add("-..-.\t-..-.");
        sentence.add("");
        sentence.add("-..-.\t");
        List<String> normalLines = translator.translateLinesFromMorse(sentence);
        normalLines.forEach(System.out::println); //your input lines in regular text

        OutputFilesWriter writer = new OutputFilesWriter();
        System.out.println(writer.writeLinesToFile(normalLines, "output.txt")); //true
        //This should also create a new file/ write in an existing file
    }
}
