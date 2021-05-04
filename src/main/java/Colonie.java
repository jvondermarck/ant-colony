import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Colonie {
    
    private final int numColonie;

    public Colonie()
    {
        this.numColonie = 1;
    }

    public int getNumColonie() {
        return numColonie;
    }

    /*
    private static final Map<Integer, String> colonie = new HashMap<Integer, String>();

    public Colonie()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nom de la colonie :  ");
        String nomColonie = sc.next();
        if(colonie.isEmpty())
        {
            colonie.put(1,nomColonie);
        } else {
            colonie.put(colonie.size()+1, nomColonie);
        }
    }

    public static void parcoursColonie()
    {
        for (Map.Entry mapentry : colonie.entrySet())
        {
            System.out.println("Clé: "+ mapentry.getKey() + " | Valeur: " + mapentry.getValue());
        }
    }

    public static Map<Integer, String> getColonie() {
        return colonie;
    }
     */
}
