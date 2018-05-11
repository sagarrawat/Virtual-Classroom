/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import entity.CurrentUser;
import entity.Session;
import entity.User;
import entity.UserType;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
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
            String sql = "select name from branch where course_id = (select id from course where name = ?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, course);
            rs = ps.executeQuery();
            
            while (rs.next())
                branch.add(rs.getString ("name"));
                
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    
        return branch;
    }
    
    // get the name of all subject in branch of any course presesnt
    public ArrayList<String> getAllSubjects (String branch){
        
        ArrayList<String> subject = new ArrayList<>();
        
        try{
            String sql = "select name from subject where branch_id = (select id from branch where name = ?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, branch);
            rs = ps.executeQuery();
            
            while (rs.next())
                subject.add(rs.getString ("name"));
                
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
     
     public ArrayList<String> getAllSubjects (int type, String username){
        
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
            String sql = "insert into course (name) values (?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, courseName);
            
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Successfull");
            
        }catch (SQLException se){
            se.printStackTrace();
        }
    }
    
    //insert a branch in db
    public void addBranch (String branch, String course){
        
        try{
            String sql = "insert into branch (name , course_id) values (?, (select id from course where name = ?))";
            
            ps = connection.prepareStatement(sql);
            ps.setString (1, branch);
            ps.setString (2, course);
            
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Successfull");
            
        }catch (SQLException se){
            se.printStackTrace();
        }
    }
    
    //insert a subject in db
    public void addSubject (String subject , String branch){
        
        try{
            String sql = "insert into subject (name, branch_id) values ( ?, (select id from branch where name = ?) )";
            
            ps = connection.prepareStatement(sql);
            ps.setString (1, subject);
            ps.setString (2, branch);
            
            
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
            
            int stat = ps.executeUpdate();
            
            if (stat == 1)  
                JOptionPane.showMessageDialog(null, "successfully removed");
        
        }catch (SQLException se){se.printStackTrace();}
    }
    
    
    
    public void addFacultySubject (String username , String subject){
        try{
            String sql = "insert into faculty values( (select id from subject where name = ?) , ?)";
            ps = connection.prepareStatement(sql);
            
            ps.setString (1, subject);
            ps.setString (2, username);
            
            ps.executeUpdate();
            
        
        }catch (SQLException se){
            se.printStackTrace();
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
    
    public boolean removeCourse (String course){
        
        try{
            String sql = "delete from course where name = ?";
            ps = connection.prepareStatement(sql);
            
            ps.setString(1, course);
            
            int status = ps.executeUpdate();
            
            if (status != 1)
                return true;
        
        }catch (SQLException e){
            e.printStackTrace();
        }
        
        return false;
    }
    
    public boolean removeBranch (String branch){
        
        try{
            String sql = "delete from branch where name = ?";
            ps = connection.prepareStatement(sql);
            
            ps.setString(1, branch);
            
            int status = ps.executeUpdate();
            
            if (status == 1)
                return true;
        
        }catch (SQLException se){
            se.printStackTrace();
        }
        
        return false;
    }
    
    public boolean removeSubject (String subject){
        
        try{
            String sql = "delete from subject where name = ?";
            ps = connection.prepareStatement(sql);
            
            ps.setString(1, subject);
            
            int status = ps.executeUpdate();
            
            if (status == 1)
                return true;
        
        }catch (SQLException se){
            se.printStackTrace();
        }
        
        return false;
    }

    public boolean updateUserDetail (String username , String full_name, String id, String phone){
    
        try{
            String sql = "update user set full_name = ? , id_no = ?, phone = ? where username = ?";
            ps = connection.prepareStatement(sql);
            
            ps.setString(1, full_name);
            ps.setString(2, id);
            ps.setString(3, phone);
            ps.setString(4, username);
            
            int stat = ps.executeUpdate();
            
            if (stat == 1)
                return true;
            
        
        }catch (SQLException se){
            se.printStackTrace();
        }
    
        return false;
    }
    
    public boolean addSession(Session session){
          
        try{
            String sql = "insert into Session (subject , topic, date, type, hostid, hostaddress) values (?, ?, ?, ?, ?, ?)";
            
            ps = connection.prepareStatement(sql);
            ps.setString (1, session.getsubject());
            ps.setString (2, session.getTopic());        
            ps.setDate(3, new java.sql.Date(2018, 5, 21));
            //ps.setDate(3, new java.sql.Date(session.getTime().getTime()));
            ps.setInt(4, session.getType());
            ps.setString(5, session.getHostId());
            ps.setString(6, session.gethostAdd()); 
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Successfull");
            return true;   
            
        }catch (SQLException se){
            se.printStackTrace();
            return false;
        }
    }
    
    public ArrayList<Session> getSessions(User usr){
        try{
            ArrayList<Session> sessions = new ArrayList<Session>();
            String sql = "select id, subject, topic, date, type, hostid, hostaddress,ext from Session where hostid=?;";
            ps = connection.prepareStatement(sql);
            ps.setString(1, usr.getUsername());
            
            if(usr.getType() == UserType.STUDENT){
                sql = "select id, subject, topic, date, type, hostid, hostaddress,ext from Session;";
                ps = connection.prepareStatement(sql);
            }
            
            rs = ps.executeQuery();
        
            while (rs.next()){  
                Session ses = new Session();
                ses.setId(rs.getInt("id"));
                ses.setSubject(rs.getString("subject"));
                ses.setTopic(rs.getString("topic"));
                ses.setTime(rs.getDate("date"));
                ses.setType(rs.getInt("type"));
                ses.setHostId(rs.getString("hostid"));
                ses.sethostAdd(rs.getString("hostaddress"));
                ses.setExt(rs.getString("ext"));
                sessions.add(ses);
            }
            return sessions;
        }catch (Exception e){
            e.printStackTrace();
        }
        
        return null;
    }
    
    public Session getSession(int sesid){
        try{
            String sql = "select id, subject, topic, date, type, hostid, hostaddress, ext from Session where id=?;";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, sesid);
            rs = ps.executeQuery();
        
            if (rs.next()){  
                Session ses = new Session();
                ses.setId(rs.getInt("id"));
                ses.setSubject(rs.getString("subject"));
                ses.setTopic(rs.getString("topic"));
                ses.setTime(rs.getDate("date"));
                ses.setType(rs.getInt("type"));
                ses.setHostId(rs.getString("hostid"));
                ses.sethostAdd(rs.getString("hostaddress"));
                ses.setExt(rs.getString("ext"));
                return ses;
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }
        
        return null;
    }
    
    private String getExt(String name){
        String nms[] = name.split("\\.(?=[^\\.]+$)");
        return nms[nms.length-1];
    }
    
    public void uploadSession(File file, int sesId){
          try{
            String sql = "update Session set media = ?, ext=? where id = ?;";        
            ps = connection.prepareStatement(sql);         
            ps.setBlob(1, new FileInputStream (file) );
        
            ps.setString(2, getExt(file.getName()));
            ps.setInt (3, sesId);            
            ps.executeUpdate();      
            JOptionPane.showMessageDialog(null , "successfully added");
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null , "Error!");
        }  
    }
    
      public InputStream getMediaStream(int sesId){
          try{
            String sql = "select media from Session where id = ?;";        
            ps = connection.prepareStatement(sql);         
            ps.setInt(1, sesId);
            rs = ps.executeQuery();  
            if(rs.next()){
                return rs.getBlob("media").getBinaryStream();
            }
            JOptionPane.showMessageDialog(null , "successfully added");
        }catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null , "Error!");
        }
          
        return null;
    }
}
