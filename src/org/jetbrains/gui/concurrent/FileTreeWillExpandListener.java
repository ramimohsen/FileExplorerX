package org.jetbrains.gui.concurrent;

import java.io.File;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.ExpandVetoException;

/**
 *
 * @author ramy-mohsen
 */
public class FileTreeWillExpandListener implements TreeWillExpandListener {

    @Override
    public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) event.getPath().getLastPathComponent();
        Object userObject = node.getUserObject();

        if (userObject instanceof String) {
            String nodeName = (String) userObject;
            File file = new File(nodeName);

            if (file.isDirectory() && node.getChildCount() == 1 && node.getFirstChild() instanceof DefaultMutableTreeNode) {
                DefaultMutableTreeNode dummyNode = (DefaultMutableTreeNode) node.getFirstChild();

                if (dummyNode.getUserObject() == null) {
                    DefaultTreeModel model = (DefaultTreeModel) ((JTree) event.getSource()).getModel();
                    model.removeNodeFromParent(dummyNode);
                    buildFileSystemTree(model, node, file);
                }
            }
        }
    }

    @Override
    public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {
    }

    private void buildFileSystemTree(DefaultTreeModel treeModel, DefaultMutableTreeNode parent, File directory) {
        File[] files = directory.listFiles();
        if (files == null) {
            return;
        }

        for (File file : files) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(file.getAbsolutePath());
            treeModel.insertNodeInto(node, parent, parent.getChildCount());
            if (file.isDirectory()) {
                node.add(new DefaultMutableTreeNode());
            }
        }
    }
}
