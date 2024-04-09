// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.minisocs.validation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.minisocs.MiniSocs;
import eu.telecomsudparis.csc4102.minisocs.ReseauSocial;
import eu.telecomsudparis.csc4102.minisocs.EtatReseau;
import eu.telecomsudparis.csc4102.minisocs.EtatCompte;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

class TestAjouterMembre {
	private MiniSocs miniSocs;
	private String nomReseau;
	private String pseudoMod;
	private String pseudoMem;
	private String pseudoReseau;

	@BeforeEach
	void setUp() throws OperationImpossible {
		miniSocs = new MiniSocs("MiniSocs");
		pseudoMod = "pseudoMod";
		pseudoMem = "pseudoMem";
		pseudoReseau = "pseudoReseau";
		nomReseau = "nomReseau";
		miniSocs.ajouterUtilisateur(pseudoMod, "nom1", "prenom1", "courriel1@gmail.com");
		miniSocs.ajouterUtilisateur(pseudoMem, "nom2", "prenom2", "courriel2@gmail.com");
		miniSocs.creerReseauSocial(pseudoMod, nomReseau, "pseudoReseauMod");
	}

	@AfterEach
	void tearDown() {
		miniSocs = null;
		pseudoMod = null;
		pseudoMem = null;
		pseudoReseau = null;
		nomReseau = null;
	}

	@Test
	@DisplayName("nom réseau est null")
	void ajouterMembreTest1Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoMod, pseudoMem, pseudoReseau, null));
	}

	@Test
	@DisplayName("nom réseau est vide")
	void ajouterMembreTest1Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoMod, pseudoMem, pseudoReseau, ""));
	}

	@Test
	@DisplayName("réseau n'existe pas")
	void ajouterMembreTest2Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoMod, pseudoMem, pseudoReseau, "reseau"));
	}

	@Test
	@DisplayName("réseau n'est pas ouvert")
	void ajouterMembreTest3Jeu1() throws Exception {

		ReseauSocial rs = miniSocs.getReseaux().get(nomReseau);
		rs.fermerReseau();

		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoMod, pseudoMem, pseudoReseau, nomReseau));
	}

	@Test
	@DisplayName("pseudo membre est null")
	void ajouterMembreTest4Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoMod, null, pseudoReseau, nomReseau));
	}

	@Test
	@DisplayName("pseudo membre est vide")
	void ajouterMembreTest4Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoMod, "", pseudoReseau, nomReseau));
	}

	@Test
	@DisplayName("utilisateur membre inexistant")
	void ajouterMembreTest5Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoMod, "fauxmembre", pseudoReseau, nomReseau));
	}

	@Test
	@DisplayName("compte utilisateur membre non actif")
	void ajouterMembreTest7Jeu1() throws Exception {

		miniSocs.getUtilisateurs().get(pseudoMem).desactiverCompte();

		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoMod, pseudoMem, pseudoReseau, nomReseau));
	}

	@Test
	@DisplayName("pseudo modérateur est null")
	void ajouterMembreTest8Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(null, pseudoMem, pseudoReseau, nomReseau));
	}

	@Test
	@DisplayName("pseudo modérateur est vide")
	void ajouterMembreTest8Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre("", pseudoMem, pseudoReseau, nomReseau));
	}

	@Test
	@DisplayName("utilisateur modérateur inexistant")
	void ajouterMembreTest9Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre("fauxmod", pseudoMem, pseudoReseau, nomReseau));
	}

	@Test
	@DisplayName("compte utilisateur moderateur non actif")
	void ajouterMembreTest10Jeu1() throws Exception {

		miniSocs.getUtilisateurs().get(pseudoMod).desactiverCompte();

		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoMod, pseudoMem, pseudoReseau, nomReseau));
	}

	@Test
	@DisplayName("utilisateur supposé modérateur ne fait pas partie du réseau")
	void ajouterMembreTest11Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoMem, pseudoMem, pseudoReseau, nomReseau));
	}

	@Test
	@DisplayName("utilisateur supposé modérateur n'est pas modérateur")
	void ajouterMembreTest12Jeu1() throws Exception {

		miniSocs.getUtilisateurs().get(pseudoMod).getMembres().get(nomReseau).demote();

		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoMod, pseudoMem, pseudoReseau, nomReseau));
	}

	@Test
	@DisplayName("pseudo choisi pour le réseau est null")
	void ajouterMembreTest13Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoMod, pseudoMem, null, nomReseau));
	}

	@Test
	@DisplayName("pseudo choisi pour le réseau est vide")
	void ajouterMembreTest13Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoMod, pseudoMem, "", nomReseau));
	}

	@Test
	@DisplayName("pseudo choisi pour le réseau est déjà pris")
	void ajouterMembreTest14Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoMod, pseudoMem, "pseudoReseauMod", nomReseau));
	}

	@Test
	@DisplayName("membre créé et ajouté, puis test ajout d'un membre déjà présent")
	void creerReseauSocialTest6Et7() throws Exception {

		miniSocs.ajouterMembre(pseudoMod, pseudoMem, pseudoReseau, nomReseau);

		Assertions.assertTrue(miniSocs.getReseaux().get(nomReseau).getMembres().get(pseudoReseau) != null);
		Assertions.assertTrue(miniSocs.getUtilisateurs().get(pseudoMem).getMembres().get(nomReseau) != null);
		Assertions.assertTrue(miniSocs.getUtilisateurs().get(pseudoMem).getMembres().get(nomReseau)
				.equals(miniSocs.getReseaux().get(nomReseau).getMembres().get(pseudoReseau)));
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterMembre(pseudoMod, pseudoMem, pseudoReseau, nomReseau));

	}

	/* TEST 6 membre fait déja parti du réseau à faire à la fin */
}
