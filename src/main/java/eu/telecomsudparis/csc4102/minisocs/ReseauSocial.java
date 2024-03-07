package eu.telecomsudparis.csc4102.minisocs;

import java.util.Objects;

import org.apache.commons.validator.routines.EmailValidator;

/**
 * Cette classe réalise le concept de réseau social du système
 * 
 * @author Jeanne Villette & Ludovic Hu
 */

public class ReseauSocial {
	/**
	 * le nom du réseau social
	 */
	private final String nomReseau;
	/** 
	 * état du réseau
	 */
	private EtatReseau etatReseau;

	/**
	 * construit un réseau social.
	 * 
	 * @param nomReseau le nom du réseau.
	 */
	public ReseauSocial(final String nomReseau) {
		if (nomReseau == null || nomReseau.isBlank()) {
			throw new IllegalArgumentException("nomReseau ne peut pas être null ou vide");
		}
		this.nomReseau = nomReseau;
		this.etatReseau = EtatReseau.OUVERT;
	}

}
