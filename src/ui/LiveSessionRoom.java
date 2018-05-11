package ui;

import dal.Database;
import dal.DerbyDatabase;
import entity.CurrentUser;
import entity.Session;
import entity.User;
import entity.UserType;
import factory.View;
import factory.ViewFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import ui.partial.Chat;
import ui.partial.VPlayer;
import utility.Resource;
import virtaulclassroom.IChangeView;

/**
 *
 * @author sagar
 */
public class LiveSessionRoom extends javax.swing.JPanel {    
    IChangeView parent;
    Database database;
    Session session;
    VPlayer vp;
    Chat chat;
    private User user;
    private JFileChooser fileChooser;
    
    public LiveSessionRoom() {
        initComponents();
        
        vp = new VPlayer();
        chat = new Chat();
        fileChooser = new JFileChooser();
        streamPanel.add(vp);
        streamPanel.revalidate();
        streamPanel.repaint();
        user = CurrentUser.getCurrentUser().getUser(); 
        database = new DerbyDatabase();
        
        chatPanel.add (chat);
        chatPanel.revalidate();
        chatPanel.repaint();
        
        initSession();
    }
    
    public LiveSessionRoom (IChangeView parent){
        this();
        this.parent = parent;
    }

    public LiveSessionRoom (IChangeView parent, Session ses){
        this();
        this.parent = parent;
        this.session = ses;
        jLabel1.setText(ses.getTopic() + "  @["+ses.getTime()+"]");
       
    }
    
    private void processMedia(int c){
        if(c>1) {
            JOptionPane.showMessageDialog(this, "fail to play");
            return;
        }
        try{
        if(session.getType() == 1){
            String base = Resource.getDefaultPath();
            String fPath = base + "/"+session.getsubject()+"/"+session.getID()+"_"+session.getTopic()+"."+session.getExt();          
            File f = new File(fPath);
            if(f.exists()){
                JOptionPane.showMessageDialog(this, "Start?");
                vp.playMedia(fPath);
            }else {
                f.getParentFile().mkdirs();
                InputStream is = database.getMediaStream(session.getID());
                if(is==null){
                    JOptionPane.showMessageDialog(this, "No media found");
                    return;
                }
                OutputStream os = new FileOutputStream(f);
                int read = 0;
		byte[] bytes = new byte[1024];
                while ((read = is.read(bytes)) != -1) {
                    os.write(bytes, 0, read);
		}
                processMedia(c++);
            }
        }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    
    private void initSession(){
        if(user.getType()==UserType.STUDENT){
            jButton1.setVisible(false);
            jButton2.setVisible(false);
            jButton3.setVisible(false);
        }else{
            jButton1.setVisible(true);
            jButton2.setVisible(true);
            jButton3.setVisible(true);
        }
    }
  
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        streamPanel = new javax.swing.JPanel();
        chatPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setBackground(new java.awt.Color(33, 137, 212));

        streamPanel.setBackground(new java.awt.Color(33, 137, 212));
        streamPanel.setLayout(new java.awt.BorderLayout());

        chatPanel.setBackground(new java.awt.Color(33, 137, 212));
        chatPanel.setLayout(new javax.swing.BoxLayout(chatPanel, javax.swing.BoxLayout.LINE_AXIS));

        jPanel2.setBackground(new java.awt.Color(210, 210, 210));

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/icons/home16.png"))); // NOI18N
        jLabel2.setText("Home");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel3.setText("<< Back");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 22)); // NOI18N
        jLabel1.setText("Topic of Diescussion OR Some Controls may go here ...");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jButton1.setText("Stop Session");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("GoLive");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Upload");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Start");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70))
                            .addComponent(streamPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chatPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(streamPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3)
                            .addComponent(jButton4)))
                    .addComponent(chatPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
           parent.requestView( ViewFactory.getView (parent , View.Home) );
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try{
            String media = utility.Stream.startStream();
            String[] options = {
                ":file-caching=0",
                ":network-caching=300",
                ":sout = #transcode{vcodec=x264,vb=800,scale=0.25,acodec=none,fps=23}:display :no-sout-rtp-sap :no-sout-standard-sap :ttl=1 :sout-keep"};
            vp.playMedia(media, options);
            
        }catch (Exception e){}
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        utility.Stream.stopStream();
            
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        parent.requestView(ViewFactory.getView (parent, View.Home));
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
         fileChooser.addChoosableFileFilter(new FileNameExtensionFilter(".mp4","mp4", "mp3", "avi"));
        
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION ){
            File file = fileChooser.getSelectedFile();
            String filep = file.getAbsolutePath();
            int choice = JOptionPane.showConfirmDialog(this, "Are you sure upload ? "+filep);
            if (choice == 0){
                database.uploadSession(file, session.getID());
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        vp.Stop();
          parent.requestView(ViewFactory.getView (parent, View.LiveSessionRoom));
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        vp.Stop();
        processMedia(0);
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel chatPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel streamPanel;
    // End of variables declaration//GEN-END:variables
}
