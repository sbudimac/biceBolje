package api;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class ParserPodataka {
	private static NumberFormat nf = NumberFormat.getInstance();
	public static Object parse(String vrednost) {
		Object preradjenaVrednost = null;
		if(vrednost != "") {
			try {
				preradjenaVrednost = LocalDateTime.parse(vrednost);
			} catch (DateTimeParseException e) {
				e.printStackTrace();
			}
			if(preradjenaVrednost == null) {
				try {
					preradjenaVrednost = nf.parse(vrednost);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if(preradjenaVrednost == null) {
				preradjenaVrednost = vrednost;
			}
		}
		return preradjenaVrednost;
	}
}
