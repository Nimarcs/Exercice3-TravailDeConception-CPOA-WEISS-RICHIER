package tests;

import static org.junit.Assert.*;

import code.Etudiant;
import code.Formation;
import code.Identite;
import exceptions.ListeNotesVideException;
import exceptions.MatiereExisteDejaException;
import exceptions.MatiereInexistanteException;
import exceptions.ValeurImpossibleException;

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
     * @throws ValeurImpossibleException renvoie si la valeur est impossible
     */
    @Test
    public void ajouterNote_casNormal() throws MatiereInexistanteException, ValeurImpossibleException {
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
     * @throws ValeurImpossibleException exception non attendue
     */
    @Test (expected = MatiereInexistanteException.class)
    public void ajouterNote_casMatiereInexistante() throws MatiereInexistanteException, ValeurImpossibleException {
        //preparation
        //methode a tester
        etu1.ajouterNote("informatique", 20.0);
        //assertion
    }

    /**
     * le test verifie que la methode renvoie bien une exception si la note est negative
     * @throws MatiereInexistanteException exception non attendue
     * @throws ValeurImpossibleException exception attendue
     */
    @Test (expected = ValeurImpossibleException.class)
    public void ajouterNote_casNegatif() throws MatiereInexistanteException, ValeurImpossibleException {
        //preparation
        //methode a tester
        etu1.ajouterNote("epluchage", -1.5);
        //assertion
    }

    /**
     * le test verifie que la methode renvoie bien une exception si la note est superieure a 20
     * @throws MatiereInexistanteException exception non attendue
     * @throws ValeurImpossibleException exception attendue
     */
    @Test (expected = ValeurImpossibleException.class)
    public void ajouterNote_casGrandeValeur() throws MatiereInexistanteException, ValeurImpossibleException {
        //preparation
        //methode a tester
        etu1.ajouterNote("epluchage", 20.2);
        //assertion
    }


    /**
     * test qui verifie le bon fonctionnement de calculer moyenne dans un cas normal
     * @throws MatiereInexistanteException exception non attendue
     * @throws ValeurImpossibleException exception non attendue
     * @throws ListeNotesVideException exception non attendue
     */
    @Test
    public void calculerMoyenne_casNormal() throws MatiereInexistanteException, ValeurImpossibleException, ListeNotesVideException {
        //preparation
        etu1.ajouterNote("cueille", 3.0);
        etu1.ajouterNote("cueille", 7.0);
        etu1.ajouterNote("cueille", 20.0);
        double exp = 10.0;
        //methode a tester
        double res = etu1.calculerMoyenne("cueille");
        //assertion
        assertEquals("l'etudiant doit avoir la bonne moyenne", exp, res, 0.01);
    }

    /**
     * test qui verifie que la methode calculerMoyenne renvoie une exception si on essaie de calculer la moyenne dans une matiere inexistante
     * @throws MatiereInexistanteException exception attendue
     * @throws ListeNotesVideException exception non attendue
     */
    @Test (expected = MatiereInexistanteException.class)
    public void calculerMoyenne_casMatiereInexistante() throws MatiereInexistanteException, ListeNotesVideException {
        //preparation
        //methode a tester
        etu1.calculerMoyenne("lancer");
        //assertion
    }

    /**
     * test qui permet de verifier si calculer moyenne renvoie bien une exception si il essaie de calculer la moyenne d'une matiere sans note
     * @throws MatiereInexistanteException exception non attendue
     * @throws ListeNotesVideException exception attendue
     */
    @Test (expected = ListeNotesVideException.class)
    public void calculerMoyenne_casPasDeNote() throws MatiereInexistanteException, ListeNotesVideException {
        //preparation
        //methode a tester
        etu1.calculerMoyenne("epluchage");
        //assertion
    }

    /**
     * test qui permet de tester le cas ou il y a une infinite de chiffre après la virgule
     * @throws MatiereInexistanteException exception non attendue
     * @throws ValeurImpossibleException exception non attendue
     * @throws ListeNotesVideException exception non attendue
     */
    @Test
    public void calculerMoyenne_casMoyenneAvecVirguleInfinie() throws MatiereInexistanteException, ValeurImpossibleException, ListeNotesVideException {
        //preparation
        etu1.ajouterNote("cueille", 2.0);
        etu1.ajouterNote("cueille", 2.0);
        etu1.ajouterNote("cueille", 1.0);
        double exp = 1.66;
        //methode a tester
        double res = etu1.calculerMoyenne("cueille");
        //assertion
        assertEquals("l'etudiant doit avoir la bonne moyenne", exp, res, 0.01);
    }

    /**
     * test qui permet de verifier que le calcul de la moyenne generale fonctionne dans un cas normal
     * @throws MatiereInexistanteException exception non attendue
     * @throws ValeurImpossibleException exception non attendue
     * @throws ListeNotesVideException exception non attendue
     */
    @Test
    public void calculerMoyenneGenerale_casNormal() throws MatiereInexistanteException, ValeurImpossibleException, ListeNotesVideException {
        //preparation
        etu1.ajouterNote("cueille", 5.0);
        etu1.ajouterNote("cueille", 15.0);
        etu1.ajouterNote("epluchage", 0.0);
        etu1.ajouterNote("cuisson", 10.0);
        etu1.ajouterNote("cuisson", 20.0);
        double exp = 5.0;
        //methode a tester
        double res = etu1.calculerMoyenneGenerale();
        //assertion
        assertEquals("l'etudiant doit avoir la bonne moyenne", exp, res, 0.01);
    }

    /**
     * test pour le cas ou il n'y a pas de note dans une matiere
     * @throws MatiereInexistanteException exception non attendue
     * @throws ValeurImpossibleException exception non attendue
     */
    @Test
    public void calculerMoyenneGenerale_casPasNoteDansMatiere() throws MatiereInexistanteException, ValeurImpossibleException, ListeNotesVideException {
        //preparation
        etu1.ajouterNote("cueille", 20.0);
        //pas de note dans l'epluchage
        etu1.ajouterNote("cuisson", 0.0);
        etu1.ajouterNote("cuisson", 10.0);
        double exp = 10.0;
        //methode a tester
        double res = etu1.calculerMoyenneGenerale();
        //assertion
        assertEquals("l'etudiant doit avoir la bonne moyenne", exp, res, 0.01);
    }

    /**
     * test qui verifie que si il n'y a pas de note on renvoie une exception
     * @throws ListeNotesVideException exception attendue
     */
    @Test (expected = ListeNotesVideException.class)
    public void calculerMoyenneGenerale_casPasNote() throws ListeNotesVideException{
        //preparation
        //methode a tester
        etu1.calculerMoyenneGenerale();
        //assertion
    }

    /**
     * test qui permet de verifier que calculer moyenne generale traite correctement le cas ou il y a une infinite de chiffre apres la virgule
     * @throws MatiereInexistanteException exception non attendue
     * @throws ValeurImpossibleException exception non attendue
     * @throws ListeNotesVideException exception non attendue
     */
    @Test
    public void calculerMoyenneGenerale_casMoyenneAvecVirguleInfinie() throws MatiereInexistanteException, ValeurImpossibleException, ListeNotesVideException {
        //preparation
        etu1.ajouterNote("cueille", 1.75);
        etu1.ajouterNote("epluchage", 0.5);
        //pas de note
        double exp = 0.71;
        //methode a tester
        double res = etu1.calculerMoyenneGenerale();
        //assertion
        assertEquals("l'etudiant doit avoir la bonne moyenne", exp, res, 0.01);
    }

    /**
     * test de equals dans les cas normaux
     */
    @Test
    public void testEquals_casNormal() {
        //mise en place
        Formation formationMath = new Formation("Math");
        Etudiant etu1Bis = new Etudiant(new Identite("SK1", "Souta", "Kamazu"), formationMath);
        Etudiant etu1Diff = new Etudiant(new Identite("SK2", "Souta", "Kamazu"), formationMath);
        //assertion
        assertNotEquals(etu1, etu2);
        assertEquals(etu1, etu1);
        assertEquals(etu1Bis, etu1);
        assertNotEquals(etu1Bis, etu1Diff);
    }

    /**
     * test du cas ou l'une des valeurs est null pour equals
     */
    @Test
    public void testEquals_casNull() {
        assertFalse(etu1.equals(null));
        assertNotEquals(null, etu2);
    }

}