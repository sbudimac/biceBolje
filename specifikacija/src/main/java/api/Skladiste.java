package api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Skladiste {	
	private List<Entitet> entiteti;
	private Set<String> kljucevi;
	private AbstractOperator operator;
	
	public Skladiste() {
		entiteti=new ArrayList<>();
		kljucevi=new HashSet<>();
	}
	
	public List<Entitet> getEntiteti(){
		return entiteti;
	}
	
	public void setEntiteti(List<Entitet> entiteti) {
		this.entiteti = entiteti;
	}
	
	public Set<String> getKljucevi(){
		return kljucevi;
	}
	
	/*public void nalepiEntitete(List<Entitet> entiteti) {
		this.entiteti.addAll(entiteti);
		for (Entitet e : entiteti) {
			kljucevi.addAll(e.getAtributi().keySet());
		}
	}*/
	
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
		kljucevi.addAll(entitet.getAtributi().keySet());
		if(operator!=null) {
			operator.dodajEntitet(entitet);
		}
		return 0;
	}
	
	public int naknadnoDodaj(String idSpoljasnjeg, String kljucSpoljasnjeg, Entitet ugnjezden) {
		for (Entitet entitet : entiteti) {
			if(entitet.getId().equals(idSpoljasnjeg)){
				entitet.dodajAtribut(kljucSpoljasnjeg, ugnjezden);
				if(operator!=null) {
					operator.izmeniEntitet(entitet);
				}
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
				if(!(uslov.test(entitet))) {
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
	
	public void brisi(Entitet entitet) {
		entiteti.remove(entitet);
		if(operator!=null) {
			operator.brisi(entitet);
		}
	}

	public AbstractOperator getOperator() {
		return operator;
	}

	public void setOperator(AbstractOperator operator) {
		this.operator = operator;
		List<Entitet> ucitaniEntiteti = operator.ucitajUSkladiste();
		List<Entitet> entitetiZaBrisanje = new ArrayList<Entitet>();
		for(Entitet entitet : entiteti) {
			if(ucitaniEntiteti.contains(entitet)) {
				entitetiZaBrisanje.add(entitet);
			} else {
				operator.dodajEntitet(entitet);
			}
		}
		entiteti.removeAll(entitetiZaBrisanje);
		entiteti.addAll(ucitaniEntiteti);
		for (Entitet e : ucitaniEntiteti) {
			kljucevi.addAll(e.getAtributi().keySet());
		}
	}

	public void izmeniEntitet(Entitet entitet, String kljuc, String vrednost) {
		entitet.setAtribut(kljuc, vrednost);
		if(operator!=null) {
			operator.izmeniEntitet(entitet);
		}
	}
}