package api;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Skladiste {
	static Skladiste instance=null;
	
	static int maxBrojEntiteta=3;
	static int currFileIndex=0;
	
	private String putanja;
	
	private HashMap<String, ArrayList<Entitet>> fajloviEntiteta;
	
	private Skladiste() {
		fajloviEntiteta=new HashMap<String, ArrayList<Entitet>>();
	}
	
	public String getPutanja() {
		return putanja;
	}
	
	public void setPutanja(String putanja) {
		this.putanja=putanja;
	}
	
	public HashMap<String, ArrayList<Entitet>> getFajloviEntiteta(){
		return fajloviEntiteta;
	}
	
	public String dodajEntitet(Entitet entitet) {
		for (ArrayList<Entitet> e : fajloviEntiteta.values()) {
			for (Entitet ent : e) {
				if(ent.getId().equals(entitet.getId())){
					return null;
				}
				for (Object o : ent.getAtributi().values()) {
					if(o instanceof Entitet) {
						Entitet en=(Entitet)o;
						if(ent.getId()==en.getId()) {
							return null;
						}
					}
				}
			}
		}
		for(Map.Entry<String, ArrayList<Entitet>> grupaEntiteta : fajloviEntiteta.entrySet()) {
			if(grupaEntiteta.getValue().size()<maxBrojEntiteta) {
				grupaEntiteta.getValue().add(entitet);
				return grupaEntiteta.getKey();
			}
		}
		Path filePath=Paths.get(putanja, "BiceBolje"+currFileIndex);
		currFileIndex++;
		File file=new File(filePath.toString());
		try {
			if(file.createNewFile()) {
				fajloviEntiteta.put(filePath.toString(), new ArrayList<Entitet>());
				fajloviEntiteta.get(filePath.toString()).add(entitet);
				return filePath.toString();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return null;
	}
	
	public String naknadnoDodavanje(String idSpoljasnjeg, Entitet ugnjezden, String kljucSpoljasnjeg) {
		Entitet spoljasnji=null;
		for (Map.Entry<String, ArrayList<Entitet>> grupaEntiteta : fajloviEntiteta.entrySet()) {
			for (Entitet ent : grupaEntiteta.getValue()) {
				if(ent.getId()==idSpoljasnjeg){
					spoljasnji=ent;
					spoljasnji.getAtributi().put(kljucSpoljasnjeg, spoljasnji);
					return grupaEntiteta.getKey();
				}
			}
		}
		return null;
	}
	
	public List<Map.Entry<Entitet, String>> pretraga(List<Uslov> uslovi) {
		List<Map.Entry<Entitet, String>> rezultat=new ArrayList<>();
		for (Map.Entry<String, ArrayList<Entitet>> grupaEntiteta : fajloviEntiteta.entrySet()) {
			for (Entitet entitet : grupaEntiteta.getValue()) {
				boolean ispunjava=true;
				for (Uslov uslov : uslovi) {
					if(!(uslov.poredi(entitet))) {
						ispunjava=false;
						break;
					}
				}
				if(ispunjava) {
					rezultat.add(new AbstractMap.SimpleEntry<Entitet, String>(entitet, grupaEntiteta.getKey()));
				}
			}
		}
		return rezultat;
	}
	
	public void sortiranje(List<Uslov> uslovi) {
		
	}
	
	public void brisanje(List<Uslov> uslovi) {
		List<Map.Entry<Entitet, String>> rezultat=pretraga(uslovi);
		for (Map.Entry<Entitet, String> entitet : rezultat) {
			fajloviEntiteta.get(entitet.getValue()).remove(entitet.getKey());
			if(fajloviEntiteta.get(entitet.getValue()).size()<=0) {
				fajloviEntiteta.remove(entitet.getValue());
				File file=new File(entitet.getValue());
				file.delete();
			}
		}
	}
	
	public void konfiguracija(int maxEntiteta) {
		maxBrojEntiteta=maxEntiteta;
		return;
	}
	
	public static Skladiste getInstance() {
		if(instance==null) {
			instance=new Skladiste();
		}
		return instance;
	}
}