module com.example.projecthp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires java.sql;


    opens com.example.projecthp to javafx.fxml;
    exports com.example.projecthp;
}