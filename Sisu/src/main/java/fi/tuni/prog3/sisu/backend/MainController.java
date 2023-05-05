package fi.tuni.prog3.sisu.backend;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Controller for the main view. This controller stores the primary stage. Other classes change
 * rootLayout to change views.
 */
public class MainController {

  private Stage primaryStage;
  private BorderPane rootLayout;

  /**
   * Constructor. Initializes the root layout and shows the login page.

   * @param primaryStage the primary stage
   */
  public MainController(Stage primaryStage) {
    this.primaryStage = primaryStage;
    initRootLayout();
    showLoginPage();
  }

  private void initRootLayout() {
    rootLayout = new BorderPane();
    Scene scene = new Scene(rootLayout);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  /**
   * Shows the login view.
   */
  public void showLoginPage() {
    try {
      // Set scene size
      primaryStage.getScene().getWindow().setWidth(330);
      primaryStage.getScene().getWindow().setHeight(650);

      // Load login page
      FXMLLoader loader =
          new FXMLLoader(getClass().getResource("/fi/tuni/prog3/sisu/gui/start.fxml"));
      Pane loginPage = loader.load();

      StartController startController = loader.getController();
      startController.setMainController(this);

      rootLayout.setCenter(loginPage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Switches to the main view. Sets the sidebar to the left and the landing page to the center.
   */
  public void showMainView() {
    try {

      // Set scene size
      primaryStage.getScene().getWindow().setWidth(1000);
      primaryStage.getScene().getWindow().setHeight(650);

      // Load sidebar
      FXMLLoader sidebarLoader = 
          new FXMLLoader(getClass().getResource("/fi/tuni/prog3/sisu/gui/sidebar.fxml"));
      Pane sidebar = sidebarLoader.load();

      // Set sidebar to the left of the root layout
      rootLayout.setLeft(sidebar);

      // Set the main controller in the sidebar controller
      SidebarController sidebarController = sidebarLoader.getController();
      sidebarController.setMainController(this);

      // Load landing page to the center of the root layout
      sidebarController.showLanding();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Sets the center of the root layout. This method is used to change pages after logging in.

   * @param pane the pane to be set as the center
   */
  public void setCenter(Pane pane) {
    rootLayout.setCenter(pane);
  }
}