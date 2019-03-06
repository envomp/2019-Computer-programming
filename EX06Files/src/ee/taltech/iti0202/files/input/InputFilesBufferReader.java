package ee.taltech.iti0202.files.input;

import ee.taltech.iti0202.files.exception.FileReaderException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputFilesBufferReader implements InputFilesReader {

    @Override
    public List<String> readTextFromFile(String filename) throws FileReaderException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            ArrayList<String> fileContent = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                fileContent.add(line);
            }
            return fileContent;

        } catch (IOException e) {
            System.out.println("Error reading file:" + e.getMessage());
            e.printStackTrace();
            throw new FileReaderException("", e);
        }
    }
}
