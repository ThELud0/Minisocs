// CHECKSTYLE:OFF

package eu.telecomsudparis.csc4102.minisocs.unitaires;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.minisocs.EtatReseau;
import eu.telecomsudparis.csc4102.minisocs.ReseauSocial;


class TestReseauSocial {

	@BeforeEach
	void setUp() {
	}

	@AfterEach
	void tearDown() {
	}

	@DisplayName("nomReseau ne doit pas être null")
	@Test
	void constructeurReseauSocialTest1Jeu1() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new ReseauSocial(null));
	}

	@DisplayName("nomReseau ne doit pas être vide")
	@Test
	void constructeurReseauSocialTest1Jeu2() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new ReseauSocial(""));
	}

	@DisplayName("constructeur fonctionne")
	@Test
	void constructeurReseauSocialTest2() {
		final ReseauSocial rs = new ReseauSocial("nomReseau");
		Assertions.assertNotNull(rs);
		Assertions.assertEquals("nomReseau", rs.getNomReseau());
		Assertions.assertEquals(EtatReseau.OUVERT, rs.getEtatReseau());
		Assertions.assertNotNull(rs.getMembres());
		Assertions.assertNotNull(rs.getMessages());
	}

}
