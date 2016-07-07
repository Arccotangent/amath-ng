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
		} else {
			return -2;
		}
	}

}
