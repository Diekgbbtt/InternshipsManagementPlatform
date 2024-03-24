package com.gestionetirocinio.Modello;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TirocinioInterno {

    @Id
    public int Matricola;


    public String NomeRelatore;

    public String EmailRelatore;

    public String TitoloElaborato;

    public String DescrizioneElaborato;

    public Integer NumSettimane;

    public StatoTirocinio Stato;

}