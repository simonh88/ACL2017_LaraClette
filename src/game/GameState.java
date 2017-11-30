package game;

import characters.Character;
import factory.SoundFactory;

import java.util.ArrayList;
import java.util.List;


public class GameState {
    private Character hero;
    private List<Character> lMonsters;

    private static enum State {
        Menu,
        Running,
        Victory,
        Loss
    };

    public State state;

    public GameState(){
        lMonsters = new ArrayList<>();
        hero = new Character(4,4, 0);
        state = State.Menu;
        lMonsters.add(new Character(2,2, 1));
        lMonsters.add(new Character(5,5, 0));
    }


    public Character getHero(){
        return hero;
    }

    public void setHero(int x, int y, int currentRoom){
        hero = new Character(x,y, currentRoom);
    }

    public Character getMonster(int i){
        if(i >= 0 && i < lMonsters.size()){
            //Innaccessible
            return lMonsters.get(i);
        }
        //ERROR
        return null;
    }

    public void killMonster(Character monster){
        lMonsters.remove(monster);
        System.out.println("SIZE: " + lMonsters.size());
    }

    public State getState(){
        return state;
    }

    public int sizeMonsters(){
        return lMonsters.size();
    }

    public boolean isRunning(){
        return this.state == State.Running;
    }

    public boolean isVictory(){
        return this.state == State.Victory;
    }

    public boolean isLoss(){
        return this.state == State.Loss;
    }

    public boolean isMenu(){
        return this.state == State.Menu;
    }

    public void setVictory(){
        if (!isVictory()){
            SoundFactory.instance().playSound("res/sound/Victory.wav");
            this.state = State.Victory;
        }
    }

    public void setLoss(){
        if (!isLoss() ){
            SoundFactory.instance().playSound("res/sound/Lose.wav");
            this.state = State.Loss;
        }
    }

    public void setMenu(){ this.state = State.Menu;}


    public void setRunning(){
        this.state = State.Running;

    }

    public List<Character> monsters() {
        return lMonsters;
    }
}
