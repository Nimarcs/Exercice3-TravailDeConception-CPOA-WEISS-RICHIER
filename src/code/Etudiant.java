// PACKAGE
package code;

// IMPORTS

import exceptions.*;
import java.security.InvalidParameterException;
import java.util.*;

/**
 * CLASSE ETUDIANT
 * Definit un etudiant avevc ses notes, son identite et la formation a laquelle il est inscrit
 */
public class Etudiant implements Comparable<Etudiant> {

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
    public Etudiant(Identite id, Formation form) throws InvalidParameterException{
        if (id == null || form == null) throw new InvalidParameterException("L'etudiant doit avoir forcement une identite et une formation");
        this.identite = id;
        this.formation = form;
        // On initialise l'HashMap resultats avec toutes les matieres de la formation donnee
        this.resultats = new HashMap<String, List<Double>>();
        Iterator<String> it = this.formation.domaineMatieres().iterator();
        while (it.hasNext()) {
            this.resultats.put(it.next(), new ArrayList<Double>());
        }
    }

    // METHODES

    /**
     * Methode ajouterNote qui ajoute une note donnee pour une matiere donnee
     *
     * @param matiere : String, nom de la matiere ou on doit ajouter une note
     * @param note    : Double, valeur de la note a ajoutee
     * @throws MatiereInexistanteException : Exception levee ssi le nom donnee pour la matiere ne retourne aucune liste de notes
     * @throws ValeurImpossibleException : Exception levee lorsque la note est inférieur a 0 ou supérieur a 20
     */
    public void ajouterNote(String matiere, Double note) throws MatiereInexistanteException, ValeurImpossibleException {
        List<Double> listeNotes = this.resultats.get(matiere);
        if (listeNotes == null) {
            throw new MatiereInexistanteException(matiere);
        }
        if (note<0 || note>20) {
            throw new ValeurImpossibleException(note);
        }
        listeNotes.add(note);
    }

    /**
     * Methode calculerMoyenne qui calcule la moyenne de l'etudiant pour un nom de matiere donne
     *
     * @param matiere : String, nom de la matiere dont on veut faire la moyenne
     * @return Double: moyenne de l'etudiant pour la matiere donnee
     * @throws MatiereInexistanteException : Exception levee ssi le nom donnee pour la matiere ne retourne aucune liste de notes
     * @throws ListeNotesVideException : Exception levee ssi la lise des notes pour la matiere donnee est vide
     */
    public double calculerMoyenne(String matiere) throws MatiereInexistanteException, ListeNotesVideException {
        List<Double> listeNotes = this.resultats.get(matiere);
        if (listeNotes == null) {
            throw new MatiereInexistanteException(matiere);
        }
        if (listeNotes.size()==0) {
            throw new ListeNotesVideException(matiere);
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
    public double calculerMoyenneGenerale() throws ListeNotesVideException {
        Set<String> domaineMatieres = this.formation.domaineMatieres();
        Iterator<String> it = domaineMatieres.iterator();
        double sommeCoeff = 0;
        double sommeMoyenne = 0;
        String matiere;
        double moyenne = 0, coefficient = 0;
        while (it.hasNext()) {
            matiere = it.next();
            try {
                coefficient = this.formation.getCoefficient(matiere);
                moyenne = this.calculerMoyenne(matiere);
            } catch (MatiereInexistanteException | ListeNotesVideException e) {
                // Cas techniquement impossible pour MatiereInexistanteException, car les matières de la formation doivent être les mêmes que celle de l'étudiant, au mêmes nombres
                if (e.getClass() == MatiereInexistanteException.class)
                    e.printStackTrace();
                
                // Cas ListeNotesVideException, on mets la moyenne et le coefficient a 0 afin de ne pas compter cette matiere dans la moyenne
                moyenne = 0;
                coefficient = 0;
            }
            sommeCoeff += coefficient;
            sommeMoyenne += (moyenne * coefficient);
        }
        if (sommeCoeff==0) throw new ListeNotesVideException("toutes les matieres");
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

    /**
     * Methode equals qui determine si l'objet this et celui en parametres sont egaux ou non
     * @param o : Object, objet dont on teste l'égalité
     * @return boolean, true si egaux sinon false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Identite other = ((Etudiant) o).getIdentite();
        Identite t = this.getIdentite();
        return (other.getNip().equals(t.getNip()));
    }

    /**
     * methode qui renvoie un code identique a un autre Etudiant uniquement si les Etudiants sont egaux
     * @return hashCode de l'etudiant
     */
    @Override
    public int hashCode() {
        return this.identite.getNip().hashCode();
    }

    /**
     * methode d'affichage d'un etudiant
     * on affiche sont identite
     * @return affichage de sont identite
     */
    @Override
    public String toString() {
        return identite.toString();
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure
     * {@code sgn(x.compareTo(y)) == -sgn(y.compareTo(x))}
     * for all {@code x} and {@code y}.  (This
     * implies that {@code x.compareTo(y)} must throw an exception iff
     * {@code y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code x.compareTo(y)==0}
     * implies that {@code sgn(x.compareTo(z)) == sgn(y.compareTo(z))}, for
     * all {@code z}.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
     * <i>signum</i> function, which is defined to return one of {@code -1},
     * {@code 0}, or {@code 1} according to whether the value of
     * <i>expression</i> is negative, zero, or positive, respectively.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Etudiant o) {
        return this.identite.getNip().compareTo(o.identite.getNip());
    }
}
