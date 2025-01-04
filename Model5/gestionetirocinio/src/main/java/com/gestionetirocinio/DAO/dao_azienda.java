package com.gestionetirocinio.DAO;

import java.util.*;

import com.gestionetirocinio.Modello.Azienda;

import org.springframework.data.jpa.repository.JpaRepository;


public interface dao_azienda extends JpaRepository<Azienda,String> {
    
}