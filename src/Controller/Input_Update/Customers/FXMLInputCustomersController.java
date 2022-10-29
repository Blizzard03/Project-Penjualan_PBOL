/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Input_Update.Customers;

import project.penjualan.Controller.MainMenu.FXMLDocumentController;
import project.penjualan.Models.Costumers.Custumers_Models;
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
public class FXMLInputCustomersController implements Initializable {
public boolean editdata;
    @FXML
    private TextField TextId;
    @FXML
    private TextField TextNama;
    @FXML
    private TextField TextAlamat;
    @FXML
    private TextField TextTotal;
    @FXML
    private Button Save;
    @FXML
    private Button Cancel;
    @FXML
    private Button Exit;

    
    public void execute(Custumers_Models d){
        if(!d.getIdmember().isEmpty()){
          editdata=true;
          TextId.setText(d.getIdmember());
          TextNama.setText(d.getNama());          
          TextAlamat.setText(d.getAlamat());
          TextTotal.setText(String.valueOf(d.getTotal()));          
          TextId.setEditable(false);         
          TextNama.requestFocus();         
        }
    }

    @FXML
    private void keluarklik(ActionEvent event) {
        Exit.getScene().getWindow().hide();
    }
    
    @FXML
    private void cancelklik(ActionEvent event){
        TextId.setText("");
        TextNama.setText("");
        TextAlamat.setText("");
        TextTotal.setText("");
        TextId.requestFocus();
    }
    
    @FXML
    private void simpanklik(ActionEvent event) {
        Custumers_Models n=new Custumers_Models();        
        n.setIdmember(TextId.getText());
        n.setNama(TextNama.getText());     
        n.setAlamat(TextAlamat.getText());  
        n.setTotal(Double.parseDouble(TextTotal.getText()));
        FXMLDocumentController.dtcust.setCustModel(n);
        if(editdata){
            if(FXMLDocumentController.dtcust.update()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil diubah",ButtonType.OK);
               a.showAndWait();   
               TextId.setEditable(true);        
               cancelklik(event);                
            } else {               
                Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal diubah",ButtonType.OK);
                a.showAndWait(); 
            }
            
            }else if(FXMLDocumentController.dtcust.validasi(n.getIdmember())<=0){
            if(FXMLDocumentController.dtcust.insert()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil disimpan",ButtonType.OK);
               a.showAndWait();            
               cancelklik(event);
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal disimpan",ButtonType.OK);
               a.showAndWait();            
            }
        }else{Alert a=new Alert(Alert.AlertType.ERROR,"Data sudah ada",ButtonType.OK);
            a.showAndWait();
            TextId.requestFocus();
        }
    }

     

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
