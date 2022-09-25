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
@Table(name="TWO_UNIDADE",indexes = { @Index(name = "IDX_UNIDADE", columnList = "BASE_ID,COD,TIPO") })
public class TwoUnidade implements Serializable {

    @Id
    @Column(name="UNIDADE_ID",length = 15,nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long unidadeId;

    @Column(name="COD",length = 10,nullable = false)
    private String cod;

    @Column(name="DESCR",length = 240,nullable = false)
    private String descr;

    @Column(name="TIPO",length=2,nullable = false)
    private String tipo;

    @Column(name="IDENTIFICADOR",length=30)
    private String identificador;

    @ManyToOne
    @JoinColumn(name="BASE_ID")
   // @Column(name="BASE_ID",length=15,nullable = false)
    private TwoBaseDado baseId; // FK => BASE_DADO

    @Column(name="USUARIO_ID_CRIACAO",length=50,nullable = false)
    private String usuarioIdCriacao;

    @Column(name="DT_HR_CRIACAO",nullable = false)
    private Timestamp dtHrCriacao;

    @Column(name="USUARIO_ID_ALTER",length=50,nullable = false)
    private String usuarioIdAlter;

    @Column(name="DT_HR_ALTER",nullable = false)
    private Timestamp dtHrAlter;

    @Column(name="DT_FINAL")
    private Timestamp dtFinal;

    @Column(name="UO_DESSA_OI",length = 10)
    private String uoDessaOi;

    @Column(name="ORGANIZATION_CODE",length=3)
    private String organizationCode;

    @Column(name="CID_ID",length = 15)
    private Long cidId;

    @Column(name="IND_CTR_PROT",length=1)
    private String indCtrProt;

    @Column(name="TP_CTR_EMAIL_PROT",length=1)
    private String tpCtrEmailProt;

    @Column(name="RI_CAPEX_OPEX_FLAG",length=1)
    private String riCapexOpexFlag;

    @Column(name="NEGOCIO",length=150)
    private String negocio;

    @Column(name="IND_RI_DATE_CREATION",length=1)
    private String indRiDateCreation;

    @Column(name="IND_CHECK_RI_DATE",length=1)
    private String indCheckRiDate;

    @Column(name="E_MAIL_GAF",length=256)
    private String emailGaf;

    @Column(name="IND_CTR_EMAIL_RECHACA",length=1)
    private String indCtrEmailRechaca;

    @Column(name="IND_CTR_RECHACA",length=1)
    private String indCtrRechaca;

}
