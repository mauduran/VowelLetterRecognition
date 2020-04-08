package com.iteso.rm;

/**
 * LetterRecognition <br>
 * Esta clase funciona para obtener en un objeto la información sobre las clases a reconocer. Sirve para obtener la información 
 * proveniente de los archivos train del dataset de EMNIST. En un objeto de esta clase se guardan arreglos de matrices con información 
 * acerca de los "id" de las letras (A = 1, B = 2, C = 3, D = 4, E = 5) y la información acerca de la intensidad de los colores de la imagen.
 * @author Mauricio Duran Padilla, Edgar Francisco Medina Rifas
 *
 */
public class LetterRecognition {
	
	private Matrix [] idLetter = null;
	private Matrix [] inputs = null;	
	
	/**
	 * Método constructor de la clase. Recibe como parametros referencias para los objetos de la clase.
	 * @param inputs - Referencia a arreglo de tipo Matrix con la información de la intensidad de los colores de las muestras.
	 * @param outputs - Referencia a arreglo de tipo Matrix con la información de los ids de las letras.
	 */
	public LetterRecognition(Matrix[] inputs, Matrix[] outputs) {
		this.idLetter = outputs;
		this.inputs = inputs;
	}
	

	/**
	 * Método constructor de la clase.
	 */
	public LetterRecognition() {
		this.idLetter = null;
		this.inputs = null;
	}

	/**
	 * Método que regresa el atributo idLetter del objeto invocador.
	 * @return Arreglo de objetos tipo matrix del objeto invocador.
	 */
	public Matrix[] getIdLetter() {
		return idLetter;
	}
	
	/**
	 * Método que inicializa el atributo idLetter del objeto invocador. Recibe la referencia a un arreglo de tipo Matrix.
	 * @param idLetter - Referencia a arreglo de tipo Matrix con información de los id de las letras.
	 */
	public void setIdLetter(Matrix[] idLetter) {
		this.idLetter = idLetter;
	}
	
	/**
	 * Método que regresa el atributo de Inputs del objeto invocador.
	 * @return Arreglo de tipo Matrix con información sobre la intensidad de los colores de las muestras del archivo de entrenamiento.
	 */
	public Matrix[] getInputs() {
		return inputs;
	}
	
	/**
	 * Método que inicializa el atributo inputs del objeto de invocador.
	 * @param inputs Referencia a un arreglo de tipo  Matrix con información sobre la intensidad de los colores de las muestras del archivo de entrenamiento.
	 */
	public void setInputs(Matrix[] inputs) {
		this.inputs = inputs;
	}
	
	/**
	 * Método que te dice la letra apartir de una imagen y la red neuronal con el entrenamiento previo.
	 * @param image - Objeto tipo matrix con información de la imagen a adivinar.
	 * @param nn - Objeto tipo red neuronal, el cual contiene la información de una red neuronal ya entrenada.
	 * @return Un String con la letra interpretada por la red neuronal o NaN en caso de no poder realizar la operación.
	 * @see Letters
	 */
	public static String interpretLetter(Matrix image, Neural_Net nn) {
		Matrix temp = nn.obtainResult(image);
		int pos = 0;
		double max =0;
		for(int i = 0; i < 5; i++) {
			if(temp.getElement(i, 0)> max) {
				pos = i;
				max = temp.getElement(i, 0);
			}
		}

		return Letters.getValue(pos);
		
	}

}
