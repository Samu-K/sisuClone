module fi.tuni.prog3.sisu {
    requires javafx.controls;
    requires transitive javafx.graphics;
    requires transitive javafx.fxml;
    requires transitive com.google.gson;

    exports fi.tuni.prog3.sisu.api;
    exports fi.tuni.prog3.sisu.backend;
    //exports fi.tuni.prog3.sisu.gui;

}
