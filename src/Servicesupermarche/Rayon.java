package Servicesupermarche;

public class Rayon  {

	private int ident;
	private Produit produit;
	private int stock;

	
	
	
	public Rayon(int ident, Produit produit, int stock) {
		this.ident = ident;
		this.produit = produit;
		this.stock = stock;
	}

		
	
	
	public int getIdent() {
		return ident;
	}




	public void setIdent(int ident) {
		this.ident = ident;
	}




	public int getStock() {
		return stock;
	}




	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	


	public synchronized int prendreStockMax(int val) {

		if (stock > Supermarche.RAYON_STOCK_MAX) 
		{
			val += (stock - Supermarche.RAYON_STOCK_MAX);
		}
		else if(stock < Supermarche.RAYON_STOCK_MAX)
		{
			int diff = Supermarche.RAYON_STOCK_MAX - stock;
			if(val > diff)
			{
				stock = stock + diff;
				val = val - diff;
			}
			else
			{
				stock = stock + val;
				val = 0;
			}
		}
		notifyAll();
		return val;
	}

	public synchronized void prendreProduit() throws InterruptedException {

		if(stock > 0)
		{
			stock--;
		}
		else 
		{
			wait();
		}
	}

	public void sortirDuRayon() {
		System.out.println(Thread.currentThread().getName()
				+ "J'ai fini de parcourir ce rayon ");

	}

	
}
