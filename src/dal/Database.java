/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import entity.CurrentUser;
import entity.User;
import entity.UserType;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
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
        //System.out.println (username + password);
        try{
            String sql = "select type from user where username = ? AND password = ?;";
            ps = connection.prepareStatement(sql);
            ps.setString (1, username);
            ps.setString (2, password);
            rs = ps.executeQuery();
            
            if (rs.next()){
                int type = rs.getInt("type");
                UserType userType = UserType.getType (type);
                CurrentUser.setCurrentUser (new User (username, userType));
                
                return true;
            }
        
        }catch ( SQLException e){
            e.printStackTrace();
        }
        
        return false;
    }
    
    
    // creates a user account
    public boolean registerUser (UserType userType, String[] userDetail){
        
        int type = UserType.valueOf(userType);
        
        try {
            String sql = "insert into user values (? , ?, ? , ? , ? , ?)"; 
                    
            ps = connection.prepareStatement(sql);
            
            ps.setString(1, userDetail[0]);                              // username of user
            ps.setString (2, userDetail[1]);                             // password of user
            ps.setInt (3, type);                                         // type of user
            ps.setString (4, userDetail[2]);                             // full name of user
            ps.setString (5, userDetail[3]);                             // id no. of user
            ps.setString (6, userDetail[4]);                             // phone no. of user
            ps.executeUpdate();
            
            if ( UserType.getType(type) == UserType.STUDENT)
                System.out.println ("");
            
            javax.swing.JOptionPane.showMessageDialog(null, "You have been Successfully Registered to VCR");
            
            return true;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    // check weather, if a user already exist or not
    public boolean userExist (String username){
    
        try{
            String sql = "select (1) from user where username = ?";
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
            String sql = "select name from course";
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            
            while (rs.next())
                courses.add(rs.getString ("name"));
                
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    
        return courses;
    }
    
    
    // get the name of all branch in the course presesnt
    public ArrayList<String> getAllBranch (String course){
        
        ArrayList<String> branch = new ArrayList<>();
        
        try{
            String sql = "select branch from course where name = ?";
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
    
    // get the name of all subject in branch of any course presesnt
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
    
    //get all subject in present
     public ArrayList<String> getAllSubjects (){
        
        ArrayList<String> subject = new ArrayList<>();
        
        try{
            String sql = "select name from subject";
            statement = connection.createStatement();
            
            rs = ps.executeQuery(sql);
            
            while (rs.next())
                subject.add(rs.getString ("name"));
                
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    
        return subject;
    }
     
    // get all subject of a particular student
     
     public ArrayList<String> getAllSubjects (String username){
        
        ArrayList<String> subject = new ArrayList<>();
        
        try{
            String sql = "select subject from courses where username = ?";  // something like that
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
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
    public void addCourse (String courseName){
        
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
    public void addBranch (String courseName, String branchName){
        
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
    public void addSubject (String courseName, String branchName, String subjectName){
        
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
    
    
    // gives user's details
    public String[] getUserDetails( String username ){
        String userDetail[];
        try{
            String sql = "select username , full_name, id_no, phone from user where username = ? ";
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            
            if (rs.next()){
                userDetail = new String[4];
                
                userDetail[0] = rs.getString("username");
                userDetail[1] = rs.getString("full_name");
                userDetail[2] = rs.getString("id_no");
                userDetail[3] = rs.getString("phone");
                
            
                return userDetail;
            }
            
            
        }catch (SQLException se){
            se.printStackTrace();
        }
    
        return null;
    }
    
    
    
    public ArrayList<String> getSubjectByFacultyName (String username){
        
        ArrayList <String> subject = new ArrayList<>();
        
        try{
            String sql = "select name from subject where id in ( select subject_id from faculty where faculty_name = ?)";
            ps = connection.prepareStatement(sql);
            ps.setString (1, username);
            
            rs = ps.executeQuery();
            
            while (rs.next())
                subject.add (rs.getString ("name"));
            
            return subject;
            
        }catch (SQLException se){se.printStackTrace();}
    
        return null;
    }
    
    
    
    public void removeFacultySubject (String username , String subject){
        try{
            String sql = "delete from faculty where faculty_name = ? and subject_id = (select id from subject where name = ? )";
            ps = connection.prepareStatement(sql);
            
            ps.setString (1, username);
            ps.setString (2, subject);
            
            ps.execute();
            
            JOptionPane.showMessageDialog(null, "successfully deleted");
        
        }catch (SQLException se){se.printStackTrace();}
    }
    
    
    
    public void addFacultySubject (String username , String subject){
        try{
            String sql = "insert into faculty values( (select id from subject where name = ?) , ?)";
            ps = connection.prepareStatement(sql);
            
            ps.setString (1, username);
            ps.setString (2, subject);
            
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "successfully inserted");
        
        }catch (SQLException se){
            JOptionPane.showMessageDialog(null, "already selected");
        }
    }
    
    
    public void addAssignment ( File file , Date date , String course, String subject){
        
        try{
            String sql = "insert into assigment values (? , ? , ?, ?)";
            
            ps = connection.prepareStatement(sql);
            
            ps.setBlob(1, new FileInputStream (file) );
            ps.setDate(2, new java.sql.Date (date.getDate()));
            ps.setString (3, course);
            ps.setString (4, subject);
            
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null , "successfully added");
        
        }catch (SQLException se){
            se.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        
        
    }
    
    public Object[] getAssignment (String subject){
        
        try{
            String sql = "select * from assigment where subject = ? and submission_date < current_date"; // something like that
            ps = connection.prepareStatement(sql);
            ps.setString(1, subject);
            rs = ps.executeQuery();
        
            if (rs.next()){
             
                return new Object[] {
                    rs.getBlob("assignemtn_pdf").getBinaryStream(),
                    rs.getDate("submission date"),
                };
            
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }
        
    
        return null;
    }
    
    
    public void changePassword (String username, String password , String newPassword){
        try{
            String sql = "update user set password = ? where username=? and password = ?;";
            
            ps = connection.prepareStatement(sql);
            
            ps.setString (1, newPassword);
            ps.setString (2, username);
            ps.setString (3, password);
            
            int status = ps.executeUpdate();
            
            if (status != 0)
                JOptionPane.showMessageDialog(null, "successfully update");
            else
                JOptionPane.showMessageDialog(null, "invalid password");
        
        }catch (Exception e){
            e.printStackTrace();
        }
    
    }
    
    
}
