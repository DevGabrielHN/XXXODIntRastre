package com.twoone.XXODIntRastre.app.repository;

import com.twoone.XXODIntRastre.app.entities.TwoUnidadeMedidaTmp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TwoUnidadeMedidaTmpRepository extends JpaRepository<TwoUnidadeMedidaTmp,Long> {

    @Query(
        value = "SELECT cpt " +
                "                        FROM TwoUnidadeMedidaTmp cpt " +
                "                       WHERE cpt.loteId.id = :loteId " +
                "                         AND cpt.status = 'NEW'"
    )
    public List<TwoUnidadeMedidaTmp> getUnidadadeMedTmp(@Param("loteId") Long loteId);

}
