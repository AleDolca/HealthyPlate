package com.example.projecthp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ConectController implements Initializable {

    @FXML
    private Button buton_conectare;

    @FXML
    private Button buton_inregistrare;

    @FXML
    private TextField txt_nume;

    @FXML
    private TextField txt_parola;


    @Override
    public void initialize(URL url, ResourceBundle resources) {
        buton_conectare.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ConexiuneBD.conectareUser(event, txt_nume.getText(), txt_parola.getText());

            }
        });

        buton_inregistrare.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ConexiuneBD.schimbaScene(event,"inregistrare.fxml", "Înregistrare", null, null);
            }
        });
    }

}
