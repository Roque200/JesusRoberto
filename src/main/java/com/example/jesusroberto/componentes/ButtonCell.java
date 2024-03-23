package com.example.jesusroberto.componentes;

import com.example.jesusroberto.modelos.EmpleadosDAO;
import com.example.jesusroberto.vistas.EmpleadosForm;
import javafx.scene.control.*;

import java.util.Optional;

public class ButtonCell extends TableCell<EmpleadosDAO,String> {
    Button btnCelda;
    int opc;
    EmpleadosDAO objEmp;

    public ButtonCell(int opc){
        this.opc = opc;
        String txtButtom = (opc == 1) ? "Editar" : "Eliminar";
        btnCelda = new Button(txtButtom);
        btnCelda.setOnAction(event -> AccionBoton(opc));

    }

    private void AccionBoton(int opc) {
        TableView<EmpleadosDAO> tbvEmpleados = ButtonCell.this.getTableView();
        objEmp = tbvEmpleados.getItems().get(ButtonCell.this.getIndex());
        if (opc == 1){
            //Editar
            new EmpleadosForm(tbvEmpleados, objEmp);


        }else {
            //Eliminar
            Alert alert  = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Mensaje del sistema");
            alert.setHeaderText("Confirmacion de Accion");
            alert.setContentText("¿Deseas borrar el Empleado: "+objEmp.getIdEmpleado()+"?");
            Optional<ButtonType> result = alert.showAndWait();
            if ( result.get() == ButtonType.OK ){
                objEmp.ELIMINAR();
                tbvEmpleados.setItems(objEmp.CONSULTAR());
                tbvEmpleados.refresh();
            }
        }
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if ( !empty)
            this.setGraphic(btnCelda);
    }
}
