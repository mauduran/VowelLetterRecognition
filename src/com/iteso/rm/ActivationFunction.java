package com.iteso.rm;

/**
 * ActivationFunction.java<br><br>
 * Interfaz que tiene dos métodos base y que se utiliza para la clase Neural_Net
 * @author Mauricio Durán Padilla, Edgar Francisco Medina Rifas
 * @version 1.0
 * 
 */
public interface ActivationFunction {
	/**
	 * Método de activación que usa una función matemática para normalizar de cierto modo los datos de una matriz.
	 * @param mat Matriz que se busca activar.
	 * @return Matriz con los valores activados
	 */
	public Matrix activation(Matrix mat);
	
	/**
	 * Método de usa una la derivada de una función matemática para una matriz.
	 * @param mat Matriz que se busca derivar.
	 * @return Matriz con los valores de la derivada
	 */
	public Matrix activationDerived(Matrix mat);
	
}
