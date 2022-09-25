package com.twoone.XXODIntRastre.app.repository;


import com.twoone.XXODIntRastre.app.entities.TwoLotesUnidades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TwoLoteUnidadeRepository extends JpaRepository<TwoLotesUnidades,Long> {

}
