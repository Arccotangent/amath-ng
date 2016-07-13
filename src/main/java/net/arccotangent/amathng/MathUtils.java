package net.arccotangent.amathng;

import org.apache.commons.lang3.StringUtils;
import org.apfloat.*;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

public class MathUtils {

	public static final Apcomplex ZERO = Main.num("0");
	public static final Apint ZERO_INT = new Apint("0");
	public static final Apcomplex ONE = Main.num("1");
	public static final Apint ONE_INT = new Apint("1");
	public static final Apcomplex TWO = Main.num("2");
	public static final Apint TWO_INT = new Apint("2");
	public static final Apcomplex THREE = Main.num("3");
	public static final Apint THREE_INT = new Apint("3");
	public static final Apcomplex FOUR = Main.num("4");
	public static final Apint FOUR_INT = new Apint("4");
	public static final Apcomplex TEN = Main.num("10");
	public static final Apint TEN_INT = new Apint("10");
	public static final Apcomplex ONE_HUNDRED = Main.num("100");
	public static final Apint ONE_HUNDRED_INT = new Apint("100");

	public static Apcomplex toDegrees(Apcomplex radian) {
		Apcomplex a = Main.num("180").divide(ApfloatMath.pi(Main.NUMBER_PRECISION));
		return radian.multiply(a);
	}

	public static Apcomplex toRadians(Apcomplex degree) {
		Apcomplex a = ApfloatMath.pi(Main.NUMBER_PRECISION).divide(Main.num("180"));
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
		Apfloat[] sorted = unsorted;

		for (int i = 0; i < sorted.length; i++) {
			for (int j = i + 1; j < sorted.length; j++) {
				Apfloat tmp;
				if (sorted[i].compareTo(sorted[j]) == 1) {
					tmp = sorted[i];
					sorted[i] = sorted[j];
					sorted[j] = tmp;
				}
			}
		}
		return sorted;
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
		BigInteger tmp = new BigInteger(bits, rng);
		tmp = tmp.nextProbablePrime(); //Uses a certainty of 100 (amath-ng's default), which should be good enough

		return new Apint(tmp);
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


	public static Apcomplex getCompoundInterest(Apcomplex principal, Apcomplex pct_rate, Apcomplex compounds, Apcomplex time) {
		pct_rate = pct_rate.divide(ONE_HUNDRED);

		Apcomplex total_compounds = compounds.multiply(time);
		Apcomplex urate = ONE.add(pct_rate);
		urate = urate.divide(compounds);
		Apcomplex factor = ApcomplexMath.pow(urate, total_compounds);

		return principal.multiply(factor);
	}

	public static Apcomplex getPercentError(Apcomplex accepted, Apcomplex experimental) {
		Apcomplex numer = ApcomplexMath.abs(accepted.subtract(experimental));
		Apcomplex error = numer.divide(accepted);
		return error.multiply(ONE_HUNDRED);
	}

	public static Apcomplex getDiscrimSquared(Apcomplex a, Apcomplex b, Apcomplex c) {
		Apcomplex b2 = b.multiply(b);
		Apcomplex fac = FOUR.multiply(a).multiply(c);
		return b2.subtract(fac);
	}

	public static long getSignificantFigures(Apcomplex input) {
		return (input.scale() < 0
						? input.precision() - input.scale()
						: input.precision());
	}

	public static Apcomplex probability(int certainty) {
		Apcomplex ONE_HALF = Main.num("0.5");
		Apcomplex a = ApcomplexMath.pow(ONE_HALF, Main.num(Integer.toString(certainty)));
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
		Apint dx = x;

		while (dx.mod(TWO_INT).equals(ZERO)) {
			factorz.add(TWO_INT);
			dx = dx.divide(TWO_INT);
		}

		Apint sqrtx = ApintMath.sqrt(x)[0].add(ONE_INT);

		for (Apint i = THREE_INT; i.compareTo(sqrtx) <= 0; i = i.add(TWO_INT)) {
			while (dx.mod(i).equals(ZERO_INT)) {
				factorz.add(i);
				dx = dx.divide(i);
			}
		}

		factorz.add(dx);

		return factorz;
	}

	public static Apcomplex[] getVertex(Apcomplex a, Apcomplex b, Apcomplex c) {
		Apcomplex x, y;

		//We solve by completing the square here
		//Side work
		Apcomplex b2 = b.divide(a);
		b2 = b2.divide(TWO);
		Apcomplex cs = ApcomplexMath.pow(b2, TWO);
		//End side work

		Apcomplex neg_cs = cs.negate();
		y = c.add(neg_cs);
		x = ApcomplexMath.sqrt(cs);

		if (b.real().compareTo(ZERO.real()) == -1)
			x = x.negate(); //"drop" minus sign if b is negative

		x = x.negate(); //vertex x is negated

		Apcomplex[] vertex = {x, y};
		return vertex;
	}

	public static boolean verifyVertex(Apcomplex a, Apcomplex b, Apcomplex x) {
		Apcomplex neg_b = b.negate();
		Apcomplex test_x = neg_b.divide(a);

		if (test_x.equals(x))
			return true;
		else
			return false;
	}

}
