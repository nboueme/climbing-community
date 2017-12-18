## Créez un site communautaire autour de l’escalade

Ceci est le dépôt associé au projet [_Créez un site communautaire autour de l’escalade_](https://openclassrooms.com/projects/creez-un-site-communautaire-autour-de-lescalade)
sur [_OpenClassrooms_](https://www.openclassrooms.com).


### Technologies

- JDK8 version 152
- Apache Tomcat 9.0.1
- Apache Maven 3.5.2
- PostgreSQL 9.6


### Modules

- `climbing-webapp` : module contenant les vues et ses contrôleurs
- `climbing-business` : module contenant la logique métier
- `climbing-consumer` : module contenant la persistance et le pattern DAO
- `climbing-model` : module contenant les entités de la solution
- `script` : tous les scripts de création de la base de données PostgreSQL


## Diagramme de classes
![](image/class_diagram-v2.png?raw=true)


## MPD
![](image/mpd_escalade-v3.jpg?raw=true)


### Déploiement

- Importation du projet dans IntelliJ
- Configurer la base de données :

Ouvrir le fichier `climbing-webapp/src/main/webapp/META-INF/context.xml` et trouver les lignes suivantes :
```
url="jdbc:postgresql://localhost:5432/app_climbing"
driverClassName="org.postgresql.Driver"
username="admin_climbing"
password="Shangri_La"
defaultAutoCommit="true"
defaultTransactionIsolation="READ_COMMITTED"
```

Il suffit de paramètrer une base de données avec la même configuration, ou d'adapter cette dernière à une déjà existante.

- Configurer le serveur Apache Tomcat en local :

Dans l'onglet `Deployment` de la configuration du serveur, déployer le war de l'application web ainsi que le dossier présent sur le serveur pour les transferts des fichiers.
Définir une `Application context` pour ce dossier, comme par exemple `/image`, un fichier sera ensuite accessible via cette URL exemple :
`http://localhost:8080/image/user/user-1.jpg` selon l'architecture de votre dossier.