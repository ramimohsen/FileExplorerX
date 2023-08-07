package org.jetbrains.file;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.swing.ImageIcon;
import javax.swing.tree.DefaultTreeModel;
import org.jetbrains.enums.FileType;

/**
 *
 * @author ramy-mohsen
 */
public class ZIPFileReader extends AbstractFileReader<DefaultTreeModel> {

    public ZIPFileReader(File selectedFIle) {
        super(selectedFIle);
    }

    @Override
    public DefaultTreeModel readFile() throws IOException {
        try (ZipFile zipFile = new ZipFile(this.getSelectedFIle())) {
            final TreeFileStrucutre treeFileStrucutre = new TreeFileStrucutre(zipFile);
            return treeFileStrucutre.buildZipContentsTree();
        } catch (IOException iOException) {
            throw new IOException("Can not read Zip File", iOException);
        }
    }

    public static String readZiPTextFileContent(String filePath, ZipEntry zipEntry) throws IOException {

        StringBuilder content = new StringBuilder();

        try (ZipFile zipFile = new ZipFile(filePath); InputStream inputStream = zipFile.getInputStream(zipEntry); InputStreamReader reader = new InputStreamReader(inputStream); BufferedReader bufferedReader = new BufferedReader(reader)) {
            char[] buffer = new char[4096];
            int bytesRead;
            while ((bytesRead = bufferedReader.read(buffer)) != -1) {
                content.append(buffer, 0, bytesRead);
            }
        }
        return content.toString();
    }

    public static ImageIcon readZiPImageFileContent(String filePath, ZipEntry zipEntry) throws IOException {

        try (ZipFile zipFile = new ZipFile(filePath); InputStream inputStream = zipFile.getInputStream(zipEntry); ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }

            byte[] imageData = byteArrayOutputStream.toByteArray();

            return new ImageIcon(imageData);

        } catch (IOException e) {
            throw new IOException("Error while reading Image file ", e);
        }
    }

    public static boolean isZipFile(String fileName) {
        return fileName.endsWith(FileType.ZIP.getExtension());
    }

}
