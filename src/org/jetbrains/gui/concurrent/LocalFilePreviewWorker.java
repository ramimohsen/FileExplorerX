package org.jetbrains.gui.concurrent;

import javax.swing.SwingWorker;
import org.jetbrains.file.AbstractFileReader;
import org.jetbrains.file.factory.DefaultFileReaderFactory;

/**
 *
 * @author ramy-mohsen
 */
public class LocalFilePreviewWorker extends SwingWorker<AbstractFileReader<?>, Void> {

    private final String selectedEntry;

    public LocalFilePreviewWorker(String selectedEntry) {
        this.selectedEntry = selectedEntry;
    }

    @Override
    protected AbstractFileReader<?> doInBackground() throws Exception {
        return new DefaultFileReaderFactory().createFileReader(selectedEntry);
    }

}
