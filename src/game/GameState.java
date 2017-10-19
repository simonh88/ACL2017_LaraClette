package game;

import characters.Character;
import characters.Hero;
import characters.Monster;

import java.util.ArrayList;
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
        hero = new Hero(0,0);
        state = State.Running;
    }


    public Hero getHero(){
        return hero;
    }

    public Monster get(int i){
        if(i >= 0 && i < lMonsters.size()){//Innaccessible
            return lMonsters.get(i);
        }
        //ERROR
        return null;
    }

    public State getState(){
        return state;
    }

    public boolean isRunning(){
        return this.state == State.Running;
    }


}
