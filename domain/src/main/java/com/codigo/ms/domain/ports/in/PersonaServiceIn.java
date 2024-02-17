package com.codigo.ms.domain.ports.in;

import com.codigo.ms.domain.agregates.dto.PersonDto;
import com.codigo.ms.domain.agregates.request.RequestPersona;

import java.util.List;
import java.util.Optional;

public interface PersonaServiceIn {
    PersonDto createPersonIn(RequestPersona requestPersona);
    Optional<PersonDto> getPersonIn(String numDocu);
    List<PersonDto> getAllPersonsIn();
    List<PersonDto> getPersonsByStateIn(Integer estado);
    PersonDto updatePersonIn(Long id, PersonDto personDto);

    PersonDto deletePersonIn(Long id);

}
