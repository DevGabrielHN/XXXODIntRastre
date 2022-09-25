package com.twoone.XXODIntRastre.app.repository;

import com.twoone.XXODIntRastre.app.entities.TwoFornecedorTmp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TwoFornecedorTmpRepository extends JpaRepository<TwoFornecedorTmp,Long> {

////// QUERYS USADAS NA FUNÇÃO "validaFornecedor" DA CLASSE TwoProcessaFornecedorService
    @Query(
        value = "SELECT ft " +
                "                     FROM TwoFornecedorTmp ft " +
                "                     WHERE ft.loteId.id = :loteId " + // subsitituido pelo instance_id
                "                     AND ft.status = 'NEW' "/* +
                "                     GROUP BY ft.nome " +
                "                            , ft.tpIdentId " +
                "                            , ft.identificador " +
                "                            , ft.usuario " +
                "                            , ft.base"*/
    )
    public List<TwoFornecedorTmp> cursorValidaTmp1(@Param("loteId") Long loteId);
//
    @Query(
            value="SELECT ft " +
                    "                               FROM TwoFornecedorTmp ft " +
                    "                              WHERE ft.loteId.id = :loteId " + // subsitituido pelo instance_id
                    "                                AND ft.identificador = :identificador "  +
                    "                                AND ft.siteCod IS NOT NULL "/* +
                    "                             GROUP BY ft.tpIdentId " +
                    "                                    , ft.raiz " +
                    "                                    , ft.filial " +
                    "                                    , ft.digitoVerificador " +
                    "                                    , ft.siteCod " +
                    "                                    , ft.endereco " +
                    "                                    , ft.numero " +
                    "                                    , ft.complemento " +
                    "                                    , ft.bairro " +
                    "                                    , TRIM(ft.cidade) " +
                    "                                    , TRIM(ft.codCidade) " +
                    "                                    , TRIM(ft.estado) " +
                    "                                    , TRIM(ft.codEstado) " +
                    "                                    , TRIM(ft.pais) " +
                    "                                    , TRIM(ft.codPais) " +
                    "                                    , ft.email " +
                    "                                    , ft.indDesoneracao " +
                    "                                    , ft.usuario"*/
    )
    public List<TwoFornecedorTmp> cursorValidaTmp2(@Param("loteId") Long loteId,
                                                   @Param("identificador") String identificador);
//
    @Query(
            value = "SELECT ft " +
                    "FROM TwoFornecedorTmp ft "  +
                    "WHERE ft.loteId.id = :loteId "  + // subsitituido pelo instance_id
                    "AND ft.identificador = :identificador " +
                    "AND ft.siteCod = :siteCod " +
                    "AND ft.indConta = 'S' " /* + // quando existe conta bancaria no site do fornecedor
                    "GROUP BY ft.codBanco " +
                    ", ft.nomeBanco " +
                    ", ft.codAgencia " +
                    ", ft.nomeAgencia " +
                    ", ft.codConta " +
                    ", ft.digVerifConta " +
                    ", ft.nivelConta " +
                    ", ft.dtFinal " +
                    ", ft.unidade"*/
    )
    public List<TwoFornecedorTmp> cursorValidaTmp3(@Param("loteId") Long loteId,
                                                   @Param("identificador") String identificador,
                                                   @Param("siteCod") String siteCod);
//
    @Transactional
    @Modifying
    @Query(
        value=" UPDATE TwoFornecedorTmp " +
                "SET unidadeId = :lUnidadeId " +
                ", statusConta = 'SUCCESS' " +
                ", msgConta = NULL " +
                "WHERE identificador = :identificador " +
                "AND siteCod = :siteCod " +
                "AND fornTmpId = :fornTmpId " + ///// VERIFICAR (ID ADICIONADO)
              /*  "AND COALESCE(codBanco, '-9999') = COALESCE(:codBanco, '-9999') " +
                "AND COALESCE(codAgencia, '-9999') = COALESCE(:codAgencia, '-9999') " +
                "AND COALESCE(codConta, '-9999') = COALESCE(:codConta, '-9999') " +
                "AND COALESCE(unidade, '-9999') = COALESCE(:unidade , '-9999') " +
                "AND COALESCE(digVerifConta, '-9999') = COALESCE(:digVerifConta, '-9999') " + */
                "AND loteId.id = :loteId "  + // subsitituido pelo instance_id
                "AND (nivelConta = :nivelConta OR nivelConta IS NULL)" // VERIFICAR ESSE "OR nivelConta IS NULL"
    )
    public void atuFornecedorSuccess(@Param("lUnidadeId") Long lUnidadeId,
                              @Param("identificador") String identificador,
                              @Param("siteCod") String siteCod,
                              @Param("fornTmpId") Long fornTmpId,
                            /*  @Param("codBanco") String codBanco,
                              @Param("codAgencia") String codAgencia,
                              @Param("codConta") String codConta,
                              @Param("unidade") String unidade,
                              @Param("digVerifConta") String digVerifConta, */
                              @Param("loteId") Long loteId,
                              @Param("nivelConta") String nivelConta);

//
    @Transactional
    @Modifying
    @Query(
            value="UPDATE TwoFornecedorTmp " +
                    "SET indConta = 'N' " +
                    ", msgConta = :msgConta " +
                    ", statusConta = :statusConta " +
                    "WHERE identificador = :identificador " +
                    "AND siteCod = :siteCod " +
                    "AND fornTmpId = :fornTmpId " + ///// VERIFICAR (ID ADICIONADO)
                   /* "AND COALESCE(codBanco, '-9999') = COALESCE(:codBanco, '-9999') " +
                    "AND COALESCE(codAgencia, '-9999') = COALESCE(:codAgencia, '-9999') " +
                    "AND COALESCE(codConta, '-9999') = COALESCE(:codConta, '-9999') " +
                    "AND COALESCE(unidade, '-9999') = COALESCE(:unidade , '-9999') " +
                    "AND COALESCE(digVerifConta, '-9999') = COALESCE(:digVerifConta, '-9999') " +*/
                    "AND loteId.id = :loteId "  + // subsitituido pelo instance_id
                    "AND (nivelConta = :nivelConta OR nivelConta IS NULL)" // VERIFICAR ESSE "OR nivelConta IS NULL"
    )
    public void atuFornecedorN(@Param("msgConta") String msgConta,
                               @Param("statusConta") String statusConta,
                               @Param("identificador") String identificador,
                               @Param("siteCod") String siteCod,
                               @Param("fornTmpId") Long fornTmpId,
                             /*  @Param("codBanco") String codBanco,
                               @Param("codAgencia") String codAgencia,
                               @Param("codConta") String codConta,
                               @Param("unidade") String unidade,
                               @Param("digVerifConta") String digVerifConta,*/
                               @Param("loteId") Long loteId,
                               @Param("nivelConta") String nivelConta);
//
    @Transactional
    @Modifying
    @Query(
        value="UPDATE TwoFornecedorTmp " +
                "SET cidId = :cidId " +
                "WHERE identificador = :identificador " +
                "AND siteCod = :siteCod " +
                "AND loteId.id = :loteId" // subsitituido pelo instance_id
    )
    public void atuCidIdFornecedor(@Param("cidId") Long cidId,
                                   @Param("identificador") String identificador,
                                   @Param("siteCod") String siteCod,
                                   @Param("loteId") Long loteId);
//
    @Transactional
    @Modifying
    @Query(
            value = "UPDATE TwoFornecedorTmp " +
                    "           SET status = 'IN PROCESS' " +
                    "             , msg = 'Fornecedor validado com sucesso' " +
                    "             , baseId = :baseId " +
                    "             , sistemaId = :sistemaId " +
                    "         WHERE identificador = :identificador " +
                    "           AND loteId.id = :loteId" // subsitituido pelo instance_id
    )

    public void atuFornecedorTmp(@Param("baseId") Long baseId,
                                 @Param("sistemaId") Long sistemaId,
                                 @Param("identificador") String identificador,
                                 @Param("loteId") Long loteId);
//
    @Transactional
    @Modifying
    @Query(
            value= " UPDATE TwoFornecedorTmp " +
                    "SET status = :status " +
                    ", msg = :msg " +
                    "WHERE (identificador = :identificador OR identificador IS NULL) " +
                    "AND loteId.id = :loteId" // subsitituido pelo instance_id
    )
    public void atuFornecedorTmp2(@Param("status") String status,
                                  @Param("msg") String msg,
                                  @Param("identificador") String identificador,
                                  @Param("loteId") Long loteId);
////// QUERYS USADAS NA FUNÇÃO "postFornecedor" DA CLASSE TwoProcessaFornecedorService

    @Query(
            value = "SELECT ft " +
                    "FROM TwoFornecedorTmp ft " +
                    "WHERE loteId.id = :loteId  " + // subsitituido pelo instance_id
                    "AND ft.status = 'IN PROCESS' " /* +
                    "GROUP BY ft.nome\n" +
                    ", ft.tp_ident_id\n" +
                    ", ft.identificador\n" +
                    ", ft.usuario\n" +
                    ", ft.base_id\n" +
                    ", ft.sistema_id"*/
    )
    public List<TwoFornecedorTmp> cursor1( @Param("loteId") Long loteId);
//
    @Query(
            value = "SELECT ft " +
                    "                             FROM TwoFornecedorTmp ft " +
                    "                            WHERE ft.loteId.id = :loteId " + // subsitituido pelo instance_id
                    "                              AND ft.identificador = :identificador " +
                    "                              AND ft.status = 'IN PROCESS' " +
                    "                              AND ft.siteCod IS NOT NULL " /* +
                    "                           GROUP BY ft.raiz " +
                    "                                  , ft.filial " +
                    "                                  , ft.digito_verificador " +
                    "                                  , ft.site_cod " +
                    "                                  , ft.cidade " +
                    "                                  , ft.endereco " +
                    "                                  , ft.numero " +
                    "                                  , ft.complemento " +
                    "                                  , ft.bairro " +
                    "                                  , ft.cid_id " +
                    "                                  , ft.email " +
                    "                                  , ft.ind_desoneracao " +
                    "                                  , ft.usuario " +
                    "                                  , ft.sistema_id " +
                    "                                  , tp_ident_id " +
                    "                               -- , ft.responsibility_name " +
                    "                               -- , ft.user_name " +
                    "                           ORDER BY ft.site_cod, NVL(ft.cidade, 'A'), NVL(ft.endereco, 'A')" */
    )
    public  List<TwoFornecedorTmp> cursor2(@Param("loteId") Long loteId,
                                           @Param("identificador") String identificador);
    //
    @Query(
        value = "SELECT ft " +
                "                                FROM TwoFornecedorTmp ft " +
                "                               WHERE ft.loteId.id = :loteId " + // subsitituido pelo instance_id
                "                                 AND ft.identificador = :identificador " +
                "                                 AND ft.siteCod = :siteCod " +
                "                                 AND ft.status = 'IN PROCESS' " +
                "                                 AND ft.indConta = 'S' " + // quando existe conta bancaria no site do fornecedor
                "                                 AND ft.nivelConta = 'SITE' " /* +
                "                               GROUP BY ft.cod_banco " +
                "                                      , ft.nome_banco " +
                "                                      , ft.cod_agencia " +
                "                                      , ft.nome_agencia " +
                "                                      , ft.cod_conta " +
                "                                      , ft.dig_verif_conta " +
                "                                      , ft.dt_final " +
                "                                      , ft.unidade_id " +
                "                                      , ft.sistema_id " +
                "                                      , ft.base_id " +
                "                                      , ft.usuario " */
    )
    public List<TwoFornecedorTmp> cursor3(@Param("loteId") Long loteId,
                                          @Param("identificador") String identificador,
                                          @Param("siteCod") String siteCod);
    //

    @Query(
            value = "SELECT ft " +
                    "                              FROM TwoFornecedorTmp ft " +
                    "                              WHERE ft.loteId.id = :loteId " + // subsitituido pelo instance_id
                    "                              AND ft.identificador = :identificador " +
              /*      "                               --AND ft.site_cod = r_frne_site.site_cod " + */
                    "                               AND ft.status = 'IN PROCESS' " +
                    "                               AND ft.indConta = 'S' " + // quando existe conta bancaria no site do fornecedor
                    "                               AND ft.nivelConta = 'HEADER' " /* +
                    "                             GROUP BY ft.cod_banco " +
                    "                                    , ft.nome_banco " +
                    "                                    , ft.cod_agencia " +
                    "                                    , ft.nome_agencia " +
                    "                                    , ft.cod_conta " +
                    "                                    , ft.dig_verif_conta " +
                    "                                    , ft.dt_final " +
                    "                                    , ft.unidade_id " +
                    "                                    , ft.sistema_id " +
                    "                                    , ft.base_id " +
                    "                                    , ft.usuario" */
    )
    public List<TwoFornecedorTmp> cursor4(@Param("loteId") Long loteId,
                                          @Param("identificador") String identificador);
}
