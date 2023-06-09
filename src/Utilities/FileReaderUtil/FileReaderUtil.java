package Utilities.FileReaderUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileReaderUtil {
    private static final Logger logger = Logger.getLogger(FileReaderUtil.class.getName());

    public static String read(Path path) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
                content.append(System.lineSeparator());
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error while reading the file: " + path, e);
            throw e;
        }
        return content.toString();
    }
}