package code;

import exceptions.MatiereExisteDejaException;
import exceptions.ValeurNegativeImpossibleException;

import java.util.HashMap;

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
    }

    //methodes

    /**
     * methode permettant d'ajouter une matiere a la formation
     * @param matiere matiere a ajouter, ne doit pas deja exister
     * @param coeff coefficient de la matiere, ne doit pas etre negatif
     * @throws MatiereExisteDejaException renvoye si la matiere existe deja pour la formation
     */
    public void ajouterMatiere(String matiere, Double coeff) throws MatiereExisteDejaException {
        if (coefficients.containsKey(matiere)){
            throw new MatiereExisteDejaException(matiere);
        } else {
            if (coeff < 0)
                throw  new ValeurNegativeImpossibleException(coeff)
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
    



}
