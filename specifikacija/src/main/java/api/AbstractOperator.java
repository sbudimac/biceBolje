package api;


public abstract class AbstractOperator {
	public abstract void kreirajSkladiste(String putanja, Skladiste skladiste);
	
	public abstract Skladiste ucitajSkladiste(String putanja);
	
	public abstract void izmeniSkladiste(String putanja, Skladiste skladiste);
	
	public abstract void izbrisiSkladiste(String putanja, Skladiste skladiste);
	
	public abstract void prevodEntiteta(Entitet entitet);
	
	public TipFajla proveriTip(String putanja) {
		return null;
	}
}
