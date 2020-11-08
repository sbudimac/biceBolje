package api;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class FileOperator extends AbstractOperator {
	static int maxBrojEntiteta=3;
	
	protected int currFileIndex=0;
	protected Path putanja;
	protected Map<Path, List<Entitet>> fajloviEntiteta;
	
	public FileOperator(String putanja) {
		this.putanja = Paths.get(putanja);
		this.fajloviEntiteta=new HashMap<Path, List<Entitet>>();
	}
	
	@Override
	public void ucitajSkladiste() {
		skladiste=new Skladiste();
		File direktorijum=putanja.toFile();
		File[] fajloviDirektorijuma=direktorijum.listFiles();
		if(fajloviDirektorijuma!=null) {
			for (File fajl : fajloviDirektorijuma) {
				List<Entitet> entitetiFajla = prevediFajl(fajl);
				if(entitetiFajla == null || entitetiFajla.size() <= 0) continue;
				skladiste.nalepiEntitete(entitetiFajla);
				Path ucitanFajl = direktorijum.toPath().relativize(fajl.toPath());
				fajloviEntiteta.put(ucitanFajl, entitetiFajla);
				
				int fileIndex = BrojevniPomagac.getInstance().getBrojNaKrajuStringa(ucitanFajl.toString());
				if(fileIndex >= currFileIndex) currFileIndex = fileIndex + 1;
			}
		}
	}
	
	protected abstract List<Entitet> prevediFajl(File fajl);
	
	public abstract void prevediEntitet(Entitet entitet);
	
	protected abstract void ispisiFajl(Path putanjaFajla);
	
	protected Path fajlEntiteta(Entitet ent) {
		for(Map.Entry<Path, List<Entitet>> grupaEntiteta : fajloviEntiteta.entrySet()) {
			for(Entitet entitet : grupaEntiteta.getValue()) {
				if(entitet.getId().equals(ent.getId())) {
					return grupaEntiteta.getKey();
				}
			}
		}
		return null;
	}
	
	protected Path fajlEntiteta(String id) {
		for(Map.Entry<Path, List<Entitet>> grupaEntiteta : fajloviEntiteta.entrySet()) {
			for(Entitet entitet : grupaEntiteta.getValue()) {
				if(entitet.getId().equals(id)) {
					return grupaEntiteta.getKey();
				}
			}
		}
		return null;
	}
	
	@Override
	public void dodajEntitet(Entitet entitet) {
		int rezultat = skladiste.dodajEntitet(entitet);
		if(rezultat != 0) return;
		Path putanjaFajla;
		for(Map.Entry<Path, List<Entitet>> grupaEntiteta : fajloviEntiteta.entrySet()) {
			if(grupaEntiteta.getValue().size()<maxBrojEntiteta) {
				grupaEntiteta.getValue().add(entitet);
				ispisiFajl(grupaEntiteta.getKey());
				return;
			}
		}
		putanjaFajla = Paths.get("BiceBolje" + currFileIndex);
		currFileIndex++;
		try {
			if(putanja.resolve(putanjaFajla).toFile().createNewFile()) {
				fajloviEntiteta.put(putanjaFajla, new ArrayList<Entitet>());
				fajloviEntiteta.get(putanjaFajla).add(entitet);
				ispisiFajl(putanjaFajla);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	@Override
	public void naknadnoDodaj(String idSpoljasnjeg, String kljucSpoljasnjeg, Entitet ugnjezden) {
		int rezultat = skladiste.naknadnoDodaj(idSpoljasnjeg, kljucSpoljasnjeg, ugnjezden);
		if(rezultat != 0) return;
		Path putanjaFajla = fajlEntiteta(idSpoljasnjeg);
		ispisiFajl(putanjaFajla);
	}
	
	@Override
	public void izmeniEntitet(Entitet entitet, String kljuc, String vrednost) {
		// TODO Auto-generated method stub

	}

	@Override
	public void brisi(List<Uslov> uslovi) {
		List<Entitet> rezultat=skladiste.brisi(uslovi);
		for(Entitet entitet : rezultat) {
			Path putanjaFajla=fajlEntiteta(entitet);
			fajloviEntiteta.get(putanjaFajla).remove(entitet);
			if(fajloviEntiteta.get(putanjaFajla).size()<=0) {
				fajloviEntiteta.remove(putanjaFajla);
				putanja.resolve(putanjaFajla).toFile().delete();
			}else {
				ispisiFajl(putanjaFajla);
			}
		}
	}
	
	public void setKonfiguracija(int maxEntiteta) {
		maxBrojEntiteta=maxEntiteta;
	}

	protected abstract boolean validanFajl(Path putanjaFajla);
}
