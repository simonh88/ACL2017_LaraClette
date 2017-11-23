package factory;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundFactory {

    public static SoundFactory instance;

    private Clip swordSwing;

    private SoundFactory(){
        try{
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(new File("res/sound/Sword_Swing.mp3"));
            Clip swordSwing = AudioSystem.getClip();
            swordSwing.open(audioInputStream);
            //clip.start();
        }
        catch(Exception ex)
        {
            System.out.println("Impossible de charger res/sound/Sword_Swing.mp3");
            System.exit(-1);
        }
    }


    public void playSwordSwing(){
        this.swordSwing.start();
    }

    public static SoundFactory instance() {
        if (instance == null) {
            instance = new SoundFactory();
        }

        return instance;
    }
}
