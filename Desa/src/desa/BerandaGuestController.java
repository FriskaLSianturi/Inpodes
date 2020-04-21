/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desa;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
public class BerandaGuestController implements Initializable {

    @FXML
    private AnchorPane ApBeranda;
    @FXML
    private Button btnBeranda;
    @FXML
    private Button btnSaran;
    @FXML
    private Button btnBerita;
    @FXML
    private Button btnDesa;
    @FXML
    private Button btnLogout;
    @FXML
    private Button btnProfil;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void actionDesa(ActionEvent event){
        btnDesa.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AnchorPane pane;
                try {
                    pane = FXMLLoader.load(getClass().getResource("/ui/DesaGuest.fxml"));
                    ApBeranda.getChildren().setAll(pane);
                } catch (IOException ex) {
                    Logger.getLogger(BerandaGuestController.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }
        });
    }
    @FXML
    private void actionBeranda(ActionEvent event) {
           btnBeranda.setOnAction(new EventHandler<ActionEvent>() {
               @Override
               public void handle(ActionEvent event) {
                   AnchorPane pane;
                   try {
                       pane = FXMLLoader.load(getClass().getResource("/ui/BerandaGuest.fxml"));
                       ApBeranda.getChildren().setAll(pane);
                   } catch (IOException ex) {
                       Logger.getLogger(BerandaGuestController.class.getName()).log(Level.SEVERE, null, ex);
                   }                   
               }
           });
           
    }

    @FXML
    private void actionSaran(ActionEvent event) {
        btnSaran.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AnchorPane pane;
                try {
                    pane = FXMLLoader.load(getClass().getResource("/ui/pageSaran.fxml"));                    
                    ApBeranda.getChildren().setAll(pane);
                } catch (IOException ex) {
                    Logger.getLogger(BerandaGuestController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @FXML
    private void actionBerita(ActionEvent event) {
        btnBerita.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AnchorPane pane;
                try {
                    pane = FXMLLoader.load(getClass().getResource("/ui/pageBeritaGuest.fxml"));
                    ApBeranda.getChildren().setAll(pane);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }               
            }
        });
    }   
    @FXML
    private void actionLogout(ActionEvent event){
        btnLogout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AnchorPane pane;
                try {
                    pane = FXMLLoader.load(getClass().getResource("/ui/Login.fxml"));
                    ApBeranda.getChildren().setAll(pane);
                } catch (IOException ex) {
                    Logger.getLogger(BerandaGuestController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
    }
    @FXML
    private void actionProfil(ActionEvent event){
        btnProfil.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                   AnchorPane ap;
                try {
                    ap = FXMLLoader.load(getClass().getResource("/ui/pageProfil.fxml"));
                     ApBeranda.getChildren().setAll(ap);
                } catch (IOException ex) {
                    Logger.getLogger(BerandaGuestController.class.getName()).log(Level.SEVERE, null, ex);
                }                  
            }
        }) ;
    }
}
