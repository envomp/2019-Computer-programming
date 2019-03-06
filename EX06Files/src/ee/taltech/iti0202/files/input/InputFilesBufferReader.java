package ee.taltech.iti0202.files.input;

import ee.taltech.iti0202.files.exception.FileReaderException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class InputFilesBufferReader implements InputFilesReader {

    @Override
    public List<String> readTextFromFile(String filename) throws FileReaderException {
        try {
            FileReader in = new FileReader(filename);
            BufferedReader br = new BufferedReader(in);
            ArrayList<String> fileContent = new ArrayList<>();
            while (br.readLine() != null) {
                fileContent.add(br.readLine());
            }
            return fileContent;

        } catch (Exception e) {
            throw new FileReaderException("No file was found!", e);
        }
    }
}
