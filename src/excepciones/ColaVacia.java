package excepciones;

public class ColaVacia extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ColaVacia (){
		System.out.println ("Error, imposible eliminar la cola esta vacia");
	}

}
