package com.iteso.rm;
import java.io.IOException;

public class testing2 {
	public static void main(String[] args) {
		Neural_Net n1;
		try {
			n1 = Writer.readFromFile("letras.txt");
			Matrix b = ImagetoMatrix.imageToMatrix("/Users/Duran/Proyecto/Neural Net/src/com/iteso/rm/dmayus2.jpg");
			
			System.out.println(LetterRecognition.interpretLetter(b, n1));
			
			//n1.printWeights();

		} catch(IOException ex){
			System.out.println("No se pudo escribir el archivo");
		}
		
	}
}

