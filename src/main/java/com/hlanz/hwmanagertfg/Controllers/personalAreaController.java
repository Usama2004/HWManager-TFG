package com.hlanz.hwmanagertfg.Controllers;

import com.hlanz.hwmanagertfg.Clases.DatabaseConnection;
import com.hlanz.hwmanagertfg.Main.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class personalAreaController implements Initializable {

    private Main mainApp; // Referencia a la clase principal MainApp
    @FXML
    private CheckBox checkbox_completado;

    @FXML
    private CheckBox checkbox_pendiente;

    @FXML
    private ChoiceBox<String> choicebox_categoria;

    @FXML
    private DatePicker datepicker_fecha;

    @FXML
    private VBox vbox_tareas;

    @FXML
    void boton_crearPulsado() {
        
    }

    //Método para establecer la referencia a MainApp
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        // Añadir categorías al ChoiceBox
        choicebox_categoria.getItems().addAll(
                "Trabajo", "Estudios", "Salud", "Hogar", "Finanzas", "Ocio",
                "Viajes", "Compras", "Eventos", "Familia", "Amigos", "Proyectos",
                "Deportes", "Tecnología", "Creatividad", "Voluntariado", "Autoayuda",
                "Educación", "Negocios", "Hobbies"
        );
        // Cargar tareas desde la base de datos
        cargarTareas();
    }

    public void cargarTareas() {
        vbox_tareas.getChildren().clear();
        String query = "SELECT Nombre_tarea, Descripcion, Fecha, Hora, Prioridad, Estado FROM Recordatorio";
        int contadorTareas = 0;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String nombre = resultSet.getString("Nombre_tarea");
                String descripcion = resultSet.getString("Descripcion");
                String fecha = resultSet.getDate("Fecha").toString();
                String hora = resultSet.getTime("Hora").toString();
                String prioridad = resultSet.getString("Prioridad");
                String estado = resultSet.getString("Estado");

                this.agregarTarea(nombre, descripcion, fecha, hora, prioridad, estado);
                contadorTareas++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Imprimir el número de tareas añadidas por consola
        System.out.println("Número de tareas añadidas: " + contadorTareas);
    }

    private void agregarTarea(String nombre, String descripcion, String fecha, String hora, String prioridad, String estado) {
        HBox tarea = new HBox();
        tarea.setSpacing(10);
        tarea.setStyle("-fx-border-color: black; -fx-padding: 10;");

        Label nombreLabel = new Label("Nombre: " + nombre);
        Label descripcionLabel = new Label("Descripción: " + descripcion);
        Label fechaLabel = new Label("Fecha: " + fecha);
        Label horaLabel = new Label("Hora: " + hora);
        Label prioridadLabel = new Label("Prioridad: " + prioridad);
        Label estadoLabel = new Label("Estado: " + estado);

        tarea.getChildren().addAll(nombreLabel, descripcionLabel, fechaLabel, horaLabel, prioridadLabel, estadoLabel);
        vbox_tareas.getChildren().add(tarea);
    }
}
