package ee.taltech.iti0202.files.output;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class OutputFilesWriter {

    public boolean writeLinesToFile(List<String> lines, String filename) {

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename))) {

            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("IOException:" + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
