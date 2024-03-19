package eu.telecomsudparis.csc4102.minisocs.validation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.minisocs.MiniSocs;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

class TestPosterMessage {
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
		pseudoMod=null;
		pseudoMem=null;
		pseudoReseau=null;
		nomReseau = null;
	}

	

}
