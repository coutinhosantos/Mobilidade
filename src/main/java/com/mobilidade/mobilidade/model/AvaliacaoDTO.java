package com.mobilidade.mobilidade.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AvaliacaoDTO {

    private String id;
    private String idUsuario;
    private String nome;
    private String email;
    private String telefone;
    private String tipoTransporte;
    private String tipoAvaliacao;
    private EmpresaDTO empresa;
    private String descricaoAvaliacao;
    private Double rating;
}
