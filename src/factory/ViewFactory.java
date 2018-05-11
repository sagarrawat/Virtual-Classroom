/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

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
            case Setting :
                return new ui.Setting(parent);
            case Assignment :
                return new ui.Assignment(parent);
            case Resources:
                return new ui.ResourceViewer(parent);
            case Session :
                return new ui.LiveSessionRoom(parent);
            case LiveSessionRoom:
                return new ui.SessionView(parent);
            case Master :
                return new ui.Master(parent);
            case Profile :
                return new ui.Profile(parent);
        }
        return null;
    }
    
}
