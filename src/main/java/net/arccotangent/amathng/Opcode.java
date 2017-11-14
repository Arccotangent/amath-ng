package net.arccotangent.amathng;

class Opcode {

	static Operation getOpcode(String operation, int argc) {
		if (operation.equalsIgnoreCase("add")) {
			if (argc >= 2)
				return Operation.ADDITION;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("sub")) {
			if (argc >= 2)
				return Operation.SUBTRACTION;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("mul")) {
			if (argc >= 2)
				return Operation.MULTIPLICATION;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("div")) {
			if (argc >= 2)
				return Operation.DIVISION;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("exp")) {
			if (argc == 2)
				return Operation.EXPONENTIATION;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("sqrt")) {
			if (argc == 1)
				return Operation.SQUARE_ROOT;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("qdr")) {
			if (argc == 3)
				return Operation.QUADRATIC;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("sf")) {
			if (argc == 1)
				return Operation.SIGNIFICANT_FIGURES;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("aoc")) {
			if (argc == 1)
				return Operation.AREA_OF_CIRCLE;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("mod")) {
			if (argc >= 2)
				return Operation.MODULUS;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("prm")) {
			if (argc == 1)
				return Operation.PRIMALITY_TEST;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("rand")) {
			if (argc == 2)
				return Operation.RANDOM_NUMBER;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("fac")) {
			if (argc == 1)
				return Operation.FACTOR;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("gcd")) {
			if (argc >= 2)
				return Operation.GREATEST_COMMON_DENOMINATOR;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("vtx")) {
			if (argc == 3)
				return Operation.VERTEX;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("hypot")) {
			if (argc == 2)
				return Operation.HYPOTENUSE;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("fct")) {
			if (argc == 1)
				return Operation.FACTORIAL;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("sin")) {
			if (argc == 1)
				return Operation.SINE;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("cos")) {
			if (argc == 1)
				return Operation.COSINE;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("tan")) {
			if (argc == 1)
				return Operation.TANGENT;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("asin")) {
			if (argc == 1)
				return Operation.ARCSINE;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("acos")) {
			if (argc == 1)
				return Operation.ARCCOSINE;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("atan")) {
			if (argc == 1)
				return Operation.ARCTANGENT;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("cbrt")) {
			if (argc == 1)
				return Operation.CUBE_ROOT;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("log")) {
			if (argc == 1)
				return Operation.LOGARITHM_E;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("log10")) {
			if (argc == 1)
				return Operation.LOGARITHM_10;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("psq")) {
			if (argc == 1)
				return Operation.PRINT_SQUARES;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("ppwr")) {
			if (argc == 2)
				return Operation.PRINT_POWER;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("pcr")) {
			if (argc == 2)
				return Operation.PERCENT_ERROR;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("cpi")) {
			if (argc == 4)
				return Operation.COMPOUND_INTEREST;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("avg")) {
			if (argc >= 2)
				return Operation.AVERAGE;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("stdev")) {
			if (argc >= 2)
				return Operation.STANDARD_DEVIATION;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("zsc")) {
			if (argc == 3)
				return Operation.Z_SCORE;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("ord")) {
			if (argc >= 2)
				return Operation.SORT;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("ccm")) {
			if (argc == 1)
				return Operation.CIRCUMFERENCE;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("los")) {
			if (argc == 3)
				return Operation.LAW_OF_SINES;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("loc")) {
			if (argc == 3)
				return Operation.LAW_OF_COSINES;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("pprm")) {
			if (argc == 1)
				return Operation.PRINT_PRIMES;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("hl")) {
			if (argc == 1)
				return Operation.HALF_LIFE;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("csc")) {
			if (argc == 1)
				return Operation.COSECANT;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("sec")) {
			if (argc == 1)
				return Operation.SECANT;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("cot")) {
			if (argc == 1)
				return Operation.COTANGENT;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("acsc")) {
			if (argc == 1)
				return Operation.ARCCOSECANT;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("asec")) {
			if (argc == 1)
				return Operation.ARCSECANT;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("acot")) {
			if (argc == 1)
				return Operation.ARCCOTANGENT;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("logb")) {
			if (argc == 2)
				return Operation.LOGARITHM_BASE;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("lcm")) {
			if (argc >= 2)
				return Operation.LEAST_COMMON_MULTIPLE;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("getf")) {
			if (argc == 1)
				return Operation.GET_FACTORS;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("cbc")) {
			if (argc == 4)
				return Operation.CUBIC;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("st")) {
			if (argc == 3)
				return Operation.ARITHMETIC_SEQUENCE_TERM;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("ss")) {
			if (argc == 3)
				return Operation.ARITHMETIC_SEQUENCE_SUM;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("ncr")) {
			if (argc == 2)
				return Operation.COMBINATION;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("npr")) {
			if (argc == 2)
				return Operation.PERMUTATION;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("genprm")) {
			if (argc == 1)
				return Operation.RANDOM_PRIME;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("dst")) {
			if (argc == 4)
				return Operation.DISTANCE;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("f2c")) {
			if (argc == 1)
				return Operation.FAHRENHEIT_TO_CELSIUS;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("c2f")) {
			if (argc == 1)
				return Operation.CELSIUS_TO_FAHRENHEIT;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("i2c")) {
			if (argc == 1)
				return Operation.INCHES_TO_CENTIMETERS;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("c2i")) {
			if (argc == 1)
				return Operation.CENTIMETERS_TO_INCHES;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("m2k")) {
			if (argc == 1)
				return Operation.MILES_TO_KILOMETERS;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("k2m")) {
			if (argc == 1)
				return Operation.KILOMETERS_TO_MILES;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("f2m")) {
			if (argc == 1)
				return Operation.FEET_TO_METERS;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("m2f")) {
			if (argc == 1)
				return Operation.METERS_TO_FEET;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("midpt")) {
			if (argc == 4)
				return Operation.MIDPOINT;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("slp")) {
			if (argc == 4)
				return Operation.SLOPE;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("d2r")) {
			if (argc == 1)
				return Operation.DEGREES_TO_RADIANS;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("r2d")) {
			if (argc == 1)
				return Operation.RADIANS_TO_DEGREES;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("p2k")) {
			if (argc == 1)
				return Operation.POUNDS_TO_KILOGRAMS;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("k2p")) {
			if (argc == 1)
				return Operation.KILOGRAMS_TO_POUNDS;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("g2l")) {
			if (argc == 1)
				return Operation.GALLONS_TO_LITERS;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("l2g")) {
			if (argc == 1)
				return Operation.LITERS_TO_GALLONS;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("cf2cm")) {
			if (argc == 1)
				return Operation.CUBIC_FEET_TO_CUBIC_METERS;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("cm2cf")) {
			if (argc == 1)
				return Operation.CUBIC_METERS_TO_CUBIC_FEET;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("crd")) {
			if (argc == 1)
				return Operation.CIRCLE_RADIUS;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("and")) {
			if (argc == 2)
				return Operation.AND;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("or")) {
			if (argc == 2)
				return Operation.OR;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("xor")) {
			if (argc == 2)
				return Operation.XOR;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("ti")) {
			if (argc == 2)
				return Operation.TOLERANCE_INTERVAL;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("erf")) {
			if (argc == 1)
				return Operation.ERROR_FUNCTION;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("bc")) {
			if (argc == 3)
				return Operation.BASE_CONVERT;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("pr")) {
			if (argc == 3)
				return Operation.PARALLEL_RESISTANCE;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("genrsa")) {
			if (argc == 1)
				return Operation.GENERATE_RSA_KEY;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("addmtx")) {
			if (argc == 3)
				return Operation.ADD_MATRICES;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("submtx")) {
			if (argc == 3)
				return Operation.SUBTRACT_MATRICES;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else if (operation.equalsIgnoreCase("linreg")) {
			if (argc == 1)
				return Operation.LINEAR_REGRESSION;
			else
				return Operation.INVALID_ARGUMENT_COUNT;
		} else {
			return Operation.INVALID_OPERATION;
		}
	}

}
