package V2.javaClass;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.BitSet;

/**
 * The type Ant facade historique.
 */
public class AntFacadeHistorique {

    private final int width;
    private final int height;
    private BitSet[][] grid;
    private FileWriter fw;
    private File fLog;
    private int durationPlay;
    private Graphe graphe;

    /**
     * Instantiates a new Ant facade historique.
     *
     * @param width      the width
     * @param height     the height
     * @param grid       the grid
     * @param antLogFile the ant log file
     */
    public AntFacadeHistorique(int width, int height, BitSet[][] grid, String antLogFile, Graphe graphe)
    {
        this.grid = grid;
        this.height = height;
        this.width = width;
        this.durationPlay = 0;
        this.graphe = graphe;

        try {
            this.fLog = new File(antLogFile);
            boolean bool = fLog.delete();
            System.out.println("File deleted: "+ bool);
            boolean sucess = fLog.createNewFile();
        } catch (IOException e)
        {
            System.err.println("Erreur dans la création du fichier");
            e.printStackTrace();
        }
    }

    /**
     * Start file.
     *
     * @throws IOException the io exception
     */
    public void startFile() throws IOException {
        if(this.durationPlay == 0)
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.now();
            String formattedDateTime = dateTime.format(formatter);

            fw = new FileWriter(fLog,true);
            fw.write("\t\t\t\t\t\t|---------------------------------------------------------------------------------------------------|\n");
            fw.write("\t\t\t\t\t\t|                          Projet Colonie de fourmis - A22 et P21 - 2021                            |\n");
            fw.write("\t\t\t\t\t\t|---------------------------------------------------------------------------------------------------|\n");
            fw.write("\t\t\t\t\t\t|---------------------------------------------------------------------------------------------------|\n");
            fw.write("\t\t\t\t\t\t|                 Fichier d'historique des etats succesifs du systeme de fourmis                    |\n");
            fw.write("\t\t\t\t\t\t|---------------------------------------------------------------------------------------------------|\n");
            fw.write("\t\t\t\t\t\t|                    Lancement de la colonie de fourmis : "+ formattedDateTime +"                          |\n");
            fw.write("\t\t\t\t\t\t|---------------------------------------------------------------------------------------------------|\n");
            fw.write("\t\t\t\t\t\t| Petit mémo :                                                                                      |\n");
            fw.write("\t\t\t\t\t\t| ............                                                                                      |\n");
            fw.write("\t\t\t\t\t\t| le bit n° 0 vaut true si le noeud correspondant de la grille abrite la fourmilière                |\n");
            fw.write("\t\t\t\t\t\t| le bit n° 1 vaut true si le noeud est occupé par un obstacle                                      |\n");
            fw.write("\t\t\t\t\t\t| le bit n° 2 vaut true s'il y a au moins une fourmi-soldat sur le noeud                            |\n");
            fw.write("\t\t\t\t\t\t| le bit n° 3 vaut true s'il y a au moins une fourmi-ouvrière sans nourriture sur le noeud          |\n");
            fw.write("\t\t\t\t\t\t| le bit n° 4 vaut true s'il y a au moins une ouvrière portant de la nourriture sur le noeud        |\n");
            fw.write("\t\t\t\t\t\t| le bit n° 5 vaut true s'il y a de la nourriture sur le noeud                                      | \n");
            fw.write("\t\t\t\t\t\t| le bit n° 6 vaut true s'il y a des phéromones sur le noeud.                                       | \n");
            fw.write("\t\t\t\t\t\t|                                                                                                   | \n");
            fw.write("\t\t\t\t\t\t| 1. Vous trouverez en premier le numéro de l'itération de la Colonie                               | \n");
            fw.write("\t\t\t\t\t\t| 2. Vous trouverez en deuxième l'état du graphe avec les 7 bits différents concernant              | \n");
            fw.write("\t\t\t\t\t\t| 3. Vous trouverez en troisième l'état de chaques ouvriers et ses noeuds voisins, soit             | \n");
            fw.write("\t\t\t\t\t\t|     - Concernant les ouvriers vous avait son numéro, sa colonie, son noeud, sa nourriture         | \n");
            fw.write("\t\t\t\t\t\t|       transporté, et si il est à la recherche de nourriture ou si il ramène à sa colonie à manger | \n");
            fw.write("\t\t\t\t\t\t|     - Si le noeud voisin en question est un obstacle ou non                                       | \n");
            fw.write("\t\t\t\t\t\t|     - Si le noeud voisin en question contient de la nourriture ou non                             | \n");
            fw.write("\t\t\t\t\t\t|     - Si le noeud voisin en question contient au plus 1 phéromone                                 | \n");
            fw.write("\t\t\t\t\t\t| 4. Vous trouverez en quatrième l'état de chaques soldats et ses noeuds voisins, soit              | \n");
            fw.write("\t\t\t\t\t\t|     - Concernant les soldats vous avait son numéro, sa colonie, et son noeud                      | \n");
            fw.write("\t\t\t\t\t\t|     - Et vous aurez la même nomenclature nommé ci-dessus concernant l'affichage des noeuds voisins| \n");
            fw.write("\t\t\t\t\t\t|---------------------------------------------------------------------------------------------------|\n");
            fw.write("\t\t\t\t\t\t|---------------------------------------------------------------------------------------------------|\n");
            fw.write("\t\t\t\t\t\t|                            Made by Julien VON DER MARCK et Jad MACHKOUR                           |\n");
            fw.write("\t\t\t\t\t\t|---------------------------------------------------------------------------------------------------|\n");

            fw.close();
        }
    }

    /**
     * Iteration.
     *
     * @param grid        the grid
     * @param theWorkers  the the workers
     * @param theSoldiers the the soldiers
     * @throws IOException the io exception
     */
    public void iteration(BitSet[][] grid, ArrayList<Ouvrier> theWorkers, ArrayList<Soldat> theSoldiers) throws IOException {
        fw = new FileWriter(fLog,true);
        durationPlay++;

        this.grid = grid;

        fw.write("\n");
        fw.write("\n|-----------------------------------------------------------------------------------------------------------------------------------------------------|");
        fw.write("\n|-----------------------------------------------------------------------------------------------------------------------------------------------------|");
        fw.write("\n|                                                                 ITÉRATION n°"+durationPlay+"                                                                       |\n");
        fw.write("|-----------------------------------------------------------------------------------------------------------------------------------------------------|\n");
        fw.write("|-----------------------------------------------------------------------------------------------------------------------------------------------------|");
        fw.write("\n\t|-----------------------------|");
        fw.write("\n\t|      Etat du Graphe         |\n");
        fw.write("\t|-----------------------------|");


        for(int x=0;x<height;x++)
        {
            fw.write("\n\t| ");
            for (int y = 0; y < width; y++) {
                fw.write(String.format("%s", this.grid[x][y]) + " ");
            }
        }

        fw.write("\n\n\t|-----------------------------|");
        fw.write("\n\t|  Etat des fourmis ouvrières |\n");
        fw.write("\t|-----------------------------| -------------------------------------------------------------------------------------------------------------------|\n");
        for(Ouvrier o : theWorkers)
        {
            fw.write("\t| " + o.toString() + "   \n");
            fw.write("\t| " + o.noeudVoisin(o, graphe) + " \n");
        }

        fw.write("\t| -------------------------------------------------------------------------------------------------------------------------------------------------|\n");

        fw.write("\n\t|-----------------------------|");
        fw.write("\n\t|  Etat des fourmis soldats   |\n");
        fw.write("\t|-----------------------------| -------------------------------------------------------------------------------------------------------------------|\n");
        for(Soldat s : theSoldiers)
        {
            fw.write("\t|  " + s.toString() + " \n");
            fw.write("\t| " + s.noeudVoisin(s, graphe) + " \n");
        }
        fw.write("\t| -------------------------------------------------------------------------------------------------------------------------------------------------|\n");
    }

    /**
     * Close file.
     *
     * @throws IOException the io exception
     */
    public void closeFile() throws IOException {
        fw.close();
    }
}
