package com.mobilidade.mobilidade.service;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.mobilidade.mobilidade.model.Usuario;
import com.mobilidade.mobilidade.model.UsuarioDTO;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    private final MongoTemplate mongoTemplate;

    private UsuarioFactory usuarioFactory;
    
    public UsuarioServiceImpl(MongoTemplate mongoTemplate, UsuarioFactory usuarioFactory) {
        super();
        this.mongoTemplate = mongoTemplate;
        this.usuarioFactory = usuarioFactory;
    }

    @Override
    public Usuario cadastrar(UsuarioDTO usuario) {
        List<Usuario> lst = mongoTemplate.findAll(Usuario.class);
        for (Usuario u : lst) {
            if(u.getLogin().equalsIgnoreCase(usuario.getLogin())) {
                return null;
            }
        }
        
        return mongoTemplate.save(usuarioFactory.getUsuario(usuario));
    }

    @Override
    public Usuario login(UsuarioDTO usuario) {
        Query query = new Query();
        Usuario u = usuarioFactory.getUsuario(usuario);
        query.addCriteria(Criteria.where("login").in(u.getLogin()));
        Usuario usuarioMongo = mongoTemplate.findOne(query, Usuario.class, "usuario");
        
        if(null != usuarioMongo && usuarioFactory.validaSenha(usuario.getSenha(),usuarioMongo.getSenha())) {
            usuarioMongo.setSenha(null);
            return usuarioMongo;
        }
        
        return null;
    }

    @Override
    public List<Usuario> listar() {
        return mongoTemplate.findAll(Usuario.class);
    }

    @Override
    public Usuario alterar(UsuarioDTO usuario) {
        return mongoTemplate.save(usuarioFactory.getUsuario(usuario));
    }
}
