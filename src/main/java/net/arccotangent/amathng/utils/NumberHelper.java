package net.arccotangent.amathng.utils;

import org.apache.commons.lang3.StringUtils;
import org.apfloat.Apcomplex;
import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;

public class NumberHelper {
	
	/**
	 * Create a zero number
	 * @return A number with a value of 0+0i
	 */
	public static Apcomplex create(long precision) {
		Apfloat real = new Apfloat("0", precision);
		Apfloat imag = new Apfloat("0", precision);
		return new Apcomplex(real, imag);
	}
	
	/**
	 * Create a number with a set real and/or imaginary value
	 * @param real_value The value in the form a+bi, constants are supported
	 * @param radix The base of the number, 10 for decimal, 2 for binary, 16 for hexadecimal, etc
	 * @return The number
	 * @throws IllegalArgumentException If the real value is invalid
	 */
	public static Apcomplex create(String real_value, int radix, long precision) throws IllegalArgumentException {
		if (real_value.equalsIgnoreCase("pi")) {
			Apfloat real = ApfloatMath.pi(precision);
			Apfloat imag = new Apfloat("0", precision, radix);
			return new Apcomplex(real, imag);
		} else if (real_value.equalsIgnoreCase("e")) {
			Apfloat real = ApfloatMath.exp(MathUtils.ONE.real());
			Apfloat imag = new Apfloat("0", precision, radix);
			return new Apcomplex(real, imag);
		} else if (real_value.equalsIgnoreCase("-i")) {
			Apfloat real = new Apfloat("0", precision, radix);
			Apfloat imag = new Apfloat("-1", precision, radix);
			return new Apcomplex(real, imag);
		} else {
			if (!real_value.contains("i")) {
				Apfloat real = new Apfloat(real_value, precision, radix);
				Apfloat imag = new Apfloat("0", precision, radix);
				return new Apcomplex(real, imag);
			} else {
				boolean neg_real = false;
				boolean neg_imag = false;
				boolean neg_first_term = false;
				real_value = StringUtils.replace(real_value, "I", "i");
				
				if (real_value.charAt(0) == '-') {
					neg_first_term = true;
					real_value = StringUtils.removeStart(real_value, "-");
				}
				
				String r, i;
				
				String[] vals = real_value.split("[+-]");
				if (vals[0].isEmpty()) {
					vals[0] = vals[1];
					if (vals.length == 3)
						vals[1] = vals[2];
				}
				
				
				if (!vals[0].contains("i")) {
					if (real_value.charAt(0) == '-' || neg_first_term) {
						neg_real = true;
					}
					
					if (real_value.substring(1).contains("-")) {
						neg_imag = true;
					}
					r = vals[0];
					i = vals[1];
				} else {
					if (real_value.charAt(0) == '-' || neg_first_term) {
						neg_imag = true;
					}
					r = "0";
					i = vals[0];
				}
				
				if (vals.length != 2) {
					String imagstr = StringUtils.remove(vals[0], "i");
					if (imagstr.isEmpty())
						imagstr = "1";
					else if (imagstr.equals("-"))
						imagstr = "-1";
					
					if (neg_imag || neg_first_term) {
						imagstr = "-" + imagstr;
					}
					Apfloat real = new Apfloat("0", precision, radix);
					Apfloat imag = new Apfloat(imagstr, precision, radix);
					return new Apcomplex(real, imag);
				}
				
				i = StringUtils.remove(i, "i");
				
				if (neg_real)
					r = "-" + r;
				
				if (neg_imag)
					i = "-" + i;
				
				return create(r, i, precision);
			}
		}
	}
	
	private static Apcomplex create(String real_value, String imaginary_value, long precision) {
		Apfloat real = new Apfloat(real_value, precision);
		Apfloat imag = new Apfloat(imaginary_value, precision);
		return new Apcomplex(real, imag);
	}
	
	/**
	 * Format a number as a String
	 * @param toFormat The number to format
	 * @return The formatted string in a+bi form
	 */
	public static String format(Apcomplex toFormat) {
		String real = toFormat.real().toString(true);
		String imag = toFormat.imag().toString(true);
		String fmt;
		
		if (real.equals("0")) {
			if (imag.equals("0"))
				fmt = "0";
			else if (imag.equals("1"))
				fmt = "i";
			else if (imag.equals("-1"))
				fmt = "-i";
			else
				fmt = imag + "i";
		} else {
			if (imag.equals("0"))
				fmt = real;
			else if (imag.equals("1"))
				fmt = real + " + i";
			else if (imag.equals("-1"))
				fmt = real + " - i";
			else if (imag.contains("-")) {
				imag = StringUtils.remove(imag, "-");
				fmt = real + " - " + imag + "i";
			} else
				fmt = real + " + " + imag + "i";
		}
		
		return fmt;
	}
	
}
