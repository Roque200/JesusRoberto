package com.example.jesusroberto.vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Cuadromagico extends Stage {

    private Scene escena;
    public Cuadromagico(){
        this.setTitle("Cuadro Magico");
        this.setScene(new Scene(new Button("Da Click")));
        this.show();
    }

    private void CrearUI(){
        escena = new Scene (new Button("Da Click"));
    }
}
