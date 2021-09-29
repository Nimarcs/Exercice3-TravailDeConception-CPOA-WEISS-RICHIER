// PACKAGE
package tests;

// IMPORTS
import code.Formation;
import exceptions.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * TEST FORMATION
 */
public class FormationTest {

    private Formation form1, form2;

    @Before
    public void setUp() throws Exception {
        this.form1 = new Formation("Informatique");
        this.form2 = new Formation("Science");
        this.form2.ajouterMatiere("Mathematique", 4.0);
    }

    @Test
    public void ajouterMatiere_CasOK() throws MatiereExisteDejaException, ValeurImpossibleException, MatiereInexistanteException {
        Set<String> listeMatieres = this.form1.domaineMatieres();
        assertEquals( "Test OK1: form1 ne doit avoir aucune matiere", listeMatieres.size(), 0);
        this.form1.ajouterMatiere("CPOA", 5.0);
        assertEquals("Test 0K2: form1 doit avoir une matiere", listeMatieres.size(), 1);
        listeMatieres = this.form1.domaineMatieres();
        Iterator<String> it = listeMatieres.iterator();
        String matiere = it.next();
        double coeff = this.form1.getCoefficient("CPOA");
        assertEquals("Test OK3: la matiere de form1 doit etre CPOA", "CPOA", matiere);
        assertEquals("Test OK4: La coefficient de la seule matiere de form1 est de 5.0", 5.0, coeff, 5.0);
    }

    @Test (expected = MatiereExisteDejaException.class)
    public void ajouterMatiere_CasMatiereInexistante() throws MatiereExisteDejaException, ValeurImpossibleException {
        this.form1.ajouterMatiere("CPOA", 4.0);
        try {
            this.form1.ajouterMatiere("CPOA", 4.0);
        } catch (Exception e) {
            Set<String> listeMatieres = this.form1.domaineMatieres();
            assertEquals("Test1: la formation doit avoir une matiere", 1, listeMatieres.size());
        }
        this.form1.ajouterMatiere("CPOA", 4.0);
    }

    @Test (expected = ValeurImpossibleException.class)
    public void ajouterMatiere_CasValeurException() throws MatiereExisteDejaException, ValeurImpossibleException {
        this.form1.ajouterMatiere("CPOA", -4.0);
    }

    @Test
    public void supprimerMatiere() {
        Set<String> listeMatieres = this.form2.domaineMatieres();
        assertEquals("Test1: la formation doit avoir une matiere", 1, listeMatieres.size());
        this.form2.supprimerMatiere("CPOA");
        listeMatieres = this.form2.domaineMatieres();
        assertEquals("Test2: la formation doit etre vide", 0, listeMatieres.size());
    }

    @Test
    public void changerCoefficient() {
    }

    @Test
    public void domaineMatieres() {
    }

    @Test
    public void getCoefficient() {
    }

    @Test
    public void getIdFormation() {
    }
}