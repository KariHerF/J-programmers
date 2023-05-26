package grupofp.vista;

import java.time.LocalDateTime;
import java.util.List;

import grupofp.modelo.ClienteEstandar;
import grupofp.modelo.ClientePremium;
import grupofp.modelo.Pedido;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author joanb
 */
public class FXPedido extends FXMain {

    Stage pedidoStage = new Stage();

    private void addPedido(String emailCliente, String codigoArticulo, String cantidadPedido, Stage stage) {
        try {
            LocalDateTime fechaHoraPedido;
            String vacio = "";
            if (vacio.matches(emailCliente) || vacio.matches(codigoArticulo)
                    || vacio.matches(cantidadPedido)) {
                mostrarAlerta("error", "Debes llenar todos los campos");
            } else {
                fechaHoraPedido = LocalDateTime.now();
                int cantidadInt = Integer.parseInt(cantidadPedido);
                this.miControlador.getDatos().validarEmail(emailCliente);
                this.miControlador.getDatos().validarArgumentoIntPositivo(cantidadInt);

                this.miControlador.crearPedido(emailCliente, codigoArticulo, fechaHoraPedido, cantidadInt);
                stage.close();
                mostrarAlerta("success", "Se ha creado el pedido correctamente");
                pedidoStage.show();
            }

        } catch (Exception ex) {
            System.out.println(ex);
            mostrarAlerta("error", ex.getMessage());
        }
    }

    private void deletePedido(String numeroPedido, Stage stage) {
        try {
            String vacio = "";
            if (vacio.matches(numeroPedido)) {
                mostrarAlerta("error", "Debes llenar todos los campos");
            } else {
                int cantidadInt = Integer.parseInt(numeroPedido);
                this.miControlador.eliminarPedido(cantidadInt);
                mostrarAlerta("success", "Se ha eliminado el pedido correctamente");
                stage.close();
                pedidoStage.show();
            }
        } catch (Exception ex) {
            System.out.println(ex);
            mostrarAlerta("error", ex.getMessage());
        }
    }

