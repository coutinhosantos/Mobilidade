package com.mobilidade.mobilidade.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobilidade.mobilidade.model.Ranking;
import com.mobilidade.mobilidade.service.RankingService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/ranking")
public class RankingController {
    
    RankingService service;

    @GetMapping("/melhoresEmpresas")
    public ResponseEntity<List<Ranking>> melhoresEmpresas() {
        return ResponseEntity.ok(service.melhoresEmpresas());
    }
    
    @GetMapping("/pioresEmpresas")
    public ResponseEntity<List<Ranking>> pioresEmpresas() {
        return ResponseEntity.ok(service.pioresEmpresas());
    }
}
