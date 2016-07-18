package net.arccotangent.amathng.math;

import net.arccotangent.amathng.utils.MathUtils;
import org.apfloat.Apcomplex;
import org.apfloat.ApcomplexMath;

public class Algebra {
	
	public static Apcomplex getCompoundInterest(Apcomplex principal, Apcomplex pct_rate, Apcomplex compounds, Apcomplex time) {
		pct_rate = pct_rate.divide(MathUtils.ONE_HUNDRED);
		
		Apcomplex total_compounds = compounds.multiply(time);
		Apcomplex urate = MathUtils.ONE.add(pct_rate);
		urate = urate.divide(compounds);
		Apcomplex factor = ApcomplexMath.pow(urate, total_compounds);
		
		return principal.multiply(factor);
	}
	
}
