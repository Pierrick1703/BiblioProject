package main.java.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "livres" })
@XmlRootElement(name = "bibliotheque")


public class Bibliotheque {

    @XmlElement(required = true)
    protected ArrayList<Livre> livres = new ArrayList<Livre>();

    public Bibliotheque() {

    }

    /**
     * Gets the value of the livre property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the
     * livre property.
     *
     * <p>
     * To add a new item, do as follows:
     *
     * <pre>
     * getLivre().add(newItem);
     * </pre>
     *
     * @return this.livres
     */
    public List<Livre> getLivreList() {
        return this.livres;
    }

    public void addLivre(Livre newLivre) {
        livres.add(newLivre);
    }

    /**
     * Doit-on pas plutot changer les proprietes du livre ?
     *
     * @param lastLivre
     * @param newLivre
     */
    public void updateLivre(Livre lastLivre, Livre newLivre) {
        livres.remove(lastLivre);
        livres.add(newLivre);
    }

    public void supprLivre(Livre newLivre) {
        livres.remove(newLivre);
    }

    public void clear() {
        livres.clear();
    }

}