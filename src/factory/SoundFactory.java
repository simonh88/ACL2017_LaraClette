package factory;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

public class SoundFactory {

    public static SoundFactory instance;

    private Clip background;

    private SoundFactory(){

    }



    public static SoundFactory instance() {
        if (instance == null) {
            instance = new SoundFactory();
        }

        return instance;
    }

    public void playSound(String fichier){
        // fonctionne qu'avec .wav
        try {
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(new File(fichier));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }catch (Exception ex){
            System.out.println("Impossible de charger " + fichier);
            System.exit(-1);
        }
    }


    public void playBackground(){
        try {
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(new File("res/sound/Background_Retro.wav"));
            background = AudioSystem.getClip();
            background.open(audioInputStream);
            background.loop(Clip.LOOP_CONTINUOUSLY);
            background.start();
        }catch (Exception ex){
            System.out.println(ex);
            System.out.println("Impossible de charger res/sound/Background_Retro.wav");
            System.exit(-1);
        }
    }

    public void stopBackground(){
        background.stop();
    }
}
