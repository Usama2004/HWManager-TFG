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
        FXMLLoader login = new FXMLLoader(getClass().getResource("loginView.fxml"));
        FXMLLoader register = new FXMLLoader(getClass().getResource("registerView.fxml"));
        FXMLLoader personalArea = new FXMLLoader(getClass().getResource("personalAreaView.fxml"));
        FXMLLoader crearTareaPersonalArea = new FXMLLoader(getClass().getResource("crearTareaPersonalAreaView.fxml"));
        FXMLLoader group = new FXMLLoader(getClass().getResource("groupView.fxml"));
        FXMLLoader createGroup = new FXMLLoader(getClass().getResource("createGroupView.fxml"));
        FXMLLoader contenidoGroup = new FXMLLoader(getClass().getResource("contenidoGrupoView.fxml"));
        FXMLLoader crearApartadoGrupo = new FXMLLoader(getClass().getResource("crearApartadoGrupoView.fxml"));
        FXMLLoader calendario = new FXMLLoader(getClass().getResource("calendarioView.fxml"));

        // Cargar las vistas
        Node loginView = login.load();
        Node registerView = register.load();
        Node personalAreaView = personalArea.load();
        Node crearTareaPersonalAreaView = crearTareaPersonalArea.load();
        Node groupView = group.load();
        Node createGroupView = createGroup.load();
        Node contenidoGrupoView = contenidoGroup.load();
        Node crearApartadoGrupoView = crearApartadoGrupo.load();
        Node calendarioView = calendario.load();
        // Añadir las vistas al StackPane
        rootPane.getChildren().addAll(loginView, registerView, personalAreaView,
                crearTareaPersonalAreaView, groupView, createGroupView, contenidoGrupoView,
                crearApartadoGrupoView, calendarioView);

        // Obtener los controladores de las vistas
        loginController loginController = login.getController();
        loginController.setMainApp(this); // Pasar la referencia de MainApp al controlador de la vista 1

        registerController registerController = register.getController();
        registerController.setMainApp(this); // Pasar la referencia de MainApp al controlador de la vista 2

        personalAreaController personalAreaController = personalArea.getController();
        personalAreaController.setMainApp(this);

        crearTarea_PersonalAreaController crearTarea_PersonalAreaController = crearTareaPersonalArea.getController();
        crearTarea_PersonalAreaController.setMainApp(this);

        groupController groupController = group.getController();
        groupController.setMainApp(this);

        createGroupController createGroupController = createGroup.getController();
        createGroupController.setMainApp(this);

        contenidoGrupoController contenidoGrupoController = contenidoGroup.getController();
        contenidoGrupoController.setMainApp(this);

        crearApartadoGrupoController crearApartadoGrupoController = crearApartadoGrupo.getController();
        crearApartadoGrupoController.setMainApp(this);

        calendarioController calendarioController = calendario.getController();
        calendarioController.setMainApp(this);
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
        ((Node) login.getRoot()).setVisible(true);
        ((Node) register.getRoot()).setVisible(false);
        ((Node) personalArea.getRoot()).setVisible(false);
        ((Node) crearTareaPersonalArea.getRoot()).setVisible(false);
        ((Node) group.getRoot()).setVisible(false);
        ((Node) createGroup.getRoot()).setVisible(false);
        ((Node) contenidoGroup.getRoot()).setVisible(false);
        ((Node) crearApartadoGrupo.getRoot()).setVisible(false);
        ((Node) calendario.getRoot()).setVisible(false);

        // Crear la escena con el StackPane
        Scene scene = new Scene(rootPane, 675, 460);
        primaryStage.setScene(scene); // Establecer la escena en el escenario principal
        primaryStage.setResizable(false);
        primaryStage.show(); // Mostrar el escenario
    }


    // Método para mostrar la 1 vista
    public void showLoginView() {
        rootPane.getChildren().get(0).setVisible(true); // Hacer visible la primera vista
        rootPane.getChildren().get(1).setVisible(false); // Hacer invisible la segunda vista
        rootPane.getChildren().get(2).setVisible(false);
        rootPane.getChildren().get(3).setVisible(false);
        rootPane.getChildren().get(4).setVisible(false);
        rootPane.getChildren().get(5).setVisible(false);
        rootPane.getChildren().get(6).setVisible(false);
        rootPane.getChildren().get(7).setVisible(false);
        rootPane.getChildren().get(8).setVisible(false);
    }

    // Método para mostrar la 2 vista
    public void showRegisterView() {
        rootPane.getChildren().get(0).setVisible(false); // Hacer invisible la primera vista
        rootPane.getChildren().get(1).setVisible(true); // Hacer visible la segunda vista
        rootPane.getChildren().get(2).setVisible(false);
        rootPane.getChildren().get(3).setVisible(false);
        rootPane.getChildren().get(4).setVisible(false);
        rootPane.getChildren().get(5).setVisible(false);
        rootPane.getChildren().get(6).setVisible(false);
        rootPane.getChildren().get(7).setVisible(false);
        rootPane.getChildren().get(8).setVisible(false);
    }

    // Método para mostrar la 3 vista
    public void showPersonalAreaView() {
        rootPane.getChildren().get(0).setVisible(false); // Hacer invisible la primera vista
        rootPane.getChildren().get(1).setVisible(false); // Hacer visible la segunda vista
        rootPane.getChildren().get(2).setVisible(true);
        rootPane.getChildren().get(3).setVisible(false);
        rootPane.getChildren().get(4).setVisible(false);
        rootPane.getChildren().get(5).setVisible(false);
        rootPane.getChildren().get(6).setVisible(false);
        rootPane.getChildren().get(7).setVisible(false);
        rootPane.getChildren().get(8).setVisible(false);
    }

    // Método para mostrar la 4 vista
    public void showCrearTareaPersonalAreaView() {
        rootPane.getChildren().get(0).setVisible(false); // Hacer invisible la primera vista
        rootPane.getChildren().get(1).setVisible(false); // Hacer visible la segunda vista
        rootPane.getChildren().get(2).setVisible(false);
        rootPane.getChildren().get(3).setVisible(true);
        rootPane.getChildren().get(4).setVisible(false);
        rootPane.getChildren().get(5).setVisible(false);
        rootPane.getChildren().get(6).setVisible(false);
        rootPane.getChildren().get(7).setVisible(false);
        rootPane.getChildren().get(8).setVisible(false);
    }

    // Método para mostrar la 5 vista
    public void showGroupView() {
        rootPane.getChildren().get(0).setVisible(false); // Hacer invisible la primera vista
        rootPane.getChildren().get(1).setVisible(false); // Hacer visible la segunda vista
        rootPane.getChildren().get(2).setVisible(false);
        rootPane.getChildren().get(3).setVisible(false);
        rootPane.getChildren().get(4).setVisible(true);
        rootPane.getChildren().get(5).setVisible(false);
        rootPane.getChildren().get(6).setVisible(false);
        rootPane.getChildren().get(7).setVisible(false);
        rootPane.getChildren().get(8).setVisible(false);
    }

    // Método para mostrar la 6 vista
    public void showCreateGroupView() {
        rootPane.getChildren().get(0).setVisible(false); // Hacer invisible la primera vista
        rootPane.getChildren().get(1).setVisible(false); // Hacer visible la segunda vista
        rootPane.getChildren().get(2).setVisible(false);
        rootPane.getChildren().get(3).setVisible(false);
        rootPane.getChildren().get(4).setVisible(false);
        rootPane.getChildren().get(5).setVisible(true);
        rootPane.getChildren().get(6).setVisible(false);
        rootPane.getChildren().get(7).setVisible(false);
        rootPane.getChildren().get(8).setVisible(false);
    }

    // Método para mostrar la 7 vista
    public void showContenidoGroupView() {
        rootPane.getChildren().get(0).setVisible(false); // Hacer invisible la primera vista
        rootPane.getChildren().get(1).setVisible(false); // Hacer visible la segunda vista
        rootPane.getChildren().get(2).setVisible(false);
        rootPane.getChildren().get(3).setVisible(false);
        rootPane.getChildren().get(4).setVisible(false);
        rootPane.getChildren().get(5).setVisible(false);
        rootPane.getChildren().get(6).setVisible(true);
        rootPane.getChildren().get(7).setVisible(false);
        rootPane.getChildren().get(8).setVisible(false);
    }

    // Método para mostrar la 8 vista
    public void showCrearApartadoGrupoView() {
        rootPane.getChildren().get(0).setVisible(false); // Hacer invisible la primera vista
        rootPane.getChildren().get(1).setVisible(false); // Hacer visible la segunda vista
        rootPane.getChildren().get(2).setVisible(false);
        rootPane.getChildren().get(3).setVisible(false);
        rootPane.getChildren().get(4).setVisible(false);
        rootPane.getChildren().get(5).setVisible(false);
        rootPane.getChildren().get(6).setVisible(false);
        rootPane.getChildren().get(7).setVisible(true);
        rootPane.getChildren().get(8).setVisible(false);
    }

    // Método para mostrar la 9 vista
    public void showCalendarioView() {
        rootPane.getChildren().get(0).setVisible(false); // Hacer invisible la primera vista
        rootPane.getChildren().get(1).setVisible(false); // Hacer visible la segunda vista
        rootPane.getChildren().get(2).setVisible(false);
        rootPane.getChildren().get(3).setVisible(false);
        rootPane.getChildren().get(4).setVisible(false);
        rootPane.getChildren().get(5).setVisible(false);
        rootPane.getChildren().get(6).setVisible(false);
        rootPane.getChildren().get(7).setVisible(false);
        rootPane.getChildren().get(8).setVisible(true);
    }

    public static void main(String[] args) {
        launch(args); // Lanzar la aplicación JavaFX
    }
}
