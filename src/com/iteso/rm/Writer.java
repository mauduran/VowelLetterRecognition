package com.iteso.rm;

import java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * Writer.java<br><br>
 * Esta clase se utiliza para leer o escribir los pesos ya "entrenados" de una red neuronal para poder hacer uso 
 * de los mismos sin necesidad de volver a hacer el training de la red neuronal.
 * @author Mauricio Durán Padilla, Edgar Francisco Medina Rifas
 * @version 1.0
 *
 */

public class Writer {
	
	/**
	 * Método static para escribir los pesos de una red neuronal ya entrenada en un archivo.
	 * 
	 * @param nn - Objeto de tipo red neuronal
	 * @param name - Nombre del archivo a crear
	 * @throws IOException Cuando se designa un nombre de archivo no válido para escribir.
	 */
	public static void WritetoFile(Neural_Net nn, String name) throws IOException{
		FileOutputStream fout = new FileOutputStream(name);
		DataOutputStream dout = new DataOutputStream(fout);
		
		double temp[][];
		
		
		dout.writeInt(nn.topologyStruct.length);
		for (int i = 0; i < nn.topologyStruct.length; i++) {
			dout.writeInt(nn.topologyStruct[i]);
		}
		
		for (int i = 0; i < nn.topologyStruct.length; i++) {
			temp = nn.network[i].getWeights().to2dArray();


			for(int j = 0; j<temp.length; j++) {
				for(int k = 0; k<temp[j].length; k++) {
					dout.writeDouble(temp[j][k]);
				}
			}
		}
			
		for (int i = 0; i < nn.topologyStruct.length-1; i++) {
			temp = nn.network[i].getBias().to2dArray();
			//System.out.println(nn.network[i].getBias());
			for(int j = 0; j<temp.length; j++) {
				for(int k = 0; k<temp[j].length; k++) {
					dout.writeDouble(temp[j][k]);
					//System.out.printf("%f ",temp[j][k] );
				}
				//System.out.println();
			}
			//System.out.println();
		}

		dout.close();
	}
	
	/**
	 * Metodo static que lee un archivo dada su ruta para la creación de un red neuronal.
	 * @param name ruta del archivo a leer
	 * @return Una red neuronal con pesos asignados
	 * @throws IOException Cuando la ruta del archivo es incorrecta o el atchivo inexistente
	 */
	public static Neural_Net readFromFile(String name) throws IOException{
		Neural_Net nn;
		FileInputStream fin = new FileInputStream(name);
		DataInputStream din = new DataInputStream(fin);
		
		int x = din.readInt();
		
		int topology[] = new int[x];
		
		for(int i=0; i<x; i++) {
			topology[i]= din.readInt();
		}
		
		nn = new Neural_Net(topology);
		double temp[][];
		
		for(int i=0; i<topology.length-1; i++) {
			temp = new double[topology[i+1]][topology[i]];
			for(int j = 0; j< temp.length; j++) {
				for(int k = 0; k< temp[j].length; k++) {
					temp[j][k] = din.readDouble();
				}
			}
			
			nn.network[i].setWeights(temp);
		}
		
		for(int i=0; i<topology.length-1; i++) {
			temp = new double[topology[i+1]][1];
			for(int j = 0; j< temp.length; j++) {
				for(int k = 0; k< temp[j].length; k++) {
					temp[j][k] = din.readDouble();
					//System.out.println(temp[j][k]);
					
				}
			}
		
			nn.network[i].setBias(temp);
		}
		din.close();
		
		return nn;
	}
}





