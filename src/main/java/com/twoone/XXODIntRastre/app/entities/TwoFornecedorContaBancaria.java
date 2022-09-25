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
@Table(name="TWO_FORNECEDOR_CONTA_BANCARIA",indexes = {
        @Index(name = "XUI1FORNECEDOR_CONTA_BANCARIA", columnList = "COD_BANCO,COD_AGENCIA,COD_CONTA,DIG_VERIF_CONTA" +
                ",FORN_SITE_ID,FORN_ID,UNIDADE_ID,BASE_ID") })
public class TwoFornecedorContaBancaria implements Serializable {

    @Id
    @Column(name="FORN_CTA_BCO_ID",length=15,nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long fornCtaBcoId;

    @Column(name="COD_BANCO",length=100,nullable = false)
    private String codBanco;

    @Column(name="NOME_BANCO",length=100)
    private String nomeBanco;

    @Column(name="COD_AGENCIA",length=100,nullable = false)
    private String codAgencia;

    @Column(name="NOME_AGENCIA",length=100)
    private String nomeAgencia;

    @Column(name="COD_CONTA",length=45,nullable = false)
    private String codConta;

    @Column(name="DIG_VERIF_CONTA",length=30)
    private String digVerifConta;

    @ManyToOne
    @JoinColumn(name="FORN_ID")
    private TwoFornecedor fornId;

    @ManyToOne
    @JoinColumn(name="FORN_SITE_ID")
    private TwoFornecedorSite fornSiteId;

    @ManyToOne
    @JoinColumn(name="SISTEMA_ID")
    private TwoSistemaOrigem sistemaId;

    @Column(name="DT_FINAL")
    private Timestamp dtFinal;

    @Column(name="USUARIO_ID_CRIACAO",length=50,nullable = false)
    private String usuarioIdCriacao;

    @Column(name="DT_HR_CRIACAO",nullable = false)
    private Timestamp dtHrCriacao;

    @Column(name="USUARIO_ID_ALTER",length=50,nullable = false)
    private String usuarioIdAlter;

    @Column(name="DT_HR_ALTER",nullable = false)
    private Timestamp dtHrAlter;

    @ManyToOne
    @JoinColumn(name="UNIDADE_ID")
    private TwoUnidade unidadeId;

    @Column(name="BASE_ID")
    private Long baseId;

    @Column(name="IND_AUTO_CRIACAO",length=1)
    private String indAutoCriacao;

}
