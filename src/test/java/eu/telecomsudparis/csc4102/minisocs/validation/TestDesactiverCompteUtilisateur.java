package eu.telecomsudparis.csc4102.minisocs.validation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.minisocs.EtatCompte;
import eu.telecomsudparis.csc4102.minisocs.MiniSocs;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

class TestDesactiverCompteUtilisateur {

	private MiniSocs miniSocs;
	private String pseudo;


	@BeforeEach
	void setUp() throws OperationImpossible {
		miniSocs = new MiniSocs("MiniSocs");
		pseudo = "pseudo";
		miniSocs.ajouterUtilisateur(pseudo, "nom", "prenom", "courriel@gmail.com");
	}
	
	@AfterEach
	void tearDown() {
		miniSocs = null;
		pseudo=null;
	}
	
	@Test
	@DisplayName ("pseudo est null")
	void desactiverCompteUtilisateurTest1Jeu1() {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.desactiverCompteUtilisateur(null));
	}
	
	@Test
	@DisplayName ("pseudo est vide")
	void desactiverCompteUtilisateurTest1Jeu2() {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.desactiverCompteUtilisateur(""));
	}
	@Test
	@DisplayName ("utilisateur est inexistant")
	void desactiverCompteUtilisateurTest2Jeu1() {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.desactiverCompteUtilisateur("inexistant"));
	}
	
	@Test
	@DisplayName ("le compte est bloqué")
	void desactiverCompteUtilisateurTest3Jeu1() {
		miniSocs.getUtilisateurs().get(pseudo).bloquerCompte();
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.desactiverCompteUtilisateur(pseudo));
	}
	
	@Test
	@DisplayName ("le compte utilisateur est désactivé")
	void desactiverCompteUtilisateurTest4Jeu1() throws Exception {
		
		miniSocs.desactiverCompteUtilisateur(pseudo);

		Assertions.assertTrue(miniSocs.getUtilisateurs().get(pseudo).getEtatCompte()==EtatCompte.DESACTIVE);
		
	}
	

}
