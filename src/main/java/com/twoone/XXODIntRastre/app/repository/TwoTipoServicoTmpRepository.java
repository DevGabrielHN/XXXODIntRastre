package com.twoone.XXODIntRastre.app.repository;

import com.twoone.XXODIntRastre.app.entities.TwoTipoServicoTmp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TwoTipoServicoTmpRepository extends JpaRepository<TwoTipoServicoTmp,Long> {

    @Query(
        value="SELECT tst " +
                "FROM TwoTipoServicoTmp tst " +
                "WHERE tst.loteId.id = :loteId " +
                "AND tst.status = 'NEW' "
    )
    public List<TwoTipoServicoTmp> getTipoServicoTmp(@Param("loteId") Long loteId);

}
