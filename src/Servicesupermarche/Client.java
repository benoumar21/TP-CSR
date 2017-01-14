package Servicesupermarche;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;



public class Client extends Thread {

	private int ident;
	public HashMap<Produit, Integer> listeDeCourses;
	public statusClient status;

	public Client(int ident) {
		this.ident = ident;
		this.status = statusClient.ATTENTE_CHARIOT;
		listeDeCourses = new HashMap<Produit, Integer>();
	}

	public int getIdent() {
		return ident;
	}

	public void setIdent(int ident) {
		this.ident = ident;
	}
	
	
	
	public HashMap<Produit, Integer> getListeDeCourses() {
		return listeDeCourses;
	}

	public void setListeDeCourses(HashMap<Produit, Integer> listeDeCourses) {
		this.listeDeCourses = listeDeCourses;
	}

	public statusClient getStatus() {
		return status;
	}

	public void setStatus(statusClient status) {
		this.status = status;
	}



	public enum statusClient {

		ATTENTE_CHARIOT, EN_COURSE, ATTENTE_PRODUIT, ATTENTE_CAISSE, A_LA_CAISSE
	}

	public void run() {

		try {
			// on genere la liste de courses
			GenererListeCourse();
			// on prends un chariot
			PrendreUnChariot();
			System.out.println(Thread.currentThread().getName() +" Le client " + ident + " prend son chariot");
			// on parcours les rayons
			ParcourirRayons();
			System.out.println(Thread.currentThread().getName() + " Le client " + ident + " attend de passer en caisse ");
			// on passe en caisse
			PassageCaisse();
			// on depose en caisse
			DeposerEnCaisse();
			System.err.println(Thread.currentThread().getName() +" Le client " + ident + " depose ses produit en caisse !");
			// on prepare le reglement
			Payement();
			Supermarche.caisse.partirDeCaisse();
			// on depose le chariot
			Supermarche.chariots.RendreChariot();
			System.out.println( Thread.currentThread().getName() +" Le client " + ident + "a rendu son chariot");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public void GenererListeCourse() {
		listeDeCourses.put(Supermarche.lesProduits.get(0), 3);
		listeDeCourses.put(Supermarche.lesProduits.get(1), 4);
		listeDeCourses.put(Supermarche.lesProduits.get(2), 7);
		listeDeCourses.put(Supermarche.lesProduits.get(3), 0);
		System.out.println("Le client " + ident + " a decider ce qu'il a besoin");
	}

	public void PrendreUnChariot() throws InterruptedException {
		Supermarche.chariots.prendreChariot();
	}

	public void ParcourirRayons() throws InterruptedException {
		for (Rayon r : Supermarche.lesRayons) {
			sleep(300);
			int nb = listeDeCourses.get(r.getProduit());
			while (nb > 0) {
				r.prendreProduit();
				nb--;
			}
			System.out.println(Thread.currentThread().getName() + " Le client " + ident + " a pris ses " + listeDeCourses.get(r.getProduit()) + " "
					+ r.getProduit().getNomProduit() + " ");
		}
		System.err.println( Thread.currentThread().getName() +" Le client " + ident + " a fini ses courses");
	}

	
	public void PassageCaisse() throws InterruptedException
	{
		Supermarche.caisse.passerEnCaisse();			
	}

	public void DeposerEnCaisse() throws InterruptedException
	{
		int i;
		for(Produit p : listeDeCourses.keySet())
		{
			i = listeDeCourses.get(p);
			while(i > 0)
			{			
				Supermarche.caisse.deposerSurTapis(p.getNomProduit());
				i--;
				sleep(20);				
			}
		}
	}
	
	public void Payement() throws InterruptedException
	{
		// on entre en mode payment
		Supermarche.caisse.deposerSurTapis(Supermarche.caisse.alert);
		Supermarche.caisse.payerAchats();
	}
}
