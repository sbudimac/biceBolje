package api;

import java.util.function.Predicate;

public abstract class Uslov implements Predicate<Entitet> {
	protected String kljuc;
	
	public String getKljuc() {
		return kljuc;
	}
	
	public abstract Object getVrednost();
	
	public abstract void setVrednost(Object vrednost);
}
