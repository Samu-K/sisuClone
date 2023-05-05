package fi.tuni.prog3.sisu.backend;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main sisu class.
 */
public class Sisu extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {

    primaryStage.setTitle("SIM");
    new MainController(primaryStage);
  }
}
