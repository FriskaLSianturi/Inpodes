/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desa;

import static desa.LoginController.id_user;
import entity.Userreg;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.modelUser;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.NewHibernateUtil;

/**
 * FXML Controller class
 *
 * @author frisca
 */
public class PageProfilController implements Initializable {

    @FXML
    private AnchorPane ApProfil;
    @FXML
    private TextField fNama;
    @FXML
    private TextField fPassword;
    @FXML
    private TextField fUsername;
    @FXML
    private Button btnUpdate;

    public static String QUERY_LOGIN ="from Userreg where id like '"+id_user+"'";
 
    /**
     * Initializes the controller class.
     */
    model.modelUser muser = new modelUser();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        readUser();
    }    
    public void actionUpdate(ActionEvent event){
        btnUpdate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            try{               
                Userreg u = new Userreg();
                u.setId(id_user);
                u.setNama(fNama.getText());
                u.setUsername(fUsername.getText());
                u.setPassword(fPassword.getText());
                muser.update(u);
                clear(); 
                try {
                    back();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
               }catch(Exception e){
                   System.out.println(e.getMessage());
               }
            }
        });
        
    }
    public void readUser(){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        Query q = session.createQuery(QUERY_LOGIN);
        List<Userreg> listUser = q.list();
        for(Object u : listUser){
            Userreg user = (Userreg)u;
            fNama.setText(user.getNama());
            fUsername.setText(user.getUsername());
            fPassword.setText(user.getPassword());
        }
        session.getTransaction().commit();
   }
   public void clear(){
       fNama.clear();
       fPassword.clear();
       fUsername.clear();
   }
   public void back() throws IOException{
       Alert alert = new Alert(Alert.AlertType.NONE,"Berhasil Update Profil", ButtonType.OK);
       alert.setTitle("Berhasil");
       alert.showAndWait();
       
       AnchorPane pane = FXMLLoader.load(getClass().getResource("/ui/BerandaGuest.fxml"));
       ApProfil.getChildren().setAll(pane);
   }

    
}
