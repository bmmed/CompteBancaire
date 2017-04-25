package code;

/**
 * Created by BMMed on 07/03/2017.
 */
public class soldeNegatifExeption extends Exception {

    public soldeNegatifExeption() {
        System.out.println("L'operation ne peut etre executer le solde n'est pas sufisant");
    }

}
