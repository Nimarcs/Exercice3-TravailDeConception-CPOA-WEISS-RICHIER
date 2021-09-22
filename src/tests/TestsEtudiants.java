package tests;

import static org.junit.Assert.*;

import code.Etudiant;
import code.Formation;
import code.Identite;
import exceptions.MatiereInexistanteException;
import org.junit.Test;
import org.junit.Before;

public class TestsEtudiants {
    
    private Etudiant etu1, etu2;

    @Before
    public void setUp() throws Exception {
        Formation formationPatate = new Formation("PATATE");
        formationPatate.ajouterMatiere("cueille", 1.0);
        formationPatate.ajouterMatiere("epluchage", 5.0);
        formationPatate.ajouterMatiere("cuisson", 2.0);
        etu1 = new Etudiant(new Identite("SK1", "Satou", "Kazuma"), formationPatate);
        etu2 = new Etudiant(new Identite("GA1", "God", "Aqua"), formationPatate);
    }

    /**
     * methode qui teste le cas normal d'un ajout de note
     * @throws MatiereInexistanteException renvoye si la matiere n'existe pas
     */
    @Test
    public void ajouterNote_casNormal() throws MatiereInexistanteException {
        //preparation
        //methode a tester
        etu1.ajouterNote("cueille", 10.0);
        //assertion
        assertEquals("l'etudiant doit avoir une note de 10 dans la matiere cueille", 10.0, etu1.getResultat("cueille").get(0), 5.0 );

        //on essaye avec une deuxieme note
        //methode a tester
        etu1.ajouterNote("cueille", 12.5);
        //assertion
        assertEquals("l'etudiant doit avoir une note de 12.5 dans la matiere cueille", 12.5, etu1.getResultat("cueille").get(1), 5.0 );

        //on essaye avec une troisieme note dans une matiere differente
        //methode a tester
        etu1.ajouterNote("epluchage", 3.0);
        //assertion
        assertEquals("l'etudiant doit avoir une note de 10 dans la matiere cueille", 3.0, etu1.getResultat("epluchage").get(0), 5.0 );

    }

    /**
     * le test verifie que la methode renvoie bien une exception si la matiere n'existe pas
     * @throws MatiereInexistanteException exception attendue
     */
    @Test (expected = MatiereInexistanteException.class)
    public void ajouterNote_casMatiereInexistante() throws MatiereInexistanteException {
        //preparation
        //methode a tester
        etu1.ajouterNote("informatique", 20.0);
        //assertion
    }
    
    @Test
    public void ajouterNote_casNegatif() {
        //preparation
        //methode a tester
        //assertion
    }

    @Test
    public void ajouterNote_casGrandeValeur() {
        //preparation
        //methode a tester
        //assertion
    }

    
    
    @Test
    public void calculerMoyenne() {
    }

    @Test
    public void calculerMoyenneGenerale() {
    }

    @Test
    public void getIdentite() {
    }
}