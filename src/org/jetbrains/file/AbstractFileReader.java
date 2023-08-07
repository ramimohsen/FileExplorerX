package org.jetbrains.file;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author ramy-mohsen
 * @param <T>
 */
public abstract class AbstractFileReader<T> {

    private final File selectedFIle;

    public AbstractFileReader(File selectedFIle) {
        this.selectedFIle = selectedFIle;
    }

    public File getSelectedFIle() {
        return selectedFIle;
    }

    public abstract T readFile() throws IOException;

}
