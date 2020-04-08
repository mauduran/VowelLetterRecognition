package com.iteso.rm;

/**
 * Matrix.java<br><br>
 * Esta clase contiene las operaciones básicas que pueden hacerse en una matriz.
 * @author Mauricio Durán Padilla, Edgar Francisco Medina Rifas
 * @version 1.0
 */
public class Matrix {
	private int rows;
	private int cols;
	private double[][] matrix;
 
	/**
	 * Constructor de matriz con dos parámetros 
	 * @param r - Número de filas
	 * @param c - Número de columnas
	 */
	public Matrix(int r, int c) {
		rows = r;
		cols = c;
		matrix = new double[rows][cols];
	}

	/**
	 * Constructor de Matriz a partir de una previa
	 * @param m - Arreglo bidimensional
	 */
	public Matrix(double[][] m) {
		matrix = m;
		cols = m.length;
		rows = m[0].length;
	}
	
	/**
	 * Obtención de un elemento en la posición dada
	 * @param i - Índice de la fila
	 * @param j - Índice de la columna
	 * @return Elemento de la matriz en [i][j]
	 */
	public double getElement(int i, int j){
		return matrix[i][j];
	}
	
	/**
	 * Asignación de un elemento en la posición dada
	 * @param i - Índice de la fila
	 * @param j - Índice de la columna
	 * @param x - Valor a asignar
	 */
	public void setElement(int i, int j, double x){
		matrix[i][j] = x;
	}
	
	/**
	 * A partir del objeto se regresa un arreglo 2D
	 * @return arreglo bidimensional con los datos de la matriz
	 */
	public double[][] to2dArray(){
		return matrix;
	}

	/**
	 * Getter para las filas
	 * @return la cantidad de filas de la matriz
	 */
	public int getRows() {
		return this.rows;
	}
	
	/**
	 * Getter para las columnas
	 * @return la cantidad de columnas de la matriz
	 */
	public int getColumns() {
		return this.cols;
	}
	
	/**
	 * Asignar los valores de la matriz 
	 * @param mat - Arreglo bidimensional de datos
	 */
	public void setMat(double mat[][] ) {
		this.matrix = mat;
		this.setColumns(mat[0].length);
		this.setRows(mat.length);
	} 
	
	/**
	 * Asignar cantidad de filas 
	 * @param rows - Número de filas a las que se quiere redimensionar la matriz
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	/**
	 * Asignar cantidad de filas 
	 * @param cols - Número de columnas a las que se quiere redimensionar la matriz
	 */
	public void setColumns(int cols) {
		this.cols = cols;
	}

	/**
	 * Impresión de la matriz haciendo Override del método toString
	 */
	@Override 
	public String toString() {
		String output = "";
		for (int i =0; i<rows; i++) {
			for (int j = 0; j<cols; j++) {
				output += String.format("%+.4f ", matrix[i][j]); 		    	  
			}
			output += "\n";
		}
		return output;
	}

	/**
	 * Se modifica la matriz al ser multilicada por un escalar
	 * @param n - Escalar a multiplicar
	 */
	public void multiplyScalar(double n ) {
		for (int i =0; i<rows; i++) {
			for (int j = 0; j<cols; j++) {
				matrix[i][j] *= n;
			}
		}
	}
	
	/**
	 * Multiplicación entre dos matrices, la propia por otra
	 * @param n - Matriz a multilpicar
	 * @return Resultado de la multiplicación de las dos matrices
	 */
	public Matrix multiply(Matrix n) {
		Matrix result = new Matrix(rows, n.cols);
		if (cols == n.rows) {
			//for each spot in the new matrix
			for (int i =0; i<rows; i++) {
				for (int j = 0; j<n.cols; j++) {
					double sum = 0;
					for (int k = 0; k<cols; k++) {
						sum+= matrix[i][k]*n.matrix[k][j];
					}
					result.matrix[i][j] = sum;
				}
			}
		}
		return result;
	}


	/**
	 * Producto punto entre dos matrices.
	 * @param n - Matriz por la que se multiplica el objeto
	 * @return Resultado del producto punto
	 */
	public Matrix dot(Matrix n) {
		Matrix newMatrix = new Matrix(rows, cols);
		if (cols == n.cols && rows == n.rows) {
			for (int i =0; i<rows; i++) {
				for (int j = 0; j<cols; j++) {
					newMatrix.matrix[i][j] = matrix[i][j] * n.matrix[i][j];
				}
			}
		}
		return newMatrix;
	}
	
	/**
	 * Inicialización de la matriz con valores random.
	 */
	void randomize() {
		for (int i =0; i<rows; i++) {
			for (int j = 0; j<cols; j++) {
				matrix[i][j] = (double) (Math.random()*2-1);
			}
		}
	}

