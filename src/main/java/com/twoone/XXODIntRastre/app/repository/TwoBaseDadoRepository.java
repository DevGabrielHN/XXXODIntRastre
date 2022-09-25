package com.twoone.XXODIntRastre.app.repository;

import com.twoone.XXODIntRastre.app.entities.TwoBaseDado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface TwoBaseDadoRepository extends JpaRepository<TwoBaseDado,Long> {

    @Query(
            value = "SELECT bd " +
                    "          FROM TwoBaseDado bd " +
                    "         WHERE nome = :base"
    )
    public TwoBaseDado lBaseId(@Param("base") String base);
//


}
