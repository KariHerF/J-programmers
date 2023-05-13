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
public class FXPedido extends FXMain {
    Stage pedidosStage = new Stage();

    public void start(Stage mainMenu) {

        Button btn = new Button();
        btn.setText("Articulo");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                pedidosStage.close();
                mainMenu.show();
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        pedidosStage.setTitle("Gestionar");
        pedidosStage.setScene(scene);
        pedidosStage.show();
    }
    
}