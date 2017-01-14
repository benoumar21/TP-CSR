package Servicesupermarche;

public class EntrepotChariot {
	public int nBChariotEntrepot ;

	public EntrepotChariot() {
		this.nBChariotEntrepot = Supermarche.CHARIOTS_INIT;;
	}
	
	public synchronized void prendreChariot() throws InterruptedException{
		if(nBChariotEntrepot > 0)
		{
			nBChariotEntrepot--;
		}
		else 
		{
			wait();
		}
	}
	public synchronized void RendreChariot(){
		nBChariotEntrepot++;
		notifyAll();
	}
}
