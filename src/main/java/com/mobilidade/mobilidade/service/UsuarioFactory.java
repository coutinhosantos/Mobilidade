package com.mobilidade.mobilidade.service;

import com.mobilidade.mobilidade.model.Usuario;
import com.mobilidade.mobilidade.model.UsuarioDTO;

public interface UsuarioFactory {
    public Usuario getUsuario(UsuarioDTO usuario);
    public String encript(String senha);
    public Boolean validaSenha(String senhaBruta, String senhaCriptografada);
}
