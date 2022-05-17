package com.example.projecthp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class formularController implements Initializable {


    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_locdemunca;

    @FXML
    private TextField txt_nume;

    @FXML
    private Button buton_salveaza;

    @FXML
    private TextField txt_specializare;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buton_salveaza.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if( !txt_nume.getText().trim().isEmpty() && !txt_email.getText().trim().isEmpty()  && !txt_specializare.getText().trim().isEmpty() && !txt_locdemunca.getText().trim().isEmpty() ){
                    ConexiuneBD.inregistrareMedic(event, txt_nume.getText(), txt_email.getText(), txt_specializare.getText(), txt_locdemunca.getText());
                } else {
                    System.out.println("Completeaza toate campurile.\n");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Trebuie să completezi toate campurile!\n");
                    alert.show();
                }
            }
        });
    }
}