    public void start(Stage mainMenu) {

        Text titulo = new Text(30, 30, "�Que deseas hacer?");
        titulo.setStyle("-fx-font: 15 arial;");

        Button addPedido = new Button();
        addPedido.setText("A�adir pedido");
        addPedido.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                pantallaAddPedido();
            }
        });

        Button showPedidos = new Button();
        showPedidos.setText("Mostrar pedidos");
        showPedidos.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                pantallaShowPedidos();
            }
        });

        Button deletePedidos = new Button();
        deletePedidos.setText("Eliminar pedido");
        deletePedidos.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                pantallaDeletePedido();
            }
        });

        Button volver = new Button();
        volver.setText("Volver");
        volver.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                pedidoStage.close();
                mainMenu.show();
            }
        });

        StackPane root = new StackPane();
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(titulo, addPedido, showPedidos, deletePedidos, volver);
        vbox.setAlignment(Pos.CENTER);
        root.getChildren().add(vbox);

        Scene scene = new Scene(root, 300, 250);

        pedidoStage.setTitle("Pedidos");
        pedidoStage.setScene(scene);
        pedidoStage.show();
    }

    private void pantallaAddPedido() {

        Stage addPedidoStage = new Stage();
        StackPane root = new StackPane();
        Text titulo = new Text(30, 30, "A�adir pedido");
        titulo.setStyle("-fx-font: 15 arial;");

        TextField emailCliente = new TextField();
        emailCliente.setPromptText("Email del cliente");
        Label labelEmailCliente = new Label("Cliente:");
        HBox boxEmailCliente = new HBox(10);
        boxEmailCliente.getChildren().addAll(labelEmailCliente, emailCliente);
        boxEmailCliente.setAlignment(Pos.BASELINE_LEFT);

        TextField codigoArticulo = new TextField();
        codigoArticulo.setPromptText("Codigo del articulo");
        Label labelCodigoArticulo = new Label("Articulo:");
        HBox boxCodigoArticulo = new HBox(10);
        boxCodigoArticulo.getChildren().addAll(labelCodigoArticulo, codigoArticulo);
        boxCodigoArticulo.setAlignment(Pos.BASELINE_LEFT);

        TextField cantidadPedido = new TextField();
        cantidadPedido.setPromptText("Ex: 10");
        Label labelCantidadPedido = new Label("Cantidad:");
        HBox boxCantidadPedido = new HBox(10);
        boxCantidadPedido.getChildren().addAll(labelCantidadPedido, cantidadPedido);
        boxCantidadPedido.setAlignment(Pos.BASELINE_LEFT);

        Button add = new Button();
        add.setText("A�adir");
        add.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                addPedido(emailCliente.getText(), codigoArticulo.getText(),
                        cantidadPedido.getText(), addPedidoStage);

            }
        });

        Button volver = new Button();
        volver.setText("Volver");
        volver.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                addPedidoStage.close();
                pedidoStage.show();
            }
        });

        HBox boxBotones = new HBox(20);
        boxBotones.getChildren().addAll(add, volver);
        boxBotones.setAlignment(Pos.CENTER);
        boxBotones.setPadding(new Insets(20, 20, 0, 0));

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(titulo, boxEmailCliente, boxCodigoArticulo, boxCantidadPedido, boxBotones);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20, 20, 20, 20));
        root.getChildren().add(vbox);

        Scene scene = new Scene(root, 400, 300);
        addPedidoStage.setScene(scene);
        addPedidoStage.setTitle("A�adir pedido");
        addPedidoStage.show();
    }

    private void pantallaShowPedidos() {
        Stage showPedidoStage = new Stage();
        StackPane root = new StackPane();
        Text titulo = new Text(30, 30, "Lista de pedidos");
        titulo.setStyle("-fx-font: 15 arial;");
        
        TableView<Pedido> tableView = new TableView<>();
        TableColumn<Pedido, String> numPedidoColumn = new TableColumn<>("Numero");
        TableColumn<Pedido, String> fechaPedidoColumn = new TableColumn<>("Fecha");
        TableColumn<Pedido, String> cantidadColumn = new TableColumn<>("Cantidad");
        TableColumn<Pedido, String> codigoArtColumn = new TableColumn<>("CodigoArticulo");
        TableColumn<Pedido, String> articuloColumn = new TableColumn<>("Articulo");
        TableColumn<Pedido, String> clienteColumn = new TableColumn<>("Cliente");
        TableColumn<Pedido, String> emailColumn = new TableColumn<>("Email");
        
        // Agrega más columnas según los atributos de tu clase Articulo

        tableView.getColumns().addAll(numPedidoColumn,fechaPedidoColumn, cantidadColumn,codigoArtColumn,articuloColumn, clienteColumn,emailColumn);

        // Obtén la lista de artículos desde la base de datos
        var listaObjetos = this.miControlador.getListaPedidos();
     
        //NOSE COMO HACER ESTO
        


        if (listaObjetos instanceof List) {
            List<Pedido> listaPedidos = (List<Pedido>) listaObjetos;
            ObservableList<Pedido> pedidos = FXCollections.observableArrayList(listaPedidos);
            tableView.setItems(pedidos);
        } else {
            mostrarAlerta("error", "La lista1 de clientes no es compatible");
        }



        Button volver = new Button();
        volver.setText("Volver");
        volver.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                showPedidoStage.close();
                pedidoStage.show();
            }
        });
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(titulo, volver);
        vbox.setAlignment(Pos.CENTER);
        root.getChildren().add(vbox);

        Scene scene = new Scene(root, 300, 250);
        showPedidoStage.setScene(scene);
        showPedidoStage.setTitle("Mostrar pedidos");
        showPedidoStage.show();
    }

    private void pantallaDeletePedido() {
        Stage deletePedidoStage = new Stage();
        StackPane root = new StackPane();
        Text titulo = new Text(30, 30, "Eliminar pedido");
        titulo.setStyle("-fx-font: 15 arial;");

        TextField numeroPedido = new TextField();
        numeroPedido.setPromptText("Introduce el numero de pedido");
        Label labelNumeroPedido = new Label("Pedido:");
        HBox boxNumeroPedido = new HBox(10);
        boxNumeroPedido.getChildren().addAll(labelNumeroPedido, numeroPedido);
        boxNumeroPedido.setAlignment(Pos.BASELINE_LEFT);

        Button deletePedido = new Button();
        deletePedido.setText("Eliminar");
        deletePedido.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                deletePedido(numeroPedido.getText(), deletePedidoStage);

            }
        });

        Button volver = new Button();
        volver.setText("Volver");
        volver.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                deletePedidoStage.close();
                pedidoStage.show();
            }
        });

        HBox boxBotones = new HBox(20);
        boxBotones.getChildren().addAll(deletePedido, volver);
        boxBotones.setAlignment(Pos.CENTER);
        boxBotones.setPadding(new Insets(20, 20, 0, 0));

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(titulo, boxNumeroPedido, boxBotones);
        vbox.setPadding(new Insets(20, 20, 20, 20));
        vbox.setAlignment(Pos.CENTER);
        root.getChildren().add(vbox);

        Scene scene = new Scene(root, 400, 300);
        deletePedidoStage.setScene(scene);
        deletePedidoStage.setTitle("Eliminar pedido");
        deletePedidoStage.show();
    }
}
