package com.iteso.rm;

import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;



public class ReconocimientoDeLetras {

	public static void main(String[] args) throws IOException {
		LetterRecognition trains = FileToMatrix.fileToMatrix("/Users/Duran/Proyecto/Redes Neuronales/src/com/iteso/rm/emnist-letters-train.csv");
		Matrix prb[] = trains.getInputs();
		int size[] = {784,16,5};
		Neural_Net n1 = new Neural_Net(size);
		n1.train(trains.getInputs(), trains.getIdLetter());
		Matrix b = ImagetoMatrix.imageToMatrix("/Users/Duran/Proyecto/Redes Neuronales/src/com/iteso/rm/e.png");
		System.out.println(LetterRecognition.interpretLetter(b, n1));

		try {
			Writer.WritetoFile(n1, "letras.txt");
			System.out.println("guardado exitoso");
		} catch(IOException ex){
			System.out.println("No se pudo escribir el archivo");
		}
	}
}
	


