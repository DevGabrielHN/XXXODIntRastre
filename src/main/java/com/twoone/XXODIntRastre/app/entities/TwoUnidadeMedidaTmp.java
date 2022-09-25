package com.twoone.XXODIntRastre.app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="TWO_UNIDADE_MEDIDA_TMP")
public class TwoUnidadeMedidaTmp implements Serializable {

    @Id
    @Column(name = "UNID_MED_TMP_ID",nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long unidMedTmpId;

    @Column(name="COD",length=5)
    private String cod; // codigoUnidadeMedida

    @Column(name="DESCR",length=40)
    private String descr; // nomeUnidadeMedida

    @Column(name="BASE",length=60)
    private String base;

    @Column(name="DT_FINAL")
    private Timestamp dtFinal;

    @Column(name="USUARIO",length=40)
    private String usuario;

    @Column(name="MSG",length=4000)
    private String msg;

    @Column(name="STATUS",length=25)
    private String status;

    @Column(name="INSTANCE_ID")
    private Long instanceId;

    @Column(name="INTEGRATION_DATE")
    private Timestamp integrationDate;

    @ManyToOne
    @JoinColumn(name="ID")
    private TwoLotesUnidadeMedida loteId;



}
