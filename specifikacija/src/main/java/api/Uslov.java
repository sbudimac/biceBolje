package api;

import java.util.function.Predicate;

public abstract class Uslov implements Predicate<Entitet> {
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
}
