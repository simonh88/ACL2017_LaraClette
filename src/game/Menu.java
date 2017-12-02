package game;

import factory.TileFactory;

import java.awt.*;

public class Menu {

    private String[] menu = {"Jouer","Commandes", "Quitter"};
    private int indiceEnCours;
    private Game game;
    private boolean menuCmd;

    public Menu(Game game){
        this.indiceEnCours = 0;
        this.game = game;
        this.menuCmd = false;
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





    public void printCommandes(Graphics2D crayon){
        String[] cmd = {"LEFT: q | RIGHT: d | UP: z | DOWN: s", "ACTION: e", "Retour avec ECHAP"};
        crayon.drawImage(TileFactory.instance().getMenuTile(), 0, 0, null);
        crayon.setFont(new Font(" Serif ",Font.PLAIN,20));

        for (int i = 0; i < cmd.length; i++){
            crayon.drawString(cmd[i], Painter.WIN_WIDTH/3 - 70, Painter.WIN_HEIGHT/2 + i * 30);
        }
    }


    public void action(){
        switch (menu[indiceEnCours]){
            case "Jouer":
                game.getGameState().setRunning();
                break;
            case "Commandes":
                menuCmd = true;
                break;
            case "Quitter":
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
}
