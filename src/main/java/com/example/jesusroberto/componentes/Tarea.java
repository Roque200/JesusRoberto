package com.example.jesusroberto.componentes;

import javafx.beans.property.*;

public class Tarea {
    private final IntegerProperty noArchivo;
    private final StringProperty nombreArchivo;
    private final IntegerProperty numHojas;
    private final StringProperty horaAcceso;

    public Tarea(int noArchivo, String nombreArchivo, int numHojas, String horaAcceso) {
        this.noArchivo = new SimpleIntegerProperty(noArchivo);
        this.nombreArchivo = new SimpleStringProperty(nombreArchivo);
        this.numHojas = new SimpleIntegerProperty(numHojas);
        this.horaAcceso = new SimpleStringProperty(horaAcceso);
    }

    public int getNoArchivo() {
        return noArchivo.get();
    }

    public IntegerProperty noArchivoProperty() {
        return noArchivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo.get();
    }

    public StringProperty nombreArchivoProperty() {
        return nombreArchivo;
    }

    public int getNumHojas() {
        return numHojas.get();
    }

    public IntegerProperty numHojasProperty() {
        return numHojas;
    }

    public String getHoraAcceso() {
        return horaAcceso.get();
    }

    public StringProperty horaAccesoProperty() {
        return horaAcceso;
    }
}
