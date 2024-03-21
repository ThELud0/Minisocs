package eu.telecomsudparis.csc4102.minisocs;

import java.time.Instant;
import java.util.Objects;

import eu.telecomsudparis.csc4102.util.Datutil;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

public class Message {
	
	/**
	 *  l'id du message
	 */
	private final String idMessage;
	
	/**
	 * le contenu du message
	 */
	private String contenu;
	
	/**
	 *  l'état du message 
	 */
	private EtatMessage etatMessage;
	
	/**
	 * construit un message.
	 * 
	 * @param contenu le contenu du message
	 * @param pseudoUtilisateur le pseudo de l'utilisateur qui crée le message
	 */
	public Message(final String contenu, String pseudoUtilisateur) {
		if (contenu == null || contenu.isBlank()) {
			throw new IllegalArgumentException("le contenu du message ne peut pas être null ou vide");	
		}
		if (pseudoUtilisateur == null || pseudoUtilisateur.isBlank()) {
			throw new IllegalArgumentException("le pseudo de l'utilisateur ne peut pas être null ou vide");	
		}
		Instant instant = Datutil.instantDuTest(); // j'espère que ça fait ce qu'il faut lol
		
		this.idMessage=pseudoUtilisateur + Datutil.instantToString(instant);
		this.contenu=contenu;
		this.etatMessage=EtatMessage.ATTENTE;
	}	
	
	public void moderer(EtatMessage etatMessage) throws OperationImpossible {
		if (this.etatMessage != EtatMessage.ATTENTE) {
			throw new OperationImpossible("seul un message en attente peut être modéré");
		}
		if ((etatMessage == EtatMessage.ATTENTE) || (etatMessage == EtatMessage.CACHE)) {
			throw new OperationImpossible("modérer un message signifie l'accepter ou le refuser");
		}
		
		this.etatMessage = etatMessage;
		// A voir comment détruire le message si set à REFUSE
		
		assert invariant();
	}
	
	public void cacher(boolean hidden) throws OperationImpossible {
		if ((this.etatMessage == EtatMessage.REFUSE ) || (this.etatMessage == EtatMessage.ATTENTE)) {
			throw new OperationImpossible("refusé ou en attente ne sont pas des états depuis lesquels on peut cacher ou rendre visible un message"); 
		}
		
		if (!hidden) {
			this.etatMessage=EtatMessage.ACCEPTE;	
		}
		
		if(hidden) {
			this.etatMessage=EtatMessage.CACHE;
		}
		
		assert invariant();
	}
	
	public String getID() {
		return idMessage;
	}
	
	public String getContenu() {
		return contenu;
	}
	
	public EtatMessage getEtatMessage() {
		return etatMessage;
	}
	
	/**
	 * vérifie l'invariant de la classe.
	 * 
	 * @return {@code true} si l'invariant est respecté.
	 */
	public boolean invariant() {
		return idMessage != null && !idMessage.isBlank() && etatMessage != null && contenu !=null && !contenu.isBlank(); 
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(idMessage);
	}
	
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Message)) {
			return false;
		}
		Message other = (Message) obj;
		return Objects.equals(idMessage, other.idMessage);
	}

	@Override
	public String toString() {
		return "Message [idMessage=" + idMessage + ", Contenu=" + contenu + ", État du message=" + etatMessage + "]";
	}
	

	
}	
