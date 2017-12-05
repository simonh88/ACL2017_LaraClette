package environement;

import utils.Position;

public class GroundLoot {

    private final Position position;
    private final Loot loot;

    public GroundLoot(int posX, int posY, Loot loot) {
        this.position = new Position(posX, posY);
        this.loot = loot;
    }

    public Position getPosition() {
        return position;
    }

    public Loot getType() {
        return loot;
    }
}
