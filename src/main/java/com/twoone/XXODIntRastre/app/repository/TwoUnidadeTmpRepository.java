package com.twoone.XXODIntRastre.app.repository;

import com.twoone.XXODIntRastre.app.entities.TwoUnidade;
import com.twoone.XXODIntRastre.app.entities.TwoUnidadeTmp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TwoUnidadeTmpRepository extends JpaRepository<TwoUnidadeTmp,Long> {

    // Query para buscar dados para possivel insert na tabela principal.
    @Query(
            value = "SELECT ut " +
                    "                         FROM TwoUnidadeTmp ut" +
                    "                        WHERE loteId.id = :loteId" +
                    "                          AND status = 'NEW'"
    )
    public List<TwoUnidadeTmp> getUnidadeTmp(@Param("loteId") Long loteId);



}