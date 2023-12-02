/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package mainpkg;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ArtistTransportationController implements Initializable {

    @FXML
    private TableView<ProductionManagerTransportList> TableTableView;
    @FXML
    private TableColumn<ProductionManagerTransportList, String> NameColumn;
    @FXML
    private TableColumn<ProductionManagerTransportList, Integer> ContactColumn;
    @FXML
    private TableColumn<ProductionManagerTransportList, String> TypeColumn;
    @FXML
    private TableColumn<ProductionManagerTransportList, String> NumberColumn;
    @FXML
    private TableColumn<ProductionManagerTransportList, String> FromColumn;
    @FXML
    private TableColumn<ProductionManagerTransportList, String> ToColumn;
    @FXML
    private TableColumn<ProductionManagerTransportList, LocalDate> DateColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SearchButtonOnMouseClick(ActionEvent event) {
            ObservableList<ProductionManagerTransportList> transportLIst1 = FXCollections.observableArrayList();
   
     NameColumn.setCellValueFactory(new PropertyValueFactory<ProductionManagerTransportList, String>("name"));
        ContactColumn.setCellValueFactory(new PropertyValueFactory<ProductionManagerTransportList, Integer>("contact"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<ProductionManagerTransportList, String>("Type"));
        NumberColumn.setCellValueFactory(new PropertyValueFactory<ProductionManagerTransportList, String>("plate"));
        FromColumn.setCellValueFactory(new PropertyValueFactory<ProductionManagerTransportList, String>("From"));
        ToColumn.setCellValueFactory(new PropertyValueFactory<ProductionManagerTransportList, String>("To"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<ProductionManagerTransportList, LocalDate>("date"));   
      
        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            f = new File("Transport List.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            ProductionManagerTransportList p;
            try {
                while (true) {
                    p = (ProductionManagerTransportList) ois.readObject();
                    transportLIst1.add(p);
                    System.out.println(p.toString());
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
        TableTableView.setItems(transportLIst1);

    }

    @FXML
    private void ExitButtonOnMouseClick(ActionEvent event) throws IOException {
         Parent mainSceneParent = FXMLLoader.load(getClass().getResource("ArtistDashBoard.fxml"));
            Scene scene1 = new Scene(mainSceneParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene1);
            window.show();
    }
    
}
