package exceptions;

import code.Etudiant;
import code.Groupe;

public class AjoutSuppressionEtudiantImpossibleException extends Exception {

    public AjoutSuppressionEtudiantImpossibleException(Etudiant e){
        super("L'operation d'ajout ou de suppresion de l'etudiant "+e.getIdentite().getNom()+" "+e.getIdentite().getPrenom()+" n'a pas pu se faire");
    }

}
