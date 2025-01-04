package com.gestionetirocinio.DAOTests;

import com.gestionetirocinio.DAO.dao_Tirocinio;
import com.gestionetirocinio.Modello.TirocinioEsterno;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class DAO_tirocinio_tests {

    @Autowired
    private dao_Tirocinio tbl_tirocinio;


    @Test
    public void saveDuplicated_test_ReturnNull() {

        TirocinioEsterno tirocinio_obj = TirocinioEsterno.builder()
                                            .matricola(823457)
                                            .nome_azienda("NSR")
                                            .SedeAzienda("FIUMICINO")
                                            .build();
        TirocinioEsterno tirocinio_obj_2 = TirocinioEsterno.builder()
                                            .matricola(823457)
                                            .nome_azienda("LEGO")
                                            .SedeAzienda("Milano")
                                            .build();

        
        tbl_tirocinio.save(tirocinio_obj_2);                          
        tbl_tirocinio.save(tirocinio_obj);


        Assertions.assertFalse(tbl_tirocinio.findAll().contains(tirocinio_obj_2));


    }


}
