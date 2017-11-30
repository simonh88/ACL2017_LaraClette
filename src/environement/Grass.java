package environement;



public class Grass implements Decor {

    private GroundType groundType;

    public Grass(GroundType type){
        this.groundType = type;
    }

    @Override
    public int getType() {
        return Decor.GRASS;
    }

    @Override
    public String toString() {
        return "__";
    }

    public GroundType getGroundType() {
        return groundType;
    }
}
