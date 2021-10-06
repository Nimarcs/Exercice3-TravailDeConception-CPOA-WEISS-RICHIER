package exceptions;

import code.Etudiant;
import code.Groupe;

public class FormationNonCorrespondanteException extends Exception {

    public FormationNonCorrespondanteException(Etudiant e){
        super("L'ajout de l'etudiant"+e.getIdentite().getNom()+" "+e.getIdentite().getPrenom()+" ne peux se faire dans ce groupe car il ne suit pas la même formation");
    }

}
