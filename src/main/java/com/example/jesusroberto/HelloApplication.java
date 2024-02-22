package com.example.jesusroberto;

import com.example.jesusroberto.vistas.Calculadora;
import com.example.jesusroberto.vistas.memorama;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private MenuBar mnbPrincipal;
    private Menu mnParcial1, mnParcial2,mnSalida;
    private MenuItem mitCalculadora, mitSalir,mitmemorama;
    private BorderPane bdpPanel;

    @Override
    public void start(Stage stage) throws IOException {
        CrearMenu();
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        bdpPanel=new BorderPane();
        bdpPanel.setTop(mnbPrincipal);
        Scene scene = new Scene(bdpPanel);
        scene.getStylesheets().add(getClass().getResource("/estilos/main.css").toString());
        stage.setTitle("Topicos Avanzados de programacion!!!!");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

    }

    private void CrearMenu() {
        //Menu primer parcial
        mitCalculadora = new MenuItem("Calculadora");
        mitmemorama = new MenuItem("Memorama");
        mnParcial1 = new Menu("Primer parcial");
        mnParcial1.getItems().addAll(mitCalculadora, mitmemorama);

        //Menu Segundo parcial
        mnParcial2= new Menu("Segundo parcial");

        //Menu salida
        mnSalida = new Menu("Salida");
        mitSalir = new MenuItem("Salir");
        mnSalida.getItems().addAll(mitSalir);
        mitSalir.setOnAction(actionEvent -> System.exit(0));
        //mnSalida.setOnAction(actionEvent -> System.exit(0));


        mnbPrincipal = new MenuBar();
        mnbPrincipal.getMenus().addAll(mnParcial1,mnParcial2,mnSalida);

        mitCalculadora.setOnAction(actionEvent ->new Calculadora());
        mitmemorama.setOnAction(actionEvent -> new memorama());


    }

    public static void main(String[] args) {
        launch();
    }

}