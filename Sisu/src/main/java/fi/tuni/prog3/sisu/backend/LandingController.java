package fi.tuni.prog3.sisu.backend;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;



/**
 * Controller class for landing.fxml.
 */
public class LandingController {

  @FXML TextField firstNameLabel;
  @FXML TextField lastNameLabel;
  @FXML TextField langLabel;
  @FXML TextField addressLabel;
  @FXML Label numLabel;
  @FXML TextField mailLabel;
  @FXML Label degreeLabel;

  private TreeMap<String, String> userInfo;
  private String id="22016";

  public void setId(String id) {
    this.id = id;
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
    userInfo = MainController.getUserInfo();
    /*
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
    */
  }
}
