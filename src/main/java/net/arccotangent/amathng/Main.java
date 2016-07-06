package net.arccotangent.amathng;

import org.apache.commons.lang3.StringUtils;
import org.nevec.rjm.BigDecimalMath;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {

	public static final String VERSION = "20160706";
	public static final int NUMBER_PRECISION = 100;

	public static BigDecimal num() {
		BigDecimal a = new BigDecimal("0");
		a = a.setScale(NUMBER_PRECISION, RoundingMode.HALF_UP);
		a = a.stripTrailingZeros();
		return a;
	}

	public static BigDecimal num(String arg) {
		BigDecimal a = new BigDecimal(arg);
		a = a.setScale(NUMBER_PRECISION, RoundingMode.HALF_UP);
		a = a.stripTrailingZeros();
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
					"prm <number> - Test if number is prime by Miller-Rabin primality test\n" +
					"genprm <bits> - Generate a prime number with bitsize BITS\n" +
					"\nMAXIMUM PRECISION IS SET TO " + NUMBER_PRECISION + " DECIMAL PLACES\n");
			System.exit(1);
		}

		int opcode = Opcode.getOpcode(args[0], argc);

		if (opcode == 1) {
			BigDecimal res = num();
			for (int i = 1; i < args.length; i++) {
				res = res.add(new BigDecimal(args[i]));
			}
			System.out.println(res.stripTrailingZeros().toPlainString());
		} else if (opcode == 2) {
			BigDecimal res = num(args[1]);
			for (int i = 2; i < args.length; i++) {
				res = res.subtract(new BigDecimal(args[i]));
			}
			System.out.println(res.stripTrailingZeros().toPlainString());
		} else if (opcode == 3) {
			BigDecimal res = num(args[1]);
			for (int i = 2; i < args.length; i++) {
				res = res.multiply(new BigDecimal(args[i]));
			}
			System.out.println(res.stripTrailingZeros().toPlainString());
		} else if (opcode == 4) {
			BigDecimal res = num(args[1]);
			for (int i = 2; i < args.length; i++) {
				res = res.divide(new BigDecimal(args[i]), NUMBER_PRECISION, RoundingMode.HALF_UP);
			}
			System.out.println(res.stripTrailingZeros().toPlainString());
		} else if (opcode == 5) {
			BigDecimal res = num(args[1]);
			BigDecimal exponent = num(args[2]);
			res = BigDecimalMath.pow(res, exponent);
			System.out.println(res.stripTrailingZeros().toPlainString());
		} else if (opcode == 6) {
			BigDecimal num = num(args[1]);
			boolean i = false; //complex number?
			if (num.signum() == -1)
				i = true;
			else if (num.signum() == 0) {
				System.out.println("0");
				System.exit(0);
			}
			num = num.abs();
			BigDecimal res = BigDecimalMath.root(2, num);
			System.out.println(res.stripTrailingZeros().toPlainString() + (i ? "i" : ""));
		} else if (opcode == 7) {
			BigDecimal a = num(args[1]);
			BigDecimal b = num(args[2]);
			BigDecimal c = num(args[3]);

			BigDecimal discrim2 = MathUtils.getDiscrimSquared(a, b, c);
			boolean i = false;
			if (discrim2.signum() == -1) {
				i = true;
				discrim2 = discrim2.abs();
			}

			BigDecimal discrim;
			if (discrim2.signum() == 0) {
				discrim = MathUtils.ZERO;
			} else {
				discrim = BigDecimalMath.root(2, discrim2);
			}

			BigDecimal neg_b = b.negate();
			BigDecimal ta = a.multiply(MathUtils.TWO);

			BigDecimal x1 = neg_b.add(discrim).divide(ta, NUMBER_PRECISION, RoundingMode.HALF_UP);
			BigDecimal x2 = neg_b.subtract(discrim).divide(ta, NUMBER_PRECISION, RoundingMode.HALF_UP);

			System.out.println("Discriminant = " + discrim.stripTrailingZeros().toPlainString() + (i ? "i" : ""));
			System.out.println("x1 = " + x1.stripTrailingZeros().toPlainString() + (i ? "i" : ""));
			System.out.println("x2 = " + x2.stripTrailingZeros().toPlainString() + (i ? "i" : ""));
		} else if (opcode == 8) {
			BigDecimal num = num(args[1]);
			System.out.println(MathUtils.getSignificantFigures(num));
		} else if (opcode == -1) {
			System.out.println("amath-ng: ERROR: Review your argument count!");
		} else if (opcode == -2) {
			System.out.println("amath-ng: ERROR: Invalid operation!");
		} else {
			System.out.println("amath-ng: ERROR: INVOPC - Please send this error to the developers! (opcode " + opcode + ", argc " + argc + ", opargc " + (argc - 1) + ")");
		}

		System.exit(0);
	}

}
