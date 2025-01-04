package com.gestionetirocinio.Client;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gestionetirocinio.DAO.dao_Elaborato;
import com.gestionetirocinio.DAO.dao_Riassunto;
import com.gestionetirocinio.DAO.dao_Tirocinio;
import com.gestionetirocinio.Modello.ElaboratoFinale;
import com.gestionetirocinio.Modello.RiassuntoAttivita;
import com.gestionetirocinio.Modello.TirocinioEsterno;
import com.gestionetirocinio.Utils.FileUtils;

@Service
public class Service_StudenteIdoneo extends Service_Studente {

    @Autowired
    private dao_Tirocinio tirocinio_tbl;

    @Autowired
    public TirocinioEsterno TirocinioEst_obj;

    /* @Autowired */
    public RiassuntoAttivita ra_obj;

    /* @Autowired */
    public ElaboratoFinale ef_obj;

    public Service_CommissioneTesi servizi_commissione;

    public Service_Docente servizi_docente;

    @Autowired
    private dao_Riassunto riassunto_tbl;

    @Autowired
    private dao_Elaborato elaborato_tbl;

    /*
     * public StatoTirocinio tirocinio_st;
     */

    public TirocinioEsterno SetTirocinioEsterno(TirocinioEsterno RichiestaTirocinio) {
        if (String.valueOf(RichiestaTirocinio.matricola).length() == 6) {
            if (VerificaIdoneita(RichiestaTirocinio.matricola)) {
                if (null == (tirocinio_tbl.findById(RichiestaTirocinio.matricola).orElse(null))) {
                    System.out.println("Richiesta Tirocinio caricata con successo");
                    /*
                     * servizi_commissione.getMatricoleRichieste().add(RichiestaTirocinio.matricola)
                     * ;
                     * servizi_docente.getMatricoleRichieste().add(RichiestaTirocinio.matricola);
                     */
                    return tirocinio_tbl.save(RichiestaTirocinio);
                } else {
                    System.out.println("Richiesta Tirocinio già presente");
                    return null;
                }
            } else {
                System.out.println("Lo studente non soddisfa i requisiti per effettuare un tirocinio");
                return null;
            }
        } else {
            System.out.println("Matricola non valida");
            return null;
        }
    }

    public String GetStatoRichiesta(int id) {
        final TirocinioEsterno TirocinioEst_obj_1 = new TirocinioEsterno()/* tirocinio_tbl.findById(id).orElse(null) */;
        TirocinioEst_obj = tirocinio_tbl.findById(id).orElse(TirocinioEst_obj_1);
        return TirocinioEst_obj.getTirocinio_st();
    }

    /* @Autowired */
    public TirocinioEsterno UpdateDate(int id, String data_inizio, String data_fine) { // da rivedere passando solo
                                                                                       // datainizio-fine e
                                                                                       // matricola(come amppare questi
                                                                                       // valori nella richiesta)

        if (GetStatoRichiesta(id).equals("Approvato")) {
            TirocinioEst_obj = tirocinio_tbl.findById(id).orElse(null);
            TirocinioEst_obj.setDataInizio(data_inizio);
            TirocinioEst_obj.setDataFine(data_fine);
            System.out.println("Date aggiunte");
            return tirocinio_tbl.save(TirocinioEst_obj);
        } else
            System.out.println("Tirocinio Non Approvato");
        return null;
    }

    public TirocinioEsterno SetStatoConcluso(int id) {
        
        TirocinioEst_obj = tirocinio_tbl.findById(id).orElse(null);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataFine = LocalDate.parse(TirocinioEst_obj.getDataFine(), formatter);
        if(TirocinioEst_obj==null){
            System.out.println("Matricola Errata");
            return null;
        }
        if(LocalDate.now().isBefore(dataFine)) {
            System.out.println("Tirocinio non concluso");
            return TirocinioEst_obj;
        }
        TirocinioEst_obj.setTirocinio_st("Concluso");
        return tirocinio_tbl.save(TirocinioEst_obj);
    }

    public String SetRiassuntoAttivita(MultipartFile file, int id) throws IOException {
        if(tirocinio_tbl.findById(id).orElse(null).getTirocinio_st().equals("Concluso")) {
            ra_obj = riassunto_tbl.save(RiassuntoAttivita.builder()
                    .matricola(id)
                    .NomeFile(file.getOriginalFilename())
                    .type(file.getContentType())
                    .FileData(FileUtils.compressImage(file.getBytes())).build());
            if (ra_obj != null) {
                return "file " + file.getOriginalFilename() + " caricato con successo";
            } else
                return null;
        } else {
            System.err.println("Tirocinio non terminato");
            return null;
        }
    }

    public String SetElaboratoFinale(MultipartFile file, int id) throws IOException {
        if(tirocinio_tbl.findById(id).orElse(null).getConvalidato()) {
            ef_obj = elaborato_tbl.save(ElaboratoFinale.builder()
                    .matricola(id)
                    .NomeFile(file.getOriginalFilename())
                    .type(file.getContentType())
                    .FileData(FileUtils.compressImage(file.getBytes())).build());
            if (ef_obj != null) {
                return "file " + file.getOriginalFilename() + " caricato con successo";
            } else
                return null;
        } else {
            System.err.println("Tirocinio non convalidato");
            return null;
        }
    }

    /*
     * public TirocinioInterno RichiestaTirocinioInterno(int matricola) {
     * 
     * TirocinioInterno Tirocinio_interno = new TirocinioInterno();
     * return Tirocinio_interno;
     * }
     * 
     * /**
     * 
     * @param matricola
     * 
     * @return
     */
    /*
     * public TirocinioEsterno RichiestaTirocinioEsterno(int matricola) {
     * // TODO implement here
     * TirocinioEsterno Tirocinio_esterno = new TirocinioEsterno();
     * return Tirocinio_esterno;
     * }
     */

    /*
     * public String ControlloStatoRichiesta(int matricola) {
     * // TODO implement here
     * return "";
     * }
     */

    /*
     * private boolean ImpostareDataInizioFine(Date DataInizio, Date DataFine) {
     * // TODO implement here
     * return false;
     * }
     */

    /**
    
     */
    /*
     * private boolean ConcludereTirocinio() {
     * // TODO implement here
     * return false;
     */

}