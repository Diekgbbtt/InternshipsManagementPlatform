package com.gestionetirocinio;

import org.springframework.boot.test.context.SpringBootTest;

import com.gestionetirocinio.Controller.DataController;

import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


@SpringBootTest
public class SmokeTests {

    @Autowired
    private DataController controller;

    @Test
    public void contextLoads() throws Exception {
        /* Assertions.assertThat(controller).isNotNull(); */
    }
    
}
