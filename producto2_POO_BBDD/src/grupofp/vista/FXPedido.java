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
public class FXPedido extends FXMain {

    Stage pedidosStage = new Stage();

    private void deletePedido(String codigo) {
        try {
            String vacio = "";
            if (vacio.matches(codigo)) {
                mostrarAlerta("error", "Debes llenar todos los campos");
            } else {
                //delete pedido
            }
        } catch (Exception ex) {
            System.out.println(ex);
            mostrarAlerta("error", ex.getMessage());
        }
    }

    public void start(Stage mainMenu) {

        Text titulo = new Text(30, 30, "¿Que deseas hacer?");
        titulo.setStyle("-fx-font: 15 arial;");

        Button addPedido = new Button();
        addPedido.setText("Añadir pedido");
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
                pedidosStage.close();
                mainMenu.show();
            }
        });

        StackPane root = new StackPane();
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(titulo, addPedido, showPedidos, deletePedidos, volver);
        vbox.setAlignment(Pos.CENTER);
        root.getChildren().add(vbox);

        Scene scene = new Scene(root, 300, 250);

        pedidosStage.setTitle("Pedidos");
        pedidosStage.setScene(scene);
        pedidosStage.show();
    }

    private void pantallaAddPedido() {
        Stage addPedidosStage = new Stage();
        StackPane root = new StackPane();
        Text titulo = new Text(30, 30, "Añadir pedido");
        titulo.setStyle("-fx-font: 15 arial;");

        Button volver = new Button();
        volver.setText("Volver");
        volver.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                addPedidosStage.close();
                pedidosStage.show();
            }
        });
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(titulo, volver);
        vbox.setAlignment(Pos.CENTER);
        root.getChildren().add(vbox);

        Scene scene = new Scene(root, 300, 250);
        addPedidosStage.setScene(scene);
        addPedidosStage.setTitle("Añadir pedido");
        addPedidosStage.show();
    }

    private void pantallaShowPedidos() {
        Stage showPedidosStage = new Stage();
        StackPane root = new StackPane();
        Text titulo = new Text(30, 30, "Lista de pedidos");
        titulo.setStyle("-fx-font: 15 arial;");

        Button volver = new Button();
        volver.setText("Volver");
        volver.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                showPedidosStage.close();
                pedidosStage.show();
            }
        });
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(titulo, volver);
        vbox.setAlignment(Pos.CENTER);
        root.getChildren().add(vbox);

        Scene scene = new Scene(root, 300, 250);
        showPedidosStage.setScene(scene);
        showPedidosStage.setTitle("Mostrar pedidos");
        showPedidosStage.show();
    }

    private void pantallaDeletePedido() {
        Stage deletePedidoStage = new Stage();
        StackPane root = new StackPane();
        Text titulo = new Text(30, 30, "Eliminar pedido");
        titulo.setStyle("-fx-font: 15 arial;");

        Button volver = new Button();
        volver.setText("Volver");
        volver.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                deletePedidoStage.close();
                pedidosStage.show();
            }
        });
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(titulo, volver);
        vbox.setAlignment(Pos.CENTER);
        root.getChildren().add(vbox);

        Scene scene = new Scene(root, 300, 250);
        deletePedidoStage.setScene(scene);
        deletePedidoStage.setTitle("Eliminar pedido");
        deletePedidoStage.show();
    }
}
