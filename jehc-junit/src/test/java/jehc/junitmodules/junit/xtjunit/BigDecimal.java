package jehc.junitmodules.junit.xtjunit;

public class BigDecimal {
	public static void main(String[]args){
		java.math.BigDecimal amount = new java.math.BigDecimal("0.00");
		amount = amount.add(new java.math.BigDecimal("12.00"));
		System.out.println(amount);
	}
}
