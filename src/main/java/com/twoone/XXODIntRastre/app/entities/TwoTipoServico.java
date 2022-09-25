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
@Table(name="TWO_TIPO_SERVICO")
public class TwoTipoServico implements Serializable {

    @Id
    @Column(name="TP_SERV_ID",length=15,nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tpServId;

    @Column(name="DESCR",length=1000,nullable = false)
    private String descr;

    @Column(name="USUARIO_ID_CRIACAO",length=50,nullable = false)
    private String usuarioIdCriacao;

    @Column(name="DT_HR_CRIACAO",nullable = false)
    private Timestamp dtHrCriacao;

    @Column(name="USUARIO_ID_ALTER",length=50,nullable = false)
    private String usuarioIdAlter;

    @Column(name="DT_HR_ALTER",nullable = false)
    private Timestamp dtHrAlter;

    @Column(name="COD",length=10)
    private String cod;

    @Column(name="DT_FINAL")
    private Timestamp dtFinal;

    @Column(name="ORIGEM",length=1,nullable = false)
    private String origem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TP_SERV_ID_INTEGRACAO", referencedColumnName = "TP_SERV_ID")
    //@Column(name="TP_SERV_ID_INTEGRACAO",length=15)
    private TwoTipoServico tpServIdIntegracao; // FK => TIPO_SERVICO

    @Column(name="PERC_MAT",precision=10, scale=7)
    private Long percMat;

    @Column(name="PERC_EQP",precision=10, scale=7)
    private Long percEqp;

    @Column(name="PERC_SERV",precision=10, scale=7)
    private Long percServ;


}
