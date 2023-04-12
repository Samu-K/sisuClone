package fi.tuni.prog3.sisu.backend;

import fi.tuni.prog3.sisu.backend.AutoCompleteComboBox.HideableItem;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * Main sisu class.
 */
public class Sisu extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    FXMLLoader loader = new FXMLLoader(); 
    URL stdUrl = new URL("file:src/main/java/fi/tuni/prog3/sisu/gui/studies.fxml");
    loader.setLocation(stdUrl);

    BorderPane main = loader.<BorderPane>load();

    /*
    // get the container setup in fxml
    HBox degreeCont = (HBox) main.lookup("#degreeContainer");
    // create the actual dropdown menu
    ComboBox<HideableItem<String>> degreeDropDown = setupCombo(
        new ArrayList<String>(Arrays.asList("JOKU KIVA OPINTO", "LISÄÄ OPINTOJA", "car3"))
    );
    degreeDropDown.setPromptText("Degree programme...");
    // add that menu to our container
    degreeCont.getChildren().add(degreeDropDown);
    */
    
    Scene scene = new Scene(main);
    primaryStage.setScene(scene);
    primaryStage.setTitle("SIM");
    primaryStage.show();
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
