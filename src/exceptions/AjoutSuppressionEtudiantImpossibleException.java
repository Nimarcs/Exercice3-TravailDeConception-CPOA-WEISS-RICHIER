package exceptions;

import code.Etudiant;
import code.Groupe;

public class AjoutSuppressionEtudiantImpossibleException extends Exception {

    public AjoutSuppressionEtudiantImpossibleException(){
        super("L'operation d'ajout ou de suppresion de l'etudiant n'a pas pu se faire");
    }

}
