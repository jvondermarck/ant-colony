# Fourmis - Rapport du projet A22 et P21
![A22](https://static.vecteezy.com/ti/vecteur-libre/t2/373734-fourmis-et-trou-rouge-feu-gratuit-vectoriel.jpg)

**Réalisation du projet** :
- Julien VON DER MARCK
- Jad MACHKOUR


## A22 - Deuxieme rendu

### **1) Principes SOLID - V2**

---

1. **Single Responsability P.**

- La classe Colonie **crée** une colonie.
- La classe Noeud **crée** un noeud.
- La classe FourmisMove **cherche** les noeuds adjacents à la fourmi.

2. **Open Closed P.**

- La classe FourmisMove est ouverte à l'extension, mais fermée aux modifications.
  La classe MoveOuvrier hérite de la classe FourmisMove, sans changer son code.
  Celle-ci permet d'ajouter de nouvelles méthodes qui sont seulement utiler par des fourmis
  ouvrière.
- La classe Soldat hérite de la classe FourmisMove, sans changer son code. 
- La classe Ouvrier hérite de la classe MoveOuvrier, sans changer son code.
  Si dans une autre version nous voulons ajouter une autre catégorie d'ouvriers, nous pourrons
  encore hériter de MoveOuvrier.
  
3. **Liskov Substitution P.**

- Une reine doit rester dans le noeud où elle réside de base.
  Comparé au soldat et à l'ouvrier qui nait dans le noeud où est la Reine, mais celui-ci doit
  pouvoir se déplacer, comparé à la reine qui ne doit pas se promener dans le graphe.
- C'est pour-cela que nous avons créé une interface Fourmis où il y a Reine qui implémente 
  cette interface, et une classe Abstraite qui implémente de cette interface qui est FourmisMove
- Au vu des grandes différences de fonctions entre une fourmi Soldat et Ouvrière, nous avons 
  décidé de créer une sous-classe de FourmisMove, qui se dénomme
  MoveOuvrier.
- FourmisMove fait déplacer des soldats de facons aléatoires en évitant des obstacles en utilsant la classe Aretes.
- Alors que MoveOuvrier fait déplacer des ouvriers qui ne doivent pas retourner sur leur pas
  antérieur, ils doivent chercher à manger et suivre les phéromones et en lacher, donc tout ceci 
  est réalisé grâce à la class MoveOuvrier qui hérite de la classe FourmisMove, cette sous-classe
  permet d'utiliser des méthodes spécifiques à des ouvriers, ce qui bloque l'àccès aux Soldats
  et à la Reine. En bref, FourmisMove permet de donner la liste de tous les noeuds adjacents de la
  fourmis ouvrière et MoveOuvrier fait déplacer la fourmi en fonction de toutes les méthodes de déplacement
  spécifiques à une fourmi ouvrière.


4. **Interface Segregation P.**

- (Decouper une interface en plusieurs interfaces afin d'éviter la casse)
- L'interface Fourmis doit servir à la Reine et aux fourmis qui se déplacent. Alors que
  l'interface FourmisMove doit servir seulement aux fourmis ouvrières et soldats.
- Ces deux interfaces permettent de spécifier des caractéristiques différentes aux différentes fourmis.
- Cela évite que la classe Reine aie des méthodes inutiles (écritent pour des fourmis qui se déplacent)
- Si l'interface FourmisMove change, la classe Reine ne sera pas affecté.

5. **Dependency Inversion P.**

- La classe FourmisMove et MoveOuvrier sont des classes abstraites, ce qui fait que la classe
  Soldat et Ouvrier qui hérite de ces classes, dependent plutôt d'une abstraction que de types concrets.
- Ces deux abstractions permettent d'être plus stables que les types concrets.


### **2) Principes GRASP - V2**

---

1. **Expert en Information**

- Nous donnons la responsabilité à la classe FourmisMove, qui détient l'information
  pour réaliser la recherche des Aretes adjacentes du noeud auquel se trouve la Fourmi.
- Nous donnons la responsabilité à la classe MoveOuvrier, qui détient l'information
  pour réaliser la recherche des Aretes adjacentes pour l'ouvrier en fonction des phéromones et des noeuds déjà
  visités. Cette classe à besoin de l'information des aretes adjacentes (au départ) de la classe FourmisMove.

2. **Protection des Variations**

- Nous avons chosit de mettre la classe Fourmis en une interface, afin de la protéger du reste du modèle.
- Nous avons aussi chosit de mettre la classe FourmisMove en une interface pour obliger toutes les fourmis
  qui peuvent se déplacer d'utiliser les méthodes obligatoires pour qu'elles puissent se déplacer.
- Nous venons de le voir, concernant les 3 types de fourmis il était claires que ces 3 fourmis aller demander certaines
  méthodes nécessaires et différentes l'une d'entre elles, c'est pour cela que cette partie était un peu instable vu qu'elles varient.
  Nous les avons protégés en faisant une interface Fourmis et FourmisMove.

3. **Faible couplage**

- La méthode createSoldier(amount) et createWorkers(amount), délègue l'action à la classe Reine qui crée une instance
  de la classe Soldat et/ou Ouvrier, et l'assigne.
  Le couplage est faible car la méthode createSoldier(amount) et createWorkers(amount) de la classe AntFacade
  n'a pas besoin de savoir qu'il existe une classe Soldat et Ouvrier, donc elle ne dépend pas de
  l'existence ou non de ces classes.
- Vu que la classe AntFacade à créer une instance de la classe Reine auparavant, il est inutile
  d’encore crée une instance Soldat et Ouvrier dans la classe AntFacade. Ce qui permnet a cette classe d'être le
  moins dépendante.

4. **Forte cohésion**

- Quels objects doivent prendre la responsabilité de :
  - Chercher les arêtes adjacentes des fourmis soldats : **Classe FourmisMove**
  - Prendre les résultats de la liste de Noeud de la classe FourmisMove : **Classe MoveOuvrier**
  - Chercher les arêtes adjacentes des fourmis ouvrières : **Classe MoveOuvrier**
  - Crée des noeuds : **Classe Graphe**
  - Déplacer les fourmis soldats : **Classe FourmisMove**
  - Déplacer les fourmis ouvrières : **Classe MoveOuvrier**
- Ces classes sont donc cohésives.

5. **Créateur**

- Quel objet doit prendre la responsabilité de créer les soldats et des ouvriers sur la fourmilière ?
  Réponse : La classe Reine doit prendre cette responsabilité car c'est elle qui
  détient toutes les informations nécessaires à la création d'un soldat et d'un ouvrier.
- Il n'est pas possible de créer des soldats ou des ouvriers sans reine, donc il faut d'abord une reine
  pour créer des soldats/ouvriers.

6. **Contrôleur**

- Quel est le contrôleur pour l'application Fourmilière ?
  La réponse est la classe AntFacade, qui s'occupe de traiter toutes les méthodes
  nécessaires pour traiter les requêtes de l'utilisateur.
- Cette classe s'occupe dans la version 2 : de créer une Grille, de mettre des obstacles,
  de créer une colonie, de mettre de la nourriture, des parametres concernant les phéromones d'une colonie, 
  de creer des soldats et des ouvriers, de faire jouer les fourmis afin qu'elles puissent se déplacer sur
  le graphe, et de créer un fichier en .CSV l'historique des mouivements des foumis et des noeuds adjacents

7. **Polymorphisme**

- Utilisation du polymorphisme pour la classe AntFacadeHistorique, car nous appelons la méthode de la classe FourmisMove
  avec la méthode noeudVoisin() pour afficher dans une chaine de charactère des noeuds adjacents à la fourmi (soldat et ouvriere) a 
  grâce à l'objet de la classe Ouvrier et Solddat.
- La classe Reine, Soldat, Ouvrier, redéfinissent la méthode toString() qui permettent de mieux les identifier.
  L'avantage c'est qu'elles formennt une forte cohésion : les sous-classes définissent leur propre comportement.

8. **Indirection**

- Utilisation d'une classe Abstrait MoveOuvrier, qui permet aux fourmis
  Ouvrières de dépendre d'un comportement (qui est de les faire déplacer), et non d'une implémentation.
- Ceci perme de diminuer le couplage entre les différents objets qui la classe Ouvrier qui hérite
  de la classe MoveOuvrier.

9. **Invention Pure**

- On veut enrengistrer les traces des états successifs de chaque fourmi. À quelle classe assigner cette responsabilité ?
- L'expert en information c'est la classe AntFacade qui possède l'information de la grille avec son BitSet par exemple.
- Mais, donner la responsabilité des enrengistrements des etats succesifs de la grille à AntFacade -->
  diminue la cohésion et couple la classe au système de fichier...
- C'est pour-cela que nous avons décidé de créer une nouvelle classe pour ce type d'opération :
  - Elle se dénomme : **AntFacadeHistorique**
  - Cette classe est une invention pure, elle aura une unique responsabilité qui est d'afficher l'historique de la grille
  - Elle aura plus de chance d'être réutilisé et elle peut être utilisé dans d'autres classes 
  - Elle a un impact minimal sur la cohésion et le couplage de AntFacade

<hr />
<hr />


## A22 - Premier rendu


### **1) Principes SOLID - V1**

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


### **2) Principes GRASP - V1**

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

  

