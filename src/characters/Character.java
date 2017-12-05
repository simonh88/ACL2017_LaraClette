package characters;


public class Character {

    private int hp;
    private int posX;
    private int posY;
    private int currentRoom;
    private boolean onAttack;
    private String lastMove;
    private int id;


    public Character(int x, int y, int currentRoom){
        this.id = 0;
        this.hp = 10;
        this.posX = x;
        this.posY = y;
        this.currentRoom = currentRoom;
        this.onAttack = false;
        this.lastMove = "S";
    }

    public Character(int x, int y, int currentRoom, int hp, int id){
        this.id = id;
        this.hp = hp;
        this.posX = x;
        this.posY = y;
        this.currentRoom = currentRoom;
        this.onAttack = false;
        this.lastMove = "S";
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
        return this.hp > 0;
    }

    public boolean isOnAttack() { return this.onAttack; }

    public void setOnAttack(boolean onAttack) { this.onAttack = onAttack; }

    public void setHP(int hp) {
        this.hp = hp;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public String getLastMove() {
        return lastMove;
    }

    public void setLastMove(String lastMove) {
        this.lastMove = lastMove;
    }
}
