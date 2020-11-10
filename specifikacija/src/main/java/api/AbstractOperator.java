package api;

import java.util.List;

public abstract class AbstractOperator {
	public AbstractOperator() {
		
	}
	//CRUD
	//READ
	abstract List<Entitet> ucitajUSkladiste();
	//CREATE
	abstract void dodajEntitet(Entitet entitet);
	//UPDATE
	abstract void izmeniEntitet(Entitet entitet);
	//DELETE
	abstract void brisi(Entitet entitet) ;
}
