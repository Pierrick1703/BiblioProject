package main.java.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class Authentification {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button validateButton;
    @FXML private Button cancelButton;

    //ce login en tant que différent user
    @FXML
    void validaton(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        //obligation d'utiliser contains than username == "pcot"
        if(username.contains("pcot") || username.contains("jkla")  || username.contains("cgob")){//accès simple
            JFrame jFrame = new JFrame();
            if(password.contains("1234")){
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/resources/apply.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = new Stage();
                    stage.setTitle("Bibliothèque - M1 M2I ESIEE IT");
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(jFrame, "Mot de passe incorrect");
            }
        } else if(username.contains("pcotin")  || username.contains("jklatt") || username.contains("cgobert")) {//accès admin

        } else {
            JFrame jFrame = new JFrame();
            JOptionPane.showMessageDialog(jFrame, "Nom d'utilisateur incorrect");
        }
    }
    //arrêter le programme
    @FXML
    void fermeture(ActionEvent event) {
        System.exit(0);
    }

}
