package api;

public abstract class VrednosniUslov extends Uslov {
	protected Operacija operacija;

	public Operacija getOperacija() {
		return operacija;
	}
	
	public abstract void setOperacija(Operacija operacija);
}
