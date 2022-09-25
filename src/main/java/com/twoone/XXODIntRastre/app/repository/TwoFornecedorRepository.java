package com.twoone.XXODIntRastre.app.repository;

import com.twoone.XXODIntRastre.app.entities.TwoFornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TwoFornecedorRepository extends JpaRepository<TwoFornecedor,Long> {

    @Query(
            value = "SELECT fornId " +
                    "FROM  TwoFornecedor " +
                    "WHERE identificador = :identificador"
    )
    public Long getFornIdIndex(@Param("identificador")String identificador);
//
    @Query(
            value = "SELECT f " +
                    "FROM  TwoFornecedor f " +
                    "WHERE fornId = :fornId"
    )
    public TwoFornecedor getFornecedor(@Param("fornId") Long fornId);
//
    @Query(
            value = "SELECT f " +
                    "FROM TwoFornecedor f " +
                    "WHERE UPPER(f.nome) LIKE  UPPER( '%' || :nome || '%')"
    )
    public List<TwoFornecedor> getPorNome(@Param("nome") String nome);




}
