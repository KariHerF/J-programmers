/**
 *
 */
package grupofp.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author J-Programers
 *
 */
public class Lista <T> implements Iterable<T> {
	protected ArrayList <T> lista = new ArrayList <>();

	public void add(T objeto){
	    lista.add(objeto);
	 }

	public ArrayList<T> getList(){
	    return lista;
	 }

	@Override
	public Iterator <T> iterator() {
		return lista.iterator();
	}

	public int size() {
		return lista.size();
	}

	public void remove(T objeto){
	    lista.remove(objeto);
	 }
	
	public void addAll(List<T> listaObjetos){
	    lista.addAll(listaObjetos);
	 }

}
