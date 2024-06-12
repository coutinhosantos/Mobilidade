package com.mobilidade.mobilidade.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.mobilidade.mobilidade.constants.TIPO_AVALIACAO;
import com.mobilidade.mobilidade.constants.TIPO_TRANSPORTE;
import com.mobilidade.mobilidade.model.Avaliacao;
import com.mobilidade.mobilidade.model.AvaliacaoDTO;
import com.mobilidade.mobilidade.model.Empresa;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class AvaliacaoFactoryImpl implements AvaliacaoFactory {

    @Override
    public Avaliacao getAvaliacao(AvaliacaoDTO avaliacao) {
        return Avaliacao.builder().id(avaliacao.getId())
                                  .idUsuario(avaliacao.getIdUsuario())  
                                  .nome(avaliacao.getNome())
                                  .email(avaliacao.getEmail())
                                  .telefone(avaliacao.getTelefone())
                                  .tipoAvaliacao(getTipoAvaliacao(avaliacao.getTipoAvaliacao().replace("ç", "c").replace("ã", "a")))
                                  .tipoTransporte(getTipoTransporte(avaliacao.getTipoTransporte().replace("ô", "o").replace("Ô", "O")))
                                  .descricaoAvaliacao(avaliacao.getDescricaoAvaliacao())
                                  .rating(avaliacao.getRating())
                                  .empresa(Empresa.builder()
                                                  .nome(avaliacao.getEmpresa().getNome())
                                                  .identificador(avaliacao.getEmpresa().getIdentificador())
                                                  .build())
                                  .dataRegistro(LocalDateTime.now())
                                  .build();
    }

    private String getTipoTransporte(String tipoTransporte) {
        if(tipoTransporte.equalsIgnoreCase(TIPO_TRANSPORTE.ONIBUS.getDescricao())) {
            return TIPO_TRANSPORTE.ONIBUS.getDescricao();
        }else if(tipoTransporte.equalsIgnoreCase(TIPO_TRANSPORTE.TREM.getDescricao())) {
            return TIPO_TRANSPORTE.TREM.getDescricao();
        }else if(tipoTransporte.equalsIgnoreCase(TIPO_TRANSPORTE.BARCA.getDescricao())) {
            return TIPO_TRANSPORTE.BARCA.getDescricao();
        }else if(tipoTransporte.equalsIgnoreCase(TIPO_TRANSPORTE.METRO.getDescricao())) {
            return TIPO_TRANSPORTE.METRO.getDescricao();
        }else {
            return null;
        }
    }

    private String getTipoAvaliacao(String tipoAvaliacao) {
        if(tipoAvaliacao.equalsIgnoreCase(TIPO_AVALIACAO.ELOGIO.getDescricao())) {
            return TIPO_AVALIACAO.ELOGIO.getDescricao();
        }else if(tipoAvaliacao.equalsIgnoreCase(TIPO_AVALIACAO.RECLAMACAO.getDescricao())) {
            return TIPO_AVALIACAO.RECLAMACAO.getDescricao();
        }else if(tipoAvaliacao.equalsIgnoreCase(TIPO_AVALIACAO.SUGESTAO.getDescricao())) {
            return TIPO_AVALIACAO.SUGESTAO.getDescricao();
        }else {
            return null;
        }
    }
}
