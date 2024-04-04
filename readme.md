# Gestion de mini réseaux sociaux MiniSocs

Binôme :
* Ludovic HU
* Jeanne VILLETTE

## Syntaxe MarkDown

La syntaxe MarkDown de ce document est compatible avec la syntaxe
GitLab, qui est documentée dans
https://docs.gitlab.com/ee/user/markdown.html

## 1. Spécification

### 1.1. Acteurs et cas d'utilisation

La **première étape** consiste à **bien comprendre le système** à
étudier. Dans le cadre de l'exercice, cela consiste à lire
attentivement l'énoncé. Cette lecture doit permettre de *délimiter les
contours du système* à réaliser. La méthode générale consiste à
retrouver les acteurs qui interagissent avec lui. Il est très
important de fixer des frontières au problème. Ensuite, nous
recherchons les fonctionnalités du système par la définition de ses
cas d'utilisation. Dans le cadre de ce module, il s'agit de rechercher
les principales fonctions attendues du système. Nous nous limitons aux
cas d'utilisation pour atteindre les premiers objectifs indiqués dans
le cahier des charges, en prenant en considération les simplifications
énoncées dans le cahier des charges.

Pour réaliser le diagramme de cas d'utilisation à partir de l'analyse
du texte :
* rechercher les acteurs, avec les potentielles relation de
  généralisation spécialisation,
* rechercher les fonctionnalités du système accessibles aux acteurs

Pour rappel, la documentation du langage pour écrire les diagrammes
UML avec PlantUML est disponible à l'adress suivante :
- (https://plantuml.com/fr/)

Voici ci-dessous le diagramme de cas d'utilisation avec les cas
d'utilisation les plus importants (code
[source](./Diagrammes/minisocs_uml_diag_cas_utilisation.pu)).

![diagrammecasutilisation](./Diagrammes/minisocs_uml_diag_cas_utilisation.svg)

### 1.2. Priorités, préconditions et postconditions des cas d'utilisation

Les priorités des cas d'utilisation sont choisies avec les règles de
bon sens suivantes:

* pour retirer une entité du système, elle doit y être. La priorité de
l'ajout est donc supérieure ou égale à la priorité du retrait ;

* pour lister les entités d'un type donné, elles doivent y être. La
priorité de l'ajout est donc supérieure ou égale à la priorité du
listage ;

* il est *a priori* possible, c.-à-d. sans raison contraire, de
démontrer la mise en œuvre d'un sous-ensemble des fonctionnalités du
système, et plus particulièrement la prise en compte des principales
règles de gestion, sans les retraits ou les listages ;

* la possibilité de lister aide au déverminage de l'application
pendant les activités d'exécution des tests de validation.

Par conséquent, les cas d'utilisation d'ajout sont *a priori* de
priorité « HAUTE », ceux de listage de priorité « Moyenne», et ceux de
retrait de priorité « basse ».

Voici les précondition et postcondition des cas d'utilisation de
priorité HAUTE.

#### Ajouter un membre (à un réseau) (HAUTE)
- précondition : \
∧ nom du réseau bien formé (non null ∧ non vide) \
∧ réseau avec ce nom existant \
∧ réseau avec ce nom ouvert \
∧ pseudo bien formé (non null ∧ non vide) \
∧ le compte est actif \
∧ utilisateur avec ce pseudo existant \
∧ utilisateur n'est pas membre du réseau \
∧ l'acteur a un nom bien formé (non null ^ non vide) \
∧ l'acteur existe \
∧ le compte acteur est actif \
∧ l'acteur fait partie du réseau \
∧ l'acteur est modérateur du réseau \
∧ pseudo choisi pour le réseau bien formé (non null ^ non vide) \
∧ pseudo choisi pour le réseau n'est pas déjà pris
- postcondition : \
∧ utilisateur est membre du réseau

#### Désactiver son compte (HAUTE)
- précondition : \
∧ pseudo bien formé (non null ∧ non vide) \
∧ le compte est actif \
∧ utilisateur avec ce pseudo existant
- postcondition : le compte de l'utilisateur est désactivé

