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
@Table(name="TWO_CIDADE",indexes = { @Index(name = "CIDADE_U1", columnList = "NOME,UF_ID") })
public class TwoCidade implements Serializable {

    @Id
    @Column(name="CID_ID",length=15,nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cidId;

    @Column(name="NOME",length=40,nullable = false)
    private String nome;

    @Column(name="USUARIO_ID_CRIACAO",length=50,nullable = false)
    private String usuarioIdCriacao;

    @Column(name="DT_HR_CRIACAO",nullable = false)
    private Timestamp dtHrCriacao;

    @Column(name="USUARIO_ID_ALTER",length=50,nullable = false)
    private String usuarioIdAlter;

    @Column(name="DT_HR_ALTER",nullable = false)
    private Timestamp dtHrAlter;

    @ManyToOne
    @JoinColumn(name="UF_ID")
    //@Column(name="UF_ID",length=15,nullable = false)
    private TwoUnidadeFederativa ufId; // FK => UNIDADE_FEDERATIVA

    @Column(name="URL_PREFEITURA",length=255)
    private String urlPrefeitura;

    @Column(name="STATUS_IMPORTACAO",length=15)
    private String statusImpotacao;

    @Column(name="DT_FINAL")
    private Timestamp dtFinal;


}
