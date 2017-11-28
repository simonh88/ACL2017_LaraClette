package game;

import characters.Character;

import java.util.ArrayList;
import java.util.List;


public class GameState {
    private Character hero;
    private List<Character> lMonsters;

    private static enum State {
        Running,
        Victory,
        Loss
    };

    public State state;

    public GameState(){
        lMonsters = new ArrayList<>();
        hero = new Character(4,4);
        state = State.Running;
        lMonsters.add(new Character(2,2));
        lMonsters.add(new Character(5,5));
    }


    public Character getHero(){
        return hero;
    }

    public void setHero(int x, int y){
        hero = new Character(x,y);
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

    public void setVictory(){
        this.state = State.Victory;
    }


    public void setRunning(){
        this.state = State.Running;

    }

    public List<Character> monsters() {
        return lMonsters;
    }
}
