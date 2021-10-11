package tests;

import static org.junit.Assert.*;

import code.Etudiant;
import code.Formation;
import code.Groupe;
import code.Identite;
import exceptions.AjoutSuppressionEtudiantImpossibleException;
import exceptions.MatiereExisteDejaException;
import exceptions.ValeurImpossibleException;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * classe pour tester les methodes de la classe Groupe
 */
public class TestsGroupeMarcus {

    private Groupe gr1;
    private Etudiant etu1, etu2;

    /**
     *
     */
    @Before
    public void setUp() throws MatiereExisteDejaException, ValeurImpossibleException {
        Formation formationPatate = new Formation("PATATE");
        formationPatate.ajouterMatiere("cueille", 1.0);
        formationPatate.ajouterMatiere("epluchage", 5.0);
        formationPatate.ajouterMatiere("cuisson", 2.0);
        gr1 = new Groupe(formationPatate);
        etu1 = new Etudiant(new Identite("SK1", "Satou", "Kazuma"), formationPatate);
        etu2 = new Etudiant(new Identite("GA1", "God", "Aqua"), formationPatate);
    }

    /**
     * test pour verifier si on ajoute convenablement les etudiants
     * @throws AjoutSuppressionEtudiantImpossibleException exception non attendue
     */
    @Test
    public void ajouterEtudiant_casNormal() throws AjoutSuppressionEtudiantImpossibleException {
        //mise en place
        Set<Etudiant> exp = new HashSet<>();
        exp.add(etu1); exp.add(etu2);
        //methode a tester
        gr1.ajouterEtudiant(etu1);
        gr1.ajouterEtudiant(etu2);
        //assertion
        assertEquals(exp.size(), gr1.getEtudiants().size());
        assertTrue(exp.containsAll(gr1.getEtudiants()));
    }

    /**
     * test pour verifier si on ajoute un etudiant de valeur null
     * n'est pas censee fonctionner
     * @throws AjoutSuppressionEtudiantImpossibleException exception attendue
     */
    @Test (expected = AjoutSuppressionEtudiantImpossibleException.class)
    public void ajouterEtudiant_casNull() throws AjoutSuppressionEtudiantImpossibleException {
        //mise en place
        //methode a tester
        gr1.ajouterEtudiant(null);
        //assertion
    }

    /**
     * test pour verifier si on ajoute un etudiant en double
     * n'est pas censee fonctionner
     * @throws AjoutSuppressionEtudiantImpossibleException exception attendue
     */
    @Test (expected = AjoutSuppressionEtudiantImpossibleException.class)
    public void ajouterEtudiant_casDoublons() throws AjoutSuppressionEtudiantImpossibleException {
        //mise en place
        //methode a tester
        gr1.ajouterEtudiant(etu1);
        gr1.ajouterEtudiant(etu1);
        //assertion
    }

    /**
     * test pour verifier si on ajoute un etudiant en double avec un objet Etudiant different qui a le meme nip
     * n'est pas censee fonctionner
     * @throws AjoutSuppressionEtudiantImpossibleException exception attendue
     */
    @Test (expected = AjoutSuppressionEtudiantImpossibleException.class)
    public void ajouterEtudiant_casDoublonsObjetDifferent() throws AjoutSuppressionEtudiantImpossibleException {
        //mise en place
        Etudiant etu3 = new Etudiant(new Identite(etu1.getIdentite().getNip(), "Xavier", "Padupont"), etu2.getFormation());
        //methode a tester
        gr1.ajouterEtudiant(etu1);
        gr1.ajouterEtudiant(etu3);
        //assertion
    }

    /**
     * test pour verifier si on ajoute un etudiant avec tout equivalent mais une nip differente
     * est censee fonctionner
     * @throws AjoutSuppressionEtudiantImpossibleException exception attendue
     */
    @Test (expected = AjoutSuppressionEtudiantImpossibleException.class)
    public void ajouterEtudiant_casNipDiff() throws AjoutSuppressionEtudiantImpossibleException {
        //mise en place
        Etudiant etu3 = new Etudiant(new Identite(etu1.getIdentite().getNip(), "Xavier", "Padupont"), etu2.getFormation());
        //methode a tester
        gr1.ajouterEtudiant(etu1);
        gr1.ajouterEtudiant(etu3);
        //assertion
    }



}
