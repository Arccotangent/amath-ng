package net.arccotangent.amathng.math;

import net.arccotangent.amathng.utils.MathUtils;
import net.arccotangent.amathng.utils.NumberHelper;
import org.apfloat.Apcomplex;
import org.apfloat.ApcomplexMath;

public class Cubic {

	private static Apcomplex getCubeRootOfUnity() {
		Apcomplex isqrt3 = ApcomplexMath.sqrt(NumberHelper.create("-3"));
		Apcomplex numer = isqrt3.subtract(MathUtils.ONE); //same as adding -1

		return numer.divide(MathUtils.TWO);
	}

	public static Apcomplex getDiscrim(Apcomplex a, Apcomplex b, Apcomplex c, Apcomplex d) {
		//discriminant = 18abcd - 4b^3d + b^2c^2 - 4ac^3 - 27a^2d^2

		Apcomplex term1 = NumberHelper.create("18").multiply(a).multiply(b).multiply(c).multiply(d);

		Apcomplex bCubed = ApcomplexMath.pow(b, 3);
		Apcomplex term2 = bCubed.multiply(d).multiply(MathUtils.FOUR);

		Apcomplex bSquared = ApcomplexMath.pow(b, 2);
		Apcomplex cSquared = ApcomplexMath.pow(c, 2);
		Apcomplex term3 = bSquared.multiply(cSquared);

		Apcomplex cCubed = ApcomplexMath.pow(c, 3);
		Apcomplex term4 = a.multiply(cCubed).multiply(MathUtils.FOUR);

		Apcomplex aSquared = ApcomplexMath.pow(a, 2);
		Apcomplex dSquared = ApcomplexMath.pow(d, 2);
		Apcomplex term5 = NumberHelper.create("27").multiply(aSquared).multiply(dSquared);

		return term1.subtract(term2).add(term3).subtract(term4).subtract(term5);
	}

	public static Apcomplex getT0(Apcomplex a, Apcomplex b, Apcomplex c) {
		Apcomplex bSquared = ApcomplexMath.pow(b, 2);
		Apcomplex threeXAC = a.multiply(c).multiply(MathUtils.THREE);

		return bSquared.subtract(threeXAC);
	}

	public static Apcomplex getT1(Apcomplex a, Apcomplex b, Apcomplex c, Apcomplex d) {
		Apcomplex bCubed = ApcomplexMath.pow(b, 3);
		Apcomplex term1 = bCubed.multiply(MathUtils.TWO);

		Apcomplex term2 = NumberHelper.create("9").multiply(a).multiply(b).multiply(c);

		Apcomplex aSquared = ApcomplexMath.pow(a, 2);
		Apcomplex term3 = NumberHelper.create("27").multiply(aSquared).multiply(d);

		return term1.subtract(term2).add(term3);
	}

	public static Apcomplex getC(Apcomplex t0, Apcomplex t1) {
		Apcomplex t1Squared = ApcomplexMath.pow(t1, 2);
		Apcomplex t0Cubed = ApcomplexMath.pow(t0, 3);

		Apcomplex cbrtNumerTerm2 = ApcomplexMath.sqrt(t1Squared.subtract(t0Cubed.multiply(MathUtils.FOUR)));
		Apcomplex cbrtDenom = MathUtils.TWO;

		boolean addSign = true;
		if (t0.equals(MathUtils.ZERO)) {
			Apcomplex cbrtNumer = t1.add(cbrtNumerTerm2);
			if (cbrtNumer.equals(MathUtils.ZERO)) {
				addSign = false;
			}
		}


		if (addSign) {
			Apcomplex cbrtNumer = t1.add(cbrtNumerTerm2);
			Apcomplex cbrt = cbrtNumer.divide(cbrtDenom);

			return ApcomplexMath.cbrt(cbrt);
		} else {
			Apcomplex cbrtNumer = t1.subtract(cbrtNumerTerm2);
			Apcomplex cbrt = cbrtNumer.divide(cbrtDenom);

			return ApcomplexMath.cbrt(cbrt);
		}
	}

	public static Apcomplex[] getSolutions(Apcomplex a, Apcomplex b, Apcomplex c, Apcomplex d, Apcomplex C, Apcomplex t0, Apcomplex discrim) {
		Apcomplex[] solutionz = new Apcomplex[3];

		if (t0.equals(MathUtils.ZERO) && discrim.equals(MathUtils.ZERO)) { //triple root
			Apcomplex threeA = a.multiply(MathUtils.THREE);
			Apcomplex sol = b.negate().divide(threeA);
			solutionz[0] = sol;
			solutionz[1] = sol;
			solutionz[2] = sol;
		} else if (discrim.equals(MathUtils.ZERO) && !t0.equals(MathUtils.ZERO)) { //double root
			Apcomplex nineAD = NumberHelper.create("9").multiply(a).multiply(d);
			Apcomplex bc = b.multiply(c);
			Apcomplex twoT0 = t0.multiply(MathUtils.TWO);

			Apcomplex term1Numer = nineAD.subtract(bc);
			Apcomplex solutionAB = term1Numer.divide(twoT0);

			solutionz[0] = solutionAB;
			solutionz[1] = solutionAB;

			Apcomplex fourABC = MathUtils.FOUR.multiply(a).multiply(b).multiply(c);
			Apcomplex aSquared = ApcomplexMath.pow(a, 2);
			Apcomplex nineA2D = NumberHelper.create("9").multiply(aSquared).multiply(d);
			Apcomplex bCubed = ApcomplexMath.pow(b, 3);
			Apcomplex aXT0 = a.multiply(t0);

			Apcomplex term1 = fourABC.subtract(nineA2D).subtract(bCubed);
			Apcomplex solutionC = term1.divide(aXT0);

			solutionz[2] = solutionC;
		} else if (!C.equals(MathUtils.ZERO)) {
			Apcomplex negOneOver3A = MathUtils.ONE.divide(MathUtils.THREE.multiply(a));
			negOneOver3A = negOneOver3A.negate();

			Apcomplex cbrtOfUnityToZero = MathUtils.ONE;
			Apcomplex parTerm2A = C.multiply(cbrtOfUnityToZero);

			Apcomplex parTerm3A = t0.divide(parTerm2A);

			Apcomplex term2A = b.add(parTerm2A).add(parTerm3A);
			Apcomplex solutionA = negOneOver3A.multiply(term2A);

			solutionz[0] = solutionA;

			Apcomplex cbrtOfUnity = getCubeRootOfUnity();
			Apcomplex parTerm2B = C.multiply(cbrtOfUnity);

			Apcomplex parTerm3B = t0.divide(parTerm2B);

			Apcomplex term2B = b.add(parTerm2B).add(parTerm3B);
			Apcomplex solutionB = negOneOver3A.multiply(term2B);

			solutionz[1] = solutionB;

			Apcomplex cbrtOfUnitySquared = getCubeRootOfUnity().multiply(getCubeRootOfUnity());
			Apcomplex parTerm2C = C.multiply(cbrtOfUnitySquared);

			Apcomplex parTerm3C = t0.divide(parTerm2C);

			Apcomplex term2C = b.add(parTerm2C).add(parTerm3C);
			Apcomplex solutionC = negOneOver3A.multiply(term2C);

			solutionz[2] = solutionC;
		} else {
			System.out.println("***!!! C = 0, T0 != 0, discrim != 0 !!!***");
			solutionz[0] = MathUtils.ZERO;
			solutionz[1] = MathUtils.ZERO;
			solutionz[2] = MathUtils.ZERO;
		}

		return solutionz;
	}

}
