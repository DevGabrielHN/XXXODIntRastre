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
@Table(name="TWO_UNIDADE_FEDERATIVA",indexes = { @Index(name = "UNIDADE_FEDERATIVA_U1", columnList = "NOME,PAIS_ID,SIGLA") })
public class TwoUnidadeFederativa implements Serializable {

    @Id
    @Column(name="UF_ID",length=15,nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ufId;

    @Column(name="NOME",length=40,nullable = false)
    private String nome;

    @Column(name="SIGLA",length=40,nullable = false)
    private String sigla;

    @Column(name="USUARIO_ID_CRIACAO",length=50,nullable = false)
    private String usuarioIdCriacao;

    @Column(name="DT_HR_CRIACAO",nullable = false)
    private Timestamp dtHrCriacao;

    @Column(name="USUARIO_ID_ALTER",length=50,nullable = false)
    private String usuarioIdAlter;

    @Column(name="DT_HR_ALTER",nullable = false)
    private Timestamp dtHrAlter;

    @ManyToOne
    @JoinColumn(name="PAIS_ID")
 //   @Column(name="PAIS_ID",length=15,nullable = false)
    private TwoPais paisId; // FK => PAIS

}
