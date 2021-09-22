// PACKAGE
package code;

// IMPORTS
import java.util.*;

/**
 * CLASSE ETUDIANT
 * Definit un etudiant avevc ses notes, son identite et la formation a laquelle il est inscrit
 */
public class Etudiant {

    // ATTRIBUTS
    /**
     * Attribut prive Formation qui stocke la formation auquelle l'etudiant est
     */
    private Formation formation;
    /**
     * Attribut prive Identite qui represente l'identite de l'etudiant
     */
    private Identite identite;
    /**
     * Attribut prive HashMap qui prend le nom d'une matiere (String) et qui retourne une liste de notes (List <Double>)
     */
    private HashMap<String, List<Double>> resultats;

    // CONSTRUCTEURS

    /**
     * Constructeur Etudiant avec 2 parametres
     * @param id : Identite, identite de l'etudiant
     * @param form : Formation, departement d'etude de l'etudiant
     */
    public Etudiant(Identite id, Formation form) {
        this.identite = id;
        this.formation = form;
        // On initialise l'HashMap resultats avec toutes les matieres de la formation donnee
        this.resultats = new HashMap<String, List<Double>>();
        Set<String> domaine = form.domaineMatieres();
        Iterator<String> it = domaine.iterator();
        String matiere;
        while (it.hasNext()) {
            matiere = it.next();
            this.resultats.put(matiere, new ArrayList<Double>());
        }
    }

    // METHODES




    // GETTERS

    public Formation getFormation() {
        return formation;
    }

    public Identite getIdentite() {
        return identite;
    }

    public HashMap<String, List<Double>> getResultats() {
        return resultats;
    }
}
