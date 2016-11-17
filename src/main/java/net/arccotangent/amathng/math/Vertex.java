package net.arccotangent.amathng.math;

import net.arccotangent.amathng.utils.MathUtils;
import org.apfloat.Apcomplex;
import org.apfloat.ApcomplexMath;


public class Vertex {
	
	public static Apcomplex[] getVertex(Apcomplex a, Apcomplex b, Apcomplex c) {
		Apcomplex x, y;
		
		//We solve by completing the square here
		//Side work
		Apcomplex b2 = b.divide(a);
		b2 = b2.divide(MathUtils.TWO);
		Apcomplex b2Squared = ApcomplexMath.pow(b2, MathUtils.TWO);
		//End side work
		
		Apcomplex negb2Squared = b2Squared.negate();
		y = c.add(negb2Squared);
		x = ApcomplexMath.sqrt(b2Squared);
		
		if (b.real().compareTo(MathUtils.ZERO.real()) == -1)
			x = x.negate(); //"drop" minus sign if b is negative
		
		x = x.negate(); //vertex x is negated
		
		return new Apcomplex[]{x, y};
	}
	
	public static boolean verifyVertex(Apcomplex a, Apcomplex b, Apcomplex x) {
		Apcomplex neg_b = b.negate();
		Apcomplex test_x = neg_b.divide(MathUtils.TWO.multiply(a));
		
		return test_x.equals(x);
	}
	
}
