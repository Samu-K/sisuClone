package fi.tuni.prog3.sisu.backend;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileWriter;
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
  private void logOut() throws IOException {
    LandingController lc = mainController.getLc();
    Map<String, String> info = lc.getUserData();
    
    String fileName = info.get("id") +".txt";
    String filePath = "user_data/" + fileName;
    File oldFile = new File(filePath);
    oldFile.delete();
    File newFile = new File(filePath);

    FileWriter writer = new FileWriter(newFile, false);
    for (Map.Entry<String, String> entry : info.entrySet()) {
      String line = entry.getKey() + ":" + entry.getValue() + "\n";
      System.out.println("Writing: " + line);
      writer.write(line);
    }
    writer.close();

  }
}
