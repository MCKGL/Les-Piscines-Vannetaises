<h1>Projet Java</h1>

<p>Projet bureau en Java dans le cadre du BTS Services informatiques aux organisations, option Solutions Logicielles et Applications Métier.</p>

<h2>Langages et technologies utilisés :</h2>

<ul>
  <li>Java</li>
  <li>JavaFX</li>
  <li>Scene Builder</li>
  <li>IDE Eclipse</li>
  <li>Microsoft SQL Server</li>
</ul>

<h2>Cadre fictif :</h2>

<p>Les piscines municipales vannetaises souhaitent changer de logiciel afin de générer directement des codes permettant d'accéder aux bassins. Ces codes pourront être achetés sur les bornes situées dans les piscines et ce sans aucune inscription. Le paiement sera simulé, mais la problématique ne sera pas traitée.</p>

<p>La borne permettra de générer des codes pour obtenir :</p>

<ul>
  <li>Une leçon individuelle de nage sur un des créneaux libres</li>
  <li>Un abonnement solo 10 entrées valable 10 mois</li>
  <li>Un abonnement duo 10 entrées valable 1 an</li>
  <li>D'autres formules pourront être envisagées</li>
</ul>

<p>Le tarif, le nombre de places, la durée de validité des titres de vente pourront être définis en ligne par des administrateurs authentifiés. Il conviendra donc de proposer la configuration la plus générique/modulable possible. Il faudra prévoir un formulaire permettant à n'importe qui de tester la validité d'un code et de voir son contenu actuel.</p>

<h2>Version</h2>

<p>Version 1</p>

<h2>GitHub</h2>

<p><a href="https://github.com/MCKGL/Les-Piscines-Vannetaises">https://github.com/MCKGL/Les-Piscines-Vannetaises</a></p>

<h2>Installation du projet</h2>

<h3>Prérequis :</h3>

<ul>
  <li>JavaSE-13</li>
  <li>Un IDE Java (Eclipse)</li>
  <li>JavaFX SDK 17 ou 19</li>
  <li>Scene Builder</li>
  <li>Le projet zippé "les_piscines_vannetaises"</li>
</ul>

<h2>Microsoft SQL Server Management Studio</h2>

<p>Pour configurer la base de données, suivez les étapes suivantes :</p>

<ol>
  <li>Dans l'onglet "Sécurité > Connexions", créez une nouvelle connexion.</li>
  <li>Cochez la case "Authentification SQL Server" et saisissez les informations requises.</li>
  <li>Accédez à la section "Mappage d'utilisateurs" dans le menu de gauche.</li>
  <li>Sélectionnez la base de données souhaitée (vous pourrez la modifier ultérieurement une fois les scripts du projet importés).</li>
  <li>Connectez-vous ensuite en utilisant les identifiants générés et importez les scripts au format .bacpac.</li>
  <li>Allez dans les propriétés > Mappage d'utilisateurs et cochez la case correspondant à la base de données souhaitée, en activant le statut "db_owner" pour disposer de tous les droits associés.</li>
  <li>Ensuite, accédez au gestionnaire SQL Server : "SQL Server Configuration Manager" > "Protocoles pour SQLEXPRESS", puis activez le protocole TCP/IP.</li>
</ol>

<h2>Accès et mots de passe de l'espace Administrateur</h2>

<p>Nom d'utilisateur : <strong>admin</strong></p>
<p>Mot de passe : <strong>toto</strong></p>

<h2>Tester un code</h2>

<ul>
  <li>Pour une formule entrées simple : 517173303 </li>
  <li>Pour une formule abonnement solo : 517173340 </li>
  <li>Pour une formule abonnement duo : 517173426 </li>
  <li>Pour une formule cours : 517173439 </li>
</ul>

<h2>Contributeurs</h2>
<p>Développé par Marie-Caroline GRIES, Anthony MENDES, Nicolas NGUYEN et Erwan POCHOLLE.</p>
