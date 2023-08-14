package org.jetbrains.file.remote;

import java.io.IOException;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 *
 * @author ramy-mohsen
 */
public class FTPConnection {

    private static volatile FTPConnection instance;
    private final FTPClient ftpClient;

    private FTPConnection() {
        ftpClient = new FTPClient();
    }

    public static FTPConnection getInstance() {
        if (instance == null) {
            synchronized (FTPConnection.class) {
                if (instance == null) {
                    instance = new FTPConnection();
                }
            }
        }
        return instance;
    }

    public void connect(String server, int port, String username, String password) throws IOException {
        ftpClient.connect(server, port);
        int replyCode = ftpClient.getReplyCode();
        if (!FTPReply.isPositiveCompletion(replyCode)) {
            throw new IOException("Failed to connect to the FTP server.");
        }

        if (!ftpClient.login(username, password)) {
            throw new IOException("Failed to log in to the FTP server.");
        }

        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
    }

    public void disconnect() throws IOException {
        if (ftpClient.isConnected()) {
            ftpClient.logout();
            ftpClient.disconnect();
        }
    }

    public FTPFile[] listFiles() throws IOException {
        return ftpClient.listFiles();
    }

    public FTPClient getFtpClient() {
        return ftpClient;
    }
    
    
}
