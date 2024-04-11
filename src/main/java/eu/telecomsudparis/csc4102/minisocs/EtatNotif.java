// CHECKSTYLE:OFF

package eu.telecomsudparis.csc4102.minisocs;

/**
 * Ce type énuméré modélise l'état du compte d'un utilisateur.
 * États possibles : ACTIF, DESACTIVE, BLOQUE
 */
public enum EtatNotif {
	/**
	 * reçoit toutes les notifications
	 */
	IMMEDIAT("immédiat"),
	/**
	 * ne reçoit aucune notification.
	 */
	RIEN("rien"),
	/**
	 * reçoit un récapitulatif des notifications une fois par jour
	 */
	QUOTIDIEN("quotidien");

	/**
	 * le nom de l'état à afficher.
	 */
	private String nom;

	/**
	 * construit un énumérateur.
	 * 
	 * @param nom le nom de l'état.
	 */
	EtatNotif(final String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return nom;
	}
}
