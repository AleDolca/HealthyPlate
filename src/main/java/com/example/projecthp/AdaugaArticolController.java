package com.example.projecthp;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.WeakEventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AdaugaArticolController implements Initializable {

    @FXML
    private Button buton_inapoi;

    @FXML
    private Button buton_salveaza;

    @FXML
    private TextField txt_articol;

    @FXML
    private TextField txt_autor;

    @FXML
    private TextField txt_link;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buton_salveaza.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if( !txt_autor.getText().trim().isEmpty() && !txt_articol.getText().trim().isEmpty()  && !txt_link.getText().trim().isEmpty()){
                    ConexiuneBD.inregistrareArticol(event, txt_autor.getText(), txt_articol.getText(), txt_link.getText());
                } else {
                    System.out.println("Completeaza toate campurile.\n");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Trebuie să completezi toate campurile!\n");
                    alert.show();
                }
            }
        });

        buton_inapoi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ConexiuneBD.schimbaScene(event, "principal.fxml", "Healthy Plate", null, null);
            }
        });

    }
}
