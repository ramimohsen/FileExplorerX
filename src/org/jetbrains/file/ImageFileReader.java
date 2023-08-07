package org.jetbrains.file;

import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import org.jetbrains.enums.FileType;

/**
 *
 * @author ramy-mohsen
 */
public class ImageFileReader extends AbstractFileReader<ImageIcon> {

    public ImageFileReader(File selectedFIle) {
        super(selectedFIle);
    }

    @Override
    public ImageIcon readFile() throws IOException {
        return new ImageIcon(this.getSelectedFIle().getPath());
    }

    public static boolean isImageFile(String fileName) {
        for (FileType fileType : FileType.values()) {
            if (!fileType.equals(FileType.TXT) && fileName.endsWith(fileType.getExtension())) {
                return true;
            }
        }
        return false;
    }

}
