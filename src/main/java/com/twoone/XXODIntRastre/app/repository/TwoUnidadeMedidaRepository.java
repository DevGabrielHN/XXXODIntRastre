package com.twoone.XXODIntRastre.app.repository;

import com.twoone.XXODIntRastre.app.entities.TwoUnidadeMedida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface TwoUnidadeMedidaRepository extends JpaRepository<TwoUnidadeMedida,Long> {


    @Query(
            value = "SELECT unidMedId " +
                    "FROM TwoUnidadeMedida " +
                    "WHERE base_Id = :baseId " +
                    "AND cod = :cod "
    )
    public Long getIndex(@Param("baseId") Long baseId,
                         @Param("cod") String cod);
//
    @Query(
            value="SELECT usuarioIdCriacao " +
                    "FROM TwoUnidadeMedida " +
                    "WHERE unidMedId = :unidMedId"
    )

    public String getUsuarioIdCriacao(@Param("unidMedId") Long unidMedId);
//
        @Query(
                value="SELECT dtHrCriacao " +
                        "FROM TwoUnidadeMedida " +
                        "WHERE unidMedId = :unidMedId"
        )

        public Timestamp getDtHrCriacao(@Param("unidMedId") Long unidMedId);
//
        @Query(
                value="SELECT um " +
                        "FROM TwoUnidadeMedida um " +
                        "WHERE  (UPPER(um.descr) LIKE  UPPER( '%' || :busca || '%')" +
                        "OR UPPER(um.cod) LIKE  UPPER( '%' || :busca || '%') ) "
        )
        public List<TwoUnidadeMedida> getPorDescr(@Param("busca") String busca);

//
}
