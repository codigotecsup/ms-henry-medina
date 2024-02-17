package com.codigo.ms.infraestructure.repository;

import com.codigo.ms.infraestructure.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository  extends JpaRepository<PersonaEntity, Long> {
    List<PersonaEntity> findAllByEstado(@Param("estado") Integer estado);
    Optional<PersonaEntity> findByNumDocu(@Param("numDoc") String numDoc);

    boolean existsByNumDocu(@Param("numDoc") String numDoc);

}
