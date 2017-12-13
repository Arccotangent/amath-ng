package net.arccotangent.amathng.math;

import net.arccotangent.amathng.Main;
import net.arccotangent.amathng.utils.MathUtils;
import net.arccotangent.amathng.utils.NumberHelper;
import org.apfloat.*;

import java.util.ArrayList;

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
	public static Apcomplex[] linreg(Apcomplex[][] values) {
		if (values[0].length != values[1].length)
			return null;
		
		int valueAmount = values[0].length;
		
		Apcomplex xSum = MathUtils.ZERO.real();
		Apcomplex ySum = MathUtils.ZERO.real();
		Apcomplex xySum = MathUtils.ZERO.real();
		Apcomplex x2Sum = MathUtils.ZERO.real();
		Apcomplex y2Sum = MathUtils.ZERO.real();
		
		for (int i = 0; i < valueAmount; i++) {
			Apcomplex x = values[0][i];
			Apcomplex y = values[1][i];
			Apcomplex xy = x.multiply(y);
			
			xSum = xSum.add(x);
			x2Sum = x2Sum.add(ApcomplexMath.pow(x, MathUtils.TWO));
			
			ySum = ySum.add(y);
			y2Sum = y2Sum.add(ApcomplexMath.pow(y, MathUtils.TWO));
			
			xySum = xySum.add(xy);
		}
		
		Apcomplex slopeNumer = new Apfloat(valueAmount, Main.REGRESSION_PRECISION).multiply(xySum).subtract(xSum.multiply(ySum));
		Apcomplex slopeDenom = new Apfloat(valueAmount, Main.REGRESSION_PRECISION).multiply(x2Sum).subtract(ApcomplexMath.pow(xSum, MathUtils.TWO));
		
		Apcomplex slope = slopeNumer.divide(slopeDenom);
		
		Apcomplex interceptNumer = ySum.subtract(slope.multiply(xSum));
		Apcomplex interceptDenom = new Apfloat(valueAmount);
		
		Apcomplex intercept = interceptNumer.divide(interceptDenom);
		
		return new Apcomplex[] {slope, intercept};
	}
	
	/**
	 * Calculate the correlation coefficient (Pearson) for a set of data.
	 * @param values A 2xN array holding the x and y values to be inserted into the linear regression equation in the following format:<br>
	 *               values[0][N] = x values<br>
	 *               values[1][N] = y values
	 * @return r, the correlation coefficient. Will be null if an error occurs (eg. more x values than y values).
	 */
	public static Apcomplex pearsonCorrelation(Apcomplex[][] values) {
		if (values[0].length != values[1].length)
			return null;
		
		int valueAmount = values[0].length;
		
		Apcomplex xAvg = MathUtils.ZERO;
		Apcomplex yAvg = MathUtils.ZERO;
		
		for (int i = 0; i < valueAmount; i++) {
			xAvg = xAvg.add(values[0][i]);
			yAvg = yAvg.add(values[1][i]);
		}
		
		xAvg = xAvg.divide(new Apfloat(valueAmount));
		yAvg = yAvg.divide(new Apfloat(valueAmount));
		
		Apcomplex xAvgDiffTimesYAvgDiffSum = MathUtils.ZERO.real();
		Apcomplex xAvgDiff2Sum = MathUtils.ZERO.real();
		Apcomplex yAvgDiff2Sum = MathUtils.ZERO.real();
		
		for (int i = 0; i < valueAmount; i++) {
			Apcomplex x = values[0][i];
			Apcomplex y = values[1][i];
			
			xAvgDiffTimesYAvgDiffSum = xAvgDiffTimesYAvgDiffSum.add(x.subtract(xAvg).multiply(y.subtract(yAvg)));
			
			xAvgDiff2Sum = xAvgDiff2Sum.add(ApcomplexMath.pow(x.subtract(xAvg), MathUtils.TWO));
			yAvgDiff2Sum = yAvgDiff2Sum.add(ApcomplexMath.pow(y.subtract(yAvg), MathUtils.TWO));
		}
		
		Apcomplex numer = xAvgDiffTimesYAvgDiffSum;
		Apcomplex denom = ApcomplexMath.sqrt(xAvgDiff2Sum).multiply(ApcomplexMath.sqrt(yAvgDiff2Sum)).real();
		
		return numer.divide(denom);
	}
	
	/**
	 * Get the mode of an array of sorted numbers
	 * @param sortedNumbers Array of sorted numbers
	 * @return An array of modes, blank if there are no modes
	 */
	public static Apfloat[] getModes(Apfloat[] sortedNumbers) {
		int modeOccurrences = 1;
		int maxModeOccurrences = 1;
		Apfloat temp;
		ArrayList<Apfloat> modes = new ArrayList<>();
		
		for (int i = 1; i < sortedNumbers.length; i++) {
			if (sortedNumbers[i].compareTo(sortedNumbers[i - 1]) == 0) {
				temp = sortedNumbers[i];
				modeOccurrences++;
				if (modeOccurrences > maxModeOccurrences) {
					modes.clear();
					modes.add(temp);
					maxModeOccurrences = modeOccurrences;
					i = 0;
				} else if (modeOccurrences == maxModeOccurrences) {
					modes.add(temp);
				}
			} else {
				modeOccurrences = 0;
			}
		}
		
		Apfloat[] modesArray = new Apfloat[modes.size()];
		modes.toArray(modesArray);
		
		return modesArray;
	}
	
	/**
	 * Get the medians of an array of sorted numbers
	 * @param sortedNumbers Array of sorted numbers
	 * @return An array of medians, either size 1 or 2
	 */
	public static Apfloat[] getMedians(Apfloat[] sortedNumbers) {
		int left = 0;
		int right = sortedNumbers.length - 1;
		
		while (right - left >= 2) {
			left++;
			right--;
		}
		
		if (left == right) {
			return new Apfloat[] {sortedNumbers[left]};
		} else {
			return new Apfloat[] {sortedNumbers[left], sortedNumbers[right]};
		}
	}
	
}
