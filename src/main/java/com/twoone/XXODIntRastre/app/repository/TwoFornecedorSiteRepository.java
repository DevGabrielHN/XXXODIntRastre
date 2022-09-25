package com.twoone.XXODIntRastre.app.repository;

import com.twoone.XXODIntRastre.app.entities.TwoFornecedorSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TwoFornecedorSiteRepository extends JpaRepository<TwoFornecedorSite,Long> {

    @Query(
        value = "SELECT fornSiteId " +
                "FROM TwoFornecedorSite " +
                "WHERE FORN_ID = :fornId " +
                "AND siteCod = :siteCod"
    )
    public Long fornSiteIdIndex(@Param("fornId") Long fornId,
                                @Param("siteCod") String siteCod);
    //
    @Query(
        value = "SELECT fs " +
                "FROM TwoFornecedorSite fs " +
                "WHERE fs.fornSiteId = :fornSiteId"
    )
    public  TwoFornecedorSite getFornecedorSite(@Param("fornSiteId") Long fornSiteId);

}
