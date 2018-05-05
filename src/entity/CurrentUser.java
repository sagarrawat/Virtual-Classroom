/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author sagar
 */
public class CurrentUser {
    
    private static User user;
    private static CurrentUser currentUser;
    
    private CurrentUser (){
    
    }
    
    
    public static void setCurrentUser (User user){
        CurrentUser.user = user;
    }
    
    public static CurrentUser getCurrentUser (){
        
        if (user == null)
            return null;
        else if (currentUser == null)
            return new CurrentUser();
        else 
            return currentUser;
        
    }
    
    public User getUser (){
        return user;
    }
    
}
