package com.example.jesusroberto.vistas;

import com.example.jesusroberto.modelos.EmpleadosDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EmpleadosForm extends Stage {
        private TableView<EmpleadosDAO> tbvEmpleados;
        private EmpleadosDAO objEmp;
        String[] arPromts = {"Nombre Empleado","RFC del Empleado","Sueldo del Empleado","Telefono del empleado","direccion del empleado"};
        private Scene escena;
        private TextField[] arTxtCampos = new TextField[5];
        private Button btnGuardar ;
        private VBox vbxPrincipal;


    public EmpleadosForm(TableView<EmpleadosDAO> tbvEmp,EmpleadosDAO objEmp) {
        tbvEmpleados = tbvEmp;
        this.objEmp = ( objEmp == null ) ? new EmpleadosDAO() : objEmp;
        CrearUI();
        this.setTitle("Insertar Usuario");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        vbxPrincipal = new VBox();
        vbxPrincipal.setPadding(new Insets(10));
        vbxPrincipal.setSpacing(10);
        vbxPrincipal.setAlignment(Pos.CENTER);
        for (int i = 0; i < arTxtCampos.length; i++){
            arTxtCampos[i] = new TextField();
            arTxtCampos[i].setPromptText(arPromts[i]);
            vbxPrincipal.getChildren().add(arTxtCampos[i]);
        }
        LlenarForm();
        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> GuardarEmpleado());
        vbxPrincipal.getChildren().add(btnGuardar);
        escena = new Scene(vbxPrincipal,350,250);
    }

    private void LlenarForm() {
        arTxtCampos[0].setText(objEmp.getNomEmpleado());
        arTxtCampos[1].setText(objEmp.getRfcEmpleado());
        arTxtCampos[2].setText(String.valueOf(objEmp.getSalario()));
        arTxtCampos[3].setText(objEmp.getTelefono());
        arTxtCampos[4].setText(objEmp.getDireccion());
    }

    private void GuardarEmpleado() {
        objEmp.setNomEmpleado(arTxtCampos[0].getText());
        objEmp.setRfcEmpleado(arTxtCampos[1].getText());
        objEmp.setSalario(Float.parseFloat(arTxtCampos[2].getText()));
        objEmp.setTelefono(arTxtCampos[3].getText());
        objEmp.setDireccion(arTxtCampos[4].getText());

        if ( objEmp.getIdEmpleado() > 0)
             objEmp.ACTUALIZAR();
        else
             objEmp.INSERTAR();
        tbvEmpleados.setItems(objEmp.CONSULTAR());
        tbvEmpleados.refresh();

        arTxtCampos[0].clear();
        arTxtCampos[1].clear();
        arTxtCampos[2].clear();
        arTxtCampos[3].clear();
        arTxtCampos[4].clear();
    }
}
