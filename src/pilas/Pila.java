package pilas;

import excepciones.PilaVacia;
import nodo.Nodo;

public class Pila {
	private Nodo inicio;

	public Nodo getInicio() {
		return inicio;
	}

	public void setInicio(Nodo inicio) {
		this.inicio = inicio;
	}

	public Pila (){
		this.inicio = null;
	}

	public Boolean isVacia(){
		return inicio == null;
	}

	public void push (String d){
		Nodo nuevo = new Nodo (d);

		if (isVacia())
			inicio = nuevo;
		else{
			nuevo.setSiguiente (inicio);
			inicio = nuevo;
		}
	}

	public String pop () throws PilaVacia {
		String dato;
		Nodo aux = inicio;

		if (isVacia())
			throw new PilaVacia();
		else{
			dato = inicio.getDato();
			inicio = inicio.getSiguiente();
			aux.setSiguiente(null);
			aux = null;
		}

		return dato;	
	}

	public void mostrarPila (){
		Nodo aux = inicio;

		while (aux != null){
			System.out.println ("|  "+aux.getDato() +"   |" );
			aux = aux.getSiguiente();
		}
		System.out.println ("| "+aux +" |");
		System.out.println ("--------");
	}
}
