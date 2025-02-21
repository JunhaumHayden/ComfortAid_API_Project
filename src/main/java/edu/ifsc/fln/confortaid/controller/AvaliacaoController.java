package edu.ifsc.fln.confortaid.controller;

import edu.ifsc.fln.confortaid.model.Avaliacao;
import edu.ifsc.fln.confortaid.model.Servico;
import edu.ifsc.fln.confortaid.repository.AvaliacaoRepository;
import edu.ifsc.fln.confortaid.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private AvaliacaoService avaliacaoService;
    @GetMapping
    public List<Avaliacao> listarTodosOuPorServicoId(@RequestParam(required = false) Long servicoId) {
        if (servicoId != null) {
            return avaliacaoService.buscarPorServicoId(servicoId.intValue());
        }
        return avaliacaoService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> buscarPorId(@PathVariable Integer id) {
        Optional<Avaliacao> avaliacao = avaliacaoRepository.findById(id);
        return avaliacao.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Avaliacao> criar(@RequestBody Avaliacao avaliacao) {
        Avaliacao savedAvaliacao = avaliacaoRepository.save(avaliacao);
        return new ResponseEntity<>(savedAvaliacao, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Avaliacao> atualizar(@PathVariable Integer id, @RequestBody Avaliacao avaliacaoDetails) {
        Optional<Avaliacao> avaliacao = avaliacaoRepository.findById(id);
        if (avaliacao.isPresent()) {
            Avaliacao updatedAvaliacao = avaliacao.get();
            updatedAvaliacao.setCliente(avaliacaoDetails.getCliente());
            updatedAvaliacao.setServico(avaliacaoDetails.getServico());
            updatedAvaliacao.setDataHora(avaliacaoDetails.getDataHora());
            updatedAvaliacao.setComentario(avaliacaoDetails.getComentario());
            updatedAvaliacao.setNota(avaliacaoDetails.getNota());
            avaliacaoRepository.save(updatedAvaliacao);
            return ResponseEntity.ok(updatedAvaliacao);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        Optional<Avaliacao> avaliacao = avaliacaoRepository.findById(id);
        if (avaliacao.isPresent()) {
            avaliacaoRepository.delete(avaliacao.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
