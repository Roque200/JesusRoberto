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
    private Button[][] arBotones = new Button[4][4];
    private char[] arEtiquetas = {'7','8','9','/','4','5','6','*','1','2','3','-','0','.','=','+'};

    public Calculadora(){
        CrearUI();
        this.setTitle("Mi primera Calculadora");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        txtPantalla = new TextField("0");
        gdbTeclado = new GridPane();
        crearTeclado();
        vContenedor = new VBox(txtPantalla, gdbTeclado);
        vContenedor.setSpacing(5);
        escena = new Scene(vContenedor, 200,200);
    }

    private void crearTeclado() {
            int pos = 0;
            char simbolo;
            for (int i = 0; i < 4; i++){
                for (int j = 0; j < 4; j++){

                    arBotones[i][j] = new Button(arEtiquetas[pos]+"");
                    arBotones[i][j].setPrefSize(50,50);
                    int finalPos = pos;
                    arBotones[i][j].setOnAction(event ->  setValue(arEtiquetas[finalPos]));
                    gdbTeclado.add(arBotones[i][j],j,i);
                     pos++;
                }
        }

    }

    private void setValue(char simbolo) {
        txtPantalla.appendText(simbolo+"");
    }


}
