package com.example.projecthp;

public class ClasaMedici {
    String nume;
    String email;
    String specializare;

    String loc_munca;
    public ClasaMedici(String nume, String email, String specializare, String loc_munca) {
        this.nume = nume;
        this.email = email;
        this.specializare = specializare;
        this.loc_munca = loc_munca;
    }



    public String getNume() {
        return nume;
    }

    public String getEmail() {
        return email;
    }

    public String getSpecializare() {
        return specializare;
    }

    public String getLoc_munca() {
        return loc_munca;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSpecializare(String specializare) {
        this.specializare = specializare;
    }

    public void setLoc_munca(String loc_munca) {
        this.loc_munca = loc_munca;
    }


}
