package Servicesupermarche;

import java.util.ArrayList;
import java.util.List;

public class Caisse {

	private int ident;
	private List<String> tapis;
	private int taille_du_tapis;
	private boolean clientEnCours;

	public final String alert = "Suivant";

	public Caisse(int ident) {
		this.ident = ident;
		this.tapis = new ArrayList<String>();
		this.taille_du_tapis = Supermarche.TAILLE_TAPIS;
		this.clientEnCours = false;
	}

	public int getIdent() {
		return ident;
	}

	public void setIdent(int ident) {
		this.ident = ident;
	}

	public String getAlert() {
		return alert;
	}

	public synchronized void passerEnCaisse() throws InterruptedException {
		if (clientEnCours) {
			wait();
		} else {
			clientEnCours = true;
		}
	}

	public synchronized void partirDeCaisse() {
		clientEnCours = false;
		notifyAll();
	}

	synchronized public void deposerSurTapis(String p) throws InterruptedException {
		if (tapis.size() < taille_du_tapis) {
			tapis.add(p);
			notifyAll();
		} else {
			wait();
		}
	}

	synchronized public String prendreSurTapis() throws InterruptedException {
		String s = "";
		if (tapis.size() > 0) {
			s = tapis.get(0);
			tapis.remove(0);
		} else {
			wait();
		}
		notifyAll();
		return s;
	}

	synchronized public void demanderReglement() throws InterruptedException {
		// le caisser reveille le client
		notifyAll();
		// le caissier attends le reglement
		wait();
	}

	synchronized public void payerAchats() throws InterruptedException {
		wait();
		// pay
		notifyAll();
	}

}
