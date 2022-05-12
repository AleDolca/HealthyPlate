module com.example.projecthp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projecthp to javafx.fxml;
    exports com.example.projecthp;
}