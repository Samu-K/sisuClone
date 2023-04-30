package fi.tuni.prog3.sisu.backend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


/**
 * Controller for start.fxml
 */
public class StartController {

  private MainController mainController;

  // load all textfields here
  @FXML TextField nameField;
  @FXML TextField numField;
  @FXML TextField enrollField;
  @FXML TextField gradField;
  @FXML Button contButton;
  
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

    String userName = nameField.getText();
    String userStudentId = numField.getText();
    String userExpectedGradDate = gradField.getText();
    String userEnrollDate = enrollField.getText();

    // TODO: check if there already is a user info file with the this id
    boolean userExists = false;

    if (!userExists) {
      // TODO: create new file for the user
    }

    // TODO: load the user info from the file
    // Reading the file should be done in a separate method that is called from here.
    // The function will evetually also handle loading users previous progress.


    // This is a temporary solution to update the user info to the main controller.
    // It makes it possible to show the user info in the landing view.
    MainController.addEntryToUserInfo("name", userName);
    MainController.addEntryToUserInfo("id", userStudentId);
    MainController.addEntryToUserInfo("grad", userExpectedGradDate);
    MainController.addEntryToUserInfo("enroll", userEnrollDate);

    mainController.showMainView();
  }
} 
