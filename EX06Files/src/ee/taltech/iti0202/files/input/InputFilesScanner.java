package ee.taltech.iti0202.files.input;

import ee.taltech.iti0202.files.exception.FileReaderException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputFilesScanner implements InputFilesReader {

    @Override
    public List<String> readTextFromFile(String filename) throws FileReaderException {

        ArrayList<String> fileContent = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                fileContent.add(scanner.nextLine());
            }
        } catch (IOException e) {
            System.out.println("Error reading file:" + e.getMessage());
            e.printStackTrace();
            try {
                throw new FileReaderException(e);
            } catch (FileReaderException ex) {
                return new ArrayList<>();
            }
        }
        return fileContent;
    }
}
