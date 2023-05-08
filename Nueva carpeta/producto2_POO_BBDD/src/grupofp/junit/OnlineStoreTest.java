/**
 *
 */
package grupofp.junit;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.time.Duration;

import org.junit.Test;

import grupofp.controlador.Controlador;
import grupofp.excepciones.ExcepcionesPersonalizadas.DAOException;
import grupofp.modelo.Datos;
import grupofp.vista.GestionOS;

/**
 * @author J-Programers
 *
 */
public class OnlineStoreTest {
/*
	Datos misDatos;
	Controlador miControlador;
	GestionOS miVistaGestionOS;


	@Test
	public void testAnadirArticulosMostrarArticulos() throws SQLException, DAOException {

		miVistaGestionOS = new GestionOS();
		misDatos = new Datos();
		miControlador = new Controlador(miVistaGestionOS, misDatos);
		miVistaGestionOS.setControlador(miControlador);
		misDatos.setControlador(miControlador);

		String codigo_articulo;
 	    String descripcion_articulo;
 	    float pvp_articulo;
 	    String tiempoPrep_articulo;
 	    float gastosEnvioArticulo;
 	    int num_articulos_registrados;

 	    codigo_articulo = "H001";
 	    descripcion_articulo = "Lampara de mesa";
 	    pvp_articulo = 20f;
 	    tiempoPrep_articulo = "PT0H5M0S";
 	    Duration duration = Duration.parse(tiempoPrep_articulo);
 	    gastosEnvioArticulo = 5f;

 	    this.miControlador.crearArticulo(codigo_articulo, descripcion_articulo, pvp_articulo, duration, gastosEnvioArticulo);

 	    codigo_articulo = "H002";
	    descripcion_articulo = "Lampara de mesa 2";
	    pvp_articulo = 25f;
	    tiempoPrep_articulo = "PT0H6M0S";
	    Duration duration_art2 = Duration.parse(tiempoPrep_articulo);
	    gastosEnvioArticulo = 5f;

 	    this.miControlador.crearArticulo(codigo_articulo, descripcion_articulo, pvp_articulo, duration_art2, gastosEnvioArticulo);

 	    num_articulos_registrados = this.miControlador.getListaArticulos().size();

 	    assertEquals(2, num_articulos_registrados);

	}

	@Test
	public void testClientesMostrarClientes() throws SQLException, DAOException {

		miVistaGestionOS = new GestionOS();
		misDatos = new Datos();
		miControlador = new Controlador(miVistaGestionOS, misDatos);
		miVistaGestionOS.setControlador(miControlador);
		misDatos.setControlador(miControlador);

		String email_cliente;
		String nombre_cliente;
		String domicilio_cliente;
		String nif_cliente;
		String tipo_cliente;
 	    int num_articulos_registrados;

 	    email_cliente = "mporto@gmail.com";
 	    nombre_cliente = "Mercedes Porto";
 	    domicilio_cliente = "As Cancelas 9, Santiago de Compostela";
 	    nif_cliente = "21456987N";
 	    tipo_cliente = "estandar";

 	    this.miControlador.crearCliente(email_cliente, nombre_cliente, domicilio_cliente, nif_cliente, tipo_cliente);

 	    email_cliente = "jiglesias@gmail.com";
	    nombre_cliente = "Julio Iglesias";
	    domicilio_cliente = "Miami Beach 3, Miami";
	    nif_cliente = "12456852N";
	    tipo_cliente = "premium";

 	    this.miControlador.crearCliente(email_cliente, nombre_cliente, domicilio_cliente, nif_cliente, tipo_cliente);

 	    num_articulos_registrados = this.miControlador.getListaArticulos().size();

 	    assertEquals(2, num_articulos_registrados);

	}
*/
}
