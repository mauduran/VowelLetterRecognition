package com.iteso.rm;

/**
 * Neural_Net.java<br><br>
 * Esta clase contiene la estructura de una red neuronal. Es una forma genérica de una red neuronal
 * de manera que pueden crearse redes de distintos tamaños y con distintas funciones de activación
 * @author Mauricio Durán Padilla, Edgar Francisco Medina Rifas
 * @version 1.0
 * @see ActivationFunction
 * 
 *
 */
public class Neural_Net {
	public Neural_Layer network[];
	int topologyStruct[];
	private ActivationFunction activationFunction = null;
	
	/**
	 * Función para asignar una función de activación a la red neuronal
	 * @param activationFunc - función de activación que implementa a ActivationFunction
	 * @see ActivationFunction
	 */
	public void setActivationFunction(ActivationFunction activationFunc) {
		activationFunction = activationFunc;
	}
	
	/**
	 * Constructor de un objeto de Red Neuronal. Se crea con la estructura dada y lo va haciendo capa por capa.
	 * @param topology - Arreglo o serie de datos separados por comas que indican la estructura de la Red Neuronal. 
	 * Haciendo esto con el número de neuronas por cada capa.
	 */
	public Neural_Net(int ... topology) {
		network = new Neural_Layer[topology.length];
		topologyStruct = topology;
		activationFunction = Sigmoid.getInstance();
		for (int i = 0; i<topology.length-1;i++) {
			network[i] = new Neural_Layer(topology[i], topology[i+1]);
		}
		network[topology.length-1] = new Neural_Layer(topology[topology.length-1],0);
	}
	
	/**
	 * Obtención de los resultados de la red neuronal dados los inputs
	 * @param inputs - Matriz con los datos de entrada
	 * @return arreglo de matrices con los resultados de las sumas ponderadas en cada capa de la red neuronal
	 */
	public Matrix[] feedForward(Matrix inputs) {
		inputs = activationFunction.activation(inputs);
		Matrix z[] = new Matrix[network.length-1];
		Matrix a[] = new Matrix[network.length];
		
		a[0] = inputs;
		
		z[0]= network[0].getWeights().multiply(inputs).add(network[0].getBias());
		a[1]= activationFunction.activation(z[0]);
		
		for(int i=1;i<network.length-1; i++) {
			z[i]= network[i].getWeights().multiply(a[i]).add(network[i].getBias());
			a[i+1] = activationFunction.activation(z[i]);
			
		}
		
		a[network.length-1]=activationFunction.activation(z[network.length-2]);
		return a;
	}
	
	/**
	 * Obtención de la capa de resultados de la red neuronal a partir de los valores de entrada
	 * @param inputs - Matriz con los datos de entrada
	 * @return Matriz con los valores de salida del proceso de feed forward
	 */
	public Matrix obtainResult(Matrix inputs) {
		Matrix feedingForward[] = this.feedForward(inputs);
		return feedingForward[feedingForward.length-1];
		
	}
	
	/**
	 * Obtención del resultado de una red neuronal con un sólo valor de salida dados los valores de entrada.
	 * @param inputs - Matriz con los datos de entrada.
	 * @return Numero real con el valor de salida.
	 */
	public double obtainDoubleResult(Matrix inputs) {
		Matrix result = this.obtainResult(inputs);
		return result.getElement(0, 0);
	}
	
	
	

	private void backPropragation(Matrix result[], Matrix expectedResult, float learningRate) {
		Matrix deltas[] = new Matrix[network.length-1];
		Matrix gradient;
		Matrix gradientBias;
		
		/*
		  	if(i==network.length-2) {

				deltas[i]= result[i+1].costDerivative(expectedResult).dot(result[i+1].sigmoidDerived());

			} else {
				deltas[i] = network[i+1].getWeights().transpose().multiply(deltas[i+1]).dot(result[i+1].sigmoidDerived());
			}

		 */
			
		for(int i = network.length-2; i>=0; i--) {
			if(i==network.length-2) {	
				deltas[i]= result[i+1].costDerivative(expectedResult).dot(activationFunction.activationDerived(result[i+1]));
			} else {
				deltas[i] = network[i+1].getWeights().transpose().multiply(deltas[i+1]).dot(activationFunction.activationDerived(result[i+1]));
			}

			gradient = result[i].multiply(deltas[i].transpose());
			gradientBias = deltas[i].transpose();
			gradientBias.multiplyScalar(learningRate);
			gradient.multiplyScalar(learningRate);
			network[i].updateWeights(gradient.transpose());
			network[i].updateBias(gradientBias.transpose());
			
		}
	}
	
/**
 * Función de entrenamiento de la red neuronal. Se usa backpropragation que aplica el descenso de gradiente.
 * @param inputs - Arreglo de matrices de entrada con los que se entrena la RN
 * @param output - Arreglo de matrices con los valores de salida esperados
 * @param num - Número epoch de ejecuciones
 * @param umbral - Precisión a la que se quiere llegar, diferencia aceptable entre valor calculado y valor esperado
 */
	public void train(Matrix inputs[], Matrix output[], long num, float umbral) {
		int x = 0;
		int cycles = 0;

		
		while (x<inputs.length && cycles<num) {

			for(int i = 0; i<inputs.length; i++) {
				Matrix outMatr[] = this.feedForward(inputs[i]);
				
				Matrix out = outMatr[outMatr.length-1];
							
				Matrix difference = output[i].subtract(out);
				
				if(!difference.outputIsRight(umbral)) {
					x = 0;
					this.backPropragation(outMatr, output[i], 0.1f);			
				}else {
					x++;
				}
			}
			cycles++;	
		}
	}
	
	/**
	 * Función de entrenamiento de la red neuronal. Los valores de epoch y el umbral son de 5000 y 0.3 respectivamente.
	 * @param inputs - Arreglo de matrices de entrada con los que se entrena la RN
	 * @param output - Arreglo de matrices con los valores de salida esperados
	 * 
	 */
	public void train(Matrix inputs[], Matrix output[]) {
		this.train(inputs, output, 5000, 0.3f);
	}
	
	

	/**
	 * Conversión de un resultado esperado de la red neuronal a una matriz con la que la red neuronal pueda compparar 
	 * su resultado calculado
	 * @param out - Valor del resultado esperado
	 * @param len - Tamaño de la caa de salida de la RN
	 * @return Matriz del resultado esperado
	 */
	public static Matrix convertOutput(float out, int len) {
		double array[] = new double[len];
		for(int i=0; i<len;i++) {
			if(i!=out-1) {
				array[i] = 0.0f;
			} else {
				array[i] = 1.0f;
			}		
		}
		Matrix mat = Matrix.singleColumnMatrixFromArray(array);
		return mat;
	}
	
	public void printWeights() {
		for(int i=0; i<this.network.length; i++) {
			System.out.println(this.network[i].getWeights());
		}
	}
	

}


