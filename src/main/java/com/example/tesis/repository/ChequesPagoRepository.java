package com.example.tesis.repository;

import com.example.tesis.apiDTO.DeltaDiasCliente;
import com.example.tesis.controller.DatosCliente;
import com.example.tesis.domain.ChequesPago;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChequesPagoRepository extends JpaRepository<ChequesPago, Long> {

    @Query("Select (c.fecha_vto - c.fecha) AS dias, c.Id_cliente, c.Descrip_cliente, c.importe From ChequesPago c Where c.Id_cliente = :Id_cliente AND c.estado_cheque NOT IN ('RECHAZADO', 'DE BAJA') ORDER BY importe")
    List<DeltaDiasCliente> findById_Cliente(@Param("Id_cliente") Long Id_cliente);

    @Query("Select DISTINCT (c.Id_cliente), c.Descrip_cliente FROM ChequesPago c")
    List<DatosCliente> findAllClients();

}