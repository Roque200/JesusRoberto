module com.example.jesusroberto {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.jesusroberto to javafx.fxml;
    exports com.example.jesusroberto;
}