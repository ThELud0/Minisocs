package eu.telecomsudparis.csc4102.minisocs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.validator.routines.EmailValidator;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette classe est la façade du logiciel.
 * 
 * @author Denis Conan
 */
public class MiniSocs {
	/**
	 * le nom du système.
	 */
	private final String nomDuSysteme;
	/**
	 * les utilisateurs.
	 */
	private final Map<String, Utilisateur> utilisateurs;
	/**
	 * les réseaux.
	 */
	private final Map<String, ReseauSocial> reseaux;
	
	/**
	 * construit une instance du système.
	 * 
	 * @param nomDuSysteme le nom.
	 */
	public MiniSocs(final String nomDuSysteme) {
		this.nomDuSysteme = nomDuSysteme;
		this.utilisateurs = new HashMap<>();
		this.reseaux = new HashMap<>();
	}

	/**
	 * l'invariant de la façade.
	 * 
	 * @return {@code true} si l'invariant est respecté.
	 */
	public boolean invariant() {
		return nomDuSysteme != null && !nomDuSysteme.isBlank() && utilisateurs != null && reseaux != null;
	}

	/**
	 * ajoute un utilisateur.
	 * 
	 * @param pseudo   le pseudo de l'utilisateur.
	 * @param nom      le nom de l'utilisateur.
	 * @param prenom   le prénom de l'utilisateur.
	 * @param courriel le courriel de l'utilisateur.
	 * @throws OperationImpossible en cas de problème sur les pré-conditions.
	 */
	public void ajouterUtilisateur(final String pseudo, final String nom, final String prenom, final String courriel)
			throws OperationImpossible {
		if (pseudo == null || pseudo.isBlank()) {
			throw new OperationImpossible("pseudo ne peut pas être null ou vide");
		}
		if (nom == null || nom.isBlank()) {
			throw new OperationImpossible("nom ne peut pas être null ou vide");
		}
		if (prenom == null || prenom.isBlank()) {
			throw new OperationImpossible("prenom ne peut pas être null ou vide");
		}
		if (courriel == null || courriel.isBlank()) {
			throw new OperationImpossible("courriel ne peut pas être null ou vide");
		}
		if (!EmailValidator.getInstance().isValid(courriel)) {
			throw new OperationImpossible("courriel ne respecte pas le standard RFC822");
		}
		Utilisateur u = utilisateurs.get(pseudo);
		if (u != null) {
			throw new OperationImpossible(pseudo + "déjà un utilisateur");
		}
		utilisateurs.put(pseudo, new Utilisateur(pseudo, nom, prenom, courriel));
		assert invariant();
	}

	/**
	 * liste les utilisateurs.
	 * 
	 * @return la liste des pseudonymes des utilisateurs.
	 */
	public List<String> listerUtilisateurs() {
		return utilisateurs.values().stream().map(Utilisateur::toString).toList();
	}

	/**
	 * désactiver son compte utilisateur.
	 * 
	 * @param pseudo le pseudo de l'utilisateur.
	 * @throws OperationImpossible en cas de problèmes sur les pré-conditions.
	 */
	public void desactiverCompteUtilisateur(final String pseudo) throws OperationImpossible {
		if (pseudo == null || pseudo.isBlank()) {
			throw new OperationImpossible("pseudo ne peut pas être null ou vide");
		}
		Utilisateur u = utilisateurs.get(pseudo);
		if (u == null) {
			throw new OperationImpossible("utilisateur inexistant avec ce pseudo (" + pseudo + ")");
		}
		if (u.getEtatCompte().equals(EtatCompte.BLOQUE)) {
			throw new OperationImpossible("le compte est bloqué");
		}
		u.desactiverCompte();
		assert invariant();
	}
	/**
	 * créer un réseau social.
	 * 
	 * @param nomReseau le nom du réseau.
	 * @param pseudo le nom de l'utilisateur qui crée le réseau
	 * @param pseudoReseau le pseudo affiché dans le réseau par l'utilisateur
	 * @throws OperationImpossible en cas de problèmes sur les pré-conditions.
	 */
	public void creerReseauSocial(final String pseudo, final String nomReseau, final String pseudoReseau) throws OperationImpossible {
		if (pseudo == null || pseudo.isBlank()) {
			throw new OperationImpossible("pseudo de l'utilisateur ne peut pas être null ou vide");
		}
		if (pseudoReseau == null || pseudoReseau.isBlank()) {
			throw new OperationImpossible("pseudo pour le réseau ne peut pas être null ou vide");
		}
		Utilisateur u = utilisateurs.get(pseudo);
		if (u == null) {
			throw new OperationImpossible("utilisateur inexistant avec ce pseudo (" + pseudo + ")");
		}
		if (u.getEtatCompte() != EtatCompte.ACTIF) {
			throw new OperationImpossible("compte utilisateur avec ce pseudo (" + pseudo + ") n'est pas actif");
		}
		if (nomReseau == null || nomReseau.isBlank()) {
			throw new OperationImpossible("nom du réseau ne peut pas être null ou vide");
		}
		ReseauSocial rs = reseaux.get(nomReseau);
		if (rs != null) {
			throw new OperationImpossible(nomReseau + "est déjà un réseau");
		}
		
		reseaux.put(nomReseau, new ReseauSocial(nomReseau));
		
		Membre m = new Membre(u,reseaux.get(nomReseau),pseudoReseau);
		reseaux.get(nomReseau).ajouterMembre(m);
		u.ajouterMembre(m);
		m.setModerateur();
		
		assert invariant();
		
	}
	
