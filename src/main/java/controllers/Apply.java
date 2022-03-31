package main.java.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Modality;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;


public class Apply implements Initializable {
    @FXML private TextField TitreField;
    @FXML private TextField AuteurField;
    @FXML private TextField PresentationField;
    @FXML private TextField ParutionField;
    @FXML private TextField ColonneField;
    @FXML private TextField RangeeField;
    @FXML private Button btnSuppr;
    @FXML private MenuItem MenuAboutInfos;
    @FXML private MenuItem MenuEditionSauvegarder;
    @FXML private MenuItem MenuEditionSauvegarderSous;
    @FXML private MenuItem MenuFichierOuvrir;
    @FXML private javafx.scene.control.MenuItem closeButton;
    @FXML private Button btnEdit;
    ////////////// tableau ///////////////////
    @FXML  private TableView<Livre> TableView;
    @FXML  private TableColumn<Livre, String> titre;
    @FXML  private TableColumn<Livre, String> auteur;
    @FXML  private TableColumn<Livre, String> presentation;
    @FXML  private TableColumn<Livre, String> parution;
    @FXML  private TableColumn<Livre, Integer> colonne;
    @FXML  private TableColumn<Livre, Integer> rangee;
    // Bouton Valider
    @FXML private Button btnValider;
    private boolean okClicked = false;
    private Bibliotheque bibliotheque = new Bibliotheque();
    public boolean isOkClicked(){
        return okClicked;
    }
    private boolean isInputValid() {
        String errorMessage = "";

        if (TitreField.getText() == null || TitreField.getText().length() == 0) {
            errorMessage += "No valid titre!\n";
        }
        if (AuteurField.getText() == null || AuteurField.getText().length() == 0) {
            errorMessage += "No valid auteur!\n";
        }
        if (PresentationField.getText() == null || PresentationField.getText().length() == 0) {
            errorMessage += "No valid presentation!\n";
        }
        if (ParutionField.getText() == null || ParutionField.getText().length() == 0) {
            errorMessage += "No valid parution!\n";
        }
        if (ColonneField.getText() == null || ColonneField.getText().length() == 0) {
            errorMessage += "No valid colonne!\n";
        }
        try {
            Integer.parseInt(ColonneField.getText());
        }
        catch (Exception e){
            errorMessage += "No valid colonne!\n";
        }

        if (RangeeField.getText() == null || RangeeField.getText().length() == 0) {
            errorMessage += "No valid rangee!\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();
        }
        return false;
    }

    @FXML
    void aboutinfos(ActionEvent event) throws IOException {
    }

    @FXML
    void closeButtonAction() {
        System.exit(0);
    }

    @FXML
    void editionsauvegarder(ActionEvent event) {
    }

    @FXML
    void editionsauvegardersous(ActionEvent event) {
        jaxbObjectToXML(bibliotheque);
    }

