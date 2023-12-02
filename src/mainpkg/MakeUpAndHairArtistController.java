/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package mainpkg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class MakeUpAndHairArtistController implements Initializable {

    @FXML
    private ComboBox<String> ArtistTypeComboBox;
    @FXML
    private ComboBox<String> ShiftComboBox;
    @FXML
    private TextField NameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField mobileNumberTextField;
    @FXML
    private TextField SalaryTextField;
    @FXML
    private DatePicker DateDatePicker;
    @FXML
    private TableView<MakeUpAndArtistTable> TableTableView;
    @FXML
    private TableColumn<MakeUpAndArtistTable, String> ArtistTypeColumn;
    @FXML
    private TableColumn<MakeUpAndArtistTable, String> NameColumn;
    @FXML
    private TableColumn<MakeUpAndArtistTable, Integer> MobileNumberColumn;
    @FXML
    private TableColumn<MakeUpAndArtistTable, String> ShiftColumn;
    @FXML
    private TableColumn<MakeUpAndArtistTable, String> EmailColumn;
    @FXML
    private TableColumn<MakeUpAndArtistTable, LocalDate> DateColumn;
    @FXML
    private TableColumn<MakeUpAndArtistTable, Float> SalaryColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            ArtistTypeComboBox.getItems().addAll("Makeup","Hair");
        ShiftComboBox.getItems().addAll("Day","Evening","Night");
    }    

    @FXML
    private void AddButtonOnMouseClick(ActionEvent event) {
          MakeUpAndArtistTable i = new MakeUpAndArtistTable(
    
    ArtistTypeComboBox.getValue(),
    NameTextField.getText(),
    Integer.parseInt(mobileNumberTextField.getText()),
    ShiftComboBox.getValue(),
    emailTextField.getText(),
    DateDatePicker.getValue(),
    Float.parseFloat(SalaryTextField.getText())
            
    );  
    FileOutputStream fos = null;
    ObjectOutputStream oos = null;
    File f = null;
    
    try {
    f = new File("MakeUp Artist List.bin");
    if(f.exists()){
        fos = new FileOutputStream(f,true);
        oos = new AppendableObjectOutputStream(fos);
        
    }else{
        fos = new FileOutputStream(f);
        oos = new ObjectOutputStream(fos);
    }
    
    oos.writeObject(i);
    }
     catch (IOException ex) {
            Logger.getLogger(MakeUpAndHairArtistController.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (oos != null) {
                    oos.close();

                }
            } catch (IOException ex) {
                Logger.getLogger(MakeUpAndHairArtistController.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        ArtistTypeComboBox.setValue(null);
    NameTextField.clear();
    mobileNumberTextField.clear();
    ShiftComboBox.setValue(null);
    emailTextField.clear();
    DateDatePicker.setValue(null);
    SalaryTextField.clear();
    }

    @FXML
    private void ShowButtonOnMouseClick(ActionEvent event) {
           
       ObservableList<MakeUpAndArtistTable> ArtistteamList = FXCollections.observableArrayList();
       
    ArtistTypeColumn.setCellValueFactory(new PropertyValueFactory<MakeUpAndArtistTable, String>("Type"));
    NameColumn.setCellValueFactory(new PropertyValueFactory<MakeUpAndArtistTable, String>("Name"));
    MobileNumberColumn.setCellValueFactory(new PropertyValueFactory<MakeUpAndArtistTable, Integer>("Number"));    
    ShiftColumn.setCellValueFactory(new PropertyValueFactory<MakeUpAndArtistTable, String>("Shift"));
    EmailColumn.setCellValueFactory(new PropertyValueFactory<MakeUpAndArtistTable, String>("Email"));
    DateColumn.setCellValueFactory(new PropertyValueFactory<MakeUpAndArtistTable, LocalDate>("Date"));
    SalaryColumn.setCellValueFactory(new PropertyValueFactory<MakeUpAndArtistTable, Float>("salary"));    
    
    File f =null;
    FileInputStream fis = null;
    ObjectInputStream ois = null;
  
         try {
            f = new File("MakeUp Artist List.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            MakeUpAndArtistTable p;
            try {
                while (true) {
                    p = (MakeUpAndArtistTable) ois.readObject();
                    ArtistteamList.add(p);
                }
            } catch (Exception e) {
            }
        } catch (IOException ex) {
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex) {
            }

        }
        TableTableView.setItems(ArtistteamList);
    }

    @FXML
    private void ExitButtonOnMouseClick(ActionEvent event) throws IOException {
        Parent mainSceneParent = FXMLLoader.load(getClass().getResource("ProductionManagerdashboard.fxml"));
            Scene scene1 = new Scene(mainSceneParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene1);
            window.show();
    }

    @FXML
    private void DeleteButtonOnMouseClick(ActionEvent event) {
               ObservableList<MakeUpAndArtistTable> a, b;
        b = TableTableView.getItems();
        a = TableTableView.getSelectionModel().getSelectedItems();
        a.forEach(b::remove);
    }
    
}
