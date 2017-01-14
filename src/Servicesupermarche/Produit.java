package Servicesupermarche;

public class Produit {
	private int ident;
	private String nomProduit;

	public Produit(int ident, String nomProduit) {
		
		this.ident= ident;
		this.nomProduit = nomProduit;
	}
	public String getNomProduit() {
		return nomProduit;
	}

	public void setNomProduit(String nomProduit) {
		this.nomProduit = nomProduit;
	}

	public int getIdent() {
		return ident;
	}

	public void setIdent(int ident) {
		this.ident = ident;
	}
	
	
	
}
