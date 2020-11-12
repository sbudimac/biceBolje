package api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Entitet {
	private String naziv;
	private String id;
	private Map<String, Object> atributi;
		
	/**
	 * @param naziv Tip entiteta koji oznacava koji drugi entiteti su mu slicni
	 * @param id Identifikator entiteta koji jedinstveno predstavlja entitet u bazi
	 */
	public Entitet(String naziv, String id) {
		this.naziv=naziv;
		this.id=id;
		atributi=new HashMap<String, Object>();
	}
	
	/**
	 * Ovaj konstruktor se koristi kod kreiranja entiteta u okviru Operatora i mozda u okviru dijaloga
	 * @param naziv Tip entiteta koji oznacava koji drugi entiteti su mu slicni
	 * @param id Identifikator entiteta koji jedinstveno predstavlja entitet u bazi
	 * @param atributi Mapa vec dodatih atributa
	 */
	public Entitet(String naziv, String id, Map<String, Object> atributi) {
		this.naziv=naziv;
		this.id=id;
		this.atributi=atributi;
	}
	
	public String getNaziv() {
		return naziv;
	}
	
	public String getId() {
		return id;
	}
	
	Map<String, Object> getAtributi(){
		return atributi;
	}
	
	/**
	 * @param kljuc Kljuc koji odgovara vrednosti
	 * @return Vrednost atributa
	 */
	public Object getAtribut(String kljuc) {
		return atributi.get(kljuc);
	}
	
	void setAtribut(String kljuc, String vrednost) {
		atributi.remove(kljuc);
		Object preradjenaVrednost = ParserPodataka.parse(vrednost);
		if(preradjenaVrednost == null) return;
		atributi.put(kljuc, preradjenaVrednost);
	}
	
	void dodajUgnjezdeni(String kljuc, Entitet entitet) {
		atributi.remove(kljuc);
		atributi.put(kljuc, entitet);
	}
	
	List<Entitet> nadjiUgnjezdene(){
		List<Entitet> ugnjezdeni=new ArrayList<>();
		for (Object atribut : atributi.values()) {
			if(atribut instanceof Entitet) {
				ugnjezdeni.add((Entitet)atribut);
			}
		}
		if(ugnjezdeni.size()!=0) {
			return ugnjezdeni;
		}
		return null;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entitet other = (Entitet) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		String polja="";
		for(Map.Entry<String, Object> atribut : atributi.entrySet()) {
			polja+=" "+atribut.getKey()+": ";
			if(atribut.getValue() instanceof Entitet) {
				polja += "{ " + atribut.getValue().toString() + " }";
			} else {
				polja += atribut.getValue().toString() + ";";
			}
		}
		return "Naziv: "+this.naziv+"; Id: "+this.id + ";" +polja;
	}
}