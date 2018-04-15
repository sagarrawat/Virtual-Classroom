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
    
    private User user;
    
    private CurrentUser(){
        
    }
    
    public User getUser (){
        return new User();
    }
    
    public void setUser (User user){
        this.user = user;
    }
}
