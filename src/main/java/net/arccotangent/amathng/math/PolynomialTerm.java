package net.arccotangent.amathng.math;

import net.arccotangent.amathng.utils.MathUtils;
import org.apache.commons.lang3.StringUtils;
import org.apfloat.Apfloat;

import java.util.ArrayList;

class VariableLetter {

	private char letter;
	private String exponent;

	VariableLetter(char letter, String exponent) {
		this.letter = letter;
		this.exponent = exponent;
	}

	char getLetter() {
		return letter;
	}

	int getExponentAsInt() {
		return Integer.parseInt(exponent);
	}

	void setExponent(String exponent) {
		this.exponent = exponent;
	}

	@Override
	public String toString() {
		switch (exponent) {
			case "0":
				return "";
			case "1":
				return "" + letter;
			default:
				return letter + "^" + exponent;
		}
	}
}

public class PolynomialTerm {

	private Apfloat coefficient;
	private String variable;

	public PolynomialTerm(Apfloat coefficient, String variable) {
		this.coefficient = coefficient;
		this.variable = variable;

		if (this.variable == null)
			this.variable = "";
	}

	Apfloat getCoefficient() {
		return coefficient;
	}

	String getVariable() {
		return variable;
	}

	void setCoefficient(Apfloat coefficient) {
		this.coefficient = coefficient;
	}

	private void setVariable(String variable) {
		this.variable = variable;
	}

	String highestDegree() {
		this.simplifyVariable();

		String var = this.getVariable();
		StringBuilder exponent = new StringBuilder();
		double degree = 0;

		var = StringUtils.remove(var, "^");
		char[] chars = var.toCharArray();

		for (int i = 0; i < chars.length; i++) {
			if (Character.isAlphabetic(chars[i])) {
				for (char aChar : chars) {
					if (Character.isDigit(aChar) || aChar == '-') {
						exponent.append(aChar);
					}

					if (i == 0) {
						if (exponent.toString().isEmpty())
							degree = 1;
						else
							degree = Double.parseDouble(exponent.toString());
					} else {
						double newDegree = Double.parseDouble(exponent.toString());
						degree = newDegree > degree ? newDegree : degree;
					}
				}
			}
		}

		return Double.toString(degree);
	}

	String getExponentForVariableLetter(char letter) {
		this.simplifyVariable();

		String var = this.getVariable();
		StringBuilder exponent = new StringBuilder();
		boolean exists = false;

		var = StringUtils.remove(var, "^");
		char[] chars = var.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == letter) {
				exists = true;
				for (int j = i + 1; j < chars.length; j++) {
					if (Character.isDigit(chars[j]) || chars[j] == '-')
						exponent.append(chars[j]);
					else
						break;
				}
				break;
			}
		}

		if (exponent.toString().isEmpty() && exists) {
			return "1";
		} else if (!exists) {
			return "0";
		}

		return exponent.toString();
	}

	void simplifyVariable() {
		if (variable.isEmpty())
			return;

		if (coefficient.compareTo(MathUtils.ZERO.real()) == 0) {
			variable = "";
			return;
		}

		String var = this.getVariable();
		//ArrayList<Character> varLetters = new ArrayList<>();
		ArrayList<VariableLetter> varLetters = new ArrayList<>();

		var = StringUtils.remove(var, "^");
		char[] chars = var.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (Character.isAlphabetic(chars[i])) {
				StringBuilder exponent = new StringBuilder();
				for (int j = i + 1; j < chars.length; j++) {
					if (Character.isDigit(chars[j]) || chars[j] == '-')
						exponent.append(chars[j]);
					else
						break;
				}

				VariableLetter varLetter;
				if (exponent.toString().isEmpty()) {
					//varLetters.add(chars[i]);
					varLetter = new VariableLetter(chars[i], "1");
				} else {
					//for (int x = 0; x < Integer.parseInt(exponent.toString()); x++) {
					//	varLetters.add(chars[i]);
					//}
					varLetter = new VariableLetter(chars[i], exponent.toString());
				}

				int index = -1;
				for (VariableLetter letter : varLetters) {
					if (letter.getLetter() == varLetter.getLetter()) {
						index = varLetters.indexOf(letter);
					}
				}

				if (index == -1) {
					varLetters.add(varLetter);
				} else {
					varLetters.get(index).setExponent(Integer.toString(varLetter.getExponentAsInt() + varLetters.get(index).getExponentAsInt()));
				}
			}
		}

		varLetters.sort((o1, o2) -> Character.compare(o2.getLetter(), o1.getLetter()));

		StringBuilder newVar = new StringBuilder();

		for (VariableLetter varLetter : varLetters) {
			newVar.append(varLetter.toString());
		}
		/*
		varLetters.sort(Comparator.reverseOrder());

		for (int i = 0; i < varLetters.size(); i++) {
			char current = varLetters.get(i);
			int exponent = 0;
			for (int j = i; j < varLetters.size(); j++) {
				if (varLetters.get(j) == current)
					exponent++;
				else
					break;
			}

			newVar.append(current);
			if (exponent > 1) {
				newVar.append('^');
				newVar.append(exponent);
				i += exponent - 1;
			}
		}
		*/

		this.setVariable(newVar.toString());
	}

	int compareVariables(PolynomialTerm term) {
		String termVar = term.getVariable();
		String thisVar = this.getVariable();

		termVar = StringUtils.removePattern(termVar, "((?![a-z]).)");
		thisVar = StringUtils.removePattern(thisVar, "((?![a-z]).)");

		return thisVar.compareTo(termVar);
	}

	/*public int compareCoefficients(PolynomialTerm term) {
		return this.coefficient.compareTo(term.getCoefficient());
	}*/
}
