package api;

import java.util.List;

public abstract class AbstractOperator {
	public AbstractOperator() {
		
	}
	//CRUD
	//READ
	public abstract List<Entitet> ucitajUSkladiste();
	//CREATE
	public abstract void dodajEntitet(Entitet entitet);
	//UPDATE
	public abstract void izmeniEntitet(Entitet entitet);
	//DELETE
	public abstract void brisi(List<Entitet> entiteti) ;
}
