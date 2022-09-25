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
@Table(name="TWO_TIPO_SERVICO_TMP")
public class TwoTipoServicoTmp implements Serializable {

    @Id
    @Column(name="TP_SERV_TMP_ID",length=15,nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO) // AUTO
    private Long tpServTmpId;

    @Column(name="DESCR",length=1000)
    private String descr;

    @Column(name="COD",length=25)
    private String cod;

    @Column(name="DT_FINAL")
    private Timestamp dtFinal;

    @Column(name="ORIGEM",length=1,nullable = false)
    private String origem;

    @Column(name="TP_SERV_ID_INTEGRACAO")
    private Long tpServIdIntegracao;

    @Column(name="PERC_MAT")
    private Long percMat;

    @Column(name="PERC_EQP")
    private Long percEqp;

    @Column(name="PERC_SERV")
    private Long percServ;

    @Column(name="BASE",length=50)
    private String base;

    @Column(name="BASE_ID")
    private Long baseId;

    @Column(name="USUARIO",length=50)
    private String usuario;

    @Column(name="INTEGRATION_DATE")
    private Timestamp integrationDate;

    @Column(name="INSTANCE_ID")
    private Long instanceId;

    //@Lob
   // @Basic(fetch=LAZY)
    @Column(name="MSG",length = 1000) // Coluna do tipo CLOB
    private String msg;

    @Column(name="STATUS",length=25)
    private String status;

    @ManyToOne
    @JoinColumn(name="ID")
    private TwoLotesTipoServico loteId;

    @Column(name="CREATED_DATE")
    private Timestamp createdDate;

    @Column(name="CREATED_BY")
    private String createdBy;

    @Column(name="LAST_UPDATE_DATE")
    private Timestamp lastUpdateDate;

    @Column(name="LAST_UPDATE_BY")
    private String lastUpdadeBy;



}
