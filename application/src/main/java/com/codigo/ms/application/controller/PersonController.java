package com.codigo.ms.application.controller;

import com.codigo.ms.domain.agregates.dto.PersonDto;
import com.codigo.ms.domain.agregates.request.RequestPersona;
import com.codigo.ms.domain.ports.in.PersonaServiceIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/persona")
@RequiredArgsConstructor
public class PersonController {

    private final PersonaServiceIn personaServiceIn;

    @PostMapping("/registrar")
    public ResponseEntity<PersonDto> registrar(@RequestBody RequestPersona requestPersona){
        return  ResponseEntity.status(HttpStatus.CREATED)
                .body(personaServiceIn.createPersonIn(requestPersona));
    }

    @GetMapping("/{numDoc}")
    public ResponseEntity<PersonDto> obtener(@PathVariable String numDoc){
        return ResponseEntity.status(HttpStatus.OK).body(personaServiceIn.getPersonIn(numDoc).get());
    }

    @GetMapping("/activas")
    public  ResponseEntity<List<PersonDto>> obtenerActivos(){
        return ResponseEntity.status(HttpStatus.OK).body(personaServiceIn.getPersonsByStateIn(1));
    }

    @PutMapping("/actualizar/{id}")
    public  ResponseEntity<PersonDto> actualizar(@PathVariable Long id, @RequestBody PersonDto personDto){
        return  ResponseEntity.status(HttpStatus.OK).body(personaServiceIn.updatePersonIn(id,personDto));
    }

    @DeleteMapping("/eliminar/{id}")
    public  ResponseEntity<PersonDto> eliminar(@PathVariable Long id){
        return  ResponseEntity.status(HttpStatus.OK).body(personaServiceIn.deletePersonIn(id));
    }
}
