package com.example.jesusroberto.componentes;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Pista extends Stage {
    private  ProgressBar[] pgbCorredores = new ProgressBar[5];
    private  Label[] lblCorredores = new Label[5];
    private  GridPane gdbPista;
    private Scene escena;
    private String[] strCorredores =  {"Juno","Rafa","Sergio","Joshua","Alma"};
    private Hilo[] thrCorredores = new Hilo[5];
    public Pista(){
        CreateUI();
        this.setTitle("Pista de Atletismo");
        this.setScene(escena);
        this.show();
    }

    private void CreateUI() {
        gdbPista = new GridPane();
        for (int i = 0; i < strCorredores.length; i++) {
            lblCorredores[i] = new Label(strCorredores[i]);
            pgbCorredores[i] = new ProgressBar(0);
            gdbPista.add(lblCorredores[i],0,i);
            gdbPista.add(pgbCorredores[i],1,i);
            thrCorredores[i] = new Hilo(strCorredores[i]);
            thrCorredores[i].setPgbCarril(pgbCorredores[i]);
            thrCorredores[i].start();
        }
        escena = new Scene(gdbPista,200,200);
    }
}
