package calculadora;

import colas.Cola;
import excepciones.ColaVacia;
import excepciones.ErrorParentesis;
import excepciones.PilaVacia;
import pilas.Pila;

public class Calcu {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Cola infija;
	Pila posfija;
	String cadena = "";
	String mostrar = "";
	String ans = "";

	//10-03-12 v1.0
	/*ines: checar los  operadores()
	 * mike resolverlo() 
	 */


	public String calOpera (String s) throws ColaVacia, PilaVacia, ErrorParentesis{
		infija = new Cola();
		posfija = new Pila ();

		if (balanceParentesis(s) != true)
			throw new ErrorParentesis();
		else{
			String sub = new String ();;

			for (int i = 0; i < s.length(); i ++ ){
				if ( ( (i == 0) && (s.charAt(0) == '-') ) || ( (s.charAt(i) == '(') && (s.charAt(i+1) == '-') ) )
					infija.push("0");
				if (s.charAt(i) >= '0' && s.charAt(i) <= '9' || s.charAt(i) == '.')
					sub = sub +s.charAt(i);
				else{
					if (sub.length()!= 0)
						infija.push(sub);
					sub = new String ();

					switch (s.charAt(i)){
					case '+' :
						infija.push("+"); break;
					case '(' :
						infija.push("("); break;
					case ')' :
						infija.push(")"); break;
					case '-' :
						infija.push("-"); break;
					case '*' :
						infija.push("*"); break;
					case '/' :
						infija.push("/"); break;
					case 's' :
						infija.push("s"); break;
					case 'c' :
						infija.push("c"); break;
					case 't' :
						infija.push("t"); break;
					case 'r' :
						infija.push("r"); break;
					case '^' :
						infija.push("^"); break;
					case '@' :
						infija.push("@"); break;
					case 'x':
						infija.push("*"); infija.push(String.valueOf(1)); break;
					case 'X':
						infija.push(String.valueOf(1));

					}
				}

			}

			if (sub.length()!= 0)
				infija.push(sub);
			infija.mostrarCola();
			this.posFija();
			return this.resuelve();
		}
	}

	private String resuelve () throws ColaVacia, PilaVacia{
		Pila pos = new Pila ();
		Pila resul = new Pila ();
		Double numeroA;
		Double numeroB;
		Double exp;

		while (!posfija.isVacia())
			pos.push(posfija.pop());

		String dato;
		while (!pos.isVacia()){
			dato = pos.pop();
			System.out.println(dato);
			if ( (dato.compareTo("+") != 0 ) && (dato.compareTo("-") != 0 ) && (dato.compareTo("*") != 0 ) && (dato.compareTo("/") != 0 ) && (dato.compareTo("s") != 0 ) &&(dato.compareTo("c") != 0 ) &&(dato.compareTo("t") != 0 ) && (dato.compareTo("r") != 0 ) && (dato.compareTo("^") != 0 ) && (dato.compareTo("(") != 0 ) && (dato.compareTo(")") != 0 ) && (dato.compareTo("@") != 0 ))
				resul.push(dato);
			else if ( (dato.compareTo("s") == 0 ) || ( dato.compareTo("c") == 0 ) || (dato.compareTo("t") == 0 ) || ( dato.compareTo("r") == 0 ) || (dato.compareTo("@") == 0)){
				numeroA = Double.parseDouble(resul.pop());
				switch(dato){
				case "s": exp = (numeroA * Math.PI)/180;
				resul.push(String.valueOf(Math.sin(exp)));
				break;
				case "c": exp = (numeroA * Math.PI)/180;
				resul.push(String.valueOf(Math.cos(exp)));
				break;
				case "t": exp = (numeroA * Math.PI)/180;
				resul.push(String.valueOf(Math.tan(exp)));
				break;
				case "r": resul.push(String.valueOf(Math.sqrt(numeroA)));
				break;
				case "@": resul.push(String.valueOf(0-numeroA));
				break;

				}
			}
			else{
				numeroB = Double.parseDouble(resul.pop());
				numeroA = Double.parseDouble(resul.pop());

				switch(dato){
				case "+": resul.push(String.valueOf(numeroA + numeroB));
				break;
				case "-": resul.push(String.valueOf(numeroA - numeroB));
				break;
				case "*": resul.push(String.valueOf(numeroA * numeroB));
				break;
				case "/": resul.push(String.valueOf(numeroA / numeroB));
				break;
				case "^": resul.push(String.valueOf(Math.pow(numeroA,numeroB)));
				break;
				}
			}

		} 
		//System.out.println("Resultado ===>" + resul.pop());
		//no ines, no es asi, que te pasa?
		//te envio mis avances para que veas como va quedando
		return resul.pop();

		/* ya imprime los ejercicios le hice y tambien los que vimos en la clase. :P
		 * 
		 */
	}

	private void posFija () throws ColaVacia, PilaVacia{
		Pila aux = new Pila ();
		String dato = new String ();
		String dato1 = new String ();

		while (! infija.isVacia()){
			dato = infija.pop();
			if ( (dato.compareTo("+") != 0 ) && (dato.compareTo("-") != 0 ) && (dato.compareTo("*") != 0 ) && (dato.compareTo("/") != 0 ) && (dato.compareTo("s") != 0 ) &&(dato.compareTo("c") != 0 ) &&(dato.compareTo("t") != 0 ) && (dato.compareTo("r") != 0 ) && (dato.compareTo("^") != 0 ) && (dato.compareTo("(") != 0 ) && (dato.compareTo(")") != 0 ) )
				posfija.push(dato);
			else if (dato.compareTo("(") == 0)
				aux.push(dato);
			else if (dato.compareTo(")") == 0)
			{
				do{
					dato1 = aux.pop();
					if (dato1.compareTo("(") != 0)
						posfija.push(dato1);
				}while (dato1.compareTo("(") != 0);
			}
			else if ( aux.isVacia() || ( this.prioridad(dato) > this.prioridad(aux.getInicio().getDato() ) ) )
				aux.push(dato);	
			else{
				while (!aux.isVacia() && (this.prioridad(dato) <= this.prioridad(aux.getInicio().getDato()) ) )
					posfija.push(aux.pop());
				aux.push(dato);
			}
		}

		while (!aux.isVacia())
			posfija.push(aux.pop());
	}



	private int prioridad (String op){
		if (op.compareTo("(") == 0 )
			return 0;
		else if ( op.compareTo ("+") == 0 || op.compareTo ("-") == 0 || op.compareTo("@") == 0)
			return 1;
		else if ( op.compareTo ("*") == 0 || op.compareTo("/") == 0 ) 
			return 2;
		else if ( op.compareTo ("^") == 0 || op.compareTo("r") == 0 )
			return 3;
		else if ( op.compareTo("s") == 0 || op.compareTo("c") == 0 || op.compareTo("t") == 0 )
			return 4;
		else if ( op.compareTo(")") == 0 )
			return 5;
		else
			return -1;
	}

	public Boolean balanceParentesis (String cadena) throws PilaVacia{
		Pila p = new Pila();
		for (int i = 0; i < cadena.length(); i++)
			if (cadena.charAt(i) == '(' )
				p.push( ")" );
			else if (!p.isVacia() && cadena.charAt(i) == ')')
				p.pop();
			else if (cadena.charAt(i) == ')' && p.isVacia() )
				return false;
		return p.isVacia();
	}

}

