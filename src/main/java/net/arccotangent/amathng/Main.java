package net.arccotangent.amathng;

import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;
import org.apfloat.Apint;
import org.apfloat.ApintMath;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;

public class Main {

	public static final String VERSION = "20160706";
	public static final long NUMBER_PRECISION = Configuration.getPrecision();
	public static final int CERTAINTY = Configuration.getCertainty(); //Probability of prime number = 1 - 0.5^CERTAINTY

	public static Apfloat num() {
		Apfloat a = new Apfloat("0", NUMBER_PRECISION);
		return a;
	}

	public static Apfloat num(String arg) {
		Apfloat a = new Apfloat(arg, NUMBER_PRECISION);
		return a;
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
					"ss <term count> <common difference> <first term> <nth term> - Find the sum of n (TERM COUNT) terms in an arithmetic sequence\n" +
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
					"pcr <actual> <experimental> - Calculate percent error\n" +
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
					"\nMAXIMUM PRECISION IS SET TO " + NUMBER_PRECISION + " DECIMAL PLACES\n");
			System.exit(1);
		}

		Configuration.createConfiguration();

		int opcode = Opcode.getOpcode(args[0], argc);

		if (opcode == 1) {
			Apfloat res = num();
			for (int i = 1; i < args.length; i++) {
				res = res.add(new Apfloat(args[i]));
			}
			System.out.println(res.toString(true));
		} else if (opcode == 2) {
			Apfloat res = num(args[1]);
			for (int i = 2; i < args.length; i++) {
				res = res.subtract(new Apfloat(args[i]));
			}
			System.out.println(res.toString(true));
		} else if (opcode == 3) {
			Apfloat res = num(args[1]);
			for (int i = 2; i < args.length; i++) {
				res = res.multiply(new Apfloat(args[i]));
			}
			System.out.println(res.toString(true));
		} else if (opcode == 4) {
			Apfloat res = num(args[1]);
			for (int i = 2; i < args.length; i++) {
				res = res.divide(new Apfloat(i, NUMBER_PRECISION));
			}
			System.out.println(res.toString(true));
		} else if (opcode == 5) {
			Apfloat res = num(args[1]);
			Apfloat exponent = num(args[2]);
			//long exponent = Long.parseLong(args[2]);
			//res = ApfloatMath.pow(res, exponent);
			res = ApfloatMath.pow(res, exponent);
			System.out.println(res.toString(true));
		} else if (opcode == 6) {
			Apfloat num = num(args[1]);
			Apfloat res = ApfloatMath.root(num, 2);
			System.out.println(res.toString(true));
		} else if (opcode == 7) {
			Apfloat a = num(args[1]);
			Apfloat b = num(args[2]);
			Apfloat c = num(args[3]);

			Apfloat discrim2 = MathUtils.getDiscrimSquared(a, b, c);

			Apfloat discrim;
			if (discrim2.signum() == 0) {
				discrim = MathUtils.ZERO;
			} else {
				discrim = ApfloatMath.root(discrim2, 2);
			}

			Apfloat neg_b = b.negate();
			Apfloat ta = a.multiply(MathUtils.TWO);

			Apfloat x1 = neg_b.add(discrim).divide(ta);
			Apfloat x2 = neg_b.subtract(discrim).divide(ta);

			System.out.println("Discriminant = " + discrim.toString(true));
			System.out.println("x1 = " + x1.toString(true));
			System.out.println("x2 = " + x2.toString(true));
		} else if (opcode == 8) {
			Apfloat num = num(args[1]);
			System.out.println(MathUtils.getSignificantFigures(num));
		} else if (opcode == 9) {
			Apfloat radius = num(args[1]);
			Apfloat area = radius.multiply(radius);
			area = area.multiply(ApfloatMath.pi(NUMBER_PRECISION));
			System.out.println(area.toString(true));
		} else if (opcode == 10) {
			Apfloat a = num(args[1]);
			Apfloat b = num(args[2]);
			Apfloat mod = a.mod(b);
			System.out.println(mod.toString(true));
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
		} else if (opcode == -1) {
			System.out.println("amath-ng: ERROR: Review your argument count!");
		} else if (opcode == -2) {
			System.out.println("amath-ng: ERROR: Invalid operation!");
		} else {
			System.out.println("amath-ng: ERROR: INVOPC - Please send this error to the developers! (opcode " + opcode + ", argc " + (argc + 1) + ", opargc " + argc + ")");
		}

		System.exit(0);
	}

}
