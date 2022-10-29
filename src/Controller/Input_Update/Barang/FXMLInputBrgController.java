/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Input_Update.Barang;

import project.penjualan.Models.Barang.Barang_Models;
import project.penjualan.Controller.MainMenu.FXMLDocumentController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author LIKMI
 */
public class FXMLInputBrgController implements Initializable {

    public boolean editdata;
    @FXML
    private TextField txtkode;
    @FXML
    private TextField txtnama;
    @FXML
    private TextField txttarif;
    @FXML
    private TextField txtgbr;
    @FXML
    private Button Exit;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void execute(Barang_Models d){
        if(!d.getKodebrg().isEmpty()){
          editdata=true;
          txtkode.setText(d.getKodebrg());
          txtnama.setText(d.getNamabrg());          
          txttarif.setText(String.valueOf(d.getTarif()));
          txtgbr.setText(d.getGambar());          
          txtkode.setEditable(false);         
          txtnama.requestFocus(); 
        }
    }
    
    @FXML
    private void keluarklik(ActionEvent event) {
        Exit.getScene().getWindow().hide();
    }
    
    @FXML
    private void cancelklik(ActionEvent event){
        txtkode.setText("");
        txtnama.setText("");
        txttarif.setText("");
        txtgbr.setText("");
        txtkode.requestFocus();
    }
    
    @FXML
    private void simpanklik(ActionEvent event) {
        Barang_Models n=new Barang_Models();
        if(txtkode.getText()!="" && txtnama.getText()!="" && txttarif.getText()!=""){
        n.setKodebrg(txtkode.getText());
        n.setNamabrg(txtnama.getText());     
        n.setTarif(Double.parseDouble(txttarif.getText()));  
        if(txtgbr.getText()!=""){n.setGambar(txtgbr.getText());}
        else {n.setGambar("-");}
        }
        
        FXMLDocumentController.dtbrg.setBrgModel(n);
        if(editdata){
            if(FXMLDocumentController.dtbrg.update()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil diubah",ButtonType.OK);
               a.showAndWait();   
               txtkode.setEditable(true);        
               cancelklik(event);                
            } else {               
                Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal diubah",ButtonType.OK);
                a.showAndWait(); 
            }
            
            }else if(FXMLDocumentController.dtbrg.validasi(n.getKodebrg())<=0){
            if(FXMLDocumentController.dtbrg.insert()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil disimpan",ButtonType.OK);
               a.showAndWait();            
               cancelklik(event);
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal disimpan",ButtonType.OK);
               a.showAndWait();            
            }
        }else{Alert a=new Alert(Alert.AlertType.ERROR,"Data sudah ada",ButtonType.OK);
            a.showAndWait();
            txtkode.requestFocus();
        }
        
    }
}
