package com.hlanz.hwmanagertfg.Controllers;

import com.hlanz.hwmanagertfg.Clases.DatabaseConnection;
import com.hlanz.hwmanagertfg.Clases.UserSession;
import com.hlanz.hwmanagertfg.Main.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.*;
import java.util.ResourceBundle;

public class loginController implements Initializable {
    // Referencia a la clase principal Main
    private Main mainApp;
    private String txt_usuario;
    private String txt_password;

    @FXML
    private ImageView img_login;

    @FXML
    private ImageView img_ordenador;

    @FXML
    private VBox recuadro_login;

    @FXML
    private PasswordField textField_password;

    @FXML
    private TextField textField_user;

    @FXML
    private VBox vbox_principal_login;

    @FXML
    private HBox vbox_secundario_login;


    @FXML
    void button_login_pulsado() {
        String txt_usuario = textField_user.getText();
        String txt_password = textField_password.getText();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM Usuario WHERE Usuario = ? AND Contrasenia = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, txt_usuario);
            statement.setString(2, txt_password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                //Si las credenciales son correctas, guardamos el ID del usuario
                int userId = resultSet.getInt("ID_usuario");
                //UserSession es una clase Singleton que he creado la cual me va a servir
                //para mostrar siempre la información correspondiente en base al usuario.
                //cuando estemos navegando entre ventanas. Modificamos el ID y el Nombre
                //de la una instancia que hay de UserSession.
                UserSession.getInstance().setUserId(userId);
                UserSession.getInstance().setUsername(txt_usuario);
                System.out.println("ID: "+UserSession.getInstance().getUserId());
                System.out.println("Usuario: "+UserSession.getInstance().getUsername());

                // Mostrar la vista de área personal
                mainApp.showPersonalAreaView();
            } else {
                // Credenciales incorrectas, mostrar una alerta
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error de autenticación");
                alert.setHeaderText("Usuario o contraseña incorrectos");
                alert.setContentText("Por favor, verifica tus credenciales e intenta nuevamente.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            // Error de conexión, mostrar una alerta
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de conexión");
            alert.setHeaderText("No se pudo conectar a la base de datos");
            alert.setContentText("Por favor, verifica tu conexión e intenta nuevamente.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    void button_register_pulsado() {
        mainApp.showRegisterView();
    }

    // Método para establecer la referencia a MainApp
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        this.textField_user.clear();
        this.textField_password.clear();
    }
}
