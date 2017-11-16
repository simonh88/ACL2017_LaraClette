package test.testConteneur;

import com.sun.org.apache.xpath.internal.operations.String;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

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

        // Boundary
        Conteneur conteneur3 = new ConteneurImpl(1);
        conteneur3.ajouter(null);

        // Boundary
        try {
            Conteneur conteneur4 = new ConteneurImpl(2);
            Object o1 = new Object();
            conteneur4.ajouter(o1);
            conteneur4.ajouter(o1);
        }catch (ErreurConteneur erreurConteneur){
            System.out.println(erreurConteneur);
        }

        // Boundary
        try {
            Conteneur conteneur4 = new ConteneurImpl(2);
            Object o1 = new String();
            o1 = "coucou";
            conteneur4.ajouter(o1);
            conteneur4.ajouter(o1);
        }catch (ErreurConteneur erreurConteneur){
            System.out.println(erreurConteneur);
        }

        // Boundary
        try {
            Conteneur conteneur4 = new ConteneurImpl(2);

            conteneur4.ajouter(new Object());
            conteneur4.ajouter(new Object());
        }catch (ErreurConteneur erreurConteneur){
            System.out.println(erreurConteneur);
        }

        // Boundary
        try {
            Conteneur conteneur4 = new ConteneurImpl(1);

            conteneur4.ajouter(new Object());
            conteneur4.ajouter(new Object());
        }catch (Exception erreurConteneur){
            System.out.println(erreurConteneur);
        }

        // Crosscheck
        try {
            Conteneur conteneur4 = new ConteneurImpl(4);

            conteneur4.ajouter(new Object());
            conteneur4.ajouter(new Object());
            conteneur4.ajouter(new Object());
            conteneur4.ajouter(new Object());
        }catch (Exception erreurConteneur){
            System.out.println(erreurConteneur);
        }

        try {
            HashSet<Object> conteneur4 = new HashSet<>();

            conteneur4.add(new Object());
            conteneur4.add(new Object());
            conteneur4.add(new Object());
            conteneur4.add(new Object());
        }catch (Exception erreurConteneur){
            System.out.println(erreurConteneur);
        }


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