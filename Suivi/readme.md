Ce fichier contient et contiendra des remarques de suivi sur votre
projet tant sur la modélisation que sur la programmation. Un nouveau
suivi est indiqué par une nouvelle section datée.

Certaines remarques demandent des actions de votre part, vous les
repérerez par une case à cocher.

- []  Action (à réaliser) 

Merci de nous indiquer que vous avez pris en compte la remarque en
cochant la case. N'hésitez pas à écrire dans ce fichier et à nous
exposer votre point de vue.

- [x] Action (réalisée)
  - RÉPONSE et éventuelles remarques de votre part, 

## Spécification et préparation des tests de validation

### Diagrammes de cas d'utilisation

[] DIAGUC-01-Cas-utilisation-manquant

- Il manque un ou plusieurs cas d'utilisation qui vont vous
  manquer par la suite.

eg promouvoir un membre a la fonction de moderation,

retirer la fonction de moderation

[] DIAGUC-02-Compréhension-étude-de-cas

- Un ou plusieurs cas d'utilisation montrent un erreur de
  compréhension de l'étude de cas.

Il existe de nombreux cas d'utilisation pour le modérateur qui ne devraient être réservés qu'à l'administrateur

[] DIAGUC-05-Pb-formulation-nom-cas-d-utilisation

- Un ou plusieurs cas d'utilisation sont mal « nommés ». Par
  exemple, écrire « ajouter/supprimer un client » est à ré-écrire
  en deux cas d'utilisation. Autre exemple, écrire «
  accepter/refuser de faire... » est à réécrire « prendre la
  décision de faire ou non... » avec l'acceptation ou le refus qui
  devient un argument du cas d'utilisation.

pourquoi 2 cas (accepter et refuser) ? quand on peut faire 1 cas (moderer)

### Préconditions et postconditions

1. Cas d'utilisation « créer un réseau social »
- [] PREPOSTCOND-06-Précondition-postcondition-incomplète
  
  - La précondition ou la postcondition d'un cas d'utilisation est
    incomplète. Relisez le cahier des charges pour trouver les
    conditions manquantes.
  
  post - RS ouvert ?