	/**
	 * Suma de dos matrices
	 * @param n - Matriz que se le suma a la actual.
	 * @return Resultado de la suma de matrices.
	 */
	public Matrix add(Matrix n) {
		Matrix newMatrix = new Matrix(rows, cols);
		if (cols == n.cols && rows == n.rows) {
			for (int i =0; i<rows; i++) {
				for (int j = 0; j<cols; j++) {
					newMatrix.matrix[i][j] = matrix[i][j] + n.matrix[i][j];
				}
			}
		}
		return newMatrix;
	}
	

	/**
	 * Diferencia entre dos matrices, la actual menos la del parámetro
	 * @param n - Matriz a restar
	 * @return Resultado de la resta de matrices.
	 */
	public Matrix subtract(Matrix n) {
		Matrix newMatrix = new Matrix(rows, cols);
	
		if (cols == n.cols && rows == n.rows) {
			for (int i =0; i<n.rows; i++) {
				for (int j = 0; j<n.cols; j++) {
					newMatrix.matrix[i][j] = matrix[i][j] - n.matrix[i][j];
				}
			}
		}
		return newMatrix;
	}

	/** Transposición de la matriz
	 * 
	 * @return Matriz transpuesta
	 */
	public Matrix transpose() {
		Matrix n = new Matrix(cols, rows);
		for (int i =0; i<rows; i++) {
			for (int j = 0; j<cols; j++) {
				n.matrix[j][i] = matrix[i][j];
			}
		}
		return n;
	}

	/**
	 * Crea una matriz de una columna
	 * @param arr - Arreglo unidimensional.
	 * @return Matriz de una sola columna.
	 */
	public static Matrix singleColumnMatrixFromArray(double[] arr) {
		Matrix n = new Matrix(arr.length, 1);
		for (int i = 0; i< arr.length; i++) {
			n.matrix[i][0] = arr[i];
		}
		return n;
	}


	/**
	 * Crea un matriz cuadrada a partir de un arreglo
	 * @param arr - Arreglo unidimensional.
	 */
	public void fromArray(double[] arr) {
		for (int i = 0; i< rows; i++) {
			for (int j = 0; j< cols; j++) {
				matrix[i][j] =  arr[j+i*cols];
			}
		}
	}


	/**
	 * Se crea un arreglo a partir de una matriz
	 * @return Arreglo unidimensional con los datos de la matriz.
	 */
	public double[] toArray() {
		double[] arr = new double[rows*cols];
		for (int i = 0; i< rows; i++) {
			for (int j = 0; j< cols; j++) {
				arr[j+i*cols] = matrix[i][j];
			}
		}
		return arr;
	}

	/**
	 * Este método clona una matriz.
	 * @return Objeto tipo matrix con información del objeto invocador.
	 */
	public Matrix clone() {
		Matrix clone = new  Matrix(rows, cols);
		for (int i =0; i<rows; i++) {
			for (int j = 0; j<cols; j++) {
				clone.matrix[i][j] = matrix[i][j];
			}
		} 
		return clone;
	}
	
	/**
	 * Este método hace una suma de los elementos del objeto matriz que invoca.
	 * @return Suma de los elementos.
	 */
	public double sumOfElements() {
		double sum = 0;
		
		for (int i =0; i<this.rows; i++) {
			for (int j = 0; j<cols; j++) {
				sum = matrix[i][j];
			}
		}	
		return sum;
	}
	
	/**
	 * Determina si todos los valores de una matriz 
	 * no sobrepasan el umbral. Usada en Neural_Net
	 * @param umbral - Valor con el que se quiere evaluar los datos de la matriz
	 * @return Boolean que determina si se sobrepasa el umbral
	 * @see Neural_Net
	 */
	public boolean outputIsRight(double umbral){
		for(int i=0;i<this.rows;i++) {
			for(int j=0; j<this.cols; j++) {
				if(Math.abs(this.matrix[i][j])>umbral) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Convierte una matriz bidimensional a una de una sola columna.
	 * @param n - Matriz que se quiere convertir
	 * @return Matriz con una sola columna
	 */
	public static Matrix fromMatrixToOneColumnMatrix(Matrix n) {
		double arreglo[] = n.toArray();
		
		return Matrix.singleColumnMatrixFromArray(arreglo);
	}
	
	/**
	 * Este método funciona para aplicar la función de costo a los resultados de la red neuronal.
	 * @param result - Objeto de tipo Matrix que contiene el resultado del paso hacia adelante de la red neuronal.
	 * @return Objeto tipo matrix con la información de la operacion de la función costo.
	 */
	public double cost(Matrix result) {	
		Matrix error = this.subtract(result);
		error = error.dot(error);
		return (error.sumOfElements())/error.getRows();
	}
	
	/**
	 * Método que aplica la derivada de la función de costo de la red neuronal.
	 * @param result - Objeto tipo Matrix que contiene el resultado del paso hacia adelante de la red neuronal.
	 * @return Objeto tipo matrix con la información de la operación de la derivada del costo.
	 */
	public Matrix costDerivative (Matrix result) {
		Matrix error = this.subtract(result);
		error.multiplyScalar(2.0f);
		return error;
	}
}

