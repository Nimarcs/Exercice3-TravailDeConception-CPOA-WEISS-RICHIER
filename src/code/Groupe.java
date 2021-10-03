// PACKAGE
package code;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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

    }

    public void supprimerEtudiant(Etudiant etu) {

    }

    public double calculerMoyenneGroupe(String matiere) {

    }

    public double calculerMoyenneGenerale() {

    }

    public void triParMerite() {

    }

    public void triAlpha() {
        
    }


}
