package ee.taltech.iti0202.files.input;

import ee.taltech.iti0202.files.exception.FileReaderException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputFilesScanner implements InputFilesReader {

    @Override
    public List<String> readTextFromFile(String filename) throws FileReaderException {

        try {
            ArrayList<String> fileContent = new ArrayList<>();
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                System.out.println(scanner.next());
            }
            scanner.close();
            return fileContent;

        } catch (Exception e) {
            throw new FileReaderException("No file was found!", e);
        }
    }
}
