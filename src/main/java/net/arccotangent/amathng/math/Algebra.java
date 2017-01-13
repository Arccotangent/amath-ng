package net.arccotangent.amathng.math;

import net.arccotangent.amathng.utils.MathUtils;
import org.apfloat.Apcomplex;
import org.apfloat.ApcomplexMath;
import org.apfloat.Apint;
import org.apfloat.ApintMath;

import java.math.BigInteger;
import java.util.ArrayList;

public class Algebra {
	
	public static Apcomplex getCompoundInterest(Apcomplex principal, Apcomplex pct_rate, Apcomplex compounds, Apcomplex time) {
		pct_rate = pct_rate.divide(MathUtils.ONE_HUNDRED);
		
		Apcomplex total_compounds = compounds.multiply(time);
		Apcomplex urate = MathUtils.ONE.add(pct_rate);
		urate = urate.divide(compounds);
		Apcomplex factor = ApcomplexMath.pow(urate, total_compounds);
		
		return principal.multiply(factor);
	}
	
	/**
	 * Given a list of integers, calculate their greatest common denominator (GCD)
	 * @param integers A list of integers with which to calculate the GCD
	 * @return The GCD
	 */
	public static Apint gcd(ArrayList<Apint> integers) {
		Apint res = ApintMath.gcd(integers.get(0), integers.get(1));
		for (int i = 2; i < integers.size(); i++) {
			res = ApintMath.gcd(res, integers.get(i));
		}
		
		return res;
	}
	
	/**
	 * Given a list of integers, calculate their least common multiple (LCM)
	 * @param integers A list of integers with which to calculate the LCM
	 * @return The LCM
	 */
	public static Apint lcm(ArrayList<Apint> integers) {
		Apint res = ApintMath.lcm(integers.get(0), integers.get(1));
		for (int i = 2; i < integers.size(); i++) {
			res = ApintMath.lcm(res, integers.get(i));
		}
		
		return res;
	}
	
	public static Apint modularMultiplicativeInverse(Apint a, Apint m) {
		BigInteger a2 = a.toBigInteger();
		BigInteger m2 = m.toBigInteger();
		
		return new Apint(a2.modInverse(m2));
	}
	
}
