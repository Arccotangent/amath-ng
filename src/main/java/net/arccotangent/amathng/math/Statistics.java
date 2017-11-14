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
		Apfloat sqrtPi = ApfloatMath.sqrt(NumberHelper.create("pi", Main.RADIX, Main.NUMBER_PRECISION).real());
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
		Apfloat ONE_HALF = NumberHelper.create("0.5", Main.RADIX, Main.NUMBER_PRECISION).real();
		Apfloat sqrtTwo = ApfloatMath.sqrt(MathUtils.TWO.real());
		
		Apfloat error = erf(x.divide(sqrtTwo));
		error = MathUtils.ONE.real().add(error);
		
		return ONE_HALF.multiply(error);
	}
	
	/**
	 * Linear regression line calculation function
	 * @param values A 2xN array holding the x and y values to be inserted into the linear regression equation in the following format:<br>
	 *               values[0][N] = x values<br>
	 *               values[1][N] = y values
	 * @return A 2 element array holding the coefficient for x and the y-intercept. Will be null if an error occurs (eg. more x values than y values).
	 */
	public static Apfloat[] linreg(Apfloat[][] values) {
		if (values[0].length != values[1].length)
			return null;
		
		int valueAmount = values[0].length;
		
		Apfloat xSum = MathUtils.ZERO.real();
		Apfloat ySum = MathUtils.ZERO.real();
		Apfloat xySum = MathUtils.ZERO.real();
		Apfloat x2Sum = MathUtils.ZERO.real();
		Apfloat y2Sum = MathUtils.ZERO.real();
		
		for (int i = 0; i < valueAmount; i++) {
			Apfloat x = values[0][i];
			Apfloat y = values[1][i];
			Apfloat xy = x.multiply(y);
			
			xSum = xSum.add(x);
			x2Sum = x2Sum.add(ApfloatMath.pow(x, MathUtils.TWO.real()));
			
			ySum = ySum.add(y);
			y2Sum = y2Sum.add(ApfloatMath.pow(y, MathUtils.TWO.real()));
			
			xySum = xySum.add(xy);
		}
		
		Apfloat slopeNumer = new Apfloat(valueAmount, Main.REGRESSION_PRECISION).multiply(xySum).subtract(xSum.multiply(ySum));
		Apfloat slopeDenom = new Apfloat(valueAmount, Main.REGRESSION_PRECISION).multiply(x2Sum).subtract(ApfloatMath.pow(xSum, MathUtils.TWO.real()));
		
		Apfloat slope = slopeNumer.divide(slopeDenom);
		
		Apfloat interceptNumer = ySum.subtract(slope.multiply(xSum));
		Apfloat interceptDenom = new Apfloat(valueAmount);
		
		Apfloat intercept = interceptNumer.divide(interceptDenom);
		
		return new Apfloat[] {slope, intercept};
	}
	
}
