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
@Table(name="TWO_UNIDADE_TMP",indexes = { @Index(name = "UNIDADE_TMP_U1", columnList = "INSTANCE_ID,STATUS") })
public class TwoUnidadeTmp implements Serializable {

    @Id
    @Column(name="UNIDADE_TMP_ID", nullable=false, length=15)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long unidadeTmpId;

    @Column(name="COD",length=10)
    private String cod;

    @Column(name="DESCR",length=240)
    private String descr;

    @Column(name="TIPO",length=2)
    private String tipo;

    @Column(name="IDENTIFICADOR",length=30)
    private String identificador;

    @Column(name="DT_FINAL")
    private Timestamp dtFinal;

    @Column(name="UO_DESSA_OI",length=10)
    private String uoDessaOi;

    @Column(name="ORGANIZATION_CODE",length=3)
    private String organizationCode;

    @Column(name="BASE",length=50)
    private String base;

    @Column(name="CIDADE",length=240)
    private String cidade;

    @Column(name="COD_CIDADE",length=240)
    private String codCidade;

    @Column(name="ESTADO",length=240)
    private String estado;

    @Column(name="COD_ESTADO",length=150)
    private String codEstado;

    @Column(name="PAIS",length=360)
    private String pais;

    @Column(name="COD_PAIS",length=60)
    private String codPais;

    @Column(name="USUARIO",length=50)
    private String usuario;

    @Column(name="INTEGRATION_DATE")
    private Timestamp integrationDate;

    @Column(name="INSTANCE_ID")
    private Long instanceId;

    @Column(name="MSG",length=4000)
    private String msg;

    @Column(name="STATUS",length=25)
    private String status;

    @Column(name="CREATED_DATE")
    private Timestamp createdDate;

    @Column(name="CREATED_BY")
    private String createdBy;

    @Column(name="LAST_UPDATE_DATE")
    private Timestamp lastUpdateDate;

    @Column(name="LAST_UPDATE_BY")
    private String lastUpdadeBy;

    @ManyToOne
    @JoinColumn(name="ID",nullable = false)
    private TwoLotesUnidades loteId;
}
