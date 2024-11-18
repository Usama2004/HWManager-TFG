package com.hlanz.hwmanagertfg.Controllers;

import com.hlanz.hwmanagertfg.Main.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class registerController implements Initializable {
    //Referencia a la clase principal Main
    private Main mainApp;
    //Datos para conectarnos a la base de datos
    private static final String URL = "jdbc:mariadb://localhost:3306/hwmanager";
    private static final String USER = "root";
    private static final String PASSWORD = "Edahabi2004";

    @FXML
    private TextField textField_apellidos;

    @FXML
    private TextField textField_gmail;

    @FXML
    private TextField textField_nombre;

    @FXML
    private TextField textField_password;

    @FXML
    private TextField textField_repetirPassword;

    @FXML
    private TextField textField_telefono;

    @FXML
    private TextField textField_usuario;

    @FXML
    void botonRegistrar_pulsado() {
        String nombre = textField_nombre.getText();
        String apellidos = textField_apellidos.getText();
        String gmail = textField_gmail.getText();
        String telefono = textField_telefono.getText();
        String usuario = textField_usuario.getText();
        String password = textField_password.getText();
        String retypePassword = textField_repetirPassword.getText();

        if (!nombre.isEmpty() && !apellidos.isEmpty() && !gmail.isEmpty() && !telefono.isEmpty()
            && !usuario.isEmpty() && !password.isEmpty() && !retypePassword.isEmpty() &&
            password.equals(retypePassword)) {
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                //Si todos los campos estan rellenados y la contraseña esta bien se hace el INSERT.
                String query = "INSERT INTO Usuario (Usuario, Contrasenia, Nombre, Apellidos, Gmail, Telefono) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, usuario);
                statement.setString(2, password);
                statement.setString(3, nombre);
                statement.setString(4, apellidos);
                statement.setString(5, gmail);
                statement.setString(6, telefono);

                int rowsInserted = statement.executeUpdate();

                if (rowsInserted > 0) {
                    mainApp.showLoginView(); // Volver a la vista de login
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Registro exitoso");
                    alert.setHeaderText("Usuario registrado correctamente");
                    alert.setContentText("El usuario ha sido registrado exitosamente.");
                    alert.showAndWait();
                }
            }catch (SQLException e) {
                // Error de conexión, mostrar una alerta
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error de conexión");
                alert.setHeaderText("No se pudo conectar a la base de datos");
                alert.setContentText("Por favor, verifica tu conexión e intenta nuevamente.");
                alert.showAndWait();
                e.printStackTrace();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de registro");
            alert.setHeaderText("No se pudo registrar un nuevo usuario");
            alert.setContentText("Por favor, verifica que todos los campos estan rellanados \n" +
                    "y que los 2 campos de contraseña sean iguales");
            alert.showAndWait();
        }
    }

    //Método para establecer la referencia a MainApp
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        this.textField_nombre.clear();
        this.textField_apellidos.clear();
        this.textField_gmail.clear();
        this.textField_telefono.clear();
        this.textField_usuario.clear();
        this.textField_password.clear();
        this.textField_repetirPassword.clear();
    }
}
