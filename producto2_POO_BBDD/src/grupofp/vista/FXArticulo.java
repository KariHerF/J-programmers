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
public class FXArticulo extends FXMain {
    Stage articuloStage = new Stage();

    public void start(Stage mainMenu) {

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
        
        Button deleteArticulo = new Button();
        deleteArticulo.setText("Eliminar articulo");
        deleteArticulo.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                pantallaDeleteArticulo();
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
        vbox.getChildren().addAll(titulo, addArticulo, showArticulos, deleteArticulo, volver);
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
        
        Button volver = new Button();
        volver.setText("Volver");
        volver.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                addArticuloStage.close();
                articuloStage.show();
            }
        });
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(titulo, volver);
        vbox.setAlignment(Pos.CENTER);
        root.getChildren().add(vbox);
        
        Scene scene = new Scene(root, 300, 250);
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
    
       private void pantallaDeleteArticulo() {
        Stage deleteArticuloStage = new Stage();
        StackPane root = new StackPane();
        Text titulo = new Text(30, 30, "Eliminar articulo");
        titulo.setStyle("-fx-font: 15 arial;");
        
        Button volver = new Button();
        volver.setText("Volver");
        volver.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                deleteArticuloStage.close();
                articuloStage.show();
            }
        });
        
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(titulo, volver);
        vbox.setAlignment(Pos.CENTER);
        root.getChildren().add(vbox);
        
        Scene scene = new Scene(root, 300, 250);
        deleteArticuloStage.setScene(scene);
        deleteArticuloStage.setTitle("Eliminar articulo");
        deleteArticuloStage.show();
    }
    
}