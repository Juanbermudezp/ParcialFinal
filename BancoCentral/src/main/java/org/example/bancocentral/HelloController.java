package org.example.bancocentral;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date

public class HelloController {
    @FXML
    private TextField idClienteField; // 00363823 Campo de texto para el ID del cliente
    @FXML
    private TextField nombreField; // 00363823 Campo de texto para el nombre del cliente
    @FXML
    private TextField direccionField; // 00363823 Campo de texto para la dirección del cliente
    @FXML
    private TextField telefonoField; // 00363823 Campo de texto para el teléfono del cliente
    @FXML
    private TextField idTarjetaField; // 00363823 Campo de texto para el ID de la tarjeta
    @FXML
    private TextField numTarjetaField; // 00363823 Campo de texto para el número de la tarjeta
    @FXML
    private TextField fechaExpiracionField; // 00363823 Campo de texto para la fecha de expiración de la tarjeta
    @FXML
    private ComboBox<String> tipoField; // 00363823 ComboBox para seleccionar el tipo de tarjeta
    @FXML
    private ComboBox<String> facilitadorField; // 00363823 ComboBox para seleccionar el facilitador de la tarjeta
    @FXML
    private TextField idClienteForeField; // 00363823 Campo de texto para el ID del cliente relacionado con la tarjeta
    @FXML
    private TextField idCompraField; // 00363823 Campo de texto para el ID de la compra
    @FXML
    private TextField fechaField; // 00363823 Campo de texto para la fecha de la compra
    @FXML
    private TextField montoField; // 00363823 Campo de texto para el monto de la compra
    @FXML
    private TextField descripcionField; // 00363823 Campo de texto para la descripción de la compra
    @FXML
    private TextField idTarjetaForeField; //00363823 Campo de texto para el ID de la tarjeta relacionada con la compra
    @FXML
    private Button ButtonCliente; // 00363823 Botón para añadir un cliente
    @FXML
    private Button ButtonTarjeta; // 00363823 Botón para añadir una tarjeta
    @FXML
    private Button ButtonCompra; // 00363823 Botón para añadir una compra
    @FXML
    private Button reporteA_Button; // 00363823 Botón para generar el reporte A
    @FXML
    private Button reporteB_Button; //00363823  Botón para generar el reporte B
    @FXML
    private Button reporteC_Button; // 00363823 Botón para generar el reporte C
    @FXML
    private Button reporteD_Button; // 00363823 Botón para generar el reporte D
    @FXML
    private TextArea reporteTextArea; // 00363823 Área de texto para mostrar los reportes




    private void InsertarTarjeta(int id, String numTarjeta, String fechaExpiracion, String tipo, String facilitador, int idCliente) { //Metodo para insertar una Tarjeta
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BancoCentral", "root", "#Juanbermudezp"); // Conexión a la base de datos
            PreparedStatement st = conn.prepareStatement("insert into Tarjeta values(?,?,?,?,?,?)"); // Prepara la declaración SQL para insertar una tarjeta

            st.setInt(1, id); //  00363823 Establece el ID de la tarjeta en la declaración
            st.setString(2, numTarjeta); //  00363823 Establece el número de la tarjeta en la declaración
            st.setString(3, fechaExpiracion); //  00363823 Establece la fecha de expiración de la tarjeta en la declaración
            st.setString(4, tipo); //  00363823 Establece el tipo de la tarjeta en la declaración
            st.setString(5, facilitador); //  00363823 Establece el facilitador de la tarjeta en la declaración
            st.setInt(6, idCliente); //  00363823 Establece el ID del cliente relacionado en la declaración

            try {
                int results = st.executeUpdate(); //  00363823 Ejecuta la declaración SQL y obtiene el número de filas afectadas
                System.out.println(results + " fila(s) afectada(s)"); // 00363823  Imprime el número de filas afectadas
            } catch (SQLException e) {
                System.out.println("Error al insertar datos"); //  00363823 Imprime un mensaje de error si la inserción falla
            }

        } catch (SQLException e) {
            System.out.println("Error al conectar la base de datos"); //  00363823 Imprime un mensaje de error si la conexión falla
        }
    }
}