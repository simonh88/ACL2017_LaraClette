package game;

import factory.TileFactory;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Menu {

    private String[] menu = {"Play","Commands", "Scores", "Easy", "Medium", "Hard", "Exit"};
    private int indiceEnCours;
    private Game game;
    private boolean menuCmd;
    private boolean menuScore;
    private String menuDifficulty = "Easy";

    private float alpha = 1f;
    private float diff = -0.02f;

    public Menu(Game game){
        this.indiceEnCours = 0;
        this.game = game;
        this.menuCmd = false;
        this.menuScore = false;
    }

    public void setIndiceVersBas(){
        if (indiceEnCours < menu.length - 1){
            this.indiceEnCours++;
        }
    }


    public void setIndiceVersHaut(){
        if (indiceEnCours > 0){
            this.indiceEnCours--;
        }
    }


    public void printMenu(Graphics2D crayon){
        crayon.drawImage(TileFactory.instance().getMenuTile(), 0, 0, null);
        crayon.setFont(new Font(" Serif ",Font.PLAIN,25));

        // save le composite de base
        Composite composite = crayon.getComposite();

        // update l'opacite
        alpha += 4 * diff;
        if (alpha < 0) {
            diff *= -1;
            alpha = diff;
        } else if (alpha > 1f) {
            diff *= -1;
            alpha = 1f + diff;
        }


        for (int i = 0; i < menu.length; i++){

            if (i == indiceEnCours){
                Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
                crayon.setComposite(c);
                crayon.setColor(new Color(102, 51, 0));

            } else {
                // restore le composite
                crayon.setComposite(composite);
                crayon.setColor(Color.WHITE);
            }
            // si on est dans indice taille menu - 4 (exit + avant 3 lvl difficultees)
            if ( (i < menu.length - 4)  ) {
                crayon.setFont(new Font(" Serif ", Font.PLAIN, 25)); // restore font
                crayon.drawString(menu[i], Painter.WIN_WIDTH / 2 - 50, Painter.WIN_HEIGHT / 2 + 10 + i * 35);
            }else if ((i == menu.length - 1)) { // print exit
                crayon.setFont(new Font(" Serif ", Font.PLAIN, 25)); // restore font
                crayon.drawString(menu[i], Painter.WIN_WIDTH / 2 - 50, Painter.WIN_HEIGHT / 2 + i * 28);
            } else {
                crayon.setFont(new Font(" Serif ", Font.PLAIN, 18)); // modify font

                if (i == 4) { // print medium
                    crayon.drawString(menu[i], Painter.WIN_WIDTH / 2 - 245 + i * 55, Painter.WIN_HEIGHT / 2 + 3 * 40);
                } else { // print easy hard
                    crayon.drawString(menu[i], Painter.WIN_WIDTH / 2 - 240 + i * 58, Painter.WIN_HEIGHT / 2 + 3 * 40);
                }

            }



            if ( menuDifficulty == menu[i]){
                crayon.setColor(Color.BLACK);
                if (i == 4) { // print medium
                    crayon.drawString(menu[i], Painter.WIN_WIDTH / 2 - 245 + i * 55, Painter.WIN_HEIGHT / 2 + 3 * 40);
                } else {
                    crayon.drawString(menu[i], Painter.WIN_WIDTH / 2 - 240 + i * 58, Painter.WIN_HEIGHT / 2 + 3 * 40);
                }
            }

        }



    }





    public void printCommandes(Graphics2D crayon){
        //String[] cmd = {"LEFT: q | RIGHT: d | UP: z | DOWN: s", "ACTION: e | ATTACK: spacebar", "Return with ESC"};
        String[] cmd = {"LEFT: q",  "RIGHT: d", "UP: z", "DOWN: s", "ACTION: e", "ATTACK: spacebar", "RETURN : esc","PAUSE: p"};
        crayon.drawImage(TileFactory.instance().getMenuTile(), 0, 0, null);
        crayon.setFont(new Font(" Serif ",Font.BOLD,16));
        crayon.setColor(new Color(102, 51, 0));

        for (int i = 0; i < cmd.length; i++){
            crayon.drawString(cmd[i], Painter.WIN_WIDTH/3 + 40, Painter.WIN_HEIGHT/2 + i * 20);
        }
    }



    public void printScores(Graphics2D crayon){

        System.out.println("hello");






    }


    public void action(){
        switch (menu[indiceEnCours]){
            case "Play":
                game.getGameState().setRunning();
                game.setDifficulty(menuDifficulty);
                break;
            case "Commands":
                menuCmd = true;
                break;
            case "Scores":
                menuScore = true;
                break;
            case "Easy":
                menuDifficulty = "Easy";
                break;
            case "Medium":
                menuDifficulty = "Medium";
                break;
            case "Hard":
                menuDifficulty = "Hard";
                break;
            case "Exit":
                System.exit(0);
                break;
        }

    }

    public boolean isMenuCmd(){
        return menuCmd;
    }

    public void setMenuCmd(boolean menuCmd) {
        this.menuCmd = menuCmd;
    }

    public boolean isMenuScore() {
        return menuScore;
    }

    public void setMenuScore(boolean menuScore) {
        this.menuScore = menuScore;
    }

    public String getMenuDifficulty() {
        return menuDifficulty;
    }

    public void setMenuDifficulty(String menuDifficulty) {
        this.menuDifficulty = menuDifficulty;
    }
}
