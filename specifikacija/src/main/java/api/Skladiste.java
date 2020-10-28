package api;

import java.util.ArrayList;

public class Skladiste {
	static int brojEntiteta=0;
	static int maxBrojEntiteta;
	
	private String putanja;
	private ArrayList<String> fajlovi;
	private ArrayList<Entitet> entiteti;
	
	public String getPutanja() {
		return putanja;
	}
	
	public void setPutanja(String putanja) {
		this.putanja=putanja;
	}
	
	public ArrayList<String> getFajlovi(){
		return fajlovi;
	}
	
	public ArrayList<Entitet> getEntiteti(){
		return entiteti;
	}
	
	public int dodajEntitet(Entitet entitet) {
		ArrayList<Entitet> ugnjezdeni=new ArrayList<Entitet>();
		for (Entitet e : entiteti) {
			if(e.getId().equals(entitet.getId())){
				return 0;
			}
			for (Object o : e.getAtributi().values()) {
				if(o instanceof Entitet) {
					Entitet en=(Entitet)o;
					ugnjezdeni.add(en);
				}
			}
		}
		entiteti.add(entitet);
		brojEntiteta++;
		entiteti.addAll(ugnjezdeni);
		brojEntiteta+=ugnjezdeni.size();
		return 1;
	}
	
	public int naknadnoDodavanje(String idSpoljasnjeg, Entitet ugnjezden, String kljucSpoljasnjeg) {
		Entitet spoljasnji=null;
		for (Entitet entitet : entiteti) {
			if(entitet.getId()==idSpoljasnjeg) {
				spoljasnji=entitet;
				break;
			}
		}
		if(spoljasnji!=null) {
			spoljasnji.getAtributi().put(kljucSpoljasnjeg, spoljasnji);
			return 0;
		}
		return 1;
	}
	
	public void pretraga(String uslovi) {
		
	}
	
	public void sortiranje(String uslovi) {
		
	}
	
	public void brisanje(String uslovi) {
		
	}
	
	public void konfiguracija(int maxEntiteta) {
		maxBrojEntiteta=maxEntiteta;
		return;
	}
}