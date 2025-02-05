package edu.ifsc.fln.confortaid.controller;

import edu.ifsc.fln.confortaid.model.Profissional;
import edu.ifsc.fln.confortaid.model.Servico;
import edu.ifsc.fln.confortaid.repository.ProfissionalRepository;
import edu.ifsc.fln.confortaid.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicos")
public class ServicoController {

    @Autowired
    private ServicoRepository servicoRepository;
    @Autowired
    private ProfissionalRepository profissionalRepository;

    @GetMapping
    public List<Servico> listarTodos() {
        return servicoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> buscarPorId(@PathVariable Integer id) {
        return servicoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Servico> criar(@RequestBody Servico servico) {
        Profissional profissional = profissionalRepository.findById(servico.getProfissional().getId())
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));
        servico.setProfissional(profissional);

        Servico novoServico = servicoRepository.save(servico);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoServico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servico> atualizar(@PathVariable Integer id, @RequestBody Servico servicoAtualizado) {
        Profissional profissional = profissionalRepository.findById(servicoAtualizado.getProfissional().getId())
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));
        servicoAtualizado.setProfissional(profissional);
        return servicoRepository.findById(id)
                .map(servico -> {
                    servico.setNome(servicoAtualizado.getNome());
                    servico.setDescricao(servicoAtualizado.getDescricao());
                    servico.setPreco(servicoAtualizado.getPreco());
                    servico.setDuracao(servicoAtualizado.getDuracao());
                    servico.setSituacao(servicoAtualizado.getSituacao());
                    servico.setDataDisponivel(servicoAtualizado.getDataDisponivel());
                    servico.setProfissional(servicoAtualizado.getProfissional());
                    return ResponseEntity.ok(servicoRepository.save(servico));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluir(@PathVariable Integer id) {
        return servicoRepository.findById(id)
                .map(servico -> {
                    servicoRepository.delete(servico);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
