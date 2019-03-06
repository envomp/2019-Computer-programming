package ee.taltech.iti0202.files.input;

import ee.taltech.iti0202.files.exception.FileReaderException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class InputFilesScanner implements InputFilesReader {

    @Override
    public List<String> readTextFromFile(String filename) throws FileReaderException {

        try {
            BufferedReader scanner = new BufferedReader(new FileReader(filename));
            ArrayList<String> fileContent = new ArrayList<>();
            String cur_line;
            while ((cur_line = scanner.readLine()) != null) {
                fileContent.add(cur_line);
            }
            return fileContent;

        } catch (Exception e) {
            throw new FileReaderException("No file was found!", e);
        }
    }
}
