/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

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
