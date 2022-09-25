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
@Table(name="XXOD_DE_PARA_DETAIL",indexes = { @Index(name = "XXOD_DE_PARA_DETAIL_U2"
        , columnList = "DESTINO_01,DESTINO_02,DESTINO_03,DESTINO_04,DESTINO_05,DESTINO_06,DESTINO_07" +
        ",DESTINO_08,DESTINO_09,DESTINO_10,ID_DE_PARA,ORIGEM_01,ORIGEM_02,ORIGEM_03,ORIGEM_04,ORIGEM_05" +
        ",ORIGEM_06,ORIGEM_07,ORIGEM_08,ORIGEM_09,ORIGEM_10") })
public class XxodDeParaDetail implements Serializable {

    @Id
    @Column(name="ID_DE_PARA_DETAIL",nullable = false)
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idDeParaDetail;

    @ManyToOne
    @JoinColumn(name="ID_DE_PARA")
    //@Column(name="ID_DE_PARA",nullable = false)
    private XxodDePara idDePara; // FK => XXOD_DE_PARA

    @Column(name="ORIGEM_01",length=50,nullable = false)
    private String origem01;

    @Column(name="ORIGEM_02",length=250)
    private String origem02;

    @Column(name="ORIGEM_03",length=50)
    private String origem03;

    @Column(name="ORIGEM_04",length=50)
    private String origem04;

    @Column(name="ORIGEM_05",length=50)
    private String origem05;

    @Column(name="ORIGEM_06",length=250)
    private String origem06;

    @Column(name="ORIGEM_07",length=250)
    private String origem07;

    @Column(name="ORIGEM_08",length=250)
    private String origem08;

    @Column(name="ORIGEM_09",length=250)
    private String origem09;

    @Column(name="ORIGEM_10",length=250)
    private String origem10;

    @Column(name="DESTINO_01",length=250,nullable = false)
    private String destino01;

    @Column(name="DESTINO_02",length=250)
    private String destino02;

    @Column(name="DESTINO_03",length=250)
    private String destino03;

    @Column(name="DESTINO_04",length=250)
    private String destino04;

    @Column(name="DESTINO_05",length=250)
    private String destino05;

    @Column(name="DESTINO_06",length=250)
    private String destino06;

    @Column(name="DESTINO_07",length=250)
    private String destino07;

    @Column(name="DESTINO_08",length=250)
    private String destino08;

    @Column(name="DESTINO_09",length=250)
    private String destino09;

    @Column(name="DESTINO_10",length=250)
    private String destino10;

    @Column(name="HABILITADO",length=1)
    private String habilitado;

}
