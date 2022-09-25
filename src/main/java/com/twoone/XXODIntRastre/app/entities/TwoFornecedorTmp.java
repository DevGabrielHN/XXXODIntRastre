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
@Table(name="TWO_FORNECEDOR_TMP")
public class TwoFornecedorTmp implements Serializable {

    @Id
    @Column(name="FORN_TMP_ID",length=15,nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long fornTmpId; // É o Id?

    @Column(name="INSTANCE_ID" /*,nullable = false*/)
    private Long instanceId;

    @Column(name="NOME",length=240)
    private String nome;

    @Column(name="TP_IDENT_ID",length=6)
    private String tpIdentId;

    @Column(name="IDENTIFICADOR",length=30)
    private String identificador;

    @Column(name="RAIZ",length=150)
    private String raiz;

    @Column(name="FILIAL",length=150)
    private String filial;

    @Column(name="DIGITO_VERIFICADOR",length=150)
    private String digitoVerificador;

    @Column(name="SITE_COD",length=40)
    private String siteCod;

    @Column(name="ENDERECO",length=240)
    private String endereco;

    @Column(name="NUMERO",length=240)
    private String numero;

    @Column(name="COMPLEMENTO",length=240)
    private String complemento;

    @Column(name="BAIRRO",length=240)
    private String bairro;

    @Column(name="CID_ID")
    private Long cidId;

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

    @Column(name="EMAIL",length=240)
    private String email;

    @Column(name="IND_DESONERACAO",length=1)
    private String indDesoneracao;

    @Column(name="IND_CONTA",length=10)
    private String indConta;

    @Column(name="COD_BANCO",length=100)
    private String codBanco;

    @Column(name="NOME_BANCO",length=100)
    private String nomeBanco;

    @Column(name="COD_AGENCIA",length=100)
    private String codAgencia;

    @Column(name="NOME_AGENCIA",length=100)
    private String nomeAgencia;

    @Column(name="COD_CONTA",length=100)
    private String codConta;

    @Column(name="DIG_VERIF_CONTA",length=30)
    private String digVerifConta;

    @Column(name="DT_FINAL")
    private Timestamp dtFinal;

    @Column(name="UNIDADE",length=10)
    private String unidade;

    @Column(name="UNIDADE_ID")
    private Long unidadeId;

    @Column(name="USUARIO",length=50)
    private String usuario;

    @Column(name="INTEGRATION_DATE")
    private Timestamp integrationDate;

    @Column(name="MSG",length=4000)
    private String msg;

    @Column(name="STATUS",length=25)
    private String status;

    @Column(name="BASE",length=240)
    private String base;

    @Column(name="BASE_ID")
    private Long baseId;

    @Column(name="SISTEMA_ID")
    private Long sistemaId;

    @Column(name="NIVEL_CONTA",length=50)
    private String nivelConta;

    @Column(name="MSG_CONTA",length=4000)
    private String msgConta;

    @Column(name="STATUS_CONTA",length=25)
    private String statusConta;

    @ManyToOne
    @JoinColumn(name="ID",nullable = false)
    private TwoLotesFornecedor loteId;


}
