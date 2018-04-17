/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import entity.User;
import entity.UserType;
import java.awt.HeadlessException;
import static java.awt.image.ImageObserver.WIDTH;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

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
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                user = new User();
                user.id = 0;
                user.name = result.getString("uname");
            }
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }
    
    
    
}
