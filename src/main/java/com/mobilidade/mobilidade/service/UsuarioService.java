package com.mobilidade.mobilidade.service;

import java.util.List;

import com.mobilidade.mobilidade.model.Usuario;
import com.mobilidade.mobilidade.model.UsuarioDTO;

public interface UsuarioService {
    public Usuario cadastrar(UsuarioDTO usuario);
    public Usuario login(UsuarioDTO usuario);
    public List<Usuario> listar();
    public Usuario alterar(UsuarioDTO usuario);
}
