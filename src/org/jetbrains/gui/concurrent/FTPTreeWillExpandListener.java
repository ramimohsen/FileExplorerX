package org.jetbrains.gui.concurrent;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.ExpandVetoException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author ramy-mohsen
 */
public class FTPTreeWillExpandListener implements TreeWillExpandListener {

    private final FTPClient fTPClient;

    public FTPTreeWillExpandListener(FTPClient fTPClient) {
        this.fTPClient = fTPClient;
    }

    @Override
    public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) event.getPath().getLastPathComponent();
        Object userObject = node.getUserObject();

        if (userObject instanceof String) {
            String nodeName = (String) userObject;

            if (node.getChildCount() == 1 && node.getFirstChild() instanceof DefaultMutableTreeNode) {
                DefaultMutableTreeNode dummyNode = (DefaultMutableTreeNode) node.getFirstChild();

                if (dummyNode.getUserObject() == null) {
                    DefaultTreeModel model = (DefaultTreeModel) ((JTree) event.getSource()).getModel();
                    model.removeNodeFromParent(dummyNode);
                    try {
                        buildFTPSystemTree(model, node, nodeName);
                    } catch (IOException ex) {
                        Logger.getLogger(FTPTreeWillExpandListener.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    @Override
    public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {
    }

    public void buildFTPSystemTree(DefaultTreeModel treeModel, DefaultMutableTreeNode parent, String directory) throws IOException {
        FTPFile[] files = this.fTPClient.listFiles(directory);
        if (files == null) {
            return;
        }
        for (FTPFile file : files) {
            String filePath;

            if (directory.equals("/")) { // Special case for root directory
                filePath = "/" + file.getName();
            } else {
                filePath = directory + "/" + file.getName();
            }
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(filePath);
            treeModel.insertNodeInto(node, parent, parent.getChildCount());

            if (file.isDirectory()) {
                node.add(new DefaultMutableTreeNode());
            }
        }
    }
}
