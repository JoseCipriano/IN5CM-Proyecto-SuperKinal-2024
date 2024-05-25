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
import org.josecipriano.dto.DistribuidorDTO;
import org.josecipriano.model.Cargos;
import org.josecipriano.model.Distribuidor;

/**
 * FXML Controller class
 *
 * @author informatica
 */
public class MenuDistribuidorController implements Initializable {
    public Main stage;
    private int op = 0;
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private ResultSet resultSet = null;

    
    
    @FXML
    Button btnRegresar, btnAgregar, btnEliminar, btnEditar, btnBuscar, btnVaciar;
    @FXML 
    TextField tfNombre, tfDistribuidorId, tfNit,tfBuscar, tfDireccion , tfWeb, tfTelefono;
    @FXML
    TableView tblDistribuidor;
    @FXML
    TableColumn colDistribuidorId, colNombre, colDireccion, colNit, colTelefono, colWeb;
    @FXML
    public void handleButtonAction(ActionEvent event){
    if(event.getSource() == btnRegresar){
        stage.menuPrincipalView();
    }else if (event.getSource() == btnAgregar){
        if(!tfNombre.getText().equals("") && !tfDireccion.getText().equals("") &&!tfNit.getText().equals("") &&!tfTelefono.getText().equals("") &&!tfWeb.getText().equals("")){
            agregarDistribuidores();
            cargarDatos();
        }
    }else if (event.getSource() == btnEliminar){
        eliminarDistribuidor(((Distribuidor)tblDistribuidor.getSelectionModel().getSelectedItem()).getDistribuidorId());
        cargarDatos();
    
    
    
    }else if (event.getSource() == btnEditar){
        if(!tfNombre.getText().equals("") && !tfDireccion.getText().equals("") &&!tfNit.getText().equals("") &&!tfTelefono.getText().equals("") &&!tfWeb.getText().equals("")){
        editarDistribuidor();
        DistribuidorDTO.getDistribuidorSTO().setDistribuidor(null);
        cargarDatos();
        
        }
    
    }else if (event.getSource() == btnBuscar){
    tblDistribuidor.getItems().clear();
    tfBuscar.getText().equals("");
    tblDistribuidor.getItems().add(buscarDistribuidor());
    
    
    }else if(event.getSource() == btnVaciar){
        vaciarForm();
    }
    
   }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
        // TODO
    }   
    
    
    public void cargarDatos(){
        tblDistribuidor.setItems(listarDistribuidor());
        colDistribuidorId.setCellValueFactory(new PropertyValueFactory<Distribuidor, Integer> ("distribuidorId"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Distribuidor, String> ("NombreDistribuidor"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<Distribuidor, String> ("DireccionDistribuidor"));
        colNit.setCellValueFactory(new PropertyValueFactory<Distribuidor, String> ("nitDistribuidor"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<Distribuidor, String> ("telefonoDistribuidor"));
        colWeb.setCellValueFactory(new PropertyValueFactory<Distribuidor, String> ("web"));
    
    
    }
    
    public ObservableList<Distribuidor> listarDistribuidor(){
        ArrayList<Distribuidor> distribuidor = new ArrayList<>();
         try{
       conexion = Conexion.getInstance().obtenerConexion();
       String sql = "call sp_ListarDistribuidores()";
       statement = conexion.prepareStatement(sql);
       resultSet = statement.executeQuery();
       
       while(resultSet.next()){
           int distribuidorId = resultSet.getInt("distribuidorId");
           String nombreDistribuidor = resultSet.getString("NombreDistribuidor");
           String direccionDistribuidor = resultSet.getString("DireccionDistribuidor");
           String nitDistribuidor = resultSet.getString("nitDistribuidor");
           String telefonoDistribuidor = resultSet.getString("TelefonoDistribuidor");
           String web = resultSet.getString("web");
           
           distribuidor.add(new Distribuidor(distribuidorId, nombreDistribuidor, direccionDistribuidor, nitDistribuidor, telefonoDistribuidor,web));
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
    
    
    
    return FXCollections.observableList(distribuidor);
    
    
    }
    
    public void agregarDistribuidores(){
         try{
       conexion = Conexion.getInstance().obtenerConexion();
       String sql = "call sp_agregarDistribuidores(?,?,?,?,?)";
       statement = conexion.prepareStatement(sql);
       statement.setString(1,tfNombre.getText());
       statement.setString(2,tfDireccion.getText());
       statement.setString(3,tfNit.getText());
       statement.setString(4,tfTelefono.getText());
       statement.setString(5,tfWeb.getText());
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
    
    public void eliminarDistribuidor(int distId ){
        
        try{
       conexion = Conexion.getInstance().obtenerConexion();
       String sql = "call sp_eliminarDistribuidores(?)";
       statement = conexion.prepareStatement(sql);
       statement.setInt(1, distId);
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
    
    public void editarDistribuidor(){
        try{
       conexion = Conexion.getInstance().obtenerConexion();
       String sql = "call sp_EditarDistribuidores(?,?,?,?,?,?)";
       statement = conexion.prepareStatement(sql);
       statement.setInt(1, Integer.parseInt(tfDistribuidorId.getText()));
       statement.setString(2, tfNombre.getText());
       statement.setString(3, tfDireccion.getText());
       statement.setString(4, tfNit.getText());
       statement.setString(5, tfTelefono.getText());
       statement.setString(6, tfWeb.getText());
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
    
    public Distribuidor buscarDistribuidor(){
     Distribuidor distribuidor = null;
      
      try{
       conexion = Conexion.getInstance().obtenerConexion();
       String sql = "call sp_BuscarDistribuidores(?)";
       statement = conexion.prepareStatement(sql);
       statement.setInt(1, Integer.parseInt(tfBuscar.getText()));
       resultSet = statement.executeQuery();
       
       if(resultSet.next()){
            int distribuidorId = resultSet.getInt("distribuidorId");
           String nombreDistribuidor = resultSet.getString("NombreDistribuidor");
           String direccionDistribuidor = resultSet.getString("DireccionDistribuidor");
           String nitDistribuidor = resultSet.getString("nitDistribuidor");
           String telefonoDistribuidor = resultSet.getString("TelefonoDistribuidor");
           String web = resultSet.getString("web");
           
           distribuidor = new Distribuidor(distribuidorId, nombreDistribuidor, direccionDistribuidor, nitDistribuidor, telefonoDistribuidor,web);
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
        
    return  distribuidor;
    
    
    
    }
    
    
    @FXML
    public void cargarForm(){
        Distribuidor dist = (Distribuidor)tblDistribuidor.getSelectionModel().getSelectedItem();
        if(dist != null){
            tfDistribuidorId.setText(Integer.toString(dist.getDistribuidorId()));
            tfNombre.setText(dist.getNombreDistribuidor());
            tfDireccion.setText(dist.getDireccionDistribuidor());
            tfNit.setText(dist.getNitDistribuidor());
            tfTelefono.setText(dist.getTelefonoDistribuidor());
            tfWeb.setText(dist.getWeb());
        
        }
    
 }
    
    public void vaciarForm(){
        tfDistribuidorId.clear();
        tfNombre.clear();
        tfDireccion.clear();
        tfNit.clear();
        tfTelefono.clear();
        tfWeb.clear();
    }

    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    
}
