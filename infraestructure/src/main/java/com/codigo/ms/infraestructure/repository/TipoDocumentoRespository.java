package com.codigo.ms.infraestructure.repository;

import com.codigo.ms.infraestructure.entity.TipoDocumentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoDocumentoRespository extends JpaRepository<TipoDocumentoEntity, Long> {
    TipoDocumentoEntity findByCodTipo(@Param("codTipo") String codTipo);

}
