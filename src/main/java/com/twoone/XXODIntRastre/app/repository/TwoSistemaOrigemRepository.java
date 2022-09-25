package com.twoone.XXODIntRastre.app.repository;

import com.twoone.XXODIntRastre.app.entities.TwoSistemaOrigem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TwoSistemaOrigemRepository extends JpaRepository<TwoSistemaOrigem,Long> {

    @Query(
            value = "SELECT so " +
                    "FROM TwoSistemaOrigem so " +
                    "WHERE so.sistemaId = :sistemaId"
    )
    public TwoSistemaOrigem getSistemaOrigem(@Param("sistemaId") Long sistemaId);

}
