package fi.tuni.prog3.sisu.backend;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


/**
 * Controller for start.fxml
 */
public class StartController {

  private MainController mainController;
  private LandingController landingController;

  // load all textfields here
  @FXML TextField nameField;
  @FXML TextField numField;
  @FXML TextField enrollField;
  @FXML TextField gradField;
  @FXML Button contButton;

  private Map<String, String> userInfo = new HashMap<>();
  
  /**
   * Return id.
   */
  public String getId() {
    return numField.getText();
  }

  public void setMainController(MainController mainController) {
    this.mainController = mainController;
  }

  /**
   * Is executed when continue button is pressed.
   * Writes all given info to file, or reads if it ID exists.
   * After this it shows the main view.
   */
  public void handleContinue(ActionEvent event) {

    try {
      writeToUserFile();
    } catch (IOException e) {
      System.out.println("Error writing to file");
    }
    mainController.showMainView();
  }

  private void writeToUserFile() throws IOException {
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
        String line = entry.getKey() + ":" + entry.getValue() + "\n";
        System.out.println("Writing: " + line);
        writer.write(line);
      }
      writer.close();
    }
  }

} 
