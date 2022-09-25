package com.twoone.XXODIntRastre.app.repository;

import com.twoone.XXODIntRastre.app.entities.TwoBaseDado;
import com.twoone.XXODIntRastre.app.entities.TwoSistemaOrigem;
import com.twoone.XXODIntRastre.app.entities.TwoUnidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface TwoUnidadeRepository extends JpaRepository<TwoUnidade,Long> {


    @Query(
            value = "  SELECT unidadeId " +
                    "            FROM TwoUnidade " +
                    "           WHERE cod = :uoDessaOi " +
                    "             AND tipo IN ('UO', 'UE') " +
                    "             AND baseId.baseId = :lBaseId"
            )
    public Long lUnidadeIdUo(@Param("uoDessaOi") String uoDessaOi,
                                   @Param("lBaseId") Long lBaseId);
//
    @Query(
            value= "SELECT xdpd.destino01 " +
                    "                FROM XxodDePara xdp " +
                    "                   , XxodDeParaDetail xdpd " +
                    "               WHERE xdp.referencia = 'RASTRE_UF' " +
                    "                 AND xdp.nome = 'UF' " +
                    "                 AND xdpd.idDePara = xdp.idDePara " +
                    "                 AND xdpd.origem01 = TRANSLATE( UPPER(:codPais), 'ÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÄËÏÖÜÃÕÇÑ','AEIOUAEIOUAEIOUAEIOUAOCN') " + //cod_pais
                    "                 AND xdpd.origem02 = TRANSLATE( UPPER(:codEstado), 'ÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÄËÏÖÜÃÕÇÑ','AEIOUAEIOUAEIOUAEIOUAOCN') " // cod_estado
    )
    public String lEstado(@Param("codPais") String codPais,
                          @Param("codEstado") String codEstado);
//

    @Query(
            value = "SELECT cid.cidId " +
                    "              FROM TwoCidade cid " +
                    "                 , TwoUnidadeFederativa uf " +
                    "                 , TwoPais pais " +
                    "             WHERE cid.nome = TRIM(TRANSLATE( UPPER(:cidade), 'ÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÄËÏÖÜÃÕÇÑ','AEIOUAEIOUAEIOUAEIOUAOCN')) " +
                    "               AND uf.nome = TRIM(TRANSLATE( UPPER(:lEstado), 'ÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÄËÏÖÜÃÕÇÑ','AEIOUAEIOUAEIOUAEIOUAOCN')) " +
                    "               AND pais.nome = TRIM(TRANSLATE( UPPER(:pais), 'ÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÄËÏÖÜÃÕÇÑ','AEIOUAEIOUAEIOUAEIOUAOCN')) " +
                    "               AND uf.ufId = cid.ufId  " +
                    "               AND pais.paisId = uf.paisId"
    )
    public Long lCidId(@Param("cidade") String cidade,
                       @Param("lEstado") String lEstado,
                       @Param("pais") String pais);
//
    @Query(
            value = "SELECT unidadeId " +
                    "FROM TwoUnidade " +
                    "WHERE cod = :cod " +
                    "AND tipo = :tipo " +
                    "AND base_Id = :baseId"
    )
    public Long getIndex(@Param("cod") String cod,
                         @Param("tipo") String tipo,
                         @Param("baseId") Long baseId);
//
    @Query(
            value="SELECT usuarioIdCriacao " +
                  "FROM TwoUnidade " +
                  "WHERE unidadeId = :unidadeId"
    )

    public String getUsuarioIdCriacao(@Param("unidadeId") Long unidadeId);
//
    @Query(
            value="SELECT dtHrCriacao " +
                    "FROM TwoUnidade " +
                    "WHERE unidadeId = :unidadeId"
        )

    public Timestamp getDtHrCriacao(@Param("unidadeId") Long unidadeId);
    //
    @Query(
            value = "SELECT  u " +
                    "FROM TwoUnidade u " +
                    "WHERE u.unidadeId = :unidadeId"
    )
    public TwoUnidade getUnidade(@Param("unidadeId") Long unidadeId);
    //
    @Query(
        value = "SELECT u " +
                "FROM TwoUnidade u " +
                "WHERE UPPER(u.cod) LIKE UPPER('%' || :busca || '%') " +
                "OR  UPPER(u.descr) LIKE UPPER('%' || :busca || '%') " +
                "OR  UPPER(u.identificador) LIKE UPPER('%' || :busca || '%')"
    )
    public List<TwoUnidade> getUnidadePorBusca(@Param("busca") String busca);

}
