package org.jetbrains.utility;

import java.awt.Component;
import java.io.File;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author ramy-mohsen
 */
public class FileChooserWrapper {

    private final String dialogTtile;

    private final List<FileNameExtensionFilter> fileFilter;

    public FileChooserWrapper(String dialogTtile, List<FileNameExtensionFilter> fileFilter) {
        this.dialogTtile = dialogTtile;
        this.fileFilter = fileFilter;
    }

    public File getSelectedFile(Component parent) {

        JFileChooser fileChooser = this.getFileChooserInstance();

        int value = fileChooser.showOpenDialog(parent);

        if (value == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }

        return null;
    }

    private JFileChooser getFileChooserInstance() {

        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setDialogTitle(this.dialogTtile);

        this.fileFilter.forEach(filter -> fileChooser.setFileFilter(filter));

        fileChooser.setSize(500, 500);

        fileChooser.setMultiSelectionEnabled(false);

        return fileChooser;
    }

}
