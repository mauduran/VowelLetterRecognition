package com.iteso.rm;

import java.io.IOException;
import java.util.ArrayList;

public class testing {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub		
		
		IO prb = new IO("/Users/Duran/Proyecto/Redes Neuronales/src/com/iteso/rm/trainingSet1.txt");
		

		int size[] = {prb.getInputSize(),7,4,prb.getOutputSize()};
		
		Neural_Net n1 = new Neural_Net(size);
		
		n1.train(prb.getInputs(), prb.getOutputs());

		
		IO test = new IO("/Users/Duran/Proyecto/Redes Neuronales/src/com/iteso/rm/testingSet1.txt");
		
		//float x = n1.obtainFloatResult(test.inputs[0]);
		for (int i = 0; i < 20; i++) {
			double x = n1.obtainDoubleResult(test.getInputFromIndex(i));
			if(x<0.5f) {
				System.out.println("O");
			} else {
				System.out.println("X");
			}
			
		}
		
		
		try {
			Writer.WritetoFile(n1, "prueba.txt");
			//Writer.readFromFile(n1, "prueba.txt");
		} catch(IOException ex){
			System.out.println("No se pudo escribir el archivo");
		}
		
		
	}

}

