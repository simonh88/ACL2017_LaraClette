package characters;


public class Character {

    private int hp;
    private int posX;
    private int posY;

    public Character(int x, int y){
        this.hp = 10;
        this.posX = x;
        this.posY = y;
    }

    public int getHP() {
        return this.hp;
    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public void setHP(int hp) {
        this.hp = hp;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

}
