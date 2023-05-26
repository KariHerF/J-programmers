package grupofp.vista;

import grupofp.controlador.Controlador;
import grupofp.controlador.Main;
import java.time.Duration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
public class FXArticulo extends FXMain {

    Stage articuloStage = new Stage();

    private void addArticulo(String codigo, String descripcion, String pvp, String tiempoPrep,
            String gastosEnvio, Stage stage) {
        try {
            String vacio = "";
            if (vacio.matches(codigo) || vacio.matches(descripcion)
                    || vacio.matches(pvp) || vacio.matches(tiempoPrep) || vacio.matches(gastosEnvio)) {
                mostrarAlerta("error", "Debes llenar todos los campos");
            } else {
                float pvpFloat = Float.parseFloat(pvp);
                Duration duracion = Duration.parse(tiempoPrep);
                float gastosEnvioFloat = Float.parseFloat(gastosEnvio);
                System.out.println("before");

                this.miControlador.crearArticulo(codigo, descripcion, pvpFloat,
                        duracion, gastosEnvioFloat);
                stage.close();
                mostrarAlerta("success", "Se ha creado el articulo correctamente");
                articuloStage.show();
            }

        } catch (Exception ex) {
            System.out.println(ex);
            mostrarAlerta("error", ex.getMessage());
        }
    }



    public void start(Stage mainMenu) {
        if (this.miControlador == null) {
            System.out.println("null inside");
        }
        Text titulo = new Text(30, 30, "¿Que deseas hacer?");
        titulo.setStyle("-fx-font: 15 arial;");

        Button addArticulo = new Button();
        addArticulo.setText("Añadir articulo");
        addArticulo.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                pantallaAddArticulo();
                articuloStage.hide();

            }
        });

        Button showArticulos = new Button();
        showArticulos.setText("Mostrar articulos");
        showArticulos.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                pantallaShowArticulos();
                articuloStage.hide();

            }
        });

 

        Button volver = new Button();
        volver.setText("Volver");
        volver.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                articuloStage.close();
                mainMenu.show();
            }
        });

        StackPane root = new StackPane();
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(titulo, addArticulo, showArticulos, volver);
        vbox.setAlignment(Pos.CENTER);
        root.getChildren().add(vbox);

        Scene scene = new Scene(root, 300, 250);

        articuloStage.setTitle("Articulos");
        articuloStage.setScene(scene);
        articuloStage.show();
    }

    private void pantallaAddArticulo() {
        Stage addArticuloStage = new Stage();
        StackPane root = new StackPane();
        Text titulo = new Text(30, 30, "Añadir articulo");
        titulo.setStyle("-fx-font: 15 arial;");

        TextField codigoArticulo = new TextField();
        codigoArticulo.setPromptText("Introduce el codigo");
        Label labelCodigo = new Label("Codigo:");
        HBox boxCodigo = new HBox(10);
        boxCodigo.getChildren().addAll(labelCodigo, codigoArticulo);
        boxCodigo.setAlignment(Pos.BASELINE_LEFT);

        TextField descripcionArticulo = new TextField();
        descripcionArticulo.setPromptText("Introduce la descripcion");
        Label labelDescripcion = new Label("Descripcion:");
        HBox boxDescripcion = new HBox(10);
        boxDescripcion.getChildren().addAll(labelDescripcion, descripcionArticulo);
        boxDescripcion.setAlignment(Pos.BASELINE_LEFT);

        TextField pvpArticulo = new TextField();
        pvpArticulo.setPromptText("Introduce un decimal");
        Label labelPvp = new Label("PVP:");
        HBox boxPvp = new HBox(10);
        boxPvp.getChildren().addAll(labelPvp, pvpArticulo);
        boxPvp.setAlignment(Pos.BASELINE_LEFT);

        TextField tiempoPrepArticulo = new TextField();
        tiempoPrepArticulo.setPromptText("Formato ISO 8601 (PTnHnMnS)");
        Label labelTiempoPrep = new Label("Tiempo de Preparacion:");
        HBox boxTiempoPrep = new HBox(10);
        boxTiempoPrep.getChildren().addAll(labelTiempoPrep, tiempoPrepArticulo);
        boxTiempoPrep.setAlignment(Pos.BASELINE_LEFT);

        TextField gastosEnvioArticulo = new TextField();
        gastosEnvioArticulo.setPromptText("Introduce un decimal");
        Label labelGastosEnvio = new Label("Gastos Envio:");
        HBox boxGastosEnvio = new HBox(10);
        boxGastosEnvio.getChildren().addAll(labelGastosEnvio, gastosEnvioArticulo);
        boxGastosEnvio.setAlignment(Pos.BASELINE_LEFT);

        Button add = new Button();
        add.setText("Añadir");
        add.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                addArticulo(codigoArticulo.getText(), descripcionArticulo.getText(),
                        pvpArticulo.getText(), tiempoPrepArticulo.getText(), gastosEnvioArticulo.getText(),
                        addArticuloStage);

            }
        });

        Button volver = new Button();
        volver.setText("Volver");
        volver.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                addArticuloStage.close();
                articuloStage.show();
            }
        });

        HBox boxBotones = new HBox(20);
        boxBotones.getChildren().addAll(add, volver);
        boxBotones.setAlignment(Pos.CENTER);
        boxBotones.setPadding(new Insets(20, 20, 0, 0));

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(titulo, boxCodigo, boxDescripcion, boxPvp, boxTiempoPrep, boxGastosEnvio, boxBotones);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20, 20, 20, 20));
        root.getChildren().add(vbox);

        Scene scene = new Scene(root, 400, 300);
        addArticuloStage.setScene(scene);
        addArticuloStage.setTitle("Añadir articulo");
        addArticuloStage.show();
    }

    private void pantallaShowArticulos() {
        Stage showArticuloStage = new Stage();
        StackPane root = new StackPane();
        Text titulo = new Text(30, 30, "Lista de articulos");
        titulo.setStyle("-fx-font: 15 arial;");

        Button volver = new Button();
        volver.setText("Volver");
        volver.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                showArticuloStage.close();
                articuloStage.show();
            }
        });

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(titulo, volver);
        vbox.setAlignment(Pos.CENTER);
        root.getChildren().add(vbox);

        Scene scene = new Scene(root, 300, 250);
        showArticuloStage.setScene(scene);
        showArticuloStage.setTitle("Mostrar articulos");
        showArticuloStage.show();
    }

}
