package api;

import java.time.LocalDateTime;

public class VremenskiUslov extends Uslov {
	private LocalDateTime prag;
	
	VremenskiUslov(String kljuc, Operacija operacija, LocalDateTime prag) {
		this.kljuc = kljuc;
		this.operacija = operacija;
		this.prag = prag;
	}

	@Override
	public void setOperacija(Operacija operacija) {
		if(operacija.getTip().isTipPodrzan(Tip.DATUMVREME)) {
			this.operacija = operacija;
		}
	}

	@Override
	public Object getVrednost() {
		return prag;
	}

	@Override
	public void setVrednost(Object vrednost) {
		if(vrednost instanceof LocalDateTime) {
			prag = (LocalDateTime) vrednost;
		}
	}

	@Override
	public boolean test(Entitet entitet) {
		Object vrednost = entitet.getAtribut(this.kljuc);
		if(vrednost != null && vrednost instanceof LocalDateTime) {
			LocalDateTime vreme = (LocalDateTime) vrednost;
			switch(this.operacija) {
				case JEDNAKO:
					if(vreme.isEqual(prag)) {
						return true;
					}
					break;
				case MANJE:
					if(vreme.isBefore(prag)) {
						return true;
					}
					break;
				case MANJE_JEDNAKO:
					if(vreme.isBefore(prag) || vreme.isEqual(prag)) {
						return true;
					}
					break;
				case NIJE_JEDNAKO:
					if(!vreme.isEqual(prag)) {
						return true;
					}
					break;
				case VECE:
					if(vreme.isAfter(prag)) {
						return true;
					}
					break;
				case VECE_JEDNAKO:
					if(vreme.isAfter(prag) || vreme.isEqual(prag)) {
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
