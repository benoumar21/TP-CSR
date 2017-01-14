package Servicesupermarche;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;



public class ChefDeRayon extends Thread {

	private int ident;
	private HashMap<Produit,Integer> stock;
	public List<Rayon> listeDeRayon;
	public final int quantiteChefRayon = 5;



	public ChefDeRayon(int ident) {
		setIdentDuChefRayon(ident);
		setStock(new HashMap<Produit, Integer>());
	}

	
	
	
	
	public int getIdentDuChefRayon() {
		return ident;
	}





	public void setIdentDuChefRayon(int ident) {
		this.ident = ident;
	}





	public HashMap<Produit, Integer> getStock() {
		return stock;
	}





	public void setStock(HashMap<Produit, Integer> stock) {
		this.stock = stock;
	}





	@Override
	public void run() {
		
		try 
		{
			while(true)
			{			
				
				Supermarche.restockFromEntrepot();
				sleep(500); // le temps mis par le chef de rayon
				System.out.println("Le chef de rayon s'apprete a faire le tour de ses rayons !");
				for (Rayon r : Supermarche.lesRayons) 
				{	
					sleep(200); // le temps d'arriver au rayon
					int nb = stock.get(r.getProduit());
					nb = r.prendreStockMax(nb);
					stock.replace(r.getProduit(), nb);
					System.out.println(Thread.currentThread().getName() + " Le chef de rayon est passer au rayon " + r.getProduit().getNomProduit());
					System.out.println(Thread.currentThread().getName() +" Le chef de rayon a approvisionner le rayon "+ r.getProduit().getNomProduit() );
				}
				System.out.println(Thread.currentThread().getName() + " le chef de rayon a fini de faire le tour de ses rayons");
				sleep(10000);
				
			}
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
}
