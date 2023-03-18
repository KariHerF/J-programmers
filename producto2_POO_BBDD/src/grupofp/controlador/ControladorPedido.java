/**
 * 
 */
package grupofp.controlador;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;

import grupofp.modelo.Cliente;
import grupofp.modelo.LineaPedido;
import grupofp.modelo.Pedido;
import grupofp.vista.PedidoVista;

/**
 * @author josem
 *
 */
public class ControladorPedido {
	private PedidoVista vista;
	private Pedido modelo;

	/**
	 * @param vista
	 * @param modeloo
	 */
	public ControladorPedido(PedidoVista vista, Pedido modelo) {
		super();
		this.vista = vista;
		this.modelo = modelo;
	}

	// getters y setters para actuar sobre el modelo
	public void setNumPedido(int numPedido) {
		this.modelo.setNumPedido(numPedido);
		;
	}

	public int getCodigo() {
		return this.modelo.getNumPedido();
	}

	public void setCliente(Cliente cliente) {
		this.modelo.setCliente(cliente);
		;
	}

	public Cliente getCliente() {
		return this.modelo.getCliente();
	}

	public void setListaLineasPedido(ArrayList<LineaPedido> listaLineasPedido) {
		this.modelo.getListaLineasPedido();
		;
	}

	public ArrayList<LineaPedido> getListaLineasPedido() {
		return this.modelo.getListaLineasPedido();
	}

	public void setFechaHora(Date fechaHora) {
		this.modelo.setFechaHora(fechaHora);
	}

	public Date getFechaHora() {
		return this.modelo.getFechaHora();
	}

}
