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
		assert invariant();
	}
	
	/**
	 * vérifie l'invariant de la classe.
	 * 
	 * @return {@code true} si l'invariant est respecté.
	 */
	public boolean invariant() {
		return nomReseau != null && !nomReseau.isBlank() && etatReseau!=null;
	}
	
	/**
	 * obtient le nom du réseau.
	 * 
	 * @return le nom du réseau.
	 */
	public String getNomReseau() {
		return nomReseau;
	}
	
	/**
	 * l'état du réseau.
	 * 
	 * @return l'énumérateur.
	 */
	public EtatReseau getEtatReseau() {
		return etatReseau;
	}
	
	/**
	 * rend inactif le compte de l'utilisateur. L'opération est
	 * idempotente. L'opération est refusée si le compte n'est pas actif.
	 */
	public void fermerReseau() {
		if (etatReseau.equals(EtatReseau.FERME)) {
			return;
		}
		if (!etatReseau.equals(EtatReseau.OUVERT)) {
			throw new IllegalStateException("le réseau n'est pas ouvert");
		}
		this.etatReseau = EtatReseau.FERME;
		assert invariant();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(nomReseau);
	}
	
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ReseauSocial)) {
			return false;
		}
		ReseauSocial other = (ReseauSocial) obj;
		return Objects.equals(nomReseau, other.nomReseau);
	}

	@Override
	public String toString() {
		return "ReseauSocial [nomReseau=" + nomReseau + ", etatReseau=" + etatReseau + "]";
	}

}
