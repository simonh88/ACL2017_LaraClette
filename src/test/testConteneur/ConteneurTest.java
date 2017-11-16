package test.testConteneur;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

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



        c = new ConteneurImpl(4);
        o = new Object();
        try {
            c.retirer(o);
            fail("On a retiré un objet non présent");
        } catch (ErreurConteneur ec) {

        }


        c = new ConteneurImpl(5);
        o = new Object();
        String p = "str";
        c.ajouter(o);
        c.ajouter(p);
        c.retirer(p);
        try {
            c.retirer(p);
            fail("L'objet P a été retiré deux fois");
        } catch (ErreurConteneur ec) {

        }



    }
}