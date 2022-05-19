package com.example.projecthp;
public class ClasaArticole {
    String nume_autor, nume_articol, link;

    public ClasaArticole(String nume_autor, String nume_articol, String link) {
        this.nume_autor = nume_autor;
        this.nume_articol = nume_articol;
        this.link = link;
    }

    public String getNume_autor() {
        return nume_autor;
    }

    public String getNume_articol() {
        return nume_articol;
    }

    public String getLink() {
        return link;
    }

    public void setNume_autor(String nume_autor) {
        this.nume_autor = nume_autor;
    }

    public void setNume_articol(String nume_articol) {
        this.nume_articol = nume_articol;
    }

    public void setLink(String link) {
        this.link = link;
    }
}