NB : l'opération est idempotente.

#### Poster un message (HAUTE)
- précondition : \
∧ nom du réseau bien formé (non null ∧ non vide) \
∧ réseau avec ce nom existant \
∧ réseau avec ce nom ouvert \
∧ pseudo bien formé (non null ∧ non vide) \
∧ le compte n'est pas bloqué \
∧ utilisateur avec ce pseudo existant \
∧ message bien formé (non null ∧ non vide) \
∧ l'utilisateur est un membre du réseau
- postcondition : \
∧ le message est en attente de traitement par un modérateur 

#### Ajouter un utilisateur (à MiniSocs) (HAUTE)
- précondition : \
∧ pseudo bien formé (non null ∧ non vide) \
∧ nom bien formé  (non null ∧ non vide) \
∧ prénom bien formé  (non null ∧ non vide) \
∧ courriel bien formé (respectant le standard RFC822) \
∧ utilisateur avec ce pseudo inexistant \
∧ utilisateur avec ce courriel inexistant \
∧ l'acteur a un nom bien formé (non null ^ non vide)\
∧ l'acteur existe \
∧ l'acteur est administrateur du réseau 
- postcondition : \
∧ utilisateur avec ce pseudo existant \
∧ utilisateur avec ce courriel existant \
∧ le compte de l'utilisateur est actif

#### Créer un réseau social (HAUTE)
- précondition : \
∧ pseudo bien formé (non null ∧ non vide) \
∧ le compte est actif \
∧ utilisateur avec ce pseudo existant \
∧ réseau avec ce nom inexistant \
∧ nom du réseau bien formé (non null ∧ non vide) \
∧ pseudo réseau bien formé (non null ∧ non vide)
- postcondition : \
∧ réseau existant \
∧ réseau ouvert \
∧ utilisateur est membre du réseau \
∧ utilisateur est modérateur du réseau

#### Modérer un message (MOYENNE)
- précondition : \
∧ pseudo bien formé (non null ∧ non vide) \
∧ utilisateur avec ce pseudo existant \
∧ le compte est actif \
∧ nom du réseau bien formé (non null ∧ non vide) \
∧ réseau avec ce nom existant \
∧ réseau avec ce nom ouvert \
∧ utilisateur est membre du réseau \
∧ utilisateur est modérateur du réseau \
∧ message fait partie du réseau \
∧ message en attente de modération \
∧ état demandé du message est ACCEPTE ou REFUSE
- postcondition : \
∧ état du message ACCEPTE ou REFUSE


#### Autres cas d'utilisation et leur priorité respective

- Retirer un utilisateur (de MiniSocs) (basse)

