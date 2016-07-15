package net.arccotangent.amathng;

import net.arccotangent.amathng.utils.NumberHelper;
import org.apfloat.*;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

public class MathUtils {

	public static final Apcomplex ZERO = NumberHelper.create("0");
	private static final Apint ZERO_INT = new Apint("0");
	public static final Apcomplex ONE = NumberHelper.create("1");
	static final Apint ONE_INT = new Apint("1");
	public static final Apcomplex TWO = NumberHelper.create("2");
	static final Apint TWO_INT = new Apint("2");
	public static final Apcomplex THREE = NumberHelper.create("3");
	private static final Apint THREE_INT = new Apint("3");
	public static final Apcomplex FOUR = NumberHelper.create("4");
	static final Apcomplex TEN = NumberHelper.create("10");
	static final Apcomplex ONE_HUNDRED = NumberHelper.create("100");

	public static Apcomplex toDegrees(Apcomplex radian) {
		Apcomplex a = NumberHelper.create("180").divide(ApfloatMath.pi(Main.NUMBER_PRECISION));
		return radian.multiply(a);
	}

	public static Apcomplex toRadians(Apcomplex degree) {
		Apcomplex a = ApfloatMath.pi(Main.NUMBER_PRECISION).divide(NumberHelper.create("180"));
		return degree.multiply(a);
	}

	static Apint combination(Apint n, Apint r) {
		if (n.compareTo(r) == -1) {
			return ZERO_INT;
		}

		Apint numer = factorial(n);
		Apint denom = factorial(r).multiply(factorial(n.subtract(r)));

		return numer.divide(denom);
	}

	static Apint permutation(Apint n, Apint r) {
		Apint numer = factorial(n);
		Apint denom = factorial(n.subtract(r));

		return numer.divide(denom);
	}

	static Apfloat[] sort(Apfloat[] unsorted) {
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

	static Apint[] getPrimes(int amount) {
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

	static Apint generateRandomPrime(int bits) {
		SecureRandom rng = new SecureRandom();
		BigInteger num = new BigInteger(bits, rng);
		num = num.nextProbablePrime(); //Uses a certainty of 100 (amath-ng's default), which should be good enough

		return new Apint(num);
	}

	static Apint[] getFactors(Apint num) {
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


	static Apcomplex getCompoundInterest(Apcomplex principal, Apcomplex pct_rate, Apcomplex compounds, Apcomplex time) {
		pct_rate = pct_rate.divide(ONE_HUNDRED);

		Apcomplex total_compounds = compounds.multiply(time);
		Apcomplex urate = ONE.add(pct_rate);
		urate = urate.divide(compounds);
		Apcomplex factor = ApcomplexMath.pow(urate, total_compounds);

		return principal.multiply(factor);
	}

	static Apcomplex getPercentError(Apcomplex accepted, Apcomplex experimental) {
		Apcomplex numer = ApcomplexMath.abs(accepted.subtract(experimental));
		Apcomplex error = numer.divide(accepted);
		return error.multiply(ONE_HUNDRED);
	}

	static long getSignificantFigures(Apcomplex input) {
		return (input.scale() < 0
						? input.precision() - input.scale()
						: input.precision());
	}

	static Apcomplex probability(int certainty) {
		Apcomplex ONE_HALF = NumberHelper.create("0.5");
		Apcomplex a = ApcomplexMath.pow(ONE_HALF, NumberHelper.create(Integer.toString(certainty)));
		return ONE.subtract(a);
	}

	static Apint getRandom(Apint min, Apint max, Random rng) {
		Apint result;
		do {
			result = new Apint(new BigInteger(max.toBigInteger().bitLength(), rng));
			result = result.add(min);
		} while (result.compareTo(max) > 0);

		return result;
	}

	static Apint factorial(Apint x) {
		Apint res = new Apint(ONE.toString());
		for (int i = x.intValue(); i > 1; i--) {
			res = res.multiply(new Apint(Integer.toString(i)));
		}
		return res;
	}

	static ArrayList<Apint> factor(Apint x) {
		ArrayList<Apint> factorz = new ArrayList<>();

		while (x.mod(TWO_INT).equals(ZERO)) {
			factorz.add(TWO_INT);
			x = x.divide(TWO_INT);
		}

		Apint sqrtx = ApintMath.sqrt(x)[0].add(ONE_INT);

		for (Apint i = THREE_INT; i.compareTo(sqrtx) <= 0; i = i.add(TWO_INT)) {
			while (x.mod(i).equals(ZERO_INT)) {
				factorz.add(i);
				x = x.divide(i);
			}
		}

		factorz.add(x);

		return factorz;
	}

	static Apcomplex[] getVertex(Apcomplex a, Apcomplex b, Apcomplex c) {
		Apcomplex x, y;

		//We solve by completing the square here
		//Side work
		Apcomplex b2 = b.divide(a);
		b2 = b2.divide(TWO);
		Apcomplex b2Squared = ApcomplexMath.pow(b2, TWO);
		//End side work

		Apcomplex negb2Squared = b2Squared.negate();
		y = c.add(negb2Squared);
		x = ApcomplexMath.sqrt(b2Squared);

		if (b.real().compareTo(ZERO.real()) == -1)
			x = x.negate(); //"drop" minus sign if b is negative

		x = x.negate(); //vertex x is negated

		return new Apcomplex[]{x, y};
	}

	static boolean verifyVertex(Apcomplex a, Apcomplex b, Apcomplex x) {
		Apcomplex neg_b = b.negate();
		Apcomplex test_x = neg_b.divide(a);

		return test_x.equals(x);
	}

	static void printFactors(Apint[] factors) {
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
