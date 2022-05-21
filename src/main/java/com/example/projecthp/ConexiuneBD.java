package com.example.projecthp;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class ConexiuneBD {
    public static String encodePassword(String salt, String password){
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));
        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
        return new String(hashedPassword,StandardCharsets.UTF_8).replace("\"", "");
    }

    private static MessageDigest getMessageDigest(){
        MessageDigest md;
        try{
            md = MessageDigest.getInstance("SHA-512");
        }catch(NoSuchAlgorithmException e){
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }
    public static void schimbaScene(ActionEvent event, String fxmlFile, String titlu, String nume, String rol) {
        Parent root = null;

        if (nume != null && rol!=null) {
            try{
                FXMLLoader loader = new FXMLLoader(ConexiuneBD.class.getResource(fxmlFile));
                root = loader.load();
                DeconController deconController = loader.getController();
                deconController.setUserInformation(nume,rol);
            }catch (IOException e){
                e.printStackTrace();
            }
        }else
        {
            try {
                root=FXMLLoader.load(ConexiuneBD.class.getResource(fxmlFile));
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(titlu);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void inregistrareUser(ActionEvent event, String nume, String parola, String rol) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "rootpassword");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM utilizatori WHERE nume = ?");
            psCheckUserExists.setString(1, nume);
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()) {
                System.out.println("Acest cont este deja creat!\n");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Nu puteti folosi acest nume.\n");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO utilizatori (nume, parola, rol) VALUES (?, ?, ?)");
                psInsert.setString(1, nume);
                psInsert.setString(2, encodePassword(nume, parola));
                psInsert.setString(3, rol);
                psInsert.executeUpdate();

                if(rol.equals("Pacient")) {
                    schimbaScene(event, "pagPrincipala.fxml", "Healthy Plate", null, null);
                } else if(rol.equals("Medic")) {
                    schimbaScene(event, "formular.fxml", "Formular", null, null);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckUserExists != null){
                try {
                    psCheckUserExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(psInsert!=null){
                try {
                    psInsert.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection!=null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public static void  conectareUser(ActionEvent event, String nume, String parola){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "rootpassword");
            preparedStatement = connection.prepareStatement("SELECT parola, rol FROM utilizatori WHERE nume = ?");
            preparedStatement.setString(1, nume);
            resultSet = preparedStatement.executeQuery();

            if(!resultSet.isBeforeFirst()){
                System.out.println("Utilizatorul nu a fost gasit.\n");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Datele introduse sunt incorecte.\n");
                alert.show();
            }else {
                while (resultSet.next()) {
                    String retrievedPassword = resultSet.getString("parola");
                    String retrievedRole = resultSet.getString("rol");
                    if(retrievedPassword.equals(encodePassword(nume,parola))){
                        if(retrievedRole.equals("Pacient")) {
                            schimbaScene(event, "pagPrincipala.fxml", "Healthy Plate", null, null);
                        } else if(retrievedRole.equals("Medic")) {
                            schimbaScene(event, "principal.fxml", "Healthy Plate", null, null);
                        }



                    } else {
                        System.out.println("Parola este incorecta!\n");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Datele introduse sunt incorecte.\n");
                        alert.show();
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(resultSet!=null){
                try {
                    resultSet.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection!=null){
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void inregistrareMedic(ActionEvent event, String nume, String email, String specializare, String loc_munca) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "rootpassword");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM formular WHERE nume = ?");
            psCheckUserExists.setString(1, nume);
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()) {
                System.out.println("Acest medic este deja inregistrat!\n");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Nu puteti folosi acest nume.\n");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO formular (nume, email, specializare, loc_munca) VALUES (?, ?, ?, ?)");
                psInsert.setString(1, nume);
                psInsert.setString(2, email);
                psInsert.setString(3, specializare);
                psInsert.setString(4,loc_munca);
                psInsert.executeUpdate();


                schimbaScene(event, "principal.fxml", "Healthy Plate", null, null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckUserExists != null){
                try {
                    psCheckUserExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(psInsert!=null){
                try {
                    psInsert.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection!=null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void inregistrareArticol(ActionEvent event, String nume_autor, String nume_articol, String link) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "rootpassword");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM articole WHERE nume_articol = ?");
            psCheckUserExists.setString(1, nume_articol);
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()) {
                System.out.println("Acest articol este deja adaugat!\n");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Nu puteti folosi acest nume de articol.\n");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO articole (nume_autor, nume_articol, link) VALUES (?, ?, ?)");
                psInsert.setString(1, nume_autor);
                psInsert.setString(2, nume_articol);
                psInsert.setString(3, link);
                psInsert.executeUpdate();


                schimbaScene(event, "principal.fxml", "Healthy Plate", null, null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}