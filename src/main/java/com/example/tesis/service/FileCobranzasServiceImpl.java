package com.example.tesis.service;

import com.example.tesis.domain.Cobranza;
import com.example.tesis.repository.CobranzasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileCobranzasServiceImpl implements FileService {

    @Autowired
    private CobranzasRepository cobranzasRepository;

    @Override
    public void insertNewFile(Object objectFile) {

        Cobranza cobranza = (Cobranza) objectFile;
        this.cobranzasRepository.save(cobranza);

    }
}