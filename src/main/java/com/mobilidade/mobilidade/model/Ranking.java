package com.mobilidade.mobilidade.model;

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
@Document(collection = "ranking")
public class Ranking {
    
    @Id
    private String id;
    
    @Field(name = "empresa")
    @SerializedName("empresa")
    private String empresa;
    
    @Field(name = "pontuacao")
    @SerializedName("pontuacao")
    private Double pontuacao;
}
