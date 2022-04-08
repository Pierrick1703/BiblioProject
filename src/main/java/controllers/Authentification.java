package main.java.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.java.model.User;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Authentification {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button validateButton;
    @FXML private Button cancelButton;

    //ce login en tant que différent user
    @FXML
    void validaton(ActionEvent event) throws IOException, SQLException {
        JFrame jFrame = new JFrame();
        String username = usernameField.getText();
        String passwordText = passwordField.getText();
        ArrayList<User> users = new ArrayList<User>();
        Boolean connecter = false;
        databaseConnection connectNow = new databaseConnection();
        Connection connectDB = connectNow.getConnection();

        String selectFields = "SELECT userName, password FROM user";
        String selectToRegister = selectFields;
        PreparedStatement statement = connectDB.prepareStatement(selectToRegister);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            String userName = result.getString("userName");
            String password = result.getString("password");
            User user = new User(userName, password);
            users.add(user);
        }
        for (User u : users) {
            if (u.getUsername().contains(username)) {
                if (u.getPassword().contains(passwordText)) {
                    connecter = true;
                }
            }
        }
        if (connecter == true) {
            try {
                ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/resources/Admin.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("Bibliothèque - M1 M2I ESIEE IT");
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(jFrame, "Connection mauvaise, nom d'utilisateur ou mot de passe incorrect");
        }
    }

    //arrêter le programme
    @FXML
    void fermeture(ActionEvent event) throws IOException {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        /*Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/resources/apply.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Bibliothèque - M1 M2I ESIEE IT");
        stage.setScene(scene);
        stage.show();*/
    }

    @FXML
    void closeButtonAction(ActionEvent event) throws IOException {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        /*Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/resources/apply.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Bibliothèque - M1 M2I ESIEE IT");
        stage.setScene(scene);
        stage.show();*/
    }
}
