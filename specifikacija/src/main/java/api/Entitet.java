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
		for (Object atribut : atributi.values()) {
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
}