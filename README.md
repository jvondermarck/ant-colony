# Fourmis - Rapport du projet A22

**Réalisation du projet** :
- Julien VON DER MARCK
- Jad MACHKOUR

## Premier rendu


### **1) Principes SOLID**

---

1. **Single Responsability P.**
   
- La classe Reine **fait** naitre des soldats.
- La classe Colonie **crée** une colonie.
- La classe Noeud **crée** un noeud.
- La classe Soldat **cherche** une nouvelle position.

2. **Open Closed P.**

- La classe FourmisMove est ouverte à l'extension, mais fermée aux modifications. 
  La classe Soldat hérite de la classe FourmisMove, sans changer son code.
  Dans la version 2, nous aurons des fourmis ouvrières, celle-ci héritera de la classe FourmisMove.
   
3. **Liskov Substitution P.**
   
- Une reine doit rester dans le noeud où elle réside de base. 
  Comparé au soldat qui nait dans le noeud où est la Reine, mais celui-ci doit 
  pouvoir se déplacer, comparé à la reine qui ne doit pas se promener dans le graphe.
  C'est pour cela que la classe Soldat hérite de la classe FourmisMove, pour qu'il 
  y ait que le soldat qui puisse changer de direction, et qu'on bloque l'accès
  à la reine de changer de noeud.
- Dans une Version 2, nous aurons une classe Ouvrière qui héritera aussi de
  la classe FourmisMove. 
  
4. **Interface Segregation P.**
   
- (Decouper une interface en plusieurs interfaces afin d'éviter la casse)
- Dans la version 1, nous n’avons pas utilisé d'interface pour implementer des classes.
  On ne veut pas mettre la classe FourmisMove dans une interface car quand on aura la classe 
  Ouvriere, on devra écrire en double, deux fois la méthode randomDirection.
- Cependant nous avons l'interface Fourmis, mais celle-ci ne répond pas aux critères d'une ISP.
  
5. **Dependency Inversion P.**

- La classe FourmisMove est une classe abstraite, ce qui fait que la classe 
  Soldat qui hérite de cette classe, depend d'une abstraction que d'un type concret.


### **2) Principes GRASP**

---

1. **Expert en Information**
   
- Nous donnons la responsabilité à la classe Aretes, qui détient l'information
pour reáliser la recherche des Aretes adjacentes du noeud auquel se trouve la Fourmi.
  
2. **Protection des Variations**
   
- Nous avons chosit de mettre la classe Fourmis en une interface, afin de la protéger du reste du modèle. 
  Néanmoins, cette interface n'etait pas une partie instable pour cette première version mais surement dans la 
  Version 2, elle sera peut-être ammené à évoluer.

3. **Faible couplage**

- La méthode createSoldier(amount), délègue l'action à la classe Reine qui crée une instance
  de la classe Soldat et l'assigne.
  Le couplage est faible car la méthode createSoldier(amount) de la classe AntFacade
  n'a pas besoin de savoir qu'il existe une classe Soldat, donc elle ne dépend pas de 
  l'existence ou non de cette classe.
- Vu que la classe AntFacade à créer une instance de la classe Reine auparavant, il est inutile
  d’encore crée une instance Soldat dans la classe AntFacade. Ce qui permnet a cette classe d'être le
  moins dépendante.

4. **Forte cohésion**

- Quels objects doivent prendre la responsabilité de :
    - Chercher les arêtes adjacentes : **Classe Aretes** 
    - Prendre les résultats de la coordonnée X et Y : **Classe FourmisMove**
    - Chercher le numéro du nouveau noeud : **Classe Graphe**
- Ces 3 classes sont donc cohésives.

5. **Créateur**

- Quel objet doit prendre la responsabilité de créer les soldats sur la fourmilière ?
Réponse : La classe Reine doit prendre cette responsabilité car c'est elle qui
détient toutes les informations nécessaires à la création d'un soldat.
- Il n'est pas possible de créer des soldats sans reine, donc il faut d'abord une reine 
pour créer des soldats.

6. **Contrôleur**

- Quel est le contrôleur pour l'application Fourmilière ?
  La réponse est la classe AntFacade, qui s'occupe de traiter toutes les méthodes 
  nécessaires pour traiter les requêtes de l'utilisateur. 
- Cette classe s'occupe dans la version 1 : de créer une Grille, de mettre des obstacles, 
  de créer une colonie, et de faire jouer les fourmis afin qu'elles puissent se déplacer sur
  le graphe.

7. **Polymorphisme**

- Pas d'utilisation de polymorphisme dans cette version au vu de l'organisation du code.

8. **Indirection**

- Utilisation d'une classe Abstrait FourmisMove, pour que dans la version 2, les fourmis Soldat et
  Ouvrières puissent dépendre d'un comportement (qui est de les faire déplacer), et non d'une implémentation

9. **Invention Pure**

- Pas utilisé dans cette version, mais nous allons sans doute l'utiliser
dans la version 2 lors de l'enrengistrement des traces des états successifs de chaque fourmi.
