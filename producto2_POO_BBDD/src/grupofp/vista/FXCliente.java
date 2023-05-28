/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package grupofp.vista;

import java.util.List;

import grupofp.modelo.Articulo;
import grupofp.modelo.Cliente;
import grupofp.modelo.ClienteEstandar;
import grupofp.modelo.ClientePremium;
import grupofp.modelo.Lista;
import grupofp.modelo.ListaClientes;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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

        Button showAllClientes = new Button();
        showAllClientes.setText("Mostrar clientes");
        showAllClientes.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                pantallaShowClientes("todos");
            }
        });
        Button showStandardClientes = new Button();
        showStandardClientes.setText("Mostrar clientes estandar");
        showStandardClientes.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                pantallaShowClientes("estandar");
            }
        });

        Button showPremiumClientes = new Button();
        showPremiumClientes.setText("Mostrar clientes premium");
        showPremiumClientes.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                pantallaShowClientes("premium");
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
        vbox.getChildren().addAll(titulo, addCliente, showAllClientes, showStandardClientes, showPremiumClientes, volver);
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
        Text titulo = new Text(30, 30, "Aï¿½adir cliente");
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
        add.setText("Aï¿½adir");
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

    private void pantallaShowClientes(String tipo) {
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
        vbox.setAlignment(Pos.CENTER);
        root.getChildren().add(vbox);

        if (tipo == "estandar") {
            try {
                Lista<ClienteEstandar> listaEstandar = this.miControlador.getListaClientesEstandar();

                TableView<ClienteEstandar> tableView = new TableView<>();
                TableColumn<ClienteEstandar, String> emailColumn = new TableColumn<>("Email");
                emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

                TableColumn<ClienteEstandar, String> nombreColumn = new TableColumn<>("Nombre");
                nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));

                TableColumn<ClienteEstandar, String> domicilioColumn = new TableColumn<>("Domicilio");
                domicilioColumn.setCellValueFactory(new PropertyValueFactory<>("domicilio"));

                TableColumn<ClienteEstandar, String> nifColumn = new TableColumn<>("Nif");
                nifColumn.setCellValueFactory(new PropertyValueFactory<>("nif"));

                TableColumn<ClienteEstandar, Float> cuotaColumn = new TableColumn<>("CuotaAnual");
                cuotaColumn.setCellValueFactory(new PropertyValueFactory<>("cuotaAnual"));

                TableColumn<ClienteEstandar, Float> descuentoColumn = new TableColumn<>("dtoGtoEnvio");
                descuentoColumn.setCellValueFactory(new PropertyValueFactory<>("dtoGtoEnvio"));

                TableColumn<ClienteEstandar, String> tipoCliCloumn = new TableColumn<>("tipoCliente");
                tipoCliCloumn.setCellValueFactory(new PropertyValueFactory<>("tipoCliente"));

                tableView.getColumns().addAll(emailColumn, nombreColumn, domicilioColumn, nifColumn, cuotaColumn, descuentoColumn, tipoCliCloumn);

                List<ClienteEstandar> listaClientes1 = (List<ClienteEstandar>) listaEstandar;
                ObservableList<ClienteEstandar> clientes1 = FXCollections.observableArrayList(listaClientes1);
                tableView.setItems(clientes1);
                vbox.getChildren().addAll(titulo, tableView, volver);

            } catch (Exception e) {
                mostrarAlerta("error", e.getMessage());
                vbox.getChildren().addAll(titulo, volver);
            }
        } else if (tipo == "premium") {
            try {
                Lista<ClientePremium> listaPremium = this.miControlador.getListaClientesPremium();
                
                TableView<ClientePremium> tableView = new TableView<>();

                TableColumn<ClientePremium, String> emailColumn2 = new TableColumn<>("Email");
                emailColumn2.setCellValueFactory(new PropertyValueFactory<>("email"));

                TableColumn<ClientePremium, String> nombreColumn2 = new TableColumn<>("Nombre");
                nombreColumn2.setCellValueFactory(new PropertyValueFactory<>("nombre"));

                TableColumn<ClientePremium, String> domicilioColumn2 = new TableColumn<>("Domicilio");
                domicilioColumn2.setCellValueFactory(new PropertyValueFactory<>("domicilio"));

                TableColumn<ClientePremium, String> nifColumn2 = new TableColumn<>("Nif");
                nifColumn2.setCellValueFactory(new PropertyValueFactory<>("nif"));

                TableColumn<ClientePremium, String> cuotaColumn2 = new TableColumn<>("CuotaAnual");

                TableColumn<ClientePremium, String> descuentoColumn2 = new TableColumn<>("dtoGtoEnvio");

                TableColumn<ClientePremium, String> tipoCliCloumn2 = new TableColumn<>("tipoCliente");

                tableView.getColumns().addAll(emailColumn2, nombreColumn2, domicilioColumn2, nifColumn2, cuotaColumn2, descuentoColumn2, tipoCliCloumn2);

                 List<ClientePremium> listaClientes1 = (List<ClientePremium>) listaPremium;
                ObservableList<ClientePremium> clientes2 = FXCollections.observableArrayList(listaClientes1);
                tableView.setItems(clientes2);
                vbox.getChildren().addAll(titulo, tableView, volver);

            } catch (Exception e) {
                mostrarAlerta("error", e.getMessage());
                vbox.getChildren().addAll(titulo, volver);
            }
        } else {
            try {
                 ListaClientes listaClientes = this.miControlador.getListaClientes();


                TableView<Cliente> tableView = new TableView<>();

                TableColumn<Cliente, String> emailColumn2 = new TableColumn<>("Email");
                emailColumn2.setCellValueFactory(new PropertyValueFactory<>("email"));

                TableColumn<Cliente, String> nombreColumn2 = new TableColumn<>("Nombre");
                nombreColumn2.setCellValueFactory(new PropertyValueFactory<>("nombre"));

                TableColumn<Cliente, String> domicilioColumn2 = new TableColumn<>("Domicilio");
                domicilioColumn2.setCellValueFactory(new PropertyValueFactory<>("domicilio"));

                TableColumn<Cliente, String> nifColumn2 = new TableColumn<>("Nif");
                nifColumn2.setCellValueFactory(new PropertyValueFactory<>("nif"));

                TableColumn<Cliente, String> cuotaColumn2 = new TableColumn<>("CuotaAnual");
                cuotaColumn2.setCellValueFactory(new PropertyValueFactory<>("cuotaAnual"));

                TableColumn<Cliente, String> descuentoColumn2 = new TableColumn<>("dtoGtoEnvio");
                descuentoColumn2.setCellValueFactory(new PropertyValueFactory<>("dtoGtoEnvio"));

                TableColumn<Cliente, String> tipoCliCloumn2 = new TableColumn<>("tipo_cliente");
                tipoCliCloumn2.setCellValueFactory(new PropertyValueFactory<>("tipo_cliente"));
// Agrega mÃ¡s columnas segÃºn los atributos de tu clase Articulo
                tableView.getColumns().addAll(emailColumn2, nombreColumn2, domicilioColumn2, nifColumn2, cuotaColumn2, descuentoColumn2, tipoCliCloumn2);

                List<Cliente> listaClientes1 = (List<Cliente>) listaClientes;
                ObservableList<Cliente> clientes1 = FXCollections.observableArrayList(listaClientes1);
                tableView.setItems(clientes1);

                vbox.getChildren().addAll(titulo, tableView, volver);

            } catch (Exception e) {
                mostrarAlerta("error", e.getMessage());
                vbox.getChildren().addAll(titulo, volver);
            }
        }

        Scene scene = new Scene(root, 300, 250);
        showClientesStage.setScene(scene);
        showClientesStage.setTitle("Mostrar clientes");
        showClientesStage.show();
    }
}
