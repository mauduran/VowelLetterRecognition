 package com.iteso.rm;

/**
 * Neural_Net.java<br><br>
 * Esta clase tiene visibilidad package y contiene la estructura de una capa de red neuronal
 * con los pesos y bias de sus neuronas con la siguiente capa.
 * @author Mauricio Durán Padilla & Edgar Francisco Medina Rifas
 * @version 1.0
 */
class Neural_Layer {
	private final int NUM_NEURONS;
	
	private final int NUM_CONNECTIONS;
	
	private Matrix weights;
	
	private Matrix bias;
	
	/**
	 * Constructor de un objeto Neural Layer, este objeto se crea en la clase de Neural_Net
	 * @param num_neurons - Número de neuronas en la capa a crear 
	 * @param num_connections - Número de neuronas en la capa siguiente
	 * @see Neural_Net 
	 */
	
	public Neural_Layer(int num_neurons, int num_connections) {
		NUM_NEURONS = num_neurons;
		NUM_CONNECTIONS = num_connections;
		weights = new Matrix(num_connections, num_neurons);
		weights.randomize();
		bias = new Matrix(num_connections,1);
		bias.randomize();
	}
	
	/**
	 * Getter de los pesos de la capa 
	 * @return Pesos de la capa actual con la capa siguiente
	 */
	public Matrix getWeights() {
		return weights;
	}
	
	/**
	 * Getter de los bias de la capa 
	 * @return Bias de la capa actual con la capa siguiente
	 */
	public Matrix getBias() {
		return bias;
	}
	
	/**
	 * Método que permite modificar los pesos durante el training 
	 * @param delta - Diferencial por el que se quiere cambiar los pesos
	 */
	
	public void updateWeights(Matrix delta) {
		Matrix temp = this.weights.subtract(delta);
		if(temp.sumOfElements()!=0.0f) {
			this.weights = temp;
		}
	}
	
	/**
	 * Método que permite modificar los bias durante el training 
	 * @param delta - Diferencial por el que se quiere cambiar los bias
	 */

	public void updateBias(Matrix delta) {
		Matrix temp = this.bias.subtract(delta);
		if(temp.sumOfElements()!=0.0f) {
			this.bias = temp;
		}
		
	}
	
	/**
	 * Método que permite asignar los pesos durante el training 
	 * @param mat Matriz con los pesos que quieren asignarse
	 */
	public void setWeights(double mat[][]) {
		this.weights.setMat(mat);
	}
	
	/**
	 * Método que permite asignar los bias durante el training 
	 * @param mat Matriz con los bias que quieren asignarse
	 */
	public void setBias(double mat[][]) {
		this.bias.setMat(mat);
	}
	
}
