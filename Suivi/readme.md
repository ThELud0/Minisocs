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
