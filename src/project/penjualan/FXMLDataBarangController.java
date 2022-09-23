/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package project.penjualan;

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
public class FXMLDataBarangController implements Initializable {

    @FXML
    private Button edit;
    @FXML
    private TableView<BrgModel> tbvbrg;
    @FXML
    private Button add;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showdata();
    }    

    @FXML
    private void tambahklik(ActionEvent event) {
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLInputBrg.fxml"));   
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
    private void ubahklik(ActionEvent event) {
        BrgModel s= new BrgModel();
        s=tbvbrg.getSelectionModel().getSelectedItem();
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLInputBrg.fxml"));    
        Parent root = (Parent)loader.load();
        FXMLInputBrgController isidt=(FXMLInputBrgController)loader.getController();
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
    private void hapusklik(ActionEvent event) {
        BrgModel s= new BrgModel();       
        s=tbvbrg.getSelectionModel().getSelectedItem();
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Mau dihapus?",ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        if(a.getResult()==ButtonType.YES){
           if(FXMLDocumentController.dtbrg.delete(s.getKodebrg())){
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

    @FXML
    private void keluarklik(ActionEvent event) {
        exit.getScene().getWindow().hide();
    }

    @FXML
    private void awalklik(ActionEvent event) {
        tbvbrg.getSelectionModel().selectFirst();        
        tbvbrg.requestFocus();
    }

    @FXML
    private void sebelumklik(ActionEvent event) {
        tbvbrg.getSelectionModel().selectAboveCell();       
        tbvbrg.requestFocus(); 
    }

    @FXML
    private void sesudahklik(ActionEvent event) {
        tbvbrg.getSelectionModel().selectBelowCell();        
        tbvbrg.requestFocus();
    }

    @FXML
    private void akhirklik(ActionEvent event) {
        tbvbrg.getSelectionModel().selectLast();        
        tbvbrg.requestFocus();    
    }

    @FXML
    private void findbrg(KeyEvent event) {
        BrgModel s = new BrgModel();
        String key = search.getText();
        if(key!=""){
        ObservableList<BrgModel> data=FXMLDocumentController.dtbrg.CariBrg(key,key);
        if(data!=null){            
            show(data);
        }else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvbrg.getScene().getWindow().hide();;
        }            
            } else{
               showdata();
            }
    }
    
    public void showdata(){
        ObservableList<BrgModel> data=FXMLDocumentController.dtbrg.Load();
        if(data!=null){            
            show(data);
    }else {Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvbrg.getScene().getWindow().hide();
        }                
    }


    
    public void show(ObservableList<BrgModel> data){
            tbvbrg.getColumns().clear();            
            tbvbrg.getItems().clear();
            TableColumn col = new TableColumn("KodeBarang");
            col.setCellValueFactory(new PropertyValueFactory<CustModel, String>("kodebrg"));
            tbvbrg.getColumns().addAll(col);
            col=new TableColumn("NamaBarang");
            col.setCellValueFactory(new PropertyValueFactory<CustModel, String>("namabrg"));
            tbvbrg.getColumns().addAll(col);
            col=new TableColumn("Tarif");
            col.setCellValueFactory(new PropertyValueFactory<CustModel, String>("tarif"));
            tbvbrg.getColumns().addAll(col);
            col=new TableColumn("Gambar");
            col.setCellValueFactory(new PropertyValueFactory<CustModel, String>("gambar"));
            tbvbrg.getColumns().addAll(col);
            tbvbrg.setItems(data);
    }
    
}
