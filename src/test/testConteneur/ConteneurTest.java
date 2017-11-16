package test.testConteneur;

class ConteneurTest {

    public ConteneurTest() throws ErreurConteneur {
        // Right
        Conteneur conteneur1 = new ConteneurImpl(1);
        conteneur1.ajouter(new Object());

        // Right
        Conteneur conteneur1 = new ConteneurImpl(2);
        conteneur1.ajouter(new Object());
        conteneur1.ajouter(new Object());

        // Boundary


    }

    public static void main(String[] args) throws ErreurConteneur {
        new ConteneurTest();
    }
}