package api;

public class Uslov {
	private String kljuc;
	private Operacija operacija;
	private Object vrednost;
	
	public Uslov() {};
	
	public Uslov(String kljuc, Operacija operacija, Object vrednost) {
		this.kljuc=kljuc;
		this.operacija=operacija;
		this.vrednost=vrednost;
	}
	
	public String getKljuc() {
		return kljuc;
	}
	
	public void setKljuc(String kljuc) {
		this.kljuc=kljuc;
	}
	
	public Operacija getOperacija() {
		return operacija;
	}
	
	public void setOperacija(Operacija operacija) {
		this.operacija=operacija;
	}
	
	public Object getVrednost() {
		return vrednost;
	}
	
	public void setVrednost(Object vrednost) {
		this.vrednost=vrednost;
	}
	
	public boolean poredi(Uslov this, Entitet entitet) {
		Object value;
		if(this.kljuc=="naziv") {
			value=entitet.getNaziv();
		}else if(this.kljuc=="id") {
			value=entitet.getId();
		}else {
			value=entitet.getAtribut(this.kljuc);
		}
		if(value==null) {
			return false;
		}
		switch(this.operacija) {
			case JEDNAKO:
				if(this.vrednost.equals(value)) {
					return true;
				}else {
					return false;
				}
			case NIJE_JEDNAKO:
				if(!(this.vrednost.equals(value))) {
					return true;
				}else {
					return false;
				}
			case MANJE:
				if(value instanceof Number && vrednost instanceof Number) {
					if((Double)value<(Double)vrednost) {
						return true;
					}else {
						return false;
					}
				}else {
					return false;
				}
			case VECE:
				if(value instanceof Number && vrednost instanceof Number) {
					if((Double)value>(Double)vrednost) {
						return true;
					}else {
						return false;
					}
				}else {
					return false;
				}
			case MANJE_JEDNAKO:
				if(value instanceof Number && vrednost instanceof Number) {
					if((Double)value<=(Double)vrednost) {
						return true;
					}else {
						return false;
					}
				}else {
					return false;
				}
			case VECE_JEDNAKO:
				if(value instanceof Number && vrednost instanceof Number) {
					if((Double)value>=(Double)vrednost) {
						return true;
					}else {
						return false;
					}
				}else {
					return false;
				}
			case SADRZI:
				if(value instanceof String && vrednost instanceof String) {
					if(value.toString().contains(this.vrednost.toString())) {
						return true;
					}else {
						return false;
					}
				}else {
					return false;
				}
			case POCINJE_SA:
				if(value instanceof String && vrednost instanceof String) {
					if(value.toString().startsWith(this.vrednost.toString())) {
						return true;
					}else {
						return false;
					}
				}else {
					return false;
				}
			case ZAVRSAVA_SA:
				if(value instanceof String && vrednost instanceof String) {
					if(value.toString().endsWith(this.vrednost.toString())) {
						return true;
					}else {
						return false;
					}
				}else {
					return false;
				}
			default:
				return false;
		}
	}
}
