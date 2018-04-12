/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

/**
 *
 * @author sagar
 */

import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.headless.HeadlessMediaPlayer;
import uk.co.caprica.vlcj.test.VlcjTest;

/**
 * An example of how to stream a media file over HTTP.
 * <p>
 * The client specifies an MRL of <code>http://127.0.0.1:5555</code>
 */
public class Stream extends VlcjTest {

    private static HeadlessMediaPlayer mediaPlayer;
    private static MediaPlayerFactory mediaPlayerFactory;
    
    private static boolean isStream = false;
    
    public static String startStream () throws Exception {
        
        String media =  "v4l2://";
        String options = ":sout=#transcode{vcodec=h264,acodec=mpga,ab=128,channels=2,samplerate=44100}:http{mux=ffmpeg{mux=flv},dst=:8080/}";
        
        mediaPlayerFactory = new MediaPlayerFactory();
        mediaPlayer = mediaPlayerFactory.newHeadlessMediaPlayer();
        
        mediaPlayer.playMedia(media, options , ":no-sout-rtp-sap", ":no-sout-standard-sap", ":sout-all", ":sout-keep");
       
        isStream = true;
        
        // Don't exit
    //    Thread.currentThread().join();

        return "http://localhost:8080";
    }
    
    public static void stopStream(){
        if (isStream == true){
           mediaPlayer.stop();
           isStream = false;
        }
        
    } 
    
    private static String formatHttpStream(String serverAddress, int serverPort) {
        StringBuilder sb = new StringBuilder(60);
        sb.append(":sout=#duplicate{dst=std{access=http,mux=ts,");
        sb.append("dst=");
        sb.append(serverAddress);
        sb.append(':');
        sb.append(serverPort);
        sb.append("}}");
        return sb.toString();
    }

}
