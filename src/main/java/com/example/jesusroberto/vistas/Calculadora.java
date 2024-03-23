package com.example.jesusroberto.vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Calculadora extends Stage {
    // Declaración de variables
    private Scene escena;
    private VBox vContenedor;
    private GridPane gdbTeclado;
    private TextField txtPantalla;
    private Button[][] arBotones = new Button[4][5];
    private char[] arEtiquetas = {' ', ' ', '.', 'C', 'D',
                                  '5', '6', '7', '8', '9',
                                  '4', '3', '2', '1', '0',
                                  '=', '*', '+', '-', '/'};
    char operador = ' ';
    boolean puntoIntroducido = false;
    double primerOperando, segundoOperador, resultadoParcial, resultadoFinal;
    String textoPantalla;

    // Constructor de la clase
    public Calculadora() {
        // Inicializa la interfaz de usuario
        CrearUI();
        this.setTitle("Mi primera Calculadora");
        // Establece la escena en la ventana
        this.setScene(escena);
        // Muestra la ventana
        this.show();
    }
    // Método para crear la interfaz de usuario
    private void CrearUI() {
        txtPantalla = new TextField("");
        txtPantalla.setEditable(false);
        gdbTeclado = new GridPane();
        // Crea el teclado numérico y de operaciones
        crearTeclado();
        vContenedor = new VBox(txtPantalla, gdbTeclado);
        vContenedor.setSpacing(5);
        escena = new Scene(vContenedor, 200, 200);
        escena.getStylesheets()
                .add(getClass().getResource("/estilos/calculadora.css").toString());
        ;
    }
    // Método para crear el teclado de la calculadora
    private void crearTeclado() {
        int pos = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (pos < arEtiquetas.length) {
                    arBotones[i][j] = new Button(String.valueOf(arEtiquetas[pos]));
                    arBotones[i][j].setPrefSize(50, 50);
                    int finalPos = pos;
                    // Manejador de evento para los botones
                    arBotones[i][j].setOnAction(event -> setValue(arEtiquetas[finalPos]));
                    gdbTeclado.add(arBotones[i][j], j, i);

                    // Establecer un ID para los botones de operadores para aplicar un estilo especial
                    if (arEtiquetas[pos] == '+' || arEtiquetas[pos] == '-' || arEtiquetas[pos] == '*' || arEtiquetas[pos] == '/' || arEtiquetas[pos] == 'C' || arEtiquetas[pos] == 'D') {
                        arBotones[i][j].setId("Color-operador");
                    }
                    pos++;
                }
            }
        }
    }
    // Método para manejar los eventos de clic en los botones
    private void setValue(char simbolo) {
        if (simbolo == 'C') {
            // Limpiar la pantalla y reiniciar variables
            txtPantalla.clear();
            puntoIntroducido = false;
            operador = ' ';
            primerOperando = 0;
            segundoOperador = 0;
        } else if (simbolo == 'D') {
            // Eliminar el último carácter de la pantalla
        textoPantalla = txtPantalla.getText();
        if (!textoPantalla.isEmpty()){
            txtPantalla.setText(textoPantalla.substring(0,textoPantalla.length() - 1));
        }
        } else if (Character.isDigit(simbolo)) {
            // Agregar dígito a la pantalla
            txtPantalla.appendText(String.valueOf(simbolo));
        } else if (simbolo == '.' && !puntoIntroducido) {
            // Agregar punto decimal a la pantalla
            txtPantalla.appendText(String.valueOf(simbolo));
            puntoIntroducido = true;
        } else if (simbolo == '+' || simbolo == '-' || simbolo == '*' || simbolo == '/') {
            // Se realizar operaciones aritméticas
            if (!txtPantalla.getText().isEmpty() && Character.isDigit(txtPantalla.getText().charAt(txtPantalla.getText().length() - 1))) {
                if (operador != ' ') {
                    segundoOperador = Double.parseDouble(txtPantalla.getText());
                    double resultadoParcial = 0;
                    switch (operador) {
                        case '+':
                            resultadoParcial = primerOperando + segundoOperador;
                            break;
                        case '-':
                            resultadoParcial = primerOperando - segundoOperador;
                            break;
                        case '*':
                            resultadoParcial = primerOperando * segundoOperador;
                            break;
                        case '/':
                            if (segundoOperador != 0) {
                                resultadoParcial = primerOperando / segundoOperador;
                            } else {
                                txtPantalla.setText("Error");
                                return;
                            }
                            break;
                    }
                    primerOperando = resultadoParcial;
                    txtPantalla.setText(String.valueOf(resultadoParcial));
                } else {

                    primerOperando = Double.parseDouble(txtPantalla.getText());
                }
                operador = simbolo;
                puntoIntroducido = false;
                txtPantalla.clear();
            }
        } else if (simbolo == '=') {
            if (!txtPantalla.getText().isEmpty() && operador != ' ' && Character.isDigit(txtPantalla.getText().charAt(txtPantalla.getText().length() - 1))) {

                segundoOperador = Double.parseDouble(txtPantalla.getText());
                double resultadoFinal = 0;
                switch (operador) {
                    case '+':
                        resultadoFinal = primerOperando + segundoOperador;
                        break;
                    case '-':
                        resultadoFinal = primerOperando - segundoOperador;
                        break;
                    case '*':
                        resultadoFinal = primerOperando * segundoOperador;
                        break;
                    case '/':
                        if (segundoOperador != 0) {
                            resultadoFinal = primerOperando / segundoOperador;
                        } else {
                            txtPantalla.setText("Error");
                            return;
                        }
                        break;
                }
                txtPantalla.setText(String.valueOf(resultadoFinal));
                operador = ' ';
                primerOperando = resultadoFinal;
                puntoIntroducido = false;
            }
        }
    }
}