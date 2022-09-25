package com.twoone.XXODIntRastre.app.repository;

import com.twoone.XXODIntRastre.app.entities.TwoTipoServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TwoTipoServicoRepository extends JpaRepository<TwoTipoServico,Long> {

    @Query(
            value = "SELECT tpServId " +
                    "FROM TwoTipoServico " +
                    "WHERE cod = :cod " +
                    "AND origem = :origem"
    )
    public Long getIndex(@Param("cod") String cod,
                         @Param("origem") String origem);
//
    @Query(
            value = "SELECT ts " +
                    "FROM TwoTipoServico ts " +
                    "WHERE ts.tpServId = :tpServId"
    )
    public TwoTipoServico getAll(@Param("tpServId") Long tpServId);
//
    @Query(
            value = "SELECT ts " +
                    "FROM TwoTipoServico ts " +
                    "WHERE UPPER(ts.descr) LIKE UPPER('%' || :busca || '%') "
    )
    public List<TwoTipoServico> getTipoServicoPorBusca(String busca);
}
