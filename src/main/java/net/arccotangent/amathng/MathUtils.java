package net.arccotangent.amathng;

import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;
import org.apfloat.Apint;
import org.apfloat.ApintMath;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

public class MathUtils {

	public static final Apfloat ZERO = new Apfloat("0", Main.NUMBER_PRECISION);
	public static final Apint ZERO_INT = new Apint("0");
	public static final Apfloat ONE = new Apfloat("1", Main.NUMBER_PRECISION);
	public static final Apint ONE_INT = new Apint("1");
	public static final Apfloat TWO = new Apfloat("2", Main.NUMBER_PRECISION);
	public static final Apint TWO_INT = new Apint("2");
	public static final Apfloat THREE = new Apfloat("3", Main.NUMBER_PRECISION);
	public static final Apint THREE_INT = new Apint("3");
	public static final Apfloat FOUR = new Apfloat("4", Main.NUMBER_PRECISION);
	public static final Apint FOUR_INT = new Apint("4");

	public static Apfloat getDiscrimSquared(Apfloat a, Apfloat b, Apfloat c) {
		Apfloat b2 = b.multiply(b);
		Apfloat fac = FOUR.multiply(a).multiply(c);
		return b2.subtract(fac);
	}

	public static long getSignificantFigures(Apfloat input) {
		return (input.scale() < 0
						? input.precision() - input.scale()
						: input.precision());
	}

	public static Apfloat probability(int certainty) {
		Apfloat ONE_HALF = new Apfloat(0.5);
		Apfloat a = ApfloatMath.pow(ONE_HALF, new Apfloat(certainty));
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

	public static Apint gcd(Apint a, Apint b) {
		Apint r = a.mod(b);
		return null;
	}

}
