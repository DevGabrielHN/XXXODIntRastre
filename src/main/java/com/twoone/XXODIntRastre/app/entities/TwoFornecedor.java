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
@Table(name="TWO_FORNECEDOR",indexes = { @Index(name = "UK_FORN_IDENT", columnList = "IDENTIFICADOR") })
public class TwoFornecedor implements Serializable {

    @Id
    @Column(name="FORN_ID",length=15,nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long fornId;

    @Column(name="NOME",length=200,nullable = false)
    private String nome;

    @Column(name="TP_IDENT_ID",length=200,nullable = false)
    private String tpIdentId;

    @Column(name="IDENTIFICADOR",length=200,nullable = false)
    private String identificador;

    @Column(name="USUARIO_ID_CRIACAO",length=50,nullable = false)
    private String usuarioIdCriacao;

    @Column(name="DT_HR_CRIACAO",nullable = false)
    private Timestamp dtHrCriacao;

    @Column(name="USUARIO_ID_ALTER",length=50,nullable = false)
    private String usuarioIdAlter;

    @Column(name="DT_HR_ALTER",nullable = false)
    private Timestamp dtHrAlter;

    @Column(name="BASE_ID")
    private Long baseId;

    @Column(name="IND_FIS_JUR",length=1)
    private String indFisJur;

}
