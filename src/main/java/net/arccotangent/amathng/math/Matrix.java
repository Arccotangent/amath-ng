package net.arccotangent.amathng.math;

import net.arccotangent.amathng.utils.NumberHelper;
import org.apfloat.Apcomplex;

public class Matrix {
	
	// Matrix data structure:
	// Apcomplex[x][y]
	// x = rows
	// y = columns
	
	private static int getMatrixRows(Apcomplex[][] matrix) {
		return matrix.length;
	}
	
	private static int getMatrixColumns(Apcomplex[][] matrix) {
		return matrix[0].length;
	}
	
	public static Apcomplex[][] addMatrices(Apcomplex[][] matrix1, Apcomplex[][] matrix2) {
		
		int rows = getMatrixRows(matrix1);
		int cols = getMatrixColumns(matrix1);
		
		Apcomplex[][] result = new Apcomplex[rows][cols];
		
		for (int x = 0; x < rows; x++) {
			for (int y = 0; y < cols; y++) {
				result[x][y] = matrix1[x][y].add(matrix2[x][y]);
			}
		}
		
		return result;
		
	}
	
	public static Apcomplex[][] subtractMatrices(Apcomplex[][] matrix1, Apcomplex[][] matrix2) {
		
		int rows = getMatrixRows(matrix1);
		int cols = getMatrixColumns(matrix1);
		
		Apcomplex[][] result = new Apcomplex[rows][cols];
		
		for (int x = 0; x < rows; x++) {
			for (int y = 0; y < cols; y++) {
				result[x][y] = matrix1[x][y].subtract(matrix2[x][y]);
			}
		}
		
		return result;
		
	}
	
	/*
	
	TODO fix matrix multiplication
	
	public static Apcomplex[][] multiplyMatrices(Apcomplex[][] matrix1, Apcomplex[][] matrix2) {
		int rows_1 = getMatrixRows(matrix1);
		int rows_2 = getMatrixRows(matrix2);
		int cols_1 = getMatrixColumns(matrix1);
		int cols_2 = getMatrixColumns(matrix2);
		
		if (rows_1 != cols_2) {
			throw new IllegalArgumentException("Matrix 1 row count is not equal to matrix 2 column count.");
		}
		
		Apcomplex[][] result = new Apcomplex[rows_2][cols_2];
		
		int resrow = 0;
		int rescol = 0;
		
		for (int x1 = 0; x1 < rows_1; x1++) {
			Apcomplex value = MathUtils.ZERO;
			for (int y = 0; y < cols_1; y++) {
				value = value.add(matrix1[x1][y].multiply(matrix2[y][x1]));
			}
			result[resrow][rescol] = value;
			resrow++;
		}
		
		return result;
	}
	*/
	
	public static void printMatrix(Apcomplex[][] matrix) {
		int rows = getMatrixRows(matrix);
		int cols = getMatrixColumns(matrix);
		
		for (int x = 0; x < rows; x++) {
			System.out.print("[");
			for (int y = 0; y < cols; y++) {
				System.out.print(NumberHelper.format(matrix[x][y]));
				if (cols - y != 1)
					System.out.print("\t");
			}
			System.out.println("]");
		}
	}
	
}
