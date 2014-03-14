package principal;

import calculadora.Calcu;
import excepciones.ColaVacia;
import excepciones.ErrorParentesis;
import excepciones.PilaVacia;

public class Principal {
	public static void main (String args[]) throws ColaVacia, PilaVacia, ErrorParentesis{

		Calcu cal = new Calcu ();
		String rsult = cal.calOpera("(1+2)*2");
		System.out.println("resultado: " + rsult);


	}
}
