package com.example.projecthp;

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

public class TabelArticoleController implements Initializable {

    @FXML
    private Button buton_inapoi;

    @FXML
    private TableColumn<ClasaArticole, String> c_articol;

    @FXML
    private TableColumn<ClasaArticole, String> c_autor;

    @FXML
    private TableColumn<ClasaArticole, String> c_link;

    @FXML
    private TableView<ClasaArticole> tabel;

    ObservableList<ClasaArticole> listaArticole = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ConexiuneArticole connectNow = new ConexiuneArticole();
        Connection connectDB = connectNow.getDBConnection();

        String productViewQuery = "SELECT * FROM articole";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOut = statement.executeQuery(productViewQuery);

            while (queryOut.next()){
                String qautor=queryOut.getString("nume_autor");
                String qarticol=queryOut.getString("nume_articol");
                String qlink=queryOut.getString("link");
                listaArticole.add(new ClasaArticole(qautor, qarticol, qlink));

            }

            c_autor.setCellValueFactory(new PropertyValueFactory<>("nume_autor"));
            c_articol.setCellValueFactory(new PropertyValueFactory<>("nume_articol"));
            c_link.setCellValueFactory(new PropertyValueFactory<>("link"));

            tabel.setItems(listaArticole);

        }catch (SQLException e){
            Logger.getLogger(TabelArticoleController.class.getName()).log(Level.SEVERE, null, e);
        }

        buton_inapoi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ConexiuneBD.schimbaScene(event, "pagPrincipala.fxml", "Healthy Plate", null, null);
            }
        });
    }
}