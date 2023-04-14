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
    TreeItem<String> root1 = new TreeItem<String>("Yhteiset opinnot");
    root1.getChildren().addAll(
        new TreeItem<String>("Orientoivat opinnot"),
        new TreeItem<String>("Tilastotieteen johdatuskurssi"),
        new TreeItem<String>("Johdatus yliopistomatematiikkaan")
    );
    TreeItem<String> root2 = new TreeItem<>("Perusopinnot");
    root2.getChildren().addAll(
        new TreeItem<String>("Ohjelmointi 1"),
        new TreeItem<String>("Ohjelmointi 2"),
        new TreeItem<String>("Ohjelmointi 3")
    );
    ArrayList<TreeItem<String>> roots = new ArrayList<>();
    roots.add(root1);
    roots.add(root2);
    TreeView<String> courseView = createMultiNodeTreeView(roots);
    courseView.getStylesheets().add("file:src/resources/css/courseView.css");
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

  private static TreeView<String> createMultiNodeTreeView(ArrayList<TreeItem<String>> roots) {
    TreeItem<String> dummyRoot = new TreeItem<>();
    for (TreeItem<String> root : roots) {
      dummyRoot.getChildren().add(root);
    }
    TreeView<String> tree = new TreeView<>(dummyRoot);
    tree.setShowRoot(false);

    return tree;
  }

}
