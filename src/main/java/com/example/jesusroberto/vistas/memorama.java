package com.example.jesusroberto.vistas;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;

public class memorama extends Stage {

    private Scene scene;
    private Label lblPairs, lblPlayer1, lblPlayer2, lblTime, lblScore1, lblScore2;
    private TextField txtPairs;
    private GridPane cardGrid;
    private Button btnPlay;
    private VBox root, playersVBox;
    private HBox timeBox, gameBox, player1Box, player2Box;
    private int scorePlayer1, scorePlayer2, currentPlayer, timeLimit, numPairs, turnTimeLimit;
    private ArrayList<Button> selectedButtons = new ArrayList<>();
    private Timeline turnTimer;

    public memorama() {
        createUI();

        this.setTitle("Memorama :)");
        this.setScene(scene);
        this.show();
        currentPlayer = 1;
        turnTimeLimit = 30;
        // Modificamos el tiempo límite a 30 segundos
    }


    private void startTurnTimer() {
        turnTimer = new Timeline(new KeyFrame(Duration.seconds(turnTimeLimit), event -> {
            currentPlayer = (currentPlayer == 1) ? 2 : 1;
            actualizaretiquetadegiro();
            restablecerbotonesseleccionados();
            iniciarJugadorTemporizador(); // Reinicia el temporizador del jugador
        }));
        turnTimer.play();
    }


