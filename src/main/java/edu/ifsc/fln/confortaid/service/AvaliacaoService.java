package edu.ifsc.fln.confortaid.service;

import edu.ifsc.fln.confortaid.model.Avaliacao;
import edu.ifsc.fln.confortaid.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public List<Avaliacao> listarTodas() {
        return avaliacaoRepository.findAll();
    }

    public List<Avaliacao> buscarPorServicoId(int servicoId) {
        return avaliacaoRepository.findByServicoId(servicoId);
    }
}

