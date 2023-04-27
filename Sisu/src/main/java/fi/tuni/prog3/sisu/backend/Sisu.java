package fi.tuni.prog3.sisu.backend;

import fi.tuni.prog3.sisu.gui.SidebarController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import fi.tuni.prog3.sisu.backend.StartController;
import fi.tuni.prog3.sisu.backend.LandingController;

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
    FXMLLoader studiesLoader = 
        new FXMLLoader(getClass().getResource("/fi/tuni/prog3/sisu/gui/studies.fxml"));
    FXMLLoader sidebarLoader =
        new FXMLLoader(getClass().getResource("/fi/tuni/prog3/sisu/gui/sidebar.fxml"));
    FXMLLoader landingLoader =
        new FXMLLoader(getClass().getResource("/fi/tuni/prog3/sisu/gui/landing.fxml"));

    StudiesController studiesController = studiesLoader.getController(); 
    SidebarController sidebarController = sidebarLoader.getController();
    StartController loginController = loginLoader.getController();
    LandingController landingController = landingLoader.getController();

    landingController.setId(loginController.getId());

    StackPane sidebar = sidebarLoader.load();
    BorderPane main = loginLoader.load();
    sidebarController.setMain(main);
  
    studiesController.setSidebar(sidebar);
  
    Scene scene = new Scene(main, 330, 650);
    primaryStage.setScene(scene);
    primaryStage.setTitle("SIM");
    primaryStage.show();
  }
}
