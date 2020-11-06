package api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Skladiste {	
	private List<Entitet> entiteti;
	
	public Skladiste() {
		entiteti=new ArrayList<>();
	}
	
	public List<Entitet> getEntiteti(){
		return entiteti;
	}
	
	public void nalepiEntitete(List<Entitet> entiteti) {
		this.entiteti.addAll(entiteti);
	}
	
	public int dodajEntitet(Entitet entitet) {
		for (Entitet e : entiteti) {
			if(e.getId().equals(entitet.getId())){
				return -1;
			}
			if(e.nadjiUgnjezdene()!=null) {
				if(e.nadjiUgnjezdene().contains(entitet)) {
					return -1;
				}
			}
		}
		entiteti.add(entitet);
		return 0;
	}
	
	public int naknadnoDodaj(String idSpoljasnjeg, String kljucSpoljasnjeg, Entitet ugnjezden) {
		for (Entitet entitet : entiteti) {
			if(entitet.getId().equals(idSpoljasnjeg)){
				entitet.dodajAtribut(kljucSpoljasnjeg, ugnjezden);
				return 0;
			}
		}
		return -1;
	}
	
	public List<Entitet> pretrazi(List<Uslov> uslovi) {
		List<Entitet> rezultat=new ArrayList<>();
		for (Entitet entitet : entiteti) {
			boolean ispunjava=true;
			for (Uslov uslov : uslovi) {
				if(!(uslov.poredi(entitet))) {
					ispunjava=false;
					break;
				}
			}
			if(ispunjava) {
				rezultat.add(entitet);
			}
		}
		return rezultat;
	}
	
	public List<Entitet> sortiraj(List<String> kriterijumi, List<Entitet> entiteti) {
		Collections.sort(entiteti, new EntitetKomparator(kriterijumi));
		return entiteti;
	}
	
	public List<Entitet> brisi(List<Uslov> uslovi) {
		List<Entitet> rezultat=pretrazi(uslovi);
		entiteti.removeAll(rezultat);
		return rezultat;
	}
}