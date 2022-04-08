package main.java.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.poi.xwpf.usermodel.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;


public class Admin implements Initializable {
    @FXML private TextField TitreField;
    @FXML private TextField AuteurField;
    @FXML private TextField PresentationField;
    @FXML private TextField ParutionField;
    @FXML private TextField ColonneField;
    @FXML private TextField RangeeField;
    @FXML private TextField resumeeField;
    @FXML private ComboBox etatField = new ComboBox();;
    @FXML private Button btnSuppr;
    @FXML private MenuItem MenuAboutInfos;
    @FXML private MenuItem MenuEditionSauvegarder;
    @FXML private MenuItem MenuEditionSauvegarderSous;
    @FXML private MenuItem MenuFichierOuvrir;
    @FXML private javafx.scene.control.MenuItem closeButton;
    @FXML private Button btnEdit;
    @FXML private ImageView imageViewParution;
    @FXML private Button buttonVerificationParution;
    ////////////// tableau ///////////////////
    @FXML  private TableView<Livre> TableView;
    @FXML  private TableColumn<Livre, String> titre;
    @FXML  private TableColumn<Livre, String> auteur;
    @FXML  private TableColumn<Livre, String> presentation;
    @FXML  private TableColumn<Livre, Integer> parution;
    @FXML  private TableColumn<Livre, Short> colonne;
    @FXML  private TableColumn<Livre, Short> rangee;
    @FXML private TableColumn<Livre, String> etat;
    @FXML private TableColumn<Livre, String> resumer;
    // Bouton Valider
    @FXML private Button btnValider;
    private boolean okClicked = false;
    private Bibliotheque bibliotheque = new Bibliotheque();
    public boolean isOkClicked(){
        return okClicked;
    }
    databaseConnection connectNow = new databaseConnection();
    Connection connectDB = connectNow.getConnection();
    private boolean isInputValid() {
        String errorMessage = "";

        //Tri sur tout les messages d'erreur possible sur les champs si ils ne sont pas remplis convenablement
        if (TitreField.getText() == null || TitreField.getText().length() == 0) {
            errorMessage += "Champ titre vide!\n";
        }
        if (AuteurField.getText() == null || AuteurField.getText().length() == 0) {
            errorMessage += "Champ auteur vide!\n";
        }
        if (PresentationField.getText() == null || PresentationField.getText().length() == 0) {
            errorMessage += "Champ presentation vide!\n";
        }
        if (ParutionField.getText() == null || ParutionField.getText().length() == 0) {
            errorMessage += "Champ parution vide!\n";
        }
        if (ColonneField.getText() == null || ColonneField.getText().length() == 0) {
            errorMessage += "Champ colonne vide!\n";
        }
        if (RangeeField.getText() == null || RangeeField.getText().length() == 0) {
            errorMessage += "Champ rangée vide!\n";
        }
        if (resumeeField.getText() == null || resumeeField.getText().length() == 0){
            errorMessage += "Champ résumé vide!\n";
        }
        Scanner scannerParution = new Scanner(ParutionField.getText());
        try {
            Integer.parseInt(scannerParution.next());
        }
        catch (Exception e){
            errorMessage += "invalide parution!\n";
        }
        Scanner scannerColonne = new Scanner(ColonneField.getText());
        try {
            Short.parseShort(scannerColonne.next());
        }
        catch (Exception e){
            errorMessage += "invalide colonne!\n";
        }
        Scanner scannerRangee = new Scanner(RangeeField.getText());
        try {
            Short.parseShort(scannerRangee.next());
        }
        catch (Exception e){
            errorMessage += "invalide rangée!\n";
        }
        ArrayList<Livre> lesLivresVerification = (ArrayList<Livre>) bibliotheque.getLivreList();
        for(Livre livre:lesLivresVerification){
            if(livre.colonne == Short.parseShort(ColonneField.getText())){
                if(livre.rangee == Short.parseShort(RangeeField.getText())){
                    errorMessage += "invalide chaque livre à une unique place!\n";
                }
            }
        }
        if(etatField.getSelectionModel().getSelectedIndex() == 0 || etatField.getSelectionModel().getSelectedIndex() != 1){

        } else if (etatField.getSelectionModel().getSelectedIndex() != 0 || etatField.getSelectionModel().getSelectedIndex() == 1){

        } else {
            errorMessage += "invalide état!\n";
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

    @FXML void aboutinfos(ActionEvent event) throws IOException {
    }

    @FXML void closeButtonAction() {
        System.exit(0);
    }

    @FXML void editionsauvegarder(ActionEvent event) {    }

    @FXML void editionsauvegardersous(ActionEvent event) {
        jaxbObjectToXML(bibliotheque);
    }

    /*
    Evenement quand on clique sur l'onglet fichier > ouvrir, il permet de rechercher un XML potentiel différent pour le charger
    */
    @FXML void fichierouvrir(ActionEvent event) {
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
    @FXML void handleDeleteLivre(ActionEvent event) {
        int selectedIndex = TableView.getSelectionModel().getSelectedIndex();
        Livre selectedLivre = TableView.getSelectionModel().getSelectedItem();

        if (selectedIndex >= 0) {
            //Retire de l'élément tableView, de la classe bibliothèque et dans la BDD
            TableView.getItems().remove(selectedIndex);
            bibliotheque.supprLivre(selectedLivre);

            String insertFields ="DELETE FROM Livre WHERE ";
            String insertValues = "colonne = " + selectedLivre.colonne +" AND rangee = "+ selectedLivre.rangee;
            String insertToRegister = insertFields + insertValues;

            try{
                Statement statement =connectDB.createStatement();
                statement.executeLargeUpdate(insertToRegister);

            }catch (Exception e){
                e.printStackTrace();
                e.getCause();
            }
        } else {
            // Nothing selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune sélection");
            alert.setHeaderText("Aucun livre sélectionné");
            alert.setContentText("Merci de choisir un livre dans le tableau.");

            alert.showAndWait();
        }
    }
    /*
Fonction qui lance une nouvelle fenêtre pour s'authentifier et passer à la version sur BDD
 */
    @FXML void deconnexion(ActionEvent event) throws IOException {
        /*
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/resources/Apply.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Bibliothèque - M1 M2I ESIEE IT - Authentification");
        stage.setScene(scene);
        stage.show();
        */
    }

    /*
      Evenement déclenché au moment du clic sur le bouton modifier :
       - vérificaion qu'une ligne est été sélectionnée
       - suppression du livre dans la list bibliotheque et de la listView
       - Remplissage des informations du livre supprimer dans les champs de la fiche livre
      */
    @FXML void handleModifLivre(ActionEvent event) {
        Livre selectedLivre = TableView.getSelectionModel().getSelectedItem();
        int selectedIndex = TableView.getSelectionModel().getSelectedIndex();

        if (selectedLivre != null) {
            bibliotheque.supprLivre(selectedLivre);
            TableView.getItems().remove(selectedIndex);
            TitreField.setText(selectedLivre.titre);
            AuteurField.setText(selectedLivre.auteur);
            PresentationField.setText(selectedLivre.presentation);

            int parution = selectedLivre.parution;
            ParutionField.setText(String.valueOf(parution));

            short colonne = selectedLivre.colonne;
            ColonneField.setText(String.valueOf(colonne));

            short rangee = selectedLivre.rangee;
            RangeeField.setText(String.valueOf(rangee));

            if(selectedLivre.etat == "prété"){
                etatField.getSelectionModel().select(0);
            } else {
                etatField.getSelectionModel().select(1);
            }
            resumeeField.setText(selectedLivre.resumer);
        }
    }

    /*
    Evenement déclenché au moment du clic sur le bouton vérification :
    - Récupération de l'URL saisi dans le champ presentation
    - Affichage dans une autre fenêtre de l'image pour visualiser
    */
    @FXML void verificationParution(ActionEvent event){
        Stage primaryStage = new Stage();
        String imageURL = PresentationField.getText();
        Image image = new Image(imageURL);
        ImageView imageViewParution = new ImageView(image);
        Pane root = new Pane();
        root.getChildren().setAll(imageViewParution);
        Scene scene = new Scene(root, 350, 300);
        primaryStage.setTitle("Visualisation d'image");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /*
    Evenement déclenché au moment du clic sur le menu de l'onglet fichier :
        - ressort un fichier word/pdf des données
     */
    @FXML void extractWord(ActionEvent event) throws IOException {
        ArrayList<Livre> lesLivres = (ArrayList<Livre>) bibliotheque.getLivreList();
        File wordfile = new File("bibliothequeWord.docx");
        try (XWPFDocument doc = new  XWPFDocument()) {

            // create a paragraph
            XWPFParagraph p1 = doc.createParagraph();
            XWPFParagraph documentControlHeading = doc.createParagraph();

            documentControlHeading.setAlignment(ParagraphAlignment.LEFT);
            documentControlHeading.setPageBreak(true);



            doc.createTOC();
            p1.setAlignment(ParagraphAlignment.CENTER);

            // set font
            XWPFRun r1 = p1.createRun();
            r1.setBold(true);
            r1.setItalic(true);
            r1.setFontSize(22);
            r1.setFontFamily("New Roman");
            r1.setText("Bibliothèque du groupe JCP.");
            XWPFTable table = doc.createTable();


            //Creating first Row
            XWPFTableRow row01 = table.getRow(0);
            row01.getCell(0).setText("Nom livre");
            row01.addNewTableCell().setText("Nom et prénom de l'auteur");
            row01.addNewTableCell().setText("Presentation");
            row01.addNewTableCell().setText("Parution");
            row01.addNewTableCell().setText("Colonne");
            row01.addNewTableCell().setText("Rangee");
            row01.addNewTableCell().setText("Etat");


            for(Livre livre:lesLivres)
            {
                XWPFTableRow row0 = table.createRow();
                row0.getCell(0).setText(livre.titre);
                row0.getCell(1).setText(livre.auteur);
                row0.getCell(2).setText(livre.presentation);
                String parution= String.valueOf(livre.parution);
                row0.getCell(3).setText(parution);
                String colonne= String.valueOf(livre.colonne);
                row0.getCell(4).setText(colonne);
                String rangee= String.valueOf(livre.rangee);
                row0.getCell(5).setText(rangee);
                String etat= String.valueOf(livre.etat);
                row0.getCell(6).setText(etat);
            }

            try (FileOutputStream out = new FileOutputStream(wordfile)) {
                doc.write(out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        File wordfileFinal = new File("bibliothequeWord.docx");
        Desktop dsk = Desktop.getDesktop();
        dsk.open(wordfileFinal);
    }

    /*
    Fonction lier a JAXB permettant d'éditer un fichier XML avec la classe Bibliothèque
     */
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

    /*
    Fonction invoquer au début du lancement de la classe qui récupère
    les informations du XML présent dans le projet.
    initialise les éléments visuels et ajout les données dans les classes Bibliothèque et Livre
    */
    public void initialize (URL url, ResourceBundle rb){
        startTableViewBDD();
        etatField.getItems().addAll(
                "prété",
                "disponible"
        );
        // Initialiser les colonnes
        titre.setCellValueFactory(new PropertyValueFactory<Livre,String>("titre"));
        auteur.setCellValueFactory(new PropertyValueFactory<Livre,String>("auteur"));
        presentation.setCellValueFactory(new PropertyValueFactory<Livre,String>("presentation"));
        parution.setCellValueFactory(new PropertyValueFactory<Livre,Integer>("parution"));
        colonne.setCellValueFactory(new PropertyValueFactory<Livre,Short>("colonne"));
        rangee.setCellValueFactory(new PropertyValueFactory<Livre,Short>("rangee"));
        resumer.setCellValueFactory(new PropertyValueFactory<Livre,String>("resumer"));
        etat.setCellValueFactory(new PropertyValueFactory<Livre,String>("etat"));

        ObservableList<Livre> list = FXCollections.observableArrayList(bibliotheque.getLivreList());
        TableView.setItems(list);

        //partie de la fonction qui ce déclenche au moment de cliquer sur le bouton Ajouter
        btnValider.setOnMouseClicked(btnAction -> {
            if (isInputValid()){
                String etat;
                if(etatField.getSelectionModel().getSelectedIndex() == 0){
                    etat = "prété";
                } else {
                    etat = "disponible";
                }
                Livre monLivre = new Livre(TitreField.getText(), AuteurField.getText(), PresentationField.getText(), Integer.parseInt(ParutionField.getText()), Short.parseShort(ColonneField.getText()), Short.parseShort(RangeeField.getText()), etat, resumeeField.getText());
                bibliotheque.addLivre(monLivre);
                TableView.getItems().add(monLivre);

                String insertFields ="INSERT INTO Livre (titre, auteur, presentation, parution, colonne, rangee, resumer, etat) VALUES ('";
                String insertValues = monLivre.titre +"','"+ monLivre.auteur +"','"+ monLivre.presentation + "'," + monLivre.parution+ "," + monLivre.colonne +","+ monLivre.rangee + ",'"+ monLivre.resumer + "','"+ monLivre.etat + "')";
                String insertToRegister = insertFields + insertValues;

                try{
                    Statement statement =connectDB.createStatement();
                    statement.executeLargeUpdate(insertToRegister);

                }catch (Exception e){
                    e.printStackTrace();
                    e.getCause();
                }
            }
        });

    }
    /*
    Fonction qui va récupérer les données de la bdd pour le charger en tant qu'un objet
    bibliothèque (déjà initialiser au début de la classe) si la BDD fonctionne pas il prendra les données du XML
     */
    private void startTableViewBDD(){
        String selectFields ="SELECT titre, auteur, presentation, parution, colonne, rangee, etat, resumer FROM livre ";
        String selectToRegister = selectFields;
        try{
            PreparedStatement statement =connectDB.prepareStatement(selectToRegister);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                String titre = result.getString("titre");
                String auteur = result.getString("auteur");
                String presentation = result.getString("presentation");
                int parution = result.getInt("parution");
                short colonne = result.getShort("colonne");
                short rangee = result.getShort("rangee");
                String etat = result.getString("etat");
                String resumer = result.getString("resumer");
                Livre livre =  new Livre(titre,auteur,presentation,parution, colonne, rangee, etat, resumer);
                bibliotheque.addLivre(livre);
            }
            JFrame jFrame = new JFrame();
            JOptionPane.showMessageDialog(jFrame, "Connection à la base de donnée");
        }catch (Exception e){
            try {
                File file = new File("Bibliotheque.xml");
                JAXBContext context = JAXBContext.newInstance(Bibliotheque.class);
                Unmarshaller mapperXMLObjet = context.createUnmarshaller();
                bibliotheque = (Bibliotheque) mapperXMLObjet.unmarshal(file);
                JFrame jFrame = new JFrame();
                JOptionPane.showMessageDialog(jFrame, "Utilisation du XML");
            } catch(JAXBException d){
                d.printStackTrace();
            }
        }
    }
}