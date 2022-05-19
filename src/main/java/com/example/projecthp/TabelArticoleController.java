package com.example.projecthp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TabelArticoleController {

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

    }

}