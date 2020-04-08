package com.iteso.rm;

/**
 * Sigmoid.java<br><br>
 * Esta clase es un Singleton que implementa la interfaz ActivationFunction. Utiliza la función 
 * sigmoide como función de activación para una red neuronal y así mismo su derivada
 * @author Mauricio Durán Padilla+ Edgar Francisco Medina Rifas
 * @version 1.0
 * @see ActivationFunction
 * 
 *
 */
public class Sigmoid implements ActivationFunction {
	private static Sigmoid instance = null;
	
	private Sigmoid() {
		
	}
	
	/**
	 * Método que permite trabajar con la función sigmoide. Daddo que es un Singleton no hay constructor público por
	 * lo que se utiliza este método para obtener la instancia de la clase.
	 * @return objeto Singleton de la clase Sigmoid
	 */
	public static Sigmoid getInstance() {
		if(instance == null) {
			instance = new Sigmoid();
		}
		return instance;
	}
	

	/**
	 * Se lleva a cabo la evaluación de la función sigmoide.
	 * @param x - Entrada para la función sigmoide
	 * @return Resultado de la función Sigmoide dado un input
	 */
	
	public static double sigmoid(double x) {
		double y = (1 / (1 + Math.pow(Math.E, -x)));
		return y;
	}
	
	
	/**
	 * Se usa la función sigmoide para la activación de una matriz
	 * @param mat - Matriz de entrada y la cual quiere ser activada
	 * @return Matriz resultado de la activación
	 */
	@Override
	public Matrix activation(Matrix mat) {
		Matrix n = new Matrix(mat.getRows(), mat.getColumns());
		for (int i =0; i<mat.getRows(); i++) {
			for (int j = 0; j<mat.getColumns(); j++) {
				n.setElement(i, j, sigmoid(mat.getElement(i, j)));
			}
		}
		return n;
	}

	/**
	 * Derivada de la función sigmoide. 
	 * @param mat - Matriz de entrada de la cual se quiere obtener su derivada
	 * @return Matriz con las derivadas de la Matriz mat
	 */
	@Override
	public Matrix activationDerived(Matrix mat) {
		Matrix n = new Matrix(mat.getRows(), mat.getColumns());
		for (int i =0; i<mat.getRows(); i++) {
			for (int j = 0; j<mat.getColumns(); j++) {
				n.setElement(i, j, (mat.getElement(i, j) * (1- mat.getElement(i, j))));
			}
		}
		return n;
	}

}


