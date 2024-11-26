package com.hlanz.hwmanagertfg.Controllers;

import com.hlanz.hwmanagertfg.Clases.DatabaseConnection;
import com.hlanz.hwmanagertfg.Main.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.*;
import java.util.ResourceBundle;

public class registerController implements Initializable {
    //Referencia a la clase principal Main
    private Main mainApp;
    private loginController loginController;

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

            // Validar formato del correo electrónico
            if (gmail.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                // Validar formato del teléfono
                if (telefono.matches("^[0-9]{7,15}$")) {
                    try (Connection connection = DatabaseConnection.getConnection()) {
                        // Comprobar que el usuario es único
                        String checkUserQuery = "SELECT COUNT(*) FROM Usuario WHERE Usuario = ?";
                        PreparedStatement checkUserStatement = connection.prepareStatement(checkUserQuery);
                        checkUserStatement.setString(1, usuario);
                        ResultSet resultSet = checkUserStatement.executeQuery();
                        resultSet.next();
                        if (resultSet.getInt(1) == 0) {
                            // Si el usuario es único, se procede con el registro
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

                                this.textField_nombre.clear();
                                this.textField_apellidos.clear();
                                this.textField_gmail.clear();
                                this.textField_telefono.clear();
                                this.textField_usuario.clear();
                                this.textField_password.clear();
                                this.textField_repetirPassword.clear();
                            }
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error de registro");
                            alert.setHeaderText("Usuario ya existe");
                            alert.setContentText("El nombre de usuario ya está en uso. Por favor, elige otro.");
                            alert.showAndWait();
                        }
                    } catch (SQLException e) {
                        // Error de conexión, mostrar una alerta
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error de conexión");
                        alert.setHeaderText("No se pudo conectar a la base de datos");
                        alert.setContentText("Por favor, inténtalo de nuevo más tarde.");
                        alert.showAndWait();
                        e.printStackTrace();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error de validación");
                    alert.setHeaderText("Teléfono no válido");
                    alert.setContentText("Por favor, introduce un número de teléfono válido (7-15 dígitos).");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error de validación");
                alert.setHeaderText("Correo electrónico no válido");
                alert.setContentText("Por favor, introduce un correo electrónico válido.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de validación");
            alert.setHeaderText("Campos incompletos o contraseñas no coinciden");
            alert.setContentText("Por favor, rellena todos los campos y asegúrate de que las contraseñas coinciden.");
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
