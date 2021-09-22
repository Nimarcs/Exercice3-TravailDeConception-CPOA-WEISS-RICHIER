package exceptions;

public class MatiereExisteDejaException extends Exception{

    public MatiereExisteDejaException(String nom){
        super("La matiere " + nom + " existe deja");
    }

}
