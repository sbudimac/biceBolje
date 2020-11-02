package api;

import java.util.HashMap;

public class Entitet {
	private String naziv;
	private String id;
	private HashMap<String, Object> atributi;
	private String fajl;
	
	public Entitet() {};
	
	public Entitet(String naziv, String id) {
		this.naziv=naziv;
		this.id=id;
		atributi=new HashMap<String, Object>();
	}
	
	public String getNaziv() {
		return naziv;
	}
	
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public HashMap<String, Object> getAtributi() {
		return atributi;
	}
	
	public String getFajl() {
		return fajl;
	}
	
	public void setFajl(String fajl) {
		this.fajl=fajl;
	}
	
	public void setAtributi(HashMap<String, Object> atributi) {
		this.atributi = atributi;
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