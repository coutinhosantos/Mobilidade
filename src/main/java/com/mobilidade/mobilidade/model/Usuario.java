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
@Document(collection = "usuario")
public class Usuario {
    
    @Id
    private String id;
    
    @Field(name = "nome")
    @SerializedName("nome")
    private String nome;
    
    @Field(name = "login")
    @SerializedName("login")
    private String login;

    @Field(name = "senha")
    @SerializedName("senha")
    private String senha;
    
    @Field(name = "dataCadastro")
    @SerializedName("dataCadastro")
    private LocalDateTime dataCadastro;
}
