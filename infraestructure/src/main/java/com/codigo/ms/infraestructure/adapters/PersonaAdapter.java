package com.codigo.ms.infraestructure.adapters;

import com.codigo.ms.domain.agregates.constants.Constants;
import com.codigo.ms.domain.agregates.dto.PersonDto;
import com.codigo.ms.domain.agregates.request.RequestPersona;
import com.codigo.ms.domain.agregates.response.ResponseReniec;
import com.codigo.ms.domain.ports.out.PersonaServiceOut;
import com.codigo.ms.infraestructure.entity.PersonaEntity;
import com.codigo.ms.infraestructure.entity.TipoDocumentoEntity;
import com.codigo.ms.infraestructure.entity.TipoPersonaEntity;
import com.codigo.ms.infraestructure.mapper.PersonaMapper;
import com.codigo.ms.infraestructure.repository.PersonRepository;
import com.codigo.ms.infraestructure.repository.TipoDocumentoRespository;
import com.codigo.ms.infraestructure.repository.TipoPersonaRepository;
import com.codigo.ms.infraestructure.rest.client.ClientReniec;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonaAdapter implements PersonaServiceOut {
    private final PersonRepository personRepository;
    private final TipoDocumentoRespository tipoDocumentoRespository;
    private  final TipoPersonaRepository tipoPersonaRepository;
    private  final PersonaMapper personaMapper;
    private final ClientReniec clientReniec;


    @Value("${token.api}")
    private String tokenApi;

    @Override
    public PersonDto createPersonOut(RequestPersona requestPersona) {
        ResponseReniec datosReniec  =getDataFromReniec(requestPersona.getNumDoc());
        personRepository.save(getEntity(datosReniec,requestPersona));
        return personaMapper.mapToDto(getEntity(datosReniec,requestPersona));
    }

    @Override
    public Optional<PersonDto> getPersonOut(String numDocu) {
        return  Optional.ofNullable(personaMapper.mapToDto(personRepository.findByNumDocu(numDocu).get()));

    }

    @Override
    public List<PersonDto> getAllPersonsOut() {
        List<PersonDto> personDtoList = new ArrayList<>();
        List<PersonaEntity> entities = personRepository.findAll();
        for(PersonaEntity persona : entities){
            PersonDto personaDTO = personaMapper.mapToDto(persona);
            personDtoList.add(personaDTO);
        }
        return personDtoList;
    }

    @Override
    public List<PersonDto> getPersonsByStateOut(Integer estado) {
        List<PersonDto> personDtoList = new ArrayList<>();
        List<PersonaEntity> entities = personRepository.findAllByEstado(estado);
        for(PersonaEntity persona : entities){
            PersonDto personaDTO = personaMapper.mapToDto(persona);
            personDtoList.add(personaDTO);
        }
        return personDtoList;
    }

    @Override
    public PersonDto updatePersonOut(Long id, PersonDto personDto) {
        boolean exist = personRepository.existsById(id);
        if(exist){
            Optional<PersonaEntity> entity = personRepository.findById(id);
            if(entity.isPresent()){;
                ResponseReniec responseReniec= getDataFromReniec(entity.get().getNumDocu());
                personRepository.save(getEntityUpdate(responseReniec,personDto,entity.get()));
                return  personaMapper.mapToDto(getEntityUpdate(responseReniec,personDto,entity.get()));
            }
            return null;
        }
        return null;
    }

    @Override
    public PersonDto deletePersonOut(Long id) {
        boolean exist = personRepository.existsById(id);
        if (exist){
            Optional<PersonaEntity> entity = personRepository.findById(id);
            if(entity.isPresent()){
                entity.get().setEstado(0);
                entity.get().setUsuaDelet(Constants.AUDIT_ADMIN);
                entity.get().setDateDelet(getTimestamp());
                personRepository.save(entity.get());
                return personaMapper.mapToDto(entity.get());

            }

            return  null;
        }
        return  null;
    }

    private   ResponseReniec getDataFromReniec(String numDoc){
        String authorization = "Bearer " +tokenApi;
        ResponseReniec responseReniec= clientReniec.getInfoReniec(numDoc,authorization);
        return  responseReniec;

    }

    private PersonaEntity getEntity(ResponseReniec responseReniec, RequestPersona requestPersona){
        TipoDocumentoEntity tipoDocumento= tipoDocumentoRespository.findByCodTipo(requestPersona.getTipoDoc());
        TipoPersonaEntity tipoPersona = tipoPersonaRepository.findByCodTipo(requestPersona.getTipoPer());
        PersonaEntity entity = new PersonaEntity();
        entity.setNumDocu(responseReniec.getNumeroDocumento());
        entity.setNombres(responseReniec.getNombres());
        entity.setApePat(responseReniec.getApellidoPaterno());
        entity.setApeMat(responseReniec.getApellidoMaterno());
        entity.setEstado(Constants.STATUS_ACTIVE);
        entity.setUsuaCrea(Constants.AUDIT_ADMIN);
        entity.setDateCreate(getTimestamp());
        entity.setTipoPersona(tipoPersona);
        entity.setTipoDocumento(tipoDocumento);
        return entity;
    }

    private PersonaEntity getEntityUpdate(ResponseReniec responseReniec, PersonDto personDto, PersonaEntity personaEntity){
        personaEntity.setNombres(personDto.getNombres()!=null ? personDto.getNombres(): responseReniec.getNombres());
        personaEntity.setApePat(personDto.getApePat()!=null ? personDto.getApePat(): responseReniec.getApellidoPaterno());
        personaEntity.setApeMat(personDto.getApeMat()!=null ? personaEntity.getApeMat() : responseReniec.getApellidoMaterno());
        personaEntity.setUsuaModif(Constants.AUDIT_ADMIN);
        personaEntity.setDateModif(getTimestamp());
        return  personaEntity;
    }

    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTime);
        return timestamp;
    }
}
