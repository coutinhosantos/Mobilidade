package com.mobilidade.mobilidade.service;

import java.util.List;

import com.mobilidade.mobilidade.model.Avaliacao;
import com.mobilidade.mobilidade.model.AvaliacaoDTO;
import com.mongodb.client.result.DeleteResult;

public interface AvaliacaoService {
    public Avaliacao avaliar(AvaliacaoDTO avaliacao);
    public Avaliacao avaliacaoPorId(String id);
    public List<Avaliacao> avaliacao();
    public DeleteResult excluirPorId(String id);
    public Avaliacao alterarAvaliacao(AvaliacaoDTO avaliacao);
    public List<Avaliacao> avaliacaoPorUsuario(String login);
    public List<Avaliacao> melhoresEmpresas();
    public List<Avaliacao> pioresEmpresas();
    
    public void deletAll(String email);
}
