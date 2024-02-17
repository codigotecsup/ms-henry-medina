package com.codigo.ms.domain.ports.out;

import com.codigo.ms.domain.agregates.response.ResponseReniec;

public interface RestReniecOut {
    ResponseReniec getInfoReniec(String numDoc);
}
