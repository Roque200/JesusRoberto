package com.example.jesusroberto.vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppTaqueria extends Stage {
    private Scene escena;
    private HBox hbox;
    private VBox vboxBotones, vboxMesas;
    private Button cocinaButton, empleadosButton, cuentaButton, tomarOrdenButton, regresarButton;
    private GridPane gridMesas;
    private String estiloBotones, estilomesa, Contraseña, Usuario;


    public AppTaqueria() {
        super();
        CrearUI();
        escena = new Scene(hbox);
        this.setTitle("AppTaqueria");
        this.setScene(escena);
        this.sizeToScene();
        this.show();
    }

    private void CrearUI() {
        hbox = new HBox(10);
        hbox.setPadding(new Insets(20));
        hbox.setAlignment(Pos.CENTER);


        vboxBotones = new VBox(10);
        vboxBotones.setAlignment(Pos.TOP_CENTER);

        Usuario = "Armando";
        Contraseña = "54321";

        Image imagenOrden = new Image(getClass().getResourceAsStream("/imagenes/orden.png"));
        ImageView imageViewOrden = new ImageView(imagenOrden);
        imageViewOrden.setFitWidth(50);
        imageViewOrden.setFitHeight(50);
        tomarOrdenButton = new Button();
        tomarOrdenButton.setGraphic(imageViewOrden);

        Image imagenCuenta = new Image(getClass().getResourceAsStream("/imagenes/cuenta.png"));
        ImageView imageViewCuenta = new ImageView(imagenCuenta);
        imageViewCuenta.setFitWidth(50);
        imageViewCuenta.setFitHeight(50);
        cuentaButton = new Button();
        cuentaButton.setGraphic(imageViewCuenta);

        Image imagenEmpleados = new Image(getClass().getResourceAsStream("/imagenes/empleados.png"));
        ImageView imageViewEmpleados = new ImageView(imagenEmpleados);
        imageViewEmpleados.setFitWidth(50);
        imageViewEmpleados.setFitHeight(50);
        empleadosButton = new Button();
        empleadosButton.setGraphic(imageViewEmpleados);

        Image imagenCocina = new Image(getClass().getResourceAsStream("/imagenes/cocina.png"));
        ImageView imageViewCocina = new ImageView(imagenCocina);
        imageViewCocina.setFitWidth(50);
        imageViewCocina.setFitHeight(50);
        cocinaButton = new Button();
        cocinaButton.setGraphic(imageViewCocina);

        Image imagenSalir = new Image(getClass().getResourceAsStream("/imagenes/salir.png"));
        ImageView imageViewSalir = new ImageView(imagenSalir);
        imageViewSalir.setFitWidth(50);
        imageViewSalir.setFitHeight(50);
        regresarButton = new Button();
        regresarButton.setGraphic(imageViewSalir);

        estiloBotones = "-fx-font-size: 15px; -fx-min-width: 120px; -fx-min-height: 120px; -fx-background-color: gray;";


        tomarOrdenButton.setStyle(estiloBotones);
        cuentaButton.setStyle(estiloBotones);
        empleadosButton.setStyle(estiloBotones);
        cocinaButton.setStyle(estiloBotones);
        regresarButton.setStyle(estiloBotones);


        tomarOrdenButton.setOnAction(event -> botonPresionado("Tomar una orden",tomarOrdenButton));
        cuentaButton.setOnAction(event -> botonPresionado("Cuenta",cuentaButton));
        empleadosButton.setOnAction(event -> botonPresionado("Empleados",empleadosButton));
        cocinaButton.setOnAction(event -> botonPresionado("Cocina",cocinaButton));
        regresarButton.setOnAction(event -> botonPresionado("Regresar",regresarButton));


        vboxBotones.getChildren().addAll(tomarOrdenButton, cuentaButton, empleadosButton, cocinaButton, regresarButton);
        vboxMesas = new VBox(10);
        vboxMesas.setAlignment(Pos.TOP_CENTER); // Alineación superior


        gridMesas = new GridPane();
        gridMesas.setHgap(10);
        gridMesas.setVgap(10);


        int numMesas = 20;
        for (int i = 0; i < numMesas; i++) {

            Image imagenMesa = new Image(getClass().getResourceAsStream("/imagenes/mesas.png"));
            ImageView imageViewMesa = new ImageView(imagenMesa);
            imageViewMesa.setFitWidth(50);
            imageViewMesa.setFitHeight(50);

            Button mesaButton = new Button();
            mesaButton.setGraphic(imageViewMesa);
            mesaButton.setPrefSize(80, 80);
            gridMesas.add(mesaButton, i % 5, i / 5);

            estilomesa = "-fx-background-color: gray;";
            mesaButton.setStyle(estilomesa);


        }
        vboxMesas.getChildren().add(gridMesas);
        hbox.getChildren().addAll(vboxBotones, vboxMesas);
    }

    private Button CrearBoton(String rutaImagen, String texto) {
        Image imagen = new Image(getClass().getResourceAsStream(rutaImagen));
        ImageView imageView = new ImageView(imagen);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);

        Button boton = new Button(texto);
        boton.setGraphic(imageView);
        boton.setStyle(estiloBotones);

        return boton;
    }
    private void botonPresionado(String opcion, Button boton) {
        System.out.println("Botón presionado: " + opcion);
        if (opcion.equals("Empleados")) {
            // Verificar las credenciales del administrador
            if (Usuario.equals("Armando") && Contraseña.equals("54321")) {
                // Si las credenciales son correctas, abrir la ventana de empleados y habilitar el botón
                EmpleadoTaqueria empleadoTaqueria = new EmpleadoTaqueria();
                empleadoTaqueria.show();
                boton.setDisable(false);
            } else {
                // De lo contrario, mostrar un mensaje de error o realizar alguna otra acción
                System.out.println("No tiene permiso para acceder a esta opción.");
            }
        }
    }
}