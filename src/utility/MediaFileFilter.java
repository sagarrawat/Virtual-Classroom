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
        // implement the logic to select files here..
        String name = file.getName().toLowerCase();
        //return name.endsWith(".java") || name.endsWith(".class");
        return name.length()<20;
    }
}
