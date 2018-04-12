/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import dal.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComponent;
import javax.swing.JTextPane;

/**
 *
 * @author sagar
 */
public class ChatThread extends Thread {
    
    private final JComponent component; 
    private Database db;
    private final String user;
    private int lastRead;
    
    
    
    public ChatThread ( JComponent component ,String user){
        this.component = component;
        this.user = user;
        this.lastRead = 0;
        
    }
    
    
    @Override
    public void run(){
        
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        JTextPane text = (JTextPane) this.component;
        
        try{
            String sql = "select * from /*chat*/ where id > ? ";

            preparedStatement= db.connection.prepareStatement(sql);
            preparedStatement.setString(2, user);

            while (true){

                preparedStatement.setInt(1, lastRead);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()){

                    text.setText( text.getText() + resultSet.getString("username") + " : " +resultSet.getString("message")+"\n");
                    lastRead = resultSet.getInt("id");
                    text.setCaretPosition(text.getDocument().getLength());
                }

            }
        }catch (SQLException se){
            se.printStackTrace();
        }
    
    }
}
