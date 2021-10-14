package tests;

import static org.junit.Assert.*;

import code.Etudiant;
import code.Formation;
import code.Groupe;
import code.Identite;
import exceptions.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * classe pour tester les methodes de la classe Groupe
 */
public class TestsGroupeMarcus {

    private Groupe gr1;
    private Etudiant etu1, etu2, etu3, etu4, etu5;

    /**
     * preparation pour les tests
     */
    @Before
    public void setUp() throws MatiereExisteDejaException, ValeurImpossibleException, MatiereInexistanteException {
        Formation formationPatate = new Formation("PATATE");
        formationPatate.ajouterMatiere("cueille", 1.0);
        formationPatate.ajouterMatiere("epluchage", 5.0);
        formationPatate.ajouterMatiere("cuisson", 2.0);
        gr1 = new Groupe(formationPatate);
        etu1 = new Etudiant(new Identite("SK1", "Satou", "Kazuma"), formationPatate);
        etu2 = new Etudiant(new Identite("GA1", "God", "Aqua"), formationPatate);
        etu3 = new Etudiant(new Identite("CM1", "Crimson", "Megumin"), formationPatate);
        etu3.ajouterNote("cueille", 3.);//2
        etu4 = new Etudiant(new Identite("LD1", "Lalatina", "Darkness"), formationPatate);
        etu4.ajouterNote("cueille", 4.);//3
        etu5 = new Etudiant(new Identite("CC1", "Crimson", "Chunchunmaru"), formationPatate);
        etu5.ajouterNote("cueille", 5.);//1
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
     * n'est pas censee fonctionner sans erreur renvoye
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
     * n'est pas censee fonctionner sans erreur renvoye
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
     * n'est pas censee fonctionner sans erreur renvoye
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
     * n'est censee fonctionner sans erreur renvoye
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

    /**
     * test pour verifier si on ajoute un etudiant different mais avec une formation différente du groupe
     * n'est censee fonctionner sans erreur renvoye
     * @throws AjoutSuppressionEtudiantImpossibleException exception attendue
     */
    @Test (expected = AjoutSuppressionEtudiantImpossibleException.class)
    public void ajouterEtudiant_casFormationDiff() throws AjoutSuppressionEtudiantImpossibleException {
        //mise en place
        Etudiant etu3 = new Etudiant(new Identite("MC1", "Megumin", "Crimson"), new Formation("Informatique"));
        //methode a tester
        gr1.ajouterEtudiant(etu3);
        //assertion
    }

    /**
     * test pour verifier la suppression d'etudiant dans un cas normal
     * est censee fonctionner sans erreur renvoye
     * @throws AjoutSuppressionEtudiantImpossibleException exception non attendue
     */
    @Test
    public void supprimerEtudiant_casNormal() throws AjoutSuppressionEtudiantImpossibleException {
        //mise en place
        gr1.ajouterEtudiant(etu1);
        gr1.ajouterEtudiant(etu2);
        //methode a tester
        gr1.supprimerEtudiant(etu1);
        //assertion
        assertTrue(gr1.getEtudiants().contains(etu2) && !gr1.getEtudiants().contains(etu1));
        //on verifie que le groupe contient l'etudiant 2 mais plus le 1
    }

    /**
     * test pour verifier qu'il est impossible de supprimer un etudiant pas dans le groupe
     * n'est pas censee fonctionner sans erreur renvoye
     * @throws AjoutSuppressionEtudiantImpossibleException exception attendue
     */
    @Test (expected = AjoutSuppressionEtudiantImpossibleException.class)
    public void supprimerEtudiant_casPasDedans() throws AjoutSuppressionEtudiantImpossibleException {
        //mise en place
        gr1.ajouterEtudiant(etu2);
        //methode a tester
        gr1.supprimerEtudiant(etu1);
        //assertion
    }

    /**
     * test pour verifier que le cas ou l'on fournit null est traite de maniere approprie
     * n'est pas censee fonctionner sans erreur renvoye
     * @throws AjoutSuppressionEtudiantImpossibleException exception attendue
     */
    @Test (expected = AjoutSuppressionEtudiantImpossibleException.class)
    public void supprimerEtudiant_casNull() throws AjoutSuppressionEtudiantImpossibleException {
        //mise en place
        gr1.ajouterEtudiant(etu2);
        //methode a tester
        gr1.supprimerEtudiant(null);
        //assertion
    }

    /**
     * test pour verifier que l'on calcule convenablement la moyenne du groupe dans une matiere donnee
     * est censee fonctionner sans erreur renvoye
     * @throws MatiereInexistanteException exception non attendue
     * @throws ListeNotesVideException exception non attendue
     * @throws ValeurImpossibleException exception non attendue
     */
    @Test
    public void calculerMoyenneGroupe_casNormal() throws MatiereInexistanteException, ListeNotesVideException, ValeurImpossibleException, AjoutSuppressionEtudiantImpossibleException {
        //preparation
        //note de l'etudiant 1
        etu1.ajouterNote("cueille", 5.0);
        etu1.ajouterNote("cueille", 15.0);
        etu1.ajouterNote("epluchage", 0.0);
        etu1.ajouterNote("cuisson", 10.0);
        etu1.ajouterNote("cuisson", 20.0);
        //note de l'etudiant 2 (plus simple, l'etudiant suffit a prouver que les bonnes methodes de calcul de moyenne ont ete appelle
        etu2.ajouterNote("cueille", 15.);
        etu2.ajouterNote("cuisson", 5.);
        double expCueille = 12.5;
        double expCuisson = 10.;

        //on ajoute les etudiants
        gr1.ajouterEtudiant(etu1);
        gr1.ajouterEtudiant(etu2);


        //methode a tester
        double resCueille = gr1.calculerMoyenneGroupe("cueille");
        double resCuisson = gr1.calculerMoyenneGroupe("cuisson");

        //assertion
        assertEquals("le groupe doit avoir la bonne moyenne", expCueille, resCueille, 0.01);
        assertEquals("le groupe doit avoir la bonne moyenne", expCuisson, resCuisson, 0.01);
    }

    /**
     * test pour verifier que la methode renvoie une erreur si on tente de calculer la moyenne du groupe sur une matiere inexistante
     * n'est pas censee fonctionner sans erreur renvoye
     * @throws MatiereInexistanteException exception attendue
     * @throws ListeNotesVideException exception non attendue
     * @throws ValeurImpossibleException exception non attendue
     */
    @Test (expected = MatiereInexistanteException.class)
    public void calculerMoyenneGroupe_casMatiereInexistante() throws MatiereInexistanteException, ListeNotesVideException, ValeurImpossibleException, AjoutSuppressionEtudiantImpossibleException {
        //preparation
        gr1.ajouterEtudiant(etu1);
        gr1.ajouterEtudiant(etu2);

        //methode a tester
        gr1.calculerMoyenneGroupe("CPOA");
        //assertion
    }

    /**
     * test pour verifier que la methode renvoie une erreur si on tente de calculer la moyenne du groupe sur une matiere sans note
     * n'est pas censee fonctionner sans erreur renvoye
     * @throws MatiereInexistanteException exception non attendue
     * @throws ListeNotesVideException exception attendue
     * @throws ValeurImpossibleException exception non attendue
     */
    @Test (expected = ListeNotesVideException.class)
    public void calculerMoyenneGroupe_casPasDeNote() throws MatiereInexistanteException, ListeNotesVideException, ValeurImpossibleException, AjoutSuppressionEtudiantImpossibleException {
        //preparation
        gr1.ajouterEtudiant(etu1);
        gr1.ajouterEtudiant(etu2);

        //methode a tester
        gr1.calculerMoyenneGroupe("cueille");
        //assertion
    }

    /**
     * test pour verifier que la methode renvoie le bon resultat quand tout les eleves n'ont pas ete note dans la matiere
     * est censee fonctionner sans erreur renvoye
     * @throws MatiereInexistanteException exception non attendue
     * @throws ListeNotesVideException exception non attendue
     * @throws ValeurImpossibleException exception non attendue
     */
    @Test
    public void calculerMoyenneGroupe_casPasTousNote() throws MatiereInexistanteException, ListeNotesVideException, ValeurImpossibleException, AjoutSuppressionEtudiantImpossibleException {
        //preparation
        //note de l'etudiant 1
        etu1.ajouterNote("cueille", 10.0);
        etu1.ajouterNote("cueille", 20.0);
        etu1.ajouterNote("epluchage", 0.0);
        etu2.ajouterNote("cuisson", 10.);
        double expCueille = 15.;
        double expCuisson = 10.;
        double expEpluchage = 0.;

        //on ajoute les etudiants
        gr1.ajouterEtudiant(etu1);
        gr1.ajouterEtudiant(etu2);

        //methode a tester
        double resCueille = gr1.calculerMoyenneGroupe("cueille");
        double resCuisson = gr1.calculerMoyenneGroupe("cuisson");
        double resEpluchage = gr1.calculerMoyenneGroupe("epluchage");

        //assertion
        assertEquals(expCueille, resCueille, 0.01);
        assertEquals(expCuisson, resCuisson, 0.01);
        assertEquals(expEpluchage, resEpluchage, 0.01);
    }

    /**
     * test pour verifier que l'on calcule convenablement la moyenne generale du groupe
     * est censee fonctionner sans erreur renvoye
     * @throws MatiereInexistanteException exception non attendue
     * @throws ListeNotesVideException exception non attendue
     * @throws ValeurImpossibleException exception non attendue
     */
    @Test
    public void calculerMoyenneGenerale_casNormal() throws MatiereInexistanteException, ListeNotesVideException, ValeurImpossibleException, AjoutSuppressionEtudiantImpossibleException {
        //preparation
        //note de l'etudiant 1
        etu1.ajouterNote("cueille", 5.0);
        etu1.ajouterNote("cueille", 15.0);
        etu1.ajouterNote("epluchage", 6.0);
        etu1.ajouterNote("cuisson", 10.0);
        etu1.ajouterNote("cuisson", 20.0);
        //note de l'etudiant 2 (plus simple, l'etudiant suffit a prouver que les bonnes methodes de calcul de moyenne ont ete appelle
        etu2.ajouterNote("cueille", 15.);
        etu1.ajouterNote("epluchage", 10.0);
        etu2.ajouterNote("cuisson", 5.);
        double exp = 9.06;

        //on ajoute les etudiants
        gr1.ajouterEtudiant(etu1);
        gr1.ajouterEtudiant(etu2);

        //methode a tester
        double res = gr1.calculerMoyenneGenerale();

        //assertion
        assertEquals("le groupe doit avoir la bonne moyenne", exp, res, 0.01);
    }


    /**
     * test pour verifier que la methode renvoie une erreur si on tente de calculer la moyenne du groupe sur une matiere sans note
     * n'est pas censee fonctionner sans erreur renvoye
     * @throws ListeNotesVideException exception attendue
     * @throws AjoutSuppressionEtudiantImpossibleException exception non attendue
     */
    @Test (expected = ListeNotesVideException.class)
    public void calculerMoyenneGenerale_casPasDeNote() throws ListeNotesVideException, AjoutSuppressionEtudiantImpossibleException {
        //preparation
        gr1.ajouterEtudiant(etu1);
        gr1.ajouterEtudiant(etu2);

        //methode a tester
        gr1.calculerMoyenneGenerale();
        //assertion
    }

    /**
     * test pour verifier que la methode renvoie le bon resultat quand tout les eleves n'ont pas ete note dans toutes les matieres
     * est censee fonctionner sans erreur renvoye
     * @throws MatiereInexistanteException exception non attendue
     * @throws ListeNotesVideException exception non attendue
     * @throws ValeurImpossibleException exception non attendue
     */
    @Test
    public void calculerMoyenneGenerale_casPasTousNote() throws MatiereInexistanteException, ListeNotesVideException, ValeurImpossibleException, AjoutSuppressionEtudiantImpossibleException {
        //preparation
        //note de l'etudiant 1
        etu1.ajouterNote("cueille", 10.0);
        etu1.ajouterNote("cueille", 20.0);
        etu1.ajouterNote("epluchage", 4.5);
        etu2.ajouterNote("epluchage", 5.5);
        etu2.ajouterNote("cuisson", 10.);
        double exp= 7.5;

        //on ajoute les etudiants
        gr1.ajouterEtudiant(etu1);
        gr1.ajouterEtudiant(etu2);

        //methode a tester
        double res = gr1.calculerMoyenneGenerale();

        //assertion
        assertEquals(exp, res, 0.01);
    }

    @Test
    public void triParMerite_casNormal() throws AjoutSuppressionEtudiantImpossibleException {
        //preparation
        gr1.ajouterEtudiant(etu3);
        gr1.ajouterEtudiant(etu4);
        gr1.ajouterEtudiant(etu5);
        String exp = "Identite{nip='CC1', nom='Crimson', prenom='Chunchunmaru'}Identite{nip='LD1', nom='Lalatina', prenom='Darkness'}Identite{nip='CM1', nom='Crimson', prenom='Megumin'}";
        String res = "";
        //methode a tester
        gr1.triParMerite();
        //assertion
        for (Etudiant e : gr1.getEtudiants()){
            res += e.toString();
        }
        assertEquals(exp, res);
    }

    @Test
    public void triParMerite_casDoublons() throws AjoutSuppressionEtudiantImpossibleException, MatiereInexistanteException, ValeurImpossibleException {
        //preparation
        etu1.ajouterNote("cueille", 3.);
        gr1.ajouterEtudiant(etu3);
        gr1.ajouterEtudiant(etu4);
        gr1.ajouterEtudiant(etu5);
        gr1.ajouterEtudiant(etu1);
        String exp = "Identite{nip='CC1', nom='Crimson', prenom='Chunchunmaru'}Identite{nip='LD1', nom='Lalatina', prenom='Darkness'}Identite{nip='CM1', nom='Crimson', prenom='Megumin'}Identite{nip='SK1', nom='Satou', prenom='Kazuma'}";        String res = "";
        //methode a tester
        gr1.triParMerite();
        //assertion
        for (Etudiant e : gr1.getEtudiants()){
            res += e.toString();
        }
        assertEquals(exp, res);
    }

    @Test
    public void triParMerite_casUnElement() throws AjoutSuppressionEtudiantImpossibleException {
        //preparation
        gr1.ajouterEtudiant(etu3);
        String exp = "Identite{nip='CM1', nom='Crimson', prenom='Megumin'}";
        String res = "";
        //methode a tester
        gr1.triParMerite();
        //assertion
        for (Etudiant e : gr1.getEtudiants()) {
            res += e.toString();
        }
        assertEquals(exp, res);
    }

    @Test
    public void triAlpha_casNormal() throws AjoutSuppressionEtudiantImpossibleException {
        //preparation
        gr1.ajouterEtudiant(etu3);
        gr1.ajouterEtudiant(etu4);
        gr1.ajouterEtudiant(etu5);
        String exp = "Identite{nip='CC1', nom='Crimson', prenom='Chunchunmaru'}Identite{nip='CM1', nom='Crimson', prenom='Megumin'}Identite{nip='LD1', nom='Lalatina', prenom='Darkness'}";
        String res = "";
        //methode a tester
        gr1.triAlpha();
        //assertion
        for (Etudiant e : gr1.getEtudiants()){
            res += e.toString();
        }
        assertEquals(exp, res);
    }

    @Test
    public void triAlpha_casDuplicats() throws AjoutSuppressionEtudiantImpossibleException {
        //preparation
        Etudiant etu1Bis = new Etudiant(new Identite("SK2", "Satou", "Kazuma"), etu2.getFormation());
        gr1.ajouterEtudiant(etu1);
        gr1.ajouterEtudiant(etu1Bis);
        gr1.ajouterEtudiant(etu3);
        gr1.ajouterEtudiant(etu4);
        gr1.ajouterEtudiant(etu5);
        String exp = "Identite{nip='CC1', nom='Crimson', prenom='Chunchunmaru'}Identite{nip='CM1', nom='Crimson', prenom='Megumin'}Identite{nip='LD1', nom='Lalatina', prenom='Darkness'}Identite{nip='SK1', nom='Satou', prenom='Kazuma'}Identite{nip='SK2', nom='Satou', prenom='Kazuma'}";
        String res = "";
        //methode a tester
        gr1.triAlpha();
        //assertion
        for (Etudiant e : gr1.getEtudiants()){
            res += e.toString();
        }
        assertEquals(exp, res);
    }

    @Test
    public void triAlpha_casUnElement() throws AjoutSuppressionEtudiantImpossibleException {
        //preparation
        gr1.ajouterEtudiant(etu3);
        String exp = "Identite{nip='CM1', nom='Crimson', prenom='Megumin'}";
        String res = "";
        //methode a tester
        gr1.triAlpha();
        //assertion
        for (Etudiant e : gr1.getEtudiants()) {
            res += e.toString();
        }
        assertEquals(exp, res);
    }



}
