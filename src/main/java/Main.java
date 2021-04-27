public class Main {
    public static void main(String[] args) {
        Graphe g = new Graphe(5,2);
        //g.afficherGraphe();
        Noeud a = new Noeud(g);
        Noeud b = new Noeud(g);

        System.out.println("L'emplacement du noeud A se trouve à la case numéro : " + a.getCoordonneNoeud());
        System.out.println("L'emplacement du noeud B se trouve à la case numéro : " + b.getCoordonneNoeud());

        Display d = new Display(g.getLongueur(),g.getLargueur(),g.getSize());

    }
}
