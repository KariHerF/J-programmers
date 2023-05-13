/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package grupofp.vista;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    public void start(Stage mainMenu) {

        Text titulo = new Text(30, 30, "¿Que deseas hacer?");
        titulo.setStyle("-fx-font: 15 arial;");
        
        Button addCliente = new Button();
        addCliente.setText("Añadir cliente");
        addCliente.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                addClienteStage();
            }
        });
        
        Button showClientes = new Button();
        showClientes.setText("Mostrar clientes");
        showClientes.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                showClientesEscena();
            }
        });
        
        Button deleteCliente = new Button();
        deleteCliente.setText("Eliminar cliente");
        deleteCliente.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                deleteClienteStage();
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
        vbox.getChildren().addAll(titulo, addCliente, showClientes, deleteCliente, volver);
        vbox.setAlignment(Pos.CENTER);
        root.getChildren().add(vbox);
        
        Scene scene = new Scene(root, 300, 250);
        
        clienteStage.setTitle("Clientes");
        clienteStage.setScene(scene);
        clienteStage.show();
    }
    
    private void addClienteStage() {
        Stage addClienteStage = new Stage();
        StackPane root = new StackPane();
        Text titulo = new Text(30, 30, "Añadir cliente");
        titulo.setStyle("-fx-font: 15 arial;");
        root.getChildren().add(titulo);
        
        Scene scene = new Scene(root, 300, 250);
        addClienteStage.setScene(scene);
        addClienteStage.setTitle("Añadir cliente");
        addClienteStage.show();
    }
    
    private void showClientesEscena() {
        Stage showClientesStage = new Stage();
        StackPane root = new StackPane();
        Text titulo = new Text(30, 30, "Lista de clientes");
        titulo.setStyle("-fx-font: 15 arial;");
        root.getChildren().add(titulo);
        
        Scene scene = new Scene(root, 300, 250);
        showClientesStage.setScene(scene);
        showClientesStage.setTitle("Mostrar clientes");
        showClientesStage.show();
    }
    
       private void deleteClienteStage() {
        Stage deleteClienteStage = new Stage();
        StackPane root = new StackPane();
        Text titulo = new Text(30, 30, "Eliminar cliente");
        titulo.setStyle("-fx-font: 15 arial;");
        root.getChildren().add(titulo);
        
        Scene scene = new Scene(root, 300, 250);
        deleteClienteStage.setScene(scene);
        deleteClienteStage.setTitle("Eliminar cliente");
        deleteClienteStage.show();
    }
    
}
