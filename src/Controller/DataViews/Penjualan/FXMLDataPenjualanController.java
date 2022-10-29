/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.DataViews.Penjualan;

import project.penjualan.Controller.MainMenu.FXMLDocumentController;
import project.penjualan.Models.Jual.Jual_Models;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LIKMI
 */
public class FXMLDataPenjualanController implements Initializable {
    
    public static DBJual dtjual = new DBJual();
    

    @FXML
    private TableView<Jual_Models> tbvjual;
    @FXML
    private Button add;
    @FXML
    private Button edit;
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
    private TextField txtid;
    @FXML
    private TextField txtnojual;
    @FXML
    private TableView<DetilJualModel> tbvdjual;
    @FXML
    private DatePicker tanggal;
    @FXML
    private TextField Total;

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
        Jual_Models s = new Jual_Models();
        s=tbvjual.getSelectionModel().getSelectedItem();
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLInputBrg.fxml"));    
        Parent root = (Parent)loader.load();
        //FXMLInputBrgController isidt=(FXMLInputBrgController)loader.getController();
        //isidt.execute(s);                
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
        Jual_Models s= new Jual_Models();       
        s=tbvjual.getSelectionModel().getSelectedItem();
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Mau dihapus?",ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        if(a.getResult()==ButtonType.YES){
           if(FXMLDocumentController.dtjual.delete(s.getNojual())){
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
        tbvjual.getSelectionModel().selectFirst();        
        setdata();
        tbvjual.requestFocus();
    }

    @FXML
    private void sebelumklik(ActionEvent event) {
        tbvjual.getSelectionModel().selectAboveCell();      
        setdata();
        tbvjual.requestFocus(); 
    }

    @FXML
    private void sesudahklik(ActionEvent event) {
        tbvjual.getSelectionModel().selectBelowCell();        
        setdata();
        tbvjual.requestFocus();
    }

    @FXML
    private void akhirklik(ActionEvent event) {
        tbvjual.getSelectionModel().selectLast();
        setdata();
        tbvjual.requestFocus();    
    }

    @FXML
    private void findbrg(KeyEvent event) {
        Jual_Models s = new Jual_Models();
        String key = search.getText();
        if(key!=""){
        ObservableList<Jual_Models> data=FXMLDocumentController.dtjual.CariJual(key);
        
        if(data!=null){        
            show(data);
            showdatadetil(key);
        }else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvjual.getScene().getWindow().hide();;
        }            
            } else{
               showdata();
            }
        
    }
    
    @FXML
    public void setdata(){
        txtnojual.setText(tbvjual.getSelectionModel().getSelectedItem().getNojual());
        tanggal.setValue(tbvjual.getSelectionModel().getSelectedItem().getTanggal().toLocalDate());
        txtid.setText(tbvjual.getSelectionModel().getSelectedItem().getIdmember());
        showdatadetil(txtnojual.getText());
    }
    
    public void showdata(){
        ObservableList<Jual_Models> data=FXMLDocumentController.dtjual.Load();
        if(data!=null){            
            show(data);
    }else {Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvjual.getScene().getWindow().hide();
        }             
    }
    
    public void show(ObservableList<Jual_Models> data){
            tbvjual.getColumns().clear();            
            tbvjual.getItems().clear();
            TableColumn col = new TableColumn("No Jual");
            col.setCellValueFactory(new PropertyValueFactory<Jual_Models, String>("nojual"));
            tbvjual.getColumns().addAll(col);
            col=new TableColumn("Tanggal");
            col.setCellValueFactory(new PropertyValueFactory<Jual_Models, Date>("tanggal"));
            tbvjual.getColumns().addAll(col);
            col=new TableColumn("Id Member");
            col.setCellValueFactory(new PropertyValueFactory<Jual_Models, String>("idmember"));
            tbvjual.getColumns().addAll(col);
            col=new TableColumn("Nama");
            col.setCellValueFactory(new PropertyValueFactory<Jual_Models, String>("nama"));
            tbvjual.getColumns().addAll(col);
            tbvjual.setItems(data);
            
    }
    
    public void showdatadetil(String text){
        ObservableList<DetilJualModel> data=FXMLDocumentController.dtjualdetil.Load(text);
        if(data!=null){            
            showdetil(data);
    }else {Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvdjual.getScene().getWindow().hide();
        }             
    }
    
    public void showdetil(ObservableList<DetilJualModel> data){
            tbvdjual.getColumns().clear();            
            tbvdjual.getItems().clear();
            TableColumn col = new TableColumn("No Jual");
            col.setCellValueFactory(new PropertyValueFactory<DetilJualModel, String>("nojual"));
            tbvdjual.getColumns().addAll(col);
            col=new TableColumn("kode barang");
            col.setCellValueFactory(new PropertyValueFactory<DetilJualModel, String>("kodebrg"));
            tbvdjual.getColumns().addAll(col);
            col=new TableColumn("nama barang");
            col.setCellValueFactory(new PropertyValueFactory<DetilJualModel, String>("namabrg"));
            tbvdjual.getColumns().addAll(col);
            col=new TableColumn("jumlah");
            col.setCellValueFactory(new PropertyValueFactory<DetilJualModel, Integer>("jumlah"));
            tbvdjual.getColumns().addAll(col);
            col=new TableColumn("tarif");
            col.setCellValueFactory(new PropertyValueFactory<DetilJualModel, Double>("tarif"));
            tbvdjual.getColumns().addAll(col);
            col=new TableColumn("total");
            col.setCellValueFactory(new PropertyValueFactory<DetilJualModel, Float>("total"));
            tbvdjual.getColumns().addAll(col);
            tbvdjual.setItems(data);
            double totalall=0;
            for(int i=0; i<tbvdjual.getItems().size();i++){
               DetilJualModel n=tbvdjual.getItems().get(i);
               totalall+=n.getTotal();
            }
            Total.setText("Rp" + String.valueOf(totalall));
            //Total.setText(String.valueOf(FXMLDocumentController.dtjualdetil.Total));
    }
    
}
