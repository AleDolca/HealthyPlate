package com.example.projecthp;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TabelMediciController implements Initializable {

    @FXML
    private Button buton_inapoi;

    @FXML
    private TableColumn<ClasaMedici, String> c_email;

    @FXML
    private TableColumn<ClasaMedici, String> c_loc_munca;

    @FXML
    private TableColumn<ClasaMedici, String> c_nume;

    @FXML
    private TableColumn<ClasaMedici, String> c_specializare;

    @FXML
    private TableView<ClasaMedici> tabel;

    ObservableList<ClasaMedici> listaMedici = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ConexiuneFormular connectNow = new ConexiuneFormular();
        Connection connectDB = connectNow.getDBConnection();

        String productViewQuery = "SELECT * FROM formular";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOut = statement.executeQuery(productViewQuery);

            while (queryOut.next()){
                String qnume=queryOut.getString("nume");
                String qemail=queryOut.getString("email");
                String qspecializare=queryOut.getString("specializare");
                String qloc_munca=queryOut.getString("loc_munca");
                listaMedici.add(new ClasaMedici(qnume, qemail, qspecializare, qloc_munca));

            }

            c_nume.setCellValueFactory(new PropertyValueFactory<>("nume"));
            c_email.setCellValueFactory(new PropertyValueFactory<>("email"));
            c_specializare.setCellValueFactory(new PropertyValueFactory<>("specializare"));
            c_loc_munca.setCellValueFactory(new PropertyValueFactory<>("loc_munca"));

            tabel.setItems(listaMedici);

        }catch (SQLException e){
            Logger.getLogger(TabelMediciController.class.getName()).log(Level.SEVERE, null, e);
        }

        buton_inapoi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ConexiuneBD.schimbaScene(event, "pagPrincipala.fxml", "HealthyPlate", null, null);
            }
        });
    }
}
