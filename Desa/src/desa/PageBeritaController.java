/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desa;

import static desa.LoginController.role;
import entity.Berita;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent; 
import model.implcrud;

/**
 * FXML Controller class
 *
 * @author frisca
 */
public class PageBeritaController implements Initializable {

    @FXML
    private AnchorPane apBerita;
    @FXML
    private TextArea fBerita;
    @FXML
    private TextField fJudul;
    @FXML
    private DatePicker fTanggal;
    @FXML
    private TableView<Berita> tableBerita;
    @FXML
    private TableColumn<Berita, Integer> cId;
    @FXML
    private TableColumn<Berita, String> cJudul;
    @FXML 
    private TableColumn<Berita, String> cTanggal;
    @FXML
    private TableColumn<Berita, String> cIsiBerita;

    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnLogout;
    
    model.modelBerita berita = new model.modelBerita();
    private ObservableList<Berita> listBerita = FXCollections.observableArrayList();
    private String key = null;
    @FXML
    private Label lJudul;
    @FXML
    private Label lTanggal;
    @FXML
    private Label lBerita;
    @FXML
    private TextArea fBeritafJudulfTanggal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        read();
        selectRow();
    }    
    @FXML
    private void actionAdd(ActionEvent event) {
        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Berita b = new Berita();
                b.setJudul(fJudul.getText());
                b.setTanggal(fTanggal.getValue().toString());
                b.setIsi(fBerita.getText());
                berita.add(b);
                clear();
                read();
            }
        });
    }
    @FXML
    private void actionUpdate(ActionEvent event) {
        btnUpdate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               try{
                    Berita b = new Berita();
                    b.setId(Integer.parseInt(key));
                    b.setJudul(fJudul.getText());
                    b.setTanggal(fTanggal.getValue().toString());
                    b.setIsi(fBerita.getText());
                    berita.update(b);
                    clear();
                    read();
               }catch(Exception e){
                   System.out.println(e.getMessage());
               }
            }
        });
    }
    
    @FXML
    private void actionDelete(ActionEvent event) {
        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Berita b = new Berita();
                b.setId(Integer.parseInt(key));
                b.setJudul(fJudul.getText());
                b.setTanggal(fTanggal.getValue().toString());
                b.setIsi(fBerita.getText());
                berita.delete(b);
                clear();
                read();
            }
        });
    }

    @FXML
    private void actionBack(ActionEvent event) throws IOException {
        btnBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AnchorPane pane;
                try {
                    if(role.equals("admin")){
                        pane = FXMLLoader.load(getClass().getResource("/ui/BerandaAdmin.fxml"));
                        apBerita.getChildren().setAll(pane);
                    }else{
                        pane = FXMLLoader.load(getClass().getResource("/ui/BerandaGuest.fxml"));
                        apBerita.getChildren().setAll(pane);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(PageSaranController.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }
        });
    }
    
    @FXML
    private void actionLogout(ActionEvent event) throws IOException {        
        btnLogout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AnchorPane pane;
                try {                     
                    pane = FXMLLoader.load(getClass().getResource("/ui/Login.fxml"));
                    apBerita.getChildren().setAll(pane);                     
                } catch (IOException ex) {
                    Logger.getLogger(PageSaranController.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }
        });
    }       
    public void clear(){
        fJudul.clear();
        fBerita.clear();
        fTanggal.setValue(null);
    }
    public void read(){
        tableBerita.getItems().clear();
        List<Berita> list = berita.read();
        for(Berita b : list){
            listBerita.add(b); 
           cId.setCellValueFactory(new PropertyValueFactory<>("id"));
           cJudul.setCellValueFactory(new PropertyValueFactory<>("judul"));
           cTanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
           cIsiBerita.setCellValueFactory(new PropertyValueFactory<>("isi"));
           tableBerita.setItems(listBerita);
        }        
    }
    
    public void selectRow(){
        tableBerita.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {                
                int i = tableBerita.getSelectionModel().getSelectedIndex();
                key = String.valueOf(cId.getCellData(i));
                LocalDate localDate = LocalDate.parse(cTanggal.getCellData(i));
                
                if(role.equals("admin")){
                    fJudul.setText(cJudul.getCellData(i));
                    fBerita.setText(cIsiBerita.getCellData(i));
                    fTanggal.setValue(localDate);                
                }else{
                    fBerita.setText(
                  "Judul Berita\t: "+cJudul.getCellData(i) + "\n" + "Tanggal\t\t:"+ cTanggal.getCellData(i) +"\n" +
                  "Isi\t\t\t:"+ cIsiBerita.getCellData(i));  
              
                    fTanggal.setValue(localDate);    
                  
                    fBerita.setEditable(false);
                     
              } 
            }
            
        });
    }

}
