package com.mobilidade.mobilidade.model;

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
public class Empresa {
    
    @Field(name = "nome")
    @SerializedName("nome")
    private String nome;
    
    @Field(name = "identificador")
    @SerializedName("identificador")
    private String identificador;

}
