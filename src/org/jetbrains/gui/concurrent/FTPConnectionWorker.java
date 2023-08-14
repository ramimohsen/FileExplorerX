package org.jetbrains.gui.concurrent;

import javax.swing.SwingWorker;
import org.apache.commons.net.ftp.FTPClient;
import org.jetbrains.file.remote.FTPConnection;

/**
 *
 * @author ramy-mohsen
 */
public class FTPConnectionWorker extends SwingWorker<FTPClient, Void> {

    private final FTPConnection fTPConnection = FTPConnection.getInstance();

    private final String server;
    private final int port;
    private final String username;
    private final String password;

    public FTPConnectionWorker(String server, int port, String username, String password) {
        this.server = server;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    @Override
    protected FTPClient doInBackground() throws Exception {
        this.fTPConnection.connect(this.server, this.port, this.username, this.password);
        return this.fTPConnection.getFtpClient();
    }

}
