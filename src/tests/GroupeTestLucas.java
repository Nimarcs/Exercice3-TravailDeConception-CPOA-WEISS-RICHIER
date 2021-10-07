package tests;

import code.*;
import exceptions.AjoutSuppressionEtudiantImpossibleException;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class GroupeTestLucas {

    private Groupe gr;

    @Before
    public void init() {
        Formation f = new Formation("444");
        this.gr = new Groupe(f);
        try {
            this.gr.ajouterEtudiant(new Etudiant(new Identite("0001", "Weiss", "Lucas"), f));
            this.gr.ajouterEtudiant(new Etudiant(new Identite("0002", "Richier", "Marcus"), f));
        } catch (AjoutSuppressionEtudiantImpossibleException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ajouterEtudiant_CasOK() throws AjoutSuppressionEtudiantImpossibleException {
        assertEquals("test 1: nombre d'etudiant de la formation est de 2", 2, this.gr.getEtudiants().size());
        this.gr.ajouterEtudiant(new Etudiant(new Identite("0003", "Long", "Martin"), this.gr.getFormation()));
        assertEquals("test 2: nombre d'etudiant de la formation est de 3", 3, this.gr.getEtudiants().size());
    }
    @Test (expected = AjoutSuppressionEtudiantImpossibleException.class)
    public void ajouterEtudiant_CasException() throws AjoutSuppressionEtudiantImpossibleException {
        this.gr.ajouterEtudiant(new Etudiant(new Identite("0003", "Long", "Martin"), new Formation("0999")));
    }

    @Test
    public void supprimerEtudiant() throws AjoutSuppressionEtudiantImpossibleException {
        this.gr.ajouterEtudiant(new Etudiant(new Identite("0002", "Long", "Martin"), this.gr.getFormation()));
        Iterator i = this.gr.getEtudiants().iterator();
        while (i.hasNext()) {
            Etudiant e = (Etudiant)i.next();
            System.out.println(e.getIdentite().getNip());
        }

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