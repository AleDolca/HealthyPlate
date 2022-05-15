package com.example.projecthp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class DeconController implements Initializable {

    @FXML
    private Button buton_deconectare;

    @FXML
    private Label label_welcome;

    @FXML
    private Label label_rol;

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        buton_deconectare.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            ConexiuneBD.schimbaScene(event, "conectare.fxml","Conectare", null,  null);
            }
        });
    }
    public void setUserInformation(String nume, String rol) {
        label_welcome.setText("Bine ai venit " + nume + "!");
        label_rol.setText("Rolul tău este - " + rol + "!");
    }
}
