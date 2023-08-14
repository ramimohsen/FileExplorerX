package org.jetbrains.file.factory;

import java.io.File;
import org.jetbrains.file.AbstractFileReader;
import org.jetbrains.file.ImageFileReader;
import org.jetbrains.file.TextFileReader;

/**
 *
 * @author ramy-mohsen
 */
public class DefaultFileReaderFactory implements FileReaderFactory {

    @Override
    public <T> AbstractFileReader<T> createFileReader(String filePath) {

        if (TextFileReader.isTextFile(filePath)) {
            return (AbstractFileReader<T>) new TextFileReader(new File(filePath));
        } else if (ImageFileReader.isImageFile(filePath)) {
            return (AbstractFileReader<T>) new ImageFileReader(new File(filePath));
        } else {
            throw new UnsupportedOperationException("File format not supported yet.");
        }

    }

}
