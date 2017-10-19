package characters;

/**
 * Created by simon on 19/10/17.
 */
public abstract class Character {

    private int hp;
    private int posX;
    private int posY;


    public int getHP() {
        return this.hp;
    }

    public int getPosX(){
        return this.posX;
    }

    public int getPosY(){
        return this.posY;
    }

    public abstract String toString();

}
