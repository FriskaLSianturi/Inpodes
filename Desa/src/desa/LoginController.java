/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desa;

import entity.Userreg;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.hibernate.Query;
import org.hibernate.Session;
import util.NewHibernateUtil;

/**
 * FXML Controller class
 *
 * @author frisca
 */
public class LoginController implements Initializable {

    @FXML
    private AnchorPane login;
    @FXML
    private PasswordField password;
    @FXML
    private Button buttonRegister;
    @FXML
    private Button buttonlogin;
    @FXML
    private TextField username;

    /**
     * Initializes the controller class.
     */
     

    /**
     * Initializes the controller class.
     */
    public static String savedUsername;
    public static Integer id_user; 
    public String savedPassword;
    public  static String role = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }
     private static String QUERY_LOGIN ="from Userreg a where a.username like '";
     private void runLogin() {
        executeHQLQuery(QUERY_LOGIN + username.getText() + "'");
    }
 
    private void executeHQLQuery(String sql) {
        try {
            Session session = NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.createQuery(sql);
            List resultList = q.list();
            for (Object o : resultList) {
                Userreg usereg = (Userreg) o;
                savedUsername =  usereg.getUsername();
                savedPassword =  usereg.getPassword();
                role = usereg.getNama();
                id_user = usereg.getId();
                System.out.println(role);
            }
            session.getTransaction().commit();
            if (savedPassword == null || savedUsername == null) {
                autentikasi(" ", " ");
            } else {
                autentikasi(savedPassword, savedUsername);
            }

        } catch (Exception e) {
            System.out.println(savedUsername + " "+savedPassword +" " + e.getMessage());
        }
    }
      private void autentikasi(String savedPassword, String savedUsername) throws IOException {
        if (savedPassword.equals(password.getText()) && savedUsername.equals(username.getText())) {
           if(role.equals("admin")){
              AnchorPane panes = FXMLLoader.load(getClass().getResource("/ui/BerandaAdmin.fxml"));
              login.getChildren().setAll(panes);  
           }else{
                AnchorPane panes = FXMLLoader.load(getClass().getResource("/ui/BerandaGuest.fxml"));
                login.getChildren().setAll(panes);
           }           
        } else {
            System.out.println(savedPassword +" "+ savedUsername);
            Alert alert = new Alert(Alert.AlertType.NONE, "Username atau password salah", ButtonType.OK);
            alert.setTitle("Username atau password salah");
            alert.showAndWait();
        }

    }

    @FXML
    private void actionLogin(ActionEvent event) {
           runLogin();
    }
    @FXML
    private void actionRegister(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/ui/Register.fxml"));
        login.getChildren().setAll(pane);
        
    }
}
