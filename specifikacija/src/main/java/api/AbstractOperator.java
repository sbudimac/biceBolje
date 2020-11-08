package api;

import java.util.List;
import java.util.Set;

public abstract class AbstractOperator {
	protected Skladiste skladiste;
	
	public AbstractOperator() {
		
	}
	
	public Set<String> getKljucevi() {
		return skladiste.getKljucevi();
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
	
	public abstract void ucitajSkladiste();
	
	public abstract void dodajEntitet(Entitet entitet);
	
	public abstract void naknadnoDodaj(String idSpoljasnjeg, String kljucSpoljasnjeg, Entitet ugnjezden);
		
	public abstract void izmeniEntitet(Entitet entitet, String kljuc, String vrednost);
}
