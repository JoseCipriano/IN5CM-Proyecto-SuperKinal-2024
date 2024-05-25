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
import org.josecipriano.System.Main;
import org.josecipriano.dao.Conexion;
import org.josecipriano.dto.CategoriaProductoDTO;
import org.josecipriano.model.CategoriaProducto;


/**
 * FXML Controller class
 *
 * @author informatica
 */
public class MenuCategoriaProductoController implements Initializable {
    private Main stage;
    private int op;
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private ResultSet resultSet = null;
    
    @FXML
    TextField tfBuscar, tfcategoriaId,tfNombreCategoria;
    @FXML
    TextArea taDescripcion;
    @FXML
    TableView tblCategoria;
    @FXML
    TableColumn colCategoriaId , colNombreCategoria, colDescripcionCategoria;
    @FXML
    Button btnAgregar, btnEliminar, btnEditar, btnRegresar, btnBuscar, btnVaciar; 
            
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnRegresar){
            stage.menuPrincipalView();
        } else if(event.getSource() == btnAgregar){
            agregarCategoriaProductos();
            cargarDatos();
        } else if (event.getSource() == btnEliminar){
            eliminarCategoriaProducto(((CategoriaProducto)tblCategoria.getSelectionModel().getSelectedItem()).getCategoriaProductoId());
            cargarDatos();
        }else  if (event.getSource() == btnBuscar){
            tblCategoria.getItems().clear();
            tfBuscar.getText().equals("");
            tblCategoria.getItems().add(buscarCategoriaProducto());
            
            
        
        } else if (event.getSource() == btnEditar){
            if(!tfNombreCategoria.getText().equals("") && !taDescripcion.getText().equals("")){
                editarCategoriaProducto();
                CategoriaProductoDTO.getCategoriaProductoDTO().setCategoriaProducto(null);
                cargarDatos();
            }
        
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
    }    

   public void cargarDatos(){
      
        tblCategoria.setItems(listarCategoriaProducto());
        colCategoriaId.setCellValueFactory(new PropertyValueFactory<CategoriaProducto, Integer> ("categoriaProductoId"));
        colNombreCategoria.setCellValueFactory(new PropertyValueFactory<CategoriaProducto, String> ("nombreCategoria"));
        colDescripcionCategoria.setCellValueFactory(new PropertyValueFactory<CategoriaProducto, String> ("descripcionCategoria"));
        
        
        
      }
      
   public ObservableList<CategoriaProducto> listarCategoriaProducto(){
        ArrayList<CategoriaProducto> categoriaProducto = new ArrayList<>();
        
        try {
             conexion = Conexion.getInstance().obtenerConexion();
             String sql = "call sp_ListarCategoriaProductos()";
             statement = conexion.prepareStatement(sql);
             resultSet = statement.executeQuery();
             
             while(resultSet.next()){
                 int categoriaProductoId = resultSet.getInt("categoriaProductoId");
                 String nombreCategoria = resultSet.getString("nombreCategoria");
                 String descripcionCategoria = resultSet.getString("descripcionCategoria");
                 
                 
                 categoriaProducto.add(new CategoriaProducto(categoriaProductoId, nombreCategoria,descripcionCategoria));
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
    
    return FXCollections.observableList(categoriaProducto);
    
    }
    
   public void agregarCategoriaProductos(){
    
       try{
       conexion = Conexion.getInstance().obtenerConexion();
       String sql = "call sp_agregarCategoriaProductos(?,?)";
       statement = conexion.prepareStatement(sql);
       statement.setString(1,tfNombreCategoria.getText());
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
       CategoriaProducto cP =(CategoriaProducto)tblCategoria.getSelectionModel().getSelectedItem();
       if(cP != null){
           tfcategoriaId.setText(Integer.toString(cP.getCategoriaProductoId()));
           tfNombreCategoria.setText(cP.getNombreCategoria());
           taDescripcion.setText(cP.getDescripcionCategoria());
       
       }
   
   
   
   }
   
   public void eliminarCategoriaProducto(int catPId){
    try{
       conexion = Conexion.getInstance().obtenerConexion();
       String sql = "call sp_eliminarCategoriaProductos(?)";
       statement = conexion.prepareStatement(sql);
       statement.setInt(1, catPId);
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
   
   public void vaciarForm(){
        tfcategoriaId.clear();
        tfNombreCategoria.clear();
        taDescripcion.clear();
   }
   
   public CategoriaProducto buscarCategoriaProducto(){
       CategoriaProducto categoriaProducto = null;
        try{
          conexion = Conexion.getInstance().obtenerConexion();
          String sql = "call sp_BuscarCategoriaProductos(?)";
          statement = conexion.prepareStatement(sql);
          statement.setInt(1, Integer.parseInt(tfBuscar.getText()));
          resultSet = statement.executeQuery();
          
          if(resultSet.next()){
              int categoriaProductoId = resultSet.getInt("categoriaProductoId");
              String nombreCategoria = resultSet.getString("nombreCategoria");
              String descripcionCategoria = resultSet.getString("descripcionCategoria");
             
              
              categoriaProducto = new CategoriaProducto(categoriaProductoId, nombreCategoria,descripcionCategoria);
          
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
                }if(resultSet != null){
                    resultSet.close();
                
                }
          }catch(SQLException e){
              System.out.println(e.getMessage());
        
        
        
        
        }
    
    
    }
    return categoriaProducto;
   
   }
   
   public void editarCategoriaProducto(){
    try{
           conexion = Conexion.getInstance().obtenerConexion();
           String sql = "call sp_EditarCategoriaProductos(?,?,?)";
           statement = conexion.prepareStatement(sql);
           statement.setInt(1, Integer.parseInt(tfcategoriaId.getText()));
           statement.setString(2, tfNombreCategoria.getText());
           statement.setString(3, taDescripcion.getText());
           statement.execute();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(statement != null){
                    statement.close();
                }
                if (conexion != null){
                    conexion.close();
                }
            }catch(SQLException e){
            System.out.println(e.getMessage());
            }
        }
   
   
   }
   
   
   
   
    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    
}
