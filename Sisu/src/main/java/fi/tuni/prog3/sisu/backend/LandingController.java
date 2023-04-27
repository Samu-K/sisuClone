package fi.tuni.prog3.sisu.backend;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.io.IOException;

/**
 * Controller class for landing.fxml.
 */
public class LandingController {

  private String id;
  private Map<String, String> userInfo = new HashMap<>();

  @FXML TextField firstNameLabel;
  @FXML TextField lastNameLabel;
  @FXML TextField langLabel;
  @FXML TextField addressLabel;
  @FXML TextField numLabel;
  @FXML TextField mailLabel;
  @FXML Label degreeLabel;

  public void setId(String id) {
    this.id = id;
  }

  @FXML
  public void initialize() {
    try {
      readFile();
    } catch (IOException e) {
      System.out.println("ID file not found");
    }
    
    firstNameLabel.setText(userInfo.get("name"));
    lastNameLabel.setText(userInfo.get("name"));
    numLabel.setText(userInfo.get("id"));
  }

  private void readFile() throws IOException {
    String fileName = id + ".txt"; 
    String filePath = "user_data/" + fileName;
    File file = new File(filePath);

    List<String> lines = Files.readAllLines(Paths.get(file.getPath()));
    for (String line : lines) {
      String[] spl = line.split(":");
      userInfo.put(spl[0], spl[1]);
    }
  }
}
