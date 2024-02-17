package com.codigo.ms.infraestructure.mapper;

import com.codigo.ms.domain.agregates.dto.PersonDto;
import com.codigo.ms.infraestructure.entity.PersonaEntity;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;


@Service
public class PersonaMapper {
    private static final ModelMapper modelMapper= new ModelMapper();
    public PersonDto mapToDto(PersonaEntity entity){
        return  modelMapper.map(entity,PersonDto.class);
    }

    public PersonaEntity mapToEntity(PersonDto personDto){
        return  modelMapper.map(personDto,PersonaEntity.class);
    }
}
