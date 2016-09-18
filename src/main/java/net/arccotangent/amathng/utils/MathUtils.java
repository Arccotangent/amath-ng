package net.arccotangent.amathng.utils;

import net.arccotangent.amathng.Main;
import org.apache.commons.lang3.StringUtils;
import org.apfloat.*;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

public class MathUtils {

	public static final Apcomplex ZERO = NumberHelper.create("0", Main.RADIX);
	public static final Apint ZERO_INT = new Apint("0");
	public static final Apcomplex ONE = NumberHelper.create("1", Main.RADIX);
	public static final Apint ONE_INT = new Apint("1");
	public static final Apcomplex TWO = NumberHelper.create("2", Main.RADIX);
	public static final Apint TWO_INT = new Apint("2");
	public static final Apcomplex THREE = NumberHelper.create("3", Main.RADIX);
	public static final Apint THREE_INT = new Apint("3");
	public static final Apcomplex FOUR = NumberHelper.create("4", Main.RADIX);
	public static final Apcomplex TEN = NumberHelper.create("10", Main.RADIX);
	public static final Apcomplex ONE_HUNDRED = NumberHelper.create("100", Main.RADIX);
	
	public static void printUsage() {
		System.out.println("amath-ng " + Main.VERSION + " - A Command Line Calculator by Arccotangent\n" +
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
				"gcd <2+ numbers> - Get GCD (greatest common denominator) of numbers\n" +
				"lcm <2+ numbers> - Get LCM (least common multiple) of numbers\n" +
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
				"dst <x1> <y1> <x2> <y2> - Get distance between 2 points\n" +
				"midpt <x1> <y1> <x2> <y2> - Get midpoint of 2 points\n" +
				"slp <x1> <y1> <x2> <y2> - Get slope of line between 2 points\n" +
				"ccm <radius> - Calculate circumference of circle\n" +
				"crd <circumference> - Calculate radius of circle\n" +
				"\n--Statistics--\n\n" +
				"avg <numbers> - Calculate average of numbers\n" +
				"stdev <numbers> - Calculate standard deviation of numbers\n" +
				"zsc <data> <average> <standard deviation> - Get z-score of a number\n" +
				"ti <minimum z-score> <maximum z-score> - Tolerance interval (think of the normal distribution bell curve, 68-95-99.7 rule)\n" +
				"erf <number> - Error function\n" +
				"\n--Trigonometry--\n\n" +
				"sin <number> - Trigonometric function - Sine - opposite / hypotenuse\n" +
				"cos <number> - Trigonometric function - Cosine - adjacent / hypotenuse\n" +
				"tan <number> - Trigonometric function - Tangent - opposite / adjacent\n" +
				"asin <number> - Inverse Trigonometric function - Arcsine\n" +
				"acos <number> - Inverse Trigonometric function - Arccosine\n" +
				"atan <number> - Inverse Trigonometric function - Arctangent\n" +
				"los <side A> <side B> <angle A> - Trigonometric law of sines - Returns angle B (across from side B)\n" +
				"loc <side A> <side B> <side C> - Trigonometric law of cosines - Returns angle C (across from side C)\n" +
				"csc <number> - Reciprocal Trigonometric Function - Cosecant - hypotenuse / opposite\n" +
				"sec <number> - Reciprocal Trigonometric Function - Secant - hypotenuse / adjacent\n" +
				"cot <number> - Reciprocal Trigonometric Function - Cotangent - adjacent / opposite\n" +
				"acsc <number> - Inverse Reciprocal Trigonometric Function - Arccosecant\n" +
				"asec <number> - Inverse Reciprocal Trigonometric Function - Arcsecant\n" +
				"acot <number> - Inverse Reciprocal Trigonometric Function - Arccotangent\n" +
				"\n--Science--\n\n" +
				"pcr <accepted> <experimental> - Calculate percent error\n" +
				"hl <amount> - Print amount of half lives with respective ratios\n" +
				"\n--Unit Conversions--\n\n" +
				"f2c <fahrenheit> - Convert degrees Fahrenheit into degrees Celsius\n" +
				"c2f <celsius> - Convert degrees Celsius into degrees Fahrenheit\n\n" +
				"i2c <inches> - Convert inches to centimeters\n" +
				"c2i <centimeters> - Convert centimeters to inches\n" +
				"m2k <miles> - Convert miles to kilometers\n" +
				"k2m <centimeters> - Convert kilometers to miles\n" +
				"f2m <feet> - Convert feet to meters\n" +
				"m2f <meters> - Convert meters to feet\n\n" +
				"d2r <degrees> - Convert degrees to radians\n" +
				"r2d <radians> - Convert radians to degrees\n\n" +
				"p2k <pounds> - Convert pounds to kilograms\n" +
				"k2p <kilograms> - Convert kilograms to pounds\n\n" +
				"g2l <gallons> - Convert gallons to liters\n" +
				"l2g <liters> - Convert liters to gallons\n" +
				"cf2cm <cubic feet> - Convert cubic feet to cubic meters\n" +
				"cm2cf <cubic meters> - Convert cubic meters to cubic feet\n" +
				"\n--Logic--\n\n" +
				"and <2 numbers> - Logical AND\n" +
				"or <2 numbers> - Logical OR\n" +
				"xor <2 numbers> - Logical XOR\n" +
				"\n--Miscellaneous--\n\n" +
				"sf <1 number> - Get amount of significant figures in number\n" +
				"psq <amount> - Print AMOUNT perfect squares starting with 1\n" +
				"ppwr <amount> <exponent> - Print AMOUNT bases to EXPONENT starting with 1\n" +
				"pprm <amount> - Print AMOUNT probable prime numbers starting with 3\n" +
				"ord <numbers> - Order numbers smallest to greatest\n" +
				"rand <min> <max> - Generate random integer between MIN and MAX with optional SEED\n" +
				"prm <number> - Test if number is a probable prime by Miller-Rabin and Lucas-Lehmer primality tests\n" +
				"genprm <bits> - Generate a prime number with bitsize BITS\n" +
				"bc <original base> <target base> <number in original base> - Convert a number from base x to base y, base must be between 2-36\n" +
				"\n--Constants\n\n" +
				"e - Base of the natural logarithm (2.71828182...)\n" +
				"pi - Ratio of a perfect circle's circumference to its diameter (3.14159265358979...)\n" +
				"\nMAXIMUM PRECISION IS SET TO " + Main.NUMBER_PRECISION + " SIGNIFICANT FIGURES\n" +
				"PRIMALITY TEST CERTAINTY IS SET TO " + Main.CERTAINTY + "\n");
	}

