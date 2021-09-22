package exceptions;

public class MatiereInexistanteException extends Exception{

    public MatiereInexistanteException(String nomMatiere){
        super("La matiere " + nomMatiere + " inexistante");
    }

}
