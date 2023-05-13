package grupofp.vista;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author joanb
 */
public class FXArticulo extends FXMain {
    Stage articuloStage = new Stage();

    public void start(Stage mainMenu) {

        Button btn = new Button();
        btn.setText("Articulo");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                articuloStage.close();
                mainMenu.show();
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        articuloStage.setTitle("Hello World!");
        articuloStage.setScene(scene);
        articuloStage.show();
    }
    
}