	public static Apcomplex toDegrees(Apcomplex radian) {
		Apcomplex a = NumberHelper.create("180", Main.RADIX).divide(ApfloatMath.pi(Main.NUMBER_PRECISION));
		return radian.multiply(a);
	}

	public static Apcomplex toRadians(Apcomplex degree) {
		Apcomplex a = ApfloatMath.pi(Main.NUMBER_PRECISION).divide(NumberHelper.create("180", Main.RADIX));
		return degree.multiply(a);
	}

	public static Apint combination(Apint n, Apint r) {
		if (n.compareTo(r) == -1) {
			return ZERO_INT;
		}

		Apint numer = factorial(n);
		Apint denom = factorial(r).multiply(factorial(n.subtract(r)));

		return numer.divide(denom);
	}

	public static Apint permutation(Apint n, Apint r) {
		Apint numer = factorial(n);
		Apint denom = factorial(n.subtract(r));

		return numer.divide(denom);
	}

	public static Apfloat[] sort(Apfloat[] unsorted) {
		//bubble sort
		for (int i = 0; i < unsorted.length; i++) {
			for (int j = i + 1; j < unsorted.length; j++) {
				Apfloat tmp;
				if (unsorted[i].compareTo(unsorted[j]) == 1) {
					tmp = unsorted[i];
					unsorted[i] = unsorted[j];
					unsorted[j] = tmp;
				}
			}
		}
		return unsorted;
	}

