package com.example.jesusroberto.vistas;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;

public class Cuadromagico extends Stage {

    private Scene scene;
    private Button btnCalcular;
    private TextField txtTamano;

    /*
      Constructor de la clase Cuadromagico.
     */
    public Cuadromagico() {
        inicializarComponentes();
        configurarEscena();
        this.setTitle("Cuadro Mágico");
        this.setScene(scene);
        this.show();
    }

    /*
      Inicializa los componentes de la ventana, como etiquetas, campos de texto y botones.
     */
    private void inicializarComponentes() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        Label lblTamano = new Label("Tamaño del cuadro:");
        txtTamano = new TextField();
        txtTamano.setPromptText("Introduce un número impar mayor o igual a 3");
        gridPane.add(lblTamano, 0, 0);
        gridPane.add(txtTamano, 1, 0);

        btnCalcular = new Button("Calcular");
        btnCalcular.setOnAction(e -> mostrarCuadroMagico());
        btnCalcular.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 16px;");
        gridPane.add(btnCalcular, 0, 1, 2, 1);
        scene = new Scene(gridPane, 250, 200);
    }

    /*
      Configura la escena de la ventana.
      No se necesitan configuraciones adicionales en este momento.
     */
    private void configurarEscena() {
        // No es necesario configurar las filas y columnas aquí
    }

    /*
      Método invocado cuando se presiona el botón "Calcular".
      Lee el tamaño del cuadro de texto y muestra el cuadro mágico correspondiente.
      Si el tamaño es inválido, muestra un mensaje de error.
     */
    private void mostrarCuadroMagico() {
        try {
            int tamano = Integer.parseInt(txtTamano.getText());
            if (tamano < 3 || tamano % 2 == 0) {
                mostrarMensajeError("El tamaño del cuadro debe ser un número impar mayor o igual a 3.");
                return;
            }

            mostrarCuadroMagicoEnVentana(tamano);

        } catch (NumberFormatException e) {
            mostrarMensajeError("Por favor, introduce un número válido para el tamaño del cuadro.");
        }
    }

    /*
      Muestra el cuadro mágico en una nueva ventana.
      Lee los números del cuadro mágico desde un archivo binario y los muestra en la ventana.
     */
    private void mostrarCuadroMagicoEnVentana(int tamano) {
        try (RandomAccessFile archivo = new RandomAccessFile("cuadromagico.bin", "r")) {
            GridPane gridPane = new GridPane();
            gridPane.setPadding(new Insets(10));
            gridPane.setHgap(5);
            gridPane.setVgap(5);
            gridPane.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 10px;");

            for (int fila = 0; fila < tamano; fila++) {
                for (int columna = 0; columna < tamano; columna++) {
                    int numero = archivo.readInt();
                    TextField campoTexto = new TextField(String.valueOf(numero));
                    campoTexto.setEditable(false);
                    campoTexto.setStyle("-fx-background-color: white; -fx-border-color: #ccc; -fx-font-size: 14px;");
                    gridPane.add(campoTexto, columna, fila);
                }
            }

            Scene scene = new Scene(gridPane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Cuadro Mágico");
            stage.show();

        } catch (IOException e) {
            mostrarMensajeError("No se pudo leer el cuadro mágico del archivo.");
        }
    }

    /*
      Muestra un mensaje de error en la consola.
     */
    private void mostrarMensajeError(String mensaje) {
        System.err.println("Error: " + mensaje);
    }
}
