package crudCustom;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import api.Entitet;
import api.FileOperator;
import api.ParserPodataka;

public class CustomOperator extends FileOperator {

	public CustomOperator(String putanja) {
		super(putanja);
	}

	@Override
	protected List<Entitet> prevediFajl(File fajl) {
		List<Entitet> entiteti=new ArrayList<>();
		if(validanFajl(fajl)) {
			try {
				BufferedReader reader=new BufferedReader(new FileReader(fajl));
				String linija;
				while((linija=reader.readLine())!=null) {
					entiteti.add(parsirajEntitet(linija));
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return entiteti;
	}
	
	private Entitet parsirajEntitet(String tekst) {
		String[] atributi=tekst.split(";", 3);
		String[] kljucVrednost = atributi[0].split(":");
		String naziv = kljucVrednost[1].strip();
		kljucVrednost = atributi[1].split(":");
		String id = kljucVrednost[1].strip();
		
		Map<String, Object> mapa = new HashMap<>();
		
		if(atributi.length < 3) {
			tekst = "";
		} else {
			tekst = atributi[2];
		}
		int indeks = tekst.indexOf(';');
		while(indeks != -1) {
			int indeksUgnjezdenog = tekst.indexOf('{');
			if(indeksUgnjezdenog != -1 && indeksUgnjezdenog < indeks) {
				String kljuc = tekst.split(":", 2)[0].strip();
				String podEntitet = tekst.substring(indeksUgnjezdenog + 1);
				tekst = parsirajUgnjezdeni(podEntitet, kljuc, mapa);
			} else {
				atributi = tekst.split(";", 2);
				kljucVrednost = atributi[0].split(":");
				String kljuc = kljucVrednost[0].strip();
				String vrednost = kljucVrednost[1].strip();
				Object preradjenaVrednost = ParserPodataka.parse(vrednost);
				mapa.put(kljuc, preradjenaVrednost);
				if(atributi.length < 2) {
					tekst = "";
				} else {
					tekst = atributi[1];
				}
			}
			indeks = tekst.indexOf(';');
		}
		return new Entitet(naziv, id, mapa);
	}
	
	private String parsirajUgnjezdeni(String tekst, String kljucSpoljasnjeg, Map<String, Object> mapaSpoljasnjeg) {
		String[] atributi=tekst.split(";", 3);
		String[] kljucVrednost = atributi[0].split(":");
		String naziv = kljucVrednost[1].strip();
		kljucVrednost = atributi[1].split(":");
		String id = kljucVrednost[1].strip();
		
		Map<String, Object> mapa = new HashMap<>();
		
		if(atributi.length < 3) {
			tekst = "";
		} else {
			tekst = atributi[2];
		}
		int indeks = tekst.indexOf(';');
		int indeksZatvora = tekst.indexOf('}');
		while(indeks != -1 && indeks < indeksZatvora) {
			int indeksUgnjezdenog = tekst.indexOf('{');
			if(indeksUgnjezdenog != -1 && indeksUgnjezdenog < indeks) {
				String kljuc = tekst.split(":", 2)[0].strip();
				String podEntitet = tekst.substring(indeksUgnjezdenog + 1);
				tekst = parsirajUgnjezdeni(podEntitet, kljuc, mapa);
			} else {
				atributi = tekst.split(";", 2);
				kljucVrednost = atributi[0].split(":");
				String kljuc = kljucVrednost[0].strip();
				String vrednost = kljucVrednost[1].strip();
				Object preradjenaVrednost = ParserPodataka.parse(vrednost);
				mapa.put(kljuc, preradjenaVrednost);
				tekst = atributi[1];
			}
			indeks = tekst.indexOf(';');
			indeksZatvora = tekst.indexOf('}');
		}
		mapaSpoljasnjeg.put(kljucSpoljasnjeg, new Entitet(naziv, id, mapa));
		return tekst.substring(indeksZatvora + 1);
	}
	
	/*private String parsirajEntitet(String id, String naziv, String tekst, List<Entitet> entiteti) {
		Map<String, Object> mapa = new HashMap<>();
		int indeks = tekst.indexOf(';');
		int indeksZatvora = tekst.indexOf('}');
		while(indeks != -1 && (indeksZatvora == -1 || indeks < indeksZatvora)) {
			int indeksUgnjezdenog = tekst.indexOf('{');
			if(indeksUgnjezdenog != -1 && indeksUgnjezdenog < indeks) {
				String podEntitet = tekst.substring(indeksUgnjezdenog + 1);
				String[] atributi=podEntitet.split(";", 3);
				String[] kljucVrednost = atributi[0].split(":");
				String naziv = kljucVrednost[1].strip();
				kljucVrednost = atributi[1].split(":");
				String id = kljucVrednost[1].strip();
				Entitet ugnjezden = new Entitet(naziv, id);
				entiteti.add(ugnjezden);
				tekst = parsirajEntitet(ugnjezden, atributi[2], entiteti);
			} else {
				String[] atributi = tekst.split(";", 2);
				String[] kljucVrednost = atributi[0].split(":");
				String kljuc = kljucVrednost[0].strip();
				String vrednost = kljucVrednost[1].strip();
				Object preradjenaVrednost = ParserPodataka.parse(vrednost);
				mapa.put(kljuc, preradjenaVrednost);
				tekst = atributi[1];
			}
			indeks = tekst.indexOf(';');
			indeksZatvora = tekst.indexOf('}');
		}
		if(indeksZatvora != -1) {
			tekst = tekst.substring(indeksZatvora + 1);
		}
		return tekst;
	}*/

	@Override
	public String prevediEntitet(Entitet entitet) {
		return entitet.toString();
	}

	@Override
	protected void ispisiFajl(Path putanjaFajla) {
		List<Entitet> entiteti=fajloviEntiteta.get(putanjaFajla);
		try {
			boolean prviPut = true;
			Writer writer=new FileWriter(putanja.resolve(putanjaFajla).toFile(), false);
			for (Entitet entitet : entiteti) {
				if(!prviPut) {
					writer.write("\n");
				} else {
					prviPut = false;
				}
				writer.write(entitet.toString());
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validanFajl(File fajl) {
		try {
			Scanner reader=new Scanner(fajl);
			String fileStart=reader.nextLine();
			if(fileStart.charAt(0)=='N') {
				reader.close();
				return true;
			}else {
				reader.close();
				return false;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

}
