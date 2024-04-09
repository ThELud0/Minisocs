// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.minisocs.validation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.minisocs.MiniSocs;
import eu.telecomsudparis.csc4102.minisocs.EtatReseau;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

class TestCreerReseauSocial {
	private MiniSocs miniSocs;
	private String nomReseau;
	private String pseudo;
	private String pseudoReseau;

	@BeforeEach
	void setUp() throws OperationImpossible {
		miniSocs = new MiniSocs("MiniSocs");
		pseudo = "pseudo";
		pseudoReseau = "pseudoReseau";
		nomReseau = "nomReseau";
		miniSocs.ajouterUtilisateur(pseudo, "nom", "prenom", "courriel@gmail.com");
	}

	@AfterEach
	void tearDown() {
		miniSocs = null;
		pseudo = null;
		pseudoReseau = null;
		nomReseau = null;
	}

	@Test
	@DisplayName("pseudo est null")
	void creerReseauSocialTest1Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.creerReseauSocial(null, nomReseau, pseudoReseau));
	}

	@Test
	@DisplayName("pseudo est vide")
	void creerReseauSocialTest1Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.creerReseauSocial("", nomReseau, pseudoReseau));
	}

	@Test
	@DisplayName("utilisateur non existant")
	void creerReseauSocialTest2Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.creerReseauSocial("pseudo2", nomReseau, pseudoReseau));
	}

	@Test
	@DisplayName("compte utilisateur non actif")
	void creerReseauSocialTest3Jeu1() throws Exception {
		try {
			miniSocs.getUtilisateurs().get(pseudo).desactiverCompte();
		} catch (IllegalStateException e) {
			Assertions.fail();
		}
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.creerReseauSocial(pseudo, nomReseau, pseudoReseau));
	}

	@Test
	@DisplayName("nom du reseau est null")
	void creerReseauSocialTest4Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.creerReseauSocial(pseudo, null, pseudoReseau));
	}

	@Test
	@DisplayName("nom du reseau est vide")
	void creerReseauSocialTest4Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class, () -> miniSocs.creerReseauSocial(pseudo, "", pseudoReseau));
	}

	@Test
	@DisplayName("pseudo pour le reseau est null")
	void creerReseauSocialTest5Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class, () -> miniSocs.creerReseauSocial(pseudo, nomReseau, null));
	}

	@Test
	@DisplayName("pseudo pour le reseau est vide")
	void creerReseauSocialTest5Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class, () -> miniSocs.creerReseauSocial(pseudo, nomReseau, ""));
	}

	@Test
	@DisplayName("postconditions respectées et test si le réseau existe déjà")
	void creerReseauSocialTest6Et7() throws Exception {
		Assertions.assertTrue(miniSocs.getReseaux().isEmpty());
		miniSocs.creerReseauSocial(pseudo, nomReseau, pseudoReseau);
		Assertions.assertTrue(miniSocs.getReseaux().get(nomReseau) != null);
		Assertions.assertTrue(miniSocs.getReseaux().get(nomReseau).getEtatReseau() == EtatReseau.OUVERT);
		Assertions.assertTrue(miniSocs.getReseaux().get(nomReseau).getMembres().get(pseudoReseau) != null);
		Assertions.assertTrue(miniSocs.getUtilisateurs().get(pseudo).getMembres().get(nomReseau) != null);
		Assertions.assertTrue(miniSocs.getUtilisateurs().get(pseudo).getMembres().get(nomReseau)
				.equals(miniSocs.getReseaux().get(nomReseau).getMembres().get(pseudoReseau)));
		Assertions.assertTrue(miniSocs.getUtilisateurs().get(pseudo).getMembres().get(nomReseau).estModerateur());
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.creerReseauSocial(pseudo, nomReseau, pseudoReseau));
	}

}
