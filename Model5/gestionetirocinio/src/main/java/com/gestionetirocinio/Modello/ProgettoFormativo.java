package com.gestionetirocinio.Modello;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
// @IdClass(CustomKey.class)
public class ProgettoFormativo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

/*     @ManyToOne
    @JoinColumn(name = "azienda", referencedColumnName = "NomeAzienda") */
    // private Azienda azienda;

    public String NomeAzienda;

    public String NomeTutor;

    public String Ruolo;

    public String Attività;

    public String Modalità;

    public String ObiettiviFormativi;

    public Integer DurataMinima;

    public String Luogo;

    public Integer Compenso;

    public String Requisiti;


}

