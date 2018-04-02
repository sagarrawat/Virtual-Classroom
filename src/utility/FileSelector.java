/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.io.File;

/**
 *
 * @author sagar
 */
public class FileSelector extends javax.swing.filechooser.FileFilter {

    private final String fileExtension;
    
    public static FileSelector getFileFillter (String fileExtension){
        return new FileSelector (fileExtension);
    }

    private FileSelector(String fileExtension) {
        this.fileExtension = fileExtension;
    }
    
    
    @Override
    public boolean accept(File file) {
        return file.isDirectory() || file.getAbsolutePath().endsWith(fileExtension);
    }

    @Override
    public String getDescription() {
        return fileExtension;
    }
    
}
