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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.josecipriano.System.Main;
import org.josecipriano.dao.Conexion;
import org.josecipriano.model.Cliente;
import org.josecipriano.model.TicketS;


/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class MenuTicketSoporteViewController implements Initializable {
    Main stage;
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null;
    
    @FXML
    TextField tfTicketId;
    @FXML
    TextArea taDescripcion;
    @FXML
    ComboBox cmbEstatus, cmbCliente, cmbFactura;
    @FXML
    TableView tblTickets;
    @FXML
    TableColumn colTicketId, colDescripcion, colEstatus, colCliente, colFacturaID;
    @FXML
    Button btnGuardar, btnVaciar, btnRegresar;
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnRegresar){
            stage.menuPrincipalView();
        }else if(event.getSource() == btnGuardar){
           if (tfTicketId.getText().equals("")){
            agregarTicket();
           cargarDatos();
           
           }else{
            editarTicket();
             cargarDatos();
           
           }
           
        }else if(event.getSource() == btnVaciar){
        vaciarForm();
        }
            
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
        cargarcmbEstatus();
        cargarCmbFactura();
       
        cmbCliente.setItems(listarClientes());
        
                
    }
    
    public void cargarDatos(){
        tblTickets.setItems(listarTickets());
        colTicketId.setCellValueFactory(new PropertyValueFactory<TicketS , Integer>("TicketSId"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<TicketS , String>("descripcionTicket"));
        colEstatus.setCellValueFactory(new PropertyValueFactory<TicketS , String>("estatus"));
        colCliente.setCellValueFactory(new PropertyValueFactory<TicketS , String>("cliente"));
        colFacturaID.setCellValueFactory(new PropertyValueFactory<TicketS , Integer>("facturaId"));
    
    }
    
  
    
    public void cargarcmbEstatus(){
        cmbEstatus.getItems().add("En proceso");
        cmbEstatus.getItems().add("Finalizado");
    
    }
    
    public void cargarCmbFactura(){
        cmbFactura.getItems().add("1");
    
    }
    
    @FXML
    public void cargarForm(){
        TicketS ts = (TicketS)tblTickets.getSelectionModel().getSelectedItem();
    if(ts != null){
       tfTicketId.setText(Integer.toString(ts.getTicketSId()));
       taDescripcion.setText(ts.getDescripcionTicket());
       cmbEstatus.getSelectionModel().select(0);
       cmbCliente.getSelectionModel().select(ObtenerIndexCliente());
       cmbFactura.getSelectionModel().select(0);
    }
    
    }
    
    public void vaciarForm(){
        tfTicketId.clear();
        taDescripcion.clear();
        cmbEstatus.getSelectionModel().clearSelection();
        cmbCliente.getSelectionModel().clearSelection();
        cmbFactura.getSelectionModel().clearSelection();
        
        
    
    
    
    
    }
    
    public int ObtenerIndexCliente(){
    int index = 0;
    String clienteTbl = ((TicketS)tblTickets.getSelectionModel().getSelectedItem()).getCliente();
    for(int i = 0; i <= cmbCliente.getItems().size(); i++){
            String clienteCmb = cmbCliente.getItems().get(i).toString();
            
         if(clienteTbl.equals(clienteCmb)){
             index = i ;
             break;
         
         }
    
    
    }
    
    
    return index;
        
    }
    
    public  ObservableList<TicketS>listarTickets(){
     ArrayList<TicketS> tickets = new ArrayList<>();
     try{
         conexion = Conexion.getInstance().obtenerConexion();
         String sql = "call sp_ListarTicketS2()";
         statement = conexion.prepareStatement(sql);
         resultSet = statement.executeQuery();
         
         while(resultSet.next()){
             int ticketSId = resultSet.getInt("ticketSId");
             String descripcion = resultSet.getString("descripcionTicket");
             String estatus = resultSet.getString("estatus");
             String cliente = resultSet.getString("cliente");
             int facturaId = resultSet.getInt("facturaId");
             
             tickets.add(new TicketS(ticketSId, descripcion, estatus, cliente, facturaId));
         }
     
     } catch(SQLException e){
         System.out.println(e.getMessage());
     }finally{
         
         try{
         if(resultSet != null){
             resultSet.close();
         }
         if (statement != null){
             statement.close();
         }
         if(conexion != null){
             conexion.close();
         }
         }catch(SQLException e){
            System.out.println(e.getMessage());

         }
                
            
     }
     return FXCollections.observableList(tickets);
    }
    
    public ObservableList<Cliente> listarClientes(){
        ArrayList<Cliente> clientes = new ArrayList<>();
        
        try {
             conexion = Conexion.getInstance().obtenerConexion();
             String sql = "call sp_ListarClientes()";
             statement = conexion.prepareStatement(sql);
             resultSet = statement.executeQuery();
             
             while(resultSet.next()){
                 int clienteId = resultSet.getInt("clienteId");
                 String nombre = resultSet.getString("nombre");
                 String apellido = resultSet.getString("apellido");
                 String telefono = resultSet.getString("telefono");
                 String direccion = resultSet.getString("direccion");
                 String NIT = resultSet.getString("NIT");
                 
                 clientes.add(new Cliente(clienteId, nombre, apellido,telefono,direccion,NIT));
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
        
        
        return FXCollections.observableList(clientes);
    }
    
    public void agregarTicket(){
        try{
        conexion = Conexion.getInstance().obtenerConexion();
        String sql = "call sp_agregarTicketS(?,?,?)";
        statement = conexion.prepareStatement(sql);
        statement.setString(1, taDescripcion.getText());
        statement.setInt(2,((Cliente)cmbCliente.getSelectionModel().getSelectedItem()).getClienteId());
        statement.setInt(3,Integer.parseInt(cmbFactura.getSelectionModel().getSelectedItem().toString()));
        statement.execute();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        } finally{
        try{
            if(statement != null){
                statement.close();
            }if(conexion != null){
                conexion.close();
            }
        
        
        }catch(SQLException e){
         System.out.println(e.getMessage());
        }
        }
    
    }
    
    
    public void editarTicket(){
     try{
         conexion = Conexion.getInstance().obtenerConexion();
         String sql = "call sp_EditarTicketS(?,?,?,?,?)";
         statement = conexion.prepareStatement(sql);
         statement.setInt(1, Integer.parseInt(tfTicketId.getText()));
         statement.setString(2, taDescripcion.getText());
         statement.setString(3,(cmbEstatus.getSelectionModel().getSelectedItem().toString()));
         statement.setInt(4, ((Cliente)cmbCliente.getSelectionModel().getSelectedItem()).getClienteId());
         statement.setInt(5, Integer.parseInt(cmbFactura.getSelectionModel().getSelectedItem().toString()));
         statement.execute();
     }catch(SQLException e){
        System.out.println(e.getMessage());
     }finally{
         try{
            if(statement != null){
                statement.close();
            }if(conexion != null){
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