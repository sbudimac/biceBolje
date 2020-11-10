package api;

import java.time.LocalDateTime;
import java.util.Comparator;

public class AttributeComparator implements Comparator<String> {

	@Override
	public int compare(String s1, String s2) {
		int rez=0;
		Object prvi=ParserPodataka.parse(s1);
		Object drugi=ParserPodataka.parse(s2);
		if(prvi==null || drugi==null) {
			return rez;
		}
		if(prvi instanceof String && drugi instanceof String) {
			rez=s1.compareTo(s2);
		}else if(prvi instanceof Number && drugi instanceof Number) {
			Number op1=(Number)prvi;
			Number op2=(Number)drugi;
			rez=BrojevniPomagac.getInstance().uporedi(op1, op2);
		}else if(prvi instanceof LocalDateTime && drugi instanceof LocalDateTime) {
			LocalDateTime op1=(LocalDateTime)prvi;
			LocalDateTime op2=(LocalDateTime)drugi;
			rez=op1.compareTo(op2);
		}
		return rez;
	}

}
