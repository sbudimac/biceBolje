package api;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public class EntitetKomparator implements Comparator<Entitet> {
	List<String> kriterijumi;
	
	public EntitetKomparator(List<String> kriterijumi) {
		this.kriterijumi=kriterijumi;
	}

	@Override
	public int compare(Entitet e1, Entitet e2) {
		int rez=0;
		for (String kriterijum : kriterijumi) {
			if(kriterijum=="naziv") {
				rez=e1.getNaziv().compareTo(e2.getNaziv());
			}else if(kriterijum=="id") {
				rez=e1.getId().compareTo(e2.getId());
			}else {
				if(e1.getAtribut(kriterijum)==null || e2.getAtribut(kriterijum)==null) {
					return 0;
				}
				if(e1.getAtribut(kriterijum) instanceof String && e2.getAtribut(kriterijum) instanceof String) {
					String op1=(String)e1.getAtribut(kriterijum);
					String op2=(String)e2.getAtribut(kriterijum);
					rez=op1.compareTo(op2);
				}else if(e1.getAtribut(kriterijum) instanceof Number && e2.getAtribut(kriterijum) instanceof Number) {
					Number op1=(Number)e1.getAtribut(kriterijum);
					Number op2=(Number)e2.getAtribut(kriterijum);
					rez=BrojevniPomagac.getInstance().uporedi(op1, op2);
				}else if(e1.getAtribut(kriterijum) instanceof LocalDateTime && e2.getAtribut(kriterijum) instanceof LocalDateTime) {
					LocalDateTime op1=(LocalDateTime)e1.getAtribut(kriterijum);
					LocalDateTime op2=(LocalDateTime)e2.getAtribut(kriterijum);
					rez=op1.compareTo(op2);
				}
			}
			if(rez!=0) {
				break;
			}
		}
		return rez;
	}

}
