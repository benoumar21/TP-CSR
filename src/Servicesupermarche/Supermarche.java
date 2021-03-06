package Servicesupermarche;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.restlet.Application;
import org.restlet.Context;

public class Supermarche extends Application
{

	public static final int RAYON_STOCK_INIT = 5;
	public static final int RAYON_STOCK_MAX = 10;
	public static final int TAILLE_TAPIS = 10;
	public static final int CHARIOTS_INIT = 2;
	public static final int MAX_CLIENT = 3;
		
	public static EntrepotChariot chariots;	
	public static List<Produit> lesProduits;
	public static List<Rayon> lesRayons;
	public static Caisse caisse;
	
	public static List<Client> lesClients;	
	public static ChefDeRayon chef;
	public static HotesseCaisse caissier;
	
	
	
	
	public Supermarche(Context context) {
	
		InitSupermarche();
		chef.start();
		caissier.start();
		for (Client c : lesClients) 
		{
			c.start();
		}
		
		
	}

	public static void InitSupermarche()
	{
		chariots = new EntrepotChariot();
		caisse = new Caisse(0);
		InitProduits();
		InitRayons();
		InitClient();
		chef = new ChefDeRayon(0);
		caissier = new HotesseCaisse(0);
	}
		
	public static void InitProduits()
	{
		lesProduits = new ArrayList<Produit>();
		lesProduits.add(new Produit(0, "Sucre"));
		lesProduits.add(new Produit(1, "Farine"));
		lesProduits.add(new Produit(2, "Beurre"));
		lesProduits.add(new Produit(3, "Lait"));
	}
	
	public static void InitRayons()
	{
		lesRayons = new ArrayList<Rayon>();
		for (Produit p : lesProduits) 
		{
			lesRayons.add(new Rayon(p.getIdent(), p, RAYON_STOCK_INIT));
		}
	}
	
	public static void InitClient()
	{
		lesClients = new ArrayList<Client>();
		for (int i = 0; i < MAX_CLIENT; i++) 
		{
			lesClients.add(new Client(i));
		}
	}
	
	public static void main(String args[])
	{
//		InitSupermarche();
//		chef.start();
//		caissier.start();
//		for (Client c : lesClients) 
//		{
//			c.start();
//		}
	}
	
	synchronized public static void restockFromEntrepot()
	{
		HashMap<Produit, Integer> h = chef.getStock();
		for (Produit p : lesProduits) 
		{			
			if(h.containsKey(p))
			{
				h.replace(p,5);
			}
			else
			{
				h.put(p, 5);
			}
		}
		chef.setStock(h);
	}
	
	
}
