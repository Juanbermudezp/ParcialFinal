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
    private void InsertarTarjeta(int id, String numTarjeta, String fechaExpiracion, String tipo, String facilitador, int idCliente) { // 00363823 Metodo para insertar una Tarjeta
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BancoCentral", "root", "#Juanbermudezp"); // 00363823 Conexión a la base de datos
            PreparedStatement st = conn.prepareStatement("insert into Tarjeta values(?,?,?,?,?,?)"); // 00363823 Prepara la declaración SQL para insertar una tarjeta

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


    @FXML

    private void InsertarCompra(int id, String fecha, double monto, String descripcion, int idTarjeta) { // 00218123 Metodo para insertar una compra
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BancoCentral", "root", "#Juanbermudezp"); // 00218123 Conexión a la base de datos
            PreparedStatement st = conn.prepareStatement("insert into Compra values(?,?,?,?,?)"); // 00218123 Prepara la declaración SQL para insertar una compra

            st.setInt(1, id); // 00218123 Establece el ID de la compra en la declaración
            st.setString(2, fecha); // 00218123 Establece la fecha de la compra en la declaración
            st.setDouble(3, monto); // 00218123 Establece el monto de la compra en la declaración
            st.setString(4, descripcion); // 00218123 Establece la descripción de la compra en la declaración
            st.setInt(5, idTarjeta); // 00218123 Establece el ID de la tarjeta relacionada en la declaración

            try {
                int results = st.executeUpdate(); // 00218123 Ejecuta la declaración SQL y obtiene el número de filas afectadas
                System.out.println(results + " fila(s) afectada(s)"); // 00218123 Imprime el número de filas afectadas
            } catch (SQLException e) {
                System.out.println("Error al insertar datos"); // 00218123 Imprime un mensaje de error si la inserción falla
            }

        } catch (SQLException e) {
            System.out.println("Error al conectar la base de datos"); // 00218123 Imprime un mensaje de error si la conexión falla
        }
    }
  
  public void cargarReporteB() { // 00363823 Metodo para cargar el Reporte B
        reporteTextArea.clear(); // 00363823 Limpia el área de texto del reporte
        File directory = new File("Reportes"); // 00363823 Crea un objeto File para la carpeta "Reportes"
        if (!directory.exists()) { // 00363823 Verifica si la carpeta no existe
            directory.mkdir(); // 00363823 Crea la carpeta "Reportes"
        }

        String timestamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm").format(new Date()); // 00363823 Obtiene la fecha y hora actual en el formato deseado
        String filepath = "Reportes/ReporteB_" + timestamp + ".txt"; //00363823  Genera el nombre del archivo del reporte
        String query = "SELECT SUM(c.monto) AS total_gastado " +
                "FROM Compra c " +
                "JOIN Tarjeta t ON c.tarjeta_id = t.id " +
                "JOIN Cliente cl ON t.cliente_id = cl.id " +
                "WHERE cl.id = 1 AND MONTH(c.fecha) = 01 AND YEAR(c.fecha) = 2024"; // 00363823 Consulta SQL para obtener el total gastado por el cliente 1 en enero de 2024

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BancoCentral", "root", "#Juanbermudezp"); // 00363823 Conexión a la base de datos
             BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) { // 00363823 Crea un BufferedWriter para escribir en el archivo

            writer.write("Consulta SQL: " + query); // 00363823 Escribe la consulta SQL en el archivo
            writer.newLine(); // 00363823 Escribe una nueva línea en el archivo
            writer.newLine(); //00363823  Escribe una nueva línea en el archivo

            Statement st = conn.createStatement(); // 00363823 Crea una declaración SQL
            ResultSet rs = st.executeQuery(query); // 00363823 Ejecuta la consulta SQL y obtiene el resultado

            StringBuilder reporteBuilder = new StringBuilder(); //00363823  StringBuilder para construir el contenido del reporte

            if (rs.next()) { // 00363823 Verifica si hay un resultado
                double totalGastado = rs.getDouble("total_gastado"); //  00363823 Obtiene el total gastado
                String linea = String.format("Total gastado por el cliente 1 en el mes 1/2024: %.2f", totalGastado); // Formatea los datos en una línea
                writer.write(linea); //  00363823 Escribe la línea en el archivo
                writer.newLine(); //  00363823 Escribe una nueva línea en el archivo
                reporteBuilder.append(linea).append("\n"); // 00363823  Añade la línea al StringBuilder
            }

            writer.close(); // 00363823 Cierra el BufferedWriter
            reporteTextArea.setText(reporteBuilder.toString()); //  00363823 Muestra el contenido del reporte en el área de texto

        } catch (SQLException e) {
            System.out.println("Fallo al conectar la base de datos"); //  00363823 Imprime un mensaje de error si la conexión falla
            e.printStackTrace(); // 00363823  Imprime la traza del error
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo"); //  00363823 Imprime un mensaje de error si la escritura en el archivo falla
            e.printStackTrace(); //  00363823 Imprime la traza del error
}