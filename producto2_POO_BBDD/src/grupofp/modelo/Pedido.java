package grupofp.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import grupofp.controlador.ConexionDB;

/**
 * @author J-Programers
 *
 */
public class Pedido {

	private int numPedido;
	private Cliente cliente;
	private Articulo articulo;
	private LocalDateTime fechaHora;
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
	public Pedido(int numPedido, Cliente cliente, Articulo articulo, LocalDateTime fechaHora, int cantUnidades) {
		this.numPedido = numPedido;
		this.cliente = cliente;
		this.articulo = articulo;
		this.fechaHora = fechaHora;
		this.cantUnidades = cantUnidades;
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
	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	/**
	 * @param fechaHora the fechaHora to set
	 */
	public void setFechaHora(LocalDateTime fechaHora) {
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
		LocalDateTime fechaHora_pedido;
		LocalDateTime fechaHora_actual = LocalDateTime.now();
		LocalDateTime fechaHora_pedido_con_tiempo_prep_articulo_sumado;
		Duration duracion_prep_articulo_de_pedido;

		fechaHora_pedido = this.getFechaHora();
		duracion_prep_articulo_de_pedido = this.getArticulo().getTiempoPrep();

		fechaHora_pedido_con_tiempo_prep_articulo_sumado = fechaHora_pedido.plus(duracion_prep_articulo_de_pedido);

		// Comparar los dos objetos LocalDateTime
		int resultado_com_fechas = fechaHora_actual.compareTo(fechaHora_pedido_con_tiempo_prep_articulo_sumado);

		if (resultado_com_fechas < 0) {
			enviado = false;
		} else {
			enviado = true;
		}
		return enviado;
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
			return this.articulo.getGastosEnvio() * this.cantUnidades * (1 - this.cliente.descuentoEnv());
		}
		return this.articulo.getGastosEnvio() * this.cantUnidades;
	}

	public void insertarPedido(Pedido pedido) throws SQLException {
		String query = "INSERT INTO pedidos (numPedido, cliente_nif, articulo_codigo, fechaHora, cantUnidades, enviado) "
				+
				"VALUES (?, ?, ?, ?, ?, ?)";

		Connection connection = ConexionDB.conectar();
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, pedido.getNumPedido());
		statement.setString(2, pedido.getCliente().getNif());
		statement.setString(3, pedido.getArticulo().getCodigo());
		Date fecha = Date.from(pedido.getFechaHora().toInstant(null));
		java.sql.Date fechaSql = new java.sql.Date(fecha.getTime());
		statement.setDate(4, fechaSql);
		statement.setInt(5, pedido.getCantUnidades());
		int enviado = 0;
		if (pedido.pedidoEnviado()) {
			enviado = 1;
		}
		statement.setInt(6, enviado);
		statement.executeUpdate();
		statement.close();
	}

	@Override
	public String toString() {
		return "Pedido [N�mero pedido =" + getNumPedido() + ", Fecha y hora =" + getFechaHora() +
				", NIF Cliente=" + getCliente().getNif() + ", Nombre Cliente=" + getCliente().getNombre() +
				", C�digo Art�culo=" + getArticulo().getCodigo() + ", Descripci�n art�culo="
				+ getArticulo().getDescripcion() +
				", N�mero de unidades =" + getCantUnidades() + ", Precio art�culo=" + getArticulo().getPvp() +
				", Coste env�o =" + precioEnvio() / this.cantUnidades + ", Coste total ="
				+ getArticulo().getPvp() * this.cantUnidades +
				", �Ha sido enviado?=" + pedidoEnviado() + "]";
	}

}
