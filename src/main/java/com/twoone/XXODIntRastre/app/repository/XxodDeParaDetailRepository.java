package com.twoone.XXODIntRastre.app.repository;

import com.twoone.XXODIntRastre.app.entities.XxodDeParaDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface XxodDeParaDetailRepository extends JpaRepository<XxodDeParaDetail,Long> {


    @Query(
            value = " SELECT xdpd.destino01 " +
                    "          FROM XxodDePara xdp " +
                    "             , XxodDeParaDetail xdpd " +
                    "         WHERE xdp.referencia = 'RASTRE_UF' " +
                    "           AND xdp.nome = 'UF' " +
                    "           AND xdpd.idDePara = xdp.idDePara " +
                    "           AND xdpd.origem01 = :codPais " +
                    "           AND xdpd.origem02 = :codEstado"
    )
    public String getLEstado(@Param("codPais") String codPais,
                             @Param("codEstado") String codEstado);
//
    @Query(
            value = "SELECT xdpd.destino01 " +
                    "FROM XxodDePara xdp " +
                    ",XxodDeParaDetail xdpd " +
                    "WHERE xdp.referencia = 'RASTRE_UF' " +
                    "AND xdp.nome = 'UF' " +
                    "AND xdpd.idDePara = xdp.idDePara " +
                    "AND xdpd.origem01 = TRANSLATE( UPPER(:codPais),   'ÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÄËÏÖÜÃÕÇÑ','AEIOUAEIOUAEIOUAEIOUAOCN') " +
                    "AND xdpd.origem02 = TRANSLATE( UPPER(:codEstado), 'ÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÄËÏÖÜÃÕÇÑ','AEIOUAEIOUAEIOUAEIOUAOCN') "
    )
    public String getLEstado2(@Param("codPais") String codPais,
                              @Param("codEstado") String codEstado);

}
