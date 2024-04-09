// CHECKSTYLE:OFF

package eu.telecomsudparis.csc4102.minisocs.validation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.minisocs.EtatMessage;
import eu.telecomsudparis.csc4102.minisocs.Message;
import eu.telecomsudparis.csc4102.minisocs.MiniSocs;
import eu.telecomsudparis.csc4102.minisocs.ReseauSocial;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

class TestModererMessage {
	private MiniSocs miniSocs;
	private String nomReseau;
	private String pseudoMod;
	private String pseudoMem;
	private String pseudoReseau;
	private String contenu;
	private String instant;
	private Message message;
	
	@BeforeEach
	void setUp() throws OperationImpossible {
		miniSocs = new MiniSocs("MiniSocs");
		pseudoMod = "pseudoMod";
		pseudoMem = "pseudoMem";
		pseudoReseau = "pseudoReseau";
		nomReseau = "nomReseau";
		contenu = "contenu";
		instant = miniSocs.getInstant();
		miniSocs.ajouterUtilisateur(pseudoMod, "nom1", "prenom1", "courriel1@gmail.com");
		miniSocs.ajouterUtilisateur(pseudoMem, "nom2", "prenom2", "courriel2@gmail.com");
		miniSocs.creerReseauSocial(pseudoMod, nomReseau, "pseudoReseauMod");
		miniSocs.ajouterMembre(pseudoMod, pseudoMem, pseudoReseau, nomReseau);
		miniSocs.ajouterUtilisateur("pseudoUtilisateur", "nom3", "prenom3", "courriel3@gmail.com");
		miniSocs.posterMessage(pseudoMem, nomReseau, contenu, instant);
		message = miniSocs.getReseaux().get(nomReseau).getMessages().get(pseudoMem+instant);
				}
	
	@AfterEach
	void tearDown() {
		miniSocs = null;
		pseudoMod = null;
		pseudoMem = null;
		pseudoReseau = null;
		nomReseau = null;
		contenu = null;
		instant = null;
		message = null;
	}

	@Test
	@DisplayName("pseudo modérateur est null")
	void modererMessageTest1Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.modererMessage(null, nomReseau, message, EtatMessage.ACCEPTE));
	}
	
	@Test
	@DisplayName("pseudo modérateur est vide")
	void modererMessageTest1Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.modererMessage("", nomReseau, message, EtatMessage.ACCEPTE));
	}
	
	@Test
	@DisplayName("modérateur inexistant")
	void modererMessageTest2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.modererMessage("fauxutilisateur", nomReseau, message, EtatMessage.ACCEPTE));
	}
	
	@Test
	@DisplayName("compte modérateur inactif")
	void modererMessageTest3() throws Exception {
		
		miniSocs.getUtilisateurs().get(pseudoMod).desactiverCompte();
		
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.modererMessage(pseudoMod, nomReseau, message, EtatMessage.ACCEPTE));
	}

	@Test
	@DisplayName("nom réseau est null")
	void modererMessageTest4Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.modererMessage(pseudoMod, null, message, EtatMessage.ACCEPTE));
	}

	@Test
	@DisplayName("nom réseau est vide")
	void modererMessageTest4Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.modererMessage(pseudoMod, "", message, EtatMessage.ACCEPTE));
	}

	@Test
	@DisplayName("réseau inexistant")
	void modererMessageTest5() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.modererMessage(pseudoMod, "fauxreseau", message, EtatMessage.ACCEPTE));
	}
	
	@Test
	@DisplayName("réseau fermé")
	void modererMessageTest6() throws Exception {
		
		ReseauSocial rs = miniSocs.getReseaux().get(nomReseau);
		rs.fermerReseau();
		
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.modererMessage(pseudoMod, nomReseau, message, EtatMessage.ACCEPTE));
	}
	
	@Test
	@DisplayName("utilisateur supposé modérateur ne fait pas partie du réseau")
	void modererMessageTest7() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.modererMessage("pseudoUtilisateur", nomReseau, message, EtatMessage.ACCEPTE));
	}
	
	@Test
	@DisplayName("utilisateur supposé modérateur n'est pas modérateur")
	void modererMessageTest8() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.modererMessage(pseudoMem, nomReseau, message, EtatMessage.ACCEPTE));
	}
	
	@Test
	@DisplayName("message ne fait pas partie du réseau")
	void modererMessageTest9() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.modererMessage();
	}
}
