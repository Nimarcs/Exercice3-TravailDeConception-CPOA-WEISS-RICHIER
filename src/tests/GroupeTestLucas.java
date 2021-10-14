// PACKAGE
package tests;

// IMPORTATIONS
import  code.*;
import exceptions.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Random;
import static org.junit.Assert.*;

/**
 * CLASSE TESTE GROUPE, by Lucas
 */
public class GroupeTestLucas {

    // ATTRIBUTS
    private Groupe gr;
    private Etudiant i1, i2, i3;

    /**
     * Methode Before qui s'execute avant chaque test afin de preparer
     */
    @Before
    public void init() {
        // On cree la formation
        Formation f = new Formation("444");
        // On ajoute des matiere a la formation
        try {
            f.ajouterMatiere("CPOA", 4.0);
            f.ajouterMatiere("ExprComm", 2.0);
            f.ajouterMatiere("Math", 0.5);
            f.ajouterMatiere("SystemeReseau", 1.0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // On cree un groupe
        this.gr = new Groupe(f);
        // On aoute au groupe de nouveaux etudiants
        try {
            this.i1 = new Etudiant(new Identite("FR0001", "Weiss", "Lucas"), f);
            this.i2 = new Etudiant(new Identite("FR0002", "Richier", "Marcus"), f);
            this.i3 = new Etudiant(new Identite("FR1000", "Marc", "Antoine"), f);
            this.gr.ajouterEtudiant(i1);
            this.gr.ajouterEtudiant(i2);
            this.gr.ajouterEtudiant(i3);
        } catch (AjoutSuppressionEtudiantImpossibleException e) {
            e.printStackTrace();
        }

    }

    /**
     * Test ajouterEtudiant, situation normale où tout fonctionne
     * @throws AjoutSuppressionEtudiantImpossibleException : déclenchée ssi l'ajout est impossible, ce qui n'est pas le cas ici
     */
    @Test
    public void ajouterEtudiant_CasOK() throws AjoutSuppressionEtudiantImpossibleException {
        assertEquals("test 1: nombre d'etudiant de la formation est de 3", 3, this.gr.getEtudiants().size());
        this.gr.ajouterEtudiant(new Etudiant(new Identite("0003", "Long", "Martin"), this.gr.getFormation()));
        assertEquals("test 2: nombre d'etudiant de la formation est de 4", 4, this.gr.getEtudiants().size());
    }

    /**
     * Test ajouterEtudiant, situation d'ajout impossible, l'exception AjoutSuppressionEtudiantImpossible doit être remontée
     * @throws AjoutSuppressionEtudiantImpossibleException exception déclenchée
     */
    @Test (expected = AjoutSuppressionEtudiantImpossibleException.class)
    public void ajouterEtudiant_CasFormationNonIdentique() throws AjoutSuppressionEtudiantImpossibleException {
        this.gr.ajouterEtudiant(new Etudiant(new Identite("FR0003", "Long", "Martin"), new Formation("0999")));
    }

    /**
     * Test ajouterEtudiant, situation ou on insere un objet null, l'exception AjoutSuppressionEtudiantImpossible doit etre remontée
     * @throws AjoutSuppressionEtudiantImpossibleException exception à declenchée
     */
    @Test (expected = AjoutSuppressionEtudiantImpossibleException.class)
    public void ajouterEtudiant_CasEtudiantNull() throws AjoutSuppressionEtudiantImpossibleException {
        this.gr.ajouterEtudiant(null);
    }

    /**
     * Test ajouterEtudiant, situation ou l'etudiant est deja dans le groupe, l'exception AjoutSuppressionEtudiantImpossible doit etre remontée
     * @throws AjoutSuppressionEtudiantImpossibleException exception à declenchée
     */
    @Test (expected = AjoutSuppressionEtudiantImpossibleException.class)
    public void ajouterEtudiant_CasEtudiantDejaDedans() throws AjoutSuppressionEtudiantImpossibleException {
        this.gr.ajouterEtudiant(i1);
    }

    /**
     * Test supprimerEtudiant, situation normale où tout fonctionne
     * @throws AjoutSuppressionEtudiantImpossibleException : déclenchée ssi la suppression est impossible, ce qui n'est pas le cas ici
     */
    @Test
    public void supprimerEtudiant() throws AjoutSuppressionEtudiantImpossibleException {
        assertEquals("test 1: nombre d'etudiant de la formation est de 3", 3, this.gr.getEtudiants().size());
        this.gr.supprimerEtudiant(i1);
        assertEquals("test 2: nombre d'etudiant de la formation est de 2", 2, this.gr.getEtudiants().size());
    }

    /**
     * Test supprimerEtudiant, situation ou le parametre est null, l'exception doit etre remontée
     * @throws AjoutSuppressionEtudiantImpossibleException exception à remontée
     */
    @Test (expected = AjoutSuppressionEtudiantImpossibleException.class)
    public void supprimerEtudiant_CasEtudiantNull() throws AjoutSuppressionEtudiantImpossibleException {
        this.gr.supprimerEtudiant(null);
    }

    /**
     * Test supprimerEtudiant, situation ou la suppression est impossible car l'etudiant en parametre n'est pas dans le groupe
     * @throws AjoutSuppressionEtudiantImpossibleException exception à remontée
     */
    @Test (expected = AjoutSuppressionEtudiantImpossibleException.class)
    public void supprimerEtudiant_CasEtudiantPasDansLeGroupe() throws AjoutSuppressionEtudiantImpossibleException {
        this.gr.supprimerEtudiant(new Etudiant(new Identite("9999", "Grolet", "Michel"), new Formation("444")));
    }

    /**
     * Test calculerMoyenneGroupe, situation normale où tout fonctionne
     * @throws MatiereInexistanteException : exception remontée seulement ssi la matiere n'existe pas, ici, ce n'est pas le cas
     * @throws ValeurImpossibleException : exception remontée seulement ssi les notes ajouter ne sont pas comprises en 0 et 20, pas le cas ici car insertion sûre
     * @throws ListeNotesVideException : exception remontée ssi la liste des notes est vide, pas le cas ici vus qu'on insere dans une note minimum dans chaque matiere
     */
    @Test
    public void calculerMoyenneGroupe_OK() throws ValeurImpossibleException, ListeNotesVideException, MatiereInexistanteException {
        Random r = new Random();
        for (String s : gr.getFormation().domaineMatieres()) {
            double moyenne = 0;
            for (int i=0; i<5; i++) {
                double res = r.nextDouble()*20;
                i1.ajouterNote(s, r.nextDouble()*20);
                i2.ajouterNote(s, r.nextDouble()*20);
                i3.ajouterNote(s, r.nextDouble()*20);
            }
            moyenne=i1.calculerMoyenne(s)+i2.calculerMoyenne(s)+i3.calculerMoyenne(s);
            moyenne=moyenne/3;
            assertEquals (moyenne, gr.calculerMoyenneGroupe(s), 0.01);
        }
    }

    /**
     * Test calculerMoyenneGroupe, situation ou la matiere entrée en paramètre n'est pas une matiere de la formation de l'etudiant, remonte une exception
     * @throws MatiereInexistanteException : exception remontée car la matiere "Français" n'existe pas dans la formation du groupe
     * @throws ListeNotesVideException : exception non remontée ici car avant de chercher des notes, il faut tester la matiere
     */
    @Test (expected = MatiereInexistanteException.class)
    public void calculerMoyenneGroupe_ExceptionMatiereInexistante() throws MatiereInexistanteException, ListeNotesVideException {
        double res = this.i1.calculerMoyenne("Français");
    }

    /**
     * Test calculerMoyenneGroupe, situation où la liste des de notes de l'eleve pour une matiere en question est vide
     * @throws MatiereInexistanteException : exception non remontée ici car la matiere insérée existe bien dans la formation du groupe
     * @throws ListeNotesVideException : exception remontée ici car la liste de notes de l'etudiant est vide pour toutes les matieres
     */
    @Test (expected = ListeNotesVideException.class)
    public void calculerMoyenneGroupe_ExceptionListeNoteVideException() throws MatiereInexistanteException, ListeNotesVideException {
        double res = this.i1.calculerMoyenne("CPOA");
    }

    /**
     * Test calculerMoyenneGroupe, situation où un etudiant a des notes mais pas dans toutes les matieres
     * @throws MatiereInexistanteException : exception remontée ssi l'une des matiere insérée n'existe pas dans la formation du groupe
     * @throws ListeNotesVideException : exception remontée ssi la liste de notes est complètement pour toutes la matiere du groupe pour un etudiant
     */
    @Test
    public void calculerMoyenneGroupe_CasNotesMaisPasDansToutesLesMatieres() throws MatiereInexistanteException, ListeNotesVideException {
        try {
            i1.ajouterNote("CPOA", 12.0);
            i2.ajouterNote("CPOA", 16.0);
            i3.ajouterNote("Math", 8.5);
            i1.ajouterNote("ExprComm", 17.5);
            i2.ajouterNote("ExprComm", 14.5);
            i1.ajouterNote("SystemeReseau", 8.4);
        } catch (MatiereInexistanteException e) {
            e.printStackTrace();
        } catch (ValeurImpossibleException e) {
            e.printStackTrace();
        }
        assertEquals("test1: la moyenne du groupe en CPOA doit etre de 14.0", 14.0, gr.calculerMoyenneGroupe("CPOA"), 0.1);
        assertEquals("test2: la moyenne du groupe en ExprComm doit etre de 8.5", 8.5, gr.calculerMoyenneGroupe("Math"), 0.1);
        assertEquals("test3: la moyenne du groupe en SystemeReseau doit etre de 8.4", 8.4, gr.calculerMoyenneGroupe("SystemeReseau"), 0.1);
        assertEquals("test4: la moyenne du groupe en ExprComm doit etre de 16.0", 16.0, gr.calculerMoyenneGroupe("ExprComm"), 0.1);

    }

    /**
     * Test calculerMoyenneGenerale, situation normale où tout fonctionne
     * @throws ListeNotesVideException : exception remontée ssi la liste de notes d'un étudiant est intégralement vide
     */
    @Test
    public void calculerMoyenneGenerale_OK() throws ListeNotesVideException {
        //ajouts des notes
        try {
            i1.ajouterNote("CPOA", 12.0);
            i1.ajouterNote("CPOA", 16.0);
            i1.ajouterNote("Math", 8.5);
            i1.ajouterNote("ExprComm", 17.5);
            i1.ajouterNote("ExprComm", 14.5);
            i1.ajouterNote("SystemeReseau", 15.0);
        } catch (MatiereInexistanteException e) {
            e.printStackTrace();
        } catch (ValeurImpossibleException e) {
            e.printStackTrace();
        }
        double res = i1.calculerMoyenneGenerale();
        assertEquals("test1: la moyenne générale de l'etudiant 1 doit etre a 14.3", 14.3, res, 0.1);
    }

    /**
     * Test calculerMoyenneGenerale, situation ou la liste de notes d'un étudiant est intégralement vide
     * @throws ListeNotesVideException : exception remontée car la liste de notes de l'etudiant i1 est totalement vide pour chaque matiere
     */
    @Test (expected = ListeNotesVideException.class)
    public void calculerMoyenneGenerale_ExceptionListeNotesVide() throws ListeNotesVideException {
        i1.calculerMoyenneGenerale();
    }

    /**
     * Test triParMerite, verifie que les etudiants sont bien triés par leur moyenneGenerale par ordre decroissant
     */
    @Test
    public void triParMerite_CasOK () {
        for (String s : gr.getFormation().domaineMatieres()) {
            for (int i=0; i<5; i++) {
                try {
                    i2.ajouterNote(s,10.0);
                    i1.ajouterNote(s,12.0);
                    i3.ajouterNote(s,14.0);
                } catch (MatiereInexistanteException e) {
                    e.printStackTrace();
                } catch (ValeurImpossibleException e) {
                    e.printStackTrace();
                }
            }
        }
        // On lance la méthode de tri
        gr.triParMerite();
        int i=0;
        Etudiant[] tabEtudiant = {i3, i1, i2};
        for (Etudiant val : gr.getEtudiants()) {
            assertEquals(val, tabEtudiant[i]);
            i++;
        }

    }

    /**
     * Test triAlpha, vérifie que les etudiants du groupe sont triés par ordre alphabétique par ordre croissant
     */
    @Test
    public void triAlpha_CasOK() {
        Etudiant i4 = new Etudiant(new Identite("FR999", "Richier", "Celia"), this.gr.getFormation());
        Etudiant i5 = new Etudiant(new Identite("ALL001", "Zerk", "Mehdi"), this.gr.getFormation());
        try {
            this.gr.ajouterEtudiant(i4);
            this.gr.ajouterEtudiant(i5);
        } catch (AjoutSuppressionEtudiantImpossibleException e) {
            e.printStackTrace();
        }
        int i=0;
        // On lance la méthode de tri
        gr.triAlpha();
        Etudiant[] tabEtudiant = {i3, i4, i2, i1, i5};
        for (Etudiant val : gr.getEtudiants()) {
            assertEquals(val, tabEtudiant[i]);
            i++;
        }
    }
}