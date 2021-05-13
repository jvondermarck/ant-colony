# Fourmis - Rapport du projet A22

**Réalisation du projet** :
- Julien VON DER MARCK
- Jad MACHKOUR

## Premier rendu


### 1) Principes SOLID

---

1. Single Responsability P.
   
- La classe Reine **fait** naitre des soldats.
- La classe Colonie **fait** crée une colonie.
- La classe Noeud **fait** crée un noeud.

2. Open Closed P.

- La classe Fourmis est ouverte à l'extension, mais fermée aux modifications. La classe Soldat et Reine hérite de la classe Fourmis, sans changer le code de la classe Fourmis.
   
3. Liskov Substitution P.
   
- Une reine doit rester dans le noeud où elle réside de base. 
  Comparé au soldat qui nait dans le noeud où est la Reine, mais celui-ci doit 
  pouvoir se déplacer, comparé à la reine qui ne doit pas se promener dans le graphe.
  C'est pour cela que la classe Soldat hérite de la classe FourmisMove, pour qu'il 
  y ai que le soldat qui puisse changer de direction, et qu'on bloque l'accès
  à la reine de changer de noeud.
- Dans une Version 2, nous aurons une classe Ouvrière qui héritera aussi de
la classe FourmisMove. 
  
4. Interface Segregation P.
   
- (Decouper une interface en plusieurs interfaces afin d'éviter la casse)
- Dans la version 1, nous avons pas utilisé d'interface pour implementer des classes.
    On ne veut pas mettre la classe FourmisMove dans une interface car quand on aura la classe 
    Ouvriere, on devra écrire en double, deux fois la méthode randomDirection.
  
5. Dependency Inversion P.

- 


### 2) Principes GRASP

---

1. Expert en Information
   
- Nous donnons la responsabilité à la classe Aretes, qui détient l'information
pour reáliser la recherche des Aretes adjacentes du noeud auquel se trouve la Fourmi.
  
2. Protection des Variations
   
- 

3. Faible couplage

-

4. Forte cohésion

- Quels objects doivent prendre la responsabilité de :
    - Chercher les arêtes adjacentes : **Classe Aretes** 
    - Prendre les résultats de la coordonnée X et Y : **Classe FourmisMove**
    - Chercher le numéro du nouveau noeud : **Classe Graphe**
- Ces 3 classes sont donc cohésives.

5. Créateur

- Quel objet doit prendre la responsabilité de créer les soldats sur la fourmilière ?
Réponse : La classe Reine doit prendre cette responsabilité car c'est elle qui
détient toutes les informations nécessaires à la création de soldat.
- Il n'est pas possible de créer des soldats sans reine, donc il faut d'abord une reine 
pour créer des soldats.

6. Contrôleur

- Quel est le contrôleur pour l'application Fourmilière ?
La réponse est la classe AntFacade, qui s'occupe de traiter toutes les méthodes 
nécessaires pour traiter les requêtes de l'utilisateur.

7. Polymorphisme

- Pas d'utilisation de polymorphisme dans cette version au vu de
l'organisation du code.

8. Indirection

- Utilisation d'une classe Abstrait Fourmis et FourmisMove

9. Invention Pure

- Pas utilisé dans cette version, mais nous allons sans doute l'utiliser
dans la version 2 lors de l'enrengistrement des traces des états successifs de chaque fourmi.
