package api;

import java.util.List;

public abstract class AbstractOperator {
	protected Skladiste skladiste;
		
	public AbstractOperator(String putanja) {
		skladiste=ucitajSkladiste(putanja);
	}
	
	public List<Entitet> getEntiteti(){
		return skladiste.getEntiteti();
	}
	
	public List<Entitet> pretrazi(List<Uslov> uslovi) {
		return skladiste.pretrazi(uslovi);
	}
	
	public abstract void brisi(List<Uslov> uslovi) ;
	
	public List<Entitet> sortiraj(List<String> kriterijumi, List<Entitet> entiteti){
		return skladiste.sortiraj(kriterijumi, entiteti);
	}
	
	public abstract void kreirajSkladiste(Skladiste skladiste);
	
	public abstract Skladiste ucitajSkladiste(String putanja);
	
	public abstract void prevediEntitet(Entitet entitet);
	
	protected abstract boolean validanFajl(String putanja);
	
	public abstract void dodajEntitet(Entitet entitet);
		
	public abstract void izmeniEntitet(Entitet entitet, String kljuc, String vrednost);
}
