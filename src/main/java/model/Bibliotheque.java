package main.java.model;

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

    /*
    Renvoie un objet livre existant dans la liste
     */
    public List<Livre> getLivreList() {
        return this.livres;
    }

    /*
    Ajout un objet livre dans la liste
    en Paramètre un objet Livre
     */
    public void addLivre(Livre newLivre) {
        livres.add(newLivre);
    }

    public void updateLivre(Livre lastLivre, Livre newLivre) {
        livres.remove(lastLivre);
        livres.add(newLivre);
    }

    /*
    Supprime le livre en paramètre de la liste
     */
    public void supprLivre(Livre currentLivre) {
        livres.remove(currentLivre);
    }

    public void clear() {
        livres.clear();
    }

}