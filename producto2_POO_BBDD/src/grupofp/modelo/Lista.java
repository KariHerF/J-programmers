/**
 * 
 */
package grupofp.modelo;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author josem
 *
 */
public class Lista <T> implements Iterable<T> {
	private ArrayList <T> listaClientes = new ArrayList <>();
	
	public Iterator <T> iterator() {
		return listaClientes.iterator();
	}

}
