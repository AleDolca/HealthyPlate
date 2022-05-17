package com.example.projecthp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class Doctor2 implements Initializable {

    @FXML
    private Button buton_back;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buton_back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ConexiuneBD.schimbaScene(event,"pagPrincipala.fxml", "HealthyPlate", null, null);
            }
        });
    }
}