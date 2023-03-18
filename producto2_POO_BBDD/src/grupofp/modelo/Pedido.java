package grupofp.modelo;

import java.util.ArrayList;
import java.util.Date;

public class Pedido {

	private int numPedido;
	private Cliente cliente;
	private Articulo articulo;
	private Date fechaHora;
	private int cantUnidades;
	private boolean enviado = false;


	/**
	 * @param numPedido
	 * @param cliente
	 * @param articulo
	 * @param fechaHora
	 * @param cantUnidades
	 * @param enviado
	 */
	public Pedido(int numPedido, Cliente cliente, Articulo articulo, Date fechaHora, int cantUnidades,
			boolean enviado) {
		this.numPedido = numPedido;
		this.cliente = cliente;
		this.articulo = articulo;
		this.fechaHora = fechaHora;
		this.cantUnidades = cantUnidades;
		this.enviado = enviado;
	}



	/**
	 * @return the numPedido
	 */
	public int getNumPedido() {
		return numPedido;
	}



	/**
	 * @param numPedido the numPedido to set
	 */
	public void setNumPedido(int numPedido) {
		this.numPedido = numPedido;
	}



	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}



	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



	/**
	 * @return the fechaHora
	 */
	public Date getFechaHora() {
		return fechaHora;
	}



	/**
	 * @param fechaHora the fechaHora to set
	 */
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}



	/**
	 * @return the cantUnidades
	 */
	public int getCantUnidades() {
		return cantUnidades;
	}



	/**
	 * @param cantUnidades the cantUnidades to set
	 */
	public void setCantUnidades(int cantUnidades) {
		this.cantUnidades = cantUnidades;
	}



	/**
	 * @return the enviado
	 */
	public boolean pedidoEnviado() {
		return enviado;
	}



	/**
	 * @param enviado the enviado to set
	 */
	public void setEnviado(boolean enviado) {
		this.enviado = enviado;
	}



	/**
	 * @return the articulo
	 */
	public Articulo getArticulo() {
		return articulo;
	}


	/**
	 * @param articulo the articulo to set
	 */
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}


	public float precioEnvio() {
		if (this.cliente.tipoCliente() == "Premium") {
			return this.articulo.getGastosEnvio() * this.cantUnidades * (1- this.cliente.descuentoEnv());
		}
		return this.articulo.getGastosEnvio()*this.cantUnidades;
	}



	@Override
	public String toString() {
		return "Pedido [Número pedido =" + getNumPedido() + ", Fecha y hora =" + getFechaHora() +
				", NIF Cliente=" + getCliente().getNif() + ", Nombre Cliente=" + getCliente().getNombre() +
			    ", Código Artículo=" + getArticulo().getCodigo() + ", Descripción artículo=" + getArticulo().getDescripcion() +
				", Nº unidades =" + getCantUnidades() + ", Precio Artículo=" + getArticulo().getPvp() +
				", Coste envío =" + precioEnvio() + ", Coste total =" + getArticulo().getPvp() * this.cantUnidades +
				", ¿Ha sido enviado?=" + pedidoEnviado() + "]";
	}

	

}