2. Cas d'utilisation « ajouter un membre à un réseau social »
- [] PREPOSTCOND-01-Pré-post-condition-manquante
  
  - Il manque une ou plusieurs préconditions et postconditions, tel
    qu'indiqué dans la [liste des
    tâches](https://www-inf.telecom-sudparis.eu/COURS/CSC4102/?page=liste_recapitulative_des_taches). Re-parcourez
    aussi l'énoncé du TP.
3. Cas d'utilisation « poster un message »

[] PREPOSTCOND-04-Pb-séparation

- Les préconditions et postconditions ne sont pas clairement
  séparées dans le fichier `readme.md`. Cela empêche de faire des
  retours.

2 fois les memes pre/post?

- [] PREPOSTCOND-06-Précondition-postcondition-incomplète
  
  - La précondition ou la postcondition d'un cas d'utilisation est
    incomplète. Relisez le cahier des charges pour trouver les
    conditions manquantes.
  
  Pre/PostConditions sur le RS?
  
  Il manque plusieurs postconditions importantes.

### Tables de décision des tests de validation

1. Cas d'utilisation « créer un réseau social »
- [] TABLEDECTV-07-MAJ-précondition-postcondition
  - Pensez à mettre à jour les tables de décision si vous faites des
    mises à jour des pré/post conditions.
2. Cas d'utilisation « ajouter un membre à un réseau social »
- [] TABLEDECTV-07-MAJ-précondition-postcondition
  - Pensez à mettre à jour les tables de décision si vous faites des
    mises à jour des pré/post conditions.
3. Cas d'utilisation « poster un message »
- [] TABLEDECTV-07-MAJ-précondition-postcondition
  - Pensez à mettre à jour les tables de décision si vous faites des
    mises à jour des pré/post conditions.

## 3. Conception

### Diagramme de classes

[] pourquoi avez-vous besoin d'un attribut booléen "caché" - cela pourrait être une valeur de l'énumération "Etat Message"

- [] DIAGCLAS-07-Généralisation-spécialisation-inappropriée
  
  - Une ou plusieurs généralisation de votre diagramme de classes
    sont inappropriées : par exemple, dans l'étude de cas
    médiathèque, un client peut changer de catégorie et créer un
    client dans une catégorie donnée pour le changer de catégorie
    demanderait de supprimer un client (p.ex. de type
    ClientTarifNormal) pour en recréer un (p.ex. de type
    ClientTarifRéduit).
  
  Pourquoi définir "Membre" comme une sous-classe de "Utilisateur" alors qu'en fait il s'agit bien d'une adhésion/participation associant un utilisateur à un RS.

- [] DIAGCLAS-13-Présence-acteur-dans-système
  
  - Les acteurs n'ont pas tous vocation à être modélisés dans le
    système : par exemple, l'employé de la médiathèque n'est pas
    dans le diagramme de classes car aucun cas d'utilisation ne
    demande à utiliser des informations sur l'employé.
  
  Vous n'avez pas besoin de la valeur "ADM" (administrateur ?) dans l'énumération TypeMembre

### Diagrammes de séquence

1. Cas d'utilisation « créer un réseau social »

[] DIAGSEQ-02-Pb-cohérence-avec-diag-classes

- Un ou plusieurs éléments de la séquence ne sont pas cohérents
  avec le diagramme de classes.

"EnvoyerVersListeAttente"??? IL FAUT REVOIR CA AVEC PAUL

- [] DIAGSEQ-03-Pb-cohérence-arguments-avec-précondition
  - Pour un ou plusieurs diagrammes de séquence, les arguments du
    message en provenance de l'acteur ne correspondent pas avec les
    données de la précondition du cas d'utilisation correspondant.

Lorsque vous créez le message vous passez le paramètre "réseau" mais ne l'utilisez pas dans la construction.

2. Cas d'utilisation « ajouter un membre à un réseau social »
- [] DIAGSEQ-01-diagramme-manquant
  
  - Il manque un ou plusieurs diagrammes de séquence, tel qu'indiqué
    dans la [liste des
    tâches](https://www-inf.telecom-sudparis.eu/COURS/CSC4102/?page=liste_recapitulative_des_taches). Re-parcourez
    aussi l'énoncé du TP.
3. Cas d'utilisation « poster un message »
- [] DIAGSEQ-03-Pb-cohérence-arguments-avec-précondition
  
  - Pour un ou plusieurs diagrammes de séquence, les arguments du
    message en provenance de l'acteur ne correspondent pas avec les
    données de la précondition du cas d'utilisation correspondant.
  
  Comment tester le valuer de mem_pseudo lorsqu'il n'est pas passé en paramètre à la façade?
  
  Dans "setID(ID)", comment utiliser l'identifiant du message avant sa création est terminée?

### Raffinement du diagramme de classes

1. Fiche de la classe « Message »
- OK

### Diagramme de machine à états et invariant

1. Diagramme de machine à états de la classe « Message »
- [] DIAGMACHETATS-03-Cohérence-avec-diagramme-classes
  - Un ou plusieurs états ne sont pas cohérents avec le diagramme de
    classes : par exemple, vous utilisez un type énuméré pour nommer
    les états, mais n'utilisez pas les mêmes noms dans le diagramme
    de machine à états.
  - 
  - Le message n'est jamais dans un état "REFUSE" il est immédiatement détruit
2. Invariant de la classe « Message »

[] INV-03-Pb-cohérence-vocabulaire-avec-diagramme-machine-à-états

- Une ou plusieurs variables utilisées dans la formulation de
  l'invariant ne correspondent pas au vocabulaire utilisé pour
  nommer les états du diagramme de machine à états.

- [] "(cache ⇒ etatMessage==ACCEPTE)"  A REVOIR 

## 4. Préparation des tests unitaires

1. Table de décision des tests unitaires de la méthode Message::constructeur
- OK
2. Table de décision des tests unitaires de la méthode Message::modérer
- OK

## Programmation du logiciel

### Utilisation des outils de programmation

1. Module Maven et tests avec JUnit
- [OK

### Programmation de la solution

#### Classes du diagramme de classes avec leurs attributs

Excellent

#### Méthodes des cas d'utilisation de base

1. Cas d'utilisation « créer un réseau social »
- []Excellent
2. Cas d'utilisation « ajouter un membre à un réseau social »
- []Excellent
3. Cas d'utilisation « poster un message »
- []Excellent

#### Cohérence entre le code et le modèle

1. Cohérences du code avec le diagramme de classes
- []OK
2. Cohérences du code avec les diagrammes de séquence
- [] JAVA-08-Cohérence-avec-diagrammes-de-séquence
  - Il faudra veiller à ce que la modélisation et les opérations des
    classes JAVA soient en cohérence. Pour le moment ce n'est pas le
    cas. Par exemple, les noms des méthodes sont différents, les
    prototypes des méthodes sont différents...

## Programmation et exécution des tests

### Tests de validation des cas d'utilisation

1. Cas d'utilisation « créer un réseau social »
- [] EXCELLENT
2. Cas d'utilisation « ajouter un membre à un réseau social »
- []EXCELLENT
3. Cas d'utilisation « poster un message »
- []EXCELLENT
4. ### Tests unitaires des méthodes d'une classe

5. Constructeur de la classe `Message`
- []EXCELLENT
2. Méthode `modérer` de la classe `Message`
- [] A FAIRE

## Cohérence entre le code et le modèle

1. Préconditions, postconditions et diagrammes de séquence
- [] OK
2. Diagrammes de classes et de séquence
- [] OK
3. Diagrammes de classes et code
- []  A REVOIR - eg dans MiniSocs il y a des attributs dans votre diagramme de classes qui ne sont pas  dans votre code.
4. Diagrammes de séquence et code
- [] OK
5. Table de décision des tests unitaires et programmation des tests unitaires
- [] A COMPLETER (eg Message constructeur)
6. Table de décision des tests de validation et programmation des tests de validation
- [] A COMPLETER - moderer message

## Qualité du code

1. Spotbugs
- [] OK
2. Checkstyle
- [] A COMPLETER

## Application d'idiomes JAVA

1. Idiome méthode `equals` et `hashCode` de la classe `Object`
- [] OK
2. Idiome méthode `toString` de la classe `Object`
- [] OK
3. Idiome des pipelines de *Streams*
- [] A FAIRE
4. Idiome de gestion des références `null` avec `Optional`
- []A FAIRE
