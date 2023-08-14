package org.jetbrains.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.jetbrains.enums.FileType;

/**
 *
 * @author ramy-mohsen
 */
public class TextFileReader extends AbstractFileReader<String> {

    public TextFileReader(File selectedFIle) {
        super(selectedFIle);
    }

    @Override
    public String readFile() throws IOException {

        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.getSelectedFIle()))) {
            char[] buffer = new char[4096];

            int bytesRead;
            while ((bytesRead = reader.read(buffer)) != -1) {
                content.append(buffer, 0, bytesRead);
            }
        }
        return content.toString();
    }

    public static boolean isTextFile(String fileName) {
        return fileName.endsWith(FileType.TXT.getExtension());
    }
}
