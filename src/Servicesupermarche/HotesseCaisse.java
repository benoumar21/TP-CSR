package Servicesupermarche;

public class HotesseCaisse extends Thread{

	
	private int ident;

	public HotesseCaisse(int ident) {
		this.ident = ident;
	}

	public int getIdent() {
		return ident;
	}

	public void setIdent(int ident) {
		this.ident = ident;
	}
	
	public void waitClient() throws InterruptedException
	{
		String s = "";
		while(s != Supermarche.caisse.alert)
		{
			s = Supermarche.caisse.prendreSurTapis();
		}
		System.err.println(Thread.currentThread().getName() +" L'hotesse de caisse à comptabiliser tous les produits du client ");
		Supermarche.caisse.demanderReglement();
		System.out.println("Le client a payer le montant de ses produits");
	}
	
	@Override
	public void run() {

		while(true)
		{
			try 
			{
				waitClient();
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	
		
	}
	
	
	
}
