// PACKAGE
package code;

import exceptions.AjoutSuppressionEtudiantImpossibleException;
import exceptions.ListeNotesVideException;
import exceptions.MatiereInexistanteException;

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
     * @throws AjoutSuppressionEtudiantImpossibleException : declenchee ssi la formation du groupe et de celle de l'etudiant sont differentes
     */
    public void ajouterEtudiant(Etudiant etu) throws AjoutSuppressionEtudiantImpossibleException {
        if (etu==null || this.formation!=etu.getFormation()) throw new AjoutSuppressionEtudiantImpossibleException(etu);
        this.etudiants.add(etu);
    }

    /**
     * Methode de suppression d'un etudiant au sein du groupe
     * @param etu : Etudiant, celui qu'on desire supprimer
     * @throws AjoutSuppressionEtudiantImpossibleException : declenchee ssi l'etudiant n'est pas present dans le groupe au préalable
     */
    public void supprimerEtudiant(Etudiant etu) throws AjoutSuppressionEtudiantImpossibleException {
        if (etu==null || this.etudiants.contains(etu)) this.etudiants.remove(etu);
        else throw new AjoutSuppressionEtudiantImpossibleException(etu);
    }

    /**
     * methode permettant de calculer la moyenne du groupe dans une matiere
     * si un etudiant n'a pas de note calcule la moyenne comme si l'etudiant n'existait pas
     * @param matiere matiere dont on veut obtenir la moyenne
     * @return moyenne du groupe
     * @throws MatiereInexistanteException renvoye si les etudiants n'ont pas la matière
     * @throws ListeNotesVideException renvoye si aucun n'etudiant n'a ete note dans la matiere
     */
    public double calculerMoyenneGroupe(String matiere) throws MatiereInexistanteException, ListeNotesVideException {
        //on initialise les valeurs pour calculer la moyenne
        int somme = 0, nb = 0;

        //on parcours les etudiants avec un iterator simplifie
        for(Etudiant e : etudiants){

            //on essaie de recuperer la moyenne de l'eleve dans la matiere
            //si la matiere n'existe pas l'erreur est remonte
            try {
                //on incremente pour le calcul de la moyenne
                somme += e.calculerMoyenne(matiere);
                nb++;
            } catch (ListeNotesVideException ex) {
                //si l'etudiant n'a pas de note dans la matiere,
                //on ne le compte pas pas
            }
        }

        //si aucun etudiant n'a de note dans la matiere on renvoie une erreur
        if (nb == 0){
            throw new ListeNotesVideException("Aucune note n'a ete attribue au groupe dans cette matiere");
        }
        //sinon on renvoie la moyenne
        return (double)somme/nb;
    }

    /**
     * methode qui permet de calculer la moyenne generale d'un groupe
     * si une matiere n'a pas de note calcule la moyenne comme si la matiere n'existe pas
     * @return moyenne generale du groupe
     * @throws ListeNotesVideException renvoye si aucune note n'a ete attribue dans le groupe
     */
    public double calculerMoyenneGenerale() throws ListeNotesVideException {
        //on initialise les valeurs pour calculer la moyenne
        int somme = 0, nb = 0;

        //on parcours les matieres avec un iterateur simplifie
        for (String matiere : formation.domaineMatieres()) {

            //pour chaque matiere on incremente les valeurs pour le calcul de la moyenne
            try{

                //on recupere le coefficient
                double coef = formation.getCoefficient(matiere);
                somme += calculerMoyenneGroupe(matiere) * coef;
                nb += coef;

            }catch (ListeNotesVideException e) {
                //si la matiere n'a pas de note on ne la compte pas
            } catch (MatiereInexistanteException exceptionImpossible){
                //theoriquement impossible
                exceptionImpossible.printStackTrace();
            }
        }

        //si aucune matiere n'a de note on revoie une erreur
        if (nb == 0){
            throw new ListeNotesVideException("Aucune note n'a ete attribue dans ce groupe");
        }

        //sinon on renvoie la matiere
        return (double)somme/nb;
    }

    /**
     * Methode triParMerite, qui tri les etudiants selon leur moyenne generale decroissant
     */
    public void triParMerite() {
        List<Etudiant> listEtudiant = new ArrayList<Etudiant>(this.etudiants);
        listEtudiant.sort(new Comparator<Etudiant>() {
            @Override
            public int compare(Etudiant a, Etudiant b) {
                double moyA = 0, moyB = 0;
                try {
                    moyA = a.calculerMoyenneGenerale();
                    moyB = b.calculerMoyenneGenerale();
                } catch (ListeNotesVideException e) {
                    e.printStackTrace();
                }
                if (moyA > moyB) return 1;
                if (moyA < moyB) return -1;
                return 0;
            }
        });
        this.etudiants = new HashSet<>(listEtudiant);
    }

    public void triAlpha() {
        List<Etudiant> listEtudiant = new ArrayList<Etudiant>(this.etudiants);
        listEtudiant.sort(new Comparator<Etudiant>() {
            @Override
            public int compare(Etudiant a, Etudiant b) {
                Identite iA = a.getIdentite();
                Identite iB = b.getIdentite();
                int i = iA.getNom().compareTo(iB.getNom());
                if (i!=0) {return i;}
                else return iA.getPrenom().compareTo(iB.getPrenom());
            }
        });
        this.etudiants = new HashSet<>(listEtudiant);
    }

    // GETTERS


    public Set<Etudiant> getEtudiants() {
        return etudiants;
    }

    public Formation getFormation() {
        return formation;
    }
}
