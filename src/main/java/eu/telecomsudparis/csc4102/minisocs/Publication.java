// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.minisocs;

import java.util.Objects;

/**
 * Cette classe definit le type des publications transmises entre les
 * producteurs et les consommateurs.
 * 
 * @author Denis Conan, modifié par Ludovic HU et Jeanne VILLETTE
 */
public class Publication {
	/**
	 * l'information.
	 */
	private Message message;
	
	/**
	 * le nom du réseau envoyant la publication
	 */
	private String nomReseau;
	
	/**
	 * constructeur.
	 */
	public Publication(final Message message, String nomReseau) {
		this.nomReseau = nomReseau;
		this.message = message;
	}

	/**
	 * obtient le message.
	 * @return le message.
	 */
	public Message getMessage() {
		return message;
	}
	
	/**
	 * obtient le nom du réseau.
	 * @return la chaîne de caractères.
	 */
	public String getNomReseau() {
		return nomReseau;
	}

	@Override
	public int hashCode() {
		return Objects.hash(message);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Publication other)) {
			return false;
		}
		return Objects.equals(message, other.message);
	}

	@Override
	public String toString() {
		return "Publication [message=" + message.toString() + ", en provenance de : " + nomReseau + "]";
	}
}
