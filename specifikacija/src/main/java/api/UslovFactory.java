package api;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class UslovFactory {
	private static NumberFormat nf = NumberFormat.getInstance();
	
	public static Uslov KreirajUslov(String kljuc, Operacija operacija, String vrednost) {
		Uslov uslov = null;
		if(kljuc.equals("id") || kljuc.equals("naziv")) {
			uslov = new TekstualniUslov(kljuc, operacija, vrednost);
		} else {
			if(operacija.getTip().isTipPodrzan(Tip.DATUMVREME)) {
				try {
					uslov = new VremenskiUslov(kljuc, operacija, LocalDateTime.parse(vrednost));
				} catch (DateTimeParseException e) {
					e.printStackTrace();
				}
			} if(uslov == null && operacija.getTip().isTipPodrzan(Tip.BROJ)) {
				try {
					uslov = new BrojevniUslov(kljuc, operacija, nf.parse(vrednost));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} if(uslov == null) {
				uslov = new TekstualniUslov(kljuc, operacija, vrednost);
			}
		}
		return uslov;
	}
}
