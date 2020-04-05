# game-ui-welloo

## But du projet
L'application est une interface WEB permettant de voir les trainers ainsi que leur équipe de pokémon mais aussi de pouvoir effectuer des combats avec d'autres dresseurs
## Lancer l'application
Pour lancer l'application, il suffit d'exécuter la commande suivante:
```
mvn clean install && mvn spring-boot:run
```
L'application sera accessible via l'url http://localhost:9000/  

Cette API utilise les API pokemon-type et trainer et nécessite donc que celles-ci soient en fonctionnement, il est possible de modifier le chemin d'accès à ces API directement dans le fichier application.properties.  
Elle utilise aussi l'API battle et nécessite que celle-ci soit démarrer, pour modifier la configuration d'appel à l'API, il faudra modifier le fichier src/main/resources/static/scripts/battle.js

Il est nécessaire de s'authentifier avec les identifiants de connexion d'un trainer:
- Ash/ash_password
- Misty/misty_password

Pages disponibles:
- http://localhost:9000/pokedex : affiche les pokémons
- http://localhost:9000/trainers : affiche les trainers
- http://localhost:9000/trainers/{name} : affiche un trainer avec son équipe
- http://localhost:9000/fight/{name} : commencer un duel avec un trainer
- http://localhost:9000/profile : affiche votre profil avec votre équipe

## Exécuter les tests
Pour exécuter les tests, utiliser la commande suivante:
```
mvn test
```

## Déploiement
L'application n'a pas été déployé

## Auteur

Christopher DUCROCQ [@well-oo](https://github.com/well-oo)  
Etudiant en Master 2 MIAGE FA