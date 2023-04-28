package fi.tuni.prog3.sisu.backend;

import fi.tuni.prog3.sisu.api.Interface;
import fi.tuni.prog3.sisu.backend.AutoCompleteComboBox.HideableItem;
import java.util.ArrayList;
import java.util.TreeMap;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;


/**
 * Controller class for studies.fxml. If you need to choose the degreeProgramme again, set 
 * selectedDegree to null.
 */
public class StudiesController {

  @FXML private HBox degreeCont;
  @FXML private VBox courseBox;

  // Stores all degree programme names and ids, used to populate the dropdown menu
  private static TreeMap<String, String> degreeProgrammes;
  // Stores the selected degree programme as a StudyModule object
  private static StudyModule selectedDegree;
  // The treeview that displays the courses
  private static TreeView<String> courseTreeObject;
  // shorthand to make handling combobox mouse events easier.
  private EventHandler<MouseEvent> cboxMouseEventHandler;

  /**
   * Initialization function for studies.fxml. 
   * This gets ran whenever studies.fxml is loaded.
   */
  @FXML
  private void initialize() {


    // Get the degree programmes for the dropdown from the API if we don't have them already
    if (degreeProgrammes == null) {
      degreeProgrammes = Interface.getDegreeProgrammeNames();
    }

    // If we don't have a selected degree, create the dropdown menu to select one
    if (selectedDegree == null) {

      // create the actual dropdown menu
      ComboBox<HideableItem<String>> degreeDropDown = 
          setupCombo(new ArrayList<String>(degreeProgrammes.keySet()));

      degreeDropDown.setPromptText("Search for Degree Programmes...");

      // add that menu to our container
      degreeCont.getChildren().add(degreeDropDown);

      // listens for a value selection on the combobox
      degreeDropDown.sceneProperty().addListener((a, oldScene, newScene) -> {
        if (newScene == null || cboxMouseEventHandler != null) {
          return;
        }
        ListView<?> listView = (ListView<?>) degreeDropDown.lookup(".list-view");

        if (listView != null) {
          cboxMouseEventHandler = (e) -> {
            Platform.runLater(() -> {
              // find the chosen degree
              HideableItem<String> selectedValue = 
                  (HideableItem<String>) listView.getSelectionModel().getSelectedItem();

              // save the chosen degree to selectedDegree
              String degreeName = selectedValue.toString();
              String degreeId = degreeProgrammes.get(degreeName);
              selectedDegree = (StudyModule) ObjectBuilders.buildCourseOrStudyModule(degreeId);
              
              showCourseTree();
            });
          };
          listView.addEventFilter(MouseEvent.MOUSE_PRESSED, cboxMouseEventHandler); 
        } 
      });
    } 

    // If we run else, we already have a selected degree, so we can just show the course tree
    else {
      showCourseTree();
    }

  }

  private void showCourseTree() {

    // create our course tree based on the degree selected (tba)
    if (courseTreeObject == null) {
      courseTreeObject = createCourseTree(selectedDegree.getId());
    }

    // remove the combobox
    courseBox.getChildren().clear();
    courseBox.getChildren().add(courseTreeObject);
    Label degree = new Label(selectedDegree.getName());
    degree.setStyle("-fx-font-size:18;"
            + "-fx-text-fill:white;"
            + "-fx-padding: 25 0 0 25;");
    courseBox.getChildren().add(0, degree);
  }
  
  /**
   * Set up our autofillable combobox.
   *
   * @param items the items that we want to fill our combobox with
   * @return a filled out autocomplete combobox
   */
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

  /**
   * Create a treeview with multiple root nodes.
   *
   * @param roots an arraylist filled with treeitems, that work as root nodes
   * @return a treeview with each given treeitem as a root node
   */
  private static TreeView<String> createMultiNodeTreeView(ArrayList<TreeItem<String>> roots) {
    TreeItem<String> dummyRoot = new TreeItem<>();
    for (TreeItem<String> root : roots) {
      dummyRoot.getChildren().add(root);
    }
    TreeView<String> tree = new TreeView<>(dummyRoot);
    tree.setShowRoot(false);

    return tree;
  }

  /**
   * Get the group id of the degree programme that was selected. Clears the degreeProgrammes map
   * because it is not used anymore in normal use.
   *
   * @param name the name of the degree programme
   * @return the group id of the degree programme
   */
  public static String getDegreeProgrammeGroupId(String name) {
    String groupId = degreeProgrammes.get(name);
    degreeProgrammes.clear();
    return groupId;
  }

  /**
   * Create the treeview that containes all courses for the chosen degree.
   *
   * @param degreeId the group id of the degree programme to be shown
   * @return a filled out and styled treeview
   */
  private static TreeView<String> createCourseTree(String degreeId) {
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
    TreeView<String> courseViewToReturn = createMultiNodeTreeView(roots);
    courseViewToReturn.getStylesheets().add("file:src/resources/css/courseView.css");

    return courseViewToReturn;
  }

}


