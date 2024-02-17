package com.codigo.ms.domain.impl;

import com.codigo.ms.domain.agregates.dto.PersonDto;
import com.codigo.ms.domain.agregates.request.RequestPersona;
import com.codigo.ms.domain.ports.in.PersonaServiceIn;
import com.codigo.ms.domain.ports.out.PersonaServiceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonaServiceIn {

    private final PersonaServiceOut personaServiceOut;
    @Override
    public PersonDto createPersonIn(RequestPersona requestPersona) {
        return personaServiceOut.createPersonOut(requestPersona);
    }

    @Override
    public Optional<PersonDto> getPersonIn(String numDocu) {
        return personaServiceOut.getPersonOut(numDocu);
    }

    @Override
    public List<PersonDto> getAllPersonsIn() {
        return personaServiceOut.getAllPersonsOut();
    }

    @Override
    public List<PersonDto> getPersonsByStateIn(Integer estado) {
        return personaServiceOut.getPersonsByStateOut(estado);
    }

    @Override
    public PersonDto updatePersonIn(Long id, PersonDto personDto) {
        return personaServiceOut.updatePersonOut(id, personDto);
    }

    @Override
    public PersonDto deletePersonIn(Long id) {
        return personaServiceOut.deletePersonOut(id);
    }
}
