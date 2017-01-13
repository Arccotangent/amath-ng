package net.arccotangent.amathng.math;

import net.arccotangent.amathng.utils.MathUtils;
import org.apfloat.Apcomplex;

public class Quadratic {
	
	/**
	 * Get the discriminant of a quadratic equation
	 * @param a The first coefficient of the quadratic equation (ax^2)
	 * @param b The second coefficient of the quadratic equation (bx)
	 * @param c The third coefficient, the constant
	 * @return The discriminant
	 */
	public static Apcomplex getDiscrimSquared(Apcomplex a, Apcomplex b, Apcomplex c) {
		Apcomplex bSquared = b.multiply(b);
		Apcomplex sqrt = MathUtils.FOUR.multiply(a).multiply(c);
		return bSquared.subtract(sqrt);
	}
	
	/**
	 * Solve a quadratic equation
	 * @param a The first coefficient of the quadratic equation (ax^2)
	 * @param b The second coefficient of the quadratic equation (bx)
	 * @param discriminant The discriminant
	 * @return An array containing the solutions
	 */
	public static Apcomplex[] solve(Apcomplex a, Apcomplex b, Apcomplex discriminant) {
		Apcomplex neg_b = b.negate();
		Apcomplex ta = a.multiply(MathUtils.TWO);
		Apcomplex x1 = neg_b.add(discriminant).divide(ta);
		Apcomplex x2 = neg_b.subtract(discriminant).divide(ta);

		return new Apcomplex[]{x1, x2};
	}

}
