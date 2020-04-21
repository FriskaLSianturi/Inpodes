/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desa;

import entity.Desa;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import desa.LoginController.*;
import static desa.LoginController.role;
import org.hibernate.Query; 
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.NewHibernateUtil;
 

/**
 * FXML Controller class
 *
 * @author frisca
 */
public class CrudDesaController implements Initializable {

    @FXML
    private AnchorPane ApDesa;
    @FXML
    private TextField fNamaDesa;
    @FXML
    private TextArea fDeskripsi;
    @FXML
    private TextArea fVisiMisi;
    @FXML
    private TextArea fOrganisasi;
    @FXML
    private TextArea fPrestasi;
    @FXML
    private TextArea fObjectWisata;
    @FXML
    private TextField fSearchDesa;
    @FXML
    private TableView<Desa> tableDesa;
    @FXML
    private TableColumn<Desa, Integer> cIdDesa;
    @FXML
    private TableColumn<Desa, String> cNama;
    @FXML
    private TableColumn<Desa, String> cDeskripsi;
    @FXML
    private TableColumn<Desa, String> cVisi;
    @FXML
    private TableColumn<Desa, String> cOrganisasi;
    @FXML
    private TableColumn<Desa, String> cPrestasi;
    @FXML
    private TableColumn<Desa, String> cobjectWisata;
//    @FXML
//    private TableColumn<Desa, String> cGambar;
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
    private Button btnSearch;
    
    public static int id=0;
       
    model.modelDesa desaimpl = new model.modelDesa();
    
