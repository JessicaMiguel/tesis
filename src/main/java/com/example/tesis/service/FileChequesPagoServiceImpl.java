package com.example.tesis.service;

import com.example.tesis.apiDTO.DeltaDiasCliente;
import com.example.tesis.controller.DatosCliente;
import com.example.tesis.domain.ChequesPago;
import com.example.tesis.repository.ChequesPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@ComponentScan(basePackages = {"com.example.tesis.service"})
@Service
public class FileChequesPagoServiceImpl implements FileService{
    @Autowired
    private final ChequesPagoRepository chequesPagoRepository;

    @Autowired
    public FileChequesPagoServiceImpl(ChequesPagoRepository chequesPagoRepository) {
        this.chequesPagoRepository = chequesPagoRepository;
    }


    @Override
    public void insertNewFile(Object objectFile) {

        ChequesPago chequesPago = (ChequesPago) objectFile;
        this.chequesPagoRepository.save(chequesPago);

    }

    public List<DeltaDiasCliente> findChequesCliente(Long Id_Cliente){

        //List<ChequesPago> cheques =  this.chequesPagoRepository.findById_Cliente(Id_Cliente);
        //List<DeltaDiasCliente> delta = new ArrayList<>();
        List<DeltaDiasCliente> delta =   this.chequesPagoRepository.findById_Cliente(Id_Cliente);

        return delta;
    }



    public List<DatosCliente> getClients(){
        //List<ChequesPago> cheques = this.chequesPagoRepository.findAllClients();
        //List<DatosCliente> datos = new ArrayList<>();
        List<DatosCliente> datos = this.chequesPagoRepository.findAllClients();
        return datos;
    }

}