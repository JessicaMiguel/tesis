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
@Table(name="ChequePago")
public class Facturacion {
    @Id
    @SequenceGenerator(
            name = "facturacion_sequence",
            sequenceName = "facturacion_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "facturacion_sequence"
    )

    private Long ID_facturacion;

    @Column(name="fecha_entrega")
    private Date fecha_entrega;

    @Column(name="sucursal")
    private Long sucursal;

    @Column(name="comprobante")
    private String comprobante;

    @Column(name="razon_social")
    private String razon_social;

    @Column(name="importe_total")
    private double importe_total;
}