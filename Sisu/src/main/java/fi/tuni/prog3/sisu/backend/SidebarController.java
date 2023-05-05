package fi.tuni.prog3.sisu.backend;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;


/**
 * Controller for the sidebar.
 */
public class SidebarController {

  private MainController mainController;

  @FXML private static StackPane sidebarPlaceholder;
  @FXML private Button tiedotButton;
  @FXML private Button opinnotButton;


  public void setMainController(MainController mainController) {
    this.mainController = mainController;
  } 

  public static void setSidebar(StackPane sidebar) {
    sidebarPlaceholder.getChildren().setAll(sidebar);
  }

  private void loadAndDisplay(String fxmlFileName) {
    try {
      FXMLLoader loader = 
          new FXMLLoader(getClass().getResource("/fi/tuni/prog3/sisu/gui/" + fxmlFileName));
      BorderPane newPage = loader.load();
      mainController.setCenter(newPage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  public void showLanding() {
    loadAndDisplay("landing.fxml");
  }

  @FXML
  public void showStudies() {
    loadAndDisplay("studies.fxml");
  }

  @FXML
  public void removeDegree() {
    StudiesController.resetSelectedProgramme();
    showStudies();
  }

  @FXML
  private void logOut() throws IOException {
    // Exit the program
    System.exit(0);
  }
}
