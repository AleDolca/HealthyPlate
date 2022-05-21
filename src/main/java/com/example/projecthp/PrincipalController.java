package com.example.projecthp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.WeakEventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class PrincipalController implements Initializable {

    @FXML
    private Button buton_articole;

    @FXML
    private Button buton_chat;

    @FXML
    private Button buton_deconectare;

    @FXML
    private Button buton_medici;

    @FXML
    private Button nume1;

    @FXML
    private Button nume2;

    @FXML
    private Button nume3;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buton_deconectare.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ConexiuneBD.schimbaScene(event,"deconectare.fxml", "Deconectare", null, null);
            }
        });

        buton_chat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ConexiuneBD.schimbaScene(event,"chat.fxml", "Chat", null, null);
            }
        });

        nume1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ConexiuneBD.schimbaScene(event,"doctor1.fxml", "Profil", null, null);
            }
        });

        nume2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ConexiuneBD.schimbaScene(event,"doctor2.fxml", "Profil", null, null);
            }
        });

        nume3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ConexiuneBD.schimbaScene(event,"doctor3.fxml", "Profil", null, null);
            }
        });

        buton_medici.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ConexiuneBD.schimbaScene(event,"tabelMedici.fxml", "Medici", null, null);
            }
        });

        buton_articole.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ConexiuneBD.schimbaScene(event,"adaugaArticole.fxml", "Adaugă articol", null, null);
            }
        });
    }
}