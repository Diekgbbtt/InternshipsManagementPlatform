package com.gestionetirocinio.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gestionetirocinio.Client.Service_CommissioneTesi;
import com.gestionetirocinio.Client.Service_Studente;
import com.gestionetirocinio.Client.Service_StudenteIdoneo;
/* import com.gestionetirocinio.Modello.StatoTirocinio; */
import com.gestionetirocinio.Modello.TirocinioEsterno;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

@RestController
public class DataController {

    @Autowired
    private Service_Studente servizi_studente;
    
    @Autowired
    private Service_CommissioneTesi servizi_commissione;

    @Autowired
    private Service_StudenteIdoneo servizi_studente_idoneo;


    @GetMapping("/Idoneita/{id}")
    public boolean CheckIdoneita(@PathVariable int id) {
        return servizi_studente.VerificaIdoneita(id);
    }

    @PostMapping("/NuovaRichiestaTirocinio")
    public TirocinioEsterno AddRichiestaTirocinio(@RequestBody TirocinioEsterno tir_Esterno) {
        return servizi_studente_idoneo.SetTirocinioEsterno(tir_Esterno);
    }

    @GetMapping("/ControlloRichiestaTirocinio/{id}")
    public String CheckStatoRichiesta(@PathVariable int id){
        return servizi_studente_idoneo.GetStatoTirocinio(id);
    }
    
    @PostMapping("/DateTirocinio")
    public TirocinioEsterno AddDateTirocinio(@RequestBody TirocinioEsterno tir_Esterno){
        return servizi_studente_idoneo.UpdateDate(tir_Esterno);
    }

    @PostMapping("/ConcludiTirocinio")
    public TirocinioEsterno ConcludiTirocinio(@RequestBody int id){
        return servizi_studente_idoneo.SetStatoConcluso(id);
    }



}
