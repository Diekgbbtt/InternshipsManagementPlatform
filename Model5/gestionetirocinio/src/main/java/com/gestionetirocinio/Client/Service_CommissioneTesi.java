package com.gestionetirocinio.Client;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gestionetirocinio.Modello.TirocinioEsterno;
import com.gestionetirocinio.Utils.FileUtils;

import lombok.Data;

import com.gestionetirocinio.Modello.Studente;
import com.gestionetirocinio.Modello.ElaboratoFinale;
import com.gestionetirocinio.Modello.ResocontoTirocinio;
/* import com.gestionetirocinio.Modello.StatoTirocinio; */
import com.gestionetirocinio.DAO.dao_Elaborato;
import com.gestionetirocinio.DAO.dao_studente;
import com.gestionetirocinio.DAO.dao_Tirocinio;

// @Data
@Service
public class Service_CommissioneTesi {

    @Autowired
    private dao_Tirocinio tbl_tirocinio;
    
    @Autowired
    private dao_Elaborato tbl_elaborato;

    @Autowired
    private dao_studente tbl_studente;

/*     private Collection<Integer> MatricoleRichieste; */
/*     private StatoTirocinio Approvato;
    private StatoTirocinio Rifiutato; */

    public TirocinioEsterno GetTirocinio(int id){
       /*  for(int matricola : MatricoleRichieste) {
            if(matricola==id){ */
                return tbl_tirocinio.findById(id).orElse(null);
/*             }
        }
        System.err.println("Matricola Errata");
        return null; */
    }

    public TirocinioEsterno UpdateRichiestaTirocinio(int id) {
        TirocinioEsterno RichiestaTirocinioEsterno = GetTirocinio(id);
        if(RichiestaTirocinioEsterno.getPf_approvato() && RichiestaTirocinioEsterno.getDisponibilità_docente()) {
            RichiestaTirocinioEsterno.setTirocinio_st("Approvato");
            System.out.println("Tirocinio Approvato");
        } else { RichiestaTirocinioEsterno.setTirocinio_st("Rifiutato");
            System.out.println("Tirocinio Rifiutato"); }
        return tbl_tirocinio.save(RichiestaTirocinioEsterno);
    } // aggiungere opzionalmente controllo della matricola mandata, creando lista matricoleTirocini doeve si aggiungono amtricole se il tirocinio viene approvato
    
    public String GetStatoTirocinio(int id) {
        TirocinioEsterno TirocinioEsterno = GetTirocinio(id);
        return TirocinioEsterno.getTirocinio_st();
    }

    public byte[] GetEF(int id){
        Optional<ElaboratoFinale> ef_db_data = tbl_elaborato.findById(id);
        byte[] rt_data = FileUtils.decompressImage(ef_db_data.get().getFileData());
        return rt_data;
    }

    public Studente AddCFU(int id){
        TirocinioEsterno TirocinioEsterno = GetTirocinio(id);
        if(TirocinioEsterno.getConvalidato()) {
            Studente Studente_obj = tbl_studente.findById(id).orElse(null);
            Studente_obj.setCFU(Studente_obj.getCFU()+15);
            return tbl_studente.save(Studente_obj);
        } else return null;
    }

}





  /*   /**
     * @param Richiesta 
     * @param Matricola 
     * @return
     */
/*     private boolean ValutareRichiesta(Tirocinio Richiesta, int Matricola) {
        // TODO implement here
        return false;
    } */

/*     private StatoTirocinio ControllareStatoTirocinio(int matricola) {
        // TODO implement here
        
        StatoTirocinio st = StatoTirocinio.Richiesto;

        return st;
    } */


/*     private boolean VerbalizzareTirocinio(int matricola) {
        // TODO implement here
        return false;
    } */



/*     public boolean NuovaRichiesta(int NumRichieste) {
        // TODO implement here
        return false; */
