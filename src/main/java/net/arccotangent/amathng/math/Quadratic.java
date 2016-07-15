package net.arccotangent.amathng.math;

import net.arccotangent.amathng.MathUtils;
import org.apfloat.Apcomplex;

public class Quadratic {

	public static Apcomplex getDiscrimSquared(Apcomplex a, Apcomplex b, Apcomplex c) {
		Apcomplex bSquared = b.multiply(b);
		Apcomplex sqrt = MathUtils.FOUR.multiply(a).multiply(c);
		return bSquared.subtract(sqrt);
	}

	public static Apcomplex[] solve(Apcomplex a, Apcomplex b, Apcomplex discriminant) {
		Apcomplex neg_b = b.negate();
		Apcomplex ta = a.multiply(MathUtils.TWO);
		Apcomplex x1 = neg_b.add(discriminant).divide(ta);
		Apcomplex x2 = neg_b.subtract(discriminant).divide(ta);

		return new Apcomplex[]{x1, x2};
	}

}
