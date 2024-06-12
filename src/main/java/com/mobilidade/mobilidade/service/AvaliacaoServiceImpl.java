package com.mobilidade.mobilidade.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.mobilidade.mobilidade.constants.TIPO_AVALIACAO;
import com.mobilidade.mobilidade.model.Avaliacao;
import com.mobilidade.mobilidade.model.AvaliacaoDTO;
import com.mobilidade.mobilidade.model.Ranking;
import com.mongodb.client.result.DeleteResult;

@Service
public class AvaliacaoServiceImpl implements AvaliacaoService {

    private final MongoTemplate mongoTemplate;

    private AvaliacaoFactory avaliacaoFactory;

    @Autowired
    public AvaliacaoServiceImpl(MongoTemplate mongoTemplate, AvaliacaoFactory avaliacaoFactory) {
        this.mongoTemplate = mongoTemplate;
        this.avaliacaoFactory = avaliacaoFactory;
    }

    @Override
    public Avaliacao avaliar(AvaliacaoDTO avaliacao) {
        return mongoTemplate.save(avaliacaoFactory.getAvaliacao(avaliacao));
    }

    @Override
    public Avaliacao avaliacaoPorId(String id) {
        return mongoTemplate.findById(id, Avaliacao.class);
    }

    @Override
    public List<Avaliacao> avaliacao() {
        return mongoTemplate.findAll(Avaliacao.class);
    }

    @Override
    public DeleteResult excluirPorId(String id) {
        return mongoTemplate.remove(new Query(Criteria.where("id").is(id)),Avaliacao.class);
    }

    @Override
    public Avaliacao alterarAvaliacao(AvaliacaoDTO avaliacao) {
        return mongoTemplate.save(avaliacaoFactory.getAvaliacao(avaliacao));
    }

    @Override
    public List<Avaliacao> avaliacaoPorUsuario(String login) {
        return mongoTemplate.find(new Query(Criteria.where("email").is(login)), Avaliacao.class);
    }

    @Override
    public List<Avaliacao> melhoresEmpresas() {
        Query query = new Query();
        query.addCriteria(Criteria.where("tipoAvaliacao").is(TIPO_AVALIACAO.ELOGIO.getDescricao()));
        query.with(Sort.by("rating").descending());
        //query.limit(10);
        
        List<Avaliacao> list = mongoTemplate.find(query, Avaliacao.class);
        
        List<Avaliacao> lst = new ArrayList<>();
        
        List<String> empresas = new ArrayList<>();

        List<Ranking> rankings = new ArrayList<>();
        
        for (Avaliacao avaliacao : list) {
            if(!empresas.contains(avaliacao.getEmpresa().getNome())) {
                empresas.add(avaliacao.getEmpresa().getNome());
            }
        }

        for (String empresa : empresas) {
            Ranking ranking = (Ranking) mongoTemplate.find(new Query(Criteria.where("empresa").is(empresa)), Ranking.class);
            ranking.setEmpresa(empresa);
            ranking.setPontuacao(ranking.getPontuacao());
            mongoTemplate.save(ranking);
        }
        
        for (String empresa : empresas) {
            for (Avaliacao avaliacao : list) {
                if(avaliacao.getEmpresa().getNome().equalsIgnoreCase(empresa)) {
                    Ranking ranking = (Ranking) mongoTemplate.find(new Query(Criteria.where("empresa").is(empresa)), Ranking.class);
                    
                    if(null==ranking) {
                        ranking = new Ranking();
                        ranking.setEmpresa(avaliacao.getEmpresa().getNome());
                        ranking.setPontuacao(Double.valueOf(avaliacao.getRating()));
                        mongoTemplate.save(ranking);
                    }else {
                        ranking.setPontuacao(ranking.getPontuacao()+Double.valueOf(avaliacao.getRating()));
                        mongoTemplate.save(ranking);
                    }
                    
                    
                    rankings.add(ranking);
                }
            }
        }
        
        
        
        return list;
    }

    @Override
    public void deletAll(String email) {
        mongoTemplate.findAllAndRemove(new Query(Criteria.where("email").is(email)),Avaliacao.class);
    }

    @Override
    public List<Avaliacao> pioresEmpresas() {
        Query query = new Query();
        query.addCriteria(Criteria.where("tipoAvaliacao").is(TIPO_AVALIACAO.RECLAMACAO.getDescricao()));
        query.with(Sort.by("rating").ascending());
        query.limit(10);
        return mongoTemplate.find(query, Avaliacao.class);
    }
}
