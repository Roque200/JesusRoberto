package com.example.jesusroberto.componentes;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;

public class HiloImpresion extends Thread {
    private ProgressBar pgbCarril;
    private int numeroHojas;
    private Runnable onSucceeded;
    private volatile boolean running = true;

    public HiloImpresion(ProgressBar pgbCarril, int numeroHojas, Runnable onSucceeded) {
        this.pgbCarril = pgbCarril;
        this.numeroHojas = numeroHojas;
        this.onSucceeded = onSucceeded;
    }

    public void stopImpresion() {
        running = false;
        this.interrupt();
    }

    @Override
    public void run() {
        double avance = 0;
        double incremento = 1.0 / numeroHojas;
        while (avance < 1 && running) {
            avance += incremento;
            double finalAvance = avance;
            Platform.runLater(() -> pgbCarril.setProgress(finalAvance));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                if (!running) {
                    Platform.runLater(() -> pgbCarril.setProgress(0));
                    System.out.println("Hilo de impresión interrumpido");
                    return;
                }
            }
        }
        if (running) {
            Platform.runLater(onSucceeded);
        } else {
            System.out.println("Impresión interrumpida");
        }
    }
}
