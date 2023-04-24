package fi.tuni.prog3.sisu.gui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

/**
 * Controller for the sidebar.
 */
public class SidebarController {
  @FXML
  private Button tiedotButton;

  @FXML
  private Button opinnotButton;

  private BorderPane main;

  public void setMain(BorderPane main) {
    this.main = main;
  }

  private void loadAndDisplay(String fxmlFileName) {
    try {
      FXMLLoader loader = 
          new FXMLLoader(getClass().getResource("/fi/tuni/prog3/sisu/gui/" + fxmlFileName));
      BorderPane newPage = loader.load();
      main.setCenter(newPage);
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
}
