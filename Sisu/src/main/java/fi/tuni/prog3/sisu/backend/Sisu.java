package fi.tuni.prog3.sisu.backend;

import fi.tuni.prog3.sisu.gui.SidebarController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
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
    FXMLLoader loginLoader = 
        new FXMLLoader(getClass().getResource("/fi/tuni/prog3/sisu/gui/start.fxml"));
    
    FXMLLoader loader = 
        new FXMLLoader(getClass().getResource("/fi/tuni/prog3/sisu/gui/studies.fxml"));
    BorderPane main = loader.load();
    StudiesController studiesController = loader.getController();
  
    FXMLLoader sidebarLoader =
        new FXMLLoader(getClass().getResource("/fi/tuni/prog3/sisu/gui/sidebar.fxml"));
    StackPane sidebar = sidebarLoader.load();
    SidebarController sidebarController = sidebarLoader.getController();
    sidebarController.setMain(main);
  
    studiesController.setSidebar(sidebar);
  
    Scene scene = new Scene(loginLoader.load(), 330, 650);
    primaryStage.setScene(scene);
    primaryStage.setTitle("SIM");
    primaryStage.show();
  }
}
