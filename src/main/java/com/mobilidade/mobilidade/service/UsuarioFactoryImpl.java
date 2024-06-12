package com.mobilidade.mobilidade.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.mobilidade.mobilidade.model.Usuario;
import com.mobilidade.mobilidade.model.UsuarioDTO;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class UsuarioFactoryImpl implements UsuarioFactory {

    @Override
    public Usuario getUsuario(UsuarioDTO usuario) {
        return Usuario.builder()
                      .id(usuario.getId())
                      .nome(usuario.getNome())
                      .login(usuario.getLogin())
                      .senha(new BCryptPasswordEncoder().encode(usuario.getSenha()))
                      .dataCadastro(LocalDateTime.now())
                      .build();
    }

    @Override
    public Boolean validaSenha(String senhaBruta, String senhaCriptografada) {
        return new BCryptPasswordEncoder().matches(senhaBruta, senhaCriptografada);
    }
    
    @Override
    public String encript(String senha) {
        try {
            BigInteger hash = new BigInteger(1, MessageDigest.getInstance("MD5").digest(senha.getBytes()));
            return hash.toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

}
