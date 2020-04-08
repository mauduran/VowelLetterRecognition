package com.iteso.rm;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
/**
 * ImageToMatrix <br>
 * Esta clase funciona para convertir una imagen a matriz. Contiene un método el cual hace la función especifica de la clase.
 * @author Mauricio Duran Padilla, Edgar Francisco Medina Rifas
 * 
 */
public class ImagetoMatrix {
	/**
	 * Método static que regresa un objeto de tipo Matrix con la información de la intensidad de los colores del dibujo de una letra que 
	 * posteriormente será ingresada en el método Main para la prueba de funcionamiento de la red neuronal.
	 * @param ImageName - Objeto tipo String que contiene la dirección de la imagen a convertir en matriz.
	 * @return - Objeto tipo Matrix.
	 * @throws IOException - Cuando no se encuentra el archivo a leer 
	 * @see Matrix
	 */
	public static Matrix imageToMatrix (String ImageName) throws IOException {
		 // Abriendo la imagen
		 File imgPath = new File(ImageName);
		 BufferedImage bi = ImageIO.read(imgPath);
		 double matrix [][] = new double [bi.getHeight()][bi.getWidth()];
		 // Obteniendo DataBufferBytes desde Raster
		 for(int i = 0; i < bi.getHeight(); i++) {
			 for(int j = 0; j <  bi.getWidth(); j++) {
				matrix[i][j] =  bi.getRGB(i, j)>>16 & 0x000000FF;
			 }
		 }
		 
		 return ( Matrix.fromMatrixToOneColumnMatrix(new Matrix(matrix)) );
		}
}
	
	