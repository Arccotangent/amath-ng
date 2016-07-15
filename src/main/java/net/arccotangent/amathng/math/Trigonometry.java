package net.arccotangent.amathng.math;

import net.arccotangent.amathng.MathUtils;
import org.apfloat.Apcomplex;
import org.apfloat.ApcomplexMath;

public class Trigonometry {

	public static Apcomplex lawOfSines(Apcomplex angleA, Apcomplex angleB, Apcomplex sideA) {
		Apcomplex sinAngleA = ApcomplexMath.sin(MathUtils.toRadians(angleA));
		Apcomplex sinAngleB = ApcomplexMath.sin(MathUtils.toRadians(angleB));
		Apcomplex a = sideA.divide(sinAngleA);
		return a.multiply(sinAngleB);
	}

	public static Apcomplex lawOfCosines(Apcomplex sideA, Apcomplex sideB, Apcomplex sideC) {
		Apcomplex sideASquared = ApcomplexMath.pow(sideA, 2);
		Apcomplex sideBSquared = ApcomplexMath.pow(sideB, 2);
		Apcomplex sideCSquared = ApcomplexMath.pow(sideC, 2);

		Apcomplex twoAB = sideA.multiply(sideB).multiply(MathUtils.TWO);
		Apcomplex cosAngleC = sideASquared.add(sideBSquared).subtract(sideCSquared);
		cosAngleC = cosAngleC.divide(twoAB);

		return MathUtils.toDegrees(ApcomplexMath.acos(cosAngleC));
	}

}
