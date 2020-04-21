/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desa;

import entity.Userreg;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.implcrud; 

/**
 * FXML Controller class
 *
 * @author frisca
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField fName;
    @FXML
    private TextField fUsername;
    @FXML
    private Button buttonnRegister;
    @FXML
    private PasswordField fPassword;
    @FXML 
    private AnchorPane pageReg;
    model.modelUser u= new model.modelUser();
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void actionRegister(ActionEvent event) {
        System.out.println("Hello");
        buttonnRegister.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Userreg user = new Userreg();
                user.setNama(fName.getText());
                user.setUsername(fUsername.getText());
                user.setPassword(fPassword.getText());
                u.add(user);     
                clear();
                try {
                    backtoLogin();
                } catch (IOException ex) {
                     System.out.println(ex.getMessage());
                }
            }
        });
    }
    public void clear(){
        fName.clear();  
        fPassword.clear();
    }
    public void backtoLogin() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/ui/Login.fxml"));
        pageReg.getChildren().setAll(pane);
        
    }
    
}
