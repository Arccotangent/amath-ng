package net.arccotangent.amathng;

import org.apache.commons.lang3.StringUtils;
import org.apfloat.*;

import java.security.SecureRandom;
import java.util.ArrayList;

public class Main {

	public static final String VERSION = "20160713";
	public static final long NUMBER_PRECISION = Configuration.getPrecision(); //Precision in significant figures
	public static final int CERTAINTY = Configuration.getCertainty(); //Probability of prime number = 1 - 0.5^CERTAINTY

	public static Apcomplex num() {
		Apfloat real = new Apfloat("0", NUMBER_PRECISION);
		Apfloat imag = new Apfloat("0", NUMBER_PRECISION);
		return new Apcomplex(real, imag);
	}

	public static Apcomplex num(String real_value) throws IllegalArgumentException {
		if (real_value.equalsIgnoreCase("pi")) {
			Apfloat real = ApfloatMath.pi(NUMBER_PRECISION);
			Apfloat imag = new Apfloat("0", NUMBER_PRECISION);
			return new Apcomplex(real, imag);
		} else if (real_value.equalsIgnoreCase("-i")) {
			Apfloat real = new Apfloat("0", NUMBER_PRECISION);
			Apfloat imag = new Apfloat("-1", NUMBER_PRECISION);
			return new Apcomplex(real, imag);
		} else {
			if (!real_value.contains("i")) {
				Apfloat real = new Apfloat(real_value, NUMBER_PRECISION);
				Apfloat imag = new Apfloat("0", NUMBER_PRECISION);
				return new Apcomplex(real, imag);
			} else {
				boolean neg_r = false;
				boolean neg_i = false;
				boolean neg_0 = false;
				real_value = StringUtils.replace(real_value, "I", "i");

				if (real_value.charAt(0) == '-') {
					neg_0 = true;
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
					if (real_value.charAt(0) == '-' || neg_0) {
						neg_r = true;
					}

					if (real_value.substring(1).contains("-")) {
						neg_i = true;
					}
					r = vals[0];
					i = vals[1];
				} else {
					if (real_value.charAt(0) == '-' || neg_0) {
						neg_i = true;
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

					if (neg_i || neg_0) {
						imagstr = "-" + imagstr;
					}
					Apfloat real = new Apfloat("0", NUMBER_PRECISION);
					Apfloat imag = new Apfloat(imagstr, NUMBER_PRECISION);
					return new Apcomplex(real, imag);
				}

				i = StringUtils.remove(i, "i");

				if (neg_r)
					r = "-" + r;

				if (neg_i)
					i = "-" + i;

				return num(r, i);
			}
		}
	}

	public static Apcomplex num(String real_value, String imaginary_value) {
		Apfloat real = new Apfloat(real_value, NUMBER_PRECISION);
		Apfloat imag = new Apfloat(imaginary_value, NUMBER_PRECISION);
		return new Apcomplex(real, imag);
	}

	public static String fc(Apcomplex toFormat) {
		String real = toFormat.real().toString(true);
		String imag = toFormat.imag().toString(true);
		String fmt;

		if (real.equals("0")) {
			if (imag.equals("0")) {
				fmt = "0";
			} else if (imag.equals("1")) {
				fmt = "i";
			} else if (imag.equals("-1")) {
				fmt = "-i";
			} else {
				fmt = imag + "i";
			}
		} else {
			if (imag.equals("0")) {
				fmt = real;
			} else if (imag.equals("1")) {
				fmt = real + " + i";
			} else if (imag.equals("-1")) {
				fmt = real + " - i";
			} else if (imag.contains("-")) {
				imag = StringUtils.remove(imag, "-");
				fmt = real + " - " + imag + "i";
			} else {
				fmt = real + " + " + imag + "i";
			}
		}

		return fmt;
	}

	public static void main(String[] args) {
		int argc = args.length - 1;
		if (args.length < 1) {
			System.out.println("amath-ng " + VERSION + " - A Command Line Calculator by Arccotangent\n" +
					"Usage: amath-ng <operation> <numbers>\n" +
					"List of operations below\n" +
					"\n--General--\n\n" +
					"add <2+ numbers> - Add numbers together\n" +
					"sub <2+ numbers> - Subtract numbers\n" +
					"mul <2+ numbers> - Multiply numbers\n" +
					"div <2+ numbers> - Divide numbers\n" +
					"mod <2 numbers> - Get modulus of numbers (division remainder)\n" +
					"exp <base> <exponent> - Calculate BASE^EXPONENT\n" +
					"sqrt <1 number> - Square root\n" +
					"cbrt <1 number> - Cube root\n" +
					"fct <number> - Factorial of number\n" +
					"fac <number> - Get prime factors of number by trial division (slow, only for small integers)\n" +
					"gcd <2 numbers> - Get GCD (greatest common denominator) of numbers\n" +
					"lcm <2 numbers> - Get LCM (least common multiple) of numbers\n" +
					"\n--Algebra--\n\n" +
					"qdr <a> <b> <c> - Solve quadratic equation equal to 0\n" +
					"cbc <a> <b> <c> <d> - Solve cubic equation equal to 0\n" +
					"vtx <a> <b> <c> - Get vertex of quadratic equation equal to y OR 0\n" +
					"log <number> - Natural logarithm\n" +
					"log10 <number> - Base 10 logarithm\n" +
					"logb <base> <number> - Logarithm with custom base\n" +
					"cpi <principal> <% rate> <compounds per year> <time in years> - Calculate compound interest\n" +
					"getf <number> - Get factors of number in bundles of 2, then display their sum (to assist in solving quadratic equations using the factoring method)\n" +
					"st <term number> <common difference> <first term> - Find the nth (TERM NUMBER) term of an arithmetic sequence\n" +
					"ss <term count> <first term> <nth term> - Find the sum of n (TERM COUNT) terms in an arithmetic sequence\n" +
					"ncr <n> <r> - Combination (nCr)\n" +
					"npr <n> <r> - Permutation (nPr)\n" +
					"\n--Geometry--\n\n" +
					"aoc <radius> - Calculate approximate area of circle\n" +
					"hypot <side1> <side2> - Get hypotenuse of right triangle\n" +
					//"dst <x1> <y1> <x2> <y2> - Get distance between 2 points\n" +
					"\n--Statistics--\n\n" +
					"avg <numbers> - Calculate average of numbers\n" +
					"stdev <numbers> - Calculate standard deviation of numbers\n" +
					"zsc <data> <average> <standard deviation> - Get z-score of a number\n" +
					"\n--Trigonometry--\n\n" +
					"sin <number> - Trigonometric function - Sine - opposite / hypotenuse\n" +
					"cos <number> - Trigonometric function - Cosine - adjacent / hypotenuse\n" +
					"tan <number> - Trigonometric function - Tangent - opposite / adjacent\n" +
					"asin <number> - Trigonometric function - Arcsine\n" +
					"acos <number> - Trigonometric function - Arccosine\n" +
					"atan <number> - Trigonometric function - Arctangent\n" +
					"los <side A> <side B> <angle A> - Trigonometric law of sines - Returns angle B (across from side B)\n" +
					"loc <side A> <side B> <side C> - Trigonometric law of cosines - Returns angle C (across from side C)\n" +
					"csc <number> - Reciprocal Trigonometric Function - Cosecant - hypotenuse / opposite\n" +
					"sec <number> - Reciprocal Trigonometric Function - Secant - hypotenuse / adjacent\n" +
					"cot <number> - Reciprocal Trigonometric Function - Cotangent - adjacent / opposite\n" +
					"acsc <number> - Reciprocal Trigonometric Function - Arccosecant\n" +
					"asec <number> - Reciprocal Trigonometric Function - Arcsecant\n" +
					"acot <number> - Reciprocal Trigonometric Function - Arccotangent\n" +
					"\n--Science--\n\n" +
					"sf <1 number> - Get amount of significant figures in number\n" +
					"pcr <accepted> <experimental> - Calculate percent error\n" +
					"hl <amount> - Print amount of half lives with respective ratios\n" +
					"\n--Miscellaneous--\n\n" +
					"psq <amount> - Print AMOUNT perfect squares starting with 1\n" +
					"ppwr <amount> <exponent> - Print AMOUNT bases to EXPONENT starting with 1\n" +
					"pprm <amount> - Print AMOUNT probable prime numbers starting with 3\n" +
					"ord <numbers> - Order numbers smallest to greatest\n" +
					"ccm <radius> - Calculate circumference of circle\n" +
					"rand <min> <max> [seed] - Generate random integer between MIN and MAX with optional SEED\n" +
					"prm <number> - Test if number is a probable prime by Miller-Rabin and Lucas-Lehmer primality tests\n" +
					"genprm <bits> - Generate a prime number with bitsize BITS\n" +
					"\nMAXIMUM PRECISION IS SET TO " + NUMBER_PRECISION + " SIGNIFICANT FIGURES\n" +
					"PRIMALITY TEST CERTAINTY IS SET TO " + CERTAINTY + "\n");
			System.exit(1);
		}

		Configuration.createConfiguration(); //Create configuration if and only if it doesn't already exist

		int opcode = Opcode.getOpcode(args[0], argc);

		if (opcode == 1) {
			Apcomplex res = num();
			for (int i = 1; i < args.length; i++) {
				res = res.add(num(args[i]));
			}
			System.out.println(fc(res));
		} else if (opcode == 2) {
			Apcomplex res = num(args[1]);
			for (int i = 2; i < args.length; i++) {
				res = res.subtract(num(args[i]));
			}
			System.out.println(fc(res));
		} else if (opcode == 3) {
			Apcomplex res = num(args[1]);
			for (int i = 2; i < args.length; i++) {
				res = res.multiply(num(args[i]));
			}
			System.out.println(fc(res));
		} else if (opcode == 4) {
			Apcomplex res = num(args[1]);
			for (int i = 2; i < args.length; i++) {
				res = res.divide(num(args[i]));
			}
			System.out.println(fc(res));
		} else if (opcode == 5) {
			Apcomplex res = num(args[1]);
			Apcomplex exponent = num(args[2]);
			res = ApcomplexMath.pow(res, exponent);
			System.out.println(fc(res));
		} else if (opcode == 6) {
			Apcomplex num = num(args[1]);
			Apcomplex res = ApcomplexMath.sqrt(num);
			System.out.println(fc(res));
		} else if (opcode == 7) {
			Apcomplex a = num(args[1]);
			Apcomplex b = num(args[2]);
			Apcomplex c = num(args[3]);

			Apcomplex discrim2 = MathUtils.getDiscrimSquared(a, b, c);

			Apcomplex discrim;
			if (discrim2.real().signum()== 0) {
				discrim = MathUtils.ZERO;
			} else {
				discrim = ApcomplexMath.root(discrim2, 2);
			}

			Apcomplex neg_b = b.negate();
			Apcomplex ta = a.multiply(MathUtils.TWO);

			Apcomplex x1 = neg_b.add(discrim).divide(ta);
			Apcomplex x2 = neg_b.subtract(discrim).divide(ta);

			System.out.println("Discriminant = " + fc(discrim));
			System.out.println("x1 = " + fc(x1));
			System.out.println("x2 = " + fc(x2));
		} else if (opcode == 8) {
			Apcomplex num = num(args[1]);
			System.out.println(MathUtils.getSignificantFigures(num));
		} else if (opcode == 9) {
			Apcomplex radius = num(args[1]);
			Apcomplex area = radius.multiply(radius);
			area = area.multiply(ApfloatMath.pi(NUMBER_PRECISION));
			System.out.println(fc(area));
		} else if (opcode == 10) {
			Apcomplex a = num(args[1]);
			Apcomplex b = num(args[2]);
			Apcomplex mod = a.real().mod(b.real());
			System.out.println(fc(mod));
		} else if (opcode == 11) {
			Apint num = new Apint(args[1]);
			boolean prime = num.toBigInteger().isProbablePrime(CERTAINTY);
			System.out.println((prime ? "Probably prime (Probability = " + MathUtils.probability(CERTAINTY).multiply(new Apfloat(100)).toString(true) + "%)" : "Definitely composite"));
		} else if (opcode == 12) {
			SecureRandom rng = new SecureRandom();
			Apint min = new Apint(args[1]);
			Apint max = new Apint(args[2]);

			Apint num = MathUtils.getRandom(min, max, rng);

			System.out.println(num.toString(true));
			num = num.toRadix(16);
			System.out.println("Base 16 number: " + num.toString(true));
		} else if (opcode == 13) {
			Apint num = new Apint(args[1]);
			ArrayList<Apint> factorz = MathUtils.factor(num);
			System.out.print(num.toString(true) + ":");
			for (int i = 0; i < factorz.size(); i++) {
				System.out.print(" " + factorz.get(i).toString(true));
			}
			System.out.println("");
		} else if (opcode == 14) {
			Apint a = new Apint(args[1]);
			Apint b = new Apint(args[2]);
			System.out.println(ApintMath.gcd(a, b).toString(true));
		} else if (opcode == 15) {
			Apcomplex a = num(args[1]);
			Apcomplex b = num(args[2]);
			Apcomplex c = num(args[3]);

			Apcomplex[] vertex = MathUtils.getVertex(a, b, c); //vertex[0] = x, vertex[1] = y
			System.out.println("Vertex: (" + vertex[0] + ", " + vertex[1] + ")");
			System.out.println("Vertex form equation: y = (x + " + vertex[0].negate() + ")^2 + " + vertex[1]);
			boolean verified = MathUtils.verifyVertex(a, b, vertex[0]);
			System.out.println(verified ? "Vertex verified" : "Vertex NOT verified!");
		} else if (opcode == 16) {
			Apcomplex a = num(args[1]);
			Apcomplex b = num(args[2]);

			Apcomplex a2 = ApcomplexMath.pow(a, 2);
			Apcomplex b2 = ApcomplexMath.pow(b, 2);
			Apcomplex c2 = a2.add(b2);
			Apcomplex c = ApcomplexMath.sqrt(c2);

			System.out.println(fc(c));
		} else if (opcode == 17) {
			Apint num = new Apint(args[1]);

			Apint res = MathUtils.factorial(num);
			System.out.println(res.toString(true));
		} else if (opcode == 18) {
			Apcomplex num = num(args[1]);

			Apcomplex res = ApcomplexMath.sin(MathUtils.toRadians(num));
			System.out.println(fc(res));
		} else if (opcode == 19) {
			Apcomplex num = num(args[1]);

			Apcomplex res = ApcomplexMath.cos(MathUtils.toRadians(num));
			System.out.println(fc(res));
		} else if (opcode == 20) {
			Apcomplex num = num(args[1]);

			Apcomplex res = ApcomplexMath.tan(MathUtils.toRadians(num));
			System.out.println(fc(res));
		} else if (opcode == 21) {
			Apcomplex num = num(args[1]);

			Apcomplex res = MathUtils.toDegrees(ApcomplexMath.asin(num));
			System.out.println(fc(res));
		} else if (opcode == 22) {
			Apcomplex num = num(args[1]);

			Apcomplex res = MathUtils.toDegrees(ApcomplexMath.acos(num));
			System.out.println(fc(res));
		} else if (opcode == 23) {
			Apcomplex num = num(args[1]);

			Apcomplex res = MathUtils.toDegrees(ApcomplexMath.atan(num));
			System.out.println(fc(res));
		} else if (opcode == 24) {
			Apcomplex num = num(args[1]);

			Apcomplex cbrtnum = ApcomplexMath.cbrt(num);
			System.out.println(fc(cbrtnum));
		} else if (opcode == 25) {
			Apcomplex num = num(args[1]);

			Apcomplex lognum = ApcomplexMath.log(num);
			System.out.println(fc(lognum));
		} else if (opcode == 26) {
			Apcomplex num = num(args[1]);

			Apcomplex lognum = ApcomplexMath.log(num, MathUtils.TEN);
			System.out.println(fc(lognum));
		} else if (opcode == 27) {
			Apint num = new Apint(args[1]);

			for (Apint i = MathUtils.ONE_INT; i.compareTo(num) < 1; i = i.add(MathUtils.ONE_INT)) {
				System.out.println(ApintMath.pow(i, 2).toString(true));
			}
		} else if (opcode == 28) {
			Apint num = new Apint(args[1]);
			long p = Long.parseLong(args[2]);

			for (Apint i = MathUtils.ONE_INT; i.compareTo(num) < 1; i = i.add(MathUtils.ONE_INT)) {
				System.out.println(ApintMath.pow(i, p).toString(true));
			}
		} else if (opcode == 29) {
			Apcomplex accepted = num(args[1]);
			Apcomplex experimental = num(args[2]);

			Apcomplex error = MathUtils.getPercentError(accepted, experimental);
			System.out.println(fc(error));
		} else if (opcode == 30) {
			Apcomplex principal = num(args[1]);
			Apcomplex pct_rate = num(args[2]);
			Apcomplex compounds_year = num(args[3]);
			Apcomplex time = num(args[4]);

			Apcomplex total = MathUtils.getCompoundInterest(principal, pct_rate, compounds_year, time);
			System.out.println(fc(total));
		} else if (opcode == 31) {
			Apcomplex result = num();
			for (int i = 1; i <= argc; i++) {
				result = result.add(num(args[i]));
			}
			result = result.divide(num(Integer.toString(argc)));
			System.out.println(fc(result));
		} else if (opcode == 32) {
			Apcomplex total = num();
			for (int i = 1; i <= argc; i++) {
				total = total.add(num(args[i]));
			}
			Apcomplex avg = total.divide(num(Integer.toString(argc)));
			Apcomplex variance = num();
			for (int i = 1; i <= argc; i++) {
				Apcomplex data = num(args[i]);
				Apcomplex dfm = data.subtract(avg);
				variance = variance.add(ApcomplexMath.pow(dfm, 2));
			}
			variance = variance.divide(num(Integer.toString(argc)));
			Apcomplex stdev = ApcomplexMath.sqrt(variance);
			System.out.println(fc(stdev));
		} else if (opcode == 33) {
			Apcomplex data = num(args[1]);
			Apcomplex mean = num(args[2]);
			Apcomplex stdev = num(args[3]);

			Apcomplex zscore = data.subtract(mean);
			zscore = zscore.divide(stdev);
			System.out.println(fc(zscore));
		} else if (opcode == 34) {
			ArrayList<Apfloat> unsorted = new ArrayList<>();
			for (int i = 1; i <= argc; i++) {
				unsorted.add(num(args[i]).real());
			}
			Apfloat[] messy = new Apfloat[unsorted.size()];
			messy = unsorted.toArray(messy);

			Apfloat[] sorted = MathUtils.sort(messy);

			for (int i = 0; i < sorted.length; i++) {
				System.out.print(sorted[i].toString(true) + " ");
			}
			System.out.println("");
		} else if (opcode == 35) {
			Apcomplex radius = num(args[1]);
			Apcomplex pi = num(ApfloatMath.pi(NUMBER_PRECISION).toString(true));
			Apcomplex c = radius.multiply(MathUtils.TWO_INT).multiply(pi);
			System.out.println(fc(c));
		} else if (opcode == 36) {
			System.out.println("Mark your triangle: angle A across from side A, angle B across from side B, angle C across from side C");
			Apcomplex angleA = num(args[1]);
			Apcomplex angleB = num(args[2]);
			Apcomplex sideA = num(args[3]);

			Apcomplex sinAngleA = ApcomplexMath.sin(MathUtils.toRadians(angleA));
			Apcomplex sinAngleB = ApcomplexMath.sin(MathUtils.toRadians(angleB));

			Apcomplex a = sideA.divide(sinAngleA);
			Apcomplex sideB = a.multiply(sinAngleB);
			System.out.println(fc(sideB));
		} else if (opcode == 37) {
			System.out.println("Mark your triangle: angle A across from side A, angle B across from side B, angle C across from side C");
			Apcomplex sideA = num(args[1]);
			Apcomplex sideB = num(args[2]);
			Apcomplex sideC = num(args[3]);

			Apcomplex sideASquared = ApcomplexMath.pow(sideA, 2);
			Apcomplex sideBSquared = ApcomplexMath.pow(sideB, 2);
			Apcomplex sideCSquared = ApcomplexMath.pow(sideC, 2);

			Apcomplex twoAB = sideA.multiply(sideB).multiply(MathUtils.TWO);
			Apcomplex cosAngleC = sideASquared.add(sideBSquared).subtract(sideCSquared);

			cosAngleC = cosAngleC.divide(twoAB);
			Apcomplex angleC = MathUtils.toDegrees(ApcomplexMath.acos(cosAngleC));
			System.out.println(fc(angleC));
		} else if (opcode == 38) {
			int amount = Integer.parseInt(args[1]);

			Apint[] primes = MathUtils.getPrimes(amount);
			for (int i = 0; i < primes.length; i++) {
				System.out.println(primes[i]);
			}
		} else if (opcode == 39) {
			int num = Integer.parseInt(args[1]);

			Apcomplex parent = MathUtils.ONE_HUNDRED;
			Apcomplex daughter = MathUtils.ZERO;
			for (int i = 1; i <= num; i++) {
				parent = parent.divide(MathUtils.TWO);
				daughter = daughter.add(parent);
				System.out.println("Half life " + i + ": " + fc(parent) + "% parent, " + fc(daughter) + "% daughter");
			}
		} else if (opcode == 40) {
			Apcomplex num = num(args[1]);

			Apcomplex res = MathUtils.ONE.divide(ApcomplexMath.sin(MathUtils.toRadians(num)));
			System.out.println(fc(res));
		} else if (opcode == 41) {
			Apcomplex num = num(args[1]);

			Apcomplex res = MathUtils.ONE.divide(ApcomplexMath.cos(MathUtils.toRadians(num)));
			System.out.println(fc(res));
		} else if (opcode == 42) {
			Apcomplex num = num(args[1]);

			Apcomplex res = MathUtils.ONE.divide(ApcomplexMath.tan(MathUtils.toRadians(num)));
			System.out.println(fc(res));
		} else if (opcode == 43) {
			Apcomplex num = num(args[1]);

			Apcomplex res = MathUtils.toDegrees(ApcomplexMath.asin(MathUtils.ONE.divide(num)));
			System.out.println(fc(res));
		} else if (opcode == 44) {
			Apcomplex num = num(args[1]);

			Apcomplex res = MathUtils.toDegrees(ApcomplexMath.acos(MathUtils.ONE.divide(num)));
			System.out.println(fc(res));
		} else if (opcode == 45) {
			Apcomplex num = num(args[1]);

			Apcomplex res = MathUtils.toDegrees(ApcomplexMath.atan(MathUtils.ONE.divide(num)));
			System.out.println(fc(res));
		} else if (opcode == 46) {
			Apcomplex base = num(args[1]);
			Apcomplex num = num(args[2]);

			Apcomplex res = ApcomplexMath.log(num, base);
			System.out.println(fc(res));
		} else if (opcode == 47) {
			Apint a = new Apint(args[1]);
			Apint b = new Apint(args[2]);

			Apint lcm = ApintMath.lcm(a, b);
			System.out.println(lcm.toString(true));
		} else if (opcode == 48) {
			Apint num = new Apint(args[1]);

			Apint[] factors = MathUtils.getFactors(num);
			int fc = factors.length;
			int left = 0, right = fc - 1;

			System.out.println("FACTOR + FACTOR = SUM, PRODUCT");
			System.out.println("Found " + fc + " total factors.");
			System.out.println("-----------------------------------------------------------");

			while (left <= right - 1) {
				Apint ln = factors[left];
				Apint rn = factors[right];
				Apint sum = ln.add(rn);
				Apint product = ln.multiply(rn);
				System.out.println(ln.toString(true) + " + " + rn.toString(true) + " = " + sum.toString(true) + ", " + product.toString(true));
				left++;
				right--;
			}

		} else if (opcode == 49) {
			Apcomplex a = num(args[1]);
			Apcomplex b = num(args[2]);
			Apcomplex c = num(args[3]);
			Apcomplex d = num(args[4]);

			Apcomplex discrim = Cubic.getDiscrim(a, b, c, d);
			System.out.println("Discriminant = " + fc(discrim));

			Apcomplex t0 = Cubic.getT0(a, b, c);
			Apcomplex t1 = Cubic.getT1(a, b, c, d);

			Apcomplex C = Cubic.getC(t0, t1);

			Apcomplex[] solutionz = Cubic.getSolutions(a, b, c, d, C, t0, discrim);

			Apcomplex solutionA = solutionz[0];
			Apcomplex solutionB = solutionz[1];
			Apcomplex solutionC = solutionz[2];

			System.out.println("x1 = " + fc(solutionA));
			System.out.println("x2 = " + fc(solutionB));
			System.out.println("x3 = " + fc(solutionC));
		} else if (opcode == 50) {
			Apcomplex term_num = num(args[1]);
			Apcomplex common_diff = num(args[2]);
			Apcomplex a1 = num(args[3]);

			term_num = term_num.subtract(MathUtils.ONE);
			Apcomplex an = a1.add(common_diff.multiply(term_num));
			System.out.println(fc(an));
		} else if (opcode == 51) {
			Apcomplex term_count = num(args[1]);
			Apcomplex a1 = num(args[2]);
			Apcomplex an = num(args[3]);

			Apcomplex snNumer = term_count.multiply(a1.add(an));
			Apcomplex sn = snNumer.divide(MathUtils.TWO);
			System.out.println(fc(sn));
		} else if (opcode == 52) {
			Apint n = new Apint(args[1]);
			Apint r = new Apint(args[2]);

			Apint num = MathUtils.combination(n, r);
			System.out.println(num.toString(true));
		} else if (opcode == 53) {
			Apint n = new Apint(args[1]);
			Apint r = new Apint(args[2]);

			Apint num = MathUtils.permutation(n, r);
			System.out.println(num.toString(true));
		} else if (opcode == 54) {
			int bits = Integer.parseInt(args[1]);

			Apint prime = MathUtils.generateRandomPrime(bits);
			System.out.println(prime.toString(true));
		} else if (opcode == -1) {
			System.out.println("amath-ng: ERROR: Review your argument count!");
		} else if (opcode == -2) {
			System.out.println("amath-ng: ERROR: Invalid operation!");
		} else {
			System.out.println("amath-ng: ERROR: INVOPC - Please send this error to the developers! (opcode " + opcode + ", argc " + (argc + 1) + ", opargc " + argc + ")");
		}
	}

}
