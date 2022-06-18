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
@Table(name="CuentaCorriente")

public class CuentaCorriente {

    @Id
    @SequenceGenerator(
            name = "cuenta_corriente_sequence",
            sequenceName = "cuenta_corriente_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cuenta_corriente_sequence"
    )
    private Long ID_cuenta_corriente;

    @Column(name="fecha_recibo")
    private Date fecha_recibo;

    @Column(name="fecha_vto")
    private Date fecha_vto;

    @Column(name="fecha_pago")
    private Date fecha_pago;

    @Column(name="sucursal")
    private Long sucursal;

    //@Column(name="tipo_comprobante")
    //private String tipo_comprobante;
    @Column(name = "comprobante")
    private String comprobante;

    @Column(name="cuota")
    private Long cuota; //no se para que es

    @Column(name="concepto") //es en concepto de remito o factura
    private String concepto;

    @Column(name="razon_social")
    private String razon_social;

    @Column(name="Id_cliente")
    private Long Id_cliente;

    @Column(name="telefono_cliente")
    private String telefono_cliente;

    @Column(name="direccion_cliente")
    private String direccion_cliente;

    /**
     * Se tiene el tot_gen: que es el total generado entre debe + haber de un pago
     * El atributo debe: es cuando debe el cliente de ese pago
     * El atributo haber: es cuanto pagó el cliente
     *
     * Ejemplo:
     * tot_gen  debe    haber
     * 100      100     0
     * 250      0       250
     *
     * El atributo tot_gen lo dejamos porque no sabemos si en un futuro no puede
     * representar la suma de un debe + haber..
     */
    @Column(name = "tot_gen")
    private double tot_gen;

    @Column(name="debe")
    private double debe;

    @Column(name="haber")
    private double haber;

    /**
     * El saldo anterior no se lee
     * El saldo del cliente es una consulta a la base de datos.
     @Column(name="saldo_inicial")
     private double saldo_inicial;
     @Column(name="saldo_cliente")
     private double saldo_cliente;
     */

    @Column(name = "estado")
    private String estado;

    /*
    Preguntar si hacer dos tablas, sino filtrar por Id_cliente (generado por la empresa de embalajes)
    para obtener todos lo items de la cuenta corriente
     */
}