	public static Apint[] getPrimes(int amount) {
		Apint[] primez = new Apint[amount];

		Apint num = THREE_INT;
		int i = 0;
		while (i < amount) {
			if (num.toBigInteger().isProbablePrime(Main.CERTAINTY)) {
				primez[i] = num;
				i++;
			}
			num = num.add(TWO_INT);
		}

		return primez;
	}

	public static Apint generateRandomPrime(int bits) {
		SecureRandom rng = new SecureRandom();
		BigInteger num = new BigInteger(bits, rng);
		num = num.nextProbablePrime(); //Uses a certainty of 100 (amath-ng's default), which should be good enough

		return new Apint(num);
	}

	public static Apint[] getFactors(Apint num) {
		ArrayList<Apint> factorz = new ArrayList<>();

		if (num.compareTo(TWO_INT) < 1) {
			throw new IllegalArgumentException("You cannot enter integers less than or equal to 2.");
		}

		for (Apint i = ONE_INT; i.compareTo(num) < 1; i = i.add(ONE_INT)) {
			if (num.mod(i).compareTo(ZERO_INT) == 0) {
				factorz.add(i);
				Apint[] sqrt = ApintMath.sqrt(num);
				if (sqrt[0].compareTo(i) == 0 && sqrt[1].compareTo(ZERO_INT) == 0)
					factorz.add(i);
			}
		}

		Apint[] factors = new Apint[factorz.size()];
		return factorz.toArray(factors);
	}

	public static Apcomplex getPercentError(Apcomplex accepted, Apcomplex experimental) {
		Apcomplex numer = ApcomplexMath.abs(accepted.subtract(experimental));
		Apcomplex error = numer.divide(accepted);
		return error.multiply(ONE_HUNDRED);
	}

	public static long getSignificantFigures(String inputStr) {
		if (inputStr.contains(".")) {
			inputStr = StringUtils.remove(inputStr, '.');
			int firstNonZero = StringUtils.indexOfAnyBut(inputStr, '0');
			return inputStr.substring(firstNonZero).length();
		} else {
			int firstNonZero = StringUtils.indexOfAnyBut(inputStr, '0');
			String inputStrRev = StringUtils.reverse(inputStr);
			int lastNonZero = inputStr.length() - StringUtils.indexOfAnyBut(inputStrRev, '0');
			return inputStr.substring(firstNonZero, lastNonZero).length();
		}
	}

	public static Apcomplex probability(int certainty) {
		Apcomplex ONE_HALF = NumberHelper.create("0.5", Main.RADIX);
		Apcomplex a = ApcomplexMath.pow(ONE_HALF, NumberHelper.create(Integer.toString(certainty), Main.RADIX));
		return ONE.subtract(a);
	}

	public static Apint getRandom(Apint min, Apint max, Random rng) {
		Apint result;
		do {
			result = new Apint(new BigInteger(max.toBigInteger().bitLength(), rng));
			result = result.add(min);
		} while (result.compareTo(max) > 0);

		return result;
	}

	public static Apint factorial(Apint x) {
		Apint res = new Apint(ONE.toString());
		for (int i = x.intValue(); i > 1; i--) {
			res = res.multiply(new Apint(Integer.toString(i)));
		}
		return res;
	}

	public static ArrayList<Apint> factor(Apint x) {
		ArrayList<Apint> factorz = new ArrayList<>();

		while (x.mod(TWO_INT).equals(ZERO)) {
			factorz.add(TWO_INT);
			x = x.divide(TWO_INT);
		}

		Apint sqrtx = ApintMath.sqrt(x)[0].add(ONE_INT); //the first element in the returned array is the square root, the second is the remainder

		for (Apint i = THREE_INT; i.compareTo(sqrtx) <= 0; i = i.add(TWO_INT)) {
			while (x.mod(i).equals(ZERO_INT)) {
				factorz.add(i);
				x = x.divide(i);
			}
		}

		factorz.add(x);

		return factorz;
	}

	public static void printFactors(Apint[] factors) {
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
	}

}
