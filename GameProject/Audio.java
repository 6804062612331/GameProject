
package GameProject;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.*;

public class Audio{
    private URL url = getClass().getResource("/audio/BGMusic.wav");
    private static AudioInputStream audioStream;
    private static Clip clip;
    public static boolean Mute=true;
    Audio() throws UnsupportedAudioFileException, LineUnavailableException, IOException{
        audioStream = AudioSystem.getAudioInputStream(url);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.loop(-1);
    }

    public Clip getClip() {
        return clip;
    }
    void Mute(){
        FloatControl control =(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue(-80);
    }
    void UnMute(){
        FloatControl control =(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue(-5);
    }
}
