package eu.telecomsudparis.csc4102.minisocs;

import java.util.Objects;

import org.apache.commons.validator.routines.EmailValidator;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

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
	
	private boolean moderateur;
	
	public Membre(final Utilisateur utilisateur, final ReseauSocial reseauSocial,final String pseudoReseau){
		if (utilisateur == null || !(utilisateur.invariant())) {
			throw new IllegalArgumentException("utilisateur invalide");
		}
		if (reseauSocial == null || !(reseauSocial.invariant())) {
			throw new IllegalArgumentException("réseau social invalide");
		}
		if (pseudoReseau == null || pseudoReseau.isBlank()) {
			throw new IllegalArgumentException("pseudo pour le réseau ne peut pas être null ou vide");
		}
		
		this.utilisateur = utilisateur;
		this.reseauSocial = reseauSocial;
		this.pseudoReseau = pseudoReseau;
		this.moderateur = false;
		assert invariant();
	}
	
	public void setModerateur() {
		this.moderateur=true;
		return;
	}
	
	public void demote() {
		this.moderateur=false;
		return;
	}
	
	public String getPseudoReseau() {
		return this.pseudoReseau;
	}
	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	
	public ReseauSocial getReseauSocial() {
		return reseauSocial;
	}
	
	public boolean estModerateur() {
		return moderateur;
	}
	
	/**
	 * vérifie l'invariant de la classe.
	 * 
	 * @return {@code true} si l'invariant est respecté.
	 */
	public boolean invariant() {
		return utilisateur != null && reseauSocial != null && utilisateur.invariant() && reseauSocial.invariant()
				&& pseudoReseau != null && !pseudoReseau.isBlank();
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(pseudoReseau);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Membre)) {
			return false;
		}
		Membre other = (Membre) obj;
		return (utilisateur.equals(other.getUtilisateur())&&reseauSocial.equals(other.getReseauSocial())&&pseudoReseau.equals(other.getPseudoReseau()));
	}

	@Override
	public String toString() {
		return "Membre [utilisateur=" + utilisateur + ", reseau social=" + reseauSocial + ", pseudo dans le reseau=" + pseudoReseau + "]";
	}
	
}
