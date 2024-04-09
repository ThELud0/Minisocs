// CHECKSTYLE:OFF

package eu.telecomsudparis.csc4102.minisocs.unitaires;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.minisocs.EtatMessage;
import eu.telecomsudparis.csc4102.minisocs.EtatReseau;
import eu.telecomsudparis.csc4102.minisocs.Message;
import eu.telecomsudparis.csc4102.minisocs.ReseauSocial;

class TestMessage {

	@BeforeEach
	void setUp() {
	}

	@AfterEach
	void tearDown() {
	}

	@DisplayName("contenu ne doit pas être null")
	@Test
	void constructeurMessageTest1Jeu1() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Message(null, "pseudoUtilisateur", "instant"));
	}

	@DisplayName("contenu ne doit pas être vide")
	@Test
	void constructeurReseauSocialTest1Jeu2() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Message("", "pseudoUtilisateur", "instant"));
	}

	@DisplayName("pseudoUtilisateur ne doit pas être null")
	@Test
	void constructeurMessageTest2Jeu1() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Message("contenu", null, "instant"));
	}

	@DisplayName("pseudoUtilisateur ne doit pas être vide")
	@Test
	void constructeurMessageTest2Jeu2() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Message("contenu", "", "instant"));
	}
	
	@DisplayName("instant ne doit pas être null")
	@Test
	void constructeurMessageTest3Jeu1() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Message("contenu", "pseudoUtilisateur", null));
	}

	@DisplayName("instant ne doit pas être vide")
	@Test
	void constructeurReseauSocialTest3Jeu2() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Message("contenu", "pseudoUtilisateur", ""));
	}

	

	@DisplayName("constructeur fonctionne")
	@Test
	void constructeurMessageTest3() {
		Message message = new Message("contenu", "pseudoUtilisateur", "instant");
		Assertions.assertNotNull(message);
		Assertions.assertEquals("contenu", message.getContenu());
		Assertions.assertEquals(EtatMessage.ATTENTE, message.getEtatMessage());
		Assertions.assertNotNull(message.getID());
	}

}
