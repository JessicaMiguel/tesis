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
@Table(name="ChequesPago")

public class ChequesPago {
    @Id
    @SequenceGenerator(
            name = "cheque_sequence",
            sequenceName = "cheque_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cheque_sequence"
    )
    private Long ID_chequePago;

    @Column(name="fecha")
    private Date fecha;

    @Column(name="fecha_vto")
    private Date fecha_vto;

    @Column(name="Id_banco")
    private Long Id_banco;

    @Column(name="descrip_banco")
    private String Descrip_banco;

    @Column(name="Id_cliente")
    private Long Id_cliente;

    @Column(name="Descrip_cliente")
    private String Descrip_cliente;

    @Column(name="cheque_nro")
    private Long Cheque_nro;

    @Column(name="importe")
    private double importe;

    @Column(name="estado_cheque")
    private String estado_cheque;

}