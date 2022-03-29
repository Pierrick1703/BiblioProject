package main.java.controllers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "titre", "auteur", "presentation", "parution", "colonne", "rangee" })

public class Livre {

    @XmlElement(required = true)
    protected String titre;
    @XmlElement(required = true)
    protected String auteur;
    @XmlElement(required = true)
    protected String presentation;
    @XmlSchemaType(name = "unsignedShort")
    protected int parution;
    @XmlSchemaType(name = "unsignedByte")
    protected int colonne;
    @XmlSchemaType(name = "unsignedByte")
    protected int rangee;

    public Livre(String titre, String auteur, String presentation, int parution, int colonne, int rangee) {
        this.titre = titre;
        this.auteur = auteur;
        this.presentation = presentation;
        this.parution = parution;
        this.colonne = colonne;
        this.rangee = rangee;
    }

    // Getter
    public int getColonne() {
        return colonne;
    }

    public int getRangee() {
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

    // Setter
    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    public void setRangee(int rangee) {
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
}