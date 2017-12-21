package factory;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundFactory {

    private static SoundFactory instance;

    private Clip background;
    private Clip attackSword;
    private Clip attackMonster;
    private Clip keyDropped;
    private Clip chestOpenning;
    private Clip monsterDeath;
    private Clip heroBossSameRoom;
    private Clip victory;
    private Clip loss;

    private SoundFactory(){

        // background
        try {
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(new File("res/sound/Background_Retro.wav"));
            background = AudioSystem.getClip();
            background.open(audioInputStream);
            background.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception ex){
            System.out.println("Impossible de charger res/sound/Background_Retro.wav");
            System.exit(-1);
        }

        // victory
        try {
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(new File("res/sound/Victory.wav"));
            victory = AudioSystem.getClip();
            victory.open(audioInputStream);
        } catch (Exception ex){
            System.out.println("Impossible de charger res/sound/Victory.wav");
            System.exit(-1);
        }


        // loss
        try {
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(new File("res/sound/Lose.wav"));
            loss = AudioSystem.getClip();
            loss.open(audioInputStream);
        } catch (Exception ex){
            System.out.println("Impossible de charger res/sound/Lose.wav");
            System.exit(-1);
        }

        // attack hero
        try {
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(new File("res/sound/Sword_Swing.wav"));
            attackSword = AudioSystem.getClip();
            attackSword.open(audioInputStream);
        } catch (Exception ex){
            System.out.println("Impossible de charger res/sound/Sword_Swing.wav");
            System.exit(-1);
        }


        // attack monstres
        try {
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(new File("res/sound/Zombie_attack.wav"));
            attackMonster = AudioSystem.getClip();
            attackMonster.open(audioInputStream);

        } catch (Exception ex){
            System.out.println("Impossible de charger res/sound/Zombie_attack.wav");
            System.exit(-1);
        }


        // key dropped
        try {
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(new File("res/sound/Key_Dropped.wav"));
            keyDropped = AudioSystem.getClip();
            keyDropped.open(audioInputStream);

        } catch (Exception ex){
            System.out.println("Impossible de charger res/sound/Key_Dropped.wav");
            System.exit(-1);
        }

        // chest openning
        try {
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(new File("res/sound/Chest_Openning.wav"));
            chestOpenning = AudioSystem.getClip();
            chestOpenning.open(audioInputStream);

        } catch (Exception ex){
            System.out.println("Impossible de charger res/sound/Chest_Openning.wav");
            System.exit(-1);
        }

        // monster death
        try {
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(new File("res/sound/Monster_Death.wav"));
            monsterDeath = AudioSystem.getClip();
            monsterDeath.open(audioInputStream);

        } catch (Exception ex){
            System.out.println("Impossible de charger res/sound/Monster_Death.wav");
            System.exit(-1);
        }

        // hero boss same room
        try {
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(new File("res/sound/HeroBossSameRoom.wav"));
            heroBossSameRoom = AudioSystem.getClip();
            heroBossSameRoom.open(audioInputStream);

        } catch (Exception ex){
            System.out.println("Impossible de charger res/sound/HeroBossSameRoom.wav");
            System.exit(-1);
        }


    }

    public static SoundFactory instance() {
        if (instance == null) {
            instance = new SoundFactory();
        }

        return instance;
    }


    public void playAttackSword(){
        attackSword.setFramePosition(0);
        attackSword.start();
    }

    public void playAttackMonster(){
        attackMonster.setFramePosition(0);
        attackMonster.start();
    }

    public void playKeyDropped(){
        keyDropped.setFramePosition(0);
        keyDropped.start();
    }

    public void playChestOpenning(){
        chestOpenning.setFramePosition(0);
        chestOpenning.start();
    }

    public void playMonsterDeath(){
        monsterDeath.setFramePosition(0);
        monsterDeath.start();
    }

    public void playHeroBossSameRoom(){
        heroBossSameRoom.start();
    }

    public void playBackground(){
        background.start();
    }

    public void playVictory(){
        victory.start();
    }

    public void playLose(){
        loss.start();
    }

    public void stopBackground(){
        background.stop();
    }
}
