/**
 * 
 */
package grupofp.modelo;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author J-Programers
 *
 */
public class Lista <T> implements Iterable<T> {
	private ArrayList <T> listaClientes = new ArrayList <>();
	
	public Iterator <T> iterator() {
		return listaClientes.iterator();
	}

}