    private void createUI() {
        scorePlayer1 = 0;
        scorePlayer2 = 0;
        currentPlayer = 1;
        timeLimit = 60;
        // Puedes ajustar el tiempo límite según tus necesidades

        lblPairs = new Label("Número de pares:");
        lblTime = new Label("Tiempo restante:");
        lblScore1 = new Label("Puntuación Jugador 1: " + scorePlayer1);
        lblScore2 = new Label("Puntuación Jugador 2: " + scorePlayer2);
        txtPairs = new TextField();
        txtPairs.setMinWidth(15);
        btnPlay = new Button("Jugar");
        timeBox = new HBox(lblPairs, txtPairs, btnPlay, lblTime);
        // Agregar lblTime a la disposición

        btnPlay.setOnAction(event -> {
            try {
                numPairs = Integer.parseInt(txtPairs.getText());
                if (numPairs < 3 || numPairs > 15) {
                    showAlert("Error", "Número de pares inválido", "El número de pares debe estar entre 3 y 15.");
                    return;
                }
                // Limpiar el GridPane para eliminar las figuras existentes
                cardGrid.getChildren().clear();
                // Revolver y construir las nuevas figuras
                barajarCartas();
                // Iniciar el temporizador del jugador actual
                iniciarJugadorTemporizador();
            } catch (NumberFormatException e) {
                showAlert("Error", "Entrada inválida", "Ingrese un número válido para el número de pares.");
            }
        });

        lblPlayer1 = new Label("Jugador 1");
        lblPlayer2 = new Label("Jugador 2");
        lblPlayer1.setStyle("-fx-text-fill: green;");
        lblPlayer2.setStyle("-fx-text-fill: red;");
        player1Box = new HBox(lblPlayer1, lblScore1);
        player2Box = new HBox(lblPlayer2, lblScore2);

        cardGrid = new GridPane();
        cardGrid.setMinSize(400, 400);

        gameBox = new HBox(cardGrid);

        playersVBox = new VBox(player1Box, player2Box);
        playersVBox.setSpacing(10);

        root = new VBox(timeBox, playersVBox, gameBox);
        root.setSpacing(10);

        scene = new Scene(root);
    }
    private void iniciarJugadorTemporizador() {
        // Definir el límite de tiempo por jugador
        final int[] timeLimit = {30};

        // Detener el temporizador existente antes de iniciar uno nuevo
        detenerTurnTimer();

        // Cambiar el color de fondo de los botones según el jugador actual
        if (currentPlayer == 1) {
            cardGrid.setStyle("-fx-background-color: green;");
        } else {
            cardGrid.setStyle("-fx-background-color: red;");
        }

        // Configurar el temporizador del jugador actual
        turnTimer = new Timeline(new KeyFrame(Duration.seconds(timeLimit[0]), event -> {
            currentPlayer = (currentPlayer == 1) ? 2 : 1; // Cambia al siguiente jugador
            actualizaretiquetadegiro();
            restablecerbotonesseleccionados();
            startTurnTimer();
        }));
        turnTimer.setCycleCount(1);


        turnTimer.setOnFinished(e -> {
            currentPlayer = (currentPlayer == 1) ? 2 : 1;
            actualizaretiquetadegiro();
            // Cambiar color de fondo de los botones según el jugador actual
            if (currentPlayer == 1) {
                cardGrid.setStyle("-fx-background-color: green;");
            } else {
                cardGrid.setStyle("-fx-background-color: red;");
            }
            // Reiniciar el temporizador
            startTurnTimer();
        });
        // Mostrar el tiempo restante en la etiqueta lblTime
        lblTime.setText("Tiempo restante: " + timeLimit[0] + " segundos");

        // Configurar el temporizador para actualizar la etiqueta lblTime cada segundo
        Timeline timeUpdate = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            timeLimit[0]--;
            lblTime.setText("Tiempo restante: " + timeLimit[0] + " segundos");

            // Si el tiempo llega a cero, detener el temporizador del jugador actual y pasar al siguiente jugador
            if (timeLimit[0] == 0) {
                turnTimer.stop();
                currentPlayer = (currentPlayer == 1) ? 2 : 1;
                actualizaretiquetadegiro();
                iniciarJugadorTemporizador(); // Iniciar el temporizador del nuevo jugador
            }
        }));
        timeUpdate.setCycleCount(timeLimit[0]); // Configurar el número total de ciclos
        timeUpdate.play(); // Iniciar el temporizador de actualización del tiempo

        // Iniciar el temporizador del jugador actual
        turnTimer.play();
    }


    private void detenerTurnTimer() {
        if (turnTimer != null) {
            turnTimer.stop();
        }
    }
    private void actualizaretiquetadegiro() {
        if (currentPlayer == 1) {
            lblPlayer1.setStyle("-fx-text-fill: green;");
            lblPlayer2.setStyle("-fx-text-fill: red;");
        } else {
            lblPlayer1.setStyle("-fx-text-fill: red;");
            lblPlayer2.setStyle("-fx-text-fill: green;");
        }
    }

    private void restablecerbotonesseleccionados() {
        selectedButtons.clear();
    }

    private void barajarCartas() {
        cardGrid.getChildren().clear();
        String[] imagePaths = {"img.png", "img_1.png", "img_2.png", "img_3.png", "img_4.png", "img_5.png", "img_6.png",
                "img_7.png", "img_8.png", "img_9.png", "img_10.png", "img_11.png", "img_12.png",
                "img_13.png", "img_14.png", "img_15.png"};

        // Duplicar las imágenes para tener suficientes pares
        ArrayList<String> imageList = new ArrayList<>();
        for (int i = 0; i < numPairs; i++) {
            for (String imagePath : imagePaths) {
                imageList.add(imagePath);
            }
        }

        // Revolver las imágenes
        Collections.shuffle(imageList);

        // Crear y agregar botones con imágenes al grid pane
        for (int i = 0; i < numPairs * 2; i++) {
            final String imageName = imageList.get(i); // Declarar la variable imageName como final
            int row = i / 4; // Calcula la fila
            int col = i % 4; // Calcula la columna

            Button cardButton = new Button();
            cardButton.setOnAction(event -> {
                ImageView currentImageView = (ImageView) ((Button) event.getSource()).getGraphic();

                if (currentImageView.getImage().getUrl().equals(new Image(getClass().getResource("/imagenes/back.png").toExternalForm()).getUrl())) {
                    ImageView newImageView = new ImageView(new Image(getClass().getResource("/imagenes/" + imageName).toExternalForm()));
                    newImageView.setFitWidth(100);
                    newImageView.setFitHeight(100);
                    cardButton.setGraphic(newImageView);
                    // Añadir el botón seleccionado a la lista
                    selectedButtons.add(cardButton);
                    // Verificar si se seleccionaron dos botones
                    if (selectedButtons.size() == 2) {
                        // Comparar las imágenes de los botones seleccionados
                        ImageView img1 = (ImageView) selectedButtons.get(0).getGraphic();
                        ImageView img2 = (ImageView) selectedButtons.get(1).getGraphic();
                        if (img1.getImage().getUrl().equals(img2.getImage().getUrl())) {
                            // Si las imágenes son iguales, aumentar la puntuación del jugador actual
                            if (currentPlayer == 1) {
                                scorePlayer1++;
                                lblScore1.setText("Puntuación Jugador 1: " + scorePlayer1);
                            } else {
                                scorePlayer2++;
                                lblScore2.setText("Puntuación Jugador 2: " + scorePlayer2);
                            }
                            // Limpiar la lista de botones seleccionados
                            selectedButtons.clear();
                        } else {
                            // Si las imágenes no son iguales, cambiar al siguiente jugador y voltear las cartas seleccionadas después de un breve retraso
                            Timeline delayTimeline = new Timeline(new KeyFrame(Duration.seconds(0.5), e -> {
                                for (Button button : selectedButtons) {
                                    ImageView imageView = new ImageView(new Image(getClass().getResource("/imagenes/back.png").toExternalForm()));
                                    imageView.setFitWidth(100);
                                    imageView.setFitHeight(100);
                                    button.setGraphic(imageView);
                                }
                                selectedButtons.clear();
                                currentPlayer = (currentPlayer == 1) ? 2 : 1;
                                actualizaretiquetadegiro();
                            }));
                            delayTimeline.play();
                        }
                    }
                }
            });
            //carButton modifica el tamaño de los botones como tambien el tamaño de la fuente del boton
            cardButton.setPrefSize(100, 100);
            cardButton.setMinSize(100, 100);
            cardButton.setMaxSize(100, 100);
            cardButton.setStyle("-fx-font-size: 16px;");
            cardButton.setStyle("-fx-font-size: 16px;");

            cardButton.setOnAction(event -> {
                // Voltear la carta al hacer clic
                ImageView imageView = new ImageView(new Image(getClass().getResource("/imagenes/" + imageName).toExternalForm()));
                //manupulamos lo ancho y largo del boton
                imageView.setFitWidth(100);
                imageView.setFitHeight(100);
                cardButton.setGraphic(imageView);
                // Añadir el botón seleccionado a la lista
                selectedButtons.add(cardButton);
                // Verificar si se seleccionaron dos botones
                if (selectedButtons.size() == 2) {
                    // Comparar las imágenes de los botones seleccionados
                    ImageView img1 = (ImageView) selectedButtons.get(0).getGraphic();
                    ImageView img2 = (ImageView) selectedButtons.get(1).getGraphic();
                    if (img1.getImage().getUrl().equals(img2.getImage().getUrl())) {
                        // Si las imágenes son iguales, aumentar la puntuación del jugador actual
                        if (currentPlayer == 1) {
                            scorePlayer1++;
                            lblScore1.setText("Puntuación Jugador 1: " + scorePlayer1);
                        } else {
                            scorePlayer2++;
                            lblScore2.setText("Puntuación Jugador 2: " + scorePlayer2);
                        }
                        // Limpiar la lista de botones seleccionados
                        selectedButtons.clear();
                    } else {
                        // Si las imágenes no son iguales, cambiar al siguiente jugador
                        currentPlayer = (currentPlayer == 1) ? 2 : 1;
                        actualizaretiquetadegiro();
                        restablecerbotonesseleccionados();
                    }
                    if (scorePlayer1 + scorePlayer2 == numPairs) {
                        String winner;
                        if (scorePlayer1 > scorePlayer2) {
                            winner = "Jugador 1";
                        } else if (scorePlayer2 > scorePlayer1) {
                            winner = "Jugador 2";
                        } else {
                            winner = "Empate";
                        }
                        showAlert("¡Felicidades!", "El ganador es " + winner + "!", "Todos los pares se han encontrado.");
                    }
                }
            });

            cardGrid.add(cardButton, col, row);
            actualizaretiquetadegiro();
        }
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}