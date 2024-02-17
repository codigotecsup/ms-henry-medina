package com.codigo.ms.domain.ports.out;

import com.codigo.ms.domain.agregates.dto.PersonDto;
import com.codigo.ms.domain.agregates.request.RequestPersona;

import java.util.List;
import java.util.Optional;

public interface PersonaServiceOut {
    PersonDto createPersonOut(RequestPersona requestPersona);
    Optional<PersonDto> getPersonOut(String numDocu);
    List<PersonDto> getAllPersonsOut();
    List<PersonDto> getPersonsByStateOut(Integer estado);
    PersonDto updatePersonOut(Long id, PersonDto personDto);

    PersonDto deletePersonOut(Long id);
}
