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

    @Override
    public int getHP() {
        return this.hp;
    }

    @Override
    public int getPosX() {
        return this.posX;
    }

    @Override
    public int getPosY() {
        return this.posY;
    }

    public String toString() {
        return "H";
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
