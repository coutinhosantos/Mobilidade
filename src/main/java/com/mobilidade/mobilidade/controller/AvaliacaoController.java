package com.mobilidade.mobilidade.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobilidade.mobilidade.constants.TIPO_AVALIACAO;
import com.mobilidade.mobilidade.model.Avaliacao;
import com.mobilidade.mobilidade.model.AvaliacaoDTO;
import com.mobilidade.mobilidade.service.AvaliacaoService;
import com.mongodb.client.result.DeleteResult;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/avaliacao")
public class AvaliacaoController {
    
    private AvaliacaoService service;

    @PostMapping("/avaliar")
    public ResponseEntity<Avaliacao> avaliar(@RequestBody AvaliacaoDTO avaliacao) {

//        service.deletAll("jefcout@gmail.com");
//        for (int i = 0; i < 30; i++) {
//            avaliacao.setEmail("jefcout@gmail.com");
//            avaliacao.setTipoAvaliacao(TIPO_AVALIACAO.ELOGIO.getDescricao());
//            avaliacao.setRating(i/2==0 ? Double.valueOf(5) : Double.valueOf(4));
//            service.avaliar(avaliacao);
//        }
//        
//        for (int i = 0; i < 30; i++) {
//            avaliacao.setEmail("jefcout@gmail.com");
//            avaliacao.setTipoAvaliacao(TIPO_AVALIACAO.SUGESTAO.getDescricao());
//            avaliacao.setRating(i/2==0 ? Double.valueOf(5) : Double.valueOf(4));
//            service.avaliar(avaliacao);
//        }
//        
//        for (int i = 0; i < 30; i++) {
//            avaliacao.setEmail("jefcout@gmail.com");
//            avaliacao.setTipoAvaliacao(TIPO_AVALIACAO.RECLAMACAO.getDescricao());
//            avaliacao.setRating(i/2==0 ? Double.valueOf(5) : Double.valueOf(4));
//            service.avaliar(avaliacao);
//        }
        
        return ResponseEntity.ok(service.avaliar(avaliacao));
    }
    
    @GetMapping("/avaliacao/{id}")
    public ResponseEntity<Avaliacao> avaliacaoPorId(@NotNull @PathVariable("id") String id) {
        return ResponseEntity.ok(service.avaliacaoPorId(id));
    }
    
    @GetMapping("/avaliacao")
    public ResponseEntity<List<Avaliacao>> avaliacao() {
        return ResponseEntity.ok(service.avaliacao());
    }
    
    @DeleteMapping("/avaliacao/{id}")
    public DeleteResult excluirAvaliacaoPorId(@NotNull @PathVariable("id") String id) {
        return service.excluirPorId(id);
    }
    
    @PutMapping("/alterarAvaliacao")
    public ResponseEntity<Avaliacao> alterarAvaliacao(@RequestBody AvaliacaoDTO avaliacao) {
        return ResponseEntity.ok(service.alterarAvaliacao(avaliacao));
    }
    
    @GetMapping("/avaliacaoPorUsuario/{login}")
    public ResponseEntity<List<Avaliacao>> avaliacaoPorUsuario(@NotNull @PathVariable("login") String login) {
        return ResponseEntity.ok(service.avaliacaoPorUsuario(login));
    }
    
    @GetMapping("/melhoresEmpresas")
    public ResponseEntity<List<Avaliacao>> melhoresEmpresas() {
        return ResponseEntity.ok(service.melhoresEmpresas());
    }
    
    @GetMapping("/pioresEmpresas")
    public ResponseEntity<List<Avaliacao>> pioresEmpresas() {
        return ResponseEntity.ok(service.pioresEmpresas());
    }
}
