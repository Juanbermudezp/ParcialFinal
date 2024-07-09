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
import java.util.Date;

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
    @FXML
    public void initialize() {
        if (tipoField.getItems().isEmpty()) { // 00218123 Verifica si el ComboBox de tipo está vacío
            tipoField.getItems().addAll("Credito", "Debito"); // 00218123 Añade las opciones al ComboBox
        }

        if (facilitadorField.getItems().isEmpty()) { // 00218123 Verifica si el ComboBox de facilitador está vacío
            facilitadorField.getItems().addAll("Visa", "MasterCard", "American Express"); // 00218123 Añade las opciones al ComboBox
        }

        ButtonCliente.setOnAction(event -> { // 00218123 Configura la acción para el botón de añadir cliente
            int idCliente = Integer.parseInt(idClienteField.getText()); // 00218123 Obtiene el ID del cliente del campo de texto
            String nombre = nombreField.getText(); // 00218123 Obtiene el nombre del cliente del campo de texto
            String direccion = direccionField.getText(); // 00218123 Obtiene la dirección del cliente del campo de texto
            String telefono = telefonoField.getText(); // 00218123 Obtiene el teléfono del cliente del campo de texto
            InsertarCliente(idCliente, nombre, direccion, telefono); // 00218123 Llama al método para insertar el cliente en la base de datos
            idClienteField.clear(); // 00218123 Limpia el campo de texto del ID del cliente
            nombreField.clear(); // 00218123 Limpia el campo de texto del nombre del cliente
            direccionField.clear(); // 00218123 Limpia el campo de texto de la dirección del cliente
            telefonoField.clear(); // 00218123 Limpia el campo de texto del teléfono del cliente
        });

        ButtonTarjeta.setOnAction(event -> { // 00218123 Configura la acción para el botón de añadir tarjeta
            int idTarjeta = Integer.parseInt(idTarjetaField.getText()); // 00218123 Obtiene el ID de la tarjeta del campo de texto
            String numTarjeta = numTarjetaField.getText(); // 00218123 Obtiene el número de la tarjeta del campo de texto
            String fechaExpiracion = fechaExpiracionField.getText(); // 00218123 Obtiene la fecha de expiración de la tarjeta del campo de texto
            String tipo = tipoField.getValue(); // 00218123 Obtiene el tipo de la tarjeta del ComboBox
            String facilitador = facilitadorField.getValue(); // 00218123 Obtiene el facilitador de la tarjeta del ComboBox
            int idForeCliente = Integer.parseInt(idClienteForeField.getText()); // 00218123 Obtiene el ID del cliente relacionado del campo de texto
            InsertarTarjeta(idTarjeta, numTarjeta, fechaExpiracion, tipo, facilitador, idForeCliente); // 00218123 Llama al método para insertar la tarjeta en la base de datos
            idTarjetaField.clear(); // 00218123 Limpia el campo de texto del ID de la tarjeta
            numTarjetaField.clear(); // 00218123 Limpia el campo de texto del número de la tarjeta
            fechaExpiracionField.clear(); // 00218123 Limpia el campo de texto de la fecha de expiración de la tarjeta
            tipoField.setValue(null); // 00218123 Limpia el ComboBox del tipo de tarjeta
            facilitadorField.setValue(null); // 00218123 Limpia el ComboBox del facilitador de la tarjeta
            idClienteForeField.clear(); // 00218123 Limpia el campo de texto del ID del cliente relacionado
        });

        ButtonCompra.setOnAction(event -> { // 00218123 Configura la acción para el botón de añadir compra
            int idCompra = Integer.parseInt(idCompraField.getText()); // 00218123 Obtiene el ID de la compra del campo de texto
            String fecha = fechaField.getText(); // 00218123 Obtiene la fecha de la compra del campo de texto
            double monto = Double.parseDouble(montoField.getText()); // 00218123 Obtiene el monto de la compra del campo de texto
            String descripcion = descripcionField.getText(); // 00218123 Obtiene la descripción de la compra del campo de texto
            int idForeTarjeta = Integer.parseInt(idTarjetaForeField.getText()); // 00218123 Obtiene el ID de la tarjeta relacionada del campo de texto
            InsertarCompra(idCompra, fecha, monto, descripcion, idForeTarjeta); // 00218123 Llama al método para insertar la compra en la base de datos
            idCompraField.clear(); // 00218123 Limpia el campo de texto del ID de la compra
            fechaField.clear(); // 00218123 Limpia el campo de texto de la fecha de la compra
            montoField.clear(); // 00218123 Limpia el campo de texto del monto de la compra
            descripcionField.clear(); // 00218123 Limpia el campo de texto de la descripción de la compra
            idTarjetaForeField.clear(); // 00218123 Limpia el campo de texto del ID de la tarjeta relacionada
        });
    }
}