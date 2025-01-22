module borderGuessr {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;

    exports GUI;
    opens GUI to javafx.fxml;
    exports algorithms.bestFirstSearch;
    opens algorithms.bestFirstSearch to javafx.fxml;
    exports algorithms.minimax;
    opens algorithms.minimax to javafx.fxml;
    exports algorithms.uninformedSearch;
    opens algorithms.uninformedSearch to javafx.fxml;
    exports game;
    opens game to javafx.fxml;
}