package net.arccotangent.amathng.utils;

import net.arccotangent.amathng.Main;
import org.apache.commons.lang3.StringUtils;
import org.apfloat.*;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

public class MathUtils {

	public static final Apcomplex ZERO = NumberHelper.create("0");
	public static final Apint ZERO_INT = new Apint("0");
	public static final Apcomplex ONE = NumberHelper.create("1");
	public static final Apint ONE_INT = new Apint("1");
	public static final Apcomplex TWO = NumberHelper.create("2");
	public static final Apint TWO_INT = new Apint("2");
	public static final Apcomplex THREE = NumberHelper.create("3");
	public static final Apint THREE_INT = new Apint("3");
	public static final Apcomplex FOUR = NumberHelper.create("4");
	public static final Apcomplex TEN = NumberHelper.create("10");
	public static final Apcomplex ONE_HUNDRED = NumberHelper.create("100");

	public static Apcomplex toDegrees(Apcomplex radian) {
		Apcomplex a = NumberHelper.create("180").divide(ApfloatMath.pi(Main.NUMBER_PRECISION));
		return radian.multiply(a);
	}

	public static Apcomplex toRadians(Apcomplex degree) {
		Apcomplex a = ApfloatMath.pi(Main.NUMBER_PRECISION).divide(NumberHelper.create("180"));
		return degree.multiply(a);
	}

	public static Apint combination(Apint n, Apint r) {
		if (n.compareTo(r) == -1) {
			return ZERO_INT;
		}

		Apint numer = factorial(n);
		Apint denom = factorial(r).multiply(factorial(n.subtract(r)));

		return numer.divide(denom);
	}

	public static Apint permutation(Apint n, Apint r) {
		Apint numer = factorial(n);
		Apint denom = factorial(n.subtract(r));

		return numer.divide(denom);
	}

	public static Apfloat[] sort(Apfloat[] unsorted) {
		//bubble sort
		for (int i = 0; i < unsorted.length; i++) {
			for (int j = i + 1; j < unsorted.length; j++) {
				Apfloat tmp;
				if (unsorted[i].compareTo(unsorted[j]) == 1) {
					tmp = unsorted[i];
					unsorted[i] = unsorted[j];
					unsorted[j] = tmp;
				}
			}
		}
		return unsorted;
	}

	public static Apint[] getPrimes(int amount) {
		Apint[] primez = new Apint[amount];

		Apint num = THREE_INT;
		int i = 0;
		while (i < amount) {
			if (num.toBigInteger().isProbablePrime(Main.CERTAINTY)) {
				primez[i] = num;
				i++;
			}
			num = num.add(TWO_INT);
		}

		return primez;
	}

	public static Apint generateRandomPrime(int bits) {
		SecureRandom rng = new SecureRandom();
		BigInteger num = new BigInteger(bits, rng);
		num = num.nextProbablePrime(); //Uses a certainty of 100 (amath-ng's default), which should be good enough

		return new Apint(num);
	}

	public static Apint[] getFactors(Apint num) {
		ArrayList<Apint> factorz = new ArrayList<>();

		if (num.compareTo(TWO_INT) < 1) {
			throw new IllegalArgumentException("You cannot enter integers less than or equal to 2.");
		}

		for (Apint i = ONE_INT; i.compareTo(num) < 1; i = i.add(ONE_INT)) {
			if (num.mod(i).compareTo(ZERO_INT) == 0) {
				factorz.add(i);
				Apint[] sqrt = ApintMath.sqrt(num);
				if (sqrt[0].compareTo(i) == 0 && sqrt[1].compareTo(ZERO_INT) == 0)
					factorz.add(i);
			}
		}

		Apint[] factors = new Apint[factorz.size()];
		return factorz.toArray(factors);
	}

	public static Apcomplex getPercentError(Apcomplex accepted, Apcomplex experimental) {
		Apcomplex numer = ApcomplexMath.abs(accepted.subtract(experimental));
		Apcomplex error = numer.divide(accepted);
		return error.multiply(ONE_HUNDRED);
	}

	public static long getSignificantFigures(String inputStr) {
		if (inputStr.contains(".")) {
			inputStr = StringUtils.remove(inputStr, '.');
			int firstNonZero = StringUtils.indexOfAnyBut(inputStr, '0');
			return inputStr.substring(firstNonZero).length();
		} else {
			int firstNonZero = StringUtils.indexOfAnyBut(inputStr, '0');
			String inputStrRev = StringUtils.reverse(inputStr);
			int lastNonZero = inputStr.length() - StringUtils.indexOfAnyBut(inputStrRev, '0');
			return inputStr.substring(firstNonZero, lastNonZero).length();
		}
	}

	public static Apcomplex probability(int certainty) {
		Apcomplex ONE_HALF = NumberHelper.create("0.5");
		Apcomplex a = ApcomplexMath.pow(ONE_HALF, NumberHelper.create(Integer.toString(certainty)));
		return ONE.subtract(a);
	}

	public static Apint getRandom(Apint min, Apint max, Random rng) {
		Apint result;
		do {
			result = new Apint(new BigInteger(max.toBigInteger().bitLength(), rng));
			result = result.add(min);
		} while (result.compareTo(max) > 0);

		return result;
	}

	public static Apint factorial(Apint x) {
		Apint res = new Apint(ONE.toString());
		for (int i = x.intValue(); i > 1; i--) {
			res = res.multiply(new Apint(Integer.toString(i)));
		}
		return res;
	}

	public static ArrayList<Apint> factor(Apint x) {
		ArrayList<Apint> factorz = new ArrayList<>();

		while (x.mod(TWO_INT).equals(ZERO)) {
			factorz.add(TWO_INT);
			x = x.divide(TWO_INT);
		}

		Apint sqrtx = ApintMath.sqrt(x)[0].add(ONE_INT); //the first element in the returned array is the square root, the second is the remainder

		for (Apint i = THREE_INT; i.compareTo(sqrtx) <= 0; i = i.add(TWO_INT)) {
			while (x.mod(i).equals(ZERO_INT)) {
				factorz.add(i);
				x = x.divide(i);
			}
		}

		factorz.add(x);

		return factorz;
	}

	public static void printFactors(Apint[] factors) {
		int fc = factors.length;
		int left = 0, right = fc - 1;

		System.out.println("FACTOR + FACTOR = SUM, PRODUCT");
		System.out.println("Found " + fc + " total factors.");
		System.out.println("-----------------------------------------------------------");

		while (left <= right - 1) {
			Apint ln = factors[left];
			Apint rn = factors[right];
			Apint sum = ln.add(rn);
			Apint product = ln.multiply(rn);
			System.out.println(ln.toString(true) + " + " + rn.toString(true) + " = " + sum.toString(true) + ", " + product.toString(true));
			left++;
			right--;
		}
	}

}
