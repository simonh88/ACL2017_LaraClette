package test.testConteneur;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.fail;

class ConteneurTest {

    // ===== Ajouter ===== //

    @Test
    void rightAjouterOne() throws ErreurConteneur {
        // Right
        Conteneur conteneur1 = new ConteneurImpl(1);
        conteneur1.ajouter(new Object());
    }

    @Test
    void rightAjouterTwo() throws ErreurConteneur {
        // Right
        Conteneur conteneur1 = new ConteneurImpl(2);
        conteneur1.ajouter(new Object());
        conteneur1.ajouter(new Object());
    }

    @Test
    void boundaryAjouterNull() throws ErreurConteneur {
        Conteneur conteneur3 = new ConteneurImpl(1);
        conteneur3.ajouter(null);
    }

    @Test
    void boundaryAjouterSameObject() throws ErreurConteneur {
        Conteneur conteneur4 = new ConteneurImpl(2);
        Object o1 = new Object();
        conteneur4.ajouter(o1);
        try {
            conteneur4.ajouter(o1);
            fail("L'objet a été ajouté deux fois");
        } catch (ErreurConteneur e) {
            // Cette exception est normale
        }

    }

    @Test
    void boundaryAjouterSameString() throws ErreurConteneur {
        Conteneur conteneur4 = new ConteneurImpl(2);
        Object o1 = new String();
        o1 = "coucou";
        conteneur4.ajouter(o1);
        try {
            conteneur4.ajouter(o1);
            fail("La chaine de char a été ajouté deux fois");
        } catch (ErreurConteneur e) {}

    }

    /**
     * Ajoute deux éléments avec une capacité de 1
     *
     * @throws ErreurConteneur
     */
    @Test
    void boundaryAjouterTwoFailCap() throws ErreurConteneur {
        Conteneur conteneur4 = new ConteneurImpl(1);
        conteneur4.ajouter(new Object());
        try {
            conteneur4.ajouter(new Object());
            fail("On a ajouté deux éléments avec une capacité à 1");
        } catch (ErreurConteneur e) {
            // Cette exception est normale
        }

    }


    // ===== Retirer ===== //

    @Test
    void rightRetirerOne() throws ErreurConteneur {
        Conteneur c = new ConteneurImpl(4);

        Object o = new Object();
        c.ajouter(o);
        c.ajouter(new Object());
        c.retirer(o);
        c.ajouter(new Object());
        c.ajouter(new Object());
    }

    @Test
    void rightRetirerNonPresent() throws ErreurConteneur {
        Conteneur c;
        c = new ConteneurImpl(4);
        Object o = new Object();
        try {
            c.retirer(o);
            fail("On a retiré un objet non présent");
        } catch (ErreurConteneur ec) {
            // Cette exception est normale
        }
    }

    @Test
    void rightRetirerDeuxFois() throws ErreurConteneur {
        Conteneur c;
        Object o;

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
            // Cette exception est normale
        }
    }

    // ===== Redimensioner ===== //

    @Test
    void rightRedimensionerMoinsUn() throws ErreurConteneur {
        Conteneur c1 = new ConteneurImpl(1);
        try {
            c1.redimensionner(-1);
            fail("Redimensionement 1 -> -1");
        } catch (Exception e) {
            // Cette exception est normale
        }
    }

    @Test
    void rightRedimensionerDouble() throws ErreurConteneur {
        Conteneur c2 = new ConteneurImpl(1);
        c2.redimensionner(2);
    }

    @Test
    void rightRedimensionerZero() throws ErreurConteneur {
        Conteneur c3 = new ConteneurImpl(1);
        try {
            c3.redimensionner(0);
            fail("Redimensionement à 1 -> 0");
        } catch (Exception e) {
            // Cette exception est normale
        }
    }




}