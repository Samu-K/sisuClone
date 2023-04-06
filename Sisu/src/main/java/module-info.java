module fi.tuni.prog3.sisu {
    requires javafx.controls;
    requires transitive javafx.graphics;
    exports fi.tuni.prog3.sisu.backend;
    exports fi.tuni.prog3.sisu.api;
    requires transitive com.google.gson;
}
