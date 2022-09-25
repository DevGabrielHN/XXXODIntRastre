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
@Table(name="TWO_UNIDADE_MEDIDA",indexes = { @Index(name = "XU1UNIDADE_MEDIDA", columnList = "BASE_ID,COD") })
public class TwoUnidadeMedida implements Serializable {

    @Id
    @Column(name="UNID_MED_ID",length=15,nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long unidMedId;

    @Column(name="COD",length=5,nullable = false)
    private String cod;

    @Column(name="DESCR",length=40,nullable = false)
    private String descr;

    @ManyToOne
    @JoinColumn(name="BASE_ID")
    private TwoBaseDado baseId;

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

}
