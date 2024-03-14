package eu.telecomsudparis.csc4102.minisocs;

import java.util.Objects;

public class Message {
	
	/**
	 *  l'id du message
	 */
	private final String idMessage;
	
	/**
	 * le contenu du messsage
	 */
	private String contenu;
	
	/**
	 *  l'état du message 
	 */
	private EtatMessage etatMessage;
	
	/**
	 * construit un message.
	 * 
	 * @param id l'id du message
	 * @param contenu le contenu du message
	 */
	public Message(final String idMessage, final String contenu, final EtatMessage etatMessage) {
		if (contenu == null || contenu.isBlank()) {
			throw new IllegalArgumentException("le contenu du message ne peut pas être null ou vide");	
		}
		this.idMessage=idMessage;
		this.contenu=contenu;
		this.etatMessage=etatMessage;
	}		
}	
