package api;

public enum Tip {
	BROJ(1),
	DATUMVREME(2),
	BROJDATUMVREME(3),
	TEKST(4),
	BROJTEKST(5),
	DATUMVREMETEKST(6),
	BROJDATUMVREMETEKST(7),
	ENTITET(8),
	TEKSTENTITET(12),
	SVI(15),
	NEPOZNAT(0);
	
	private final byte bajtReprezentacija;
	
	private Tip(int bajtReprezentacija) {
		this.bajtReprezentacija = (byte)bajtReprezentacija;
	}
	
	public boolean isTipPodrzan(Tip tip) {
		return (this.bajtReprezentacija & tip.bajtReprezentacija) == tip.bajtReprezentacija;
	}
}
