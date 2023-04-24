package fi.tuni.prog3.sisu.backend;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.Priority;
import org.controlsfx.control.ToggleSwitch;


/**
 * Controller class for select.fxml.
 */ 
public class SelectController {

  @FXML private VBox courseBox;

  /**
   * Init runs on start.
   */
  @FXML
  private void initialize() {
    HBox t1 = createCourseNode("Ohjelmointi 1 | 5op");
    HBox t2 = createCourseNode("Ohjelmointi 2 | 5op");
    HBox t3 = createCourseNode("Ohjelmointi 3 | 5op");

    courseBox.getChildren().addAll(t1, t2, t3);
  }


  private static HBox createCourseNode(String name) {
    HBox container = new HBox();
    container.setStyle("-fx-background-color: #1E1E1E;");
    Label courseName = new Label(name);
    Region spacer = new Region();
    HBox.setHgrow(spacer, Priority.ALWAYS);
    ToggleSwitch toggle = new ToggleSwitch();
    container.getChildren().addAll(courseName, spacer, toggle);
    
    return container;
  }
}
