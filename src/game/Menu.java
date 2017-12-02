package game;

import factory.TileFactory;

import java.awt.*;

public class Menu {

    private String[] menu = {"Jouer", "Quitter"};
    private int indiceEnCours;
    private Game game;

    public Menu(Game game){
        this.indiceEnCours = 0;
        this.game = game;
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


        for (int i = 0; i < menu.length; i++){
            if (i == indiceEnCours){
                crayon.setColor(Color.BLUE);
            } else {
                crayon.setColor(Color.WHITE);
            }
            crayon.drawString(menu[i], Painter.WIN_WIDTH/2 - 50, Painter.WIN_HEIGHT/2 + i * 50);
        }


    }


    public void action(){
        switch (menu[indiceEnCours]){
            case "Jouer":
                game.getGameState().setRunning();
                break;
            case "Quitter":
                System.exit(0);
                break;
        }

    }

}
