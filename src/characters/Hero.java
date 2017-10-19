package characters;

/**
 * Created by simon on 19/10/17.
 */
public class Hero extends Character {

    private int hp;
    private int posX;
    private int posY;

    public Hero(){
        this.hp = 10;
        this.posX = 3;
        this.posY = 3;
    }

    public Hero(int x, int y){
        this.hp = 10;
        this.posX = x;
        this.posY = y;
    }

    public String toString() {
        return "H";
    }
}
