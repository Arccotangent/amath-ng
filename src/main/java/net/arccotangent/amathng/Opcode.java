package net.arccotangent.amathng;

public class Opcode {

	public static int getOpcode(String operation, int argc) {
		if (operation.equalsIgnoreCase("add")) {
			if (argc >= 2)
				return 1;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("sub")) {
			if (argc >= 2)
				return 2;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("mul")) {
			if (argc >= 2)
				return 3;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("div")) {
			if (argc >= 2)
				return 4;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("exp")) {
			if (argc == 2)
				return 5;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("sqrt")) {
			if (argc == 1)
				return 6;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("qdr")) {
			if (argc == 3)
				return 7;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("sf")) {
			if (argc == 1)
				return 8;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("aoc")) {
			if (argc == 1)
				return 9;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("mod")) {
			if (argc >= 2)
				return 10;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("prm")) {
			if (argc == 1)
				return 11;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("rand")) {
			if (argc == 2 || argc == 3)
				return 12;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("fac")) {
			if (argc == 1)
				return 13;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("gcd")) {
			if (argc == 2)
				return 14;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("vtx")) {
			if (argc == 3)
				return 15;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("hypot")) {
			if (argc == 2)
				return 16;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("fct")) {
			if (argc == 1)
				return 17;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("sin")) {
			if (argc == 1)
				return 18;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("cos")) {
			if (argc == 1)
				return 19;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("tan")) {
			if (argc == 1)
				return 20;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("asin")) {
			if (argc == 1)
				return 21;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("acos")) {
			if (argc == 1)
				return 22;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("atan")) {
			if (argc == 1)
				return 23;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("cbrt")) {
			if (argc == 1)
				return 24;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("log")) {
			if (argc == 1)
				return 25;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("log10")) {
			if (argc == 1)
				return 26;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("psq")) {
			if (argc == 1)
				return 27;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("ppwr")) {
			if (argc == 2)
				return 28;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("pcr")) {
			if (argc == 2)
				return 29;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("cpi")) {
			if (argc == 4)
				return 30;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("avg")) {
			if (argc >= 2)
				return 31;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("stdev")) {
			if (argc >= 2)
				return 32;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("zsc")) {
			if (argc == 3)
				return 33;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("ord")) {
			if (argc >= 2)
				return 34;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("ccm")) {
			if (argc == 1)
				return 35;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("los")) {
			if (argc == 3)
				return 36;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("loc")) {
			if (argc == 3)
				return 37;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("pprm")) {
			if (argc == 1)
				return 38;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("hl")) {
			if (argc == 1)
				return 39;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("csc")) {
			if (argc == 1)
				return 40;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("sec")) {
			if (argc == 1)
				return 41;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("cot")) {
			if (argc == 1)
				return 42;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("acsc")) {
			if (argc == 1)
				return 43;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("asec")) {
			if (argc == 1)
				return 44;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("acot")) {
			if (argc == 1)
				return 45;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("logb")) {
			if (argc == 2)
				return 46;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("lcm")) {
			if (argc == 2)
				return 47;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("getf")) {
			if (argc == 1)
				return 48;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("cbc")) {
			if (argc == 4)
				return 49;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("st")) {
			if (argc == 3)
				return 50;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("ss")) {
			if (argc == 3)
				return 51;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("ncr")) {
			if (argc == 2)
				return 52;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("npr")) {
			if (argc == 2)
				return 53;
			else
				return -1;
		} else if (operation.equalsIgnoreCase("genprm")) {
			if (argc == 1)
				return 54;
			else
				return -1;
		} else {
			return -2;
		}
	}

}
