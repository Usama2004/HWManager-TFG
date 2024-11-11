package com.hlanz.hwmanagertfg;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    private StackPane rootPane; // El StackPane principal que contendrá todas las vistas

    @Override
    public void start(Stage primaryStage) throws Exception {
        rootPane = new StackPane(); // Inicializar el StackPane

        // Cargar las vistas desde los archivos FXML
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("loginView.fxml"));
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("registerView.fxml"));
        FXMLLoader loader3 = new FXMLLoader(getClass().getResource("personalAreaView.fxml"));
        FXMLLoader loader4 = new FXMLLoader(getClass().getResource("createPersonalAreaView.fxml"));

        // Cargar las vistas y verificar los tipos
        Node view1 = loader1.load();
        Node view2 = loader2.load();
        Node view3 = loader3.load();
        Node view4 = loader4.load();
        // Añadir las vistas al StackPane
        rootPane.getChildren().addAll(view1, view2, view3, view4);

        // Obtener los controladores de las vistas
        loginController controller1 = loader1.getController();
        controller1.setMainApp(this); // Pasar la referencia de MainApp al controlador de la vista 1

        registerController controller2 = loader2.getController();
        controller2.setMainApp(this); // Pasar la referencia de MainApp al controlador de la vista 2

        personalAreaController controller3 = loader3.getController();
        controller3.setMainApp(this);

        createPersonalAreaController controller4 = loader4.getController();
        controller4.setMainApp(this);
        /*
        La razón por la que se usa setMainApp es para pasar una referencia de la clase principal
        Main a los controladores de las vistas. Esto permite que los controladores puedan llamar
        a los métodos de MainApp para cambiar entre las vistas. Aquí tienes una explicación
        detallada:

        ¿Por qué pasar la referencia de MainApp?
        - Acceso a Métodos de Cambio de Vista: Los controladores necesitan llamar a los métodos
        showView1() y showView2() de MainApp para cambiar entre las vistas. Al pasar la referencia
        de MainApp, los controladores pueden acceder a estos métodos.

        - Comunicación entre Controladores y MainApp: Esto facilita la comunicación entre los
        controladores y la clase principal, permitiendo una mejor gestión de las vistas y la
        lógica de la aplicación.
        */

        //Inicialmente, mostrar solo la primera vista
        ((Node) loader1.getRoot()).setVisible(false);
        ((Node) loader2.getRoot()).setVisible(false);
        ((Node) loader3.getRoot()).setVisible(false);
        ((Node) loader4.getRoot()).setVisible(true);

        // Crear la escena con el StackPane
        Scene scene = new Scene(rootPane, 675, 460);
        primaryStage.setScene(scene); // Establecer la escena en el escenario principal
        primaryStage.setResizable(false);
        primaryStage.show(); // Mostrar el escenario
    }


    // Método para mostrar la 1 vista
    public void showView1() {
        rootPane.getChildren().get(0).setVisible(true); // Hacer visible la primera vista
        rootPane.getChildren().get(1).setVisible(false); // Hacer invisible la segunda vista
        rootPane.getChildren().get(2).setVisible(false);
        rootPane.getChildren().get(3).setVisible(false);
    }

    // Método para mostrar la 2 vista
    public void showView2() {
        rootPane.getChildren().get(0).setVisible(false); // Hacer invisible la primera vista
        rootPane.getChildren().get(1).setVisible(true); // Hacer visible la segunda vista
        rootPane.getChildren().get(2).setVisible(false);
        rootPane.getChildren().get(3).setVisible(false);
    }

    // Método para mostrar la 3 vista
    public void showView3() {
        rootPane.getChildren().get(0).setVisible(false); // Hacer invisible la primera vista
        rootPane.getChildren().get(1).setVisible(false); // Hacer visible la segunda vista
        rootPane.getChildren().get(2).setVisible(true);
        rootPane.getChildren().get(3).setVisible(false);
    }

    // Método para mostrar la 4 vista
    public void showView4() {
        rootPane.getChildren().get(0).setVisible(false); // Hacer invisible la primera vista
        rootPane.getChildren().get(1).setVisible(false); // Hacer visible la segunda vista
        rootPane.getChildren().get(2).setVisible(false);
        rootPane.getChildren().get(3).setVisible(true);
    }

    public static void main(String[] args) {
        launch(args); // Lanzar la aplicación JavaFX
    }
}
