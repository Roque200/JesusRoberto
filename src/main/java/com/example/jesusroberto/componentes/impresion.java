package com.example.jesusroberto.componentes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class impresion extends Stage {
    private TableView<Tarea> tablaTareas;
    private ObservableList<Tarea> datos;
    private ProgressBar barraProgreso;
    private Thread hiloSimulador;
    private volatile boolean simuladorActivo = true;
    private final Object controlSimulador = new Object();
    private LinkedBlockingQueue<Tarea> colaImpresion = new LinkedBlockingQueue<>();
    private volatile HiloImpresion hiloImpresionActual;

    public impresion() {
        setTitle("Simulador de Impresión");


        tablaTareas = new TableView<>();
        datos = FXCollections.observableArrayList();

        TableColumn<Tarea, Integer> colNoArchivo = new TableColumn<>("No. Archivo");
        colNoArchivo.setCellValueFactory(cellData -> cellData.getValue().noArchivoProperty().asObject());

        TableColumn<Tarea, String> colNombreArchivo = new TableColumn<>("Nombre de Archivo");
        colNombreArchivo.setCellValueFactory(cellData -> cellData.getValue().nombreArchivoProperty());

        TableColumn<Tarea, Integer> colNumHojas = new TableColumn<>("Número de Hojas");
        colNumHojas.setCellValueFactory(cellData -> cellData.getValue().numHojasProperty().asObject());

        TableColumn<Tarea, String> colHoraAcceso = new TableColumn<>("Hora de Acceso");
        colHoraAcceso.setCellValueFactory(cellData -> cellData.getValue().horaAccesoProperty());

        tablaTareas.getColumns().addAll(colNoArchivo, colNombreArchivo, colNumHojas, colHoraAcceso);
        tablaTareas.setItems(datos);

        barraProgreso = new ProgressBar(0);

        Button btnAgregarTarea = new Button("Agregar Tarea");
        btnAgregarTarea.setOnAction(e -> agregarTarea());

        Button btnSimulador = new Button("Apagar Simulador");
        btnSimulador.setOnAction(e -> toggleSimulador(btnSimulador));

        GridPane layout = new GridPane();
        layout.add(tablaTareas, 0, 0, 2, 1);
        layout.add(btnAgregarTarea, 0, 1);
        layout.add(btnSimulador, 1, 1);
        layout.add(barraProgreso, 0, 2, 2, 1);

        Scene scene = new Scene(layout, 600, 400);
        scene.getStylesheets().add(getClass().getResource("/estilos/estilos.css").toExternalForm());
        setScene(scene);
        show();

        iniciarSimulador();
    }

    private void agregarTarea() {
        Random rand = new Random();
        int noArchivo = datos.size() + 1;
        String nombreArchivo = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".txt";
        int numHojas = rand.nextInt(50) + 1;
        String horaAcceso = new SimpleDateFormat("HH:mm:ss").format(new Date());

        Tarea nuevaTarea = new Tarea(noArchivo, nombreArchivo, numHojas, horaAcceso);
        datos.add(nuevaTarea);
        colaImpresion.add(nuevaTarea);
        System.out.println("Tarea agregada: " + nombreArchivo);
    }

    private void iniciarSimulador() {
        hiloSimulador = new Thread(() -> {
            while (true) {
                try {
                    Tarea tarea = colaImpresion.take();
                    System.out.println("Tarea tomada de la cola: " + tarea.getNombreArchivo());
                    synchronized (controlSimulador) {
                        while (!simuladorActivo) {
                            System.out.println("Simulador en pausa...");
                            controlSimulador.wait();
                        }
                    }
                    hiloImpresionActual = new HiloImpresion(barraProgreso, tarea.getNumHojas(), () -> {
                        datos.remove(tarea);
                        barraProgreso.setProgress(0);
                        System.out.println("Tarea completada: " + tarea.getNombreArchivo());
                    });
                    hiloImpresionActual.start();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Hilo simulador interrumpido");
                }
            }
        });
        hiloSimulador.setDaemon(true);
        hiloSimulador.start();
    }

    private void toggleSimulador(Button btnSimulador) {
        simuladorActivo = !simuladorActivo;
        if (simuladorActivo) {
            synchronized (controlSimulador) {
                controlSimulador.notifyAll();
                System.out.println("Simulador reanudado");
            }
        } else {
            if (hiloImpresionActual != null && hiloImpresionActual.isAlive()) {
                hiloImpresionActual.stopImpresion();
            }
            System.out.println("Simulador pausado");
        }
        btnSimulador.setText(simuladorActivo ? "Apagar Simulador" : "Encender Simulador");
    }
}
