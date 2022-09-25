package com.twoone.XXODIntRastre.app.repository;

import com.twoone.XXODIntRastre.app.entities.TwoPais;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface TwoPaisRepository extends JpaRepository<TwoPais,Long> {


    @Query(
       value = "SELECT pais.paisId " +
               "FROM TwoPais pais " +
               "WHERE pais.nome = TRIM(:nome) " +
               "AND pais.sigla = TRIM(:sigla)"
    )
    public Long indexPais(@Param("nome") String nome,
                          @Param("sigla") String sigla);
//
    @Query(
            value="SELECT usuarioIdCriacao " +
                    "FROM TwoPais " +
                    "WHERE paisId = :paisId"
    )

    public String getUsuarioIdCriacao(@Param("paisId") Long paisId);
//
    @Query(
            value="SELECT dtHrCriacao " +
                    "FROM TwoPais " +
                    "WHERE paisId = :paisId"
    )
    public Timestamp getDtHrCriacao(@Param("paisId") Long paisId);
//
}
