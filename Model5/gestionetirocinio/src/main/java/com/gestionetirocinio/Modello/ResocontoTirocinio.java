package com.gestionetirocinio.Modello;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResocontoTirocinio {


    @Id
    private int matricola;
    
    @OneToOne
    @JoinColumn(name = "matricola", referencedColumnName = "matricola")
    private TirocinioEsterno Tir_esterno;

    
    public String Ruolo;

    public String Attività;

    public Integer Durata;

    public String CompetenzeAcquisite;

    public String Progetto;

    public String Consigli;

}