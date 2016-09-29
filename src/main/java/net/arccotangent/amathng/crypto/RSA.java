package net.arccotangent.amathng.crypto;

import org.apfloat.Apint;
import org.apfloat.ApintMath;

public class RSA {
	
	public static Apint RSA_encrypt(Apint n, Apint e, Apint m) {
		return ApintMath.modPow(m, e, n);
	}
	
	public static Apint RSA_decrypt(Apint n, Apint d, Apint c) {
		return ApintMath.modPow(c, d, n);
	}
	
}
