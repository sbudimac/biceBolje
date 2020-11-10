package api;

public class BrojevniUslov extends VrednosniUslov {
	private Number prag;
	
	BrojevniUslov(String kljuc, Operacija operacija, Number prag) {
		this.kljuc = kljuc;
		this.operacija = operacija;
		this.prag = prag;
	}

	@Override
	public void setOperacija(Operacija operacija) {
		if(operacija.getTip().isTipPodrzan(Tip.BROJ)) {
			this.operacija = operacija;
		}
	}
	
	@Override
	public Object getVrednost() {
		return prag.toString();
	}

	@Override
	public void setVrednost(Object vrednost) {
		if(vrednost instanceof Number) {
			prag = (Number) vrednost;
		}
	}

	@Override
	public boolean test(Entitet entitet) {
		Object vrednost = entitet.getAtribut(this.kljuc);
		if(vrednost != null && vrednost instanceof Number) {
			Number numerickaVrednost = (Number) vrednost;
			switch(this.operacija) {
				case JEDNAKO:
					if(BrojevniPomagac.getInstance().uporedi(numerickaVrednost, prag) == 0) {
						return true;
					}
					break;
				case MANJE:
					if(BrojevniPomagac.getInstance().uporedi(numerickaVrednost, prag) < 0) {
						return true;
					}
					break;
				case MANJE_JEDNAKO:
					if(BrojevniPomagac.getInstance().uporedi(numerickaVrednost, prag) <= 0) {
						return true;
					}
					break;
				case NIJE_JEDNAKO:
					if(BrojevniPomagac.getInstance().uporedi(numerickaVrednost, prag) != 0) {
						return true;
					}
					break;
				case VECE:
					if(BrojevniPomagac.getInstance().uporedi(numerickaVrednost, prag) > 0) {
						return true;
					}
					break;
				case VECE_JEDNAKO:
					if(BrojevniPomagac.getInstance().uporedi(numerickaVrednost, prag) >= 0) {
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
