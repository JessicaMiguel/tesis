package com.example.tesis.repository;

import com.example.tesis.apiDTO.DeltaDiasCliente;
import com.example.tesis.domain.CuentaCorriente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuentaCorrienteRepository extends JpaRepository<CuentaCorriente, Long> {

    @Query("Select (B.fecha_vto - A.fecha_pago) AS DiasPago, A.Id_cliente, A.razon_social, A.tot_gen   FROM CuentaCorriente A, CuentaCorriente B WHERE A.Id_cliente = B.Id_cliente AND A.Id_cliente = :Id_cliente AND A.debe = B.haber")
    List<DeltaDiasCliente> findDeltaDiasPago(@Param("Id_cliente") Long Id_cliente);
}