package com.iteso.rm;

/**
 * Letters <br>
 * Esta enumeración funciona para utilizarla en la clase LetterRecognition para mandar a impresión la letra adecuada.
 * @author Mauricio Duran Padilla, Edgar Francisco Medina Rifas
 *
 */
public enum Letters {
	A(0),
	B(1),
	C(2),
	D(3),
	E(4);
	
	private int pos;
	/**
	 * Método constructor de la enumeración Letters.
	 * @param position - Variable que sirve para inicializar el atributo de la enumeración.
	 */
	Letters(int position) {
		this.pos = position;
	}
	
	/**
	 * Método que regresa una String según el valor que se introduzca como parametro.
	 * @param x - Variable entera para switch.
	 * @return Regresa una String según el valor de x, si no está el valor de entrada, regresa un "NaN".
	 */
	
	public static String getValue(int x) {
		switch(x) {
		case 0:
			return "a";
			
		case 1:
			return "b";
			
		case 2:
			return "c";
			
		case 3:
			return "d";
			
		case 4:
			return "e";
		
		default: 
			return "NaN";
				
	}
	}
	
	/**
	 * Metodo String para la enumeración Letters.
	 */
	@Override
	public String toString(){
		switch(this.pos) {
			case 0:
				return "a";
				
			case 1:
				return "b";
				
			case 2:
				return "c";
				
			case 3:
				return "d";
				
			case 4:
				return "e";
			
			default: 
				return "NaN";
					
		}
	}
}
