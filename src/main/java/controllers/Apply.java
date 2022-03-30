package main.java.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Apply implements Initializable {
    //   @FXML private TableView TableView;
    @FXML
    private Button btnEdit;
    // Bouton Valider
    @FXML
    private Button btnValider;
    private boolean okClicked = false;
    private Bibliotheque bibliotheque = new Bibliotheque();
    @FXML
    private Button btnSuppr;
    @FXML
    private MenuItem MenuAboutInfos;
    @FXML
    private MenuItem MenuEditionSauvegarder;
    @FXML
    private MenuItem MenuEditionSauvegarderSous;
    @FXML
    private MenuItem MenuFichierOuvrir;
    @FXML
    private javafx.scene.control.MenuItem closeButton;


    @FXML
    void closeButtonAction() {
        System.exit(0);
    }

    @FXML
    void editionsauvegardersous(ActionEvent event) {
        jaxbObjectToXML(bibliotheque);
    }

    @FXML
    void fichierouvrir(ActionEvent event) {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter(
                "xml files (*.xml)", "xml");

        chooser.setDialogTitle("Open schedule file");
        // set selected filter
        chooser.setFileFilter(xmlfilter);
        int value = chooser.showOpenDialog(null);

        if (value == JFileChooser.APPROVE_OPTION) {
            File target = chooser.getSelectedFile();
            try {
                JAXBContext context = JAXBContext.newInstance(Bibliotheque.class);
                Unmarshaller mapperXMLObjet = context.createUnmarshaller();
                Bibliotheque bibliotheque = (Bibliotheque) mapperXMLObjet.unmarshal(target);
                JFrame jFrame = new JFrame();
                JOptionPane.showMessageDialog(jFrame, "test");
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
    }

    private static void jaxbObjectToXML(Bibliotheque bibliotheque) {
        try {
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
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }


    ////////////// tableau ///////////////////
    @FXML
    private TableView<Livre> TableView;
    @FXML
    private TableColumn<Livre, String> titre;
    @FXML
    private TableColumn<Livre, String> auteur;
    @FXML
    private TableColumn<Livre, String> presentation;
    @FXML
    private TableColumn<Livre, String> parution;
    @FXML
    private TableColumn<Livre, Integer> colonne;
    @FXML
    private TableColumn<Livre, Integer> rangee;

    ObservableList<Livre> list = FXCollections.observableArrayList(bibliotheque.getLivreList());

    public void initialize(URL url, ResourceBundle rb) {
        // Initialiser les colonnes
        titre.setCellValueFactory(new PropertyValueFactory<Livre, String>("titre"));
        auteur.setCellValueFactory(new PropertyValueFactory<Livre, String>("auteur"));
        presentation.setCellValueFactory(new PropertyValueFactory<Livre, String>("presentation"));
        parution.setCellValueFactory(new PropertyValueFactory<Livre, String>("parution"));
        colonne.setCellValueFactory(new PropertyValueFactory<Livre, Integer>("colonne"));
        rangee.setCellValueFactory(new PropertyValueFactory<Livre, Integer>("rangee"));

        TableView.setItems(list);

        // Clear person details.
        //showLivreDetails(null);
    }
}