package org.jetbrains.file;

import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author ramy-mohsen
 */
public final class TreeFileStrucutre {

    private final ZipFile zipFile;

    public TreeFileStrucutre(ZipFile zipFile) {
        this.zipFile = zipFile;
    }

    public DefaultTreeModel buildZipContentsTree() {

        if (this.zipFile != null) {

            DefaultMutableTreeNode root = new DefaultMutableTreeNode(this.zipFile.getName());

            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            Map<String, DefaultMutableTreeNode> nodeMap = new HashMap<>();

            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                String entryName = entry.getName();

                String[] entryParts = entryName.split("/");

                DefaultMutableTreeNode parentNode = root;
                String currentPath = "";

                for (int i = 0; i < entryParts.length; i++) {
                    String part = entryParts[i];
                    currentPath += part + "/";
                    DefaultMutableTreeNode node = nodeMap.get(currentPath);

                    if (node == null) {
                        node = new DefaultMutableTreeNode(part);
                        nodeMap.put(currentPath, node);
                        parentNode.add(node);
                    }

                    if (i == entryParts.length - 1 && !entry.isDirectory()) {
                        DefaultMutableTreeNode fileNode = new DefaultMutableTreeNode(entry);
                        node.add(fileNode);
                    }

                    parentNode = node;
                }
            }

            return new DefaultTreeModel(root);
        }

        return null;
    }

    public static void buildFileSystemTree(DefaultTreeModel treeModel, DefaultMutableTreeNode parent, File directory) {
        File[] files = directory.listFiles();
        if (files == null) return;

        for (File file : files) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(file.getAbsolutePath());
            treeModel.insertNodeInto(node, parent, parent.getChildCount());

            if (file.isDirectory()) {
                node.add(new DefaultMutableTreeNode());
            }
        }
    }

}
