package com.gestionetirocinio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.gestionetirocinio.Client.*;
import com.gestionetirocinio.DAO.*;
import com.gestionetirocinio.Modello.*;


@SpringBootApplication
public class GestioneTirocinioApplication {

    public static void main(String[] args) {

            SpringApplication.run(GestioneTirocinioApplication.class, args);
    }
    
}
