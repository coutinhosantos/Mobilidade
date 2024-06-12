package com.mobilidade.mobilidade.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.google.gson.annotations.SerializedName;

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
@Document(collection = "avaliacao")
public class Avaliacao {
    
    @Id
    private String id;
    
    @Field(name = "idUsuario")
    @SerializedName("idUsuario")
    private String idUsuario;
    
    @Field(name = "nome")
    @SerializedName("nome")
    private String nome;
    
    @Field(name = "email")
    @SerializedName("email")
    private String email;
    
    @Field(name = "telefone")
    @SerializedName("telefone")
    private String telefone;
    
    @Field(name = "tipoTransporte")
    @SerializedName("tipoTransporte")
    private String tipoTransporte;
    
    @Field(name = "tipoAvaliacao")
    @SerializedName("tipoAvaliacao")
    private String tipoAvaliacao;
    
    @Field(name = "empresa")
    @SerializedName("empresa")
    private Empresa empresa;
    
    @Field(name = "descricaoAvaliacao")
    @SerializedName("descricaoAvaliacao")
    private String descricaoAvaliacao;
    
    @Field(name = "rating")
    @SerializedName("rating")
    private Double rating;
    
    @Field(name = "dataRegistro")
    @SerializedName("dataRegistro")
    private LocalDateTime dataRegistro;
}
