// PACKAGE
package code;

// IMPORTS

import exceptions.MatiereInexistanteException;

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
     *
     * @param id   : Identite, identite de l'etudiant
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

    /**
     * Methode ajouterNote qui ajoute une note donnee pour une matiere donnee
     *
     * @param matiere : String, nom de la matiere ou on doit ajouter une note
     * @param note    : Double, valeur de la note a ajoutee
     * @throws MatiereInexistanteException : Exception levee ssi le nom donnee pour la matiere ne retourne aucune liste de notes
     */
    public void ajouterNote(String matiere, Double note) throws MatiereInexistanteException {
        List<Double> listeNotes = this.resultats.get(matiere);
        if (listeNotes == null) {
            throw new MatiereInexistanteException(matiere);
        }
        listeNotes.add(note);
    }

    /**
     * Methode calculerMoyenne qui calcule la moyenne de l'etudiant pour un nom de matiere donne
     *
     * @param matiere : String, nom de la matiere dont on veut faire la moyenne
     * @return Double: moyenne de l'etudiant pour la matiere donnee
     * @throws MatiereInexistanteException : Exception levee ssi le nom donnee pour la matiere ne retourne aucune liste de notes
     */
    public double calculerMoyenne(String matiere) throws MatiereInexistanteException {
        List<Double> listeNotes = this.resultats.get(matiere);
        if (listeNotes == null) {
            throw new MatiereInexistanteException(matiere);
        }
        double somme = 0;
        for (int i = 0; i < listeNotes.size(); i++) {
            somme += listeNotes.get(i);
        }
        return (somme / listeNotes.size());
    }

    /**
     * Methode calculerMoyenneGenerale qui calcule la moyenne générale de l'etudiant avec toutes les matieres et leurs coefficients associés
     * @return Double: moyenne générale de l'étudiant
     */
    public double calculerMoyenneGenerale() {
        Set<String> domaineMatieres = this.formation.domaineMatieres();
        Iterator<String> it = domaineMatieres.iterator();
        double sommeCoeff = 0;
        double sommeMoyenne = 0;
        String matiere;
        double moyenne = 0, coefficient = 0;
        while (it.hasNext()) {
            matiere = it.next();
            try {
                moyenne = this.calculerMoyenne(matiere);
                coefficient = this.formation.getCoefficient(matiere);
            } catch (MatiereInexistanteException e) {
                // Cas techniquement impossible, car les matières de la formation doivent être les mêmes que celle de l'étudiant, au mêmes nombres
                e.printStackTrace();
            }
            sommeCoeff += coefficient;
            sommeMoyenne += (moyenne * coefficient);
        }
        return (sommeMoyenne / sommeCoeff);
    }

    // GETTERS

    public Identite getIdentite() {
        return identite;
    }

    public Formation getFormation() {
        return formation;
    }

    /**
     * Getter qui retourne la liste des notes selon une matiere donnee
     * @param matiere : String, matiere donnee
     * @return List<Double>, liste des notes pour la matiere donnee
     * @throws MatiereInexistanteException : Exception levee ssi le nom donnee pour la matiere ne retourne aucune liste de notes
     */
    public List<Double> getResultat(String matiere) throws MatiereInexistanteException {
        List<Double> list = this.resultats.get(matiere);
        if (list==null) {
            throw new MatiereInexistanteException(matiere);
        }
        return list;
    }
}
