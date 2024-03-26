// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.minisocs;
/**
 *  ce type énumère l'état d'un message.
 */
public enum EtatMessage {
	/**
	 * le message a été accepté et est visible.
	 */
	ACCEPTE("accepte"),
	/**
	 * le message a été refusé.
	 */
	REFUSE("refusé"),
	/**
	 * le message est en attente de traitement par un modérateure.
	 */
	ATTENTE("attente"),
	/**
	 * le message est caché par le membre.
	 */
	CACHE("caché");

	/**
	 * le nom de l'état à afficher.
	 */
	private String nom;

	/**
	 * construit un énumérateur.
	 * 
	 * @param nom le nom de l'état.
	 */
	EtatMessage(final String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return nom;
	}
}
