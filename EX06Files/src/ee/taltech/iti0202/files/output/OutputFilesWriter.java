package ee.taltech.iti0202.files.output;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class OutputFilesWriter {

    public boolean writeLinesToFile(List<String> lines, String filename) {

        try {

            FileWriter fileWriter = new FileWriter(filename);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (String line : lines) {
                printWriter.print(line);
                if (lines.get(lines.size() - 1).equals(line)) {
                    printWriter.print("\n");
                }
            }
            printWriter.close();

        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
