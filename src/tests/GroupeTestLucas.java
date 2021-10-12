package tests;

import code.*;
import exceptions.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.Random;

import static org.junit.Assert.*;

public class GroupeTestLucas {

    private Groupe gr;
    private Etudiant i1, i2, i3;

    @Before
    public void init() {
        Formation f = new Formation("444");
        try {
            f.ajouterMatiere("CPOA", 4.0);
            f.ajouterMatiere("ExprComm", 2.0);
            f.ajouterMatiere("Math", 0.5);
            f.ajouterMatiere("SystemReseau", 1.0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.gr = new Groupe(f);
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

    @Test
    public void ajouterEtudiant_CasOK() throws AjoutSuppressionEtudiantImpossibleException {
        assertEquals("test 1: nombre d'etudiant de la formation est de 3", 3, this.gr.getEtudiants().size());
        this.gr.ajouterEtudiant(new Etudiant(new Identite("0003", "Long", "Martin"), this.gr.getFormation()));
        assertEquals("test 2: nombre d'etudiant de la formation est de 4", 4, this.gr.getEtudiants().size());
    }
    @Test (expected = AjoutSuppressionEtudiantImpossibleException.class)
    public void ajouterEtudiant_CasFormationNonIdentique() throws AjoutSuppressionEtudiantImpossibleException {
        this.gr.ajouterEtudiant(new Etudiant(new Identite("FR0003", "Long", "Martin"), new Formation("0999")));
    }
    @Test (expected = AjoutSuppressionEtudiantImpossibleException.class)
    public void ajouterEtudiant_CasEtudiantNull() throws AjoutSuppressionEtudiantImpossibleException {
        this.gr.ajouterEtudiant(null);
    }
    @Test (expected = AjoutSuppressionEtudiantImpossibleException.class)
    public void ajouterEtudiant_CasEtudiantDejaDedans() throws AjoutSuppressionEtudiantImpossibleException {
        this.gr.ajouterEtudiant(i1);
    }

    @Test
    public void supprimerEtudiant() throws AjoutSuppressionEtudiantImpossibleException {
        assertEquals("test 1: nombre d'etudiant de la formation est de 3", 3, this.gr.getEtudiants().size());
        this.gr.supprimerEtudiant(i1);
        assertEquals("test 2: nombre d'etudiant de la formation est de 2", 2, this.gr.getEtudiants().size());
    }
    @Test (expected = AjoutSuppressionEtudiantImpossibleException.class)
    public void supprimerEtudiant_CasEtudiantNull() throws AjoutSuppressionEtudiantImpossibleException {
        this.gr.supprimerEtudiant(null);
    }
    @Test (expected = AjoutSuppressionEtudiantImpossibleException.class)
    public void supprimerEtudiant_CasEtudiantPasDansLeGroupe() throws AjoutSuppressionEtudiantImpossibleException {
        this.gr.supprimerEtudiant(new Etudiant(new Identite("9999", "Grolet", "Michel"), new Formation("444")));
    }

    @Test
    public void calculerMoyenneGroupe_OK() throws MatiereInexistanteException, ValeurImpossibleException, ListeNotesVideException {
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

    @Test
    public void calculerMoyenneGroupe_ExceptionMatiereInexistante(){

    }

    @Test
    public void calculerMoyenneGroupe_ExceptionListeNoteVideException() {

    }

    @Test
    public void calculerMoyenneGenerale_OK() {
    }

    @Test
    public void calculerMoyenneGenerale_ExceptionListeNotesVide() {

    }

    @Test
    public void triParMerite() {

    }

    @Test
    public void triAlpha() {
        Etudiant[] e = {i1, i2, i3};
        int i=0;
        for (Etudiant val : gr.getEtudiants()) {
            assertEquals(val, e[i]);
            i++;
        }
        gr.triAlpha();
        Etudiant[] ee = {i3, i2, i1};
        i=0;
        for (Etudiant val : gr.getEtudiants()) {
            //assertEquals(val, ee[i]);
            System.out.println(val.getIdentite().getNom());
            i++;
        }
    }
}