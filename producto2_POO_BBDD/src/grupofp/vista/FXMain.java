/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package grupofp.vista;

import grupofp.controlador.Controlador;
import grupofp.controlador.Main;
import grupofp.modelo.Datos;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author joanb
 */
public class FXMain extends Application {

    Datos misDatos = new Datos();
    GestionOS miVistaGestionOS = new GestionOS();
    Controlador miControlador = new Controlador(miVistaGestionOS, misDatos);

    private void iniciarApp() {
        /* Se instancian las clases del MVC */
         miVistaGestionOS.setControlador(miControlador);
        misDatos.setControlador(miControlador);
        
    }

    @Override
    public void start(Stage mainMenu) {
        Text titulo = new Text(30, 30, "¿Que deseas hacer?");
        titulo.setStyle("-fx-font: 16 arial;");

        Button gestionarArticulos = new Button();
        gestionarArticulos.setText("Gestionar articulos");
        gestionarArticulos.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                FXArticulo fxArt = new FXArticulo();

                mainMenu.hide();
                fxArt.start(mainMenu);
            }
        });

        Button gestionarClientes = new Button();
        gestionarClientes.setText("Gestionar clientes");
        gestionarClientes.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                FXCliente fxCliente = new FXCliente();

                mainMenu.hide();
                fxCliente.start(mainMenu);
            }
        });

        Button gestionarPedidos = new Button();
        gestionarPedidos.setText("Gestionar pedidos");
        gestionarPedidos.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                FXPedido fxPedido = new FXPedido();

                mainMenu.hide();
                fxPedido.start(mainMenu);
            }
        });

        Button salir = new Button();
        salir.setText("Salir");
        salir.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(titulo, gestionarArticulos, gestionarClientes, gestionarPedidos, salir);
        vbox.setAlignment(Pos.CENTER);

        StackPane root = new StackPane();
        root.getChildren().add(vbox);

        Scene scene = new Scene(root, 600, 500);

        mainMenu.setTitle("Producto 5");
        mainMenu.setScene(scene);
        mainMenu.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FXMain miPrincipal = new FXMain();
        miPrincipal.iniciarApp();
        miPrincipal.launch(args);

    }

    public void mostrarAlerta(String tipo, String message) {
        if (tipo == "error") {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Ha ocurrido un error");
            alert.setContentText(message);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setHeaderText(message);
            alert.showAndWait();
        }

    }
    
}
