package fi.tuni.prog3.sisu.backend;

import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.StackPane;

/**
 * Controller class for studies.fxml.
 */
public class StudiesController {

  @FXML
  private StackPane mainPane;

  @FXML
  private void initialize() {
    TreeItem<String> root = new TreeItem<String>("Root");
    root.getChildren().addAll(
        new TreeItem<String>("Item 1"),
        new TreeItem<String>("Item 2")
    );
    TreeView<String> courseView = new TreeView<String>(root);
    mainPane.getChildren().add(courseView);

    System.out.println("Init ready");
  }
}
