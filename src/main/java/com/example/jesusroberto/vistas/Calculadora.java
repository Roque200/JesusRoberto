package com.example.jesusroberto.vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Calculadora extends Stage {

    private Scene escena;
    private VBox vContenedor;
    private GridPane gdbTeclado;
    private TextField txtPantalla;
    private Button[][] arBotones = new Button[4][5];
    private char[] arEtiquetas = {' ',' ','.','C','D',
                                  '5','6','7','8','9',
                                  '4','3','2','1','0',
                                  '=','*','+','-','/'};
    char operador = ' ';
    boolean puntoIntroducido = false;
    double primerOperando , segundoOperador;
    double resultado;
    public Calculadora(){
        CrearUI();
        this.setTitle("Mi primera Calculadora");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        txtPantalla = new TextField("");
        gdbTeclado = new GridPane();
        crearTeclado();
        vContenedor = new VBox(txtPantalla, gdbTeclado);
        vContenedor.setSpacing(5);
        escena = new Scene(vContenedor, 200,200);
        escena.getStylesheets()
                .add(getClass().getResource("/estilos/calculadora.css").toString());;
    }

    private void crearTeclado() {
            int pos = 0;
            for (int i = 0; i < 4; i++){
                for (int j = 0; j < 5; j++){
                    if (pos < arEtiquetas.length) {
                        arBotones[i][j] = new Button(arEtiquetas[pos]+"");
                        arBotones[i][j].setPrefSize(50,50);
                        int finalPos = pos;
                        arBotones[i][j].setOnAction(event ->  setValue(arEtiquetas[finalPos]));
                        gdbTeclado.add(arBotones[i][j],j,i);


                        if( arEtiquetas[pos] == '+' || arEtiquetas[pos] == '-' || arEtiquetas[pos] == '*' || arEtiquetas[pos] == '/'|| arEtiquetas[pos] == 'C' )
                            arBotones[i][j].setId("Color-operador");
                        pos++;
                    }
                }
            }
    }

    private void setValue(char simbolo) {
        if (simbolo == 'C') {
            txtPantalla.clear();
            puntoIntroducido = false;
        }else if (Character.isDigit(simbolo)) {
            txtPantalla.appendText(String.valueOf(simbolo));
        } else if (simbolo == '.' && !puntoIntroducido) {
            txtPantalla.appendText(String.valueOf(simbolo));
            puntoIntroducido = true;
        }else if ((simbolo == '+' || simbolo == '-' || simbolo == '*' || simbolo == '-' || simbolo == '/') && !txtPantalla.getText().isEmpty()) {
                primerOperando = Double.parseDouble(txtPantalla.getText());
                operador = simbolo;
                txtPantalla.clear();
                puntoIntroducido = false;
        }else if (simbolo == '=' && !txtPantalla.getText().isEmpty()){
             segundoOperador = Double.parseDouble(txtPantalla.getText());
             resultado = 0;
            switch (operador){
                case '+':
                    resultado = primerOperando + segundoOperador;
                    break;
                case '-':
                    resultado = primerOperando - segundoOperador;
                    break;
                case '*':
                    resultado = primerOperando * segundoOperador;
                    break;
                case '/':
                    if (segundoOperador != 0 ){
                        resultado = primerOperando / segundoOperador;
                    }else {
                        txtPantalla.setText("Error");
                        return;
                    }
                    break;
            }
            txtPantalla.setText(String.valueOf(resultado));
        }
    }
}
