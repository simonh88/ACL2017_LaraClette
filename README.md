# Projet java ACL

## Membres du groupe :
* HAJEK Simon
* COURTIL ANtoine
* PETIT Guillaume
* ZABLOT Guillaume

## Brain storming
* Jeu en 2D
* Porte pour changer de map
* But : trouver le trésor
* Monstres (surtout autour du coffre)
* Perso armé d'une épée
* Perso a des points de vie
* Pièges (lave par exemple)
* Touche pour fouiller et trouver des objets (bonus: pdv, boost arme, minimap, malus: monstre) cachés (jarre)
* Boss ?
* Sauvegarde
* Plusieurs niveaux
* Score / temps
* Gestion déplacement par case, plus tard par pixel ?

## Backlog
* 1 Version texte : une carte avec coffre (pas aléatoire), un perso qui peut se déplacer case par case.
* 2 Première version graphique basique. Génération aléatoire map ?
* 3 Deuxième version graphique : ajout élem sur map non texturés (+piège)
* 4 Ajout des sprites + anim ?
* 5 Monstres + armes + combat + pdv
* 6 Fouiller + niveaux + sauvegarde + score
 

## Backlog détaillé

### Sprint 1

#### Séance 1

* Un plateau de taille fixe avec mur et obstacle [GP]
* Un perso qui se déplace case par case (il ne peut pas sortir du plateau) [AC + SH]
* Un coffre à une position aléatoire [GZ]
* Détection de la victoire (touche action pour ramasser le coffre) [GZ]

#### Suite du sprint 1

* Intégration du moteur graphique [GP + SH]
* Menus, hp, visuel [GZ]
* Fond map, sprite perso, sprite coffre [AC]
* Ajout de monstre qui se déplace vers le héro [SH]

### Sprint 2

#### Séance 2

* Un niveau avec plusieur map fixe à parcourir pour trouver le trésor [SH + GP]
* Le perso peut changer de maps [SH + GP]
* Ajouter un menu [GZ]
* Le monstre peut attaquer animation + hero [AC]
* Ajout de sons [GZ]

#### Suite du sprint 2

* Génération d'ensemble de map aléatoire [GP]
* Génération aléatoire décore [GP]
* Génération coffre, monstres, boss [SH]
* sprites [AC]
* Menus, fin de partie [GZ]
* Loots, jar [GP]
* Boss, Key, Coffre [SH]
* Attaques [AC]


### Sprint 3

#### Séance finale

* Ajouter un son pour la pièce du boss, attaque des monstres, clé qui tombe, ... [GZ] OK
* Améliorer l'IA des monstres [AC] SUSPEND
* Ajout de niveau de difficulté (facile, moyen, difficile) [GZ DANS MENU AC dans game] OK
* Ajout d'un système de temps avec score [SH] OK
* Améliorations pour le Héro dans les jars (attaque x2, HP x2, ...)
  avec durée (peut-être) [SH] OK
* Ajout des pouvoirs [SH] OK
* Pouvoir mettre le jeu en pause [GZ] OK, mais Texte a écrire
* Afficher les 5-10 derniers scores dans le menu EN COURS
* Correction bug son attaque qui veut pas se charger une fois dans la salle
  du boss + son victoire (restart bug) [GZ] OK
* Ralentir Héro quand peu de vie [A VOIR SI ON A LE TEMPS]

#####Bugs :

PATHFINDER FULL BUG MERCI ANTOINE

