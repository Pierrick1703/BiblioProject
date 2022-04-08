package main.java.model;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "titre", "auteur", "presentation", "parution", "colonne", "rangee", "etat","resumer" })
@XmlRootElement(name = "Livre")

public class Livre {

    @XmlElement(required = true)
    protected String titre;
    @XmlElement(required = true)
    protected String auteur;
    @XmlElement(required = true)
    protected String presentation;
    @XmlSchemaType(name = "unsignedShort")
    protected int parution;
    @XmlSchemaType(name = "unsignedShort")
    protected short rangee;
    @XmlSchemaType(name = "unsignedByte")
    protected short colonne;
    @XmlSchemaType(name = "unsignedByte")
    protected String etat;
    @XmlSchemaType(name = "unsignedByte")
    protected String resumer;


    public Livre(String titre, String auteur, String presentation, int parution, short colonne, short rangee, String etat, String resumer) {
        this.titre = titre;
        this.auteur = auteur;
        this.presentation = presentation;
        this.parution = parution;
        this.colonne = colonne;
        this.rangee = rangee;
        this.resumer = resumer;
        this.etat = etat;
    }

    public Livre(){

    }

    // Getter
    public short getColonne() {
        return colonne;
    }

    public short getRangee() {
        return rangee;
    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public String getPresentation() {
        return presentation;
    }

    public int getParution() {
        return parution;
    }

    public String getEtat() {
        return etat;
    }

    public String getResumer() {
        return resumer;
    }

    // Setter
    public void setColonne(short colonne) {
        this.colonne = colonne;
    }

    public void setRangee(short rangee) {
        this.rangee = rangee;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public void setParution(int parution) {
        this.parution = parution;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setResumer(String resumer) {
        this.resumer = resumer;
    }
}