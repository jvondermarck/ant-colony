package V2.javaClass;

public class AretesOuvrier extends Aretes {

    private Boolean[][] aVisite;

    public AretesOuvrier(Graphe g, int x, int y) {
        super(g, x, y);
    }

    // Methode qui sert uniquement a la fourmis Ouvriere en fonction des noeuds visités, et des phéromones
    public void paramOuvrier(Ouvrier ouvrier)
    {
        aVisite = ouvrier.getaVisite();
        boolean droitDePasssage = rechercheToutVisite(ouvrier); // si true (tout visités), on autorise a se deplacer aléatoirement

        // Si rechercheAucunPheromone = false = ca veut dire que ya au moins une cellule qui contient au min 1 pheromone, donc on execute cette condition
        if(!rechercheAucunPheromone(droitDePasssage))
        {
            for(int i=0; i<listX.size(); i++)
            {
                for(int j=0; j<listY.size(); j++)
                {
                    int xCoord = listX.get(i);
                    int yCoord = listY.get(j);

                    if(!aVisite[xCoord][yCoord] || droitDePasssage) // Si il a pas visité cette cellule ou qu'il a deja tout visité
                    {
                        if(rechercheBestPheromone(xCoord,yCoord)) // Si la cellule a la meilleure quantite de pheromone
                        {
                            listX.clear(); // On supprime toutes les aretes adjacentes de depart
                            listX.add(xCoord); // On ajoute juste la coordonnée de x
                            listY.clear();
                            listY.add(yCoord); // On ajoute juste la coordonnée de y
                        }
                    }
                    i++;
                }
            }
        } else { // Si il n'y a aucun phéromone ou que c'est que des cellules déja visités :
            if(!droitDePasssage) // Si on met pas cette condition et que droitPassage = true, on va supprimer toutes les coord adjacentes vu qu'on a tout visité
            {
                for(int i=0; i<listX.size(); i++)
                {
                    for(int j=0; j<listY.size(); j++)
                    {
                        int xCoord = listX.get(i);
                        int yCoord = listY.get(j);
                        if(aVisite[xCoord][yCoord]) // Si on a visité la cellule
                        {
                            listX.remove(i); // On supprime la coordonnée de X du tableau pour pas que la fourmis se deplace sur cette cellule
                            listY.remove(i);
                            i = 0;
                            j = -1;
                        } else {
                            i++;
                        }
                    }
                }
            }
        }
    }

    // Sur les cellules adjacentes de la fourmis, on cherche la cellule qui a le + de pheromone
    public boolean rechercheBestPheromone(int x, int y)
    {
        int xBest = 0;
        int yBest = 0;

        int bestQuantity = 0;

        int [][] quantityPheromone = graphe.getQuantityPheromone();

        for(int i=0; i<listX.size(); i++)
        {
            for(int j=0; j<listY.size(); j++)
            {
                int xCoord = listX.get(i);
                int yCoord = listY.get(j);
                if(quantityPheromone[xCoord][yCoord] > bestQuantity && !aVisite[xCoord][yCoord])
                {
                    xBest = xCoord;
                    yBest = yCoord;
                    bestQuantity = quantityPheromone[xCoord][yCoord];
                }

                i++;
            }
        }

        return x == xBest && y == yBest; // Si les parametres correspondent aux best coord où se trouve les pheromones
    }

    // Si les cellules adjacentes ne contiennent pas de pheromone, il devra aller de facon aléatoire comme la fourmis soldat
    public boolean rechercheAucunPheromone(Boolean droitDePassage)
    {
        int quantityNull = 0;
        int nbFois = 0;

        int [][] quantityPheromone = graphe.getQuantityPheromone();
        for(int i=0; i<listX.size(); i++)
        {
            for(int j=0; j<listY.size(); j++)
            {
                int xCoord = listX.get(i);
                int yCoord = listY.get(j);
                if(!aVisite[xCoord][yCoord] || droitDePassage) // Si il n'a pas visité le noeud OU qu'il a un droit de passage, alors on peut regarder combien il y a de pheromones ou non
                {
                    if(quantityPheromone[xCoord][yCoord] == quantityNull) // Si la cellule a une quantité de nourriture = 0, on incremente de 1 le nb de fois où ya pas a manger dans les cellules adjacentes
                    {
                        nbFois++;
                    }
                } else {
                    nbFois++; // Si la cellule a deja etait visité, on incremente quand meme de 1, car on dit que comme on peut pas la visiter, ya plus de pheromone
                }
                i++;
            }
        }

        return listX.size() == nbFois && listY.size() == nbFois;
        // return true si on a toutes les cellules de X,Y adjacentes a la fourmis qui contient 0 en pheromone
        // si 4 cellules adjacentes sans nourriture = true, sinon false
    }

    // Recherche si la fourmis soldat a deja visite tous les noeuds adjacents
    public boolean rechercheToutVisite(Ouvrier ouvrier)
    {
        int nbFois = 0;

        Boolean[][] aVisite = ouvrier.getaVisite();

        for(int i=0; i<listX.size(); i++)
        {
            for(int j=0; j<listY.size(); j++)
            {
                int xCoord = listX.get(i);
                int yCoord = listY.get(j);
                if(aVisite[xCoord][yCoord]) // Si il a deja visité la cellule, alors on incremente de 1
                {
                    nbFois++;
                }
                i++;
            }
        }

        return listX.size() == nbFois && listY.size() == nbFois;
        // return true si on a toutes les cellules de X,Y adjacentes a la fourmis qui on deja etait visité
        // si on a 4 cellules adjacentes, et que les 4 ont deja étaient visités, on renvoie true
    }
}
