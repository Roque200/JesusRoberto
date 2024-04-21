package com.example.jesusroberto.modelos;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BebidasMenu extends MenuButton {

    public BebidasMenu() {
        super();
        inicializar();
    }

    private void inicializar() {
        ImageView imvBebidas = new ImageView(new Image(getClass().getResourceAsStream("/imagenes/Bebidas.png")));
        imvBebidas.setFitHeight(50);
        imvBebidas.setFitWidth(50);
        this.setGraphic(imvBebidas);

        // Crear y agregar elementos del menú para cada bebida
        MenuItem itemBebida1 = new MenuItem("Bebida 1");
        ImageView imvBebida1 = new ImageView(new Image(getClass().getResourceAsStream("/imagenes/Cafe.png")));
        imvBebida1.setFitHeight(30);
        imvBebida1.setFitWidth(30);
        itemBebida1.setGraphic(imvBebida1);

        MenuItem itemBebida2 = new MenuItem("Bebida 2");
        ImageView imvBebida2 = new ImageView(new Image(getClass().getResourceAsStream("/imagenes/Cervesa.png")));
        imvBebida2.setFitHeight(30);
        imvBebida2.setFitWidth(30);
        itemBebida2.setGraphic(imvBebida2);

        // Agregar más bebidas si es necesario
        this.getItems().addAll(itemBebida1, itemBebida2 /* Agregar más bebidas aquí */);

        // Manejar el evento al presionar el botón de bebidas
        this.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Desplegar el menú de bebidas al ser presionado
                show();
            }
        });
    }
}
