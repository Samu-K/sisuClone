package fi.tuni.prog3.sisu.backend;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.StackPane;
import javafx.util.StringConverter;
import javafx.scene.layout.HBox;
import javafx.scene.control.ComboBox;
import java.util.Arrays;
import java.util.ArrayList;
import fi.tuni.prog3.sisu.backend.AutoCompleteComboBox.HideableItem;
import javafx.scene.layout.VBox;

/**
 * Controller class for studies.fxml.
 */
public class StudiesController {

  @FXML
  private StackPane mainPane;
  @FXML
  private HBox degreeCont;
  @FXML
  private VBox courseBox;

  @FXML
  private void initialize() {
    TreeItem<String> root = new TreeItem<String>("Root");
    root.getChildren().addAll(
        new TreeItem<String>("Item 1"),
        new TreeItem<String>("Item 2")
    );
    TreeView<String> courseView = new TreeView<String>(root);
    courseBox.getChildren().add(courseView);

    // create the actual dropdown menu
    ComboBox<HideableItem<String>> degreeDropDown = setupCombo(
        new ArrayList<String>(Arrays.asList("JOKU KIVA OPINTO", "LISÄÄ OPINTOJA", "car3"))
    );
    degreeDropDown.setPromptText("Degree programme...");
    // add that menu to our container
    degreeCont.getChildren().add(degreeDropDown);
    
  }

  private static ComboBox<HideableItem<String>> setupCombo(ArrayList<String> items) {
    ComboBox<HideableItem<String>> dropDown;

    dropDown = AutoCompleteComboBox.createComboBoxWithAutoCompletionSupport(
        items, 
        new StringConverter<HideableItem<String>>() {           
        @Override
        public String toString(HideableItem<String> object) {
          if (object != null) {                    
            return object.getObject();
          } else {
            return null;
          }
        }

        @Override
        public HideableItem<String> fromString(String string) {
          String foundString = items.stream().filter((String i)
                          -> (i).equals(string)).findFirst().orElse(null);
          return new HideableItem<>(foundString, this);

        }
        
      }
    );
    dropDown.getStylesheets().add("file:src/resources/css/dropdown.css");
    
    return dropDown;
  }

}
