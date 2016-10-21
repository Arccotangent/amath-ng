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
