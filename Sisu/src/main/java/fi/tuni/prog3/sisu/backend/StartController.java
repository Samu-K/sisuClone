package fi.tuni.prog3.sisu.backend;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import fi.tuni.prog3.sisu.backend.LandingController;
import fi.tuni.prog3.sisu.gui.SidebarController;
import java.util.Map;
import java.util.HashMap;

/**
 * Controller for start.fxml
 */
public class StartController {

  // load all textfields here
  @FXML TextField nameField;
  @FXML TextField numField;
  @FXML TextField enrollField;
  @FXML TextField gradField;
  @FXML Button contButton;

  SidebarController sbc;

  private Map<String, String> userInfo = new HashMap<>();
  
  /**
   * Return id.
   */
  public String getId() {
    return numField.getText();
  }

  @FXML
  public void initialize() {
    this.sbc = new SidebarController();
  }

  /**
   * Is executed when continue button is pressed.
   * Writes all given info to file, or reads if it ID exists.
   */
  public void handleContinue(ActionEvent event) throws IOException {
    String fileName = numField.getText() + ".txt"; 
    String filePath = "user_data/" + fileName;
    
    File file = new File(filePath);

    if (file.exists() == false) {
      file.createNewFile();
      userInfo.put("name", nameField.getText());
      userInfo.put("id", numField.getText());
      userInfo.put("enroll", enrollField.getText());
      userInfo.put("grad", gradField.getText());

      FileWriter writer = new FileWriter(file);
      for (Map.Entry<String, String> entry : userInfo.entrySet()) {
        writer.write(entry.getKey() + ":" + entry.getValue() + "\n");
      }
      writer.close();
    }

    sbc.showLanding();
  }

} 
