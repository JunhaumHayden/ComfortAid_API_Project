package edu.ifsc.fln.confortaid.controller;

import edu.ifsc.fln.confortaid.exception.HorarioOcupadoException;
import edu.ifsc.fln.confortaid.model.Agendamento;
import edu.ifsc.fln.confortaid.model.Cliente;
import edu.ifsc.fln.confortaid.model.Servico;
import edu.ifsc.fln.confortaid.repository.AgendamentoRepository;
import edu.ifsc.fln.confortaid.repository.ClienteRepository;
import edu.ifsc.fln.confortaid.repository.ProfissionalRepository;
import edu.ifsc.fln.confortaid.repository.ServicoRepository;
import edu.ifsc.fln.confortaid.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoRepository agendamentoRepository;
    @Autowired
    private ServicoRepository servicoRepository;
    @Autowired
    private ProfissionalRepository profissionalRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private AgendamentoService agendamentoService;

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
        agendamento.setServico(servico);

        Agendamento novoAgendamento = agendamentoService.agendar(agendamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAgendamento);
    }

    @PutMapping("/reagendar/{id}")
    public ResponseEntity<Agendamento> reagendar(@PathVariable Integer id, @RequestBody Agendamento agendamentoDetails) {
        return agendamentoRepository.findById(id)
                .map(agendamento -> {
                    Agendamento agendamentoReagendado = agendamentoService.reagendar(agendamentoDetails);
                    return ResponseEntity.ok(agendamentoReagendado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> cancelar(@PathVariable Integer id) {
        return agendamentoRepository.findById(id)
                .map(agendamento -> {
                    agendamentoService.alterarStatus(agendamento, Agendamento.Status.CANCELADO);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<Agendamento> alterarStatus(@PathVariable Integer id, @RequestBody Agendamento agendamentoDetails) {
        return agendamentoRepository.findById(id)
                .map(agendamento -> {
                    agendamentoService.alterarStatus(agendamento, agendamentoDetails.getStatus());
                    return ResponseEntity.ok(agendamento);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @ExceptionHandler(HorarioOcupadoException.class)
    public ResponseEntity<String> handleHorarioOcupadoException(HorarioOcupadoException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}
