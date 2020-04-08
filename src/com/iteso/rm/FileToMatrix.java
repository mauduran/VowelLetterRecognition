package com.iteso.rm;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * FileToMatrix.java<br>
 * Esta clase se utiliza para obtener los "Id" de las letras (A = 1, B = 2, C = 3, D = 4, E = 5) y la información acerca de las
 * muestras del entrenamiento. Las cuales éstas ultimas están basados en la intensidad del color en la imagen. 
 * @author Mauricio Duran Padilla, Edgar Francisco Medina Rifas
 */
public class FileToMatrix {
	
	/**
	 * Este método arroja un objeto tipo LetterRecognition, en el cual inicializamos sus atributos con arreglos de matrices según si
	 * son para los ID de las clases de las letras o los valores de intensidad de color de las muestras de las letras. Contiene manejo de excepciones.	 * 
	 * @param path - Variable de tipo String para obtener la dirección del archivo a convertir en matriz.
	 * @return - Objeto tipo Neural_Net
	 * @throws IOException - Cuando no puede leerse el archivo de la ruta esecificada
	 * @see LetterRecognition
	 */
	
	public static LetterRecognition fileToMatrix (String path) throws IOException {

		BufferedReader br = null;
        String line = "";
        ArrayList<Matrix> matrixList = new ArrayList<>();
		ArrayList<Matrix> resultList = new ArrayList<>();
		
		double [] arrayTemp = new double [784];
		boolean isValid;
		int x=0;
		
		LetterRecognition reconL = new LetterRecognition();

        try {
        	System.gc();
            br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                String[] datos= line.split(",");
                
                isValid = Integer.parseInt(datos[0])<6;
                
                if(isValid) {
                	resultList.add(Neural_Net.convertOutput(Integer.parseInt(datos[0]), 5));
                	
            		for(int i=1; i<datos.length;i++) {
            			arrayTemp[i-1] = Integer.parseInt(datos[i]);
            		}
            		
            		matrixList.add(Matrix.singleColumnMatrixFromArray(arrayTemp));
                }
            }
            Matrix tmp[] = new Matrix[matrixList.size()];
            
            for(int i=0; i<tmp.length;i++) {
            	tmp[i] = matrixList.get(i);
            }
            
            reconL.setInputs(tmp);
            
            Matrix tmp2[] = new Matrix[resultList.size()];
            
            for(int i=0; i<tmp.length;i++) {
            	tmp2[i] = resultList.get(i);
            }
           
            reconL.setIdLetter(tmp2);
            
            br.close();
            return new LetterRecognition(tmp,tmp2);

			
			
			
        } catch(Exception ex) {
        	
        }
        return reconL;
        
	}
}
