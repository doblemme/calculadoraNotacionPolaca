package colas;

import excepciones.ColaVacia;
import nodo.NodoC;

public class Cola {
	NodoC inicio;
	NodoC fin;

	public Cola (){
		inicio = null;
		fin = null;
	}

	public Boolean isVacia (){
		return inicio == null;
	}

	public void push (String dat){
		NodoC nuevo = new NodoC(dat);

		if (isVacia ()){
			inicio = nuevo;
			fin = nuevo;
		}
		else{
			nuevo.setAnterior(fin);
			fin.setSiguiente(nuevo);
			fin = nuevo;
		}
	}

	public String pop () throws ColaVacia{
		NodoC aux = this.inicio;
		if (!isVacia()){
			String resul = new String (aux.getDato());
			inicio = inicio.getSiguiente();
			if (inicio != null)
				inicio.setAnterior(null);
			aux.setSiguiente(null);
			aux = null;
			return resul;
		}
		else
			throw new ColaVacia (); 
	}

	public void mostrarCola (){
		NodoC aux = this.inicio;
		System.out.print ("null");
		while (aux != null){
			System.out.print ("<"+aux.getDato());
			aux = aux.getSiguiente();
		}
		System.out.println();
	}
}
