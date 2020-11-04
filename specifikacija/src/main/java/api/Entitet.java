package api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Entitet {
	private String naziv;
	private String id;
	private Map<String, Object> atributi;
	
	public Entitet() {};
	
	public Entitet(String naziv, String id) {
		this.naziv=naziv;
		this.id=id;
		atributi=new HashMap<String, Object>();
	}
	
	public String getNaziv() {
		return naziv;
	}
	
	public String getId() {
		return id;
	}
	
	public Object getAtribut(String kljuc) {
		return atributi.get(kljuc);
	}
	
	public List<Entitet> getUgnjezdeni(){
		List<Entitet> ugnjezdeni=new ArrayList<>();
		for (Object atribut : atributi.keySet()) {
			if(atribut instanceof Entitet) {
				ugnjezdeni.add((Entitet)atribut);
			}
		}
		return ugnjezdeni;
	}
	
	public void dodajAtribut(String key, Object value) {
		atributi.put(key, value);
	}
	
	public void obrisiAtribut(String key) {
		atributi.remove(key);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Entitet) {
			Entitet entitet=(Entitet)obj;
			if(this.getId().equals(entitet.getId())){
				return true;
			}
		}
		return false;
	}
}