package tests;

import code.*;
import exceptions.AjoutSuppressionEtudiantImpossibleException;
import exceptions.MatiereExisteDejaException;
import exceptions.ValeurImpossibleException;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

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
            this.i3 = new Etudiant(new Identite("1000", "Marc", "Antoine"), f);
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
    public void calculerMoyenneGroupe() {
    }

    @Test
    public void calculerMoyenneGenerale() {
    }

    @Test
    public void triParMerite() {
    }

    @Test
    public void triAlpha() {
    }
}