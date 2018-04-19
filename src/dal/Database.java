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
import java.util.ArrayList;
import javax.swing.JOptionPane;

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
        if (1<2){
            CurrentUser.setCurrentUser (new User (username, UserType.STUDENT));
            return true;
        } // temporary
        
        try{
            String sql = "select user_type from users where username = ? ";//and password = ?";
            ps = connection.prepareStatement(sql);
            ps.setString (1, username);
           // ps.setString (2, password);
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
    
    // get the name of all courses presesnt
    public ArrayList<String> getAllCourses (){
        
        ArrayList<String> courses = new ArrayList<>();
        
        try{
            String sql = "select course_name from courses";
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            
            while (rs.next())
                courses.add(rs.getString ("course_name"));
                
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    
        return courses;
    }
    
    
    // get the name of all branch in the course presesnt
    public ArrayList<String> getAllBranch (String course){
        
        ArrayList<String> branch = new ArrayList<>();
        
        try{
            String sql = "select branch from courses where course_name = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, course);
            rs = ps.executeQuery();
            
            while (rs.next())
                branch.add(rs.getString ("branch"));
                
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    
        return branch;
    }
    
    // get the name of all subjecy in branch of any course presesnt
    public ArrayList<String> getAllSubjects (String course, String branch){
        
        ArrayList<String> subject = new ArrayList<>();
        
        try{
            String sql = "select subject from courses where course_name = ? and branch = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, course);
            ps.setString(2, branch);
            rs = ps.executeQuery();
            
            while (rs.next())
                subject.add(rs.getString ("subject"));
                
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    
        return subject;
    }
    
    
    // get the list of all student
    public ArrayList<String> getAllStudent (){
        
        ArrayList<String> student = new ArrayList<>();
        
        try{
            String sql = "select student_name from student";
            statement = connection.createStatement();
            rs = statement.executeQuery (sql);
            
            while (rs.next())
                student.add (rs.getString ("student_name"));
            
            
        }catch (SQLException se){
            se.printStackTrace();
        }
    
        return student;
    }
    
    
    
    // get the list of all faculty
    public ArrayList<String> getAllFaculty (){
        
        ArrayList<String> faculty = new ArrayList<>();
        
        try{
            String sql = "select faculty_name from faculty";
            statement = connection.createStatement();
            rs = statement.executeQuery (sql);
            
            while (rs.next())
                faculty.add (rs.getString ("faculty_name"));
            
            
        }catch (SQLException se){
            se.printStackTrace();
        }
    
        return faculty;
    }
    
    //inset a course into db
    public void insertCourse (String courseName){
        
        try{
            String sql = "insert into courses (course_name) values (?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, courseName);
            
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Successfull");
            
        }catch (SQLException se){
            se.printStackTrace();
        }
    }
    
    //insert a branch in db
    public void insertBranch (String courseName, String branchName){
        
        try{
            String sql = "insert into branch (course_name , branch_name) values (?, ?)";
            
            ps = connection.prepareStatement(sql);
            ps.setString (1, courseName);
            ps.setString (2, branchName);
            
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Successfull");
            
        }catch (SQLException se){
            se.printStackTrace();
        }
    }
    
    //insert a subject in db
    public void insertSubject (String courseName, String branchName, String subjectName){
        
        try{
            String sql = "insert into branch (course_name , branch_name, subject_name) values (?, ?, ?)";
            
            ps = connection.prepareStatement(sql);
            ps.setString (1, courseName);
            ps.setString (2, branchName);
            ps.setString (3, subjectName);
            
            
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Successfull");
            
        }catch (SQLException se){
            se.printStackTrace();
        }
    }
}
