package com.codigo.ms.infraestructure.repository;

import com.codigo.ms.infraestructure.entity.TipoPersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPersonaRepository extends JpaRepository<TipoPersonaEntity, Long> {
    TipoPersonaEntity findByCodTipo( @Param("codTipo") String codTipo);

}
