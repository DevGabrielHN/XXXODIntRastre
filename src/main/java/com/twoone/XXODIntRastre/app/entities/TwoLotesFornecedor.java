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
@Table(name="TWO_LOTES_FORNECEDOR")
public class TwoLotesFornecedor implements Serializable {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // AUTO

    @Column(name="NUMERO_DO_LOTE")
    private Long numeroDoLote; // Total de lihas (unidades do lote) + YYYYMMDDHHMMSS

    @Column(name="CREATED_DATE")
    private Timestamp createdDate;

    @Column(name="CREATED_BY")
    private String createdBy;

    @Column(name="LAST_UPDATE_DATE")
    private Timestamp lastUpdateDate;

    @Column(name="LAST_UPDATE_BY")
    private String lastUpdadeBy;



}
