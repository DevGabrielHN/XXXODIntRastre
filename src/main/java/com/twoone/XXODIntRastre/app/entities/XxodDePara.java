package com.twoone.XXODIntRastre.app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="XXOD_DE_PARA",indexes = { @Index(name = "XXOD_DE_PARA_U1", columnList = "ID_DE_PARA") })
public class XxodDePara implements Serializable {

    @Id
    @Column(name="ID_DE_PARA",nullable = false)
 //   @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idDePara;

    @Column(name="REFERENCIA",length = 50,nullable = false)
    private String referencia;

    @Column(name="NOME",length=50,nullable = false)
    private String nome;

    @Column(name="DESCRICAO",length=100,nullable = false)
    private String descricao;

}
