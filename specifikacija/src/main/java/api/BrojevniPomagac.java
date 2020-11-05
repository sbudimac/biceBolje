package api;

public class BrojevniPomagac {
	private static BrojevniPomagac instance;
	private double epsilon = 0.0002;
	
	private BrojevniPomagac() {
	}
	
	public static BrojevniPomagac getInstance() {
		if(instance == null) {
			instance = new BrojevniPomagac();
		}
		return instance;
	}
	
	public int uporedi(Number n1, Number n2) {
		if(Math.abs(n1.doubleValue() - n2.doubleValue()) < epsilon) {
			return 0;
		} else if(n1.doubleValue() - n2.doubleValue() > epsilon) {
			return 1;
		} else if(n1.doubleValue() - n2.doubleValue() < -epsilon) {
			return -1;
		}
		return 0;
	}
}
