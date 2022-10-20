/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;


public class Assurance {
   private int id;
   private Date Datesoin ;
   private int IdentifiantAssure;
   private String Nom;
   private String Prenom;
   private String Qualite;
   private String CodeApci ;
   private String CodeAct;
   private int Cotation; 
   private int MontantHoraire;
   private int Ticket ;
   private int MontantCharge ;
   private String CodeConventionnel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatesoin() {
        return Datesoin;
    }

    public void setDatesoin(Date Datesoin) {
        this.Datesoin = Datesoin;
    }

    public int getIdentifiantAssure() {
        return IdentifiantAssure;
    }

    public void setIdentifiantAssure(int IdentifiantAssure) {
        this.IdentifiantAssure = IdentifiantAssure;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public String getQualite() {
        return Qualite;
    }

    public void setQualite(String Qualite) {
        this.Qualite = Qualite;
    }

    public String getCodeApci() {
        return CodeApci;
    }

    public void setCodeApci(String CodeApci) {
        this.CodeApci = CodeApci;
    }

    public String getCodeAct() {
        return CodeAct;
    }

    public void setCodeAct(String CodeAct) {
        this.CodeAct = CodeAct;
    }

    public int getCotation() {
        return Cotation;
    }

    public void setCotation(int Cotation) {
        this.Cotation = Cotation;
    }

    public int getMontantHoraire() {
        return MontantHoraire;
    }

    public void setMontantHoraire(int MontantHoraire) {
        this.MontantHoraire = MontantHoraire;
    }

    public int getTicket() {
        return Ticket;
    }

    public void setTicket(int Ticket) {
        this.Ticket = Ticket;
    }

    public int getMontantCharge() {
        return MontantCharge;
    }

    public void setMontantCharge(int MontantCharge) {
        this.MontantCharge = MontantCharge;
    }

    public String getCodeConventionnel() {
        return CodeConventionnel;
    }

    public void setCodeConventionnel(String CodeConventionnel) {
        this.CodeConventionnel = CodeConventionnel;
    }

    public Assurance() {
    }

    public Assurance(int id, Date Datesoin, int IdentifiantAssure, String Nom, String Prenom, String Qualite, String CodeApci, String CodeAct, int Cotation, int MontantHoraire, int Ticket, int MontantCharge, String CodeConventionnel) {
        this.id = id;
        this.Datesoin = Datesoin;
        this.IdentifiantAssure = IdentifiantAssure;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Qualite = Qualite;
        this.CodeApci = CodeApci;
        this.CodeAct = CodeAct;
        this.Cotation = Cotation;
        this.MontantHoraire = MontantHoraire;
        this.Ticket = Ticket;
        this.MontantCharge = MontantCharge;
        this.CodeConventionnel = CodeConventionnel;
    }

    public Assurance(Date Datesoin, int IdentifiantAssure, String Nom, String Prenom, String Qualite, String CodeApci, String CodeAct, int Cotation, int MontantHoraire, int Ticket, int MontantCharge, String CodeConventionnel) {
        this.Datesoin = Datesoin;
        this.IdentifiantAssure = IdentifiantAssure;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Qualite = Qualite;
        this.CodeApci = CodeApci;
        this.CodeAct = CodeAct;
        this.Cotation = Cotation;
        this.MontantHoraire = MontantHoraire;
        this.Ticket = Ticket;
        this.MontantCharge = MontantCharge;
        this.CodeConventionnel = CodeConventionnel;
    }

    public Assurance(Date Datesoin) {
        this.Datesoin = Datesoin;
    }
   
   
   
   
}
