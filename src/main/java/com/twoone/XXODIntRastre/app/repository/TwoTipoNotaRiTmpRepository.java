package com.twoone.XXODIntRastre.app.repository;

import com.twoone.XXODIntRastre.app.entities.TwoTipoNotaRiTmp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TwoTipoNotaRiTmpRepository extends JpaRepository<TwoTipoNotaRiTmp,Long> {

    @Query(
            value = "SELECT tnr " +
                    "FROM TwoTipoNotaRiTmp tnr " +
                    "WHERE tnr.loteId.id = :loteId " +
                    "AND tnr.status = 'NEW'"
    )
    public List<TwoTipoNotaRiTmp> r_tp_nota(@Param("loteId")Long loteId);
    //


}
