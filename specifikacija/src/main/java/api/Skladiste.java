package api;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
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
	
	public static Skladiste getInstance() {
		if(instance==null) {
			instance=new Skladiste();
		}
		return instance;
	}
}