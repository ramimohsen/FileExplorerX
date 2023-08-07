package org.jetbrains.gui.concurrent;

import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import org.jetbrains.file.AbstractFileReader;
import org.jetbrains.file.ImageFileReader;
import org.jetbrains.file.TextFileReader;

/**
 *
 * @author ramy-mohsen
 */
public class FilePreviewWorker extends SwingWorker<Object, Void> {

    private final String selectedEntry;

    public FilePreviewWorker(String selectedEntry) {
        this.selectedEntry = selectedEntry;
    }

    @Override
    protected Object doInBackground() throws Exception {
        AbstractFileReader<?> fileReader;

        if (TextFileReader.isTextFile(selectedEntry)) {
            fileReader = new TextFileReader(new File(selectedEntry));
            return fileReader.readFile();
        } else if (ImageFileReader.isImageFile(selectedEntry)) {
            fileReader = new ImageFileReader(new File(selectedEntry));
            return fileReader.readFile();
        } else {
            JOptionPane.showMessageDialog(null, "You can only preview text or image files", "Not Supported", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }

}