    @FXML
    void fichierouvrir(ActionEvent event) {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter("xml files (*.xml)", "xml");
        chooser.setDialogTitle("Open schedule file");
        chooser.setFileFilter(xmlfilter);
        int value = chooser.showOpenDialog(null);
        if(value == JFileChooser.APPROVE_OPTION) {
            File target = chooser.getSelectedFile();
            try {
                JAXBContext context = JAXBContext.newInstance(Bibliotheque.class);
                Unmarshaller mapperXMLObjet = context.createUnmarshaller();
                bibliotheque = (Bibliotheque) mapperXMLObjet.unmarshal(target);
                ObservableList<Livre> list = FXCollections.observableArrayList(bibliotheque.getLivreList());
                TableView.setItems(list);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
    }

    // Bouton Suppression
    @FXML
    void handleDeleteLivre(ActionEvent event) {
        int selectedIndex = TableView.getSelectionModel().getSelectedIndex();
        Livre selectedLivre = TableView.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0) {
            //déclarer en erreur sur jetBrain mais fonctionne
            TableView.getItems().remove(selectedIndex);
            bibliotheque.supprLivre(selectedLivre);
            jaxbObjectToXML(bibliotheque);
        } else {
            // Nothing selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune sélection");
            alert.setHeaderText("Aucun livre sélectionné");
            alert.setContentText("Merci de choisir un livre dans le tableau.");

            alert.showAndWait();
        }
    }

    @FXML
    void handleModifLivre(ActionEvent event) {
        //déclarer en erreur sur jetBrain mais fonctionne
        Livre selectedLivre = TableView.getSelectionModel().getSelectedItem();
        int selectedIndex = TableView.getSelectionModel().getSelectedIndex();
        if (selectedLivre != null) {
            bibliotheque.supprLivre(selectedLivre);
            TableView.getItems().remove(selectedIndex);
            TitreField.setText(selectedLivre.titre);
            AuteurField.setText(selectedLivre.auteur);
            PresentationField.setText(selectedLivre.presentation);
            ParutionField.setText(String.valueOf(selectedLivre.parution));
            int colonne = selectedLivre.colonne;
            ColonneField.setText(String.valueOf(colonne));
            int rangee = selectedLivre.rangee;
            RangeeField.setText(String.valueOf(rangee));
        }
    }

    private static void jaxbObjectToXML(Bibliotheque bibliotheque){
        try
        {
            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(Bibliotheque.class);
            //Create Marshaller
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            //Required formatting??
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            //Store XML to File
            File file = new File("Bibliotheque.xml");
            //Writes XML file to file-system
            jaxbMarshaller.marshal(bibliotheque, file);
        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }
    }
    public void initialize (URL url, ResourceBundle rb){
        startTableViewBDD();
        // Initialiser les colonnes
        titre.setCellValueFactory(new PropertyValueFactory<Livre,String>("titre"));
        auteur.setCellValueFactory(new PropertyValueFactory<Livre,String>("auteur"));
        presentation.setCellValueFactory(new PropertyValueFactory<Livre,String>("presentation"));
        parution.setCellValueFactory(new PropertyValueFactory<Livre,String>("parution"));
        colonne.setCellValueFactory(new PropertyValueFactory<Livre,Integer>("colonne"));
        rangee.setCellValueFactory(new PropertyValueFactory<Livre,Integer>("rangee"));

        ObservableList<Livre> list = FXCollections.observableArrayList(bibliotheque.getLivreList());
        TableView.setItems(list);

        // Listener
        TableView.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue,newValue) -> showLivreDetails(newValue))
        );

        btnValider.setOnMouseClicked(btnAction -> {
            if (isInputValid()){
                Livre monLivre = new Livre(TitreField.getText(), AuteurField.getText(), PresentationField.getText(), Integer.parseInt(ParutionField.getText()), Integer.parseInt(ColonneField.getText()), Integer.parseInt(RangeeField.getText()));
                //déclarer en erreur sur jetBrain mais fonctionne
                bibliotheque.addLivre(monLivre);
                TableView.getItems().add(monLivre);
                jaxbObjectToXML(bibliotheque);
            }
        });

    }

    private void startTableViewBDD(){
            try {
                File file = new File("Bibliotheque.xml");
                JAXBContext context = JAXBContext.newInstance(Bibliotheque.class);
                Unmarshaller mapperXMLObjet = context.createUnmarshaller();
                bibliotheque = (Bibliotheque) mapperXMLObjet.unmarshal(file);
            } catch(JAXBException d){
                d.printStackTrace();
            }
        }


    private void showLivreDetails(Livre livre){
        if (livre != null) {
            // Fill the labels with info from the person object.
            titre.setText(livre.getTitre());
            auteur.setText(livre.getAuteur());
            presentation.setText(livre.getPresentation());
            parution.setText(String.valueOf(livre.getParution()));
            colonne.setText(String.valueOf(livre.getColonne()));
            rangee.setText(String.valueOf(livre.getRangee()));
        }
        else {
            // Livre is null, remove all the text.
            titre.setText("");
            auteur.setText("");
            presentation.setText("");
            parution.setText("");
            colonne.setText("");
            rangee.setText("");
        }
    }

    public void switchAccount(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/resources/Authentification.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Bibliothèque - M1 M2I ESIEE IT");
        stage.setScene(scene);
        stage.show();
    }
}