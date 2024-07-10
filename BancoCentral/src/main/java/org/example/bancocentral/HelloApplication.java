package org.example.bancocentral;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException { // 00029823 Método start que se ejecuta al iniciar la aplicación
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml")); // 00029823 Carga el archivo FXML que define la interfaz de usuario
        Scene scene = new Scene(fxmlLoader.load(), 320, 240); // 00029823 Crea una nueva escena con el contenido del archivo FXML y establece su tamaño
        stage.setTitle("Gestion de Clientes"); // 00029823 Establece el título de la ventana
        stage.setScene(scene); // 00029823 Establece la escena en el escenario
        stage.show(); // 00029823 Muestra la ventana
    }

    public static void main(String[] args) {
        launch(); // 00029823 Ejecuta la aplicación JavaFX
    }
}