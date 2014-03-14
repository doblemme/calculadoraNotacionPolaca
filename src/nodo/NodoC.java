package nodo;

import nodo.NodoC;

public class NodoC {
	private String dato;
	private NodoC siguiente;
	private NodoC anterior;

	public NodoC (){

	}	

	public NodoC (String d){
		this.dato = d;
		this.siguiente = null;
		this.anterior = null;
	}

	public String getDato() {
		return dato;
	}

	public void setDato(String dato) {
		this.dato = dato;
	}

	public NodoC getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(NodoC siguiente) {
		this.siguiente = siguiente;
	}

	public NodoC getAnterior() {
		return anterior;
	}

	public void setAnterior(NodoC anterior) {
		this.anterior = anterior;
	}

}
