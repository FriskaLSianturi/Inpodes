/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desa;

import static desa.LoginController.role;
import entity.Saran;
import java.io.IOException;
import java.net.URL; 
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.implcrud;

/**
 * FXML Controller class
 *
 * @author frisca
 */
public class PageSaranController implements Initializable {

    @FXML
    private AnchorPane ApSaran;
    @FXML
    private TextField fNamaDesa;
    @FXML
    private TextArea fSaran;
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
    @FXML
    private TableView<Saran> tabelSaran;
    @FXML
    private TableColumn<Saran, String> cNamaDesa;
    @FXML
    private TableColumn<Saran, String> cSaran;
    @FXML
    private TableColumn<Saran, Integer>cId;
    
 
    
    private String key =null;
    model.modelSaran crud = new model.modelSaran();
    private ObservableList<Saran> olistSaran = FXCollections.observableArrayList();
    

    /**
     * Initializes the controller class.
     */
 //   @Override
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
                Saran saran = new Saran();
                saran.setNamaDesa(fNamaDesa.getText());
                saran.setSaran(fSaran.getText());
                crud.add(saran);
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
                Saran saran = new Saran();
                saran.setId(Integer.parseInt(key));
                saran.setNamaDesa(fNamaDesa.getText());
                saran.setSaran(fSaran.getText());
                crud.update(saran);
                clear();
                read();
                
            }
        });
    }

    @FXML
    private void actionDelete(ActionEvent event) {
        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Saran saran = new Saran();                
                saran.setId(Integer.parseInt(key));
                saran.setNamaDesa(fNamaDesa.getText());
                saran.setSaran(fSaran.getText());
                crud.delete(saran);
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
                        ApSaran.getChildren().setAll(pane);
                    }else{
                        pane = FXMLLoader.load(getClass().getResource("/ui/BerandaGuest.fxml"));
                        ApSaran.getChildren().setAll(pane);
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
                    pane = FXMLLoader.load(getClass().getResource("/ui/Beranda.fxml"));
                    ApSaran.getChildren().setAll(pane);                     
                } catch (IOException ex) {
                    Logger.getLogger(PageSaranController.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }
        });
    }       
            
    public void clear(){       
        key=null;
        fNamaDesa.clear();
        fSaran.clear();
    }
    public void read(){
        tabelSaran.getItems().clear();
        List<Saran> lists = crud.read();
        for(Saran s: lists){
            olistSaran.add(s);
            cId.setCellValueFactory(new PropertyValueFactory<>("id"));
            cSaran.setCellValueFactory(new PropertyValueFactory<>("saran"));
            cNamaDesa.setCellValueFactory(new PropertyValueFactory<>("namaDesa"));            
            tabelSaran.setItems(olistSaran);
        }
    }
    public void selectRow(){
        tabelSaran.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {                
                int i = tabelSaran.getSelectionModel().getSelectedIndex();
                key = String.valueOf(cId.getCellData(i));
               
              
                if(role.equals("admin")){ 
                    fSaran.setText( "Nama \t:"+cNamaDesa.getCellData(i) +"\nSaran \t:" + cSaran.getCellData(i) );
                    fSaran.setEditable(false); 
                }else{
                    fNamaDesa.setText(cNamaDesa.getCellData(i));
                    fSaran.setText(cSaran.getCellData(i));
                    
                }
            }
            
        });
    }
    
}
