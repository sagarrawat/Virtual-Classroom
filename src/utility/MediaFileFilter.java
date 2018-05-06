/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.io.File;
import java.io.FileFilter;

/**
 *
 * @author mukesh
 */
public class MediaFileFilter implements FileFilter {

    public boolean accept(File file) {
        if(file.isDirectory())
            return false;
        String name = file.getName();
        return name.endsWith(".mp4") || 
                name.endsWith(".mov") || 
                name.endsWith(".mkv") || 
                name.endsWith(".3gp") || 
                name.endsWith(".avi") || 
                name.endsWith(".mp3");

    }
}
