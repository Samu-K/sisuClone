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

  private String id="22016";
  private Map<String, String> userInfo = new HashMap<>();

  @FXML TextField firstNameLabel;
  @FXML TextField lastNameLabel;
  @FXML TextField langLabel;
  @FXML TextField addressLabel;
  @FXML Label numLabel;
  @FXML TextField mailLabel;
  @FXML Label degreeLabel;

  public void setId(String id) {
    this.id = id;
  }

  public Map<String, String> getUserData() {
    updateUserInfo();
    return userInfo;
  }

  private void updateUserInfo() {
    userInfo = new HashMap<String,String>();
    userInfo.put("id",numLabel.getText());
    userInfo.put("first_name",firstNameLabel.getText());
    userInfo.put("last_name",lastNameLabel.getText());
    userInfo.put("language",langLabel.getText());
    userInfo.put("address",langLabel.getText());
    userInfo.put("email",mailLabel.getText());
  }

  @FXML
  private void initialize() {
    try {
      readFile();
    } catch (IOException e) {
      System.out.println("ID file not found");
    }
    System.out.println("ID: " + userInfo.get("id")); 
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
      if (spl.length < 2) {
        continue;
      }
      System.out.println(spl[0] + " / " + spl[1]);
      userInfo.put(spl[0], spl[1]);
    }
  }
}
