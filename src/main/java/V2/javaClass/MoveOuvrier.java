package V2.javaClass;

import java.util.ArrayList;
import java.util.Random;

public abstract class MoveOuvrier implements FourmisMove {

    private int x;
    private int y;
    private Boolean[][] estObstacle;
    private Graphe g;

    public MoveOuvrier(int x, int y)
    {
        setX(x);
        setY(y);
    }

    @Override
    public void randomDirection(Graphe g, Object o, Reine r)
    {
        Ouvrier ouvrier = (Ouvrier)o; // Comme on sait que o est une instance de Ouvrier, on le cast
        this.g = g;
        this.estObstacle = g.getEstObstacle();

        AretesOuvrier aretes = new AretesOuvrier(g, this.x, this.y);
        aretes.paramOuvrier(ouvrier); // On cherche le ou les cellules adjacentes en fonction des cellules deja visite et des pheromones

        Random rand = new Random();
        ArrayList<Integer> listX;
        ArrayList<Integer> listY;

        listX = aretes.getListX();
        listY = aretes.getListY();

        int alea = rand.nextInt(listX.size());
        int nextX = listX.get(alea);
        int nextY = listY.get(alea);

        this.x = nextX;
        this.y = nextY;

        ouvrier.getaVisite()[this.x][this.y] = true;
        if(g.getEstNourriture()[this.x][this.y]) // Si au nouvelle emplacement de la fourmis il y a de la nourriture
        {
            ouvrier.setEtatRetour(true); // Elle va devoir retourner sur la fourmilière ramener la nourriture

            if(g.getQuantityFood()[this.x][this.y] >= r.getColonie().getFoodParam()) // Si ya + a manger que la fourmis va prendre
            {
                ouvrier.setQuantityFoodTaken(r.getColonie().getFoodParam()); // On prend que la quantite de nourriture qu'on lui a indiqué
                g.getQuantityFood()[this.x][this.y] -= r.getColonie().getFoodParam();
            } else {
                ouvrier.setQuantityFoodTaken(g.getQuantityFood()[this.x][this.y]); // On prend la nourriture qu'il reste, donc sa sera inferieur a ce qu'elle doit prendre de base
                g.getQuantityFood()[this.x][this.y] = 0; // On met a 0 le nbr de nourriture vu que la fourmis a tout pris
                //g.getEstNourriture()[this.x][this.y] = false; // Cette cellule de nourriture est plus disponible
                Boolean[][] estNourriture = g.getEstNourriture();
                estNourriture[this.x][this.y] = false;
                g.setEstNourriture(estNourriture); // On dit que cette case n'est plus de la nourriture
            }
            System.out.println("Nourriture trouvé ! ("+ this.x + "," + this.y + ")");
        } else {
            ouvrier.getListXRetour().add(this.x); // Si ouvrier est pas sur la cellule où se trouve de la nourriture, on ajoute ses coordonnés dans la liste
            ouvrier.getListYRetour().add(this.y);

        }
    }

    public void cheminRetour(Ouvrier ouvrier)
    {
        int coordListXRetour = ouvrier.getListXRetour().get(ouvrier.getListXRetour().size()-1);
        int coordListYRetour = ouvrier.getListYRetour().get(ouvrier.getListYRetour().size()-1);

        // On vérifie que la future coordonnée de retour n'est pas un obstacle
        if(ouvrier.isEtatRetour() && !this.estObstacle[coordListXRetour][coordListYRetour]) // Si il retourne bien à la base
        {
            this.x = coordListXRetour; // on prend sa derniere coordonnée enrengistré
            this.y = coordListYRetour;

            // On va supprimer le dernier élement de la liste des coordonnées X et Y
            ouvrier.getListXRetour().remove(ouvrier.getListXRetour().size()-1);
            ouvrier.getListYRetour().remove(ouvrier.getListYRetour().size()-1);
        }
    }

    public String noeudVoisin(Ouvrier ouvrier)
    {
        Aretes arr = new Aretes(this.g, ouvrier.getX(), ouvrier.getY());
        return arr.noeudVoisin(ouvrier);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }
}
