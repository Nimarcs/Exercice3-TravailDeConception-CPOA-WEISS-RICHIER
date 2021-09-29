package code;

import exceptions.MatiereExisteDejaException;
import exceptions.MatiereInexistanteException;
import exceptions.ValeurImpossibleException;

import java.util.HashMap;
import java.util.Set;

public class Formation {

    //attributs

    /**
     * identifiant de la formation
     */
    private String idFormation;

    /**
     * map attribuant un coefficent a chaque matiere
     */
    private HashMap<String, Double> coefficients;

    //constructeur

    /**
     * Contructeur de formation
     * @param id identifiant de la formation
     */
    public Formation(String id){
        idFormation = id;
        coefficients = new HashMap<>();
    }

    //methodes

    /**
     * methode permettant d'ajouter une matiere a la formation
     * @param matiere matiere a ajouter, ne doit pas deja exister
     * @param coeff coefficient de la matiere, ne doit pas etre negatif
     * @throws MatiereExisteDejaException renvoye si la matiere existe deja pour la formation
     * @throws ValeurImpossibleException renvoye si le coefficient est négatif
     */
    public void ajouterMatiere(String matiere, Double coeff) throws MatiereExisteDejaException, ValeurImpossibleException {
        if (coefficients.containsKey(matiere)){
            throw new MatiereExisteDejaException(matiere);
        } else {
            if (coeff < 0)
                throw new ValeurImpossibleException(coeff);
            coefficients.put(matiere,coeff);
        }
    }

    /**
     * supprime la matiere donnee de la formation
     * @param matiere matiere a supprimer
     */
    public void supprimerMatiere(String matiere){
        coefficients.remove(matiere);
    }

    /**
     * methode qui permet de modifier le coefficient d'une matiere
     * @param matiere matiere dont on veut changer le coefficient, doit deja exister
     * @param coeff coefficient a attribuer a la matiere, doit etre positif
     * @throws ValeurImpossibleException renvoye si le coefficient est negatif
     * @throws MatiereInexistanteException renvoye si la matiere n'existe pas
     */
    public void changerCoefficient(String matiere, Double coeff) throws ValeurImpossibleException, MatiereInexistanteException {
        if (coeff < 0)
            throw new ValeurImpossibleException(coeff);
        if (!coefficients.containsKey(matiere)){
            throw new MatiereInexistanteException(matiere);
        }
        coefficients.replace(matiere, coeff);
    }

    /**
     * methode permettant de recuperer le domaine des matieres
     * @return set de matiere
     */
    public Set<String> domaineMatieres(){
        return coefficients.keySet();
    }

    /**
     * methode qui permet de recuperer le coefficient d'une matiere
     * @param matiere matiere sont on veut le coefficient, doit etre dans la formation
     * @return coefficient de la matiere
     * @throws MatiereInexistanteException renvoye si la matiere n'existe pas
     */
    public Double getCoefficient(String matiere) throws MatiereInexistanteException {
        if (!coefficients.containsKey(matiere))
            throw new MatiereInexistanteException(matiere);
        return coefficients.get(matiere);
    }

    /**
     * getter de idFormation
     * @return identifiant de la formation
     */
    public String getIdFormation() {
        return idFormation;
    }
}
