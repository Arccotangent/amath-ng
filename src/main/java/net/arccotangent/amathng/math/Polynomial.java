package net.arccotangent.amathng.math;

import net.arccotangent.amathng.utils.MathUtils;
import net.arccotangent.amathng.utils.NumberHelper;
import org.apache.commons.lang3.ArrayUtils;
import org.apfloat.Apcomplex;
import org.apfloat.ApcomplexMath;
import org.apfloat.Apfloat;

import java.util.ArrayList;
import java.util.Arrays;

public class Polynomial {

	private ArrayList<PolynomialTerm> terms;

	public Polynomial(ArrayList<PolynomialTerm> terms) {
		this.terms = terms;
		this.combineLikeTerms();
	}

	private void sortTermsByVariable() {
		//bubble sort
		for (int i = 0; i < terms.size(); i++) {
			for (int j = i + 1; j < terms.size(); j++) {
				PolynomialTerm tmp;
				if (terms.get(i).compareVariables(terms.get(j)) == 1) {
					tmp = terms.get(i);
					terms.set(i, terms.get(j));
					terms.set(j, tmp);
				}
			}
		}

		PolynomialTerm[] termsArray = new PolynomialTerm[terms.size()];
		terms.toArray(termsArray);
		ArrayUtils.reverse(termsArray);
		terms = new ArrayList<>(Arrays.asList(termsArray));
	}

	private void removeZeroTerms() {
		this.combineLikeTerms();

		for (int i = 0; i < terms.size(); i++) {
			if (terms.get(i).getCoefficient().compareTo(MathUtils.ZERO.real()) == 0) {
				terms.remove(terms.get(i));
			}
		}

		if (terms.isEmpty()) {
			terms.add(new PolynomialTerm(MathUtils.ZERO.real(), ""));
		}
	}

	private void simplifyAllVariables() {
		for (PolynomialTerm term : terms) {
			term.simplifyVariable();
		}

		sortTermsByVariable();
	}

	private void combineLikeTerms() {
		ArrayList<PolynomialTerm> newTerms = new ArrayList<>();

		this.simplifyAllVariables();

		for (PolynomialTerm term : terms) {
			int index = -1;
			for (int j = 0; j < newTerms.size(); j++) {
				if (newTerms.get(j).getVariable().equals(term.getVariable())) {
					index = j;
					break;
				}
			}

			if (index != -1) {
				newTerms.get(index).setCoefficient(newTerms.get(index).getCoefficient().add(term.getCoefficient()));
			} else {
				newTerms.add(term);
			}
		}

		if (newTerms.isEmpty()) {
			newTerms.add(new PolynomialTerm(MathUtils.ZERO.real(), ""));
		}

		terms = new ArrayList<>(newTerms);
	}

	public void print() {
		if (terms.size() == 1) {
			System.out.println(NumberHelper.format(new Apcomplex(terms.get(0).getCoefficient())) + terms.get(0).getVariable());
			return;
		}

		for (int i = 0; i < terms.size(); i++) {

			if (terms.get(i).getCoefficient().compareTo(MathUtils.ZERO.real()) < 0) {
				if (i == 0)
					System.out.print("-");
				else
					System.out.print(" - ");
			} else {
				if (i != 0)
					System.out.print(" + ");
			}

			System.out.print(NumberHelper.format(ApcomplexMath.abs(new Apcomplex(terms.get(i).getCoefficient()))) + terms.get(i).getVariable());
		}
		System.out.println();
	}

	private Polynomial deepCopy() {
		ArrayList<PolynomialTerm> newTerms = new ArrayList<>(terms);
		return new Polynomial(newTerms);
	}

	private ArrayList<PolynomialTerm> getTerms() {
		return terms;
	}

	public Polynomial add(Polynomial polynomial) {
		Polynomial newPolynomial = this.deepCopy();

		newPolynomial.terms.addAll(polynomial.getTerms());
		newPolynomial.combineLikeTerms();
		newPolynomial.removeZeroTerms();

		return newPolynomial;
	}

