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

    private void InsertarCliente(int id, String nombre, String direccion, String telefono) { //Metodo para insertar un cliente
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BancoCentral", "root", "#Juanbermudezp"); // Conexión a la base de datos
            PreparedStatement st = conn.prepareStatement("insert into Cliente values(?,?,?,?)"); // Prepara la declaración SQL para insertar un cliente

            st.setInt(1, id); // Establece el ID del cliente en la declaración
            st.setString(2, nombre); // Establece el nombre del cliente en la declaración
            st.setString(3, direccion); // Establece la dirección del cliente en la declaración
            st.setString(4, telefono); // Establece el teléfono del cliente en la declaración

            try {
                int results = st.executeUpdate(); // Ejecuta la declaración SQL y obtiene el número de filas afectadas
                System.out.println(results + " fila(s) afectada(s)"); // Imprime el número de filas afectadas
            } catch (SQLException e) {
                System.out.println("Error al insertar datos"); // Imprime un mensaje de error si la inserción falla
            }

        } catch (SQLException e) {
            System.out.println("Error al conectar la base de datos"); // Imprime un mensaje de error si la conexión falla
        }
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

    @FXML
    public void cargarReporteA() { // 00029823 Metodo para cargar el reporte A
        reporteTextArea.clear(); // 00029823 Limpia el área de texto del reporte
        File directory = new File("Reportes"); // 00029823 Crea un objeto File para la carpeta "Reportes"
        if (!directory.exists()) { // 00029823 Verifica si la carpeta no existe
            directory.mkdir(); // 00029823 Crea la carpeta "Reportes"
        }

        String timestamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm").format(new Date()); // 00029823 Obtiene la fecha y hora actual en el formato deseado
        String filepath = "Reportes/ReporteA_" + timestamp + ".txt"; // 00029823 Genera el nombre del archivo del reporte
        String query = "SELECT c.id AS compra_id, c.fecha, c.monto, c.descripcion " +
                "FROM Compra c " +
                "JOIN Tarjeta t ON c.tarjeta_id = t.id " +
                "JOIN Cliente cl ON t.cliente_id = cl.id " +
                "WHERE cl.id = 1 AND c.fecha BETWEEN '2024-01-01' AND '2024-12-31'"; // 00029823 Consulta SQL para obtener las compras del cliente 1 en el año 2024

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BancoCentral", "root", "#Juanbermudezp"); // 00029823 Conexión a la base de datos
             BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) { // 00029823 Crea un BufferedWriter para escribir en el archivo

            writer.write("Consulta SQL: " + query); // 00029823 Escribe la consulta SQL en el archivo
            writer.newLine(); // 00029823 Escribe una nueva línea en el archivo
            writer.newLine(); // 00029823 Escribe una nueva línea en el archivo

            Statement st = conn.createStatement(); // 00029823 Crea una declaración SQL
            ResultSet rs = st.executeQuery(query); // 00029823 Ejecuta la consulta SQL y obtiene el resultado

            StringBuilder reporteBuilder = new StringBuilder(); // 00029823 StringBuilder para construir el contenido del reporte

            while (rs.next()) { // 00029823 Itera sobre los resultados de la consulta
                int compraId = rs.getInt("compra_id"); // 00029823 Obtiene el ID de la compra
                Date fecha = rs.getDate("fecha"); // 00029823 Obtiene la fecha de la compra
                double monto = rs.getDouble("monto"); // 00029823 Obtiene el monto de la compra
                String descripcion = rs.getString("descripcion"); // 00029823 Obtiene la descripción de la compra

                String linea = String.format("ID Compra: %d, Fecha: %s, Monto: %.2f, Descripción: %s",
                        compraId, fecha, monto, descripcion); // 00029823 Formatea los datos de la compra en una línea
                writer.write(linea); // 00029823 Escribe la línea en el archivo
                writer.newLine(); // 00029823 Escribe una nueva línea en el archivo
                reporteBuilder.append(linea).append("\n"); //00029823 Añade la línea al StringBuilder
            }

            writer.close(); // 00029823 Cierra el BufferedWriter
            reporteTextArea.setText(reporteBuilder.toString()); // 00029823 Muestra el contenido del reporte en el área de texto

        } catch (SQLException e) {
            System.out.println("Fallo al conectar la base de datos"); // 00029823 Imprime un mensaje de error si la conexión falla
            e.printStackTrace(); // 00029823 Imprime la traza del error
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo"); // 00029823 Imprime un mensaje de error si la escritura en el archivo falla
            e.printStackTrace(); // 00029823 Imprime la traza del error
        }
    }

    @FXML
    public void cargarReporteB() { // 00363523 Metodo para cargar el Reporte B
        reporteTextArea.clear(); // 00363523 Limpia el área de texto del reporte
        File directory = new File("Reportes"); // 00363523 Crea un objeto File para la carpeta "Reportes"
        if (!directory.exists()) { // 00363523 Verifica si la carpeta no existe
            directory.mkdir(); // 00363523 Crea la carpeta "Reportes"
        }

        String timestamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm").format(new Date()); // 00363523 Obtiene la fecha y hora actual en el formato deseado
        String filepath = "Reportes/ReporteB_" + timestamp + ".txt"; // 00363523 Genera el nombre del archivo del reporte
        String query = "SELECT SUM(c.monto) AS total_gastado " +
                "FROM Compra c " +
                "JOIN Tarjeta t ON c.tarjeta_id = t.id " +
                "JOIN Cliente cl ON t.cliente_id = cl.id " +
                "WHERE cl.id = 1 AND MONTH(c.fecha) = 01 AND YEAR(c.fecha) = 2024"; // 00363523 Consulta SQL para obtener el total gastado por el cliente 1 en enero de 2024

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BancoCentral", "root", "#Juanbermudezp"); // 00363523 Conexión a la base de datos
             BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) { // 00363523 Crea un BufferedWriter para escribir en el archivo

            writer.write("Consulta SQL: " + query); // 00363523 Escribe la consulta SQL en el archivo
            writer.newLine(); // 00363523 Escribe una nueva línea en el archivo
            writer.newLine(); // 00363523 Escribe una nueva línea en el archivo

            Statement st = conn.createStatement(); // 00363523 Crea una declaración SQL
            ResultSet rs = st.executeQuery(query); // 00363523 Ejecuta la consulta SQL y obtiene el resultado

            StringBuilder reporteBuilder = new StringBuilder(); // 00363523 StringBuilder para construir el contenido del reporte

            if (rs.next()) { // 00363523 Verifica si hay un resultado
                double totalGastado = rs.getDouble("total_gastado"); // 00363523 Obtiene el total gastado
                String linea = String.format("Total gastado por el cliente 1 en el mes 1/2024: %.2f", totalGastado); // 00363523 Formatea los datos en una línea
                writer.write(linea); // 00363523 Escribe la línea en el archivo
                writer.newLine(); // 00363523 Escribe una nueva línea en el archivo
                reporteBuilder.append(linea).append("\n"); // 00363523 Añade la línea al StringBuilder
            }

            writer.close(); // Cierra el BufferedWriter
            reporteTextArea.setText(reporteBuilder.toString()); // 00363523 Muestra el contenido del reporte en el área de texto

        } catch (SQLException e) {
            System.out.println("Fallo al conectar la base de datos"); // 00363523 Imprime un mensaje de error si la conexión falla
            e.printStackTrace(); // Imprime la traza del error
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo"); // 00363523 Imprime un mensaje de error si la escritura en el archivo falla
            e.printStackTrace(); // Imprime la traza del error
        }
    }

    @FXML
    public void cargarReporteC() { // 00218123 Metodo para cargar el reporte C
        reporteTextArea.clear(); // 00218123 Limpia el área de texto del reporte
        File directory = new File("Reportes"); // 00218123 Crea un objeto File para la carpeta "Reportes"
        if (!directory.exists()) { // 00218123 Verifica si la carpeta no existe
            directory.mkdir(); // 00218123 Crea la carpeta "Reportes"
        }

        String timestamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm").format(new Date()); // 00218123 Obtiene la fecha y hora actual en el formato deseado
        String filepath = "Reportes/ReporteC_" + timestamp + ".txt"; // 00218123 Genera el nombre del archivo del reporte
        String query = "SELECT t.numero_tarjeta, t.tipo " +
                "FROM Tarjeta t " +
                "JOIN Cliente cl ON t.cliente_id = cl.id " +
                "WHERE cl.id = 1"; // Consulta SQL para obtener las tarjetas del cliente 1

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BancoCentral", "root", "#Juanbermudezp"); // 00218123 Conexión a la base de datos
             BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) { // 00218123 Crea un BufferedWriter para escribir en el archivo

            writer.write("Consulta SQL: " + query); // 00218123 Escribe la consulta SQL en el archivo
            writer.newLine(); // 00218123 Escribe una nueva línea en el archivo
            writer.newLine(); // 00218123 Escribe una nueva línea en el archivo

            Statement st = conn.createStatement(); // 00218123 Crea una declaración SQL
            ResultSet rs = st.executeQuery(query); // 00218123 Ejecuta la consulta SQL y obtiene el resultado

            StringBuilder reporteBuilder = new StringBuilder(); // 00218123 StringBuilder para construir el contenido del reporte
            StringBuilder tarjetasCredito = new StringBuilder(); // 00218123 StringBuilder para las tarjetas de crédito
            StringBuilder tarjetasDebito = new StringBuilder(); // 00218123 StringBuilder para las tarjetas de débito

            while (rs.next()) { // 00218123 Itera sobre los resultados de la consulta
                String numeroTarjeta = rs.getString("numero_tarjeta"); // 00218123 Obtiene el número de la tarjeta
                String tipo = rs.getString("tipo"); // 00218123 Obtiene el tipo de la tarjeta

                if (tipo.equalsIgnoreCase("Credito")) { // 00218123 Verifica si el tipo de la tarjeta es "Credito"
                    String censurada = "XXXX XXXX XXXX " + numeroTarjeta.substring(numeroTarjeta.length() - 4); // Censura el número de la tarjeta
                    tarjetasCredito.append(censurada).append("\n"); // 00218123 Añade la tarjeta censurada al StringBuilder de tarjetas de crédito
                } else {
                    tarjetasDebito.append(numeroTarjeta).append("\n"); // 00218123 Añade la tarjeta al StringBuilder de tarjetas de débito
                }
            }

            writer.write("Tarjetas de crédito:\n"); // 00218123 Escribe el título para las tarjetas de crédito en el archivo
            if (tarjetasCredito.length() > 0) {
                writer.write(tarjetasCredito.toString()); //  00218123Escribe las tarjetas de crédito en el archivo
                reporteBuilder.append("Tarjetas de crédito:\n").append(tarjetasCredito.toString()); // 00218123 Añade las tarjetas de crédito al StringBuilder del reporte
            } else {
                writer.write("N/A\n"); // 00218123 Escribe "N/A" en el archivo si no hay tarjetas de crédito
                reporteBuilder.append("Tarjetas de crédito:\nN/A\n"); // 00218123 Añade "N/A" al StringBuilder del reporte si no hay tarjetas de crédito
            }

            writer.write("\nTarjetas de Débito:\n"); // 00218123 Escribe el título para las tarjetas de débito en el archivo
            if (tarjetasDebito.length() > 0) {
                writer.write(tarjetasDebito.toString()); // 00218123 Escribe las tarjetas de débito en el archivo
                reporteBuilder.append("\nTarjetas de Débito:\n").append(tarjetasDebito.toString()); // 00218123 Añade las tarjetas de débito al StringBuilder del reporte
            } else {
                writer.write("N/A\n"); // 00218123 Escribe "N/A" en el archivo si no hay tarjetas de débito
                reporteBuilder.append("\nTarjetas de Débito:\nN/A\n"); // 00218123 Añade "N/A" al StringBuilder del reporte si no hay tarjetas de débito
            }

            writer.close(); // 00218123 Cierra el BufferedWriter
            reporteTextArea.setText(reporteBuilder.toString()); // 00218123 Muestra el contenido del reporte en el área de texto

        } catch (SQLException e) {
            System.out.println("Fallo al conectar la base de datos"); // 00218123 Imprime un mensaje de error si la conexión falla
            e.printStackTrace(); // 00218123 Imprime la traza del error
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo"); // 00218123 Imprime un mensaje de error si la escritura en el archivo falla
            e.printStackTrace(); // 00218123 Imprime la traza del error
        }
    }

    @FXML
    public void cargarReporteD() { // 00029823 Metodo para cargaqr el reporte D
        reporteTextArea.clear(); // 00029823 Limpia el área de texto del reporte
        File directory = new File("Reportes"); // 00029823 Crea un objeto File para la carpeta "Reportes"
        if (!directory.exists()) { // 00029823 Verifica si la carpeta no existe
            directory.mkdir(); // 00029823 Crea la carpeta "Reportes"
        }

        String timestamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm").format(new Date()); // 00029823 Obtiene la fecha y hora actual en el formato deseado
        String filepath = "Reportes/ReporteD_" + timestamp + ".txt"; // 00029823 Genera el nombre del archivo del reporte
        String facilitador = "Visa"; // 00029823 Define el facilitador para la consulta
        String query = "SELECT cl.id AS cliente_id, cl.nombre, COUNT(c.id) AS cantidad_compras, SUM(c.monto) AS total_gastado " +
                "FROM Cliente cl " +
                "JOIN Tarjeta t ON cl.id = t.cliente_id " +
                "JOIN Compra c ON t.id = c.tarjeta_id " +
                "WHERE t.facilitador = ? " +
                "GROUP BY cl.id, cl.nombre"; // 00029823 Consulta SQL para obtener las compras y total gastado por cliente filtrado por facilitador

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BancoCentral", "root", "#Juanbermudezp"); // 00029823 Conexión a la base de datos
             PreparedStatement pstmt = conn.prepareStatement(query); // 00029823 Prepara la declaración SQL
             BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) { // 00029823 Crea un BufferedWriter para escribir en el archivo

            pstmt.setString(1, facilitador); // 00029823 Establece el parámetro del facilitador en la declaración

            writer.write("Consulta SQL: " + query); // 00029823 Escribe la consulta SQL en el archivo
            writer.newLine(); // 00029823 Escribe una nueva línea en el archivo
            writer.write("Parámetro facilitador: " + facilitador); // 00029823 Escribe el parámetro facilitador en el archivo
            writer.newLine(); // 00029823 Escribe una nueva línea en el archivo
            writer.newLine(); // 00029823 Escribe una nueva línea en el archivo

            ResultSet rs = pstmt.executeQuery(); // 00029823 Ejecuta la consulta SQL y obtiene el resultado

            StringBuilder reporteBuilder = new StringBuilder(); // 00029823 StringBuilder para construir el contenido del reporte

            while (rs.next()) { // 00029823 Itera sobre los resultados de la consulta
                int clienteId = rs.getInt("cliente_id"); // 00029823 Obtiene el ID del cliente
                String nombre = rs.getString("nombre"); // 00029823 Obtiene el nombre del cliente
                int cantidadCompras = rs.getInt("cantidad_compras"); // 00029823 Obtiene la cantidad de compras del cliente
                double totalGastado = rs.getDouble("total_gastado"); // 00029823 Obtiene el total gastado por el cliente

                String linea = String.format("Cliente ID: %d, Nombre: %s, Cantidad de Compras: %d, Total Gastado: %.2f",
                        clienteId, nombre, cantidadCompras, totalGastado); // 00029823 Formatea los datos en una línea
                writer.write(linea); // 00029823 Escribe la línea en el archivo
                writer.newLine(); // 00029823 Escribe una nueva línea en el archivo
                reporteBuilder.append(linea).append("\n"); // 00029823 Añade la línea al StringBuilder
            }

            writer.close(); // 00029823 Cierra el BufferedWriter
            reporteTextArea.setText(reporteBuilder.toString()); // 00029823 Muestra el contenido del reporte en el área de texto

        } catch (SQLException e) {
            System.out.println("Fallo al conectar la base de datos"); // 00029823 Imprime un mensaje de error si la conexión falla
            e.printStackTrace(); // Imprime la traza del error
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo"); // 00029823 Imprime un mensaje de error si la escritura en el archivo falla
            e.printStackTrace(); // 00029823 Imprime la traza del error
        }
    }
}