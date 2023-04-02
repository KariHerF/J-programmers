/**
 * 
 */
package grupofp.vista;

import java.time.Duration;
import grupofp.modelo.Articulo;
import java.util.Date;
import grupofp.modelo.Cliente;
/**
 * @author J-Programers
 *
 */
public class GestionOS {


	public void printClienteDetalles(String nombre, String domicilio, String nif, String email, Class tipo, float calcAnual, float descuentoEnv) {
		System.out.println("**** DATOS CLIENTE ****");
		System.out.println("Id: " + nombre);
		System.out.println("Nombre: " + domicilio);
		System.out.println("NIF: " + nif);
		System.out.println("Email: " + email);
		System.out.println("tipo: " + tipo);
		System.out.println("cuota anual: " + calcAnual);
		System.out.println("descuento: " + descuentoEnv);
	}
	
	public void printArticuloDetalles(String codigo, String descripcion, Float pvp, Float gastosEnvio,
			Duration tiempoPrep) {
		System.out.println("**** DATOS ARTICULO ****");
		System.out.println("C�digo: " + codigo);
		System.out.println("Descripcion: " + descripcion);
		System.out.println("Pvp: " + pvp);
		System.out.println("Gastos de env�o: " + gastosEnvio);
		System.out.println("Tiempo de preparaci�n: " + tiempoPrep);

	}

	public void printPedidoDetalles(int numPedido, Cliente cliente, Articulo articulo, int cantUnidades, Date fechaHora, boolean enviado, float precioEnvio) {
		System.out.println("**** DATOS PEDIDO ****");
		System.out.println("Numero de pedido: " + numPedido);
		System.out.println("Cliente: " + cliente.toString());
		System.out.println("Articulo: " + articulo.toString());
		System.out.println("Unidades: " + cantUnidades);
		System.out.println("Fecha y hora: " + fechaHora);
		String msgEnviado = enviado ? "si" : "no";
		System.out.println("Enviado: " + msgEnviado);
		System.out.println("Precio envio: " + precioEnvio);

	}

}
