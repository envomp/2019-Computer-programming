package ee.taltech.iti0202.files;

import ee.taltech.iti0202.files.input.InputFilesBufferReader;
import ee.taltech.iti0202.files.input.InputFilesScanner;
import ee.taltech.iti0202.files.morse.MorseTranslator;
import ee.taltech.iti0202.files.output.OutputFilesWriter;

import java.util.List;
import java.util.Map;

public class MorseFilesController {

    public static void main(String[] args) {
        List<String> lines = new InputFilesScanner().readTextFromFile("EX06Files\\src\\morse.txt");
        List<String> lines2 = new InputFilesBufferReader().readTextFromFile("EX06Files\\src\\morse.txt");

        MorseTranslator translator = new MorseTranslator();
        Map<String, String> codes = translator.addMorseCodes(lines2);

        List<String> morseLines = translator.translateLinesToMorse(lines2);

        List<String> normalLines = translator.translateLinesFromMorse(morseLines);

        OutputFilesWriter writer = new OutputFilesWriter();
        System.out.println(writer.writeLinesToFile(normalLines, "output.txt")); //true
        //This should also create a new file/ write in an existing file
    }
}