    ObservableList listDesa = FXCollections.observableArrayList();
    ObservableList<Desa> listCari = null;
    private static String QUERY_BASED_NAME ="from Desa a where a.namaDesa like '";
    String key=null;
   
    
    
    
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
                Desa d = new Desa();
                d.setNamaDesa(fNamaDesa.getText());
                d.setDeskripsiDesa(fDeskripsi.getText());
                d.setVisiMisi(fVisiMisi.getText());
                d.setOrganisasi(fOrganisasi.getText());
                d.setPrestasi(fPrestasi.getText());
                d.setObjectWisata(fObjectWisata.getText());
                //saveImage();
                //d.setGambar(gambar);
                desaimpl.add(d);              
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
                Desa d = new Desa();
                d.setId(Integer.parseInt(key));
                d.setNamaDesa(fNamaDesa.getText());
                d.setDeskripsiDesa(fDeskripsi.getText());
                d.setVisiMisi(fVisiMisi.getText());
                d.setOrganisasi(fOrganisasi.getText());
                d.setPrestasi(fPrestasi.getText());
                d.setObjectWisata(fObjectWisata.getText());
               //saveImage(); 
               // d.setGambar(gambar);
                desaimpl.update(d);                
                clear();
                read();
            }
        });
    }
    
    @FXML
    private void actionDelete(ActionEvent event) {
         try{
             btnDelete.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                Desa d = new Desa();                
                d.setId(Integer.parseInt(key));
                d.setNamaDesa(fNamaDesa.getText());
                d.setDeskripsiDesa(fDeskripsi.getText());
                d.setVisiMisi(fVisiMisi.getText());
                d.setOrganisasi(fOrganisasi.getText());
                d.setPrestasi(fPrestasi.getText());
                d.setObjectWisata(fObjectWisata.getText());
                //saveImage();
               // d.setGambar(gambar);               
                desaimpl.delete(d);
                clear();
               read();
             }
         });
         }catch(Exception e){
             System.out.println(e.getMessage());
         }
    }
    @FXML
    public void actionBack(ActionEvent event){
        btnBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    back();
                } catch (IOException ex) {
                    Logger.getLogger(CrudDesaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    @FXML
    public void actionLogout(ActionEvent event){
        btnLogout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                      AnchorPane pane = FXMLLoader.load(getClass().getResource("/ui/Login.fxml"));
                      ApDesa.getChildren().setAll(pane);
                } catch (IOException ex) {
                    Logger.getLogger(CrudDesaController.class.getName()).log(Level.SEVERE, null, ex);
                }
              
            }
        });
    }
    public void read(){
        tableDesa.getItems().clear();
        List<Desa> ls = desaimpl.read();
        for(Desa d: ls){
            listDesa.add(d);
        
        cIdDesa.setCellValueFactory(new PropertyValueFactory<>("id"));
        cNama.setCellValueFactory(new PropertyValueFactory<>("namaDesa"));
        cDeskripsi.setCellValueFactory(new PropertyValueFactory<>("deskripsiDesa"));
        cVisi.setCellValueFactory(new PropertyValueFactory<>("visiMisi"));
        cOrganisasi.setCellValueFactory(new PropertyValueFactory<>("organisasi"));
        cPrestasi.setCellValueFactory(new PropertyValueFactory<>("prestasi"));
        cobjectWisata.setCellValueFactory(new PropertyValueFactory<>("objectWisata")); 
        tableDesa.setItems(listDesa);        
        }
    }

    private void selectRow() {
      tableDesa.setOnMouseClicked(new EventHandler<MouseEvent>(){
          @Override
          public void handle(MouseEvent event) {
              int i = tableDesa.getSelectionModel().getSelectedIndex();
              key = String.valueOf(cIdDesa.getCellData(i)); 
              if(!role.equals("admin")){
                  
               fDeskripsi.setText("Detail Desa\n"+
                  "Nama Desa\t:"+cNama.getCellData(i) + "\n" + "Deskripsi \t:"+ cDeskripsi.getCellData(i) +"\n" +
                  "Visi\t\t\t:"+ cVisi.getCellData(i) + "\n"+ "Organisasi\t:" + cOrganisasi.getCellData(i) +"\n" +
                  "Prestasi\t\t:" +cPrestasi.getCellData(i) + "\n" +"Object Wisata:\t" +  cobjectWisata.getCellData(i)); 
                  fDeskripsi.setEditable(false); 
              }else{
                    fNamaDesa.setText(cNama.getCellData(i));
              fDeskripsi.setText(cDeskripsi.getCellData(i));
              fVisiMisi.setText(cVisi.getCellData(i));
              fOrganisasi.setText(cOrganisasi.getCellData(i));
              fPrestasi.setText(cPrestasi.getCellData(i));
              fObjectWisata.setText(cobjectWisata.getCellData(i));
              }
          }
          
      });
    }
    private void clear(){
        key = null;
        fNamaDesa.clear();
        fDeskripsi.clear();         
        fVisiMisi.clear();
        fOrganisasi.clear();
        fPrestasi.clear();
        fObjectWisata.clear();
    }
    
    public void back() throws IOException{
            if(role.equals("admin")){
                AnchorPane ap = FXMLLoader.load(getClass().getResource("/ui/BerandaAdmin.fxml"));
                ApDesa.getChildren().setAll(ap);
            }else{
                AnchorPane ap = FXMLLoader.load(getClass().getResource("/ui/BerandaGuest.fxml"));
            ApDesa.getChildren().setAll(ap);
            }
    }
    @FXML
    private void actionSearch(ActionEvent event) {
       btnSearch.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               runQueryBasedNamaDesa();
           }
       });
    }
    public void runQueryBasedNamaDesa(){
        ececuteQueryCari(QUERY_BASED_NAME + fSearchDesa.getText() +"%'");
    }
    private void ececuteQueryCari(String sql){
        try{
            Session session = NewHibernateUtil.getSessionFactory().openSession();
            Transaction trans =session.beginTransaction();
            Query q = session.createQuery(sql);
            List resultList = q.list();
            displayResult(resultList);
            session.getTransaction().commit();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }


    private void displayResult(List resultList) {
        listCari = FXCollections.observableArrayList();
        for(Object u: resultList){
              Desa d = (Desa)u;
              listCari.add(d);
        }
        cIdDesa.setCellValueFactory(new PropertyValueFactory<>("id"));
        cNama.setCellValueFactory(new PropertyValueFactory<>("namaDesa"));
        cDeskripsi.setCellValueFactory(new PropertyValueFactory<>("deskripsiDesa"));
        cVisi.setCellValueFactory(new PropertyValueFactory<>("visiMisi"));
        cOrganisasi.setCellValueFactory(new PropertyValueFactory<>("organisasi"));
        cPrestasi.setCellValueFactory(new PropertyValueFactory<>("prestasi"));
        cobjectWisata.setCellValueFactory(new PropertyValueFactory<>("objectWisata")); 
        tableDesa.getColumns().clear();
        tableDesa.setItems(listCari);
        tableDesa.getColumns().addAll(cIdDesa, cNama, cDeskripsi, cVisi, cOrganisasi,cPrestasi, cobjectWisata);
        
        tableDesa.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectRow();
            }
        });
    }
        /*    @FXML
    public void actionPilihGambar(MouseEvent event){
        try{
            file =  fileChooser.showOpenDialog(null);
        
            if(file != null){
                try {
                    String namaFoto;
                    //namaFoto = tfNamaBarang.getText();
                    BufferedImage bufferedImage = ImageIO.read(file);
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    GambarDesa.setFitWidth(180);
                    GambarDesa.setFitHeight(110);
                    GambarDesa.setPreserveRatio(true);
                    GambarDesa.setImage(image);
                    gambar = file.getName();
                    files = Paths.get(file.toURI());
                    
                    System.out.println(gambar);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
        
    private void saveImage(){
        System.out.println("save");
         if (gambar != null) {
            try {
                File dir = new File(System.getProperty("user.dir"));
                copy = Paths.get(dir+"/src/gambar/"+gambar);
                CopyOption[] options = new CopyOption[]{
                        StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.COPY_ATTRIBUTES
                };
                Files.copy(files, copy,options);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            
        }
     }*/

    
}
