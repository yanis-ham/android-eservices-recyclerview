# android-eservices-recyclerview

Ce TP présente le recyclerview, qui permet d'afficher des listes d'objets.


### Description
Cette application présente un jeu de données statique sour la forme d'une liste grâce au recyclerview.
Il présente également les différents callbacks utiles quand on utilise ce genre de listes.
Dans la version finale, je vous propose un système de gestion de clic long comme présenté dans la doc Android.

### Aspects techniques
Les concepts manipulés dans ce TP sont :
- Le RecyclerView
- Son adapteur et le pattern de ViewHolder
- La librairie Glide
- La snackbar
- La communication entre un item de la liste et l'activité


### A découvrir
- A quoi sert l'attribut xml tools:text ?
- L'astuce pour des marges négatives dans un ConstraintLayout, regardons item_recyclerview.

### Objectifs
Le principe est de faire fonctionner le RecyclerView : il faut écrire son adapteur et initialiser tout ce qu'il faut.
- [ ] Créer le squelette de l'adapteur en suivant le pattern de ViewHolder
- [ ] Ecrire le constructeur et le corps du ViewHolder : le constructeur prend en paramètre la vue custom qui a été créée et bind les éléments du layout (textviews, imageviews, etc...)
- [ ] Ecrire la méthode de bind du viewholder. Elle prend en paramètre le contenu que l'on veut afficher et l'affiche dans les vues correspondates. Pour afficher l'image, utiliser Glide (aller voir la doc :) ). 
- [ ] On veut que notre adapteur affiche des données lorsque l'on appelle la méthode bindGameViewModelList(List<GameViewModel> gameViewmodelList). Quelle méthode doit-on appeler pour mettre à jour le contenu de l'adapteur ?
- [ ] Dans l'activité, initialiser le RecyclerView, on veut afficher les éléments en liste 1 colonne verticale.
- [ ] Associer l'adapteur et appeler la méthode de bind sur le jeu de données issu de DataGenerator.
- [ ] On veut maintenant récupérer l'information sur les clics sur les éléments de la liste depuis l'activité. Mettre en place un système utilisant l'interface disponible GameActionInterface et afficher une snackbar lors du clic sur le bouton info ou l'élément entier pour chaque item de la liste (utilisez les Strings en ressource). 
- [ ] Changer le format d'affichage et afficher les éléments en grille sur deux colonnes.



