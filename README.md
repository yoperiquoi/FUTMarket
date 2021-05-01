#Yoann PERIQUOI, Maxim POZDNYAKOV B2 Conception d'application mobile DUT Informatique de Clermont-Ferrand

##Application mobile Android développé en Java sous environnement Android Studio.

###Contexte : 
Dans le cadre de notre matière de conception d’application mobile nous avons eu à réaliser
une application mobile en duo en 7 semaines. Nous avons choisi comme sujet, le football, et plus
particulièrement les joueurs de football. Nous avons voulu créer une application du style « Ligue
fantasy » ou du mode Fifa Ultimate Team de Fifa qui est un jeu où les participants endossent le rôle de
propriétaires d'équipes sportives. Le joueur a donc l’objectif de collectionner tous les footballeurs qui
sont représenté par des cartes de jeu. Pour cela, la principale méthode pour acquérir des joueurs est
d’ouvrir des packs ou paquet de cartes. Evidemment les paquets de cartes sont donnés en échange de
crédits. Ces crédits peuvent être ensuite obtenu en revendant les joueurs possédés. De plus, si vous
recherchez un joueur en particulier, il peut être retrouvé dans la boutique. Il vous sera donné en
échange de crédits. Il vous faudra donc ouvrir des paquets, pour ensuite vendre les joueurs obtenus
pour acheter les joueurs de vos rêves et collectionner tous les joueurs en ouvrant encore une fois des
paquets. Tout cela pour un objectif, être le plus grand collectionneur de FUT Market.


Veuillez retrouver tout le fichiers de documentation dans le fichier documentation qui contient :
	- un diagramme de classe
	- un diagramme de cas d'utilisation
	- un document décrivant le contexte et l'architecture
	- la javadoc généré
	- la base de données des joueurs
Le document de preuves est dans le document de code.

Nous avons choisi comme fonctionnalités d'utiliser Firebase pour assurer l'authentification et le stockage des joueurs et utilisateur.
La principale fonctionnalités intéressante à observer sont la connexion en anonyme et avec Google.

A noter que cette application ne peut pas être déployé sur le PlayStore car la plupart des éléments graphiques ne nous appartiennent pas.
Les design des cartes proviennent de Fifa, les joueurs de la base de données ont été récupéré à partir d'un site web...

###Notice pour jouer :

Pour jouer vous êtes obliger de créer un compte car toutes vos données sont stockées en ligne.
Vous avez donc le choix entre un compte Google ou bien un email anonyme pour créer votre compte.
Il vous sera alors attribué 1 000 000 de crédit lors de la création de votre compte.
Vous pouvez dès a présent vous rendre dans le marché des packs avec le bouton "Packs" pour ouvrir vos premiers packs en cliquant dessus.
Ces packs vous seront échangé contre des crédits et vous trouverez a l'intérieur des joueurs qui rejoindrons vôtre collection personnelle.
Vous pouvez rejoindre l'écran principal grâce au bouton de retour.
Vous pouvez visionner et parcourir vos joueurs en vous rendant dans votre club en cliquant sur le bouton "Mes Joueurs".
Vos joueurs seront alors listé. Vous pouvez faire une recherche en particulier grâce à la recherche en haut de l'écran.
En cliquant sur la photo du joueur, vous verrez alors apparaître le detail du joueur que vous pourrez ensuite vendre avec le bouton vendre.
La valeur du joueur sera alors ajouté à vos crédits et le joueur sera retirer de votre liste de joueur personnel.
Pour finir il existe un marché. Celui-ci contient tout les joueurs disponibles que vous pouvez trier avec la recherche en haut de l'écran.
Encore une fois, vous pouvez accéder au detail du joueur en cliquant sur sa photo et vous pourrez ensuite l'acheter pour le récupérer dans votre liste
personnel en échange de crédits. 
