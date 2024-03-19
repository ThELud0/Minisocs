package eu.telecomsudparis.csc4102.minisocs;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.validator.routines.EmailValidator;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette classe réalise le concept d'utilisateur du système, à ne pas confondre
 * avec le concept de membre, sous-entendu à un réséeau social.
 * 
 * @author Denis Conan
 */
public class Utilisateur {
	/**
	 * le pseudonyme de l'utilisateur.
	 */
	private final String pseudonyme;
	/**
	 * le nom de l'utilisateur.
	 */
	private String nom;
	/**
	 * le prénom de l'utilisateur.
	 */
	private String prenom;
	/**
	 * l'adresse courriel de l'utilisateur.
	 */
	private String courriel;
	/**
	 * état du compte de l'utilisateur.
	 */
	private EtatCompte etatCompte;
	
	/**
	 * le string clé du hashmap réfere au nom du réseau social pour lequel l'utilisateur est membre
	 */
	private final Map<String, Membre> membres;

	/**
	 * construit un utilisateur.
	 * 
	 * @param pseudonyme le pseudonyme.
	 * @param nom        le nom.
	 * @param prenom     le prénom.
	 * @param courriel   l'adresse courriel de l'utilisateur.
	 */
	public Utilisateur(final String pseudonyme, final String nom, final String prenom, final String courriel) {
		if (pseudonyme == null || pseudonyme.isBlank()) {
			throw new IllegalArgumentException("pseudonyme ne peut pas être null ou vide");
		}
		if (nom == null || nom.isBlank()) {
			throw new IllegalArgumentException("nom ne peut pas être null ou vide");
		}
		if (prenom == null || prenom.isBlank()) {
			throw new IllegalArgumentException("prenom ne peut pas être null ou vide");
		}
		if (!EmailValidator.getInstance().isValid(courriel)) {
			throw new IllegalArgumentException("courriel ne respecte pas le standard RFC822");
		}
		this.pseudonyme = pseudonyme;
		this.nom = nom;
		this.prenom = prenom;
		this.courriel = courriel;
		this.etatCompte = EtatCompte.ACTIF;
		this.membres = new HashMap<>();
		assert invariant();
	}

	/**
	 * vérifie l'invariant de la classe.
	 * 
	 * @return {@code true} si l'invariant est respecté.
	 */
	public boolean invariant() {
		return pseudonyme != null && !pseudonyme.isBlank() && nom != null && !nom.isBlank() && prenom != null
				&& !prenom.isBlank() && EmailValidator.getInstance().isValid(courriel) && etatCompte != null
				&& membres!=null;
	}

	/**
	 * obtient le pseudonyme.
	 * 
	 * @return le pseudonyme.
	 */
	public String getPseudonyme() {
		return pseudonyme;
	}

	/**
	 * l'état du compte.
	 * 
	 * @return l'énumérateur.
	 */
	public EtatCompte getEtatCompte() {
		return etatCompte;
	}

	/**
	 * rend inactif le compte de l'utilisateur. L'opération est
	 * idempotente. L'opération est refusée si le compte n'est pas actif.
	 */
	public void desactiverCompte() {
		if (etatCompte.equals(EtatCompte.DESACTIVE)) {
			return;
		}
		if (!etatCompte.equals(EtatCompte.ACTIF)) {
			throw new IllegalStateException("le compte n'est pas actif");
		}
		this.etatCompte = EtatCompte.DESACTIVE;
		assert invariant();
	}

	/**
	 * bloque le comte de l'utilisateur. L'opération est idempotente.
	 */
	public void bloquerCompte() {
		this.etatCompte = EtatCompte.BLOQUE;
		assert invariant();
	}
	
	public void ajouterMembre(Membre membre) throws OperationImpossible {
		if (membre == null || !(membre.invariant())) {
			throw new OperationImpossible("membre invalide");
		}
		if (membres.get(membre.getReseauSocial().getNomReseau()) != null) {
			throw new OperationImpossible("utilisateur déjà membre du réseau");
		}
		
		membres.put(membre.getReseauSocial().getNomReseau(), membre);
		
		assert invariant();
	}
	/**
	 * obtient les membres.
	 * 
	 * @return hashmap membres.
	 */
	public Map<String, Membre> getMembres() {
		return membres;
	}

	@Override
	public int hashCode() {
		return Objects.hash(pseudonyme);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Utilisateur)) {
			return false;
		}
		Utilisateur other = (Utilisateur) obj;
		return Objects.equals(pseudonyme, other.pseudonyme);
	}

	@Override
	public String toString() {
		return "Utilisateur [pseudonyme=" + pseudonyme + ", nom=" + nom + ", prenom=" + prenom + ", courriel="
				+ courriel + ", etatCompte=" + etatCompte + "]";
	}
}
