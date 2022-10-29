/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.DataViews.Customers;

import Controller.Input_Update.Customers.FXMLInputCustomersController;
import project.penjualan.Controller.MainMenu.FXMLDocumentController;
import project.penjualan.Models.Costumers.Custumers_Models;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LIKMI
 */
public class FXMLDataCustomerController implements Initializable {

    @FXML
    private TableView<Custumers_Models> tbvcust;
    @FXML
    private Button add;
    @FXML
    private AnchorPane edit;
    @FXML
    private Button delete;
    @FXML
    private Button exit;
    @FXML
    private Button first;
    @FXML
    private Button previous;
    @FXML
    private Button next;
    @FXML
    private Button last;
    @FXML
    private TextField search;
    @FXML
    private void awalklik(ActionEvent event) {
        tbvcust.getSelectionModel().selectFirst();        
        tbvcust.requestFocus();    
    }

    @FXML
    private void sebelumklik(ActionEvent event) {
        tbvcust.getSelectionModel().selectAboveCell();       
        tbvcust.requestFocus();    
    }
    

    @FXML
    private void sesudahklik(ActionEvent event) {
        tbvcust.getSelectionModel().selectBelowCell();        
        tbvcust.requestFocus();
    }

        @FXML
    private void akhirklik(ActionEvent event) {
        tbvcust.getSelectionModel().selectLast();        
        tbvcust.requestFocus();    
    }
    
     @FXML
    private void keluarklik(ActionEvent event) {
        exit.getScene().getWindow().hide();
    }

    
    @FXML
    private void ubahklik(ActionEvent event) {
        Custumers_Models s= new Custumers_Models();
        s=tbvcust.getSelectionModel().getSelectedItem();
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLInputNilai.fxml"));    
        Parent root = (Parent)loader.load();
        FXMLInputCustomersController isidt=(FXMLInputCustomersController)loader.getController();
        isidt.execute(s);                
        Scene scene = new Scene(root);
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);
        stg.setIconified(false);
        stg.setScene(scene);
        stg.showAndWait();
        } catch (IOException e){   e.printStackTrace();   }
        showdata();  
        awalklik(event);
    }
    
        @FXML
        private void tambahklik(ActionEvent event) {
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLInputNilai.fxml"));    
        Parent root = (Parent)loader.load();        
        Scene scene = new Scene(root);        
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);        
        stg.setIconified(false);        
        stg.setScene(scene);
        stg.showAndWait();
        } catch (IOException e){   
            e.printStackTrace();   }
        showdata();        
        awalklik(event);
}
        @FXML
        private void hapusklik(ActionEvent event) {
        Custumers_Models s= new Custumers_Models();       
        s=tbvcust.getSelectionModel().getSelectedItem();
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Mau dihapus?",ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        if(a.getResult()==ButtonType.YES){
           if(FXMLDocumentController.dtcust.delete(s.getIdmember())){
               Alert b=new Alert(Alert.AlertType.INFORMATION,"Data berhasil dihapus", ButtonType.OK);
               b.showAndWait();
           } else {
               Alert b=new Alert(Alert.AlertType.ERROR,"Data gagal dihapus", ButtonType.OK);
               b.showAndWait();               
           }    
           showdata();           
           awalklik(event);       
        }    
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showdata();
    }    
    
    public void showdata(){
        ObservableList<Custumers_Models> data=FXMLDocumentController.dtcust.Load();
        if(data!=null){            
            show(data);
    }else {Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvcust.getScene().getWindow().hide();
        }                
    }


    
    public void show(ObservableList<Custumers_Models> data){
            tbvcust.getColumns().clear();            
            tbvcust.getItems().clear();
            TableColumn col = new TableColumn("idmember");
            col.setCellValueFactory(new PropertyValueFactory<Custumers_Models, String>("idmember"));
            tbvcust.getColumns().addAll(col);
            col=new TableColumn("nama");
            col.setCellValueFactory(new PropertyValueFactory<Custumers_Models, String>("nama"));
            tbvcust.getColumns().addAll(col);
            col=new TableColumn("alamat");
            col.setCellValueFactory(new PropertyValueFactory<Custumers_Models, String>("alamat"));
            tbvcust.getColumns().addAll(col);
            col=new TableColumn("total");
            col.setCellValueFactory(new PropertyValueFactory<Custumers_Models, String>("total"));
            tbvcust.getColumns().addAll(col);
            col =new TableColumn("status");
            col.setCellValueFactory(new PropertyValueFactory<Custumers_Models, String>("status"));
            tbvcust.getColumns().addAll(col);
            tbvcust.setItems(data);
    }

    @FXML
    private void findcust(KeyEvent event) {
        Custumers_Models s = new Custumers_Models();
        String key = search.getText();
        if(key!=""){
        ObservableList<Custumers_Models> data=FXMLDocumentController.dtcust.CariCust(key,key);
        if(data!=null){            
            show(data);
        }else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvcust.getScene().getWindow().hide();;
        }            
            } else{
               showdata();
            } 
    }



    
}
