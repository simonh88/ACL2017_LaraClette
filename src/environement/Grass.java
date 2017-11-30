package environement;



public class Grass implements Decor {

    private GrassType grassType;

    public Grass(GrassType type){
        this.grassType = type;
    }

    @Override
    public int getType() {
        return Decor.GRASS;
    }

    @Override
    public String toString() {
        return "__";
    }

    public GrassType getGrassType() {
        return grassType;
    }
}
