module fi.tuni.prog3.sisu {
    requires javafx.controls;
    requires org.controlsfx.controls;
    requires transitive javafx.graphics;
    requires transitive javafx.fxml;
    requires transitive com.google.gson;
    requires transitive org.apache.commons.lang3;

    opens fi.tuni.prog3.sisu.backend to javafx.fxml;
    exports fi.tuni.prog3.sisu.backend;
    exports fi.tuni.prog3.sisu.api;
}


    

