package game;

import characters.Character;
import characters.Hero;
import characters.Monster;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by simon on 19/10/17.
 */
public class GameState {
    private Hero hero;
    private List<Monster> lMonsters;

    public GameState(){
        lMonsters = new ArrayList<>();
        hero = new Hero(0,0);
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


}
