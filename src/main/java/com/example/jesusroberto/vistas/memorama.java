package com.example.jesusroberto.vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class memorama extends Stage {
    private Scene escena;
    private VBox Vbinicial, Vbparticipantes;
    private HBox Hbinicial , Hbsecundarios, Hbparticipante1,Hbparticipante2;
    private GridPane gdbCartas;
    private Label Lbpares, Lntiempo, Lbparticimante1, Lbparticipante2, lbPuntuacion1,lbPuntuacion2;
    private TextField txtEntrada;
    private Button btnResolver;
    int puntuacion1, puntuacion2;
    String sP1, sP2;

    public memorama(){
        crearUI();
        this.setTitle("El Memorama");
        this.setScene(escena);
        this.show();
    }

    private void crearUI() {
        puntuacion1=0;
        sP1 = (puntuacion1+"");
        puntuacion2=0;
        sP2 = (puntuacion2+"");

        Lbpares = new Label("Numero de pares");
        Lntiempo = new Label();

        txtEntrada = new TextField();
        txtEntrada.setMinWidth(15);

        btnResolver = new Button("Revolver");

        Hbinicial = new HBox(Lbpares, txtEntrada, btnResolver, Lntiempo);
        Hbinicial.setSpacing(5);

        Lbparticimante1 = new Label("Participante 1");
        lbPuntuacion1 = new Label(sP1);

        Hbparticipante1 = new HBox(Lbparticimante1, lbPuntuacion1);
        Hbparticipante1.setSpacing(5);

        Lbparticipante2 = new Label("Participante 2");
        lbPuntuacion2 = new Label(sP2);

        Hbparticipante2 = new HBox(Lbparticipante2, lbPuntuacion2);
        Hbparticipante2.setSpacing(5);

        Vbparticipantes = new VBox(Hbparticipante1,Hbparticipante2);
        Vbparticipantes.setSpacing(5);

        gdbCartas = new GridPane();
        gdbCartas.setMinSize(250,250);
        ResolverCartas ();

        Hbsecundarios = new HBox(gdbCartas,Vbparticipantes);
        Hbsecundarios.setSpacing(5);

        Vbinicial = new VBox(Hbinicial,Hbsecundarios);
        Vbinicial.setSpacing(5);
        escena = new Scene(Vbinicial,400,400);



    }

    private void ResolverCartas() {
                String[] arImagenes = {"img.png","img_1.png","img_2.png","img_2.png","img_3.png","img_4.png","img_5.png","img_6.png"};
                Button[][] arCtnCartas = new Button[2][4];

                ImageView imvCarta;
                int posx = 0;
                int posy = 0;
                int cont = 0;
                for (int i = 0; i < arImagenes.length; i++){
                    posx = (int) (Math.random()*2);
                    posx = (int) (Math.random()*4);
                    if(arCtnCartas[posx][posy] == null){
                        arCtnCartas [posx][posy] = new Button();
                        imvCarta = new ImageView(
                                getClass().getResource("/imagenes/"+arImagenes[i]).toString()
                        );
                        
                        arCtnCartas[posx][posy].setGraphic(imvCarta);
                        arCtnCartas[posx][posy].setPrefSize(100,150);
                        gdbCartas.add(arCtnCartas[posx][posy],posy,posx);
                        cont++;
                        if( cont == 2 )  {
                            i++;

                            cont=0;
                        }
                    }
                }
     }
}
