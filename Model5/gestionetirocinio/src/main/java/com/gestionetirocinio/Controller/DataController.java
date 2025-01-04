package com.gestionetirocinio.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gestionetirocinio.Client.Service_CommissioneTesi;
import com.gestionetirocinio.Client.Service_DipendenteAzienda;
import com.gestionetirocinio.Client.Service_Docente;
import com.gestionetirocinio.Client.Service_Studente;
import com.gestionetirocinio.Client.Service_StudenteIdoneo;
import com.gestionetirocinio.Modello.Azienda;
import com.gestionetirocinio.Modello.ProgettoFormativo;
import com.gestionetirocinio.Modello.Studente;
import com.gestionetirocinio.Modello.TirocinioEsterno;

import lombok.Data;

@Data
@RestController
public class DataController {

    @Autowired
    private Service_Studente servizi_studente;
    
    @Autowired
    private Service_CommissioneTesi servizi_commissione;

    @Autowired
    private Service_StudenteIdoneo servizi_studente_idoneo;

    @Autowired
    private Service_DipendenteAzienda Servizi_azienda;

    @Autowired
    private Service_Docente servizi_docente;

 /* Richieste studente */ 
 
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
        return servizi_studente_idoneo.GetStatoRichiesta(id);
    }
    
    @PostMapping("/AddDateTirocinio/{id}")
    public TirocinioEsterno AddDateTirocinio(@PathVariable int id, @RequestParam("data_inizio") String data_inizio, @RequestParam("data_fine") String data_fine){
        return servizi_studente_idoneo.UpdateDate(id, data_inizio, data_fine);
    }

    @PostMapping("/ConcludiTirocinio/{id}")
    public TirocinioEsterno ConcludiTirocinio(@PathVariable int id){
        return servizi_studente_idoneo.SetStatoConcluso(id);
    }

    @PostMapping("/CaricaRiassuntoAttivita/{id}")
    public ResponseEntity<?> CaricaRiassuntoAttivita(@RequestParam("RiassuntoAttivita")MultipartFile file, @PathVariable int id) throws IOException {
             try { 
                String UploadString = servizi_studente_idoneo.SetRiassuntoAttivita(file, id);
                return ResponseEntity.status(HttpStatus.OK)
                    .body(UploadString);

             } catch(IOException e) {
                System.err.println("An error occurred: " + e.getMessage());
                return null;
             }
        
    }

    @PostMapping("/CaricaElaboratoFinale/{id}")
    public ResponseEntity<?> CaricaElaboratoFinale(@RequestParam("ElaboratoFinale")MultipartFile file, @PathVariable int id) {
        try {
        String UploadString = servizi_studente_idoneo.SetElaboratoFinale(file, id);
		return ResponseEntity.status(HttpStatus.OK)
				.body(UploadString);
        } catch(IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
            return null;
        }
    }

     /* Richieste commissone */ 

     @PostMapping("/ValutareRichiesta/{id}")
     public TirocinioEsterno ValutareRichiesta(@PathVariable int id) {
            return servizi_commissione.UpdateRichiestaTirocinio(id);
     }
     
     @GetMapping("/ControlloStatoTirocinio/{id}")
     public String CheckStatoTirocinio(@PathVariable int id){
         return servizi_commissione.GetStatoTirocinio(id);
     }

     @GetMapping("/ScaricaElaboratoFinale/{id}")
     public ResponseEntity<?> ScaricaElaboratoFinale(@PathVariable int id){
        byte[] FileData = servizi_commissione.GetEF(id);
        return ResponseEntity.status(HttpStatus.OK)
            .contentType(MediaType.valueOf("text/plain"))
            .body(FileData);
     }

     @PostMapping("/VerbalizzareTirocinio/{id}")
        public Studente VerbalizzareTirocinio(@PathVariable int id){
            return servizi_commissione.AddCFU(id);    
        }


     /* Rciheiste Dipendente Azienda */

    @PostMapping("/RegistrareAzienda")
     public Azienda RegistrareAzienda(@RequestBody Azienda Azienda_obj){
        return Servizi_azienda.SetAzienda(Azienda_obj);
     }

     @PostMapping("/NuovoProgettoFormativo")
     public ProgettoFormativo NuovoProgettoFormativo(@RequestBody ProgettoFormativo pf_obj){
        return Servizi_azienda.SetPF(pf_obj);
     }

     @PostMapping("/CaricaResocontoTirocinio/{id}")
     public ResponseEntity<?> CaricaResocontoTirocinio(@RequestParam("ResocontoTirocinio")MultipartFile file, @PathVariable int id) throws IOException {
        try { 
        String UploadString = Servizi_azienda.SetResocontoTirocinio(file, id);
         return ResponseEntity.status(HttpStatus.OK)
                 .body(UploadString);
        } catch(IOException e) {
            System.err.println("Error occurred "+ e.getMessage());
            return null;
        }
     }

     /* Richeiste Docente */

     @GetMapping("/ScaricaProgettoFormativo/{id}")
     public ProgettoFormativo ScaricaProgettoFormativo(@PathVariable int id) {
        return servizi_docente.GetPF(id);
     }

     @PostMapping("/RispondereRichiesta/{id}")
     public TirocinioEsterno RispondereRichiesta(@PathVariable int id, @RequestParam boolean Disponibilita, @RequestParam boolean Approvazionepf){
        return servizi_docente.SetRispostaRichiesta(id,  Disponibilita, Approvazionepf);    
    }

    @GetMapping("/ScaricareResoncontoTirocinio/{id}")
    public ResponseEntity<?> ScaricareResoncontoTirocinio(@PathVariable int id){
        byte[] FileData = servizi_docente.GetRT(id);
        return ResponseEntity.status(HttpStatus.OK)
            .contentType(MediaType.valueOf("text/plain"))
            .body(FileData);
    }

    @GetMapping("/ScaricareRiasssuntoAttivita/{id}")
    public ResponseEntity<?> ScaricareRiasssuntoAttivita(@PathVariable int id){
        byte[] FileData = servizi_docente.GetRA(id);
        return ResponseEntity.status(HttpStatus.OK)
            .contentType(MediaType.valueOf("text/plain"))
            .body(FileData);
    }

     @PostMapping("/ConvalidareTirocinio/{id}")
     public TirocinioEsterno ConvalidareTirocinio(@PathVariable int id){
        return servizi_docente.SetConvalida(id);
     }

}

