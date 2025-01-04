package com.gestionetirocinio.Modello;

import java.time.LocalDate;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class TirocinioEsterno { /* extends TirocinioInterno */

    @Id
    public int matricola;

/*     @ManyToOne
    @JoinColumn(name = "azienda", referencedColumnName = "NomeAzienda") */
    // private Azienda azienda;

/*     public String NomeAzienda; */

    private String nome_azienda;

    public String SedeAzienda;

    public Integer TelefonoAziendale;

    public String NomeTutor;

    public String RuoloTutor;

    private Boolean pf_approvato;

    private Boolean disponibilità_docente;

    private String tirocinio_st;

    private String DataInizio;

    private String DataFine;

    private Boolean convalidato;
/* 
    @OneToOne(mappedBy = "Tir_esterno") */
    // RiassuntoAttivita riass_Attività;
/* 
    @OneToOne(mappedBy = "Tir_esterno") */
    // ResocontoTirocinio Res_tirocinio;

/*     @OneToOne(mappedBy = "Tir_esterno") */
    // ElaboratoFinale el_finale;

}