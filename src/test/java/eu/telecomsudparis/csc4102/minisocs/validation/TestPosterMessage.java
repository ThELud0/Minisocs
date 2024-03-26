// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.minisocs.validation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.minisocs.EtatCompte;
import eu.telecomsudparis.csc4102.minisocs.EtatMessage;
import eu.telecomsudparis.csc4102.minisocs.EtatReseau;
import eu.telecomsudparis.csc4102.minisocs.Message;
import eu.telecomsudparis.csc4102.minisocs.MiniSocs;
import eu.telecomsudparis.csc4102.minisocs.ReseauSocial;
import eu.telecomsudparis.csc4102.minisocs.Utilisateur;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

class TestPosterMessage {
	private MiniSocs miniSocs;
	private String nomReseau;
	private String pseudoMod;
	private String pseudoMem;

	@BeforeEach
	void setUp() throws OperationImpossible {
		miniSocs = new MiniSocs("MiniSocs");
		pseudoMod = "pseudoMod";
		pseudoMem = "pseudoMem";
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
		nomReseau = null;
	}

	@Test
	@DisplayName("nom du réseau est null")
	void posterMessageTest1Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class, () -> miniSocs.posterMessage(pseudoMod, null, "contenu"));
	}

	@Test
	@DisplayName("nom du réseau est vide")
	void posterMessageTest1Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class, () -> miniSocs.posterMessage(pseudoMod, "", "contenu"));
	}

	@Test
	@DisplayName("pseudo de l'utilisateur est null")
	void posterMessageTest2Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class, () -> miniSocs.posterMessage(null, nomReseau, "contenu"));
	}

	@Test
	@DisplayName("pseudo de l'utilisateur est vide")
	void posterMessageTest2Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class, () -> miniSocs.posterMessage("", nomReseau, "contenu"));
	}

	@Test
	@DisplayName("utilisateur non existant")
	void posterMessageTest3() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.posterMessage("pseudo2", nomReseau, "contenu"));
	}

	@Test
	@DisplayName("réseau non existant")
	void posterMessageTest4() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.posterMessage(pseudoMod, "nomReseau2", "contenu"));
	}

	@Test
	@DisplayName("compte utilisateur non actif")
	void posterMessageTest5() throws Exception {
		try {
			miniSocs.getUtilisateurs().get(pseudoMod).desactiverCompte();
		} catch (IllegalStateException e) {
			Assertions.fail();
		}
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.posterMessage(pseudoMod, nomReseau, "contenu"));
	}

	@Test
	@DisplayName("contenu du message est null")
	void posterMessageTest6Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class, () -> miniSocs.posterMessage(pseudoMod, nomReseau, null));
	}

	@Test
	@DisplayName("contenu du message est vide")
	void posterMessageTest6Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class, () -> miniSocs.posterMessage(pseudoMem, nomReseau, ""));
	}

	@Test
	@DisplayName("l'utilsateur n'est pas membre du réseau")
	void posterMessageTest7() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.posterMessage(pseudoMem, nomReseau, "contenu"));
	}

	@Test
	@DisplayName("réseau n'est pas ouvert")
	void posterMessageTest8J() throws Exception {

		ReseauSocial rs = miniSocs.getReseaux().get(nomReseau);
		rs.fermerReseau();

		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.posterMessage(pseudoMod, nomReseau, "contenu"));
	}

	@Test
	@DisplayName("postconditions respectées et test si le message existe déjà")
	void posterMessageTest9et10() throws Exception {
		// lorsqu'un modérateur poste le message
		String idMessage = "";
		miniSocs.posterMessage(pseudoMod, nomReseau, "contenu");
		for (String i : miniSocs.getUtilisateurs().get(pseudoMod).getMembres().get(nomReseau).getMessages().keySet()) {
			idMessage = i;
		}
		Assertions.assertTrue(miniSocs.getUtilisateurs().get(pseudoMod).getMembres().get(nomReseau).getMessages()
				.get(idMessage).getEtatMessage() == EtatMessage.ACCEPTE);

		// lorsqu'un membre simple poste le message
		miniSocs.ajouterMembre(pseudoMod, pseudoMem, "pseudoReseau", nomReseau);
		miniSocs.posterMessage(pseudoMem, nomReseau, "contenu");
		for (String i : miniSocs.getUtilisateurs().get(pseudoMem).getMembres().get(nomReseau).getMessages().keySet()) {
			idMessage = i;
		}
		Assertions.assertTrue(miniSocs.getUtilisateurs().get(pseudoMem).getMembres().get(nomReseau).getMessages()
				.get(idMessage).getEtatMessage() == EtatMessage.ATTENTE);
	}

}
