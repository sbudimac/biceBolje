package api;

import java.util.Comparator;
import java.util.Date;
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
			rez=0;
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
					Double op1=((Number)e1.getAtribut(kriterijum)).doubleValue();
					Double op2=((Number)e2.getAtribut(kriterijum)).doubleValue();
					rez=op1.compareTo(op2);
				}else if(e1.getAtribut(kriterijum) instanceof Date && e2.getAtribut(kriterijum) instanceof Date) {
					Date op1=(Date)e1.getAtribut(kriterijum);
					Date op2=(Date)e2.getAtribut(kriterijum);
					rez=op1.compareTo(op2);
				}
			}
			if(rez!=0) {
				return rez;
			}
		}
		return rez;
	}

}
