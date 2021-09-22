// PACKAGE
package code;

/**
 * CLASSE IDENTITE
 * Elle definit l'identite d'une personne
 */
public class Identite {

    // ATTRIBUTS

    /**
     * Attributs prives sur le numero NIP, le nom et le prenom d'une personne
     */
    private String nip, nom, prenom;

    // CONSTRUCTEURS

    /**
     * Constructeur identite avec 3 parametres de type String
     * @param numIP : String, nip de la personne
     * @param nom : String, nom de la personne
     * @param prenom : String, prenom de la personne
     */
    public Identite (String numIP, String nom, String prenom) {
        this.nip = numIP;
        this.nom = nom;
        this.prenom = prenom;
    }

    // GETTERS

    public String getPrenom() {
        return prenom;
    }

    public String getNip() {
        return nip;
    }

    public String getNom() {
        return nom;
    }
}
