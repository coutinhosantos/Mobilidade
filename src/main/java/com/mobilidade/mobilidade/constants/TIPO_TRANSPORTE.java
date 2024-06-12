package com.mobilidade.mobilidade.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TIPO_TRANSPORTE {

    ONIBUS("Onibus"),
    TREM("Trem"),
    BARCA("Barca"),
    METRO("Metro");

    private final String descricao;
}
