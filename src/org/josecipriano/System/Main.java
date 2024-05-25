/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josecipriano.System;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.josecipriano.controller.FormClienteController;
import org.josecipriano.controller.MenuCargosController;
import org.josecipriano.controller.MenuCategoriaProductoController;
import org.josecipriano.controller.MenuClienteController;
import org.josecipriano.controller.MenuComprasController;
import org.josecipriano.controller.MenuDetalleComprasController;
import org.josecipriano.controller.MenuDetalleFacturaController;
import org.josecipriano.controller.MenuDistribuidorController;
import org.josecipriano.controller.MenuEmpleadoController;
import org.josecipriano.controller.MenuFacturaController;
import org.josecipriano.controller.MenuPrincipalViewController;
import org.josecipriano.controller.MenuProductosController;
import org.josecipriano.controller.MenuPromocionesController;
import org.josecipriano.controller.MenuTicketSoporteViewController;

/**
 *
 * @author Jose
 */




public class Main extends Application {
    private final String URLVIEW = "/org/josecipriano/view/";
    private Stage stage;
    private Scene scene;
    List<Image> icons = new ArrayList<>();
    
    
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle("Kshop App");
        icons.add(new Image(getClass().getResourceAsStream("/org/josecipriano/image/KshopLog.png")));
        menuPrincipalView();
        stage.getIcons().addAll(icons);
        stage.show();
        
    }
    
    public Initializable switchScene(String fxmlName , int width, int height) throws Exception {
        
        Initializable resultado ;
        FXMLLoader loader = new FXMLLoader();
        
        InputStream file = Main.class.getResourceAsStream(URLVIEW + fxmlName);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(URLVIEW + fxmlName));
        
        scene = new Scene((AnchorPane) loader.load(file), width, height);
        stage.setScene(scene);
        stage.sizeToScene();
        
        resultado = (Initializable) loader.getController();
        return resultado; 
    
    
    
    
    
    
    }
    
    public void menuPrincipalView(){
        try {
            MenuPrincipalViewController menuPrincipalView = (MenuPrincipalViewController)switchScene("MenuPrincipalView.fxml", 950, 675);
            menuPrincipalView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void menuClienteView(){
        try {
            MenuClienteController menuClienteView = (MenuClienteController) switchScene("MenuClienteView.fxml", 950, 675);
            menuClienteView.setStage(this);
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    
    
    }
    
    public void FormClienteView(int op){
        try {
            FormClienteController formClienteView = (FormClienteController) switchScene("FormClienteView.fxml", 500, 750);
            formClienteView.setOp(op);
            formClienteView.setStage(this);
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    
    
    
    
    
    }
    
   public void menuTicketSoporteView(){
        try{
            MenuTicketSoporteViewController menuTicketSoporteView = (MenuTicketSoporteViewController)switchScene("MenuTicketSoporteView.fxml", 950,675);
            menuTicketSoporteView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
   
   public void menuCargosView(){
       try{
           MenuCargosController menuCargosController = (MenuCargosController)switchScene("MenuCargosView.fxml", 950,675);
           menuCargosController.setStage(this);
         }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
   
   public void menuCategoriasProductos(){
       try{
           MenuCategoriaProductoController menuCategoriaProductoController = (MenuCategoriaProductoController)switchScene("MenuCategoriaProductoView.fxml", 950,675);
           menuCategoriaProductoController.setStage(this);
         }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
       
       
   public void menuEmpleado(){
   try{
          MenuEmpleadoController menuEmpleadoController = (MenuEmpleadoController)switchScene("MenuEmpleadoView.fxml",950,675);
          menuEmpleadoController.setStage(this);
         }catch(Exception e){
            System.out.println(e.getMessage());
        }
   
   
   }
   
    
    
    public void menuFacturas(){
     try{
         MenuFacturaController menuFacturaController = (MenuFacturaController)switchScene("MenuFacturaView.fxml",950,675);
         menuFacturaController.setStage(this);
         
         }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void menuDistribuidores(){
    try{
            MenuDistribuidorController menuDistribuidorController = (MenuDistribuidorController)switchScene("MenuDistribuidorView.fxml",950,675);
            menuDistribuidorController.setStage(this);
         
         }catch(Exception e){
            System.out.println(e.getMessage());
        }
    
    
    }
    
    public void menuProductos(){
    try{
            MenuProductosController menuProductosController = (MenuProductosController)switchScene("MenuProductosView.fxml",950,675);
            menuProductosController.setStage(this);
         }catch(Exception e){
            System.out.println(e.getMessage());
        }
    
  }
    
    public void menuPromociones(){
    try{
            MenuPromocionesController menuPromocionesController =(MenuPromocionesController)switchScene("MenuPromocionesView.fxml",950,675);
            menuPromocionesController.setStage(this);
         }catch(Exception e){
            System.out.println(e.getMessage());
        }
    
    }
    
    
    public void menuDetalleFacturas(){
    try{
           MenuDetalleFacturaController menuDetalleFacturaController = (MenuDetalleFacturaController)switchScene("MenuDetalleFacturaView.fxml",950,675);
           menuDetalleFacturaController.setStage(this);
         }catch(Exception e){
            System.out.println(e.getMessage());
        }
     }
    
   public void menuCompras(){
   try{
          MenuComprasController menuComprasController = (MenuComprasController)switchScene("MenuComprasView.fxml",950,675);
          menuComprasController.setStage(this);
         }catch(Exception e){
            System.out.println(e.getMessage());
        }
   }
    
  public void menuDetalleCompras(){
   try{
         MenuDetalleComprasController menuDetalleComprasController = (MenuDetalleComprasController)switchScene("MenuDetalleComprasView.fxml",950,675);
         menuDetalleComprasController.setStage(this);
         }catch(Exception e){
            System.out.println(e.getMessage());
        }
}
    
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
