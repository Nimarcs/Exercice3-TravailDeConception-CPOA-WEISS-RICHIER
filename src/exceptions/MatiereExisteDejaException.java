package exceptions;

public class MatiereExisteDejaException extends Exception{

    /**
     * exception appelle lorsque l'on donne une matiere qui existe deja alors que l'on attend une valeur nouvelle
     * @param nom nom de la matiere
     */
    public MatiereExisteDejaException(String nom){
        super("La matiere " + nom + " existe deja");
    }

}
