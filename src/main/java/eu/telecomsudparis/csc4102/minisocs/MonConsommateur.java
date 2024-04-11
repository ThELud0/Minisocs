// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.minisocs;


import java.time.LocalDate;
import java.util.concurrent.Flow.Subscriber;
import eu.telecomsudparis.csc4102.util.Datutil ;
import java.util.concurrent.Flow.Subscription;

/**
 * Cette classe définit le consommateur. Les publications sont typées.
 * 
 * @author Denis Conan, modifié par Ludovic HU
 */
public class MonConsommateur implements Subscriber<Publication> {
	/**
	 * identifiant du consommateur : pour les messages a la console qui servent a
	 * suivre l'execution.
	 */
	private String id;
	/**
	 * la souscription. Cet objet sert a controler le flux entre le producteur et le
	 * consommateur.
	 */
	private Subscription souscription;
	// on pourrait ajouter une collection pour garder les publications recues

	/**
	 * constructeur.
	 * 
	 * @param id identifiant pour les affichages.
	 */

	private EtatNotif etatNotif;

	private LocalDate date;

	private boolean moderateur;

	private int compteurMessage;

	private int compteurMessageEnAttente;


	public MonConsommateur(final String id) { // ...
		this.id = id;
		date = Datutil.aujourdhui();
		compteurMessage = 0;
		compteurMessageEnAttente = 0;
	}

	public void setEtatNotif(EtatNotif etat) {
		this.etatNotif = etat;
	}

	public void setModerateur() {
		this.moderateur = true;
		return;
	}
	
	public boolean getModStatus() {
		return moderateur;
	}

	public void demote() {
		this.moderateur = false;
		return;
	}


	@Override 
	public void onSubscribe(final Subscription souscription) {
		this.souscription = souscription;
		// on consomme un message des qu'il arrive ; un a la fois
		// on declare qu'on est pret a recevoir un message
		this.souscription.request(1);
		System.out.println("Consommateur " + id + " pret a recevoir la premiere publication");
	}

	@Override
	public void onNext(final Publication publication) {
		// reception d'une publication...
		if (etatNotif == EtatNotif.IMMEDIAT) {

			if ((publication.getMessage().getEtatMessage()==EtatMessage.ATTENTE)&&(moderateur==true)){
				System.out.println("Message en attente de modération dans le réseau " + publication.getNomReseau());
			}
			
			else if (publication.getMessage().getEtatMessage()==EtatMessage.ACCEPTE) {
				System.out.println("Nouveau message [" + publication.getNomReseau() + " à " + publication.getMessage().getInstant()
				+ "]: " + publication.getMessage().getContenu());
			}
			
			// on declare qu'on est pret a recevoir un nouveau message
			souscription.request(1);
		}
		
		else if (etatNotif == EtatNotif.QUOTIDIEN) {
			if (Datutil.dateEstAvantAujourdhui(date)){
				date = Datutil.aujourdhui();
				if (moderateur==true) {
					System.out.println(compteurMessageEnAttente + "nouveaux messages en attente de modération dans le réseau " + publication.getNomReseau());
					compteurMessageEnAttente=0;
				}
				System.out.println(compteurMessage + "nouveaux messages dans le réseau " + publication.getNomReseau());
				compteurMessage=0;
				souscription.request(1);
			}
			else {
				if ((publication.getMessage().getEtatMessage()==EtatMessage.ATTENTE)&&(moderateur==true)){
					compteurMessageEnAttente+=1;
				}
				
				else if (publication.getMessage().getEtatMessage()==EtatMessage.ACCEPTE) {
					compteurMessage+=1;
				}
				// on declare qu'on est pret a recevoir un nouveau message
				souscription.request(1);
			}
		}
		
		else if (etatNotif == EtatNotif.RIEN) {
			souscription.request(1);
		}
		
	}

	@Override
	public void onError(final Throwable throwable) { //...
		// erreur sur la gestion du flux, par exemple producteur.subscribe
		// d'un consommateur qui est deja un subscriber du producteur
		throwable.printStackTrace();
	}

	@Override
	public void onComplete() { // lorsque le producteur ferme le flux
		// on affiche la fin a la console
		System.out.println("Consommateur " + id + " est desabonne suite a la fermeture du flux par le producteur");
	}

}
