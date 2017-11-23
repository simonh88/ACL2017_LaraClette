package factory;

public class SoundFactory {

    public static SoundFactory instance;


    private SoundFactory(){

    }

    public static SoundFactory instance() {
        if (instance == null) {
            instance = new SoundFactory();
        }

        return instance;
    }
}
