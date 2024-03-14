package eu.telecomsudparis.csc4102.minisocs;

import java.util.Objects;

public class Membre {
	
	/**
	 * l'utilisateur.
	 */
	private final Utilisateur utilisateur;
	/**
	 * le réseau social.
	 */
	private final ReseauSocial reseauSocial;
	/**
	 * le pseudo au sein du réseau.
	 */
	private final String pseudoReseau;
	
	public Membre(final Utilisateur utilisateur, final ReseauSocial reseauSocial,final String pseudoReseau){
	
		
		this.utilisateur = utilisateur;
		this.reseauSocial = reseauSocial;
		this.pseudoReseau = pseudoReseau;
	}
	
}
