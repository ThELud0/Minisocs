// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.minisocs;

import java.util.Objects;
import java.util.HashMap;

import java.util.Map;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

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
	 * les membres. Le string réfere au pseudo utilisé par le membre dans le réseau
	 * social.
	 */
	private final Map<String, Membre> membres;
	/**
	 * les messages
	 */
	private final Map<String, Message> messages;

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
		this.membres = new HashMap<>();
		this.messages = new HashMap<>();

		assert invariant();
	}

	/**
	 * vérifie l'invariant de la classe.
	 * 
	 * @return {@code true} si l'invariant est respecté.
	 */
	public boolean invariant() {
		return nomReseau != null && !nomReseau.isBlank() && etatReseau != null && membres != null && messages != null;
	}

	public void ajouterMembre(Membre membre) throws OperationImpossible {
		if (membre == null || !(membre.invariant())) {
			throw new OperationImpossible("membre invalide");
		}
		if (membres.get(membre.getPseudoReseau()) != null) {
			throw new OperationImpossible("membre déjà dans le réseau");
		}

		membres.put(membre.getPseudoReseau(), membre);

		assert invariant();
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
	 * obtient les membres.
	 * 
	 * @return hashmap membres.
	 */
	public Map<String, Membre> getMembres() {
		return membres;
	}

	/**
	 * obtient les messages.
	 * 
	 * @return hashmap messages.
	 */
	public Map<String, Message> getMessages() {
		return messages;
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
	 * ferme le réseau social. L'opération est idempotente.
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

	public void ajouterMessage(Message message) throws OperationImpossible {
		if ((message == null) || !message.invariant()) {
			throw new OperationImpossible("message invalide");
		}
		if (messages.get(message.getID()) != null) {
			throw new OperationImpossible("un message avec cet ID existe déjà dans ce réseau");
		}

		messages.put(message.getID(), message);

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
		return "ReseauSocial [nomReseau=" + nomReseau + ", etatReseau=" + etatReseau + ", membres=" + membres
				+ ", messages=" + messages + "]";
	}

}
