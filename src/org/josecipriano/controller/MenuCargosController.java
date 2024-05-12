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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.josecipriano.System.Main;
import org.josecipriano.dao.Conexion;
import org.josecipriano.dto.CargosDTO;
import org.josecipriano.model.Cargos;
  
/**
 * FXML Controller class
 *
 * @author informatica
 */
public class MenuCargosController implements Initializable {
    private Main stage;
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private ResultSet resultSet = null;
    
    @FXML
    TextField tfcargoId, tfnombreCargo, tfbuscar;
    @FXML
    TextArea taDescripcion;
    @FXML
    TableView tblCargos;
    @FXML
    TableColumn colCargoId, colNombre, colDescripcion;
    @FXML
    Button btnAgregar ,btnVaciar , btnEditar , btnEliminar, btnRegresar, btnBuscar;
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnRegresar){
            stage.menuPrincipalView();
        } else if(event.getSource() == btnAgregar){
            if(!tfnombreCargo.getText().equals("") && !taDescripcion.getText().equals("")){
                agregarCargos();
                cargarDatos();
            }
        } else if(event.getSource() == btnEditar){
        
        if(!tfnombreCargo.getText().equals("") && !taDescripcion.getText().equals("")){
      
            CargosDTO.getCargosDTO().setCargos((Cargos)tblCargos.getSelectionModel().getSelectedItem());
        editarCargos();
       
        cargarDatos();
        }
      } else if (event.getSource() == btnEliminar){
          eliminarCargo(((Cargos)tblCargos.getSelectionModel().getSelectedItem()).getCargoId());
          cargarDatos();
      
      
      }else if (event.getSource() == btnBuscar){
          tblCargos.getItems().clear();
          if(tfbuscar.getText().equals("")){
              cargarDatos();
          }
      }
    
    
    
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
    } 
    
    
    public void cargarDatos(){
     tblCargos.setItems(listarCargos());
    colCargoId.setCellValueFactory(new PropertyValueFactory<Cargos, Integer> ("cargoId"));
    colNombre.setCellValueFactory(new PropertyValueFactory<Cargos, String> ("NombreCargo"));
    colDescripcion.setCellValueFactory(new PropertyValueFactory<Cargos, String> ("DescripcionCargo"));
    
    
    
    
    
    
    }
    
    public void agregarCargos(){
      
       try{
       conexion = Conexion.getInstance().obtenerConexion();
       String sql = "call sp_agregarCargos(?,?)";
       statement = conexion.prepareStatement(sql);
       statement.setString(1,tfnombreCargo.getText());
       statement.setString(2,taDescripcion.getText());
       statement.execute();
      
       }catch(SQLException e){
        System.out.println(e.getMessage());
       }finally{
       try{
               
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
   }
    
    @FXML
    public void cargarForm(){
     Cargos cr = (Cargos)tblCargos.getSelectionModel().getSelectedItem();
     if(cr != null){
     tfcargoId.setText(Integer.toString(cr.getCargoId()));
     tfnombreCargo.setText(cr.getNombreCargo());
     taDescripcion.setText(cr.getDescripcionCargo());
     }
   }
    
    public void editarCargos(){
    
       try{
       conexion = Conexion.getInstance().obtenerConexion();
       String sql = "call sp_EditarCargos(?,?,?)";
       statement = conexion.prepareStatement(sql);
       statement.setInt(1, Integer.parseInt(tfcargoId.getText()));
       statement.setString(2, tfnombreCargo.getText());
       statement.setString(3, taDescripcion.getText());
       statement.execute();
      
       }catch(SQLException e){
        System.out.println(e.getMessage());
       }finally{
       try{
               
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
    
 }
    
    public void eliminarCargo(int carId){
       try{
       conexion = Conexion.getInstance().obtenerConexion();
       String sql = "call sp_eliminarCargos(?)";
       statement = conexion.prepareStatement(sql);
       statement.setInt(1, carId);
       statement.execute();
      
       }catch(SQLException e){
        System.out.println(e.getMessage());
       }finally{
       try{
               
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
    }
    
    public Cargos buscarCargos(){
    Cargos cargos = null;
    
    try{
       conexion = Conexion.getInstance().obtenerConexion();
       String sql = "call sp_BuscarCargos(?)";
       statement = conexion.prepareStatement(sql);
       statement.setInt(1, Integer.parseInt(tfcargoId.getText()));
       resultSet = statement.executeQuery();
       
       if(resultSet.next()){
           int cargoId = resultSet.getInt("cargoId");
           String nombreCargo = resultSet.getString("nombreCargo");
           String descripcionCargo = resultSet.getString("descripcionCargo");
          
           cargos = new Cargos(cargoId , nombreCargo, descripcionCargo);
       }  
      
       }catch(SQLException e){
        System.out.println(e.getMessage());
       }finally{
       try{
               
            if(statement != null){
                  statement.close();
               }
            if(conexion != null){
                 conexion.close();
                }
            if(resultSet != null){
                resultSet.close();
            }
      
       }catch(SQLException e){
        System.out.println(e.getMessage());
       }  
       }
     return    cargos;
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

    
    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    
}
