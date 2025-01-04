package com.gestionetirocinio.Client;

import java.util.*;

import org.checkerframework.checker.units.qual.radians;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionetirocinio.DAO.dao_Elaborato;
import com.gestionetirocinio.DAO.dao_Riassunto;
import com.gestionetirocinio.DAO.dao_Tirocinio;
import com.gestionetirocinio.DAO.dao_pf;
import com.gestionetirocinio.DAO.dao_rt;
import com.gestionetirocinio.Modello.ProgettoFormativo;
import com.gestionetirocinio.Modello.ResocontoTirocinio;
import com.gestionetirocinio.Modello.RiassuntoAttivita;
import com.gestionetirocinio.Modello.TirocinioEsterno;
import com.gestionetirocinio.Utils.FileUtils;

import lombok.Data;


@Data
@Service
public class Service_Docente {


    public String Nome;

    public String Email;

    private boolean Disponibilità;

    private int NumRichieste;

/*     List<Integer> MatricoleRichieste_ = new ArrayList<Integer>(); */


    @Autowired
    private dao_pf pf_tbl;

    @Autowired
    private dao_Tirocinio tbl_tirocinio;

    @Autowired
    private dao_Riassunto tbl_riassunto;

    @Autowired
    private dao_rt rt_tbl;


    public TirocinioEsterno GetTirocinio(int id){
  /*       for(int matricola : MatricoleRichieste) {
            if(matricola==id){ */
                return tbl_tirocinio.findById(id).orElse(null);
    /*         }
        }
        System.err.println("Matricola Errata");
        return null; */
    }

    public ProgettoFormativo GetPF(int id){
        List<ProgettoFormativo> ProgettiFormativi = pf_tbl.findAll();
        TirocinioEsterno TirocinioEst_obj = GetTirocinio(id);

        for(int i = 0; i < ProgettiFormativi.size(); i++) {
            if((TirocinioEst_obj.getNome_azienda()).equals((ProgettiFormativi.get(i)).getNomeAzienda())) {
                if((TirocinioEst_obj.getNomeTutor()).equals((ProgettiFormativi.get(i)).getNomeTutor())) {
                    return ProgettiFormativi.get(i);
                }
            }
        }
        System.err.println("Progetto Formativo non caricato");
        return null;
    }

    public TirocinioEsterno SetRispostaRichiesta(int id, boolean Disponibilita, boolean Approvazionepf){
        TirocinioEsterno TirocinioEst_obj = tbl_tirocinio.findById(id).orElse(null);
        TirocinioEst_obj.setDisponibilità_docente(Disponibilita);
        TirocinioEst_obj.setPf_approvato(Approvazionepf);
        return tbl_tirocinio.save(TirocinioEst_obj);
    }

    public byte[] GetRA(int id){
        Optional<RiassuntoAttivita> ra_db_data = tbl_riassunto.findById(id);
        byte[] ra_data = FileUtils.decompressImage(ra_db_data.get().getFileData());
        return ra_data;
    }

    public byte[] GetRT(int id){
        Optional<ResocontoTirocinio> rt_db_data = rt_tbl.findById(id);
        byte[] rt_data = FileUtils.decompressImage(rt_db_data.get().getFileData());
        return rt_data;
    }

    public TirocinioEsterno SetConvalida(int id){
        TirocinioEsterno TirocinioEst_obj = tbl_tirocinio.findById(id).orElse(null);
        TirocinioEst_obj.setConvalidato(true);
        return tbl_tirocinio.save(TirocinioEst_obj);
    }







    





    /**
     * @return
     */
/*     public boolean GetDisponibilità() {
        // TODO implement here
        return false;
    } */

    /**
     * @return
     */
/*     private boolean RispondereRichiesta() {
        // TODO implement here
        return false;
    } */

    /**
     * @param matricola 
     * @return
     */
/*     private boolean ConvalidareTirocinio(int matricola) {
        // TODO implement here
        return false;
    } */

    /**
     * @param NumRichieste 
     * @return
     */
/*     public boolean NuovaRichiesta(int NumRichieste) {
        // TODO implement here
        return false;
    } */

}