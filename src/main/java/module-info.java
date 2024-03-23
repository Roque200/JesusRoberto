module com.example.jesusroberto {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.jesusroberto to javafx.fxml;
    exports com.example.jesusroberto;

    requires java.sql;
    requires mysql.connector.j;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.jesusroberto.modelos;
}