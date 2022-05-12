package com.example.projecthp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class RegisterController  implements Initializable {

    @FXML
    private Button buton_creare;

    @FXML
    private ChoiceBox <String> choiceBox;

    @FXML
    private Label labelChoiceBox;

    @FXML
    private TextField txt_nume;

    @FXML
    private TextField txt_parola;

    @FXML
    void onClickButton(ActionEvent event) {

    }
    private String[] role = {"medic", "pacient"};


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
 choiceBox.getItems().addAll(role);

    }

}
