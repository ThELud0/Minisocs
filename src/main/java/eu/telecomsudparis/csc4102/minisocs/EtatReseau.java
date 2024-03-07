package eu.telecomsudparis.csc4102.minisocs;

/**
 * Ce type énuméré modélise l'état d'un réseau.
 */
public enum EtatReseau {
	/**
	 * le réseau est ouvert.
	 */
	OUVERT("ouvert"),
	/**
	 * le réseau est fermé.
	 */
	FERME("fermé");

	/**
	 * le nom de l'état à afficher.
	 */
	private String nom;

	/**
	 * construit un énumérateur.
	 * 
	 * @param nom le nom de l'état.
	 */
	EtatReseau(final String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return nom;
	}
}
