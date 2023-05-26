/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package grupofp.vista;

import java.time.Duration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author joanb
 */
public class FXCliente extends FXMain {
  Stage clienteStage = new Stage();
  
   private void addCliente(String email, String nombre, String direccion, String nif,
            String tipoCliente, Stage stage) {
        try {
            String vacio = "";
            if (vacio.matches(email) || vacio.matches(nombre)
                    || vacio.matches(direccion) || vacio.matches(nif) || vacio.matches(tipoCliente)) {
                mostrarAlerta("error", "Debes llenar todos los campos");
            } else {
                this.miControlador.getDatos().validarEmail(email);
                this.miControlador.getDatos().validarDNIoNIE(nif);
                this.miControlador.getDatos().validarTipoCliente(tipoCliente);

                this.miControlador.crearCliente(email, nombre, direccion, nif,
			tipoCliente);
                stage.close();
                mostrarAlerta("success", "Se ha creado el cliente correctamente");
                clienteStage.show();
            }

        } catch (Exception ex) {
            System.out.println(ex);
            mostrarAlerta("error", ex.getMessage());
        }
    }



    public void start(Stage mainMenu) {

        Text titulo = new Text(30, 30, "¿Que deseas hacer?");
        titulo.setStyle("-fx-font: 15 arial;");
        
        Button addCliente = new Button();
        addCliente.setText("Añadir cliente");
        addCliente.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                pantallaAddCliente();
            }
        });
        
        Button showClientes = new Button();
        showClientes.setText("Mostrar clientes");
        showClientes.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                pantallaShowClientes();
            }
        });
        
        
        Button volver = new Button();
        volver.setText("Volver");
        volver.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                clienteStage.close();
                mainMenu.show();
            }
        });
        
        StackPane root = new StackPane();
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(titulo, addCliente, showClientes, volver);
        vbox.setAlignment(Pos.CENTER);
        root.getChildren().add(vbox);
        
        Scene scene = new Scene(root, 300, 250);
        
        clienteStage.setTitle("Clientes");
        clienteStage.setScene(scene);
        clienteStage.show();
    }
    
    private void pantallaAddCliente() {
        Stage addClienteStage = new Stage();
        StackPane root = new StackPane();
        Text titulo = new Text(30, 30, "Añadir cliente");
        titulo.setStyle("-fx-font: 15 arial;");
        
        TextField emailCliente = new TextField();
        emailCliente.setPromptText("Introduce el email");
        Label labelEmail = new Label("Email:");
        HBox boxEmail = new HBox(10);
        boxEmail.getChildren().addAll(labelEmail, emailCliente);
        boxEmail.setAlignment(Pos.BASELINE_LEFT);

        TextField nombreCliente = new TextField();
        nombreCliente.setPromptText("Introduce el nombre");
        Label labelNombre = new Label("Nombre:");
        HBox boxNombre = new HBox(10);
        boxNombre.getChildren().addAll(labelNombre, nombreCliente);
        boxNombre.setAlignment(Pos.BASELINE_LEFT);

        TextField domicilioCliente = new TextField();
        domicilioCliente.setPromptText("Domicilio");
        Label labelDomicilio = new Label("Direccion:");
        HBox boxDomicilio = new HBox(10);
        boxDomicilio.getChildren().addAll(labelDomicilio, domicilioCliente);
        boxDomicilio.setAlignment(Pos.BASELINE_LEFT);

        TextField nifCliente = new TextField();
        nifCliente.setPromptText("Ex: 12345678A");
        Label labelNifCliente = new Label("NIF:");
        HBox boxNifCliente = new HBox(10);
        boxNifCliente.getChildren().addAll(labelNifCliente, nifCliente);
        boxNifCliente.setAlignment(Pos.BASELINE_LEFT);

        TextField tipoCliente = new TextField();
        tipoCliente.setPromptText("premium / estandar");
        Label labelTipoCliente = new Label("Tipo de cliente:");
        HBox boxTipoCliente = new HBox(10);
        boxTipoCliente.getChildren().addAll(labelTipoCliente, tipoCliente);
        boxTipoCliente.setAlignment(Pos.BASELINE_LEFT);

        Button add = new Button();
        add.setText("Añadir");
        add.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                addCliente(emailCliente.getText(), nombreCliente.getText(),
                        domicilioCliente.getText(), nifCliente.getText(), tipoCliente.getText(),
                        addClienteStage);

            }
        });

        Button volver = new Button();
        volver.setText("Volver");
        volver.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                addClienteStage.close();
                clienteStage.show();
            }
        });

        HBox boxBotones = new HBox(20);
        boxBotones.getChildren().addAll(add, volver);
        boxBotones.setAlignment(Pos.CENTER);
        boxBotones.setPadding(new Insets(20, 20, 0, 0));

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(titulo, boxEmail, boxNombre, boxDomicilio, boxNifCliente, boxTipoCliente, boxBotones);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20, 20, 20, 20));
        root.getChildren().add(vbox);

        
        Scene scene = new Scene(root, 400, 300);
        addClienteStage.setScene(scene);
        addClienteStage.setTitle("Añadir cliente");
        addClienteStage.show();
    }
    
    private void pantallaShowClientes() {
        Stage showClientesStage = new Stage();
        StackPane root = new StackPane();
        Text titulo = new Text(30, 30, "Lista de clientes");
        titulo.setStyle("-fx-font: 15 arial;");
        
        Button volver = new Button();
        volver.setText("Volver");
        volver.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                showClientesStage.close();
                clienteStage.show();
            }
        });
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(titulo, volver);
        vbox.setAlignment(Pos.CENTER);
        root.getChildren().add(vbox);
        
        Scene scene = new Scene(root, 300, 250);
        showClientesStage.setScene(scene);
        showClientesStage.setTitle("Mostrar clientes");
        showClientesStage.show();
    }    
}
