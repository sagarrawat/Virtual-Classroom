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
public enum UserType {
    ADMIN ,
    FACULTY ,
    STUDENT ;
    
    public static int valueOf(UserType type){

        switch (type) {
            case ADMIN:
                return 0;                
            case FACULTY:
                return 1;
            case STUDENT:
                return 2;
            default:
                return -1;
        }
    
    }
    
    public static UserType getType (int value){
    
        switch (value) {
            case 0:
                return UserType.ADMIN;                
            case 1:
                return UserType.FACULTY;
            case 2:
                return UserType.STUDENT;
            default:
                return null;
        }
    }
    
}
