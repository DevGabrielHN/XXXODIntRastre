package com.twoone.XXODIntRastre.app.repository;

import com.twoone.XXODIntRastre.app.entities.TwoFornecedorContaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TwoFornecedorContaBancariaRepository extends JpaRepository<TwoFornecedorContaBancaria,Long> {

    @Query(
            value = "SELECT fornCtaBcoId " +
                    "FROM TwoFornecedorContaBancaria " +
                    "WHERE codBanco = :codBanco " +
                    "AND codAgencia = :codAgencia " +
                    "AND codConta = :codConta " +
                    "AND (COALESCE(digVerifConta, '-99999') =  '-99999' OR COALESCE(digVerifConta, '-99999') = :digVerifConta)" +
                    //"COALESCE(:digVerifConta, '-99999') " +
                    "AND FORN_SITE_ID = :fornSiteId " +
                    "AND FORN_ID = :fornId " +
                    "AND (COALESCE(UNIDADE_ID, -99999) = -99999 OR COALESCE(UNIDADE_ID, -99999) = :unidadeId)" +
                   // " COALESCE(:unidadeId, -99999) " + // TESTAR ESSA SUBSTITUIÇÃO
                    "AND baseId = :baseId"
    )
    public Long fornCtaBcoIdIndex(@Param("codBanco") String codBanco,
                                  @Param("codAgencia") String codAgencia,
                                  @Param("codConta") String codConta,
                                  @Param("digVerifConta") String digVerifConta,
                                  @Param("fornSiteId") Long fornSiteId,
                                  @Param("fornId") Long fornId,
                                  @Param("unidadeId") Long unidadeId,
                                  @Param("baseId") Long baseId);
    //
    @Query(
            value = "SELECT fcb " +
                    "FROM TwoFornecedorContaBancaria fcb " +
                    "WHERE fcb.fornCtaBcoId = :fornCtaBcoId"
    )
    public TwoFornecedorContaBancaria getFornecedorContaBancaria(@Param("fornCtaBcoId") Long fornCtaBcoId);
    //

    @Query(
            value = "SELECT fornCtaBcoId " +
                    "FROM TwoFornecedorContaBancaria " +
                    "WHERE codBanco = :codBanco " +
                    "AND codAgencia = :codAgencia " +
                    "AND codConta = :codConta " +
                    "AND (COALESCE(digVerifConta, '-99999') = '-99999' OR COALESCE(digVerifConta, '-99999') = :digVerifConta)" +
                    "AND FORN_SITE_ID IS NULL " +
                    "AND FORN_ID = :fornId " +
                    "AND UNIDADE_ID IS NULL " +
                    "AND baseId = :baseId"
    )
    public Long fornCtaBcoIdIndex2(@Param("codBanco") String codBanco,
                                   @Param("codAgencia") String codAgencia,
                                   @Param("codConta") String codConta,
                                   @Param("digVerifConta") String digVerifConta,
                                   @Param("fornId") Long fornId,
                                   @Param("baseId") Long baseId);
}
