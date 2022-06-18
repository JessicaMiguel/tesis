package com.example.tesis.service;

import com.example.tesis.apiDTO.DeltaDiasCliente;
import com.example.tesis.domain.CuentaCorriente;
import com.example.tesis.repository.CuentaCorrienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileCuentaCorrienteServiceImpl implements FileService {

    @Autowired
    private CuentaCorrienteRepository cuentaRepository;

    @Override
    public void insertNewFile(Object objectFile) {

        CuentaCorriente cuentaCorriente = (CuentaCorriente) objectFile;
        this.cuentaRepository.save(cuentaCorriente);
    }

    public List<DeltaDiasCliente> findChequesRechazados(Long Id_Cliente){
        return this.cuentaRepository.findDeltaDiasPago(Id_Cliente);
    }
}