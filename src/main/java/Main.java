public class Main {
    public static void main(String[] args) {
        Graphe g = new Graphe(3,3);
        //g.afficherGraphe();
        Noeud a = new Noeud(g);
        Noeud b = new Noeud(g);
        Noeud c = new Noeud(g);
        Noeud d = new Noeud(g);
        Noeud e = new Noeud(g);
        Noeud f = new Noeud(g);
        Noeud w = new Noeud(g);
        Noeud h = new Noeud(g);
        Noeud i = new Noeud(g);


        //System.out.println("L'emplacement du noeud A se trouve à la case numéro : " + a.getCoordonneNoeud());
        //System.out.println("L'emplacement du noeud B se trouve à la case numéro : " + b.getCoordonneNoeud());

        //Display z = new Display(g.getLongueur(),g.getLargueur(),g.getSize());
        System.out.println();
        System.out.println();
        Reine r1 = new Reine(a, g);


    }
}
