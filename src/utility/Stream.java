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
    
    
    private static String formatRtspStream(String serverAddress, int serverPort, String id) {
        StringBuilder sb = new StringBuilder(60);
        sb.append(":sout=#rtp{sdp=rtsp://@");
        sb.append(serverAddress);
        sb.append(':');
        sb.append(serverPort);
        sb.append('/');
        sb.append(id);
        sb.append("}");
        return sb.toString();
    }
        
    public static String startStream () throws Exception {
        
        String media =  "v4l2://";
        String options = ":sout=#transcode{vcodec=h264, acodec=mpga, fps=20,ab=128,channels=2}:http{mux=ffmpeg{mux=flv},dst=:8888/}";
        

        //options = ":sout=#transcode{vcodec=h264,vb=800,fps=30,scale=0.25,acodec=mpga}:rtp{sdp=rtsp://:8554/} :sout-keep :sout-mux-caching=10";

//:sout=#transcode{vcodec=h264,scale=Auto} :sout-keep
        //:sout=#transcode{vcodec=h264,vb=128,fps=30,scale=Auto}:display :sout-keep
        //:v4l2-standard=NTSC :input-slave=alsa://hw:0,0 :v4l2-dev=/dev/video0 :v4l2-vbidev= :v4l2-chroma= :v4l2-input=0 :v4l2-audio-input=-1 :v4l2-width=0 :v4l2-height=0 :v4l2-aspect-ratio=4\:3 :v4l2-fps=20 :v4l2-radio-dev=/dev/radio0 :v4l2-tuner-frequency=-1 :v4l2-tuner-audio-mode=3 :no-v4l2-controls-reset :v4l2-brightness=-1 :v4l2-brightness-auto=-1 :v4l2-contrast=-1 :v4l2-saturation=-1 :v4l2-hue=-1 :v4l2-hue-auto=-1 :v4l2-white-balance-temperature=-1 :v4l2-auto-white-balance=-1 :v4l2-red-balance=-1 :v4l2-blue-balance=-1 :v4l2-gamma=-1 :v4l2-autogain=-1 :v4l2-gain=-1 :v4l2-sharpness=-1 :v4l2-chroma-gain=-1 :v4l2-chroma-gain-auto=-1 :v4l2-power-line-frequency=-1 :v4l2-backlight-compensation=-1 :v4l2-band-stop-filter=-1 :no-v4l2-hflip :no-v4l2-vflip :v4l2-rotate=-1 :v4l2-color-killer=-1 :v4l2-color-effect=-1 :v4l2-audio-volume=-1 :v4l2-audio-balance=-1 :no-v4l2-audio-mute :v4l2-audio-bass=-1 :v4l2-audio-treble=-1 :no-v4l2-audio-loudness :v4l2-set-ctrls= :live-caching=300
        //options = formatRtspStream("127.0.0.1", 5555, "demo");
        
        mediaPlayerFactory = new MediaPlayerFactory();
        mediaPlayer = mediaPlayerFactory.newHeadlessMediaPlayer();
        
        mediaPlayer.playMedia(media, options , ":no-sout-rtp-sap", ":no-sout-standard-sap", ":sout-all", ":sout-keep");
       
        isStream = true;
        
        // Don't exit
    //    Thread.currentThread().join();
 //return "rtsp://localhost:8554";
        return "http://localhost:8888";
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