	public Polynomial subtract(Polynomial polynomial) {
		Polynomial newPolynomial = this.deepCopy();
		for (PolynomialTerm foreignTerm : polynomial.getTerms()) {
			PolynomialTerm negatedForeignTerm = new PolynomialTerm(foreignTerm.getCoefficient().negate(), foreignTerm.getVariable());
			newPolynomial.terms.add(negatedForeignTerm);
		}

		newPolynomial.combineLikeTerms();
		newPolynomial.removeZeroTerms();
		return newPolynomial;
	}

	public Polynomial multiply(Polynomial polynomial) {
		Polynomial newPolynomial = this.deepCopy();
		Polynomial orig = this.deepCopy();
		int termCount = terms.size();

		for (int i = 0; i < termCount; i++) {
			ArrayList<PolynomialTerm> newTerms = new ArrayList<>();

			for (PolynomialTerm foreignTerm : polynomial.getTerms()) {
				PolynomialTerm newTerm = new PolynomialTerm(terms.get(i).getCoefficient().multiply(foreignTerm.getCoefficient()), terms.get(i).getVariable() + foreignTerm.getVariable());
				newTerm.simplifyVariable();
				newTerms.add(newTerm);
			}

			newPolynomial = newPolynomial.add(new Polynomial(newTerms));

			if (i == termCount - 1) {
				newPolynomial = newPolynomial.subtract(orig);
			}
		}

		newPolynomial.combineLikeTerms();
		newPolynomial.removeZeroTerms();

		return newPolynomial;
	}

	private boolean isZero() {
		for (PolynomialTerm term : terms) {
			if (term.getCoefficient().compareTo(MathUtils.ZERO.real()) != 0)
				return false;
		}

		return true;
	}

	private double highestDegree() {
		double degree = 0;

		for (int i = 0; i < terms.size(); i++) {
			if (i == 0) {
				degree = Double.parseDouble(terms.get(i).highestDegree());
			} else {
				double newDegree = Double.parseDouble(terms.get(i).highestDegree());
				degree = newDegree > degree ? newDegree : degree;
			}
		}

		return degree;
	}

	public Polynomial divide(Polynomial polynomial) {
		Polynomial newPolynomial = new Polynomial(new ArrayList<>());
		Polynomial orig = this.deepCopy();

		orig.sortTermsByVariable();
		polynomial.sortTermsByVariable();

		while (!orig.isZero() && (orig.highestDegree() >= polynomial.highestDegree())) {
			Apfloat coefficient = orig.getTerms().get(0).getCoefficient().divide(polynomial.getTerms().get(0).getCoefficient());

			orig.getTerms().get(0).simplifyVariable();
			polynomial.getTerms().get(0).simplifyVariable();

			String var1 = orig.getTerms().get(0).getVariable();
			String var2 = polynomial.getTerms().get(0).getVariable();

			StringBuilder newVar = new StringBuilder();
			ArrayList<Character> handledVariables = new ArrayList<>();
			divideVariables(polynomial, orig, var1, newVar, handledVariables);
			divideVariables(polynomial, orig, var2, newVar, handledVariables);

			PolynomialTerm term = new PolynomialTerm(coefficient, newVar.toString());
			ArrayList<PolynomialTerm> termAL = new ArrayList<>();
			termAL.add(term);
			Polynomial quotientTerm = new Polynomial(termAL);

			newPolynomial = newPolynomial.add(quotientTerm);
			orig = orig.subtract(quotientTerm.multiply(polynomial));
		}

		newPolynomial.combineLikeTerms();
		newPolynomial.removeZeroTerms();

		return newPolynomial;
	}

	private void divideVariables(Polynomial polynomial, Polynomial orig, String var, StringBuilder newVar, ArrayList<Character> handledVariables) {
		for (int i = 0; i < var.length(); i++) {
			char aChar = var.charAt(i);
			if (handledVariables.contains(aChar)) {
				continue;
			}

			if (Character.isAlphabetic(aChar)) {
				double origVarLetterExponent = Double.parseDouble(orig.getTerms().get(0).getExponentForVariableLetter(aChar));
				double divisorVarLetterExponent = Double.parseDouble(polynomial.getTerms().get(0).getExponentForVariableLetter(aChar));

				newVar.append(aChar);
				newVar.append("^");
				newVar.append(origVarLetterExponent - divisorVarLetterExponent);

				handledVariables.add(aChar);
			}
		}
	}

}
