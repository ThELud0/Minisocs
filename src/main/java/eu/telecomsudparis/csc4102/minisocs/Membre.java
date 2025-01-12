// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.minisocs;

import java.util.HashMap;
import java.util.Map;
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
	private String pseudoReseau;

	private boolean moderateur;
	
	/**
	 * consommateur pour recevoir des notifications
	 */
	private MonConsommateur consommateur;

	/**
	 * les messages
	 */
	private final Map<String, Message> messages;

	public Membre(final Utilisateur utilisateur, final ReseauSocial reseauSocial, final String pseudoReseau, EtatNotif etat) {
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
		this.messages = new HashMap<>();
		this.consommateur = new MonConsommateur(pseudoReseau);
		this.consommateur.setEtatNotif(etat);

		assert invariant();
	}

	public void setModerateur() {
		this.moderateur = true;
		this.consommateur.setModerateur();
		return;
	}

	public void demote() {
		this.moderateur = false;
		this.consommateur.demote();
		return;
	}

	/**
	 * change le pseudo choisi pour le réseau donné
	 * 
	 * @param ps le nouveau pseudo réseau
	 * 
	 *           ATTENTION: ne jamais utiliser directement sauf cas exceptionnel! Il
	 *           faut passer par la façade car il faut actualiser les collections de
	 *           membres dans utilisateurs et reseaux en plus.
	 */
	public void setPseudoReseau(String ps) {
		this.pseudoReseau = ps;
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

	public Map<String, Message> getMessages() {
		return messages;
	}

	public void ajouterMessage(Message message) throws OperationImpossible {
		if ((message == null) || !message.invariant()) {
			throw new OperationImpossible("message invalide");
		}
		if (messages.get(message.getID()) != null) {
			throw new OperationImpossible("un message avec cet ID existe déjà pour ce membre");
		}

		messages.put(message.getID(), message);

		assert invariant();
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
		return utilisateur != null && reseauSocial != null && messages != null && utilisateur.invariant()
				&& reseauSocial.invariant() && pseudoReseau != null && !pseudoReseau.isBlank() && consommateur != null;
	}
	
	public MonConsommateur getConsommateur() {
		return consommateur;
	}

	@Override
	public int hashCode() {
		return Objects.hash(utilisateur.hashCode() + reseauSocial.hashCode());
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
		return (utilisateur.equals(other.getUtilisateur()) && reseauSocial.equals(other.getReseauSocial()));
	}

	@Override
	public String toString() {
		return "Membre [utilisateur=" + utilisateur.toString() + ", reseau social=" + reseauSocial.toString() + ", pseudo dans le reseau="
				+ pseudoReseau + ", modérateur=" + moderateur + "]";
	}

}
