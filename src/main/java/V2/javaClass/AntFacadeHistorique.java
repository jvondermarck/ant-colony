package V2.javaClass;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.BitSet;

public class AntFacadeHistorique {

    private final int width;
    private final int height;
    private BitSet[][] grid;
    private FileWriter fw;
    private File fLog;
    private int durationPlay;

    public AntFacadeHistorique(int width, int height, BitSet[][] grid, String antLogFile)
    {
        this.grid = grid;
        this.height = height;
        this.width = width;
        this.durationPlay = 0;

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

    public void startFile() throws IOException {
        if(this.durationPlay == 0)
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.now();
            String formattedDateTime = dateTime.format(formatter);

            fw = new FileWriter(fLog,true);
            fw.write("|---------------------------------------------------------------------------------------------------|\n");
            fw.write("|                          Projet Colonie de fourmis - A22 et P21 - 2021                            |\n");
            fw.write("|---------------------------------------------------------------------------------------------------|\n");
            fw.write("|---------------------------------------------------------------------------------------------------|\n");
            fw.write("|                 Fichier d'historique des etats succesifs du systeme de fourmis                    |\n");
            fw.write("|---------------------------------------------------------------------------------------------------|\n");
            fw.write("|                    Lancement de la colonie de fourmis : "+ formattedDateTime +"                          |\n");
            fw.write("|---------------------------------------------------------------------------------------------------|\n");
            fw.write("| Petit mémo :                                                                                      |\n");
            fw.write("| ............                                                                                      |\n");
            fw.write("| le bit n° 0 vaut true si le noeud correspondant de la grille abrite la fourmilière                |\n");
            fw.write("| le bit n° 1 vaut true si le noeud est occupé par un obstacle                                      |\n");
            fw.write("| le bit n° 2 vaut true s'il y a au moins une fourmi-soldat sur le noeud                            |\n");
            fw.write("| le bit n° 3 vaut true s'il y a au moins une fourmi-ouvrière sans nourriture sur le noeud          |\n");
            fw.write("| le bit n° 4 vaut true s'il y a au moins une ouvrière portant de la nourriture sur le noeud        |\n");
            fw.write("| le bit n° 5 vaut true s'il y a de la nourriture sur le noeud                                      | \n");
            fw.write("| le bit n° 6 vaut true s'il y a des phéromones sur le noeud.                                       | \n");
            fw.write("|---------------------------------------------------------------------------------------------------|\n");
            fw.write("|---------------------------------------------------------------------------------------------------|\n");
            fw.write("|                            Made by Julien VON DER MARCK et Jad MACHKOUR                           |\n");
            fw.write("|---------------------------------------------------------------------------------------------------|\n");

            fw.close();
        }
    }

    public void iteration(BitSet[][] grid, ArrayList<Ouvrier> theWorkers, ArrayList<Soldat> theSoldiers) throws IOException {
        fw = new FileWriter(fLog,true);
        durationPlay++;

        this.grid = grid;

        fw.write("\n");
        fw.write("\n|----------------------------------------------------------------------------------------------------------------------|");
        fw.write("\n|----------------------------------------------------------------------------------------------------------------------|");
        fw.write("\n|                                           Itération n°"+durationPlay+"                                                              |\n");
        fw.write("|----------------------------------------------------------------------------------------------------------------------|\n");
        fw.write("|----------------------------------------------------------------------------------------------------------------------|");
        fw.write("\n\t|-----------------------------|");
        fw.write("\n\t|      Etat de la colonie     |\n");
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
        fw.write("\t|-----------------------------| -----------------------------------------------------------------------------------------------------------------|\n");
        for(Ouvrier o : theWorkers)
        {
            fw.write("\t| " + o.toString() + " |\n");
            fw.write("\t| " + o.noeudVoisin(o) + " |\n");
        }

        fw.write("\t| -----------------------------------------------------------------------------------------------------------------------------------------------|\n");

        fw.write("\n\t|-----------------------------|");
        fw.write("\n\t|  Etat des fourmis soldats   |\n");
        fw.write("\t|-----------------------------|--------------------------------------------------------------|\n");
        for(Soldat s : theSoldiers)
        {
            fw.write("\t| " + s.toString() + " |\n");
        }
        fw.write("\t| -------------------------------------------------------------------------------------------|\n");
    }

    public void closeFile() throws IOException {
        fw.close();
    }
}
