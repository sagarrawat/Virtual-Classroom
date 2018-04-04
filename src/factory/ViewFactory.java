/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import javax.swing.JFrame;
import javax.swing.JPanel;
import virtaulclassroom.IChangeView;

/**
 *
 * @author mukesh
 */
public class ViewFactory {
    
    private ViewFactory(){
        
    }
    
    public static JPanel getView(IChangeView parent, View view){
        switch(view){
            case Home :
                return new ui.Home(parent);
            case Login :
                return new ui.Login(parent);
            case Register :
                return new ui.Register(parent);
            case LectureRoom:
                return new ui.LectureRoom(parent);
            case Setting :
                return new ui.Setting(parent);
            case Assignment :
                return new ui.Assignment(parent);
            case Resources:
                return new ui.RExplorer();
        }
        return null;
    }
    
}
