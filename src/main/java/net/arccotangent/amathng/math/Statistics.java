package net.arccotangent.amathng.math;

import net.arccotangent.amathng.Main;
import net.arccotangent.amathng.utils.MathUtils;
import net.arccotangent.amathng.utils.NumberHelper;
import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;
import org.apfloat.Apint;

public class Statistics {
	
	/**
	 * Gaussian error function
	 * @param z Value
	 * @return erf(z)
	 */
	public static Apfloat erf(Apfloat z) {
		Apfloat sqrtPi = ApfloatMath.sqrt(NumberHelper.create("pi", Main.RADIX).real());
		Apfloat term1 = MathUtils.TWO.real().divide(sqrtPi);
		
		boolean negate = false;
		Apint n = MathUtils.ZERO_INT;
		Apfloat prev;
		Apfloat current = MathUtils.ZERO_INT;
		do {
			prev = current;
			
			long twoNplusOne = (MathUtils.TWO_INT.multiply(n)).add(MathUtils.ONE_INT).longValue();
			Apfloat numerTerm2 = ApfloatMath.pow(z, twoNplusOne);
			
			Apfloat numer;
			
			if (negate) {
				numer = numerTerm2.negate();
				negate = false;
			} else {
				numer = numerTerm2;
				negate = true;
			}
			
			Apfloat nFactorial = MathUtils.factorial(n);
			Apfloat denom = nFactorial.multiply(new Apfloat((double)twoNplusOne));
			
			current = current.add(numer.divide(denom));
			n = n.add(MathUtils.ONE_INT);
		} while (prev.compareTo(current) != 0);
		
		return term1.multiply(current);
	}
	
	/**
	 * Cumulative distribution function
	 * @param x Value x
	 * @return cdf(x)
	 */
	public static Apfloat cdf(Apfloat x) {
		Apfloat ONE_HALF = NumberHelper.create("0.5", Main.RADIX).real();
		Apfloat sqrtTwo = ApfloatMath.sqrt(MathUtils.TWO.real());
		
		Apfloat error = erf(x.divide(sqrtTwo));
		error = MathUtils.ONE.real().add(error);
		
		return ONE_HALF.multiply(error);
	}
	
}
