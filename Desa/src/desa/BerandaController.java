/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desa;

import static desa.LoginController.role;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author frisca
 */
public class BerandaController implements Initializable {

    @FXML
    private AnchorPane ApBeranda;
    @FXML
    private Button btnBeranda;
    @FXML
    private Button btnSaran;
    @FXML
    private Button btnBerita;
    @FXML
    private Button btnLogout;
    @FXML 
    private Button btnDesa;
     

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void actionBeranda(ActionEvent event) throws IOException {
        btnBeranda.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    AnchorPane pane =FXMLLoader.load(getClass().getResource("/ui/Beranda.fxml"));
                    ApBeranda.getChildren().setAll(pane);
                } catch (IOException ex) {
                    Logger.getLogger(BerandaController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
    }
    
    @FXML
    private void actionDesa(ActionEvent event) throws IOException {
       btnDesa.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    AnchorPane pane =FXMLLoader.load(getClass().getResource("/ui/DesaAdmin.fxml"));
                    ApBeranda.getChildren().setAll(pane);
                } catch (IOException ex) {
                    Logger.getLogger(BerandaController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
    }

    @FXML
    private void actionSaran(ActionEvent event) throws IOException {
        btnSaran.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    AnchorPane pane =FXMLLoader.load(getClass().getResource("/ui/pageSaranAdmin.fxml"));
                    ApBeranda.getChildren().setAll(pane);
                } catch (IOException ex) {
                    Logger.getLogger(BerandaController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
    }

    @FXML
    private void actionBerita(ActionEvent event) throws IOException {
        btnBerita.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    AnchorPane pane =FXMLLoader.load(getClass().getResource("/ui/pageBerita.fxml"));
                    ApBeranda.getChildren().setAll(pane);
                } catch (IOException ex) {
                    Logger.getLogger(BerandaController.class.getName()).log(Level.SEVERE, null, ex);
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
                    ApBeranda.getChildren().setAll(pane);                     
                } catch (IOException ex) {
                    Logger.getLogger(PageSaranController.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }
        });
    }    
       
}
