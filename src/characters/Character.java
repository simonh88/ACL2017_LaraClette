package characters;


public class Character {

    private int hp;
    private int posX;
    private int posY;
    private int currentRoom;
    private boolean alive;

    public Character(int x, int y){
        this.hp = 10;
        this.posX = x;
        this.posY = y;
        this.currentRoom = 0;
        this.alive = true;
    }

    public int getCurrentRoom(){
        return currentRoom;
    }

    public void setCurrentRoom(int currentRoom){
        this.currentRoom = currentRoom;
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

    public boolean isAlive() {
        return this.alive;
    }

    public void setHP(int hp) {
        this.hp = hp;

        if(this.hp <= 0 ) this.alive = false;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

}
