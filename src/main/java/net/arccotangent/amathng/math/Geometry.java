package net.arccotangent.amathng.math;

import net.arccotangent.amathng.utils.MathUtils;
import org.apfloat.Apcomplex;
import org.apfloat.ApcomplexMath;

public class Geometry {
	
	public static Apcomplex getDistanceSquared(Apcomplex x1, Apcomplex x2, Apcomplex y1, Apcomplex y2) {
		Apcomplex distanceX = x2.subtract(x1);
		Apcomplex distanceY = y2.subtract(y1);
		return distanceX.add(distanceY);
	}
	
	public static Apcomplex[] getMidpoint(Apcomplex x1, Apcomplex x2, Apcomplex y1, Apcomplex y2) {
		Apcomplex sumX = x1.add(x2);
		Apcomplex sumY = y1.add(y2);
		
		Apcomplex midpointX = sumX.divide(MathUtils.TWO);
		Apcomplex midpointY = sumY.divide(MathUtils.TWO);
		
		return new Apcomplex[]{midpointX, midpointY};
	}
	
	public static Apcomplex getSlope(Apcomplex x1, Apcomplex x2, Apcomplex y1, Apcomplex y2) {
		Apcomplex run = x2.subtract(x1);
		Apcomplex rise = y2.subtract(y1);
		
		return rise.divide(run);
	}
	
	public static Apcomplex hypot(Apcomplex a, Apcomplex b) {
		Apcomplex a2 = ApcomplexMath.pow(a, 2);
		Apcomplex b2 = ApcomplexMath.pow(b, 2);
		Apcomplex c2 = a2.add(b2);
		return ApcomplexMath.sqrt(c2);
	}
	
}
