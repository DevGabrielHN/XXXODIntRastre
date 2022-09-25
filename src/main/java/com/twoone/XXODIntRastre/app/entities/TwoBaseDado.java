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
@Table(name="TWO_BASE_DADO")
public class TwoBaseDado implements Serializable {

    @Id
    @Column(name="BASE_ID",length=15,nullable = false)
 //  @GeneratedValue(strategy = GenerationType.AUTO)
    private Long baseId;

    @Column(name="NOME",length=20,nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name="SISTEMA_ID")
    //@Column(name="SISTEMA_ID",length=15,nullable = false)
    private TwoSistemaOrigem sistemaId; // FK => SISTEMA_ORIGEM

    @Column(name="USUARIO_ID_CRIACAO",length=50,nullable = false)
    private String usuarioIdCriacao;

    @Column(name="DT_HR_CRIACAO",nullable = false)
    private Timestamp dtHrCriacao;

    @Column(name="USUARIO_ID_ALTER",length = 50,nullable = true)
    private String usuarioIdAlter;

    @Column(name="DT_HR_ALTER",nullable = false)
    private Timestamp dtHrAlter;

    @Column(name="SISTEM_INT_WS",length=50)
    private String sistemIntWs;

    @Column(name="API_INT_WS",length=50)
    private String apiIntWs;

    @Column(name="DYQR_SERVER_NAME",length=50)
    private String dyqrServerName;

    @Column(name="LOOKUP_ID_DOCUMENT_MODEL",length=15)
    private Long lookupIdDocumentModel;

}