- Retirer un membre (d'un réseau) (basse)

- Bloquer le compte d'un utilisateur (basse)

- Lister les membres (moyenne)

- Promouvoir un membre (moyenne)

- Déstituer un modérateur (basse)

- Fermer son réseau social (basse)

- Réactiver son compte (basse)

- Cacher son message (basse)

- Définir son pseudo pour un réseau (basse)


## 2. Préparation des tests de validation des cas d'utilisation

#### Ajouter un membre (à un réseau) (HAUTE)

|                                                | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 | 12 | 13 | 14 | 15 |
|:-----------------------------------------------|:--|:--|:--|---|---|---|---|---|---|---|---|---|---|---|---|
| nom du réseau bien formé (non null ∧ non vide) | F | T | T | T | T | T | T | T | T | T | T | T | T | T | T |
| réseau avec ce nom existant                    |   | F | T | T | T | T | T | T | T | T | T | T | T | T | T |
| réseau avec ce nom ouvert                      |   |   | F | T | T | T | T | T | T | T | T | T | T | T | T |
| pseudo bien formé (non null ∧ non vide)        |   |   |   | F | T | T | T | T | T | T | T | T | T | T | T |
| utilisateur avec ce pseudo existant            |   |   |   |   | F | T | T | T | T | T | T | T | T | T | T |
| utilisateur n'est pas membre du réseau         |   |   |   |   |   | F | T | T | T | T | T | T | T | T | T |
| le compte est actif                            |   |   |   |   |   |   | F | T | T | T | T | T | T | T | T |
| nom acteur bien formé (non null ∧ non vide)    |   |   |   |   |   |   |   | F | T | T | T | T | T | T | T |
| acteur existant                                |   |   |   |   |   |   |   |   | F | T | T | T | T | T | T |
| compte acteur actif                            |   |   |   |   |   |   |   |   |   | F | T | T | T | T | T |
| acteur fait partie du réseau                   |   |   |   |   |   |   |   |   |   |   | F | T | T | T | T |
| acteur est modérateur                          |   |   |   |   |   |   |   |   |   |   |   | F | T | T | T |
| pseudo réseau bien formé (non null ^ non vide) |   |   |   |   |   |   |   |   |   |   |   |   | F | T | T |
| pseudo réseau n'est pas déjà pris              |   |   |   |   |   |   |   |   |   |   |   |   |   | F | T |
|                                                |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| utilisateur est membre du réseau               | F | F | F | F | F | F | F | F | F | F | F | F | F | F | T |
|                                                |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| nombre de tests dans le jeu de tests           | 2 | 1 | 1 | 2 | 1 | 1 | 1 | 2 | 1 | 1 | 1 | 1 | 2 | 1 | 1 |

#### Ajouter un utilisateur (à MiniSocs) (HAUTE)

|                                                     | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 |
|:----------------------------------------------------|:--|:--|:--|---|---|---|---|---|---|---|
| pseudo bien formé (non null ∧ non vide)             | F | T | T | T | T | T | T | T | T | T |
| nom bien formé  (non null ∧ non vide)               |   | F | T | T | T | T | T | T | T | T |
| prénom bien formé  (non null ∧ non vide)            |   |   | F | T | T | T | T | T | T | T |
| courriel bien formé (respectant le standard RFC822) |   |   |   | F | T | T | T | T | T | T |
| utilisateur avec ce pseudo inexistant               |   |   |   |   | F | T | T | T | T | T |
| utilisateur avec ce courriel inexistant             |   |   |   |   |   | F | T | T | T | T |
| nom acteur bien formé (non null ∧ non vide)         |   |   |   |   |   |   | F | T | T | T |
| acteur existant                                     |   |   |   |   |   |   |   | F | T | T |
| acteur est modérateur                               |   |   |   |   |   |   |   |   | F | T |
|                                                     |   |   |   |   |   |   |   |   |   |   |
| utilisateur avec ce pseudo existant                 | F | F | F | F | F | F | F | F | F | T |
| utilisateur avec ce courriel existant               | F | F | F | F | F | F | F | F | F | T |
| compte de l'utilisateur actif                       | F | F | F | F | F | F | F | F | F | T |
|                                                     |   |   |   |   |   |   |   |   |   |   |
| nombre de tests dans le jeu de tests                | 2 | 2 | 2 | 3 | 1 | 1 | 2 | 1 | 1 | 1 |

Le jeu de test 4 comporte trois tests : non null, non vide, et adresse
courriel bien formée. On aurait pu n'en faire qu'un en considérant la
bibliothèque de validation RFC822 vérifie les deux premières
conditions.

#### Désactiver son compte (HAUTE)

|                                          | 1 | 2 | 3 | 4 |
|:-----------------------------------------|:--|:--|:--|:--|
| pseudo bien formé (non null ∧ non vide)  | F | T | T | T |
| utilisateur avec ce pseudo existant      |   | F | T | T |
| le compte est actif                      |   |   | F | T |
|                                          |   |   |   |   |
| le compte de l'utilisateur est désactivé | F | F | F | T |
|                                          |   |   |   |   |
| nombre de tests dans le jeu de tests     | 2 | 1 | 1 | 1 |

#### Poster un message (HAUTE)

|                                            | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |
|:-------------------------------------------|:--|:--|:--|---|---|---|---|---|---|
| nom réseau bien formé (non null ∧ non vide)| F | T | T | T | T | T | T | T | T |
| pseudo bien formé (non null ∧ non vide)    |   | F | T | T | T | T | T | T | T |
| utilisateur avec ce pseudo existant        |   |   | F | T | T | T | T | T | T |
| réseau avec ce nom existant                |   |   |   | F | T | T | T | T | T |
| le compte utilisateur n'est pas bloqué     |   |   |   |   | F | T | T | T | T |
| message bien formé (non null ∧ non vide)   |   |   |   |   |   | F | T | T | T |
| utilisateur est un membre du réseau        |   |   |   |   |   |   | F | T | T |
| réseau avec ce nom ouvert                  |   |   |   |   |   |   |   | F | T |
|                                            |   |   |   |   |   |   |   |   |   |
| message en attente de traitement par un mod| F | F | F | F | F | F | F | F | T |
|                                            |   |   |   |   |   |   |   |   |   |
| nombre de tests dans le jeu de tests       | 2 | 2 | 1 | 1 | 2 | 2 | 1 | 1 | 1 |

#### Créer un réseau (HAUTE)

|                                                     | 1 | 2 | 3 | 4 | 5 | 6 | 7 |
|:----------------------------------------------------|:--|:--|:--|---|---|---|---|
| pseudo bien formé (non null ∧ non vide)             | F | T | T | T | T | T | T |
| utilisateur avec ce pseudo existant 				|   | F | T | T | T | T | T |
| le compte est actif                                 |   |   | F | T | T | T | T |
| nom réseau bien formé (non null ∧ non vide)         |   |   |   | F | T | T | T |
| pseudo réseau bien formé (non null ∧ non vide)      |   |   |   |   | F | T | T |
| réseau avec ce nom inexistant                       |   |   |   |   |   | F | T |
|                                                     |   |   |   |   |   |   |   |
| réseau existant                                     | F | F | F | F | F | F | T |
| réseau ouvert                                       | F | F | F | F | F | F | T |
| utilisateur est membre du réseau                    | F | F | F | F | F | F | T |
| utilisateur est modérateur du réseau                | F | F | F | F | F | F | T |
|                                                     |   |   |   |   |   |   |   |
| nombre de tests dans le jeu de tests                | 2 | 1 | 1 | 2 | 2 | 1 | 1 |

#### Modérer un message (MOYENNE)

|                                            | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 | 12 |
|:-------------------------------------------|:--|:--|:--|---|---|---|---|---|---|---|---|---|
| pseudo bien formé (non null ∧ non vide)    | F | T | T | T | T | T | T | T | T |T | T | T |
| utilisateur avec ce pseudo existant        |   | F | T | T | T | T | T | T | T |T | T | T |
| le compte utilisateur est actif            |   |   | F | T | T | T | T | T | T |T | T | T |
| nom réseau bien formé (non null ∧ non vide)|   |   |   | F | T | T | T | T | T |T | T | T |
| réseau avec ce nom existant                |   |   |   |   | F | T | T | T | T |T | T | T |
| réseau avec ce nom ouvert                  |   |   |   |   |   | F | T | T | T |T | T | T |
| utilisateur est un membre du réseau        |   |   |   |   |   |   | F | T | T |T | T | T |
| utilisateur est modérateur du réseau       |   |   |   |   |   |   |   | F | T |T | T | T |
| message fait partie du réseau              |   |   |   |   |   |   |   |   | F |T | T | T |
| message en attente de modération           |   |   |   |   |   |   |   |   |   |F | T | T |
| état demandé est ACCEPTE ou REFUSE         |   |   |   |   |   |   |   |   |   |  | F | T |
|                                            |   |   |   |   |   |   |   |   |   |
| état du message est ACCEPTE ou REFUSE      | F | F | F | F | F | F | F | F | F | F | F | T |
|                                            |   |   |   |   |   |   |   |   |   |
| nombre de tests dans le jeu de tests       | 2 | 1 | 1 | 2 | 1 | 1 | 1 | 1 | 1 | 1 | 1 | 1 |


# 3. Conception

## 3.1. Listes des classes candidates et de leurs attributs

Voici les listes des classes candidates et de leurs attributs:
- `MiniSocs` (mise en œuvre du patron de conception Façade) avec
  l'attribut `nom` pour le nom du système,
- `Utilisateur` avec les attributs `pseudo` pour identifier de manière
  unique un utilisateur, `nom` et `prénom`, adresse `courriel`, et
  `etatCompte` pour l'état de son compte,
- `ÉtatCompte` avec les énumérateurs `ACTIF`, `DÉSACTIVÉ` et `BLOQUÉ`

## 3.2. Premières opérations des classes

Les seules opérations que nous connaissons déjà sont celles
correspondant aux cas d'utilisation. Comme nous utilisons le patron de
conception Façade, toutes les opérations des cas d'utilisation sont
dans la Façade.

Donc, dans la classe `MiniSocs`, voici les premières opérations (en
ignorant celles de priorité « basse ») :
- `ajouterUtilisateur`,
- `désactiverCompte`,
- `bloquerCompte`,
- `listerUtilisateurs`.

## 3.3. Diagramme de classes

Le diagramme de classes obtenu lors d'une analyse à partir de l'énoncé
du problème est donné dans la figure qui suit. Dans ces diagrammes,
les opérations ne sont pas mentionnées parce qu'il y en aurait trop.

**Important: même dans les diagrammes de la conception détaillée, on
ne montre pas les attributs traduisant des associations.**

Pour rappel, la documentation du langage pour écrire les diagrammes
UML avec PlantUML est disponible à l'adress suivante :
- (https://plantuml.com/fr/)

Version sans les notifications
([source](./Diagrammes/minisocs_uml_diag_classes_sans_notif.pu)).

![diagrammeclasses](./Diagrammes/minisocs_uml_diag_classes_sans_notif.svg)
([source](./Diagrammes/minisocs_uml_diag_classes_sans_notif.pu))

## 3.4. Diagrammes de séquence

Dans la suite, plusieurs versions d'un même diagramme de séquence sont proposés :
- une version dite « recommandée » **avec** les barres d'activation,
- une version dite « simplifiée » **sans** les barres d'activation.

Pour rappel, la documentation du langage pour écrire les diagrammes
UML avec PlantUML est disponible à l'adress suivante :
- (https://plantuml.com/fr/)

#### Ajouter un utilisateur (HAUTE)

Version recommandée
([source](./Diagrammes/minisocs_uml_diag_seq_ajouter_utilisateur.pu)).

![diagrammeséquenceajouterutilisateur](./Diagrammes/minisocs_uml_diag_seq_ajouter_utilisateur.svg)
([source](./Diagrammes/minisocs_uml_diag_seq_ajouter_utilisateur.pu))

#### Poster un message (HAUTE)

Version recommandée
([source](./Diagrammes/minisocs_uml_diag_seq_poster_message.pu)).

![diagrammeséquencepostermessage](./Diagrammes/minisocs_uml_diag_seq_poster_message.svg)
([source](./Diagrammes/minisocs_uml_diag_seq_poster_message.pu))

#### Créer un réseau social (HAUTE)

Version recommandée
([source](./Diagrammes/minisocs_uml_diag_seq_creer_reseau.pu)).

![diagrammeséquencecréerréseau](./Diagrammes/minisocs_uml_diag_seq_creer_reseau.svg)
([source](./Diagrammes/minisocs_uml_diag_seq_creer_reseau.pu))

#### Ajouter un membre (HAUTE)

Version recommandée
([source](./Diagrammes/minisocs_uml_diag_seq_ajouter_membre.pu)).

![diagrammeséquenceajouterutilisateur](./Diagrammes/minisocs_uml_diag_seq_ajouter_membre.svg)
([source](./Diagrammes/minisocs_uml_diag_seq_ajouter_membre.pu))






# 7. Diagrammes de machine à états et invariants

Dans les diagrammes de machine à états, nous faisons le choix de faire
apparaître les états de création et de destruction. Ces états sont
transitoires, il est vrai, mais ils méritent cependant une attention
particulière.  L'état de création, en particulier, donne lieu, lors de
la réalisation dans un langage de programmation orienté objet, à
l'écriture d'une opération « constructeur » qui garantit que
tous les attributs sont initialisés correctement dès la création d'une
instance. Nous savons également qu'en Java la destruction se réalise
en « oubliant » l'objet : un mécanisme de ramasse
miettes détruit automatiquement les objets lorsqu'ils ne sont plus
référencés. Il n'en est pas de même dans tous les langages, et par
exemple en C++ qui ne possède pas de mécanisme de ramasse miettes, la
destruction des objets peut s'avérer un casse tête ardu.

Les actions provoquées par des appels en provenance d'autres objets
apparaissent sur les transitions. Nous avons gardé comme action
interne uniquement les actions correspondant à des appels que l'objet
fait seul ou fait de manière répétitive.  Les constructeurs et
destructeurs sont des exceptions (ils apparaissent en interne bien
qu'étant déclenchés par un autre objet).

## 7.1. Classe Utilisateur

### 7.1.1. Diagramme de machine à états

Diagramme ([source](./Diagrammes/minisocs_uml_diag_machine_a_etats_utilisateur.pu)).

![diagrammemachineaétatsutilisateur](./Diagrammes/minisocs_uml_diag_machine_a_etats_utilisateur.svg)
([source](./Diagrammes/minisocs_uml_diag_machine_a_etats_utilisateur.pu))

### 7.1.2. Fiche de la classe

Voici tous les attributs de la classe :
```
— final String pseudonyme
— String nom
— String prenom
— String courriel
— EtatCompte etatCompte
```

### 7.1.3. Invariant

```
  pseudonyme != null ∧ !pseudonyme.isBlank()
∧ nom != null ∧ !nom.isBlank()
∧ prenom != null ∧ !prenom.isBlank()
∧ EmailValidator.getInstance().isValid(courriel)
∧ etatCompte != null
```
## 7.2. Classe Message

### 7.2.1. Diagramme de machine à états

Diagramme ([source](./Diagrammes/minisocs_uml_diag_machine_a_etats_message.pu)).

![diagrammemachineaétatsmessage](./Diagrammes/minisocs_uml_diag_machine_a_etats_message.svg)
([source](./Diagrammes/minisocs_uml_diag_machine_a_etats_message.pu))

### 7.2.2. Fiche de la classe

Voici tous les attributs de la classe :
```
— final int idMessage
— String contenu
— etatMessage EtatMessage 
— boolean cache
— reseauSocial ReseauSocial
— membre Membre

Voici toute les méthodes de la classe : 
# constructeur()
+ modérer()
+ cacher()
+ visible()
+ setId()
+ setContent()
+ initEtatMessage()
+ envoyerVersListeAttente()
+ getId() : int
+ getContent : String
+ getEtatMessage : EtatMessage
+ getMembre() : Membre
# destructeur

```

### 7.2.3. Invariant

```
  (cache ⇒ etatMessage==ACCEPTE)
∧ contenu != null ∧ !contenu.isBlank()
∧ idMessage != null
∧ reseauSocial != null
∧ membre != null
```

# 8 Préparation des tests unitaires

## 8.1. Opérations de la classe Utilisateur

### Opération constructeur

|                                              | 1   | 2   | 3   | 4   | 5   |
|:---------------------------------------------|:----|:----|:----|:----|:----|
| pseudonyme bien formé (non null ∧ non vide)  | F   | T   | T   | T   | T   |
| nom bien formé (non null ∧ non vide)         |     | F   | T   | T   | T   |
| prénom bien formé  (non null ∧ non vide)     |     |     | F   | T   | T   |
| courriel bien formé selon le standard RFC822 |     |     |     | F   | T   |
|                                              |     |     |     |     |     |
| pseudonyme' = pseudonyme                     | F   | F   | F   | F   | T   |
| nom' = nom                                   | F   | F   | F   | F   | T   |
| prénom' = prénom                             | F   | F   | F   | F   | T   |
| courriel' = courriel                         | F   | F   | F   | F   | T   |
| étatCompte' = actif                          | F   | F   | F   | F   | T   |
|                                              |     |     |     |     |     |
| levée d'un exception                         | oui | oui | oui | oui | non |
|                                              |     |     |     |     |     |
| nombre de tests dans le jeu de tests         | 2   | 2   | 2   | 3   | 1   |

Trois tests dans le jeu de tests 4 pour non null, puis non vide, et
enfin une chaîne de caractères qui n'est pas une adresse courriel.

### Opération désactiverCompte

|                                      | 1   | 2   |
|:-------------------------------------|:----|:----|
| étatCompte = actif                   | F   | T   |
|                                      |     |     |
| étatCompte' = désactivé              |     | T   |
|                                      |     |     |
| levée d'une exception                | oui | non |
|                                      |     |     |
| nombre de tests dans le jeu de tests | 1   | 2   |

Deux tests dans le jeu de tests 2 pour l'idempotence.


## 8.2. Opérations de la classe Message

### Opération constructeur

|                                                  | 1   | 2   | 3   |
|:-------------------------------------------------|:----|:----|:----|
| pseudo bien formé (non null ∧ non vide)          |  F  |  T  |  T  |
| message bien formé (contenu non null ∧ non vide) |     |  F  |  T  |
|                                                  |     |     |     |
| idMessage non null                               |  F  |  F  |  T  |
| contenu' = contenu                               |  F  |  F  |  T  |
| etatMessage = ATTENTE                            |  F  |  F  |  T  |
|                                                  |     |     |     |
| levée d'une exception                            | oui | oui | non |
|                                                  |     |     |     |
| nombre de tests dans le jeu de tests             |  2  |  2  |  1  |

### Opération modérer()
 
|                                      | 1   | 2   | 3   | 4   | 5   | 6   | 7   | 8   | 9   | 10  |
|:-------------------------------------|:----|:----|:----|:----|:----|:----|:----|:----|:----|:----|
| contenu != null ∧ !contenu.isBlank() | F   | T   | T   | T   | T   | T   | T   | T   | T   | T   |
| reseauSocial != null                 |     | F   | T   | T   | T   | T   | T   | T   | T   | T   |
| idMessage != null                    |     |     | F   | T   | T   | T   | T   | T   | T   | T   |
| membre != null                       |     |     |     | F   | T   | T   | T   | T   | T   | T   |
| membre appartient au réseau          |     |     |     |     | F   | T   | T   | T   | T   | T   |
| réseau est ouvert                    |     |     |     |     |     | F   | T   | T   | T   | T   |
| etatMessage = ATTENTE                |     |     |     |     |     |     | F   | T   | T   | T   |
| acteur appartient au réseau          |     |     |     |     |     |     |     | F   | T   | T   |
| acteur est modérateur ou administrateur du réseau|     |     |     |     |     |     |     |     | F   | T   |
|                                      |     |     |     |     |     |     |     |     |     |     |
| etatMessage = ACCEPTE ∨ REFUSE       | F   | F   | F   | F   | F   | F   | F   | F   | F   | T   |
|                                      |     |     |     |     |     |     |     |     |     |     |
| levée d'une exception                | oui | oui | oui | oui | oui | oui | oui | oui | oui | non |
|                                      |     |     |     |     |     |     |     |     |     |     |
| nombre de tests dans le jeu de tests | 2   | 2   | 1   | 2   | 1   | 1   | 1   | 1   | 1   | 1   |

---
FIN DU DOCUMENT
