package com.mobilidade.mobilidade.service;

import com.mobilidade.mobilidade.model.Avaliacao;
import com.mobilidade.mobilidade.model.AvaliacaoDTO;

public interface AvaliacaoFactory {
    public Avaliacao getAvaliacao(AvaliacaoDTO avaliacao);
}
