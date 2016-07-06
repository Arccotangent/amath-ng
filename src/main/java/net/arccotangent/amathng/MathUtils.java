package net.arccotangent.amathng;

import org.nevec.rjm.BigDecimalMath;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class MathUtils {

	public static final BigDecimal ZERO = new BigDecimal("0").setScale(Main.NUMBER_PRECISION);
	public static final BigDecimal ONE = new BigDecimal("1").setScale(Main.NUMBER_PRECISION);
	public static final BigDecimal TWO = new BigDecimal("2").setScale(Main.NUMBER_PRECISION);
	public static final BigDecimal FOUR = new BigDecimal("4").setScale(Main.NUMBER_PRECISION);

	public static String strroot(int n, BigDecimal x) {
		boolean i = false;
		if (x.signum() == -1)
			i = true;
		else if (x.signum() == 0)
			return "0";

		return BigDecimalMath.root(n, x).stripTrailingZeros().toEngineeringString() + (i ? "i" : "");
	}

	public static BigDecimal getDiscrimSquared(BigDecimal a, BigDecimal b, BigDecimal c) {
		BigDecimal b2 = b.multiply(b);
		BigDecimal fac = FOUR.multiply(a).multiply(c);
		return b2.subtract(fac);
	}

	public static int getSignificantFigures(BigDecimal input) {
		input = input.stripTrailingZeros();
		return input.scale() < 0
				? input.precision() - input.scale()
				: input.precision();
	}

}
