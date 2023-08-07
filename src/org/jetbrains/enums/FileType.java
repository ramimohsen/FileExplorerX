package org.jetbrains.enums;

/**
 *
 * @author ramy-mohsen
 */
public enum FileType {
    TXT(".txt"),
    JPG(".jpg"),
    JPEG(".jpeg"),
    PNG(".png"),
    GIF(".gif"),
    ZIP(".zip");

    private final String extension;

    FileType(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

}
