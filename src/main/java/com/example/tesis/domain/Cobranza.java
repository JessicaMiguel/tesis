package com.example.tesis.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Cobranza")

public class Cobranza {

    @Id
    @SequenceGenerator(
            name = "cobranza_sequence",
            sequenceName = "cobranza_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cobranza_sequence"
    )
    private Long ID_cobranza;

    @Column(name="fecha_recibo")
    private Date fecha_recibo;

    @Column(name="fecha_cobro")
    private Date fecha_cobro;

    @Column(name="sucursal")
    private Long sucursal;

    @Column(name="comprobante")
    private String comprobante;

    @Column(name="cod_cliente")
    private Long cod_cliente;

    @Column(name="num_cliente")
    private Long num_cliente;

    @Column(name="Id_cliente")
    private Long Id_cliente;

    @Column(name="razon_social")
    private String razon_social;

    @Column(name= "importe")
    private double importe;

}