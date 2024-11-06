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
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("view1.fxml"));
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("view2.fxml"));


// Añadir las vistas al StackPane
        rootPane.getChildren().addAll(loader1.load(), loader2.load());
        // Obtener los controladores de las vistas
        View1Controller controller1 = loader1.getController();
        controller1.setMainApp(this); // Pasar la referencia de MainApp al controlador de la vista 1

        View2Controller controller2 = loader2.getController();
        controller2.setMainApp(this); // Pasar la referencia de MainApp al controlador de la vista 2
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
        ((Node) loader2.getRoot()).setVisible(false);

        // Crear la escena con el StackPane
        Scene scene = new Scene(rootPane, 800, 600);
        primaryStage.setScene(scene); // Establecer la escena en el escenario principal
        primaryStage.show(); // Mostrar el escenario
    }


    // Método para mostrar la primera vista
    public void showView1() {
        rootPane.getChildren().get(0).setVisible(true); // Hacer visible la primera vista
        rootPane.getChildren().get(1).setVisible(false); // Hacer invisible la segunda vista
    }

    // Método para mostrar la segunda vista
    public void showView2() {
        rootPane.getChildren().get(0).setVisible(false); // Hacer invisible la primera vista
        rootPane.getChildren().get(1).setVisible(true); // Hacer visible la segunda vista
    }

    public static void main(String[] args) {
        launch(args); // Lanzar la aplicación JavaFX
    }
}
