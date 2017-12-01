package environement;



public class Grass implements Decor {

    private GroundType groundType;

    public Grass(GroundType type){
        this.groundType = type;
    }

    @Override
    public DecorType getType() {
        return DecorType.GRASS;
    }

    @Override
    public String toString() {
        return "__";
    }

    public GroundType getGroundType() {
        return groundType;
    }

    @Override
    public boolean isTraversable() {
        return true;
    }
}
