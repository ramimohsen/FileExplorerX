package org.jetbrains.gui;

import java.awt.Component;
import org.jetbrains.utility.FileChooserWrapper;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.apache.commons.net.ftp.FTPClient;
import org.jetbrains.enums.FileType;
import org.jetbrains.file.AbstractFileReader;
import org.jetbrains.file.remote.FTPConnection;
import org.jetbrains.file.ImageFileReader;
import org.jetbrains.file.TextFileReader;
import org.jetbrains.file.TreeFileStrucutre;
import org.jetbrains.file.ZIPFileReader;
import org.jetbrains.gui.concurrent.FTPConnectionWorker;
import org.jetbrains.gui.concurrent.FTPFilePreviewWorker;
import org.jetbrains.gui.concurrent.FTPTreeWillExpandListener;
import org.jetbrains.gui.concurrent.LocalFilePreviewWorker;
import org.jetbrains.gui.concurrent.FileTreeWillExpandListener;

/**
 *
 * @author ramy-mohsen
 */
public class MainPanel extends javax.swing.JFrame {
    
    public MainPanel() {
        initComponents();
        initializeFileSystemTree();
    }
    
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(() -> {
            MainPanel main = new MainPanel();
            main.setLocationRelativeTo(null);
            main.setVisible(true);
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        statusLabel = new javax.swing.JLabel();
        imagePreviewLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        zipFileTree = new javax.swing.JTree();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaFilePreview = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        localFIleTree = new javax.swing.JTree();
        serverText = new javax.swing.JTextField();
        passwordText = new javax.swing.JPasswordField();
        usernameText = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        connectButton = new javax.swing.JButton();
        disconnectButton = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        ftpTree = new javax.swing.JTree();
        portNumber = new javax.swing.JSpinner();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        archivedMenu = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        exitMenu = new javax.swing.JMenuItem();
        aboutMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FileExplorerX");
        setLocation(new java.awt.Point(0, 0));
        setResizable(false);

        statusLabel.setText("Ready !");

        imagePreviewLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        zipFileTree.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        zipFileTree.setModel(null);
        zipFileTree.setToolTipText("Archived Files Tree");
        zipFileTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                zipFileTreeValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(zipFileTree);

        textAreaFilePreview.setEditable(false);
        textAreaFilePreview.setColumns(20);
        textAreaFilePreview.setFont(new java.awt.Font("Liberation Sans", 0, 15)); // NOI18N
        textAreaFilePreview.setLineWrap(true);
        textAreaFilePreview.setRows(5);
        textAreaFilePreview.setText("Select a file to see a preview here .");
        textAreaFilePreview.setToolTipText("");
        textAreaFilePreview.setWrapStyleWord(true);
        textAreaFilePreview.setAlignmentX(Component.CENTER_ALIGNMENT);
        textAreaFilePreview.setAlignmentY(Component.CENTER_ALIGNMENT);
        textAreaFilePreview.setBorder(null);
        jScrollPane1.setViewportView(textAreaFilePreview);

        localFIleTree.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        localFIleTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                localFIleTreeValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(localFIleTree);

        jLabel1.setText("Server:");

        jLabel2.setText("Port:");

        jLabel3.setText("Username:");

        jLabel4.setText("Password:");

        connectButton.setText("Connet");
        connectButton.setToolTipText("Connect to server");
        connectButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                connectButtonMouseClicked(evt);
            }
        });

        disconnectButton.setText("Disconnect");
        disconnectButton.setToolTipText("Disconnect from server");
        disconnectButton.setEnabled(false);
        disconnectButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                disconnectButtonMouseClicked(evt);
            }
        });

        ftpTree.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        ftpTree.setModel(null);
        ftpTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                ftpTreeValueChanged(evt);
            }
        });
        jScrollPane4.setViewportView(ftpTree);

        jMenu3.setText("File");

        archivedMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/jetbrains/icon/1519786_archive_colorful_documents_folders_office_icon.png"))); // NOI18N
        archivedMenu.setText("Open Archived File");
        archivedMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                archivedMenuActionPerformed(evt);
            }
        });
        jMenu3.add(archivedMenu);
        jMenu3.add(jSeparator2);

        exitMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_DOWN_MASK));
        exitMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/jetbrains/icon/1398919_close_cross_incorrect_invalid_x_icon.png"))); // NOI18N
        exitMenu.setText("Exit");
        exitMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuActionPerformed(evt);
            }
        });
        jMenu3.add(exitMenu);

        jMenuBar1.add(jMenu3);

        aboutMenu.setText("About");
        aboutMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aboutMenuMouseClicked(evt);
            }
        });
        jMenuBar1.add(aboutMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(serverText, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(portNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(usernameText, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(passwordText, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(connectButton, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(disconnectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
                            .addComponent(imagePreviewLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(imagePreviewLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(serverText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(usernameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(passwordText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(connectButton)
                            .addComponent(disconnectButton)
                            .addComponent(portNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusLabel)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void aboutMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aboutMenuMouseClicked
        AboutDialog aboutDialog = new AboutDialog(this, false);
        aboutDialog.setLocationRelativeTo(this);
        aboutDialog.setVisible(true);
    }//GEN-LAST:event_aboutMenuMouseClicked

    private void zipFileTreeValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_zipFileTreeValueChanged
        
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) zipFileTree.getLastSelectedPathComponent();
        
        if (selectedNode != null && selectedNode.isLeaf()) {
            
            SwingWorker<Object, Void> worker = new SwingWorker<Object, Void>() {
                
                private final ZipEntry selectedEntry = (ZipEntry) selectedNode.getUserObject();
                
                @Override
                protected Object doInBackground() throws Exception {
                    DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) zipFileTree.getModel().getRoot();
                    
                    if (TextFileReader.isTextFile(selectedEntry.getName())) {
                        return ZIPFileReader.readZiPTextFileContent(rootNode.getUserObject().toString(), selectedEntry);
                    } else if (ImageFileReader.isImageFile(selectedEntry.getName())) {
                        return ZIPFileReader.readZiPImageFileContent(rootNode.getUserObject().toString(), selectedEntry);
                    } else {
                        JOptionPane.showMessageDialog(MainPanel.this, "Text and Image Files Only Can Be Previewed", "Not Supported", JOptionPane.WARNING_MESSAGE);
                    }
                    
                    return null;
                }
                
                @Override
                protected void done() {
                    try {
                        Object result = get();
                        if (result != null) {
                            if (result instanceof String) {
                                String fileContent = (String) result;
                                setAppStatusText(String.format("File Selected: %s ", selectedEntry.getName()));
                                textAreaFilePreview.setText(fileContent);
                                imagePreviewLabel.setIcon(null);
                            } else if (result instanceof ImageIcon) {
                                ImageIcon imageFile = (ImageIcon) result;
                                Image image = imageFile
                                        .getImage()
                                        .getScaledInstance(imagePreviewLabel.getWidth(), imagePreviewLabel.getHeight(), Image.SCALE_SMOOTH);
                                setAppStatusText(String.format("File Selected: %s ", selectedEntry.getName()));
                                imagePreviewLabel.setIcon(new ImageIcon(image));
                                imagePreviewLabel.setText("");
                            }
                        }
                    } catch (InterruptedException | ExecutionException e) {
                        Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, e);
                        JOptionPane.showMessageDialog(MainPanel.this, "Error while reading file content :" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            };
            
            worker.execute();
            
        }

    }//GEN-LAST:event_zipFileTreeValueChanged

    private void localFIleTreeValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_localFIleTreeValueChanged
        
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) localFIleTree.getLastSelectedPathComponent();
        
        if (selectedNode != null && selectedNode.isLeaf()) {
            
            final String selectedEntry = (String) selectedNode.getUserObject();
            
            LocalFilePreviewWorker worker = new LocalFilePreviewWorker(selectedEntry);
            worker.execute();
            
            worker.addPropertyChangeListener(event -> {
                
                if (SwingWorker.StateValue.DONE.equals(event.getNewValue())) {
                    
                    try {
                        AbstractFileReader<?> result = worker.get();
                        
                        if (result instanceof TextFileReader) {
                            TextFileReader textFileReader = (TextFileReader) result;
                            String fileContent = textFileReader.readFile();
                            setAppStatusText(String.format("File Selected: %s ", selectedEntry));
                            setPreviewContent(fileContent, null);
                        } else if (result instanceof ImageFileReader) {
                            ImageFileReader imageFileReader = (ImageFileReader) result;
                            ImageIcon imageFile = imageFileReader.readFile();
                            Image image = imageFile
                                    .getImage()
                                    .getScaledInstance(imagePreviewLabel.getWidth(), imagePreviewLabel.getHeight(), Image.SCALE_SMOOTH);
                            setAppStatusText(String.format("File Selected: %s ", selectedEntry));
                            setPreviewContent("", new ImageIcon(image));
                        }
                        
                    } catch (InterruptedException | ExecutionException | IOException e) {
                        JOptionPane.showMessageDialog(MainPanel.this, "Error while reading file content :" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        }
        
        if (selectedNode != null && !selectedNode.isLeaf()) {
            
            final String selectedEntry = (String) selectedNode.getUserObject();
            
            SwingWorker<Void, Void> watchWorker = new SwingWorker<>() {
                
                @Override
                protected Void doInBackground() {
                    try (WatchService activeWatchService = FileSystems.getDefault().newWatchService();) {
                        
                        Path pathToWatch = Paths.get(selectedEntry);
                        pathToWatch.register(activeWatchService, StandardWatchEventKinds.ENTRY_CREATE,
                                StandardWatchEventKinds.ENTRY_DELETE,
                                StandardWatchEventKinds.ENTRY_MODIFY);
                        
                        WatchKey key;
                        while ((key = activeWatchService.take()) != null) {
                            for (WatchEvent<?> event : key.pollEvents()) {
                                SwingUtilities.invokeLater(() -> {
                                    DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) selectedNode.getParent();
                                    if (parentNode != null) {
                                        int selectedIndex = parentNode.getIndex(selectedNode);
                                        File selectedDir = new File(selectedEntry);
                                        DefaultTreeModel treeModel = (DefaultTreeModel) localFIleTree.getModel();
                                        DefaultMutableTreeNode updatedNode = new DefaultMutableTreeNode(selectedDir.getPath());
                                        TreeFileStrucutre.buildFileSystemTree(treeModel, updatedNode, selectedDir);
                                        treeModel.removeNodeFromParent(selectedNode);
                                        treeModel.insertNodeInto(updatedNode, parentNode, selectedIndex);
                                        treeModel.reload(parentNode);
                                    }
                                });
                            }
                            key.reset();
                        }
                    } catch (InterruptedException | IOException ex) {
                        JOptionPane.showMessageDialog(MainPanel.this, "File not found or you do not have access permission:" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    
                    return null;
                }
            };
            
            watchWorker.execute();
            
        }
    }//GEN-LAST:event_localFIleTreeValueChanged

    private void connectButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_connectButtonMouseClicked
        
        String server = serverText.getText().trim();
        String port = portNumber.getValue().toString();
        String username = usernameText.getText().trim();
        char[] passwordChars = passwordText.getPassword();
        String password = new String(passwordChars);
        
        if (server.isEmpty() || port.isEmpty() || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are required. Please fill in all the details.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        FTPConnectionWorker worker = new FTPConnectionWorker(server, Integer.parseInt(port),
                username, password);
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("ftp://" + server + ":" + port);
        
        worker.addPropertyChangeListener(event -> {
            
            if (SwingWorker.StateValue.DONE.equals(event.getNewValue())) {
                try {
                    FTPClient result = worker.get();
                    
                    DefaultTreeModel treeModel = new DefaultTreeModel(root);
                    FTPTreeWillExpandListener fTPTreeWillExpandListener = new FTPTreeWillExpandListener(result);
                    fTPTreeWillExpandListener.buildFTPSystemTree(treeModel, root, "/");
                    
                    ftpTree.addTreeWillExpandListener(fTPTreeWillExpandListener);
                    
                    ftpTree.setModel(treeModel);
                    setAppStatusText(String.format("Connected to server: %s on port: %s", server, port));
                    connectButton.setEnabled(false);
                    disconnectButton.setEnabled(true);
                } catch (InterruptedException | ExecutionException | IOException e) {
                    JOptionPane.showMessageDialog(MainPanel.this, "Error while connecting to FTP server:" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        worker.execute();
    }//GEN-LAST:event_connectButtonMouseClicked

    private void exitMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuActionPerformed

    private void archivedMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_archivedMenuActionPerformed
        
        List<FileNameExtensionFilter> fileFilters = new ArrayList<>() {
            {
                add(new FileNameExtensionFilter("ZIP Files", FileType.ZIP.name().toLowerCase()));
            }
        };
        
        File selectedFile = new FileChooserWrapper("Select ZIP file", fileFilters).getSelectedFile(this);
        
        if (selectedFile != null) {
            
            SwingWorker<DefaultTreeModel, DefaultTreeModel> worker = new SwingWorker<DefaultTreeModel, DefaultTreeModel>() {
                
                @Override
                protected DefaultTreeModel doInBackground() throws Exception {
                    
                    AbstractFileReader<?> fileReader;
                    String fileName = selectedFile.getName().toLowerCase();
                    
                    if (ZIPFileReader.isZipFile(fileName)) {
                        fileReader = new ZIPFileReader(selectedFile);
                        return (DefaultTreeModel) fileReader.readFile();
                    } else {
                        JOptionPane.showMessageDialog(MainPanel.this, "You can only select Zip File", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    
                    return null;
                }
                
                @Override
                protected void done() {
                    try {
                        DefaultTreeModel buildZipContentsTree = get();
                        setAppStatusText(String.format("File Selected: %s", selectedFile.getPath()));
                        zipFileTree.setModel(buildZipContentsTree);
                        textAreaFilePreview.setText("Select a file to see a preview here .");
                        imagePreviewLabel.setIcon(null);
                    } catch (InterruptedException | ExecutionException ex) {
                        Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(MainPanel.this, "Error while reading zip file content", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    
                }
                
            };
            
            worker.execute();
        }
    }//GEN-LAST:event_archivedMenuActionPerformed

    private void disconnectButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_disconnectButtonMouseClicked
        
        try {
            FTPConnection.getInstance().disconnect();
            setAppStatusText("Disconnected from server.");
            disconnectButton.setEnabled(false);
            connectButton.setEnabled(true);
            ftpTree.setModel(null);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error while disconnecting local file system. " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_disconnectButtonMouseClicked

    private void ftpTreeValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_ftpTreeValueChanged
        
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) ftpTree.getLastSelectedPathComponent();
        if (selectedNode != null && selectedNode.isLeaf()) {
            final String selectedEntry = (String) selectedNode.getUserObject();
            
            FTPFilePreviewWorker worker = new FTPFilePreviewWorker(FTPConnection.getInstance().getFtpClient(), selectedEntry);
            worker.execute();
            
            worker.addPropertyChangeListener(event -> {
                
                if (SwingWorker.StateValue.DONE.equals(event.getNewValue())) {
                    try {
                        Object result = worker.get();
                        if (result != null) {
                            if (result instanceof String) {
                                String fileContent = (String) result;
                                setAppStatusText(String.format("File Selected: %s ", selectedEntry));
                                textAreaFilePreview.setText(fileContent);
                                imagePreviewLabel.setIcon(null);
                            } else if (result instanceof ImageIcon) {
                                ImageIcon imageFile = (ImageIcon) result;
                                Image image = imageFile
                                        .getImage()
                                        .getScaledInstance(imagePreviewLabel.getWidth(), imagePreviewLabel.getHeight(), Image.SCALE_SMOOTH);
                                setAppStatusText(String.format("File Selected: %s ", selectedEntry));
                                imagePreviewLabel.setIcon(new ImageIcon(image));
                                imagePreviewLabel.setText("");
                            }
                        }
                    } catch (InterruptedException | ExecutionException e) {
                        JOptionPane.showMessageDialog(MainPanel.this, "Error while reading file content :" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        }
    }//GEN-LAST:event_ftpTreeValueChanged
    
    private void setAppStatusText(String message) {
        this.statusLabel.setText(message);
    }
    
    private void setPreviewContent(String text, ImageIcon image) {
        imagePreviewLabel.setIcon(image);
        textAreaFilePreview.setText(text);
        
    }
    
    private void initializeFileSystemTree() {
        
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Local File System");
        DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
        localFIleTree.addTreeWillExpandListener(new FileTreeWillExpandListener());
        
        SwingWorker<DefaultTreeModel, Void> worker = new SwingWorker<>() {
            @Override
            protected DefaultTreeModel doInBackground() {
                File file = new File("/");
                TreeFileStrucutre.buildFileSystemTree(treeModel, rootNode, file);
                return treeModel;
            }
            
            @Override
            protected void done() {
                try {
                    DefaultTreeModel treeModel = get();
                    localFIleTree.setModel(treeModel);
                } catch (InterruptedException | ExecutionException e) {
                    JOptionPane.showMessageDialog(MainPanel.this, "Error while loading local file system", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        
        worker.execute();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu aboutMenu;
    private javax.swing.JMenuItem archivedMenu;
    private javax.swing.JButton connectButton;
    private javax.swing.JButton disconnectButton;
    private javax.swing.JMenuItem exitMenu;
    private javax.swing.JTree ftpTree;
    private javax.swing.JLabel imagePreviewLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JTree localFIleTree;
    private javax.swing.JPasswordField passwordText;
    private javax.swing.JSpinner portNumber;
    private javax.swing.JTextField serverText;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JTextArea textAreaFilePreview;
    private javax.swing.JTextField usernameText;
    private javax.swing.JTree zipFileTree;
    // End of variables declaration//GEN-END:variables
}
