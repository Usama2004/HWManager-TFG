package com.hlanz.hwmanagertfg.Controllers;

import com.hlanz.hwmanagertfg.Clases.DatabaseConnection;
import com.hlanz.hwmanagertfg.Main.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

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
        // Crear un efecto de sombra
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.rgb(0, 0, 0, 0.4));
        dropShadow.setRadius(5);
        dropShadow.setOffsetX(0.5);
        dropShadow.setOffsetY(0.5);

        DropShadow dropShadow2 = new DropShadow();
        dropShadow2.setColor(Color.rgb(0, 0, 0, 0.9));
        dropShadow2.setRadius(5);
        dropShadow2.setOffsetX(0.5);
        dropShadow2.setOffsetY(0.5);

        VBox tarea = new VBox();
        tarea.setSpacing(10);
        tarea.setStyle("-fx-border-color: #d3d3d3; -fx-background-color: #ffffff; -fx-padding: 15; -fx-border-radius: 10; -fx-background-radius: 10; -fx-pref-width: 500;");
        tarea.setEffect(dropShadow); // Aplicar sombra al contenedor principal

        VBox nombreContainer = createLabelContainer("Nombre: " + nombre, "-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: #333333;", dropShadow, true);
        VBox descripcionContainer = createLabelContainer("Descripción: " + descripcion, "-fx-font-size: 12px; -fx-text-fill: #666666;", dropShadow, false);
        VBox fechaContainer = createLabelContainer("Fecha: " + fecha, "-fx-font-size: 12px; -fx-text-fill: #666666;", dropShadow, false);
        VBox horaContainer = createLabelContainer("Hora: " + hora, "-fx-font-size: 12px; -fx-text-fill: #666666;", dropShadow, false);
        VBox prioridadContainer = createLabelContainer("Prioridad: " + prioridad, "-fx-font-size: 12px; -fx-text-fill: #666666;", dropShadow, false);
        VBox estadoContainer = createLabelContainer("Estado: " + estado, "-fx-font-size: 12px; -fx-text-fill: #666666;", dropShadow, false);

        // Crear botones
        Button pendienteButton = new Button("Pendiente");
        pendienteButton.setEffect(dropShadow2);
        pendienteButton.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white; -fx-border-radius: 5;");
        pendienteButton.setPrefWidth(100);

        Button editarButton = new Button("Editar");
        editarButton.setEffect(dropShadow2);
        editarButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-border-radius: 5;");
        editarButton.setPrefWidth(100);

        Button borrarButton = new Button("Borrar");
        borrarButton.setEffect(dropShadow2);
        borrarButton.setStyle("-fx-background-color: #F44336; -fx-text-fill: white; -fx-border-radius: 5;");
        borrarButton.setPrefWidth(100);

        HBox buttonsContainer = new HBox(10, pendienteButton, editarButton, borrarButton);
        buttonsContainer.setAlignment(Pos.CENTER);

        tarea.getChildren().addAll(nombreContainer, descripcionContainer, fechaContainer, horaContainer, prioridadContainer, estadoContainer, buttonsContainer);

        // Añadir margen entre tareas
        VBox.setMargin(tarea, new Insets(10, 0, 10, 0));

        vbox_tareas.getChildren().add(tarea);

        // Aplicar estilos al VBox principal sin bordes redondeados
        vbox_tareas.setStyle("-fx-background-color: #ffffff; -fx-padding: 20;");
    }

    private VBox createLabelContainer(String text, String style, DropShadow dropShadow, boolean centerText) {
        Label label = new Label(text);
        label.setMaxWidth(Double.MAX_VALUE);
        label.setWrapText(true); // Permitir que el texto se ajuste
        label.setStyle(style);

        if (centerText) {
            label.setAlignment(Pos.CENTER); // Centrar el texto
            label.setTextAlignment(TextAlignment.CENTER); // Asegurar que el texto esté centrado
        }

        VBox container = new VBox(label);
        container.setStyle("-fx-background-color: #ffffff; -fx-padding: 5; -fx-border-radius: 3; -fx-background-radius: 3;");
        container.setEffect(dropShadow); // Aplicar sombra

        return container;
    }
}
