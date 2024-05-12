/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josecipriano.utils;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author informatica
 */
public class SuperKinalAlert {
    private static SuperKinalAlert instance;
    
    private SuperKinalAlert(){
    
    }
    
    public static SuperKinalAlert getInstance(){
        if(instance == null){
            instance = new SuperKinalAlert();
        }
    return instance;
    }

    public void mostrarAlertaInformacion(int code){
        if(code == 400){ 
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmación Registro");
            alert.setHeaderText("Confirmación Registro");
            alert.setContentText("¡Registro realizado con éxito!");
            alert.showAndWait();
        } else if(code == 500){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Edición Registro");
            alert.setHeaderText("Edición Registro");
            alert.setContentText("¡Edición realizada con éxito!");
            alert.showAndWait();
        } else if(code == 600){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos Pendientes");
            alert.setHeaderText("Campos Pendientes");
            alert.setContentText("¡Algunos campos necesarios para el registro están vacíos!");
            alert.showAndWait();
        }
    }
    
    public Optional<ButtonType> mostrarAlertaConfirmacion(int code){
        Optional<ButtonType> action = null;
        
        if(code == 404){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Eliminación Registro");
            alert.setHeaderText("Eliminación Registro");
            alert.setContentText("¿Desea confirmar la eliminación del registro?");
            action = alert.showAndWait();
        }else if(code == 505){ 
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Edición Registro");
            alert.setHeaderText("Edición Registro");
            alert.setContentText("¿Desea confirmar la edición del registro?");
            action = alert.showAndWait();
        }
        
        return action;
    }
 }
    
   
    
    









