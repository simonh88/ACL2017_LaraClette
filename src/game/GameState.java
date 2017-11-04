package game;

import characters.Character;
import characters.Hero;
import characters.Monster;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class GameState {
    private Hero hero;
    private List<Monster> lMonsters;

    private static enum State {
        Running,
        Victory,
        Loss
    };

    public State state;

    public GameState(){
        lMonsters = new ArrayList<>();
        hero = new Hero(4,4);
        state = State.Running;
        lMonsters.add(new Monster(2,2));
    }


    public Hero getHero(){
        return hero;
    }

    public Monster getMonster(int i){
        if(i >= 0 && i < lMonsters.size()){//Innaccessible
            return lMonsters.get(i);
        }
        //ERROR
        return null;
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

    public void setVictory(){
        this.state = State.Victory;
    }

    public List<Monster> monsters() {
        return lMonsters;
    }
}