	/**
	 * ajoute un membre à un réseau social sous un pseudo choisi.
	 * 
	 * @param pseudoMod   le pseudo de l'utilisateur qui ajoute.
	 * @param pseudoMem   le pseudo de l'utilisateur ajouté.
	 * @param pseudoReseau le pseudo sous lequel le membre est ajouté dans le réseau.
	 * @param nomReseau   le nom du réseau social.
	 * @throws OperationImpossible en cas de problème sur les pré-conditions.
	 */
	public void ajouterMembre(String pseudoMod, String pseudoMem, String pseudoReseau, String nomReseau) throws OperationImpossible{
		if (nomReseau == null || nomReseau.isBlank()) {
			throw new OperationImpossible("nom du réseau ne peut pas être null ou vide");
		}
		ReseauSocial rs = reseaux.get(nomReseau);
		if (rs == null) {
			throw new OperationImpossible(nomReseau + "est un réseau inexistant");
		}
		if (rs.getEtatReseau() != EtatReseau.OUVERT) {
			throw new OperationImpossible(nomReseau + "n'est pas ouvert");
		}
		if (pseudoMem == null || pseudoMem.isBlank()) {
			throw new OperationImpossible("pseudo du membre ne peut pas être null ou vide");
		}
		Utilisateur mem = utilisateurs.get(pseudoMem);
		if (mem == null) {
			throw new OperationImpossible("membre à ajouter inexistant avec ce pseudo (" + pseudoMem + ")");
		}
		if (mem.getMembres().get(nomReseau) != null) {
			throw new OperationImpossible("utilisateur (" + pseudoMem + ") fait déjà partie du réseau (" + nomReseau + ")");
		}
		if (mem.getEtatCompte() != EtatCompte.ACTIF) {
			throw new OperationImpossible("compte utilisateur avec ce pseudo (" + pseudoMem + ") n'est pas actif");
		}
		if (pseudoMod == null || pseudoMod.isBlank()) {
			throw new OperationImpossible("pseudo du modérateur ne peut pas être null ou vide");
		}
		Utilisateur mod = utilisateurs.get(pseudoMod);
		if (mod == null) {
			throw new OperationImpossible("utilisateur modérateur inexistant avec ce pseudo (" + pseudoMod + ")");
		}
		if (mod.getEtatCompte() != EtatCompte.ACTIF) {
			throw new OperationImpossible("compte utilisateur avec ce pseudo (" + pseudoMod + ") n'est pas actif");
		}
		if (mod.getMembres().get(nomReseau) == null) {
			throw new OperationImpossible("utilisateur supposé modérateur (" + pseudoMod + ") ne fait pas partie du réseau (" + nomReseau + ")");
		}
		if (mod.getMembres().get(nomReseau).estModerateur() != true) {
			throw new OperationImpossible("utilisateur supposé modérateur (" + pseudoMod + ") du réseau (" + nomReseau + ") n'est pas modérateur");
		}
		if (pseudoReseau == null || pseudoReseau.isBlank()) {
			throw new OperationImpossible("pseudo pour le réseau ne peut pas être null ou vide");
				}
		if (rs.getMembres().get(pseudoReseau) != null) {
			throw new OperationImpossible("pseudo choisi pour le réseau (" + pseudoReseau + ") déjà choisi");
		}
		
		Membre m = new Membre(mem,rs,pseudoReseau);
		rs.ajouterMembre(m);
		mem.ajouterMembre(m);
		
	}
	
	public Map<String, Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}
	
	public Map<String, ReseauSocial> getReseaux() {
		return reseaux;
	}
	
	/**
	 * obtient le nom du projet.
	 * 
	 * @return le nom du projet.
	 */
	public String getNomDeProjet() {
		return nomDuSysteme;
	}

	@Override
	public String toString() {
		return "MiniSocs [nomDuSysteme=" + nomDuSysteme + ", utilisateurs=" + utilisateurs + ", reseaux=" + reseaux +"]";
	}
}
