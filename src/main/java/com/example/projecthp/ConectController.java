package com.example.projecthp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
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

    @FXML
    void onClickButton(ActionEvent event) {



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        class SceneController {

            private Stage stage;
            private Scene scene;
            private Parent root;


        }
    }

}
