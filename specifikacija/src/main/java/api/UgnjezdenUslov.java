package api;

public class UgnjezdenUslov extends Uslov {
	private String kljucPodEntiteta;
	private Uslov podUslov;

	@Override
	public void setOperacija(Operacija operacija) {
		if(operacija.getTip().isTipPodrzan(Tip.ENTITET)) {
			this.operacija = operacija;
		}
	}

	@Override
	public Object getVrednost() {
		return kljucPodEntiteta;
	}

	@Override
	public void setVrednost(Object vrednost) {
		if(vrednost instanceof String) {
			kljucPodEntiteta = (String) vrednost;
		}
	}

	@Override
	public boolean poredi(Entitet entitet) {
		Object vrednost = entitet.getAtribut(this.kljucPodEntiteta);
		if(vrednost != null && vrednost instanceof Entitet) {
			Entitet podEntitet = (Entitet) vrednost;
			return podUslov.poredi(podEntitet);
		}
		return false;
	}

}
