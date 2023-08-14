package org.jetbrains.file.factory;

import org.jetbrains.file.AbstractFileReader;

/**
 *
 * @author ramy-mohsen
 */
public interface FileReaderFactory {

    <T> AbstractFileReader<T> createFileReader(String filePath);

}
