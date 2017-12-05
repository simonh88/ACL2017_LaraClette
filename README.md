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

* Un plateau de taille fixe avec mur et obstacle
* Un perso qui se déplace case par case (il ne peut pas sortir du plateau)
* Un coffre à une position aléatoire
* Détection de la victoire (touche action pour ramasser le coffre)

#### Suite du sprint 1

* Intégration du moteur graphique
* Version graphique du jeu
* Fond map, sprite perso, sprite coffre
* Ajout de monstre qui se déplace vers le héro

### Sprint 2

#### Séance 2

* Un niveau avec plusieur map fixe à parcourir pour trouver le trésor
* Le perso peut changer de maps
* Ajouter un menu
* Le monstre peut attaquer
* Ajout de sons

#### Suite du print 2

* Génération d'ensemble de map aléatoire
* Algo de validation des maps générées


#####Bugs :
* Les monstres peuvent apparemment apparaître dans les rivieres (pareil le boss du coup)

* Je ne sais pas pourquoi il s'est produit :
  * javax.sound.sampled.LineUnavailableException: line with format PCM_SIGNED 44100.0 Hz, 16 bit, stereo, 4 bytes/frame, little-endian not supported.
Impossible de charger res/sound/Background_Retro.wav

##### Pathfinder des monstres
 * Leur ajouter le fait de contourner un obstacle ?
 
#### Reste à faire
 * Bruit attaque monstres 
 * Bruit spécial boss ?
 * Sprites boss
 * Faire pop la clé à la mort du boss
 * Ramasser la clé
 * Checker si on ouvre coffre qu'on ai la clé

