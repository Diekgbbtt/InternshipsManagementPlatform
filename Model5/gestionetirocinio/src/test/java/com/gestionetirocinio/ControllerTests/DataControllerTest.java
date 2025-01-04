package com.gestionetirocinio.ControllerTests;

import org.junit.jupiter.api.Test;
import org.mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.booleanThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockRequestDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.hamcrest.CoreMatchers;
import org.hibernate.mapping.List;

import com.gestionetirocinio.Controller.DataController;
import com.gestionetirocinio.Modello.ElaboratoFinale;
import com.gestionetirocinio.Modello.Studente;
import com.gestionetirocinio.Modello.TirocinioEsterno;
import com.gestionetirocinio.Client.Service_CommissioneTesi;
import com.gestionetirocinio.Client.Service_DipendenteAzienda;
import com.gestionetirocinio.Client.Service_Docente;
import com.gestionetirocinio.Client.Service_Studente;
import com.gestionetirocinio.Client.Service_StudenteIdoneo;

@SpringBootTest
// @WebMvcTest(DataController.class)
@AutoConfigureMockMvc
public class DataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private Service_StudenteIdoneo servizi_stIdoneo;
    // private Service_StudenteIdoneo Servizi_stIdoneo = mock(Service_StudenteIdoneo.class);
    @Mock
    private Service_Studente servizi_studente;
    @Mock
    private Service_CommissioneTesi servizi_commissione;
    @Mock
    private Service_DipendenteAzienda servizi_DA;
    @Mock
    private Service_Docente servizi_docente;

    private ObjectMapper obj_mapper;

    MultipartFile file = new MockMultipartFile("file", 
    "test", 
        "application/json", 
                    new byte[] { (byte) 1024*4 });

    String efTest_content = "Questo è il test del contenuto dell'elaborato finale";
    MultipartFile ef_test = new MockMultipartFile("file", 
                    "test", 
                        "application/json", 
                                    efTest_content.getBytes());
    
    

    @Test
    public void GetIdoneita_tests_returnBool() {
        when(servizi_studente.VerificaIdoneita(ArgumentMatchers.anyInt())).thenReturn(ArgumentMatchers.anyBoolean());
        try {
        ResultActions response = mockMvc.perform(get("/Idoneita/908760"));

        response.andExpect(MockMvcResultMatchers.status().isOk()) 
                .andExpect(MockMvcResultMatchers.content().contentType("Boolean"));

        } catch(Exception e) {
            System.err.println("Error occurred " + e.getMessage());
        }
    }

    @Test
    public void postTirocinio_tests_returnTirocinio() {

        TirocinioEsterno Tirocinio_obj = TirocinioEsterno.builder()
                                            .matricola(123456)
                                            .nome_azienda("MJ INC")
                                            .SedeAzienda("Fiumicino")
                                            .NomeTutor("Fabio")
                                            .RuoloTutor("PM")
                                            .build();
        when(servizi_stIdoneo.SetTirocinioEsterno(Tirocinio_obj)).thenReturn(Tirocinio_obj);

        try {
            ResultActions response = mockMvc.perform(post("/NuovaRichiestaTirocinio")
                                                        .contentType(MediaType.APPLICATION_JSON)
                                                        .content(obj_mapper.writeValueAsString(Tirocinio_obj)));

            response.andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.matricola", CoreMatchers.is(Tirocinio_obj.getMatricola())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.nome_azienda", CoreMatchers.is(Tirocinio_obj.getNome_azienda())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.SedeAzienda", CoreMatchers.is(Tirocinio_obj.getSedeAzienda())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.NomeTutor", CoreMatchers.is(Tirocinio_obj.getNomeTutor())));
        
        } catch(Exception e) {
                System.err.println("Error occured "+ e.getMessage());

        }

    }

    @Test
    public void getRichiesta_tests_returnTirocinio() {

        Set<String> possibili_stati = new HashSet<>(Arrays.asList("Richiesto", "Attivo", "Concluso"));

        when(servizi_stIdoneo.GetStatoRichiesta(ArgumentMatchers.anyInt())).thenReturn(ArgumentMatchers.matches("(Richiesto|Attivo|Conlcuso)"));
        try {
            ResultActions response = mockMvc.perform(get("ControlloRichiestaTirocinio/123456"));
            response.andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.TEXT_PLAIN));
                    // .andExpect(MockMvcResultMatchers.content().string(CoreMatchers.allOf(possibili_stati)));
                    
        } catch(Exception e) {
            System.err.println("Error occured "+ e.getMessage());
        }

    }

    @Test
    public void postDate_test_returnTirocinio() {
        TirocinioEsterno Tirocinio_obj = TirocinioEsterno.builder()
                            .matricola(123456)
                            .nome_azienda("MJ INC")
                            .SedeAzienda("Fiumicino")
                            .NomeTutor("Fabio")
                            .RuoloTutor("PM")
                            .DataInizio("18/03/2024")
                            .DataFine("31/07/2024")
                            .build();
        
        when(servizi_stIdoneo.UpdateDate(Tirocinio_obj.getMatricola(), Tirocinio_obj.getDataInizio(), Tirocinio_obj.getDataFine())).thenReturn(Tirocinio_obj);
        try {
        ResultActions response = mockMvc.perform(post("AddDateTirocinio/123456")
                                                .param("data_inizio", Tirocinio_obj.getDataInizio())
                                                .param("data_fine", Tirocinio_obj.getDataFine()));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(obj_mapper.writeValueAsString(Tirocinio_obj))));
        } catch(Exception e) {
            System.err.println("Error occurred "+ e.getMessage());
        }

    }

    @Test
    public void postConclusione_test_returnTirocinio() {

        TirocinioEsterno Tirocinio_obj = TirocinioEsterno.builder()
        .matricola(123456)
        .nome_azienda("MJ INC")
        .SedeAzienda("Fiumicino")
        .NomeTutor("Fabio")
        .RuoloTutor("PM")
        .tirocinio_st("Concluso")
        .build();

        try {
        when(servizi_stIdoneo.SetStatoConcluso(123456)).thenReturn(Tirocinio_obj);
        
        ResultActions response = mockMvc.perform(post("/ConcludiTirocinio/123456"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.tirocinio_st", CoreMatchers.is(Tirocinio_obj.getTirocinio_st())));
        } catch(Exception e) {
            System.err.println("Error occurred " + e.getMessage());
        }
    }

    @Test
    public void postRa_test_returnResponseEntity() {

        try {
        when(servizi_stIdoneo.SetRiassuntoAttivita(file, 123456)).thenReturn("file " + file.getOriginalFilename() + " caricato con successo");
        ResultActions response = mockMvc.perform(post("/CaricaRiassuntoAttivita/123456")
                                                    .param(obj_mapper.writeValueAsString(file)));
                    response.andExpect(MockMvcResultMatchers.status().isOk())
                            .andExpect(MockMvcResultMatchers.content().string(CoreMatchers.is("file " + file.getOriginalFilename() + " caricato con successo")));
        } catch(Exception e) {
            System.err.println("Error occurred "+ e.getMessage());
        }

    }

    /* @Test
    public void postEf_test_returnResponseEntity() {

        
    }
*/
    @Test
    public void postValutazione_test_returnTirocinio() {

        TirocinioEsterno Tirocinio_obj = TirocinioEsterno.builder()
        .matricola(123456)
        .nome_azienda("MJ INC")
        .SedeAzienda("Fiumicino")
        .NomeTutor("Fabio")
        .RuoloTutor("PM")
        .tirocinio_st("Approvato")
        .build();


        when(servizi_commissione.UpdateRichiestaTirocinio(Tirocinio_obj.getMatricola())).thenReturn(Tirocinio_obj);
        try {
        ResultActions response = mockMvc.perform(post("ValutareRichiesta/123456"));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.tirocinio_st", CoreMatchers.is(Tirocinio_obj.getTirocinio_st())));
        } catch(Exception e) {
        System.err.println("Error occurred "+ e.getMessage());
        }
    }

    @Test
    public void getEf_test_returnEf() {
        try {
        ElaboratoFinale ef = ElaboratoFinale.builder()
                                    .matricola(123456)
                                    .NomeFile(ef_test.getOriginalFilename())
                                    .type(ef_test.getContentType())
                                    .FileData(ef_test.getBytes())
                                    .build();
        
        when(servizi_commissione.GetEF(ArgumentMatchers.anyInt())).thenReturn(ef.getFileData());
            

        ResultActions response = mockMvc.perform(get("/ScaricaElaboratoFinale/123456"));


            response.andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().string(CoreMatchers.is(efTest_content)));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }        

    }

    @Test
    public void postCFU_tests_returnStudente() {

        Studente stu_obj = Studente.builder()
                                    .matricola(123456)
                                    .StatoCarriera("Attiva")
                                    .CFU(140)
                                    .build();

        try {
        when(servizi_commissione.AddCFU(123456)).thenReturn(stu_obj);

        ResultActions response = mockMvc.perform(post("/VerbalizzareTirocinio/123456"));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.CFU", CoreMatchers.is("140")));
        } catch (Exception e) {
            System.err.println("Error occurred "+ e.getMessage());
        }
    }
/*    
    @Test
    public void postAzienda_tests_returnAzienda() {

    }

    @Test
    public void postPf_tests_Pf() {

    }

    @Test
    public void postRt_tests_Rt(){

    }

    @Test
    public void getPf_tests_Pf() {

    }

    @Test
    public void postRisposta_tests_Tirocinio() {


    }

    @Test
    public void getRt_tests_Rt() {

    }

    @Test
    public void getRa_tests_Ra() {

    }

    @Test
    public void postConvalida_tests_Tirocinio() {

    }
    */
    }
        











    // @Test
    // public void testAddRichiestaTirocinio_ValidInput() {
    //     // Arrange
    //     TirocinioEsterno tirocinioEsterno = new TirocinioEsterno();
    //     // Set valid input values for tirocinioEsterno

    //     DataController dataController = new DataController();
    //     Service_StudenteIdoneo mockServizi = Mockito.mock(Service_StudenteIdoneo.class);
    //     dataController.setServizi_studente_idoneo(mockServizi);

    //     TirocinioEsterno expectedResult = new TirocinioEsterno();
    //     // Set expected result values

    //     Mockito.when(mockServizi.SetTirocinioEsterno(tirocinioEsterno)).thenReturn(expectedResult);

    //     // Act
    //     TirocinioEsterno result = dataController.AddRichiestaTirocinio(tirocinioEsterno);

    //     // Assert
    //     assertEquals(expectedResult, result);
    //     Mockito.verify(mockServizi, Mockito.times(1)).SetTirocinioEsterno(tirocinioEsterno);
    // }

    // @Test
    // public void testAddRichiestaTirocinio_NullInput() {
    //     // Arrange
    //     DataController dataController = new DataController();
    //     Service_StudenteIdoneo mockServizi = Mockito.mock(Service_StudenteIdoneo.class);
    //     dataController.setServizi_studente_idoneo(mockServizi);

    //     // Act
    //     TirocinioEsterno result = dataController.AddRichiestaTirocinio(null);

    //     // Assert
    //     assertNull(result);
    //     Mockito.verify(mockServizi, Mockito.never()).SetTirocinioEsterno(Mockito.any());
    // }
