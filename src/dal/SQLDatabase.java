/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import entity.Session;
import entity.User;
import entity.UserType;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author mukesh
 */
public class SQLDatabase extends Database {
    
    public SQLDatabase() {
        super(DBConstant.SQL_DRIVER, DBConstant.SQL_CON, DBConstant.USER, DBConstant.PASSWORD);
    }   

    @Override
    public User getUser(String username, String password) {
        User user = null;
        //TODO : getuser from db
        return user;
    }  
    
    
}
