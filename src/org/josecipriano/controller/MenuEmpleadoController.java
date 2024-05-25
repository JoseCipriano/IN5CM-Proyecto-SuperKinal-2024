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
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.josecipriano.System.Main;
import org.josecipriano.dao.Conexion;
import org.josecipriano.model.Cargos;
import org.josecipriano.model.Cliente;
import org.josecipriano.model.Empleados;
import org.josecipriano.model.Encargado;

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
    Button btnAgregar,btnEliminar,btnEditar,btnBuscar, btnRegresar;
    
    @FXML
    TextField tfBuscar, tfNombre,tfApellido, tfSueldo, tfSalida, tfEntrada , tfEmpleadoId;
    
    @FXML
    ComboBox cmbCargo, cmbEncargado;
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnRegresar){
            stage.menuPrincipalView();
         } else if (event.getSource() == btnAgregar){
             if(tfEmpleadoId.getText().equals("")){
                 agregarEmpleados();
                 cargarDatos();
             
             }
         
         }
        
        
    
    
    
    
    
   
 }
  
     public void cargarDatos(){
         if(op == 3){
         
         
         
         }else{
             tblEmpleados.setItems(listarEmpleados());
         }
     colEmpleadoId.setCellValueFactory(new PropertyValueFactory<Empleados, Integer> ("EmpleadoId"));
     colNombre.setCellValueFactory(new PropertyValueFactory<Empleados , String> ("nombreEmpleado"));
     colApellido.setCellValueFactory(new PropertyValueFactory<Empleados , String> ("apellidoEmpleado"));
     colSueldo.setCellValueFactory(new PropertyValueFactory<Empleados, Double> ("sueldo"));
     colHentrada.setCellValueFactory(new PropertyValueFactory<Empleados, Time> ("HoraEntrada"));
     colHsalida.setCellValueFactory(new PropertyValueFactory<Empleados, Time> ("HoraSalida"));
     colCargo.setCellValueFactory(new PropertyValueFactory<Empleados, String> ("cargo"));
     colEncargado.setCellValueFactory(new PropertyValueFactory<Empleados, Integer> ("Encargado"));
     
     
     }
    
     public int obtenerIndexCargo(){
         int index = 0;
         String cargoTbl = ((Empleados)tblEmpleados.getSelectionModel().getSelectedItem()).getCargo();
         for(int i = 0; i <= cmbCargo.getItems().size(); i++){
             String cargoCmb = cmbCargo.getItems().get(i).toString();
             
             if(cargoTbl.equals(cargoCmb)){
                 index = i;
                 break;
             }
         
         }
     return index;
     
    }
     
     public int obtenerIndexEncargado(){
         int index = 0;
        String encargadoTbl = ((Empleados)tblEmpleados.getSelectionModel().getSelectedItem()).getEncargado();
        for(int i = 0 ; i <= cmbEncargado.getItems().size() ; i++){
            String encargadoCmb = cmbEncargado.getItems().get(i).toString();
            
            if(encargadoTbl.equals(encargadoCmb)){
                index = i;
                break;
            }
        }
        
        return index;
     
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
                 double sueldo = resultSet.getDouble("sueldo");
                 Time horaEntrada = resultSet.getTime("horaEntrada");
                 Time horaSalida = resultSet.getTime("horaSalida");
                 String cargo = resultSet.getString("cargo");
                 String encargado = resultSet.getString("encargado");
                 
                 empleados.add(new Empleados(empleadoId, nombreEmpleado, apellidoEmpleado, sueldo,horaEntrada,horaSalida, cargo, encargado));
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
   
     public ObservableList<Cargos> listarCargos(){
        ArrayList<Cargos> cargo = new ArrayList<>();
        
       try{
       conexion = Conexion.getInstance().obtenerConexion();
       String sql = "call sp_ListarCargos()";
       statement = conexion.prepareStatement(sql);
       resultSet = statement.executeQuery();
       
       while(resultSet.next()){
           int cargoId = resultSet.getInt("cargoId");
           String nombreCargo = resultSet.getString("NombreCargo");
           String descripcionCargo = resultSet.getString("DescripcionCargo");
           
           cargo.add(new Cargos(cargoId , nombreCargo,descripcionCargo));
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
        
       return FXCollections.observableList(cargo);
    }
    
    public ObservableList<Encargado> listarEncargado(){
    ArrayList<Encargado> encargados = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_listarEmpleados()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int empleadoId = resultSet.getInt("empleadoId");
                
                encargados.add(new Encargado(empleadoId));
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
        
        return FXCollections.observableList(encargados);
    
    
    }
    
    @FXML
    public void cargarForm(){
        Empleados e = (Empleados)tblEmpleados.getSelectionModel().getSelectedItem();
        Time horaEntrada = e.getHoraEntrada();
        Time horaSalida = e.getHoraSalida();
        
        if(e != null){
            tfEmpleadoId.setText(Integer.toString(e.getEmpleadoId()));
            tfNombre.setText(e.getNombreEmpleado());
            tfApellido.setText(e.getApellidoEmpleado());
            tfSueldo.setText(Double.toString(e.getSueldo()));
            tfEntrada.setText(horaEntrada.toString());
            tfSalida.setText(horaSalida.toString());
            cmbCargo.getSelectionModel().select(obtenerIndexCargo());
            cmbEncargado.getSelectionModel().select(obtenerIndexEncargado());
        
        
        
        
        
        }
    
    }
    
    public void agregarEmpleados(){
         try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_agregarEmpleados(?, ?, ?, ?, ?, ?, ?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, tfNombre.getText());
            statement.setString(2, tfApellido.getText());
            statement.setDouble(3, Double.parseDouble(tfSueldo.getText()));
            statement.setTime(4, Time.valueOf(tfEntrada.getText()));
            statement.setTime(5, Time.valueOf(tfSalida.getText()));
            statement.setInt(6,((Cargos) cmbCargo.getSelectionModel().getSelectedItem()).getCargoId());
            statement.setInt(7,((Encargado)cmbEncargado.getSelectionModel().getSelectedItem()).getEncargadoId());
            statement.execute();
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(statement!= null){
                    statement.close();
                }
                if(conexion != null){
                    conexion.close();
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
       
    
    
    
    
    
    
    
    
    
    
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
