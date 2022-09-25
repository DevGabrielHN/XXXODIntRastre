package com.twoone.XXODIntRastre.app.repository;

import com.twoone.XXODIntRastre.app.entities.TwoCidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface TwoCidadeRepository extends JpaRepository<TwoCidade,Long> {

    @Query(
            value = "SELECT cidId " +
                    "FROM TwoCidade " +
                    "WHERE nome = :nome " +
                    "AND UF_ID = :ufId"
    )
    public Long indexCidade(@Param("nome") String nome,
                            @Param("ufId") Long ufId);

//
    @Query(
            value="SELECT usuarioIdCriacao " +
                    "FROM TwoCidade " +
                    "WHERE cidId = :cidId"
    )

    public String getUsuarioIdCriacao(@Param("cidId") Long cidId);
//
    @Query(
            value="SELECT dtHrCriacao " +
                    "FROM TwoCidade " +
                    "WHERE cidId = :cidId"
    )
    public Timestamp getDtHrCriacao(@Param("cidId") Long cidId);
//
    @Query(
        value="SELECT cid.cidId " +
                "FROM TwoCidade cid " +
                ", TwoUnidadeFederativa uf " +
                ", TwoPais pais "  +
                "WHERE cid.nome = TRANSLATE( UPPER(:cidade),  'ÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÄËÏÖÜÃÕÇÑ','AEIOUAEIOUAEIOUAEIOUAOCN') " +
                "AND uf.nome = TRANSLATE( UPPER(:lEstado), 'ÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÄËÏÖÜÃÕÇÑ','AEIOUAEIOUAEIOUAEIOUAOCN') " +
                "AND pais.nome = TRANSLATE( UPPER(:pais),  'ÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÄËÏÖÜÃÕÇÑ','AEIOUAEIOUAEIOUAEIOUAOCN') " +
                "AND uf.ufId = cid.ufId " +
                "AND pais.paisId = uf.paisId"
    )
    public Long getLCidId(@Param("cidade") String cidade,
                          @Param("lEstado") String lEstado,
                          @Param("pais") String pais);
    //
    @Query(
            value="SELECT cid.cidId " +
                    "FROM TwoCidade cid " +
                    ", TwoUnidadeFederativa uf " +
                    ", TwoPais pais "  +
                    "WHERE cid.nome = TRANSLATE( UPPER(:cidade),  'ÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÄËÏÖÜÃÕÇÑ','AEIOUAEIOUAEIOUAEIOUAOCN') " +
                    "AND uf.nome = TRANSLATE( UPPER(:lEstado), 'ÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÄËÏÖÜÃÕÇÑ','AEIOUAEIOUAEIOUAEIOUAOCN') " +
                    "AND uf.sigla  = TRANSLATE( UPPER(:codEstado), 'ÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÄËÏÖÜÃÕÇÑ','AEIOUAEIOUAEIOUAEIOUAOCN') " +
                    "AND pais.nome = TRANSLATE( UPPER(:pais),  'ÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÄËÏÖÜÃÕÇÑ','AEIOUAEIOUAEIOUAEIOUAOCN') " +
                    "AND uf.ufId = cid.ufId " +
                    "AND pais.paisId = uf.paisId"
    )
    public Long getLCidId2(@Param("cidade") String cidade,
                           @Param("lEstado") String lEstado,
                           @Param("codEstado") String codEstado,
                           @Param("pais") String pais);
    //

    @Query(
        value = "SELECT cid " +
                "FROM TwoCidade cid " +
                "WHERE cid.cidId = :cidId"
    )
    public TwoCidade getCidade(@Param("cidId") Long cidId);

}
