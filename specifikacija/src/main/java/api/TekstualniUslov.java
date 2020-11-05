package api;

public class TekstualniUslov extends Uslov {
	private String term;
	
	TekstualniUslov(String kljuc, Operacija operacija, String term) {
		this.kljuc = kljuc;
		this.operacija = operacija;
		this.term = term;
	}

	@Override
	public void setOperacija(Operacija operacija) {
		if(operacija.getTip().isTipPodrzan(Tip.TEKST)) {
			this.operacija = operacija;
		}
	}

	@Override
	public Object getVrednost() {
		return term;
	}

	@Override
	public void setVrednost(Object vrednost) {
		if(vrednost instanceof String) {
			term = (String) vrednost;
		}
	}

	@Override
	public boolean poredi(Entitet entitet) {
		Object vrednost = entitet.getAtribut(this.kljuc);
		if(vrednost != null && vrednost instanceof String) {
			String tekst = (String) vrednost;
			switch(this.operacija) {
				case JEDNAKO:
					if(tekst.equals(term)) {
						return true;
					}
					break;
				case NIJE_JEDNAKO:
					if(!tekst.equals(term)) {
						return true;
					}
					break;
				case POCINJE_SA:
					if(tekst.startsWith(term)) {
						return true;
					}
					break;
				case SADRZI:
					if(tekst.contains(term)) {
						return true;
					}
					break;
				case ZAVRSAVA_SA:
					if(tekst.endsWith(term)) {
						return true;
					}
					break;
				default:
					break;
			}
		}
		return false;
	}

}
