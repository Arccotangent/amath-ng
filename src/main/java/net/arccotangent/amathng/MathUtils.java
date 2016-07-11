package net.arccotangent.amathng;

import org.apfloat.Apcomplex;
import org.apfloat.ApcomplexMath;
import org.apfloat.Apint;
import org.apfloat.ApintMath;

import java.math.BigInteger;
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

	public static Apint gcd(Apint a, Apint b) {
		Apint r = a.mod(b);
		if (!r.equals(ZERO_INT)) {
			return gcd(b, r);
		}
		return b;
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
