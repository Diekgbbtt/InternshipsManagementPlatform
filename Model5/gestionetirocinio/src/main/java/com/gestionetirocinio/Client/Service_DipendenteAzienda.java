package com.gestionetirocinio.Client;

import java.io.IOException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gestionetirocinio.DAO.dao_azienda;
import com.gestionetirocinio.DAO.dao_pf;
import com.gestionetirocinio.DAO.dao_rt;
import com.gestionetirocinio.Modello.Azienda;
import com.gestionetirocinio.Modello.ElaboratoFinale;
import com.gestionetirocinio.Modello.ProgettoFormativo;
import com.gestionetirocinio.Modello.ResocontoTirocinio;
import com.gestionetirocinio.Utils.FileUtils;


@Service
public class Service_DipendenteAzienda {

    @Autowired
    private dao_azienda azienda_tbl;

    @Autowired
    private dao_rt rt_tbl;

    @Autowired
    private dao_pf pf_tbl;

    
    private ResocontoTirocinio rt_obj;

    public String Nome;

    public String Email;

    public int Telefono;

    
    public Azienda SetAzienda(Azienda Azienda_obj){

        List<Azienda> AziendeRegistrate = azienda_tbl.findAll();

        for(int i = 0; i < AziendeRegistrate.size(); i++) {
            if(((AziendeRegistrate.get(i)).getNomeAzienda()).equals(Azienda_obj.getNomeAzienda())) {
                System.err.println("Azienda già registrata");
                return null;
            }
        }
        return azienda_tbl.save(Azienda_obj);
    }


    public ProgettoFormativo SetPF(ProgettoFormativo pf_obj){
        return pf_tbl.save(pf_obj);
    }

    public String SetResocontoTirocinio(MultipartFile file, int id) throws IOException {
        rt_obj = rt_tbl.save(ResocontoTirocinio.builder()
                    .matricola(id)
                    .NomeFile(file.getOriginalFilename())
                    .type(file.getContentType())
                    .FileData(FileUtils.compressImage(file.getBytes())).build());
        if(rt_obj != null){
            return "file "+ file.getOriginalFilename() + "caricato con successo";
        } else return null;
    }


}
/*     protected ResocontoTirocinio SetResocontoTirocinio(String NomeStudente) {
        // TODO implement here
        ResocontoTirocinio rt = new ResocontoTirocinio();
        return rt;
    } */

    /**
     * @param azienda 
     * @return
     */
/*     private boolean RegistrareAzienda(Azienda azienda) {
        // TODO implement here
        return false;
    }
 */
    /**
     * @return
     */
/*     private ProgettoFormativo NuovoProgettoFormativo() {
        // TODO implement here

        ProgettoFormativo pf = new ProgettoFormativo();
        return pf;
    } */

    /**
     * @param matricola 
     * @return
     */
/*     private ResocontoTirocinio NuovoResocontoTirocinio(int matricola) {
        ResocontoTirocinio rt = new ResocontoTirocinio();
        return rt;
    } */
