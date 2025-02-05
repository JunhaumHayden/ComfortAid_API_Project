package edu.ifsc.fln.confortaid.controller;

import edu.ifsc.fln.confortaid.model.Agendamento;
import edu.ifsc.fln.confortaid.model.Cliente;
import edu.ifsc.fln.confortaid.model.Profissional;
import edu.ifsc.fln.confortaid.model.Servico;
import edu.ifsc.fln.confortaid.repository.AgendamentoRepository;
import edu.ifsc.fln.confortaid.repository.ClienteRepository;
import edu.ifsc.fln.confortaid.repository.ProfissionalRepository;
import edu.ifsc.fln.confortaid.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoRepository agendamentoRepository;
    @Autowired
    private ServicoRepository servicoRepository;
    @Autowired
    private ProfissionalRepository profissionalRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Agendamento> listarTodos() {
        return agendamentoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> buscarPorId(@PathVariable Integer id) {
        return agendamentoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/profissional/{profissionalId}")
public List<Agendamento> listarPorProfissional(@PathVariable Integer profissionalId) {
    return agendamentoRepository.findAll().stream()
            .filter(agendamento -> agendamento.getServico().getProfissional().getId().equals(profissionalId))
            .collect(Collectors.toList());
}

    @GetMapping("/cliente/{clienteId}")
    public List<Agendamento> listarPorCliente(@PathVariable Integer clienteId) {
        return agendamentoRepository.findAll().stream()
                .filter(agendamento -> agendamento.getCliente().getId().equals(clienteId))
                .collect(Collectors.toList());
    }


    @PostMapping
    public ResponseEntity<Agendamento> criar(@RequestBody Agendamento agendamento) {
        Cliente cliente = clienteRepository.findById(agendamento.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        agendamento.setCliente(cliente);

        Servico servico = servicoRepository.findById(agendamento.getServico().getId())
                .orElseThrow(() -> new RuntimeException("Servico não encontrado"));
        Profissional profissional = profissionalRepository.findById(servico.getProfissional().getId())
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));
        servico.setProfissional(profissional);
        agendamento.setServico(servico);

        Agendamento novoAgendamento = agendamentoRepository.save(agendamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAgendamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agendamento> atualizar(@PathVariable Integer id, @RequestBody Agendamento agendamentoAtualizado) {
        return agendamentoRepository.findById(id)
                .map(agendamento -> {
                    agendamento.setDataHora(agendamentoAtualizado.getDataHora());
                    agendamento.setServico(agendamentoAtualizado.getServico());
                    return ResponseEntity.ok(agendamentoRepository.save(agendamento));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluir(@PathVariable Integer id) {
        return agendamentoRepository.findById(id)
                .map(agendamento -> {
                    agendamentoRepository.delete(agendamento);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
