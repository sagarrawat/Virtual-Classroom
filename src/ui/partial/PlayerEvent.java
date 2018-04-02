/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.partial;

import javax.swing.JProgressBar;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;

/**
 *
 * @author mukesh
 */
public class PlayerEvent extends MediaPlayerEventAdapter{
    JProgressBar progressBar;
   
    public PlayerEvent(){
        
    }
    
    public PlayerEvent(JProgressBar progress){
        progressBar = progress;
    }
    
    @Override
    public void playing(MediaPlayer mediaPlayer) {
    }

    @Override
    public void finished(MediaPlayer mediaPlayer) {
    }

    @Override
    public void error(MediaPlayer mediaPlayer) {
    }
    
    
    @Override
    public void positionChanged(MediaPlayer mediaPlayer, float newPosition) {
        int iPos = (int)(newPosition * 100.0);
        progressBar.setValue(iPos);
    }
}
