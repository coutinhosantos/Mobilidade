package com.mobilidade.mobilidade.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TIPO_AVALIACAO {

    ELOGIO("Elogio"),
    RECLAMACAO("Reclamacao"),
    SUGESTAO("Sugestao");

    private final String descricao;
}
