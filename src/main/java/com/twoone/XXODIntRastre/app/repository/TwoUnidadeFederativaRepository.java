package com.twoone.XXODIntRastre.app.repository;

import com.twoone.XXODIntRastre.app.entities.TwoUnidadeFederativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface TwoUnidadeFederativaRepository extends JpaRepository<TwoUnidadeFederativa,Long> {

    @Query(
            value = "SELECT ufId " +
                    "FROM TwoUnidadeFederativa " +
                    "WHERE nome = :nome " + // String
                    "AND PAIS_ID = :paisId " + // Long
                    "AND sigla = :sigla" // String
    )
    public Long indexUf(@Param("nome") String nome,
                        @Param("paisId") Long paisId,
                        @Param("sigla") String sigla);
//
    @Query(
            value="SELECT usuarioIdCriacao " +
                    "FROM TwoUnidadeFederativa " +
                    "WHERE ufId = :ufId"
        )

    public String getUsuarioIdCriacao(@Param("ufId") Long ufId);
//
    @Query(
            value="SELECT dtHrCriacao " +
                    "FROM TwoUnidadeFederativa " +
                    "WHERE ufId = :ufId"
    )
    public Timestamp getDtHrCriacao(@Param("ufId") Long ufId);


}
