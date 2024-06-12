package com.mobilidade.mobilidade.service;

import java.util.List;

import com.mobilidade.mobilidade.model.Ranking;

public interface RankingService {
    public List<Ranking> melhoresEmpresas();
    public List<Ranking> pioresEmpresas();
}
