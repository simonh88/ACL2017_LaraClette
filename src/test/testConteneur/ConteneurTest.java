package test.testConteneur;

import org.junit.jupiter.api.Test;

class ConteneurTest {

    @Test
    void ajouter() throws ErreurConteneur {
        // Right
        Conteneur conteneur1 = new ConteneurImpl(1);
        conteneur1.ajouter(new Object());

        // Right
        conteneur1 = new ConteneurImpl(2);
        conteneur1.ajouter(new Object());
        conteneur1.ajouter(new Object());
    }

    @Test
    void retirer() throws ErreurConteneur {
        Conteneur c = new ConteneurImpl(4) ;

        Object o = new Object();
        c.ajouter(o);
        c.ajouter(new Object());
        c.retirer(o);
        c.ajouter(new Object());
        c.ajouter(new Object());
    }
}