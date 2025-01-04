package com.gestionetirocinio.DAOTests;

import com.gestionetirocinio.DAO.dao_Elaborato;
import com.gestionetirocinio.Modello.ElaboratoFinale;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;


@SpringBootTest
public class DAO_ef_tests {


    @Autowired
    private dao_Elaborato tbl_ef;

    @Test
    public void save_test_ReturnElaborato() {

        ElaboratoFinale ef_obj = ElaboratoFinale.builder()
                                .matricola(823457)
                                .NomeFile("Titolo")
                                .type("pdf")
                                .FileData(null).build();
    

        ElaboratoFinale ef_obj_saved = tbl_ef.save(ef_obj);

        Assertions.assertEquals(ef_obj_saved, ef_obj);
        Assertions.assertTrue(tbl_ef.findAll().contains(ef_obj_saved));

    }

    @Test
    public void findById_test_Returnelaborato() {

        ElaboratoFinale ef_obj = ElaboratoFinale.builder()
        .matricola(823457)
        .NomeFile("Titolo")
        .type("pdf")
        .FileData(null).build();


        ElaboratoFinale ef_obj_saved = tbl_ef.save(ef_obj);

        Assertions.assertEquals(Optional.of(ef_obj), tbl_ef.findById(ef_obj_saved.getMatricola()));

    }


    
}
