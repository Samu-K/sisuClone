package fi.tuni.prog3.sisu.backend;

import fi.tuni.prog3.sisu.backend.AutoCompleteComboBox.HideableItem;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
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
    URL url = new URL("file:src/main/java/fi/tuni/prog3/sisu/gui/studies.fxml");
    loader.setLocation(url);
    BorderPane main = loader.<BorderPane>load(); 
    setupCombo(main);

    Scene scene = new Scene(main);

    primaryStage.setScene(scene);
    primaryStage.setTitle("SIM");
    primaryStage.show();
  }

  private static void setupCombo(BorderPane main) {
    ComboBox<HideableItem<String>> dropDown;

    String[] programmes = {"1", "12", "132"};
    List<String> locales = Arrays.asList(programmes);

    dropDown = AutoCompleteComboBox.createComboBoxWithAutoCompletionSupport(
        locales, 
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
          String foundString = locales.stream().filter((String i)
                          -> (i).equals(string)).findFirst().orElse(null);
          return new HideableItem<>(foundString, this);

        }
        
      });
      
    main.setCenter(dropDown);
    main.setAlignment(dropDown, Pos.TOP_LEFT);
    main.setMargin(dropDown, new Insets(12, 12, 12, 12));
  }
}
