/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import entity.User;
import entity.UserType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author mukesh
 */
public abstract class Database {
    
    public Connection connection;
    public String driver;
    public PreparedStatement ps;
    public Statement statement;
    public ResultSet rs;
    
    public Database(String driver, String conString, String user, String password){
        try {
            this.driver = driver;
            Class.forName(driver);
            this.connection = DriverManager.getConnection(conString, user, password);
        } 
        catch (SQLException se){
            se.printStackTrace();
        }
        catch (Exception ex) {
           ex.printStackTrace();
        }
    }
    
    public abstract User getUser(String username, String password);
    
    
    public boolean authenticUser (String username , String password){
        if (1<2)
            return true;
        try{
            String sql = "select (1) from users where username = ? and password = ?";
            ps = connection.prepareStatement(sql);
            ps.setString (1, username);
            ps.setString (2, password);
            ResultSet rs = ps.executeQuery(sql);
            
            if (rs.next())
                return true;
            else 
                return false;
        
        }catch (Exception e){
            return false;
        }
              
    }
    
    public void registerUser (UserType type){
        /*
        if (type == UserType.STUDENT)
            try{
                String sql = "insert into user values (? , ? , ? , ? , ?)";
                ps = connection.prepareStatement(sql);
                ps.setInt (1, user.id);
                ps.setString (2, user.name);
                ps.setString (3, user.password);
                ps.setInt (4, user.phone);
                ps.setString (5, user.course);

                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "You are Successfully Registered to VCR", "Successfull", WIDTH);

            }catch (SQLException | HeadlessException e){
                e.printStackTrace();
            }
        
        else if (type == UserType.FACULTY){
        }*/
        
    }
    
}
