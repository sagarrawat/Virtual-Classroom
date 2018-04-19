/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author mukesh
 */
public class User {
    
    private String username;
    private UserType type;
    
    public User(){
    
    }
    
    public User (String username , UserType type){
        this.username = username;
        this.type = type;
    }
    
    
    public void setUsername (String username){
        this.username = username;
    }
    
    public String getUsername (){
        return username;
    }
    
    public void setType(UserType type){
        this.type = type;
    }
    
    public UserType getType(){
        return type;
    }
    
}
