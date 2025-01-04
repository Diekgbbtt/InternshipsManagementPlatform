package com.gestionetirocinio.DAOTests;




import java.util.*;


import com.gestionetirocinio.DAO.dao_azienda;
import com.gestionetirocinio.Modello.Azienda;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DAO_azienda_tests {

    @Autowired
    private dao_azienda tbl_azienda;


    @Test 
    public void save_test_ReturnAzienda() {
    

        
        Azienda Azienda_obj = Azienda.builder()
                                    .NomeAzienda("Frasassi_test1")
                                    .Sede("Via Cesare 12")
                                    .Telefono(345)
                                    .Email(null).build();

        Azienda Azienda_obj_dup = Azienda.builder()
                                    .NomeAzienda("Frasassi_test1")
                                    .Sede("Via Cesare 12")
                                    .Telefono(345)
                                    .Email(null).build();

        Azienda Azienda_obj_saved = tbl_azienda.save(Azienda_obj);
        try { 
        Object SaveResult = tbl_azienda.save(Azienda_obj_dup);
        } catch(org.springframework.dao.DataIntegrityViolationException e) {

        Assertions.assertTrue(tbl_azienda.findAll().contains(Azienda_obj_saved));
        Assertions.assertFalse(tbl_azienda.findAll().contains(Azienda_obj_dup));
        }
        // check vincolo UNIQUE su nome azienda della tabella  aziende

    }

    @Test
    public void findAll_test_ReturnListAzienda() {

        tbl_azienda.deleteAll();

        Azienda Azienda_obj = Azienda.builder()
        .NomeAzienda("Frasassi_test3")
        .Sede("Via Cesare 12")
        .Telefono(345)
        .Email(null).build();

        Azienda Azienda_obj_2 = Azienda.builder()
        .NomeAzienda("Frasassi_test4")
        .Sede("Via Cesare 13")
        .Telefono(344)
        .Email(null).build();

        tbl_azienda.saveAll(Arrays.asList(Azienda_obj, Azienda_obj_2));

        Assertions.assertEquals(tbl_azienda.findAll().size(), 2);

    }
    
}
