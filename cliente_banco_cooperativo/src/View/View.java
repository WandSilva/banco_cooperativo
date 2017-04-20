package View;

import comunicacao.Comunicacao;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * Created by wanderson on 14/04/17.
 *
 * Main da aplicação. Classe responsável por iniciar a interface gráfica.
 */
public class View extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLTelaInicial.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Banco Cooperativo");
        stage.show();
    }

    public static void main(String[] args) {
        Comunicacao.setIp(args[0]);
        launch(args);
    }
}
