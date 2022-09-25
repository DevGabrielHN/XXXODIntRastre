package com.twoone.XXODIntRastre.app.repository;

import com.twoone.XXODIntRastre.app.entities.TwoTipoNotaRi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TwoTipoNotaRiRepository extends JpaRepository<TwoTipoNotaRi,Long> {

    @Query(
        value = "SELECT tnr.tipoNfId " +
                "FROM TwoTipoNotaRi tnr " +
                "WHERE tnr.invoiceTypeId = :invoiceTypeId " +
                "AND tnr.baseId = :baseId"
    )
    public Long IndexTipoNfId(@Param("invoiceTypeId") Long invoiceTypeId,
                                 @Param("baseId") Long baseId);
    //
    @Query(
            value= "SELECT tnr " +
                    "FROM TwoTipoNotaRi tnr " +
                    "WHERE tnr.tipoNfId = :tipoNfId"
    )
    public TwoTipoNotaRi getTipoNotaRiPorId(@Param("tipoNfId") Long tipoNfId);
    //
    @Query(
        value= "SELECT tnr " +
               "FROM   TwoTipoNotaRi tnr " +
                "WHERE UPPER(tnr.organizationCode) LIKE  UPPER( '%' || :busca || '%') " +
                "OR  UPPER(tnr.invoiceTypeCode) LIKE  UPPER( '%' || :busca || '%') " +
                "OR  UPPER(tnr.invoiceTypeLookupCode) LIKE  UPPER( '%' || :busca || '%') " +
                "OR  UPPER(tnr.transferType) LIKE  UPPER( '%' || :busca || '%')  "
    )
    public List<TwoTipoNotaRi> getTipoNotaRiPorNome(@Param("busca") String buscar);
}
