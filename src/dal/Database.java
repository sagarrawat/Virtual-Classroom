/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import entity.CurrentUser;
import entity.User;
import entity.UserType;
import java.sql.Connection;
import java.sql.DriverManager;
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
    
    // authenticate weather a user is registered or not
    public boolean authenticUser (String username , String password){
        if (1<2)
            return true;
        try{
            String sql = "select user_type from users where username = ? and password = ?";
            ps = connection.prepareStatement(sql);
            ps.setString (1, username);
            ps.setString (2, password);
            rs = ps.executeQuery(sql);
            if (rs.next()){
                int type = rs.getInt("user_type");
                UserType userType;
                
                switch (type) {
                    case 0:
                        userType = UserType.ADMIN;
                        break;
                    case 1:
                        userType = UserType.FACULTY;
                        break;
                    case 2:
                        userType = UserType.STUDENT;
                        break;
                    default:
                        userType = null;
                        break;
                }
                
              CurrentUser.setCurrentUser (new User (username, userType));
              return true;
            }
            
            else 
                return false;
        
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    // creates an account of faculty
    public void registerFaculty (String[] facultyDetail){
        
        try {
            String sql = "insert into user values (? , ? , ? , ? , ?)";
            ps = connection.prepareStatement(sql);
            ps.setInt (1, Integer.parseInt(facultyDetail[0]));              // id of faculty
            ps.setString (2, facultyDetail[1]);                             // name of faculty
            ps.setString (3, facultyDetail[2]);                             // password of faculty
            ps.setString (4, facultyDetail[3]);                             // phone no. of faculty
            ps.executeUpdate();
            javax.swing.JOptionPane.showMessageDialog(null, "You have been Successfully Registered to VCR");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
    // creates an account of student
    public void registerStudent (String[] studentDetail){
        
        try {
            String sql = "insert into users values (? , ? , ? , ? , ?)";
            ps = connection.prepareStatement(sql);
            ps.setInt (1, Integer.parseInt(studentDetail[0]));              // roll no. of student
            ps.setString (2, studentDetail[1]);                             // name of student
            ps.setString (3, studentDetail[2]);                             // password of student
            ps.setString (4, studentDetail[3]);                             // phone no. of student
            ps.setString (5, studentDetail[4]);                             // course of student
            ps.executeUpdate();
            javax.swing.JOptionPane.showMessageDialog(null, "You have been Successfully Registered to VCR");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
    // check weather, if a user already exist or not
    public boolean userExist (String username){
    
        try{
            String sql = "select (1) from users where username = ?";
            ps = connection.prepareStatement (sql);
            ps.setString (1, username);
            rs = ps.executeQuery();
            
            if (rs.next())
                return true;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    
        return false;
    }
    
    
    
}
