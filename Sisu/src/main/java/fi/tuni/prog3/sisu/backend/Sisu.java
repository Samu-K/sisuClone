package fi.tuni.prog3.sisu.backend;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Main sisu class.
 */
public class Sisu extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    FXMLLoader loader = new FXMLLoader(); 
    URL stdUrl = new URL("file:src/main/java/fi/tuni/prog3/sisu/gui/studies.fxml");
    loader.setLocation(stdUrl);

    BorderPane main = loader.<BorderPane>load();
    
    Scene scene = new Scene(main);
    primaryStage.setScene(scene);
    primaryStage.setTitle("SIM");
    primaryStage.show();
  }

}
