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
@Table(name="TWO_FORNECEDOR_SITE",indexes = { @Index(name = "XIU1FORNECEDOR_SITE", columnList = "FORN_ID,SITE_COD") })
public class TwoFornecedorSite implements Serializable {

    @Id
    @Column(name="FORN_SITE_ID",length=15,nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long fornSiteId;

    @ManyToOne
    @JoinColumn(name="FORN_ID")
    private TwoFornecedor fornId;

    @Column(name="SITE_COD",length=200,nullable = false)
    private String siteCod;

    @Column(name="RAIZ",length=200)
    private String raiz;

    @Column(name="FILIAL",length=200)
    private String filial;

    @Column(name="DIGITO_VERIFICADOR",length=200)
    private String digitoVerificador;

    @Column(name="ENDERECO",length=200)
    private String endereco;

    @Column(name="NUMERO",length=200)
    private String numero;

    @Column(name="COMPLEMENTO",length=200)
    private String complemeto;

    @Column(name="BAIRRO",length=200)
    private String bairro;

    @Column(name="EMAIL",length=200)
    private String email;

    @Column(name="IND_DESONERACAO",length=1,nullable = false)
    private String indDesoneracao;

    @ManyToOne
    @JoinColumn(name="CID_ID")
    private TwoCidade cidId;

    @ManyToOne
    @JoinColumn(name="SISTEMA_ID")
    private TwoSistemaOrigem sistemaId;

    @Column(name="USUARIO_ID_CRIACAO",length=50,nullable = false)
    private String usuarioIdCriacao;

    @Column(name="DT_HR_CRIACAO",nullable = false)
    private Timestamp dtHrCriacao;

    @Column(name="USUARIO_ID_ALTER",length=50,nullable = false)
    private String usuarioIdAlter;

    @Column(name="DT_HR_ALTER",nullable = false)
    private Timestamp dtHrAlter;

    @Column(name="CHAVE_COMPLETA",length=50)
    private String chaveCompleta;

    @Column(name="TP_IDENT_ID",length=200)
    private String tpIdentId;

}
