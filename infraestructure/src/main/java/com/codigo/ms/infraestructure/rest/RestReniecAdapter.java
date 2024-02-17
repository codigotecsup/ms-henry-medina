package com.codigo.ms.infraestructure.rest;

import com.codigo.ms.domain.agregates.response.ResponseReniec;
import com.codigo.ms.domain.ports.out.RestReniecOut;
import com.codigo.ms.infraestructure.rest.client.ClientReniec;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestReniecAdapter implements RestReniecOut {
    private final ClientReniec clientReniec;
    @Value("${token.api}")
    private String tokenApi;

    @Override
    public ResponseReniec getInfoReniec(String numDoc) {
        String authorization="Bearer " + tokenApi;
        ResponseReniec responseReniec = clientReniec.getInfoReniec(numDoc,authorization);
        return  responseReniec;
    }
}
