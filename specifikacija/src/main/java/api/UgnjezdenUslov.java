package api;

public class UgnjezdenUslov extends Uslov {
	private Uslov podUslov;
	
	public UgnjezdenUslov(String kljuc, Uslov podUslov) {
		this.kljuc=kljuc;
		this.podUslov=podUslov;
	}

	@Override
	public Object getVrednost() {
		return kljuc;
	}

	@Override
	public void setVrednost(Object vrednost) {
		if(vrednost instanceof String) {
			kljuc = (String) vrednost;
		}
	}

	@Override
	public boolean test(Entitet entitet) {
		Object vrednost = entitet.getAtribut(kljuc);
		if(vrednost != null && vrednost instanceof Entitet) {
			Entitet podEntitet = (Entitet) vrednost;
			return podUslov.test(podEntitet);
		}
		return false;
	}

}
