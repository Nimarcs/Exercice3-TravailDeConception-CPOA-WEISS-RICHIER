// PACKAGE
package code;

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
    public void ajouterEtudiant(Etudiant etu) {
        this.etudiants.add(etu);
    }

    public void supprimerEtudiant(Etudiant etu) {
        this.etudiants.remove(etu);
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
                double moyA = a.calculerMoyenneGenerale();
                double moyB = b.calculerMoyenneGenerale();
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
