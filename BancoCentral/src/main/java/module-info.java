module org.example.bancocentral {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.bancocentral to javafx.fxml;
    exports org.example.bancocentral;
}