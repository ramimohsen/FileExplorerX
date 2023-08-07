package org.jetbrains.file;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.swing.ImageIcon;
import org.apache.commons.net.ftp.FTPClient;

/**
 *
 * @author ramy-mohsen
 */
public class RemoteFTPFileReader {

    private final FTPClient fTPClient;
    private final String filePath;

    public RemoteFTPFileReader(FTPClient fTPClient, String filePath) {
        this.fTPClient = fTPClient;
        this.filePath = filePath;
    }

    public String readRemoteTextFileContent() throws IOException {

        StringBuilder content = new StringBuilder();

        try (InputStream inputStream = this.fTPClient.retrieveFileStream(this.filePath); InputStreamReader reader = new InputStreamReader(inputStream); BufferedReader bufferedReader = new BufferedReader(reader)) {
            char[] buffer = new char[4096];
            int bytesRead;
            while ((bytesRead = bufferedReader.read(buffer)) != -1) {
                content.append(buffer, 0, bytesRead);
            }
            this.fTPClient.completePendingCommand();
        }
        return content.toString();
    }

    public ImageIcon readRemoteImageFileContent() throws IOException {

        try (InputStream inputStream = this.fTPClient.retrieveFileStream(this.filePath); ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }

            byte[] imageData = byteArrayOutputStream.toByteArray();

            this.fTPClient.completePendingCommand();

            return new ImageIcon(imageData);

        } catch (IOException e) {
            throw new IOException("Error while reading Image file ", e);
        }
    }

}
