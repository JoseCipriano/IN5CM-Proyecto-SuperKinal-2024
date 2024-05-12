/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josecipriano.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.MenuItem;
import org.josecipriano.System.Main;

/**
 * FXML Controller class
 *
 * @author Jose
 */
public class MenuPrincipalViewController implements Initializable {
    private Main Stage;
    
    @FXML 
    MenuItem btnClientes, btnTicketSoporte, btnCargos, btnCategoria, btnEmpleados, btnFacturas, btnDistribuidores , btnProductos,btnPromociones,btnDetalleFactura,btnCompras, btnDetalleCompras  ;
    
    @FXML 
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnClientes){
            Stage.menuClienteView();
        
        }else if (event.getSource() == btnTicketSoporte){
            Stage.menuTicketSoporteView();
        } else if (event.getSource() == btnCargos){
            Stage.menuCargosView();
        }else if (event.getSource() == btnCategoria){
            Stage.menuCategoriasProductos();
        }else if (event.getSource() == btnEmpleados){
            Stage.menuEmpleado();
        }else if (event.getSource() == btnEmpleados){
            Stage.menuFacturas();
        }else if (event.getSource() == btnDistribuidores){
            Stage.menuDistribuidores();
        }else if (event.getSource() == btnProductos){
            Stage.menuProductos();
        }else if (event.getSource() == btnPromociones){
            Stage.menuPromociones();
        }else if (event.getSource() == btnDetalleFactura){
            Stage.menuDetalleFacturas();
        }else if (event.getSource() == btnCompras){
            Stage.menuCompras();
        }else if (event.getSource() == btnDetalleCompras){
            Stage.menuDetalleCompras();
        }else if (event.getSource() == btnFacturas){
            Stage.menuFacturas();
        }
     }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resource) {
        // TODO
    }    

    public Main getStage() {
        return Stage;
    }

    public void setStage(Main Stage) {
        this.Stage = Stage;
    }
    
}
