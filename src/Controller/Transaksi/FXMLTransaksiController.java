/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Transaksi;


import Controller.MainMenu.FXML_MainMenuController;
import Models.Costumers.Custumers_Models;
import Models.DetailJual.Detil_Jual_Models;
import
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;



/**
 * FXML Controller class
 *
 * @author mariq
 */
public class FXMLTransaksiController implements Initializable {

    @FXML
    private TableView<Custumers_Models> TableViewCustumers;
    @FXML
    private TableView<BrgModel> TabelViewsBarang;
    @FXML
    private TableView<?> TabelViewsDetailPembelian;
    @FXML
    private DatePicker datepickerTanggal;
    @FXML
    private TextField txtNoJual;
    @FXML
    private TextField JumlahTxt;
    @FXML
    private TextField IdMemberText;
    @FXML
    private TextField KodeBarangText;
    @FXML
    private Button ButtonTambahBarang;
    @FXML
    private Button ButtonHapusBarang;
    @FXML
    private Button ButtonBatal;
    @FXML
    private Button ButtonSave;

   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showdataCustomers();
        showdataBarang();
    }    

    @FXML
    private void ButtonTambahBarangClick(ActionEvent event) {
    }

    @FXML
    private void ButtonHapusBarangClick(ActionEvent event) {
    }

    @FXML
    private void ButtonBatalClick(ActionEvent event) {
    }

    @FXML
    private void ButtonSaveClick(ActionEvent event) {
    }
    
 public void showdataCustomers(){
        ObservableList<Custumers_Models> data=FXML_MainMenuController.dtcust.Load();
        if(data!=null){            
            showCustomer(data);
    }else {Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            TableViewCustumers.getScene().getWindow().hide();
        }                
    }

 public void showCustomer(ObservableList<Custumers_Models> data){
            TableViewCustumers.getColumns().clear();            
            TableViewCustumers.getItems().clear();
            TableColumn col = new TableColumn("idmember");
            col.setCellValueFactory(new PropertyValueFactory<Custumers_Models, String>("idmember"));
            TableViewCustumers.getColumns().addAll(col);
            col=new TableColumn("nama");
            col.setCellValueFactory(new PropertyValueFactory<Custumers_Models, String>("nama"));
            TableViewCustumers.setItems(data);
    }
 
 public void showdataBarang(){
        ObservableList<BrgModel> data=FXML_MainMenuController.dtbrg.Load();
        if(data!=null){            
            showBarang(data);
    }else {Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            TabelViewsBarang.getScene().getWindow().hide();
        }                
    }

 public void showBarang(ObservableList<BrgModel> data){
            TabelViewsBarang.getColumns().clear();            
            TabelViewsBarang.getItems().clear();
            TableColumn col = new TableColumn("KodeBarang");
            col.setCellValueFactory(new PropertyValueFactory<Custumers_Models, String>("kodebrg"));
            TabelViewsBarang.getColumns().addAll(col);
            col=new TableColumn("NamaBarang");
            col.setCellValueFactory(new PropertyValueFactory<Custumers_Models, String>("namabrg"));
            TabelViewsBarang.getColumns().addAll(col);
            col=new TableColumn("Tarif");
            col.setCellValueFactory(new PropertyValueFactory<Custumers_Models, String>("tarif"));
            TabelViewsBarang.getColumns().addAll(col);
            col=new TableColumn("Gambar");
            col.setCellValueFactory(new PropertyValueFactory<Custumers_Models, String>("gambar"));
            TabelViewsBarang.getColumns().addAll(col);
            TabelViewsBarang.setItems(data);
    }

    @FXML
    private void SetIDmember(MouseEvent event) {
        IdMemberText.setText(TableViewCustumers.getSelectionModel().getSelectedItem().getIdmember());
    }
    
    private void SetKodeBarang(MouseEvent event){
        KodeBarangText.setText(TabelViewsBarang.getSelectionModel().getSelectedItem().getKodebrg());
    }

    @FXML
    private void SetIDataBarang(MouseEvent event) {
    }

    
}
