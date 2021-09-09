package br.com.axellbrendow.diospringrestapijaxrs.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.axellbrendow.diospringrestapijaxrs.entity.SoldierEntity;

@Repository
public interface SoldierRepository extends JpaRepository<SoldierEntity, Long> {
    //
}
