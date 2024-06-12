package com.mobilidade.mobilidade.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobilidade.mobilidade.model.Usuario;
import com.mobilidade.mobilidade.model.UsuarioDTO;
import com.mobilidade.mobilidade.service.UsuarioService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/usuario")
public class UsuarioController {

    private UsuarioService service;

    @CrossOrigin(origins = "http://localhost:8081")
    @PostMapping("/cadastrar")
    public String cadastrar(@RequestBody UsuarioDTO usuario) {
        if(null!=service.cadastrar(usuario)) {
           return new StringBuilder("Usuario Cadastrado com sucesso").toString();
        }else {
            return new StringBuilder("Usuario já existe no sistema").toString();
        }
    }
    
    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(service.listar());
    }
    
    @CrossOrigin(origins = "http://localhost:8081")
    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody UsuarioDTO usuario) {
        return ResponseEntity.ok(service.login(usuario));
    }
    
    @CrossOrigin(origins = "http://localhost:8081")
    @PutMapping("/alterar")
    public String alterar(@RequestBody UsuarioDTO usuario) {
        if(null!=service.alterar(usuario)) {
           return new StringBuilder("Usuario Alterado com sucesso").toString();
        }else {
            return new StringBuilder("Erro ao alterar Usuario").toString();
        }
    }
}
