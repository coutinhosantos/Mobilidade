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
import com.mobilidade.mobilidade.model.Ranking;

@Service
public class RankingServiceImpl implements RankingService{
    
    private final MongoTemplate mongoTemplate;

    @Autowired
    public RankingServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Ranking> melhoresEmpresas() {
        
        Query query = new Query();
        query.addCriteria(Criteria.where("tipoAvaliacao").is(TIPO_AVALIACAO.ELOGIO.getDescricao()));
        List<Avaliacao> list = mongoTemplate.find(query, Avaliacao.class);
        
        List<String> empresas = new ArrayList<>();
        
        for (Avaliacao avaliacao : list) {
            if(!empresas.contains(avaliacao.getEmpresa().getNome())) {
                empresas.add(avaliacao.getEmpresa().getNome());
            }
        }
        
        for (String empresa : empresas) {
            mongoTemplate.findAllAndRemove(new Query(Criteria.where("empresa").is(empresa)), Ranking.class);
        }
        
        for (String empresa : empresas) {
            for (Avaliacao avaliacao : list) {
                if(avaliacao.getEmpresa().getNome().equalsIgnoreCase(empresa)) {
                    Ranking ranking = mongoTemplate.findOne(new Query(Criteria.where("empresa").is(empresa)), Ranking.class);
                    
                    if(null==ranking) {
                        mongoTemplate.save(Ranking.builder().empresa(avaliacao.getEmpresa().getNome())
                                                            .pontuacao(avaliacao.getRating())
                                                            .build());
                    }else {
                        mongoTemplate.save(Ranking.builder().id(ranking.getId())
                                                            .empresa(avaliacao.getEmpresa().getNome())
                                                            .pontuacao(ranking.getPontuacao()+avaliacao.getRating())
                                                            .build());
                    }
                }
            }
        }
        
        return mongoTemplate.findAllAndRemove(new Query().with(Sort.by("pontuacao").descending()), Ranking.class);
    }

    @Override
    public List<Ranking> pioresEmpresas() {
        Query query = new Query();
        query.addCriteria(Criteria.where("tipoAvaliacao").is(TIPO_AVALIACAO.RECLAMACAO.getDescricao()));
        List<Avaliacao> list = mongoTemplate.find(query, Avaliacao.class);
        
        List<String> empresas = new ArrayList<>();
        
        for (Avaliacao avaliacao : list) {
            if(!empresas.contains(avaliacao.getEmpresa().getNome())) {
                empresas.add(avaliacao.getEmpresa().getNome());
            }
        }
        
        for (String empresa : empresas) {
            mongoTemplate.findAllAndRemove(new Query(Criteria.where("empresa").is(empresa)), Ranking.class);
        }
        
        for (String empresa : empresas) {
            for (Avaliacao avaliacao : list) {
                if(avaliacao.getEmpresa().getNome().equalsIgnoreCase(empresa)) {
                    Ranking ranking = mongoTemplate.findOne(new Query(Criteria.where("empresa").is(empresa)), Ranking.class);
                    if(null==ranking) {
                        mongoTemplate.save(Ranking.builder().empresa(avaliacao.getEmpresa().getNome())
                                                            .pontuacao(avaliacao.getRating())
                                                            .build());
                    }else {
                        mongoTemplate.save(Ranking.builder().id(ranking.getId())
                                                            .empresa(avaliacao.getEmpresa().getNome())
                                                            .pontuacao(ranking.getPontuacao()+avaliacao.getRating())
                                                            .build());
                    }
                }
            }
        }
        
        return mongoTemplate.findAllAndRemove(new Query().with(Sort.by("pontuacao").ascending()), Ranking.class);
    }

}
