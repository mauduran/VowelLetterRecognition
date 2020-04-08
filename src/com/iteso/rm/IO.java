package com.iteso.rm;

import java.io.File;
import java.util.Scanner;

/**
 * IO <br>
 * Esta clase funciona en situaciones donde no se tenga una topologia dicha por el usuario.
 * Esta clase se utilizo al tener archivos de texto en los cuales teniamos la topologia escrita en ellos.
 * @author Mauricio Duran Padilla, Edgar Francisco Medina 
 *
 */

public class IO {
	
	private Matrix[] inputs;
	private Matrix[] outputs;
	private int inputSize =0;
	private int dimension =0;
	private int outputSize=0;
	
	/**
	 * Método para obtener un arreglo de tipo Matrix de las entradas. 
	 * @return Arreglo de tipo Matrix.
	 * @see Matrix
	 */
	public Matrix[] getInputs() {
		return inputs;
	}
	
	/**
	 * Método para obtener un objeto de tipo Matrix el cual se escoge según el indice de entrada en el arreglo de Inputs.
	 * @param index - Variable tipo entera el cual se utiliza como indice para buscar la matrix deseada.
	 * @return Objeto tipo Matrix.
	 */
	public Matrix getInputFromIndex(int index) {
		return inputs[index];
	}
	
	
	/**
	 * Método para obtener un objeto de tipo Matrix el cual se escogen según el indice de entrada en el arreglo de Outputs.
	 * @param index - Variable tipo entera el cual se utiliza como indice para buscar la matrix deseada.
	 * @return Objeto tipo Matrix.
	 */
	public Matrix getOutputFromIndex(int index) {
		return outputs[index];
	}

	/**
	 * Método para inicializar el atributo inputs de la clase.
	 * @param inputs - Referencia de un arreglo de matrices para asignarlo al atributo del objeto invocador.
	 */

	public void setInputs(Matrix[] inputs) {
		this.inputs = inputs;
	}

	/**
	 * Método que regresa el arreglo de matrices del atributo Outputs.
	 * @return Un arreglo de objetos de tipo Matrix con la información de las salidas.
	 */

	public Matrix[] getOutputs() {
		return outputs;
	}

	/**
	 * Método que inicializa el atributo outputs del objeto.
	 * @param outputs - Referencia de un arreglo de matrices para asignarlo al atributo del objeto invocador.
	 */

	public void setOutputs(Matrix[] outputs) {
		this.outputs = outputs;
	}

	/**
	 * Método que regresa el tamaño de la cantidad de matrices que se leyeron.
	 * @return Regresa un valor tipo entero con la cantidad de matrices que se leyeron en el atributo inputSize.
	 */
	public int getInputSize() {
		return inputSize;
	}

	/**
	 * Método que inicializa el atributo inputSize del objeto invocador.
	 * @param inputSize -Valor con el cual se inicializa el atributo del mismo valor que el parámetro.
	 */
	public void setInputSize(int inputSize) {
		this.inputSize = inputSize;
	}

	/**
	 * Método que regresa la dimensión del objeto invocador.
	 * @return Regresa un valor tipo entero con la cantidad de matrices que se leyeron.
	 */
	public int getDimension() {
		return dimension;
	}
	
	/**
	 * Método que inicializa la dimensión del objeto invocador.
	 * @param dimension - Variable tipo entera que se refiere a la cantidad de las matrices que se leyeron.
	 */

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	/**
	 * Método que regresa el valor que se refiere a la dimensión de la matriz de outputs.
	 * @return	Regresa el valor de la dimensión de la matriz de outputs.
	 */

	public int getOutputSize() {
		return outputSize;
	}

	/**
	 * Método que inicializa el atributo OutputSize que se refiere a la dimensión de la matriz de Outputs.
	 * @param outputSize - Variable tipo entera que se refiere a la dimensión de la matriz de outputs.
	 */

	public void setOutputSize(int outputSize) {
		this.outputSize = outputSize;
	}

	/**
	 * Método constructor de objetos tipo IO.
	 * @param inputSize - Variable tipo entera que inicializa el atributo de su mismo nombre.
	 * @param dimension - Variable tipo entera que inicializa el atributo de su mismo nombre.
	 * @param outputSize - Variable tipo entera que inicializa el atributo de su mismo nombre.
	 */

	public IO(int inputSize, int dimension, int outputSize) {
		inputs = null;
		outputs = null;
		this.inputSize = inputSize;
		this.dimension = dimension;
		this.outputSize= outputSize;
	}

	/**
	 * Método que regresa un objeto tipo IO. Con solo recibir una ruta de un archivo de texto. Contiene administración de excepciones.
	 * @param file - Objeto tipo String que contiene la dirección del archivo de texto.
	 */
	
	public IO(String file) {
		File f = new File(file);
        Scanner s = null;

        int m=0;
        int n=0;
        int o = 0;
        int dim = 0;
        float value = 0;
        //m es la cantidad de datos de inputs
        //n es el tamaño del input
        //o es la cantidad de datos de output
        
        try {
            s = new Scanner(f);
            if(s.hasNextLine()) {    
                m = s.nextInt();
                n = s.nextInt();
                o = s.nextInt();
                dim = (int) Math.sqrt(n);
            }

            
            Matrix inputs[] = new Matrix[m];
            Matrix outputs[] = new Matrix[m];
            double results[] = new double[o];
            
            double [][] mat = new double[dim][dim];    //creates a matrix of dimension m
            for(int i=0; i< dim; i++){    //and initializes to 0
                for(int j=0; j< dim; j++){
                    mat[i][j] = 0;
                }            
            }
            
            for (int l = 0; l < m; l++) {
            	if(s.hasNextLine()) {
                    for(int r=0; r< dim; r++){
                        for(int c=0; c< dim; c++){
                            value = s.nextFloat(); 
                            
                            mat[r][c] =  value;  
                        }         
                        s.nextLine();
                    }
                }
            	inputs[l] = new Matrix(mat);
            	
            	inputs[l]= Matrix.fromMatrixToOneColumnMatrix(inputs[l]);
            	
            	for(int x =0; x<o; x++) {
            		results[x] = s.nextFloat();
            	}
            	
            	outputs[l] = Matrix.singleColumnMatrixFromArray(results);
  
			}
            
            this.inputs = inputs;
            this.outputs = outputs;
            this.inputSize = n;
            this.dimension = dim;
            this.outputSize = o;

        } catch (Exception ex) {
            System.out.println("Message catch: " + ex.getMessage());
        } 
	}
	
	
}
