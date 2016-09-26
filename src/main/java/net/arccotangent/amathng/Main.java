package net.arccotangent.amathng;

import net.arccotangent.amathng.math.*;
import net.arccotangent.amathng.utils.Configuration;
import net.arccotangent.amathng.utils.MathUtils;
import net.arccotangent.amathng.utils.NumberHelper;
import org.apfloat.*;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;

public class Main {

	public static final String VERSION = "20160925";
	
	public static long NUMBER_PRECISION = Configuration.getPrecision(); //Precision in significant figures
	public static int CERTAINTY = Configuration.getCertainty(); //Probability of prime number = 1 - 0.5^CERTAINTY
	public static final int RADIX = 10;

	public static void main(String[] args) {
		int argc = args.length - 1;
		if (args.length < 1) {
			MathUtils.printUsage();
			System.exit(1);
		}
		
		Configuration.createConfiguration(); //Create configuration if and only if it doesn't already exist
		Operation op = Opcode.getOpcode(args[0], argc);

		switch (op) {
			case ADDITION: {
				Apcomplex res = NumberHelper.create();
				for (int i = 1; i < args.length; i++) {
					res = res.add(NumberHelper.create(args[i], RADIX));
				}
				System.out.println(NumberHelper.format(res));
				break;
			}
			case SUBTRACTION: {
				Apcomplex res = NumberHelper.create(args[1], RADIX);
				for (int i = 2; i < args.length; i++) {
					res = res.subtract(NumberHelper.create(args[i], RADIX));
				}
				System.out.println(NumberHelper.format(res));
				break;
			}
			case MULTIPLICATION: {
				Apcomplex res = NumberHelper.create(args[1], RADIX);
				for (int i = 2; i < args.length; i++) {
					res = res.multiply(NumberHelper.create(args[i], RADIX));
				}
				System.out.println(NumberHelper.format(res));
				break;
			}
			case DIVISION: {
				Apcomplex res = NumberHelper.create(args[1], RADIX);
				for (int i = 2; i < args.length; i++) {
					res = res.divide(NumberHelper.create(args[i], RADIX));
				}
				System.out.println(NumberHelper.format(res));
				break;
			}
			case EXPONENTIATION: {
				Apcomplex res = NumberHelper.create(args[1], RADIX);
				Apcomplex exponent = NumberHelper.create(args[2], RADIX);
				res = ApcomplexMath.pow(res, exponent);
				System.out.println(NumberHelper.format(res));
				break;
			}
			case SQUARE_ROOT: {
				Apcomplex num = NumberHelper.create(args[1], RADIX);
				Apcomplex res = ApcomplexMath.sqrt(num);
				System.out.println(NumberHelper.format(res));
				break;
			}
			case QUADRATIC: {
				Apcomplex a = NumberHelper.create(args[1], RADIX);
				Apcomplex b = NumberHelper.create(args[2], RADIX);
				Apcomplex c = NumberHelper.create(args[3], RADIX);

				Apcomplex discriminantSquared = Quadratic.getDiscrimSquared(a, b, c);
				Apcomplex discriminant;

				if (discriminantSquared.real().signum() == 0)
					discriminant = MathUtils.ZERO;
				else
					discriminant = ApcomplexMath.root(discriminantSquared, 2);

				Apcomplex solutions[] = Quadratic.solve(a, b, discriminant);
				System.out.println("Discriminant = " + NumberHelper.format(discriminant));
				System.out.println("x1 = " + NumberHelper.format(solutions[0]));
				System.out.println("x2 = " + NumberHelper.format(solutions[1]));
				break;
			}
			case SIGNIFICANT_FIGURES: {
				System.out.println(MathUtils.getSignificantFigures(args[1]));
				break;
			}
			case AREA_OF_CIRCLE: {
				Apcomplex radius = NumberHelper.create(args[1], RADIX);
				Apcomplex area = radius.multiply(radius);
				area = area.multiply(ApfloatMath.pi(NUMBER_PRECISION));
				System.out.println(NumberHelper.format(area));
				break;
			}
			case MODULUS: {
				Apcomplex a = NumberHelper.create(args[1], RADIX);
				Apcomplex b = NumberHelper.create(args[2], RADIX);
				Apcomplex mod = a.real().mod(b.real());
				System.out.println(NumberHelper.format(mod));
				break;
			}
			case PRIMALITY_TEST: {
				Apint num = new Apint(args[1]);
				boolean prime = num.toBigInteger().isProbablePrime(CERTAINTY);
				System.out.println((prime ? "Probably prime (Probability = " + MathUtils.probability(CERTAINTY).multiply(new Apfloat(100)).toString(true) + "%)" : "Definitely composite"));
				break;
			}
			case RANDOM_NUMBER: {
				SecureRandom rng = new SecureRandom();
				Apint min = new Apint(args[1]);
				Apint max = new Apint(args[2]);
				Apint num = MathUtils.getRandom(min, max, rng);
				System.out.println(num.toString(true));
				break;
			}
			case FACTOR: {
				Apint num = new Apint(args[1]);
				ArrayList<Apint> factorz = MathUtils.factor(num);
				System.out.print(num.toString(true) + ":");
				for (Apint aFactorz : factorz) {
					System.out.print(" " + aFactorz.toString(true));
				}
				System.out.println("");
				break;
			}
			case GREATEST_COMMON_DENOMINATOR: {
				ArrayList<Apint> numbers = new ArrayList<>();
				for (int i = 1; i < args.length; i++) {
					numbers.add(new Apint(args[i]));
				}
				System.out.println(Algebra.gcd(numbers).toString(true));
				break;
			}
			case VERTEX: {
				Apcomplex a = NumberHelper.create(args[1], RADIX);
				Apcomplex b = NumberHelper.create(args[2], RADIX);
				Apcomplex c = NumberHelper.create(args[3], RADIX);

				Apcomplex[] vertex = Vertex.getVertex(a, b, c);
				//vertex[0] = x, vertex[1] = y
				System.out.println("Vertex: (" + vertex[0] + ", " + vertex[1] + ")");
				System.out.println("Vertex form equation: y = (x + " + vertex[0].negate() + ")^2 + " + vertex[1]);

				boolean verified = Vertex.verifyVertex(a, b, vertex[0]);
				System.out.println(verified ? "Vertex verified" : "Vertex NOT verified!");
				break;
			}
			case HYPOTENUSE: {
				Apcomplex a = NumberHelper.create(args[1], RADIX);
				Apcomplex b = NumberHelper.create(args[2], RADIX);

				Apcomplex c = Geometry.hypot(a, b);
				System.out.println(NumberHelper.format(c));
				break;
			}
			case FACTORIAL: {
				Apint num = new Apint(args[1]);
				Apint res = MathUtils.factorial(num);
				System.out.println(res.toString(true));
				break;
			}
			case SINE: {
				Apcomplex num = NumberHelper.create(args[1], RADIX);
				Apcomplex res = ApcomplexMath.sin(MathUtils.toRadians(num));
				System.out.println(NumberHelper.format(res));
				break;
			}
			case COSINE: {
				Apcomplex num = NumberHelper.create(args[1], RADIX);
				Apcomplex res = ApcomplexMath.cos(MathUtils.toRadians(num));
				System.out.println(NumberHelper.format(res));
				break;
			}
			case TANGENT: {
				Apcomplex num = NumberHelper.create(args[1], RADIX);
				Apcomplex res = ApcomplexMath.tan(MathUtils.toRadians(num));
				System.out.println(NumberHelper.format(res));
				break;
			}
			case ARCSINE: {
				Apcomplex num = NumberHelper.create(args[1], RADIX);
				Apcomplex res = MathUtils.toDegrees(ApcomplexMath.asin(num));
				System.out.println(NumberHelper.format(res));
				break;
			}
			case ARCCOSINE: {
				Apcomplex num = NumberHelper.create(args[1], RADIX);
				Apcomplex res = MathUtils.toDegrees(ApcomplexMath.acos(num));
				System.out.println(NumberHelper.format(res));
				break;
			}
			case ARCTANGENT: {
				Apcomplex num = NumberHelper.create(args[1], RADIX);
				Apcomplex res = MathUtils.toDegrees(ApcomplexMath.atan(num));
				System.out.println(NumberHelper.format(res));
				break;
			}
			case CUBE_ROOT: {
				Apcomplex num = NumberHelper.create(args[1], RADIX);
				Apcomplex cbrtnum = ApcomplexMath.cbrt(num);
				System.out.println(NumberHelper.format(cbrtnum));
				break;
			}
			case LOGARITHM_E: {
				Apcomplex num = NumberHelper.create(args[1], RADIX);
				Apcomplex logOfNum = ApcomplexMath.log(num);
				System.out.println(NumberHelper.format(logOfNum));
				break;
			}
			case LOGARITHM_10: {
				Apcomplex num = NumberHelper.create(args[1], RADIX);
				Apcomplex log10OfNum = ApcomplexMath.log(num, MathUtils.TEN);
				System.out.println(NumberHelper.format(log10OfNum));
				break;
			}
			case PRINT_SQUARES: {
				Apint num = new Apint(args[1]);
				for (Apint i = MathUtils.ONE_INT; i.compareTo(num) < 1; i = i.add(MathUtils.ONE_INT)) {
					System.out.println(ApintMath.pow(i, 2).toString(true));
				}
				break;
			}
			case PRINT_POWER: {
				Apint num = new Apint(args[1]);
				long p = Long.parseLong(args[2]);
				for (Apint i = MathUtils.ONE_INT; i.compareTo(num) < 1; i = i.add(MathUtils.ONE_INT)) {
					System.out.println(ApintMath.pow(i, p).toString(true));
				}
				break;
			}
			case PERCENT_ERROR: {
				Apcomplex accepted = NumberHelper.create(args[1], RADIX);
				Apcomplex experimental = NumberHelper.create(args[2], RADIX);
				
				Apcomplex error = MathUtils.getPercentError(accepted, experimental);
				System.out.println(NumberHelper.format(error));
				break;
			}
			case COMPOUND_INTEREST: {
				Apcomplex principal = NumberHelper.create(args[1], RADIX);
				Apcomplex pct_rate = NumberHelper.create(args[2], RADIX);
				Apcomplex compounds_year = NumberHelper.create(args[3], RADIX);

				Apcomplex time = NumberHelper.create(args[4], RADIX);
				Apcomplex total = Algebra.getCompoundInterest(principal, pct_rate, compounds_year, time);
				System.out.println(NumberHelper.format(total));
				break;
			}
			case AVERAGE: {
				Apcomplex result = NumberHelper.create();
				for (int i = 1; i <= argc; i++) {
					result = result.add(NumberHelper.create(args[i], RADIX));
				}
				
				result = result.divide(NumberHelper.create(Integer.toString(argc), RADIX));
				System.out.println(NumberHelper.format(result));
				break;
			}
			case STANDARD_DEVIATION: {
				Apcomplex total = NumberHelper.create();
				for (int i = 1; i <= argc; i++) {
					total = total.add(NumberHelper.create(args[i], RADIX));
				}

				Apcomplex avg = total.divide(NumberHelper.create(Integer.toString(argc), RADIX));
				Apcomplex variance = NumberHelper.create();

				for (int i = 1; i <= argc; i++) {
					Apcomplex data = NumberHelper.create(args[i], RADIX);
					Apcomplex dfm = data.subtract(avg);
					variance = variance.add(ApcomplexMath.pow(dfm, 2));
				}

				variance = variance.divide(NumberHelper.create(Integer.toString(argc), RADIX));
				Apcomplex stdev = ApcomplexMath.sqrt(variance);

				System.out.println(NumberHelper.format(stdev));
				break;
			}
			case Z_SCORE: {
				Apcomplex data = NumberHelper.create(args[1], RADIX);
				Apcomplex mean = NumberHelper.create(args[2], RADIX);
				Apcomplex stdev = NumberHelper.create(args[3], RADIX);

				Apcomplex zscore = data.subtract(mean);
				zscore = zscore.divide(stdev);
				System.out.println(NumberHelper.format(zscore));
				break;
			}
			case SORT: {
				ArrayList<Apfloat> unsorted = new ArrayList<>();
				for (int i = 1; i <= argc; i++) {
					unsorted.add(NumberHelper.create(args[i], RADIX).real());
				}
				Apfloat[] messy = new Apfloat[unsorted.size()];
				messy = unsorted.toArray(messy);
				
				Apfloat[] sorted = MathUtils.sort(messy);
				
				for (Apfloat aSorted : sorted) {
					System.out.print(aSorted.toString(true) + " ");
				}
				System.out.println("");
				
				break;
			}
			case CIRCUMFERENCE: {
				Apcomplex radius = NumberHelper.create(args[1], RADIX);
				Apcomplex pi = NumberHelper.create(ApfloatMath.pi(NUMBER_PRECISION).toString(true), RADIX);
				Apcomplex c = radius.multiply(MathUtils.TWO_INT).multiply(pi);
				System.out.println(NumberHelper.format(c));
				break;
			}
			case LAW_OF_SINES: {
				System.out.println("Mark your triangle: angle A across from side A, angle B across from side B, angle C across from side C");
				Apcomplex angleA = NumberHelper.create(args[1], RADIX);
				Apcomplex angleB = NumberHelper.create(args[2], RADIX);
				Apcomplex sideA = NumberHelper.create(args[3], RADIX);

				Apcomplex sideB = Trigonometry.lawOfSines(angleA, angleB, sideA);
				System.out.println(NumberHelper.format(sideB));
				break;
			}
			case LAW_OF_COSINES: {
				System.out.println("Mark your triangle: angle A across from side A, angle B across from side B, angle C across from side C");
				Apcomplex sideA = NumberHelper.create(args[1], RADIX);
				Apcomplex sideB = NumberHelper.create(args[2], RADIX);
				Apcomplex sideC = NumberHelper.create(args[3], RADIX);

				Apcomplex angleC = Trigonometry.lawOfCosines(sideA, sideB, sideC);
				System.out.println(NumberHelper.format(angleC));
				break;
			}
			case PRINT_PRIMES: {
				int amount = Integer.parseInt(args[1]);
				
				Apint[] primes = MathUtils.getPrimes(amount);
				for (Apint prime1 : primes) {
					System.out.println(prime1);
				}
				
				break;
			}
			case HALF_LIFE: {
				int num = Integer.parseInt(args[1]);
				Apcomplex parent = MathUtils.ONE_HUNDRED;
				Apcomplex daughter = MathUtils.ZERO;
				for (int i = 1; i <= num; i++) {
					parent = parent.divide(MathUtils.TWO);
					daughter = daughter.add(parent);
					System.out.println("Half life " + i + ": " + NumberHelper.format(parent) + "% parent, " + NumberHelper.format(daughter) + "% daughter");
				}
				break;
			}
			case COSECANT: {
				Apcomplex num = NumberHelper.create(args[1], RADIX);
				Apcomplex res = MathUtils.ONE.divide(ApcomplexMath.sin(MathUtils.toRadians(num)));
				System.out.println(NumberHelper.format(res));
				break;
			}
			case SECANT: {
				Apcomplex num = NumberHelper.create(args[1], RADIX);
				Apcomplex res = MathUtils.ONE.divide(ApcomplexMath.cos(MathUtils.toRadians(num)));
				System.out.println(NumberHelper.format(res));
				break;
			}
			case COTANGENT: {
				Apcomplex num = NumberHelper.create(args[1], RADIX);
				Apcomplex res = MathUtils.ONE.divide(ApcomplexMath.tan(MathUtils.toRadians(num)));
				System.out.println(NumberHelper.format(res));
				break;
			}
			case ARCCOSECANT: {
				Apcomplex num = NumberHelper.create(args[1], RADIX);
				Apcomplex res = MathUtils.toDegrees(ApcomplexMath.asin(MathUtils.ONE.divide(num)));
				System.out.println(NumberHelper.format(res));
				break;
			}
			case ARCSECANT: {
				Apcomplex num = NumberHelper.create(args[1], RADIX);
				Apcomplex res = MathUtils.toDegrees(ApcomplexMath.acos(MathUtils.ONE.divide(num)));
				System.out.println(NumberHelper.format(res));
				break;
			}
			case ARCCOTANGENT: {
				Apcomplex num = NumberHelper.create(args[1], RADIX);
				Apcomplex res = MathUtils.toDegrees(ApcomplexMath.atan(MathUtils.ONE.divide(num)));
				System.out.println(NumberHelper.format(res));
				break;
			}
			case LOGARITHM_BASE: {
				Apcomplex base = NumberHelper.create(args[1], RADIX);
				Apcomplex num = NumberHelper.create(args[2], RADIX);
				Apcomplex res = ApcomplexMath.log(num, base);
				System.out.println(NumberHelper.format(res));
				break;
			}
			case LEAST_COMMON_MULTIPLE: {
				ArrayList<Apint> numbers = new ArrayList<>();
				for (int i = 1; i < args.length; i++) {
					numbers.add(new Apint(args[i]));
				}
				System.out.println(Algebra.lcm(numbers).toString(true));
				break;
			}
			case GET_FACTORS: {
				Apint num = new Apint(args[1]);
				Apint[] factors = MathUtils.getFactors(num);
				MathUtils.printFactors(factors);
				break;
			}
			case CUBIC: {
				Apcomplex a = NumberHelper.create(args[1], RADIX);
				Apcomplex b = NumberHelper.create(args[2], RADIX);
				Apcomplex c = NumberHelper.create(args[3], RADIX);
				Apcomplex d = NumberHelper.create(args[4], RADIX);

				Apcomplex discrim = Cubic.getDiscrim(a, b, c, d);
				System.out.println("Discriminant = " + NumberHelper.format(discrim));

				//Variables used to solve the cubic equation using the formula
				//See https://en.wikipedia.org/wiki/Cubic_function#General_formula for more info
				Apcomplex t0 = Cubic.getT0(a, b, c);
				Apcomplex t1 = Cubic.getT1(a, b, c, d);
				Apcomplex C = Cubic.getC(t0, t1);

				Apcomplex[] solutions = Cubic.getSolutions(a, b, c, d, C, t0, discrim);

				System.out.println("x1 = " + NumberHelper.format(solutions[0]));
				System.out.println("x2 = " + NumberHelper.format(solutions[1]));
				System.out.println("x3 = " + NumberHelper.format(solutions[2]));
				break;
			}
			case ARITHMETIC_SEQUENCE_TERM: {
				Apcomplex term_num = NumberHelper.create(args[1], RADIX);
				Apcomplex common_diff = NumberHelper.create(args[2], RADIX);
				Apcomplex a1 = NumberHelper.create(args[3], RADIX);

				term_num = term_num.subtract(MathUtils.ONE);
				Apcomplex termN = a1.add(common_diff.multiply(term_num));
				System.out.println(NumberHelper.format(termN));
				break;
			}
			case ARITHMETIC_SEQUENCE_SUM: {
				Apcomplex term_count = NumberHelper.create(args[1], RADIX);
				Apcomplex a1 = NumberHelper.create(args[2], RADIX);
				Apcomplex an = NumberHelper.create(args[3], RADIX);

				Apcomplex snNumer = term_count.multiply(a1.add(an));
				Apcomplex sumOfSeries = snNumer.divide(MathUtils.TWO);
				System.out.println(NumberHelper.format(sumOfSeries));
				break;
			}
			case COMBINATION: {
				Apint n = new Apint(args[1]);
				Apint r = new Apint(args[2]);
				Apint num = MathUtils.combination(n, r);
				System.out.println(num.toString(true));
				break;
			}
			case PERMUTATION: {
				Apint n = new Apint(args[1]);
				Apint r = new Apint(args[2]);
				Apint num = MathUtils.permutation(n, r);
				System.out.println(num.toString(true));
				break;
			}
			case RANDOM_PRIME: {
				int bits = Integer.parseInt(args[1]);
				Apint prime = MathUtils.generateRandomPrime(bits);
				System.out.println(prime.toString(true));
				break;
			}
			case DISTANCE: {
				Apcomplex x1 = NumberHelper.create(args[1], RADIX);
				Apcomplex y1 = NumberHelper.create(args[2], RADIX);
				Apcomplex x2 = NumberHelper.create(args[3], RADIX);
				Apcomplex y2 = NumberHelper.create(args[4], RADIX);
				
				Apcomplex distanceSquared = Geometry.getDistanceSquared(x1, x2, y1, y2);
				Apcomplex distance = ApcomplexMath.sqrt(distanceSquared);
				
				System.out.println("distance^2 = " + NumberHelper.format(distanceSquared));
				System.out.println("distance = " + NumberHelper.format(distance));
				break;
			}
			case FAHRENHEIT_TO_CELSIUS: {
				Apcomplex fahrenheit = NumberHelper.create(args[1], RADIX);
				
				Apcomplex celsius = fahrenheit.subtract(NumberHelper.create("32", RADIX));
				Apcomplex fiveOver9 = NumberHelper.create("5", RADIX).divide(NumberHelper.create("9", RADIX));
				celsius = celsius.multiply(fiveOver9);
				
				System.out.println(NumberHelper.format(celsius));
				break;
			}
			case CELSIUS_TO_FAHRENHEIT: {
				Apcomplex celsius = NumberHelper.create(args[1], RADIX);
				
				Apcomplex nineOver5 = NumberHelper.create("1.8", RADIX);
				Apcomplex fahrenheit = celsius.multiply(nineOver5);
				fahrenheit = fahrenheit.add(NumberHelper.create("32", RADIX));
				
				System.out.println(NumberHelper.format(fahrenheit));
				break;
			}
			case INCHES_TO_CENTIMETERS: {
				Apcomplex inches = NumberHelper.create(args[1], RADIX);
				
				Apcomplex a = NumberHelper.create("2.54", RADIX);
				Apcomplex centimeters = inches.multiply(a);
				
				System.out.println(NumberHelper.format(centimeters));
				break;
			}
			case CENTIMETERS_TO_INCHES: {
				Apcomplex centimeters = NumberHelper.create(args[1], RADIX);
				
				Apcomplex a = NumberHelper.create("2.54", RADIX);
				Apcomplex inches = centimeters.divide(a);
				
				System.out.println(NumberHelper.format(inches));
				break;
			}
			case MILES_TO_KILOMETERS: {
				Apcomplex miles = NumberHelper.create(args[1], RADIX);
				
				Apcomplex a = NumberHelper.create("1.609344", RADIX);
				Apcomplex kilometers = miles.multiply(a);
				
				System.out.println(NumberHelper.format(kilometers));
				break;
			}
			case KILOMETERS_TO_MILES: {
				Apcomplex kilometers = NumberHelper.create(args[1], RADIX);
				
				Apcomplex a = NumberHelper.create("1.609344", RADIX);
				Apcomplex miles = kilometers.divide(a);
				
				System.out.println(NumberHelper.format(miles));
				break;
			}
			case FEET_TO_METERS: {
				Apcomplex feet = NumberHelper.create(args[1], RADIX);
				
				Apcomplex a = NumberHelper.create("0.3048", RADIX);
				Apcomplex meters = feet.multiply(a);
				
				System.out.println(NumberHelper.format(meters));
				break;
			}
			case METERS_TO_FEET: {
				Apcomplex meters = NumberHelper.create(args[1], RADIX);
				
				Apcomplex a = NumberHelper.create("0.3048", RADIX);
				Apcomplex feet = meters.divide(a);
				
				System.out.println(NumberHelper.format(feet));
				break;
			}
			case MIDPOINT: {
				Apcomplex x1 = NumberHelper.create(args[1], RADIX);
				Apcomplex y1 = NumberHelper.create(args[2], RADIX);
				Apcomplex x2 = NumberHelper.create(args[3], RADIX);
				Apcomplex y2 = NumberHelper.create(args[4], RADIX);
				
				Apcomplex[] midpoint = Geometry.getMidpoint(x1, x2, y1, y2);
				//midpoint[0] = x, midpoint[1] = y
				
				System.out.println("midpoint = (" + NumberHelper.format(midpoint[0]) + ", " + NumberHelper.format(midpoint[1]) + ")");
				break;
			}
			case SLOPE: {
				Apcomplex x1 = NumberHelper.create(args[1], RADIX);
				Apcomplex y1 = NumberHelper.create(args[2], RADIX);
				Apcomplex x2 = NumberHelper.create(args[3], RADIX);
				Apcomplex y2 = NumberHelper.create(args[4], RADIX);
				
				Apcomplex slope = Geometry.getSlope(x1, x2, y1, y2);
				
				System.out.println("slope = " + NumberHelper.format(slope));
				break;
			}
			case DEGREES_TO_RADIANS: {
				Apcomplex degrees = NumberHelper.create(args[1], RADIX);
				
				Apcomplex radians = MathUtils.toRadians(degrees);
				System.out.println(NumberHelper.format(radians));
				break;
			}
			case RADIANS_TO_DEGREES: {
				Apcomplex radians = NumberHelper.create(args[1], RADIX);
				
				Apcomplex degrees = MathUtils.toDegrees(radians);
				System.out.println(NumberHelper.format(degrees));
				break;
			}
			case POUNDS_TO_KILOGRAMS: {
				Apcomplex pounds = NumberHelper.create(args[1], RADIX);
				
				Apcomplex kilograms = pounds.multiply(NumberHelper.create("0.45359237", RADIX));
				System.out.println(NumberHelper.format(kilograms));
				break;
			}
			case KILOGRAMS_TO_POUNDS: {
				Apcomplex kilograms = NumberHelper.create(args[1], RADIX);
				
				Apcomplex pounds = kilograms.multiply(NumberHelper.create("2.20462234", RADIX));
				System.out.println(NumberHelper.format(pounds));
				break;
			}
			case GALLONS_TO_LITERS: {
				Apcomplex gallons = NumberHelper.create(args[1], RADIX);
				
				Apcomplex liters = gallons.multiply(NumberHelper.create("3.785411784", RADIX));
				System.out.println(NumberHelper.format(liters));
				break;
			}
			case LITERS_TO_GALLONS: {
				Apcomplex liters = NumberHelper.create(args[1], RADIX);
				
				Apcomplex gallons = liters.divide(NumberHelper.create("3.785411784", RADIX));
				System.out.println(NumberHelper.format(gallons));
				break;
			}
			case CUBIC_FEET_TO_CUBIC_METERS: {
				Apcomplex cubicFeet = NumberHelper.create(args[1], RADIX);
				
				Apcomplex cubicMeters = cubicFeet.multiply(NumberHelper.create("0.028316846592", RADIX));
				System.out.println(NumberHelper.format(cubicMeters));
				break;
			}
			case CUBIC_METERS_TO_CUBIC_FEET: {
				Apcomplex cubicMeters = NumberHelper.create(args[1], RADIX);
				
				Apcomplex cubicFeet = cubicMeters.divide(NumberHelper.create("0.028316846592", RADIX));
				System.out.println(NumberHelper.format(cubicFeet));
				break;
			}
			case CIRCLE_RADIUS: {
				Apcomplex circumference = NumberHelper.create(args[1], RADIX);
				
				Apcomplex twoPi = NumberHelper.create("pi", RADIX).multiply(MathUtils.TWO);
				Apcomplex radius = circumference.divide(twoPi);
				System.out.println(NumberHelper.format(radius));
				break;
			}
			case AND: {
				BigInteger numA = new BigInteger(args[1]);
				BigInteger numB = new BigInteger(args[2]);
				
				System.out.println(numA.and(numB).toString());
				break;
			}
			case OR: {
				BigInteger numA = new BigInteger(args[1]);
				BigInteger numB = new BigInteger(args[2]);
				
				System.out.println(numA.or(numB).toString());
				break;
			}
			case XOR: {
				BigInteger numA = new BigInteger(args[1]);
				BigInteger numB = new BigInteger(args[2]);
				
				System.out.println(numA.xor(numB).toString());
				break;
			}
			case TOLERANCE_INTERVAL: {
				Apfloat min = NumberHelper.create(args[1], RADIX).real();
				Apfloat max = NumberHelper.create(args[2], RADIX).real();
				
				Apfloat minCDF = Statistics.cdf(min);
				Apfloat maxCDF = Statistics.cdf(max);
				
				Apfloat interval = maxCDF.subtract(minCDF);
				System.out.println(interval.toString(true));
				break;
			}
			case ERROR_FUNCTION: {
				Apfloat num = NumberHelper.create(args[1], RADIX).real();
				
				Apfloat error = Statistics.erf(num);
				System.out.println(error.toString(true));
				break;
			}
			case BASE_CONVERT: {
				int orig_base = Integer.parseInt(args[1]);
				int new_base = Integer.parseInt(args[2]);
				Apcomplex num = NumberHelper.create(args[3], orig_base);
				
				Apcomplex new_num = num.toRadix(new_base);
				System.out.println(new_num.toString(true));
				break;
			}
			case PARALLEL_RESISTANCE: {
				ArrayList<Apcomplex> resistors = new ArrayList<>();
				
				for (int i = 1; i < args.length; i++) {
					resistors.add(NumberHelper.create(args[i], 10));
				}
				
				Apcomplex resistanceReciprocal = MathUtils.ZERO;
				
				for (Apcomplex resistor : resistors) {
					resistanceReciprocal = resistanceReciprocal.add(MathUtils.ONE.divide(resistor));
				}
				
				System.out.println(NumberHelper.format(MathUtils.ONE.divide(resistanceReciprocal)));
				break;
			}
			case INVALID_ARGUMENT_COUNT: {
				System.out.println("amath-ng: ERROR: Review your argument count!");
				break;
			}
			case INVALID_OPERATION: {
				System.out.println("amath-ng: ERROR: Invalid operation!");
				break;
			}
			default: {
				System.out.println("amath-ng: ERROR: INVOPC - Please send this error to the developers! (operation " + op + ", argc " + (argc + 1) + ", opargc " + argc + ")");
				break;
			}
		}
	}

}
