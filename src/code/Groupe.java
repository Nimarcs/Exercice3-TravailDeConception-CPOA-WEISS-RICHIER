// PACKAGE
package code;

import exceptions.AjoutSuppressionEtudiantImpossibleException;
import exceptions.ListeNotesVideException;

import java.util.*;

/**
 * CLASSE GROUPE
 * Definit une groupe d'etudiant dans un domaine de formation
 */
public class Groupe {

    // ATTRIBUTS
    private Set<Etudiant> etudiants;
    private Formation formation;

    // CONSTRUCTEUR
    public Groupe(Formation form) {
        this.formation = form;
        this.etudiants = new HashSet<Etudiant>();
    }

    // METHODES

    /**
     * Methode d'ajout d'un etudiant au sein du groupe
     * @param etu : Etudiant, celui qu'on souhaite ajouter
     * @throws AjoutSuppressionEtudiantImpossibleException : dclenchee ssi la formation du groupe et de celle de l'etudiant sont differentes
     */
    public void ajouterEtudiant(Etudiant etu) throws AjoutSuppressionEtudiantImpossibleException {
        if (this.formation!=etu.getFormation()) throw new AjoutSuppressionEtudiantImpossibleException(etu);
        this.etudiants.add(etu);
    }

    /**
     * Methode de suppression d'un etudiant au sein du groupe
     * @param etu : Etudiant, celui qu'on desire supprimer
     * @throws AjoutSuppressionEtudiantImpossibleException : declenchee ssi l'etudiant n'est pas present dans le groupe au préalable
     */
    public void supprimerEtudiant(Etudiant etu) throws AjoutSuppressionEtudiantImpossibleException {
        if (this.etudiants.contains(etu)) this.etudiants.remove(etu);
        else throw new AjoutSuppressionEtudiantImpossibleException(etu);
    }

    public double calculerMoyenneGroupe(String matiere) {
        return 0.0;
    }

    public double calculerMoyenneGenerale() {
        return 0.0;
    }

    /**
     * Methode triParMerite, qui tri les etudiants selon leur moyenne generale decroissant
     */
    public void triParMerite() {
        List<Etudiant> listEtudiant = new ArrayList<Etudiant>(this.etudiants);
        Collections.sort(listEtudiant, new Comparator<Etudiant>() {
            @Override
            public int compare(Etudiant a, Etudiant b) {
                double moyA = 0, moyB = 0;
                try {
                    moyA = a.calculerMoyenneGenerale();
                    moyB = b.calculerMoyenneGenerale();
                } catch (ListeNotesVideException e) {
                    e.printStackTrace();
                }
                if (moyA>moyB) return 1;
                if (moyA<moyB) return -1;
                return 0;
            }
        });
        this.etudiants = new HashSet<>(listEtudiant);
    }

    public void triAlpha() {

    }


}
