/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josecipriano.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.josecipriano.System.Main;
import org.josecipriano.dao.Conexion;
import org.josecipriano.model.Cliente;
import org.josecipriano.model.Empleados;

/**
 * FXML Controller class
 *
 * @author informatica
 */
public class MenuEmpleadoController implements Initializable {
    public Main stage;
    private int op;

    /**
     * Initializes the controller class.
     */
    private static Connection conexion;
    private static PreparedStatement statement;
    private static ResultSet resultSet;
    
    @FXML
    TableView tblEmpleados;
    
    @FXML
    TableColumn colEmpleadoId, colNombre, colApellido, colSueldo, colHentrada, colHsalida,colCargo,colEncargado;
    
    @FXML
    Button tbnAgregar,btnEliminar,btnEditar,btnBuscar, btnRegresar;
    
    @FXML
    TextField tfBuscar;
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnRegresar){
            stage.menuPrincipalView();
         }
        
        
    
    
    
    
    
   
 }
  
     public void cargarDatos(){
         if(op == 3){
         
         
         
         }else{
             tblEmpleados.setItems(listarEmpleados());
         }
     colEmpleadoId.setCellValueFactory(new PropertyValueFactory<Empleados, Integer> ("EmpleadoId"));
     colNombre.setCellValueFactory(new PropertyValueFactory<Empleados , String> ("Nombre"));
     colApellido.setCellValueFactory(new PropertyValueFactory<Empleados , String> ("Apellido"));
     colSueldo.setCellValueFactory(new PropertyValueFactory<Empleados, Integer> ("Sueldo"));
     colHentrada.setCellValueFactory(new PropertyValueFactory<Empleados, Integer> ("Hora Entrada"));
     colHsalida.setCellValueFactory(new PropertyValueFactory<Empleados, Integer> ("Hora Salida"));
     colCargo.setCellValueFactory(new PropertyValueFactory<Empleados, Integer> ("cargo"));
     colEncargado.setCellValueFactory(new PropertyValueFactory<Empleados, Integer> ("Encargado"));
     
     
     }
    
    
    public ObservableList<Empleados> listarEmpleados(){
      ArrayList<Empleados> empleados = new ArrayList<>();
        
        try {
             conexion = Conexion.getInstance().obtenerConexion();
             String sql = "call sp_ListarEmpleados()";
             statement = conexion.prepareStatement(sql);
             resultSet = statement.executeQuery();
             
             while(resultSet.next()){
                 int empleadoId = resultSet.getInt("EmpleadoID");
                 String nombreEmpleado = resultSet.getString("nombreEmpleado");
                 String apellidoEmpleado = resultSet.getString("apellidoEmpleado");
                 int sueldo = resultSet.getInt("sueldo");
                 int horaEntrada = resultSet.getInt("horaEntrada");
                 int horaSalida = resultSet.getInt("horaSalida");
                 int cargoId = resultSet.getInt("cargoId");
                 int encargadoId = resultSet.getInt("encargadoId");
                 
                 empleados.add(new Empleados(empleadoId, nombreEmpleado, apellidoEmpleado, sueldo,horaEntrada,horaSalida, cargoId, encargadoId));
             }
             
            }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                } 
                if(statement != null){
                    statement.close();
                }
                if(conexion != null){
                    conexion.close();
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        
        
        }
        
        return FXCollections.observableList(empleados);
      }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
    }    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    
}
