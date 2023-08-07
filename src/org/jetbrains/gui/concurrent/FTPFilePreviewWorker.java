package org.jetbrains.gui.concurrent;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import org.apache.commons.net.ftp.FTPClient;
import org.jetbrains.file.ImageFileReader;
import org.jetbrains.file.RemoteFTPFileReader;
import org.jetbrains.file.TextFileReader;

/**
 *
 * @author ramy-mohsen
 */
public class FTPFilePreviewWorker extends SwingWorker<Object, Void> {

    private final FTPClient fTPClient;
    private final String filePath;
    private final RemoteFTPFileReader remoteFTPFileReader;

    public FTPFilePreviewWorker(FTPClient fTPClient, String filePath) {
        this.fTPClient = fTPClient;
        this.filePath = filePath;
        remoteFTPFileReader = new RemoteFTPFileReader(this.fTPClient, this.filePath);
    }

    @Override
    protected Object doInBackground() throws Exception {

        if (TextFileReader.isTextFile(filePath)) {
            return remoteFTPFileReader.readRemoteTextFileContent();
        } else if (ImageFileReader.isImageFile(filePath)) {
            return remoteFTPFileReader.readRemoteImageFileContent();
        } else {
            JOptionPane.showMessageDialog(null, "You can only preview text or image files", "Not Supported", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }

}
