package fi.tuni.prog3.sisu.backend;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class StartController {

  // load all textfields here
  @FXML TextField nameField;
  @FXML TextField numField;
  @FXML TextField enrollField;
  @FXML TextField gradField;
  @FXML Button contButton; 

  @FXML
  private void initialize() {
  }

  /**
   * Is executed when continue button is pressed.
   * Writes all given info to file, or reads if it ID exists.
   */
  public void handleContinue(ActionEvent event) throws IOException {
    String fileName = numField.getText() + ".txt"; 
    String filePath = "user_data/" + fileName;
    
    File file = new File(filePath);
    // currently just a test print
    if (file.exists()) {
      System.out.println("User logged in with id " + numField.getText());
      List<String> lines = Files.readAllLines(Paths.get(file.getPath()));
      for (String line : lines) {
        System.out.println(line);
      }
    } else {
      file.createNewFile();
      FileWriter writer = new FileWriter(file);
      String info = "name:" + nameField.getText() +"\n"
          + "id:" + numField.getText() + "\n"
          + "enroll:" + enrollField.getText() + "\n"
          + "grad:" + gradField.getText() + "\n";
      writer.write(info);
      writer.close();
    }
  }

} 
