module org.example.bancocentral {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.bancocentral to javafx.fxml;
    exports org.example.bancocentral;
}