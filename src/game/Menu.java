package game;

import factory.TileFactory;

import java.awt.*;

public class Menu {

    private String[] menu = {"Play","Commands", "Exit"};
    private int indiceEnCours;
    private Game game;
    private boolean menuCmd;

    private float alpha = 1f;
    private float diff = -0.02f;

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
            crayon.drawString(menu[i], Painter.WIN_WIDTH/2 - 40, Painter.WIN_HEIGHT/2 + 10+ i * 60);
        }

    }





    public void printCommandes(Graphics2D crayon){
        //String[] cmd = {"LEFT: q | RIGHT: d | UP: z | DOWN: s", "ACTION: e | ATTACK: spacebar", "Return with ESC"};
        String[] cmd = {"LEFT: q",  "RIGHT: d", "UP: z", "DOWN: s", "ACTION: e", "ATTACK: spacebar", "RETURN : esc"};
        crayon.drawImage(TileFactory.instance().getMenuTile(), 0, 0, null);
        crayon.setFont(new Font(" Serif ",Font.BOLD,16));
        crayon.setColor(new Color(102, 51, 0));

        for (int i = 0; i < cmd.length; i++){
            crayon.drawString(cmd[i], Painter.WIN_WIDTH/3 + 40, Painter.WIN_HEIGHT/2 + i * 20);
        }
    }


    public void action(){
        switch (menu[indiceEnCours]){
            case "Play":
                game.getGameState().setRunning();
                break;
            case "Commands":
                menuCmd = true;
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
}
