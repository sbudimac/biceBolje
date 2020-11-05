package api;

public abstract class Uslov {
	protected String kljuc;
	protected Operacija operacija;
	
	public String getKljuc() {
		return kljuc;
	}
	
	public Operacija getOperacija() {
		return operacija;
	}
	
	public abstract void setOperacija(Operacija operacija);
	
	public abstract Object getVrednost();
	
	public abstract void setVrednost(Object vrednost);
	
	public abstract boolean poredi(Entitet entitet);
}
