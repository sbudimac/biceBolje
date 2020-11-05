package api;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Skladiste {
	static Skladiste instance=null;
	
	static int maxBrojEntiteta=3;
	static int currFileIndex=0;
	
	private String putanja;
	
	private List<Entitet> entiteti;
	private Map<String, List<String>> fajloviEntiteta;
	
	private Skladiste() {
		entiteti=new ArrayList<>();
		fajloviEntiteta=new HashMap<String, List<String>>();
	}
	
	public String getPutanja() {
		return putanja;
	}
	
	public void setPutanja(String putanja) {
		this.putanja=putanja;
	}
	
	public List<Entitet> getForFile(String fajl) {
		List<Entitet> fajlEntiteti=new ArrayList<>();
		for (Entitet entitet : entiteti) {
			if(fajloviEntiteta.get(fajl).contains(entitet.getId())) {
				fajlEntiteti.add(entitet);
			}
		}
		return fajlEntiteti;
	}
	
	public String dodajEntitet(Entitet entitet) {
		for (Entitet e : entiteti) {
			if(e.getId().equals(entitet.getId())){
				return null;
			}
			if(e.getUgnjezdeni().contains(entitet)) {
				return null;
			}
		}
		entiteti.add(entitet);
		for(Map.Entry<String, List<String>> grupaEntiteta : fajloviEntiteta.entrySet()) {
			if(grupaEntiteta.getValue().size()<maxBrojEntiteta) {
				grupaEntiteta.getValue().add(entitet.getId());
				return grupaEntiteta.getKey();
			}
		}
		File file=new File(putanja, "BiceBolje" + currFileIndex);
		currFileIndex++;
		try {
			if(file.createNewFile()) {
				fajloviEntiteta.put(file.toString(), new ArrayList<String>());
				fajloviEntiteta.get(file.toString()).add(entitet.getId());
				return file.toString();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return null;
	}
	
	private String fajlEntiteta(Entitet entitet) {
		for(Map.Entry<String, List<String>> grupaEntiteta : fajloviEntiteta.entrySet()) {
			if(grupaEntiteta.getValue().contains(entitet.getId())) {
				return grupaEntiteta.getKey();
			}
		}
		return null;
	}
	
	public String naknadnoDodaj(String idSpoljasnjeg, Entitet ugnjezden, String kljucSpoljasnjeg) {
		Entitet spoljasnji=null;
		for (Entitet entitet : entiteti) {
			if(entitet.getId().equals(idSpoljasnjeg)){
				spoljasnji=entitet;
				spoljasnji.dodajAtribut(kljucSpoljasnjeg, spoljasnji);
				return fajlEntiteta(spoljasnji);
			}
		}
		return null;
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
	
	public void brisi(List<Uslov> uslovi) {
		List<Entitet> rezultat=pretrazi(uslovi);
		for (Entitet entitet : rezultat) {
			entiteti.remove(entitet);
			String fajl=fajlEntiteta(entitet);
			fajloviEntiteta.get(fajl).remove(entitet.getId());
			if(fajloviEntiteta.get(fajl).size()<=0) {
				fajloviEntiteta.remove(fajl);
				File file=new File(fajl);
				file.delete();
			}
		}
	}
	
	public void setKonfiguracija(int maxEntiteta) {
		maxBrojEntiteta=maxEntiteta;
	}
	
	public static Skladiste getInstance() {
		if(instance==null) {
			instance=new Skladiste();
		}
		return instance;
	}

	public Map<String, List<String>> getFajloviEntiteta() {
		return fajloviEntiteta;
	}
}