/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.File;
import java.util.Date;

/**
 *
 * @author mukesh
 */
public class Session {

    private int Sid;
    private String subject;
    private int type;
    private String topic;
    private Date time;
    private String hostId;
    private String hostAdd;
    private String ext;
    
    public String getExt(){
        return ext;
    }
    
    public void setExt(String ext){
        this.ext = ext;
    }
    
    public String gethostAdd(){
        return hostAdd;
    }
    
    public void sethostAdd(String hostAdd){
        this.hostAdd = hostAdd;
    }
    
    public int getID(){
        return Sid;
    }
    
    public void setId(int id){
        this.Sid = id;
    }
    
    public String getsubject(){
        return subject;
    }
    
    public void setSubject(String subj){
        subject = subj;
    }
    
    public String getTopic(){
        return topic;
    }
    
    public void setTopic(String topic){
        this.topic = topic;
    }
    
    public int getType(){
        return type;
    }
    
    public void setType(int type){
        this.type = type;
    }
    
    public Date getTime(){
        return time;
    }
    
    public void setTime(Date dt){
        this.time = time;
    }
    
    public String getHostId(){
        return hostId;
    }
    
    public void setHostId(String id){
        this.hostId = id;
    }
}
