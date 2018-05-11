/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

/**
 *
 * @author mukesh
 */
public class Resource {
    
    public static String getDefaultPath(){
        String user = System.getProperty("user.name");
        String resourseFolder = "/home/"+user+"/vcr";
        return resourseFolder;
    }   
}
