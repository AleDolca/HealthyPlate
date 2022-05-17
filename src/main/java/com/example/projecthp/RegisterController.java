package com.example.projecthp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;


public class RegisterController  implements Initializable {

    @FXML
    private Button buton_inregistrare;

    @FXML
    private Button buton_conectare;

    @FXML
    private RadioButton rb_pacient;

    @FXML
    private RadioButton rb_medic;

    @FXML
    private TextField txt_nume;

    @FXML
    private TextField txt_parola;

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        ToggleGroup toggleGroup = new ToggleGroup();
        rb_pacient.setToggleGroup(toggleGroup);
        rb_medic.setToggleGroup(toggleGroup);

        rb_pacient.setSelected(true);

        buton_inregistrare.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String toggleName = ((RadioButton) toggleGroup.getSelectedToggle()).getText();
                if( !txt_nume.getText().trim().isEmpty() && !txt_parola.getText().trim().isEmpty()){
                    ConexiuneBD.inregistrareUser(event, txt_nume.getText(), txt_parola.getText(), toggleName);
                    if(rb_pacient.isSelected()){
                        ConexiuneBD.schimbaScene(event, "pagPrincipala.fxml", "HealthyPlate", null, null);
                    }
                    else if(rb_medic.isSelected()){
                        ConexiuneBD.schimbaScene(event, "formular.fxml", "Formular", null, null);
                    }
                } else {
                    System.out.println("Completeaza toate campurile.\n");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Trebuie să completezi toate campurile!\n");
                    alert.show();
                }
            }
        });

        buton_conectare.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ConexiuneBD.schimbaScene(event, "conectare.fxml", "Conectare", null, null);
            }
        });
    }

}