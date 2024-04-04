// CHECKSTYLE:OFF

package eu.telecomsudparis.csc4102.minisocs.validation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.minisocs.MiniSocs;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

class TestModererMessage {
	private MiniSocs miniSocs;
	private String nomReseau;
	private String pseudoMod;
	private String pseudoMem;
	private String pseudoReseau;
	private String contenu;
	
	@BeforeEach
	void setUp() throws OperationImpossible {
		miniSocs = new MiniSocs("MiniSocs");
		pseudoMod = "pseudoMod";
		pseudoMem = "pseudoMem";
		pseudoReseau = "pseudoReseau";
		nomReseau = "nomReseau";
		contenu = "contenu";
		miniSocs.ajouterUtilisateur(pseudoMod, "nom1", "prenom1", "courriel1@gmail.com");
		miniSocs.ajouterUtilisateur(pseudoMem, "nom2", "prenom2", "courriel2@gmail.com");
		miniSocs.creerReseauSocial(pseudoMod, nomReseau, "pseudoReseauMod");
		miniSocs.ajouterMembre(pseudoMod, pseudoMem, pseudoReseau, nomReseau);
		miniSocs.posterMessage(pseudoMem, nomReseau, contenu);
	}
	
	@AfterEach
	void tearDown() {
		miniSocs = null;
		pseudoMod = null;
		pseudoMem = null;
		pseudoReseau = null;
		nomReseau = null;
		contenu = null;
	}

	@Test
	@DisplayName("pseudo modérateur est null")
	void modererMessageTest1Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.modererMessage(pseudoMod, pseudoMem, pseudoReseau, null));
	}
	

}
