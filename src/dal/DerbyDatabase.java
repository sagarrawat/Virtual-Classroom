/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import entity.User;
import java.sql.SQLException;

/**
 *
 * @author mukesh
 */
public class DerbyDatabase extends Database {

    public DerbyDatabase() {
        super(DBConstant.SQL_DRIVER, DBConstant.SQL_CON, DBConstant.USER, DBConstant.PASSWORD);
    }

    @Override
    public User getUser(String username, String password) {
        User user = null;
        try {
            String query = "SELECT uname FROM USERINFO WHERE uname = ? AND password = ?";
            
            ps= connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            
            if(rs.next()){
                user = new User(rs.getString("username") , null );      // User (username , UserType)
            }
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }
    
    
    
}
